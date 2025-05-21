package com.example.demo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class IpFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String ip = request.getRemoteAddr();

        String uri = request.getRequestURI();

        if (uri.contains("/client")) {
            if (ip.startsWith("192.168.1.")) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied from internal network");
                return;
            }
        }

        if (uri.contains("/backups")) {
            if (!(ip.startsWith("192.168.1."))) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access only allowed from internal network");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
