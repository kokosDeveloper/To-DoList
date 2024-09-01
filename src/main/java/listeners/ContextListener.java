package listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.TaskDao;
import dao.UserDao;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import services.TaskService;
import services.UserService;
import validators.LoginValidator;
import validators.PasswordValidator;

import java.io.File;
@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        File userFile = new File("C:\\Users\\ivank\\IdeaProjects\\To-DoList\\src\\main\\resources\\users.json");
        File tasksFile = new File("C:\\Users\\ivank\\IdeaProjects\\To-DoList\\src\\main\\resources\\tasks.json");
        ObjectMapper objectMapper = new ObjectMapper();
        UserDao userDao = new UserDao(objectMapper, userFile);
        TaskDao taskDao = new TaskDao(objectMapper, tasksFile);
        UserService userService = new UserService(userDao);
        TaskService taskService = new TaskService(taskDao);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        LoginValidator loginValidator = new LoginValidator(userService);
        PasswordValidator passwordValidator = new PasswordValidator();

        context.setAttribute("userService", userService);
        context.setAttribute("taskService", taskService);
        context.setAttribute("passwordEncoder", passwordEncoder);
        context.setAttribute("loginValidator", loginValidator);
        context.setAttribute("passwordValidator", passwordValidator);
    }
}
