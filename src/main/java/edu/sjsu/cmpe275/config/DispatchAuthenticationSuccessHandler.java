package edu.sjsu.cmpe275.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import edu.sjsu.cmpe275.domain.User;

public class DispatchAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		Collection<GrantedAuthority> authCollection = (Collection<GrantedAuthority>)authentication.getAuthorities();
		HttpSession session = request.getSession();
		if (session == null) {
			return;
		}
		User u = new User();
		u.setEmail(authentication.getName());
		session.setAttribute("USER", u);
		for (GrantedAuthority ga : authCollection) {
			if (ga.getAuthority().equals("USER")) {
				u.setRole(ga.getAuthority());
				response.sendRedirect("/");
				return;
			} else if(ga.getAuthority().equals("ADMIN")) {
				u.setRole(ga.getAuthority());
				response.sendRedirect("/admin");
				return;
			}
		}

	}

}
