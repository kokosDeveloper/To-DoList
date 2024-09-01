package servlets.OperationServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.TaskService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = "/secure/operations/update")
public class UpdateTaskServlet extends HttpServlet {
    private TaskService taskService;

    @Override
    public void init() throws ServletException {
        super.init();
        taskService = (TaskService) getServletContext().getAttribute("taskService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/secure/operations/updateTask.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskId = req.getParameter("taskId");
        String completedParam = req.getParameter("completed");
        boolean completed = "true".equals(completedParam);
        taskService.updateStatus(UUID.fromString(taskId), completed);
        resp.sendRedirect("/secure/tasks");
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID id = UUID.fromString(req.getParameter("id"));
        taskService.delete(id);
        HttpSession session = req.getSession();
        session.setAttribute("successUpdated", "Задача изменена");
        resp.sendRedirect("/secure/tasks");
    }
}
