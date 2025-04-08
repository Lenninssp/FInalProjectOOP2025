<%@ page import="java.util.List" %>
<%@ page import="com.tasky.app.model.Task" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/header.jsp" %>

<form action="task-list" method="get" class="row g-2 mb-4">
    <div class="col-auto">
        <input type="text" name="query" class="form-control" placeholder="Search by title"
               value="<%= request.getParameter("query") != null ? request.getParameter("query") : ""%>">
    </div>
    <div class="col-auto">
        <select name="status" class="form-select">
            <option value="">All</option>
            <option value="completed" <%="completed".equals(request.getParameter("status")) ? "selected" : ""%>>
                Completed
            </option>
            <option value="pending" <%= "pending".equals(request.getParameter("status")) ? "selected" : ""%>>Pending
            </option>
        </select>
    </div>
    <div class="col-auto">
        <button type="submit" class="btn btn-primary">Filter</button>
    </div>
</form>

<h2>My Tasks</h2>…
<%
    List<Task> tasks = (List<Task>) request.getAttribute("tasks");
    if (tasks != null && !tasks.isEmpty()) {
%>
<div class="table-responsive">
    <table class="table table-bordered table-striped">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Description</th>
            <th>Completed</th>
            <th>Due date</th>
            <th>Days left</th>
        </tr>
        <%
            for (Task task : tasks) {
        %>
        <tr class="<%=
  task.getDueDate() == null ? "" :
  task.getDueDate().isBefore(LocalDate.now()) ? "table-danger" :
  task.getDueDate().isEqual(LocalDate.now()) ? "table-warning" :
  ""
  %>">
            <td><%= task.getId() %>
            </td>
            <td><%= task.getTitle() %>
            </td>
            <td><%= task.getDescription() %>
            </td>
            <td><%= task.isCompleted() %>
            </td>
            <td><%= task.getDueDate()%>
            </td>
            <td>
                <%
                    if (task.getDueDate() == null) {
                %>
                <span class="text-muted">—</span>
                <%
                } else {
                    long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), task.getDueDate());
                    if (daysLeft < 0) {
                %>
                <span class="text-danger">Overdue by <%= Math.abs(daysLeft) %> days</span>
                <%
                } else if (daysLeft == 0) {
                %>
                <span class="text-warning">Due today</span>
                <%
                } else {
                %>
                <span>Due in <%= daysLeft %> days</span>
                <%
                        }
                    }
                %>
            </td>
            <td>
                <form action="toggle-task" method="post" style="display:inline;">
                    <input type="hidden" name="taskId" value="<%= task.getId() %>">
                    <input type="hidden" name="completed" value="<%= task.isCompleted() %>">
                    <button type="submit">
                        <%= task.isCompleted() ? "↩️ Uncomplete" : "✅ Complete" %>
                    </button>
                </form>
                <form action="delete-task" method="post" style="display: inline"
                      onsubmit="return confirm('Are you sure you want to delete this task?');">
                    <input type="hidden" name="taskId" value="<%= task.getId() %>">
                    <button type="submit">🗑️ Delete</button>
                </form>
                <form action="edit-task" method="get" style="display:inline;">
                    <input type="hidden" name="taskId" value="<%= task.getId() %>">
                    <button type="submit">✏️ Edit</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>

</div>

<%
} else {
%>
<p>No tasks found.</p>
<%
    }
%>
<%@include file="common/footer.jsp" %>
