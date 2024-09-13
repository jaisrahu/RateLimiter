package com.search.servlet;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.search.service.SearchService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet {

	@Autowired
    private SearchService searchService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientId = req.getHeader("Client-Id");
        String query = req.getParameter("query");
        if (clientId == null || query == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Client-Id and query parameters are required");
            return;
        }
        
        String result = searchService.performSearch(clientId, query);
        resp.setContentType("text/plain");
        resp.getWriter().write(result);
    }
    
}