package com.naite.bookingTour.controller.admin;

import com.naite.bookingTour.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Check if the user is an admin
        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || !user.isAdmin()) {
            // The user is not an admin, so redirect them to the login page
            httpResponse.sendRedirect("/login");
            return;
        }

        // The user is an admin, so let them continue
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
