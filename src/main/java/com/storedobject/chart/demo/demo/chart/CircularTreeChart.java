package com.storedobject.chart.demo.demo.chart;

import java.util.Random;

import com.storedobject.chart.SOChart;
import com.storedobject.chart.component.Title;
import com.storedobject.chart.component.TreeChart;
import com.storedobject.chart.data.TreeData;

public class CircularTreeChart extends SOChart {
	private static final long serialVersionUID = 7644138903351514408L;

	public CircularTreeChart() {
		setSize("800px", "500px");
		setMinWidth("300px");
		setMinHeight("200px");

		// Tree chart
		// (By default it assumes circular shape. Otherwise, we can set orientation)
		// All values are randomly generated
		TreeChart tc = new TreeChart();
		TreeData td = new TreeData("Root", 1000);
		tc.setTreeData(td);
		Random r = new Random();
		for (int i = 1; i < 21; i++) {
			td.add(new TreeData("Node " + i, r.nextInt(500)));
		}
		TreeData td1 = td.get(13);
		td = td.get(9);
		for (int i = 50; i < 56; i++) {
			td.add(new TreeData("Node " + i, r.nextInt(500)));
		}
		for (int i = 30; i < 34; i++) {
			td1.add(new TreeData("Node " + i, r.nextInt(500)));
		}

		// Add to the chart display area with a simple title
		add(tc, new Title("A Circular Tree Chart"));
	}

}
