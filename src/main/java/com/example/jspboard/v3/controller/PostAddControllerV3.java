package com.example.jspboard.v3.controller;

import com.example.jspboard.model.Post;
import com.example.jspboard.model.PostModel;
import com.example.jspboard.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class PostAddControllerV3 implements ControllerV3 {
    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equals("GET")) {
            return new MyView("/WEB-INF/v3/post-add.jsp");
        } else if (req.getMethod().equals("POST")) {
            String username = req.getSession().getAttribute("username").toString();
            if (!isPostExists(req)) {
                Post post = new Post(PostModel.generateId(), req.getParameter("title"), req.getParameter("content"), username);
                PostModel.posts.add(post);
            }

            return new MyView("/WEB-INF/v3/post-list.jsp");
        }
        return null;
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
