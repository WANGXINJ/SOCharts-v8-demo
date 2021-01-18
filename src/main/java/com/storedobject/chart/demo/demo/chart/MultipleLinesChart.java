package com.storedobject.chart.demo.demo.chart;

import com.storedobject.chart.SOChart;
import com.storedobject.chart.component.LineChart;
import com.storedobject.chart.component.Title;
import com.storedobject.chart.coordinate_system.RectangularCoordinate;
import com.storedobject.chart.coordinate_system.XAxis;
import com.storedobject.chart.coordinate_system.YAxis;
import com.storedobject.chart.data.Data;
import com.storedobject.chart.data.DataType;

public class MultipleLinesChart extends SOChart {
	private static final long serialVersionUID = 2366348257592821140L;

	public MultipleLinesChart() {
		setSize("600px", "650px");
		setMinWidth("300px");
		setMinHeight("200px");

		// Generating 10 set of values for 10 LineCharts for the equation:
		// y = a + a * x / (a - 11) where a = 1 to 10, x and y are positive
		LineChart[] lineCharts = new LineChart[10];
		Data[] xValues = new Data[lineCharts.length];
		Data[] yValues = new Data[lineCharts.length];
		int i;
		for (i = 0; i < lineCharts.length; i++) {
			xValues[i] = new Data();
			xValues[i].setName("X (a = " + (i + 1) + ")");
			yValues[i] = new Data();
			yValues[i].setName("Y (a = " + (i + 1) + ")");
		}
		// For each line chart, we need only 2 end-points (because they are straight
		// lines).
		int a;
		for (i = 0; i < lineCharts.length; i++) {
			a = i + 1;
			xValues[i].add(0);
			yValues[i].add(a);
			xValues[i].add(11 - a);
			yValues[i].add(0);
		}

		// Line charts are initialized here
		for (i = 0; i < lineCharts.length; i++) {
			lineCharts[i] = new LineChart(xValues[i], yValues[i]);
			lineCharts[i].setName("a = " + (i + 1));
		}

		// Line charts need a coordinate system to plot on
		// We need Number-type for both X and Y axes in this case
		XAxis xAxis = new XAxis(DataType.NUMBER);
		YAxis yAxis = new YAxis(DataType.NUMBER);
		RectangularCoordinate rc = new RectangularCoordinate(xAxis, yAxis);
		for (i = 0; i < lineCharts.length; i++) {
			lineCharts[i].plotOn(rc);
			add(lineCharts[i]); // Add the chart to the display area
		}

		// Add a simple title too
		add(new Title("Equation: y = a + a * x / (a - 11) where a = 1 to 10, x and y are positive"));

		// We don't want any legends
		disableDefaultLegend();
	}
}
