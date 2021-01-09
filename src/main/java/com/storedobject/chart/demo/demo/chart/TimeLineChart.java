package com.storedobject.chart.demo.demo.chart;

import java.time.LocalDateTime;
import java.util.Random;

import com.storedobject.chart.Data;
import com.storedobject.chart.DataType;
import com.storedobject.chart.LineChart;
import com.storedobject.chart.RectangularCoordinate;
import com.storedobject.chart.SOChart;
import com.storedobject.chart.TimeData;
import com.storedobject.chart.Title;
import com.storedobject.chart.XAxis;
import com.storedobject.chart.YAxis;

public class TimeLineChart extends SOChart {
	private static final long serialVersionUID = 8336420706566487408L;

	public TimeLineChart() {
		setSize("800px", "500px");
		setMinWidth("300px");
		setMinHeight("200px");

		// Generating some random values for a LineChart
		Random random = new Random();
		TimeData xValues = new TimeData(); // Time data for the X-axis
		LocalDateTime start = LocalDateTime.now();
		Data yValues = new Data();
		double yValue = 10.00;
		for (int x = 0; x < 40; x++) {
			LocalDateTime time = start.plusDays(x);
			xValues.add(time);
			yValue += random.nextDouble() * (random.nextBoolean() ? 1 : -1);
			yValues.add(yValue);
		}
		xValues.setName("X Values");
		yValues.setName("Random Values");

		// Line chart is initialized with the generated XY values
		LineChart lineChart = new LineChart(xValues, yValues);
		lineChart.setName("40 Random Values");

		// Line chart needs a coordinate system to plot on
		// We need Number-type for both X and Y axes in this case
		XAxis xAxis = new XAxis(DataType.TIME); // X-axis type is time.
		YAxis yAxis = new YAxis(DataType.NUMBER);
		RectangularCoordinate rc = new RectangularCoordinate(xAxis, yAxis);
		lineChart.plotOn(rc);

		// Add to the chart display area with a simple title
		add(lineChart, new Title("Sample Time Line Chart"));
	}

}
