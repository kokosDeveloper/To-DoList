package servlets;

import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import services.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = "/auth/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void init() throws ServletException {
        super.init();
        userService = (UserService) getServletContext().getAttribute("userService");
        passwordEncoder = (BCryptPasswordEncoder) getServletContext().getAttribute("passwordEncoder");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/auth/login.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        final String password = req.getParameter("password");
        List<User> users = userService.getAll();
        Optional<User> optional = users.stream()
                .filter(u -> u.getLogin().equals(login))
                .filter(u -> passwordEncoder.matches(password, u.getPassword()))
                .findFirst();
        if(optional.isPresent()){
            session.setAttribute("userId", optional.get().getId());
            resp.sendRedirect("/secure/tasks");
        }
        else resp.sendRedirect("/auth/login");
    }


}
