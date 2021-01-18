package com.storedobject.chart.demo.demo.chart;

import java.util.Random;

import com.storedobject.chart.SOChart;
import com.storedobject.chart.component.LineChart;
import com.storedobject.chart.component.Title;
import com.storedobject.chart.coordinate_system.RectangularCoordinate;
import com.storedobject.chart.coordinate_system.XAxis;
import com.storedobject.chart.coordinate_system.YAxis;
import com.storedobject.chart.data.Data;
import com.storedobject.chart.data.DataType;

public class SimpleLineChart extends SOChart {
	private static final long serialVersionUID = -1623760699428690323L;

	public SimpleLineChart() {
		setSize("800px", "500px");
		setMinWidth("300px");
		setMinHeight("200px");

		// Generating some random values for a LineChart
		Random random = new Random();
		Data xValues = new Data(), yValues = new Data();
		double yValue = 10.00;
		for (int x = 0; x < 40; x++) {
			xValues.add(x);
			yValue += random.nextDouble() * (random.nextBoolean() ? 1 : -1);
			yValues.add(yValue);
		}
		xValues.setName("X Values");
		yValues.setName("40 Random Values");

		// Line chart is initialized with the generated XY values
		LineChart lineChart = new LineChart(xValues, yValues);
		lineChart.setName("Random Values");

		// Line chart needs a coordinate system to plot on
		// We need Number-type for both X and Y axes in this case
		XAxis xAxis = new XAxis(DataType.NUMBER);
		YAxis yAxis = new YAxis(DataType.NUMBER);
		RectangularCoordinate rc = new RectangularCoordinate(xAxis, yAxis);
		lineChart.plotOn(rc);

		// Add to the chart display area with a simple title
		add(lineChart, new Title("Sample Line Chart"));
	}
}
