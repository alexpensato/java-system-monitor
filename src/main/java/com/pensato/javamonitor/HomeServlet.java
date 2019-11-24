package com.pensato.javamonitor;

import com.pensato.javamonitor.config.ApplicationBinder;
import com.pensato.javamonitor.controller.Controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "HomeServlet", urlPatterns = {"/oshi/*"}, loadOnStartup = 1)
public class HomeServlet extends HttpServlet {

	// Declare one map in your servlet to hold all concrete controllers.
	private Map<String, Controller> controllers;

	@Override
	public void init(final ServletConfig config) throws ServletException {
		super.init(config);
		loadControllers();
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String BASE_PATH = "/WEB-INF";
		final String PAGE_NOT_FOUND = "/WEB-INF/pageNotFound.jsp";
		String pathInfo = request.getPathInfo();
		Controller controller = findController(pathInfo);
		if(controller!=null) {
			String pageToGo = controller.execute(request);
			request.getRequestDispatcher(BASE_PATH + pageToGo).forward(request, response);
		} else {
			request.getRequestDispatcher(PAGE_NOT_FOUND).forward(request, response);
		}
	}

	private void loadControllers() {
		if(controllers == null) {
			controllers = ApplicationBinder.getControllers();
		}
	}

	private Controller findController(String path) {
		if (path == null || path.equals("")) {
			return controllers.get("/");
		} else {
			String lowerCasePath = path.toLowerCase();
			if (controllers.containsKey(lowerCasePath))
				return controllers.get(lowerCasePath);
		}
		return null;
	}
}
