package com.votingapp.votingapp.Config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.votingapp.votingapp.Model.User;
import com.votingapp.votingapp.Repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Configuration
public class MySuccessHandler implements AuthenticationSuccessHandler{

    @Autowired
    private UserRepository userRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
                User user = userRepository.loadUserByUserName(authentication.getName());
				// System.out.println(user);
                HttpSession session = request.getSession();
                
				String redirectURL = request.getContextPath();
				
				if(user.getRole().equals("ROLE_USER")) {
                    session.setAttribute("loggedInUser", user);
					redirectURL = "/user/votepage";
				}
				if(user.getRole().equals("ROLE_ADMIN")) {
                    session.setAttribute("loggedInUser", user);
					redirectURL = "/admin/candidatelist";
				}
				response.sendRedirect(redirectURL);
    }
    
}
