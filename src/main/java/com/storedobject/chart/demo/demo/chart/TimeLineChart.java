package com.storedobject.chart.demo.demo.chart;

import java.time.LocalDateTime;
import java.util.Random;

import com.storedobject.chart.ChartClick.EventDetails;
import com.storedobject.chart.SOChart;
import com.storedobject.chart.component.LineChart;
import com.storedobject.chart.component.Title;
import com.storedobject.chart.component.Tooltip;
import com.storedobject.chart.coordinate_system.RectangularCoordinate;
import com.storedobject.chart.coordinate_system.XAxis;
import com.storedobject.chart.coordinate_system.YAxis;
import com.storedobject.chart.data.Data;
import com.storedobject.chart.data.DataType;
import com.storedobject.chart.data.TimeData;
import com.storedobject.chart.property.BaseComponentProperty;
import com.storedobject.chart.property.Format;
import com.storedobject.chart.property.PropertyValueArray;
import com.vaadin.ui.Notification;

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

		PropertyValueArray markData = new PropertyValueArray();
		markData.newPropertyValue() //
				.setProperty("type", "max") //
				.setProperty("name", "Max Value");
		markData.newPropertyValue() //
				.setProperty("type", "min") //
				.setProperty("name", "Min Value");
		lineChart.setProperty(new BaseComponentProperty("markPoint") //
				.setProperty("data", markData));

		markData.clear().newPropertyValue() //
				.setProperty("type", "average") //
				.setProperty("name", "Average Value");
		lineChart.setProperty(new BaseComponentProperty("markLine") //
				.setProperty("data", markData));

		// Line chart needs a coordinate system to plot on
		// We need Number-type for both X and Y axes in this case
		XAxis xAxis = new XAxis(DataType.TIME); // X-axis type is time.
		xAxis.setMinAsMinData();
		YAxis yAxis = new YAxis(DataType.NUMBER);
		RectangularCoordinate rc = new RectangularCoordinate(xAxis, yAxis);
		lineChart.plotOn(rc);

		// Add to the chart display area with a simple title
		add(lineChart, new Title("Sample Time Line Chart"));

		Tooltip tooltip = getTooltip();
		tooltip.setProperty("trigger", "axis");
//		tooltip.setProperty("formatter", "function (params) {" //
//				+ "            params = params[0];" //
//				+ "            console.log(params);" //
//				+ "            var date = new Date(params.value[0]);" //
//				+ "            return date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear() + ' : ' + params.value[1];"
//				+ "        }"); 
		tooltip.setFormatter("%s: %s", Format.dateFormat("x", "yyyy-MM-dd"), Format.doubleFormat("y"));

		lineChart.getPointSymbol(true).show();

		addClickListener(click -> {
			EventDetails details = click.getDetails();
			Notification.show("seriesInde=" + details.getSeriesIndex() + ", dataIndex=" + details.getDataIndex());
		});
	}
}
