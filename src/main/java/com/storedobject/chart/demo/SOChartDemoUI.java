package com.storedobject.chart.demo;

import javax.servlet.annotation.WebServlet;

import com.storedobject.chart.demo.demo.chart.*;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("valo")
public class SOChartDemoUI extends UI {
	private static final long serialVersionUID = 1442601461593455592L;

	@Override
	public void init(VaadinRequest request) {

		VerticalLayout layout = new VerticalLayout();

//		layout.addComponent(new MyFirstChart());
//		layout.addComponent(new SimpleLineChart());
//		layout.addComponent(new TimeLineChart());
//		layout.addComponent(new MultipleLinesChart());
//		layout.addComponent(new CircularTreeChart());
		layout.addComponent(new SimpleDonutChart());

		setContent(layout);
	}

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = SOChartDemoUI.class)
	public static class Servlet extends VaadinServlet {
		private static final long serialVersionUID = 1L;
	}
}
