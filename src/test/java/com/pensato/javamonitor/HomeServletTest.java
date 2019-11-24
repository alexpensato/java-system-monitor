package com.pensato.javamonitor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class HomeServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private RequestDispatcher requestDispatcher;

    private HttpServlet homeServlet;

    @BeforeEach
    void before() {
        homeServlet = new HomeServlet();
    }

    @Test
    @DisplayName("Mocked classes should not be null")
    void shouldInjectMocks() {
        Assertions.assertNotNull(request, "Mocked request should not be null");
        Assertions.assertNotNull(response, "Mocked response should not be null");
        Assertions.assertNotNull(servletConfig, "Mocked servletConfig should not be null");
        Assertions.assertNotNull(requestDispatcher, "Mocked requestDispatcher should not be null");
    }

    @Test
    @DisplayName("Servlet service should dispatch to correct page when using correct path")
    void testServiceWithCorrectPath() {
        Mockito.when(request.getPathInfo()).thenReturn("/info");
        Mockito.when(request.getRequestDispatcher("/WEB-INF/start.jsp")).thenReturn(requestDispatcher);
        try {
            homeServlet.init(servletConfig);
            homeServlet.service(request, response);
            Mockito.verify(requestDispatcher).forward(request,response);

        } catch (ServletException | IOException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Servlet service should dispatch to page not found when using invalid path")
    void testServiceWithInvalidPath() {
        Mockito.when(request.getPathInfo()).thenReturn("/any-other-page");
        Mockito.when(request.getRequestDispatcher("/WEB-INF/pageNotFound.jsp")).thenReturn(requestDispatcher);
        try {
            homeServlet.init(servletConfig);
            homeServlet.service(request, response);
            Mockito.verify(requestDispatcher).forward(request,response);

        } catch (ServletException | IOException e) {
            Assertions.fail(e.getMessage());
        }
    }
}
