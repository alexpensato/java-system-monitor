package com.pensato.javamonitor.controller;

import javax.servlet.http.HttpServletRequest;

/*
 * Controller interface for implementing concrete controllers
 */
public interface Controller {
    String execute(HttpServletRequest request);
    String getPageToGo();
}