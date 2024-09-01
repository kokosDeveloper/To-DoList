package servlets;

import entity.Task;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.TaskService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(urlPatterns = "/secure/tasks")
public class ShowTaskServlet extends HttpServlet {
    private TaskService taskService;

    @Override
    public void init() throws ServletException {
        super.init();
        taskService = (TaskService) getServletContext().getAttribute("taskService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Task> tasks = taskService.getAll();
        List<Task> userTasks = tasks.stream()
                .filter(task -> task.getUserId().equals(session.getAttribute("userId")))
                .toList();
        session.setAttribute("userTasks", userTasks);
        req.getRequestDispatcher("/secure/tasks.jsp").forward(req, resp);
    }

}
