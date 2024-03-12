package com.example.jspboard.v1.controller;

import com.example.jspboard.model.Member;
import com.example.jspboard.model.MemberModel;
import com.example.jspboard.model.PostModel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "homeServlet", urlPatterns = "/")
public class homeServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        initMembers();
        ServletContext servletContext = config.getServletContext();
        initPosts(servletContext);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viewPath = "/WEB-INF/login-form.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req, resp);
    }

    private void initPosts(ServletContext servletContext) {
        servletContext.setAttribute("posts", PostModel.posts);
    }

    private void initMembers() {
        Member memberA = new Member("kim", "aaaa");
        Member memberB = new Member("park", "aaaa");
        MemberModel.members.add(memberA);
        MemberModel.members.add(memberB);
    }
}


