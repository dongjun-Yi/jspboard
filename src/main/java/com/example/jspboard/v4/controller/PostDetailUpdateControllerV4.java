package com.example.jspboard.v4.controller;

import java.util.Map;

public class PostDetailUpdateControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String method = model.get("method").toString();
        if (method.equals("GET")) {
            String id = paramMap.get("id");
            String title = paramMap.get("title");
            String content = paramMap.get("content");

            model.put("id", id);
            model.put("title", title);
            model.put("content", content);
            return "post-detail-update";
        }
        return null;
    }
}
