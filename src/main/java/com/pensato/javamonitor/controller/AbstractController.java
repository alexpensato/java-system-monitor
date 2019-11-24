package com.pensato.javamonitor.controller;

import javax.servlet.http.HttpServletRequest;

/*
 * Abstract Controller for shared code among concrete controllers
 */
public abstract class AbstractController implements Controller {

    private String pageToGo;

    AbstractController(String pageToGo) {
        this.pageToGo = pageToGo;
    }

    public abstract String execute(HttpServletRequest request);

    public String getPageToGo() {
        return this.pageToGo;
    }
}
