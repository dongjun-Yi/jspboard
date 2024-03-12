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

@WebServlet(name = "postDeleteServlet", urlPatterns = "/post-delete")
public class PostDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));

        for (Post p : PostModel.posts) {
            if (id == p.getId()) {
                PostModel.posts.remove(p);
                break;
            }
        }
        System.out.println(PostModel.posts.size());

        String viewPath = "WEB-INF/post-list.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req, resp);
    }
}
