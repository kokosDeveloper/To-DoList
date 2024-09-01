package servlets;

import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import services.UserService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = "/auth/registration")
public class RegistrationServlet extends HttpServlet {
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
        req.getRequestDispatcher("/auth/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String hashedPassword = passwordEncoder.encode(password);
        User user = User.builder()
                .id(UUID.randomUUID())
                .login(login)
                .password(hashedPassword)
                .build();
        userService.save(user);
        resp.sendRedirect("/auth/login");
    }
}
