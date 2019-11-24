package com.pensato.javamonitor.controller;

import com.pensato.javamonitor.model.HardwareInfo;
import com.pensato.javamonitor.model.OperatingSystemInfo;
import com.pensato.javamonitor.model.SystemLoadInfo;
import com.pensato.javamonitor.service.SystemService;

import javax.servlet.http.HttpServletRequest;

/*
 * Controller for the Start page
 */
public class StartController extends AbstractController {

    private SystemService systemService;

    public StartController(String pageToGo, SystemService systemService) {
        super(pageToGo);
        this.systemService = systemService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if(systemService == null) {
            request.setAttribute("osInfo", new OperatingSystemInfo());
            request.setAttribute("hardware", new HardwareInfo());
            request.setAttribute("systemLoadInfo", new SystemLoadInfo());
        } else {
            request.setAttribute("osInfo", systemService.getOperatingSystemInfo());
            request.setAttribute("hardware", systemService.getHardwareInfo());
            request.setAttribute("systemLoadInfo", systemService.getSystemLoadInfo());
        }
        return getPageToGo();
    }
}
