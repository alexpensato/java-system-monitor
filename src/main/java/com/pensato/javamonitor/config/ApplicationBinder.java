package com.pensato.javamonitor.config;

import com.pensato.javamonitor.controller.Controller;
import com.pensato.javamonitor.controller.StartController;
import com.pensato.javamonitor.service.OshiSystemService;
import com.pensato.javamonitor.service.SystemService;

import java.util.HashMap;
import java.util.Map;

/*
 * ApplicationBinder controls instantiation and provision of shared objects
 */
public final class ApplicationBinder {
    private ApplicationBinder(){}

    private static Map<String, Controller> controllers;
    private static SystemService oshiSystemService;

    public static Map<String, Controller> getControllers() {
        if (controllers == null) {
            synchronized (ApplicationBinder.class) {
                if (controllers == null) {
                    controllers = new HashMap<>();
                    controllers.put("/", new StartController("/start.jsp", getOshiSystemService()));
                    controllers.put("/info", new StartController("/start.jsp", getOshiSystemService()));
                }
            }
        }
        return controllers;
    }

    public static SystemService getOshiSystemService() {
        if (oshiSystemService == null) {
            synchronized (ApplicationBinder.class) {
                if (oshiSystemService == null) {
                    oshiSystemService = new OshiSystemService();
                }
            }
        }
        return oshiSystemService;
    }
}
