package com.tasky.app.dao;

import com.tasky.app.model.Task;
import com.tasky.app.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TaskDAOTest {
    public static int userId;
    @BeforeAll
    public static void createTestUser() {
        UserDAO.deleteUser(null, "task_user");
        User user = new User("task_user", "task@test.com", "test123");
        UserDAO.createUser(user);
        userId = user.getId();
    }

    @Test
    public void testTaskLifecycle() {
        Task task = new Task("Write tests", "Check all lifecycle methods", false, userId, LocalDate.now().plusDays(1));
        assertTrue(task.getId() > 0, "Tasl should have an ID after creation");

        Task fetched = TaskDAO.getTaskById(task.getId());
        assertNotNull(fetched);
        assertEquals("Write tests", fetched.getTitle());

        task.setTitle("Updated title");
        task.setCompleted(true);
        TaskDAO.updateTask(task);

        Task updated = TaskDAO.getTaskById(task.getId());
        assertNotNull(updated, "Updated task shouldn't be null");
        assertEquals("Update title", updated.getTitle());
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
        String baseTitle = "UniteTestTask";
        int countBefore = TaskDAO.getTasksByUserId(userId, null, null).size();

        Task task1 = new Task(baseTitle + " A", "desc A", false, userId, LocalDate.now());
        Task task2 = new Task(baseTitle + " B", "desc B", true, userId, LocalDate.now());
        Task task3 = new Task("Othertask", "should be excluded", false, userId, LocalDate.now());

        TaskDAO.createTask(task1);
        TaskDAO.createTask(task2);
        TaskDAO.createTask(task3);

        var titleFiltered = TaskDAO.getTasksByUserId(userId, "UnitTestTask", null);
        assertTrue(titleFiltered.stream().anyMatch(t -> t.getTitle().contains("UnitTestTask")));
        assertFalse(titleFiltered.stream().anyMatch(t -> t.getTitle().equals("OtherTask")));

        var completed = TaskDAO.getTasksByUserId(userId, null, "completed");
        assertTrue(completed.stream().anyMatch(Task::isCompleted));
        assertFalse(completed.stream().anyMatch(t -> !t.isCompleted()));

        var pending = TaskDAO.getTasksByUserId(userId, null, "pending");
        assertTrue(pending.stream().anyMatch(t -> !t.isCompleted()));
        assertFalse(pending.stream().anyMatch(Task::isCompleted));
    }
}
