package com.tasky.app.dao;

import com.tasky.app.model.Task;
import com.tasky.app.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TaskDAOTest {
    public static int userId;
    public static LocalDate date = LocalDate.now().plusDays(1);
    @BeforeAll
    public static void createTestUser() {
        UserDAO.deleteUser(null, "task_user");
        User user = new User("task_user", "task@test.com", "test123");
        UserDAO.createUser(user);
        userId = user.getId();
    }

    @Test
    public void testTaskLifecycle() {
        Task task = new Task("Write tests", "Check all lifecycle methods", false, userId, date);
        TaskDAO.createTask(task);

        assertTrue(task.getId() > 0, "Task should have an ID after creation");

        Task fetchedTask = TaskDAO.getTaskById(task.getId());
        assertNotNull(fetchedTask, "Task should be retrievable after creation");

        Task fetched = TaskDAO.getTaskById(task.getId());
        assertNotNull(fetched);
        assertEquals("Write tests", fetched.getTitle());

        task.setTitle("Updated title");
        task.setCompleted(true);
        TaskDAO.updateTask(task);

        Task updated = TaskDAO.getTaskById(task.getId());
        assertNotNull(updated, "Updated task shouldn't be null");
        assertEquals("Updated title", updated.getTitle());
        assertTrue(updated.isCompleted());

        TaskDAO.toggleTaskCompletion(task.getId(), updated.isCompleted());
        Task toggled = TaskDAO.getTaskById(task.getId());
        assertNotNull(toggled);
        assertFalse(toggled.isCompleted());

        TaskDAO.deleteTask(task.getId());
        Task deleted = TaskDAO.getTaskById(task.getId());
        assertNull(deleted, "Task should be deleted");
    }

    @Test
    public void taskGetTasksByUserIdWithFilter() {
        String baseTitle = "UnitTestTask";
        int countBefore = TaskDAO.getTasksByUserId(userId, null, null).size();

        Task task1 = new Task(baseTitle + " A", "desc A", false, userId, LocalDate.now());
        Task task2 = new Task(baseTitle + " B", "desc B", true, userId, LocalDate.now());
        Task task3 = new Task("Othertask", "should be excluded", false, userId, LocalDate.now());

        TaskDAO.createTask(task1);
        TaskDAO.createTask(task2);
        TaskDAO.createTask(task3);

        var titleFiltered = TaskDAO.getTasksByUserId(userId, "UnitTestTask", null);
        System.out.println("titleFiltered" + titleFiltered);
        assertTrue(titleFiltered.stream().anyMatch(t -> t.getTitle().contains("UnitTestTask")));
        assertFalse(titleFiltered.stream().anyMatch(t -> t.getTitle().equals("OtherTask")));

        var completed = TaskDAO.getTasksByUserId(userId, null, "completed");
        assertTrue(completed.stream().anyMatch(Task::isCompleted));
        assertFalse(completed.stream().anyMatch(t -> !t.isCompleted()));

        var pending = TaskDAO.getTasksByUserId(userId, null, "pending");
        assertTrue(pending.stream().anyMatch(t -> !t.isCompleted()));
        assertFalse(pending.stream().anyMatch(Task::isCompleted));
    }

    @Test
    public void testToggleTaskCompletion() {
        Task task = new Task("Toggle test", "should filp completion", false, userId, date);

        assertFalse(task.isCompleted());

        TaskDAO.createTask(task);
        assertNotNull(TaskDAO.getTaskById(task.getId()));
        TaskDAO.toggleTaskCompletion(task.getId(), task.isCompleted());
        Task toggleOnce = TaskDAO.getTaskById(task.getId());
        System.out.println(toggleOnce);
        assertNotNull(toggleOnce);
        assertTrue(toggleOnce.isCompleted(), "task should be back to pending");

        TaskDAO.deleteTask(task.getId());

    }


    @Test
    public void testDueDates() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        Task overdue = new Task("Overdue", "should properly test the due dates", false, userId, yesterday);
        Task todayTask = new Task("Today", "should properly test the due dates", false, userId, today);
        Task upcoming = new Task("Upcoming", "should properly test the due dates", false, userId, tomorrow);

        TaskDAO.createTask(overdue);
        TaskDAO.createTask(todayTask);
        TaskDAO.createTask(upcoming);

        var tasks = TaskDAO.getTasksByUserId(userId, null, "pending");

        assertTrue(tasks.stream().anyMatch(t -> t.getTitle().equals("Overdue")));
        assertTrue(tasks.stream().anyMatch(t -> t.getTitle().equals("Today")));
        assertTrue(tasks.stream().anyMatch(t -> t.getTitle().equals("Upcoming")));

        assertEquals("Overdue", tasks.getFirst().getTitle());

        TaskDAO.deleteTask(overdue.getId());
        TaskDAO.deleteTask(todayTask.getId());
        TaskDAO.deleteTask(upcoming.getId());
    }

    @Test
    public void testUpdateTask() {
        Task task = new Task("Original Title", "Original descirption", false, userId, date);
        TaskDAO.createTask(task);
        int taskId = task.getId();

        task.setTitle("Updated Title");
        task.setDescription("Updated description");
        task.setCompleted(true);
        task.setDueDate(date.plusDays(5));
        TaskDAO.updateTask(task);

        Task updated = TaskDAO.getTaskById(taskId);
        assertNotNull(updated, "updated task should not be null");
        assertEquals("Updated Title", updated.getTitle());
        assertEquals("Updated description", updated.getDescription());
        assertTrue(updated.isCompleted());
        assertEquals(date.plusDays(5), updated.getDueDate());

        TaskDAO.deleteTask(taskId);
    }

    @Test
    public void testDeleteTask(){
        Task task = new Task("Delete test", "will be removed", false, userId, date);
        TaskDAO.createTask(task);
        int taskId = task.getId();

        Task fetched = TaskDAO.getTaskById(taskId);
        assertNotNull(fetched, "Task should  exist before deletion");

        TaskDAO.deleteTask(taskId);
        Task afterDelete = TaskDAO.getTaskById(taskId);
        assertNull(afterDelete, "Task should be null after deletion");
    }
}
