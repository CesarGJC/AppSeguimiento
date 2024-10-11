package com.moxos.uab.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String message = exception.getMessage();
        if (message.contains("{5}:")) {
            request.getRequestDispatcher("/login?error=5").forward(request, response);
            return;
        }
        if (message.contains("{4}:"))
            request.getRequestDispatcher("/login?error=4").forward(request, response);
        else {
            if (message.contains("{3}:")) {
                request.getRequestDispatcher("/login?error=3").forward(request, response);
            } else {
                if (message.contains("Bad"))
                    message = "contraseña incorrecta";
                request.getRequestDispatcher("/login?error=1&message=" + message).forward(request, response);
            }
        }
    }
}
