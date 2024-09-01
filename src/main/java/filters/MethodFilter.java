package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;
@WebFilter(urlPatterns = "/secure/tasks")
public class MethodFilter extends HttpFilter {
    private static final List<String> methods = List.of("DELETE");

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String method = req.getParameter("_method");
        if(method != null && methods.contains(method)){
            HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(req){
                @Override
                public String getMethod() {
                    return method;
                }
            };
            chain.doFilter(requestWrapper, res);
        }else chain.doFilter(req, res);
    }
}
