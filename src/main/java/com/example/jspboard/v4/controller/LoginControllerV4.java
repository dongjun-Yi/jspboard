package com.example.jspboard.v4.controller;

import com.example.jspboard.model.Member;
import com.example.jspboard.model.MemberModel;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

public class LoginControllerV4 implements ControllerV4 {
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String method = model.get("method").toString();
        HttpSession session = (HttpSession) model.get("session");

        if (method.equals("GET")) {
            return "login-form.jsp";
        } else if (method.equals("POST")) {
            String username = paramMap.get("username");
            String password = paramMap.get("password");
            boolean isValidUser = false;

            for (Member m : MemberModel.members) {
                if (username.equals(m.getUsername()) && password.equals(m.getPassword())) {
                    isValidUser = true;

                    if (session.isNew() || session.getAttribute("username") == null) {
                        session.setAttribute("username", username);
                    }
                    return "post-list";
                }
            }
            if (!isValidUser) {
                model.put("loginError", "로그인 정보가 올바르지 않습니다."); //setAttribute
                return "login-form";
            }
        }
        return null;
    }
}
