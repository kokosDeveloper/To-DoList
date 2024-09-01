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
public class TaskServlet extends HttpServlet {
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
        req.setAttribute("userTasks", userTasks);
        req.getRequestDispatcher("/secure/tasks.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String completedParam = req.getParameter("completed");
        boolean completed = "true".equals(completedParam);
        HttpSession session = req.getSession();
        Task task = Task.builder()
                .id(UUID.randomUUID())
                .title(title)
                .description(description)
                .completed(completed)
                .userId((UUID) session.getAttribute("userId"))
                .build();
        taskService.save(task);
        resp.sendRedirect("/secure/tasks");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID id = UUID.fromString(req.getParameter("id"));
        taskService.delete(id);
        resp.sendRedirect("/secure/tasks");
    }
}
