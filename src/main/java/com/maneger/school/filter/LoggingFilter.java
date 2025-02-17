package com.maneger.school.filter;

import com.maneger.school.domain.LogEntry;
import com.maneger.school.repository.LogEntryRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class LoggingFilter implements Filter {

    private final LogEntryRepository logEntryRepository;

    public LoggingFilter(LogEntryRepository logEntryRepository) {
        this.logEntryRepository = logEntryRepository;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        boolean responseStatus = true;

        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            responseStatus = false;
            throw e;
        } finally {
            String machineId = request.getRemoteHost();
            String endpoint = httpRequest.getRequestURI();
            LocalDateTime timestamp = LocalDateTime.now();

            LogEntry logEntry = new LogEntry();
            logEntry.setMachineId(machineId);
            logEntry.setTimestamp(timestamp);
            logEntry.setEndpoint(endpoint);
            logEntry.setResponseStatus(responseStatus);
            logEntryRepository.save(logEntry);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}