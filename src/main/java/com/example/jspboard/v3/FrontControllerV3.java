package com.example.jspboard.v3;

import com.example.jspboard.MyView;
import com.example.jspboard.v3.controller.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "FrontControllerV3", urlPatterns = "/v3/*")
public class FrontControllerV3 extends HttpServlet {
    private HashMap<String, ControllerV3> controllerV3Map = new HashMap<>();

    public FrontControllerV3() {
        controllerV3Map.put("/v3", new HomeControllerV3());
        controllerV3Map.put("/v3/login", new LoginControllerV3());
        controllerV3Map.put("/v3/logout", new LogoutControllerV3());
        controllerV3Map.put("/v3/post-add", new PostAddControllerV3());
        controllerV3Map.put("/v3/post-delete", new PostDeleteControllerV3());
        controllerV3Map.put("/v3/post-detail", new PostDetailControllerV3());
        controllerV3Map.put("/v3/post-detail-update", new PostDetailUpdateControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();

        System.out.println(requestURI);

        ControllerV3 controller = controllerV3Map.get(requestURI);

        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyView view = controller.process(req, resp);
        view.render(req, resp);
    }
}
