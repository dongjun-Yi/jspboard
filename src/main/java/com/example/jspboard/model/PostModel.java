package com.example.jspboard.model;

import java.util.ArrayList;

public class PostModel {
    private static Long id;
    public static ArrayList<Post> posts = new ArrayList();

    public static Long generateId() {
        if (id == null) {
            id = 0L;
        }
        return ++id;
    }
}
