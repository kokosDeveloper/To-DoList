package filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import validators.LoginValidator;
import validators.PasswordValidator;

import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = "/auth/registration")
public class RegistrationFilter extends HttpFilter {
    private LoginValidator loginValidator;
    private PasswordValidator passwordValidator;

    @Override
    public void init() throws ServletException {
        super.init();
        loginValidator = (LoginValidator) getServletContext().getAttribute("loginValidator");
        passwordValidator = (PasswordValidator) getServletContext().getAttribute("passwordValidator");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq= (HttpServletRequest) req;
        if(httpReq.getMethod().equals("POST")){
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            List<String> loginErrorMessages = loginValidator.validate(login);
            List<String> passwordErrorMessages = passwordValidator.validate(password);
            if(loginErrorMessages.isEmpty() && passwordErrorMessages.isEmpty()){
                chain.doFilter(req, res);
            }else {
                if(!loginErrorMessages.isEmpty())
                    req.setAttribute("loginErrorMessages", loginErrorMessages);
                if(!passwordErrorMessages.isEmpty())
                    req.setAttribute("passwordErrorMessages", passwordErrorMessages);
                httpReq.getRequestDispatcher("/auth/registration.jsp").forward(req, res);
            }
        }else chain.doFilter(req, res);
    }


}
