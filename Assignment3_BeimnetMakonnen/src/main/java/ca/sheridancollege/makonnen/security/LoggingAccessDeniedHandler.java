package ca.sheridancollege.makonnen.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class LoggingAccessDeniedHandler implements AccessDeniedHandler {

	//Method handles bad logins and redirects to the Permission-Denied page if user is unauthorized
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, 
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null) {
			
			System.out.println(auth.getName() + " was trying to access protected resource: " 
					+ request.getRequestURI());
			
		}
		
		response.sendRedirect(request.getContextPath() + "/permission-denied");
	}
}
