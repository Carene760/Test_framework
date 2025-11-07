package com.cousin.controller;

import com.framework.annotation.MonController;
import com.framework.annotation.MesRoutes;

@MonController
public class UserController {

    @MesRoutes("/user/list")
    public String listUsers() {
        return "list.jsp";
    }

    @MesRoutes("/user/add")
    public String addUser() {
        return "add";
    }
}
