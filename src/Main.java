import java.sql.Connection;
import java.sql.SQLException;

import static java.lang.System.out;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        UserDAO.createTable();

        User newUser = new User("Lennin", "lenninssp1021@gmail.com", "1234");
        UserDAO.createUser(newUser);
        User fetchedUser = UserDAO.getUserById(newUser.getId());
        out.println("Usuario leido desde DB: " + fetchedUser);

        TaskDAO.createTaskTable();

        Task newTask = new Task("Finish project", "I gotta finish the oop project", false, 1);
        TaskDAO.createTask(newTask);
        Task fetchedTask = TaskDAO.getTaskById(newTask.getId());
        out.println("Task retreived: " + fetchedTask);
    }
}