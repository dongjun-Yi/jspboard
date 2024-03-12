package com.example.jspboard.v2.controller;

import com.example.jspboard.model.Post;
import com.example.jspboard.model.PostModel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class PostDeleteControllerV2 implements ControllerV2 {

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equals("GET")) {
            Long id = Long.parseLong(req.getParameter("id"));

            for (Post p : PostModel.posts) {
                if (id == p.getId()) {
                    PostModel.posts.remove(p);
                    break;
                }
            }
            System.out.println(PostModel.posts.size());

            String viewPath = "/WEB-INF/v2/post-list.jsp";
            RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
            dispatcher.forward(req, resp);
        }
    }
}
