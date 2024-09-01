package servlets.OperationServlets;

import entity.Task;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.TaskService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = "/secure/operations/add")
public class AddTaskServlet extends HttpServlet {
    private TaskService taskService;

    @Override
    public void init() throws ServletException {
        super.init();
        taskService = (TaskService) getServletContext().getAttribute("taskService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/secure/operations/addTask.jsp").forward(req, resp);
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
        session.setAttribute("successAdded", "Задача добавлена");
        resp.sendRedirect("/secure/tasks");
    }
}
