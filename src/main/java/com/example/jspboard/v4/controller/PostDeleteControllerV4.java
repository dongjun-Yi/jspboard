package com.example.jspboard.v4.controller;

import com.example.jspboard.model.Post;
import com.example.jspboard.model.PostModel;

import java.util.Map;

public class PostDeleteControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String method = model.get("method").toString();

        if (method.equals("GET")) {
            Long id = Long.parseLong(paramMap.get("id"));

            for (Post p : PostModel.posts) {
                if (id == p.getId()) {
                    PostModel.posts.remove(p);
                    break;
                }
            }
            return "post-list";
        }
        return null;
    }
}
