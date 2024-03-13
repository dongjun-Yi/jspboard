package com.example.jspboard.v3.controller;

import com.example.jspboard.model.Post;
import com.example.jspboard.model.PostModel;
import com.example.jspboard.v3.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class PostDeleteControllerV3 implements ControllerV3 {
    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equals("GET")) {
            Long id = Long.parseLong(req.getParameter("id"));

            for (Post p : PostModel.posts) {
                if (id == p.getId()) {
                    PostModel.posts.remove(p);
                    break;
                }
            }
            return new MyView("/WEB-INF/v3/post-list.jsp");
        }
        return null;
    }
}
