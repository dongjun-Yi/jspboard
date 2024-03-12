package com.example.jspboard.v1.controller;

import com.example.jspboard.model.Post;
import com.example.jspboard.model.PostModel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "postAddServlet", urlPatterns = "/post-add")
public class postAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viewPath = "/WEB-INF/post-add.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getSession().getAttribute("username").toString();
        if (!isPostExists(req)) {
            Post post = new Post(PostModel.generateId(), req.getParameter("title"), req.getParameter("content"), username);
            PostModel.posts.add(post);
        }

        String viewPath = "/WEB-INF/post-list.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req, resp);
    }

    private static boolean isPostExists(HttpServletRequest req) {
        String getPostId = req.getParameter("id");
        if (getPostId == null)
            return false;
        Long postId = Long.parseLong(getPostId);
        for (Post p : PostModel.posts) {
            if (postId == p.getId()) {
                p.setTitle(req.getParameter("title"));
                p.setContent(req.getParameter("content"));
                return true;
            }
        }
        return false;
    }
}
