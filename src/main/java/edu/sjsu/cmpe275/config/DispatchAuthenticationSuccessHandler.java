package edu.sjsu.cmpe275.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class DispatchAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		Collection<GrantedAuthority> authCollection = (Collection<GrantedAuthority>)authentication.getAuthorities();
		for (GrantedAuthority ga : authCollection) {
			if (ga.getAuthority().equals("USER")) {
				response.sendRedirect("/");
				return;
			} else if(ga.getAuthority().equals("ADMIN")) {
				response.sendRedirect("/admin");
				return;
			}
		}

	}

}
