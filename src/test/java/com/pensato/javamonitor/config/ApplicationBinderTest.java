package com.pensato.javamonitor.config;

import com.pensato.javamonitor.controller.Controller;
import com.pensato.javamonitor.controller.StartController;
import com.pensato.javamonitor.service.OshiSystemService;
import com.pensato.javamonitor.service.SystemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class ApplicationBinderTest {

    private static Map<String, Controller> controllerMap;

    @BeforeAll
    static void beforeAll() {
        controllerMap = ApplicationBinder.getControllers();
    }

    @Test
    @DisplayName("Method getOshiSystemService() should return an instance of OshiSystemService object")
    void testGetOshiSystemService() {
        SystemService systemService = ApplicationBinder.getOshiSystemService();
        Assertions.assertNotNull(systemService, "ApplicationBinder.getOshiSystemService() should not be null");
        Assertions.assertTrue(systemService instanceof OshiSystemService,
                "ApplicationBinder.getOshiSystemService() should return an instance of OshiSystemService class");
    }

    @Test
    @DisplayName("Method getControllers() should return a valid map")
    void testGetControllers() {
        Assertions.assertNotNull(controllerMap, "ApplicationBinder.getControllers() should not be null");
    }

    @Test
    @DisplayName("Controllers map should contain known keys")
    void testGetControllersKeys() {
        Assertions.assertTrue(controllerMap.containsKey("/"));
        Assertions.assertTrue(controllerMap.containsKey("/info"));
    }

    @Test
    @DisplayName("Controllers map should contain known values")
    void testGetControllersValues() {
        final Set<String> controllersNames = controllersNames();
        for(Controller controller: controllerMap.values()) {
            Assertions.assertTrue(controllersNames.contains(controller.getClass().getName()),
                    "Controller map contains unknown class: " + controller.getClass().getName());
        }
    }

    static Set<String> controllersNames() {
        Set<String> set = new HashSet<>();
        set.add(StartController.class.getName());
        return set;
    }
}
