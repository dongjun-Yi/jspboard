package com.example.jspboard.v4.controller;

import com.example.jspboard.model.Post;
import com.example.jspboard.model.PostModel;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

public class PostAddControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String method = model.get("method").toString();
        HttpSession session = (HttpSession) model.get("session");

        if (method.equals("GET")) {
            return "post-add";
        } else if (method.equals("POST")) {
            System.out.println("POST");
            String username = session.getAttribute("username").toString();
            if (!isPostExists(paramMap)) {
                Post post = new Post(PostModel.generateId(), paramMap.get("title"), paramMap.get("content"), username);
                PostModel.posts.add(post);
            }
            return "post-list";
        }
        return null;
    }

    private static boolean isPostExists(Map<String, String> paramMap) {
        String getPostId = paramMap.get("id");
        System.out.println("postId : " + getPostId);
        if (getPostId == null)
            return false;
        Long postId = Long.parseLong(getPostId);
        for (Post p : PostModel.posts) {
            if (postId == p.getId()) {
                p.setTitle(paramMap.get("title"));
                p.setContent(paramMap.get("content"));
                return true;
            }
        }
        return false;
    }
}
