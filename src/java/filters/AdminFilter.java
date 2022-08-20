
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;
import models.User;
import services.AccountService;

/**
 *
 * @author manve
 */
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        
        String valid_user = (String) session.getAttribute("validUser");
        
        AccountService as = new AccountService();
        try {
            User user = as.getUser(valid_user);
            Role role = user.getRole();
            int roleID = role.getRoleId();
            if(roleID != 1){
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.sendRedirect("noaccess");
                return;
            }
            
        } catch (Exception ex) {
            Logger.getLogger(AdminFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        
    }
      
}
