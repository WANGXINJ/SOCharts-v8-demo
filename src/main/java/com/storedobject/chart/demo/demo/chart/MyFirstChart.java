package com.storedobject.chart.demo.demo.chart;

import com.storedobject.chart.BarChart;
import com.storedobject.chart.CategoryData;
import com.storedobject.chart.Data;
import com.storedobject.chart.DataType;
import com.storedobject.chart.NightingaleRoseChart;
import com.storedobject.chart.Position;
import com.storedobject.chart.RectangularCoordinate;
import com.storedobject.chart.SOChart;
import com.storedobject.chart.Size;
import com.storedobject.chart.Title;
import com.storedobject.chart.Toolbox;
import com.storedobject.chart.XAxis;
import com.storedobject.chart.YAxis;

public class MyFirstChart extends SOChart {
	private static final long serialVersionUID = -3925288601291977314L;

	public MyFirstChart() {
		setSize("800px", "500px");
		setMinWidth("300px");
		setMinHeight("200px");

		// Let us define some inline data.
		CategoryData labels = new CategoryData("Banana", "Apple", "Orange", "Grapes");
		Data data = new Data(25, 40, 20, 30);

		// We are going to create a couple of charts. So, each chart should be
		// positioned
		// appropriately.
		// Create a self-positioning chart.
		NightingaleRoseChart nc = new NightingaleRoseChart(labels, data);
		Position p = new Position();
		p.setTop(Size.percentage(50));
		nc.setPosition(p); // Position it leaving 50% space at the top

		// Second chart to add.
		BarChart bc = new BarChart(labels, data);
		RectangularCoordinate rc;
		rc = new RectangularCoordinate(new XAxis(DataType.CATEGORY), new YAxis(DataType.NUMBER));
		p = new Position();
		p.setBottom(Size.percentage(55));
		rc.setPosition(p); // Position it leaving 55% space at the bottom
		bc.plotOn(rc); // Bar chart needs to be plotted on a coordinate system

		// Just to demonstrate it, we are creating a "Download" and a "Zoom" toolbox
		// button.
		Toolbox toolbox = new Toolbox();
		toolbox.addButton(new Toolbox.Download(), new Toolbox.Zoom());

		// Let's add some titles.
		Title title = new Title("My First Chart");
		title.setSubtext("2nd Line of the Title");

		// Add the chart components to the chart display area.
		add(nc, bc, toolbox, title);
	}
}
