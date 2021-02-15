package com.storedobject.chart.demo.demo.chart;

import com.storedobject.chart.SOChart;

import java.util.ArrayList;
import java.util.List;

import com.storedobject.chart.ChartClick.EventDetails;
import com.storedobject.chart.component.BarChart;
import com.storedobject.chart.component.NightingaleRoseChart;
import com.storedobject.chart.component.Title;
import com.storedobject.chart.component.Toolbox;
import com.storedobject.chart.component.Tooltip;
import com.storedobject.chart.coordinate_system.Axis.AxisLabel;
import com.storedobject.chart.coordinate_system.ContinuousVisualMap;
import com.storedobject.chart.coordinate_system.Position;
import com.storedobject.chart.coordinate_system.RectangularCoordinate;
import com.storedobject.chart.coordinate_system.XAxis;
import com.storedobject.chart.coordinate_system.YAxis;
import com.storedobject.chart.data.CategoryData;
import com.storedobject.chart.data.Data;
import com.storedobject.chart.data.DataProvider;
import com.storedobject.chart.data.DataType;
import com.storedobject.chart.property.Color;
import com.storedobject.chart.property.Format;
import com.storedobject.chart.property.Size;
import com.vaadin.ui.Notification;

public class MyFirstChart extends SOChart {
	private static final long serialVersionUID = -3925288601291977314L;

	public MyFirstChart() {
		setSize("800px", "500px");
		setMinWidth("300px");
		setMinHeight("200px");

		// Let us define some inline data.
		CategoryData labels = new CategoryData("Banana", "Apple", "Orange", "Grapes");
		Data data = new Data(25, 60, 10, 40);

		// We are going to create a couple of charts. So, each chart should be
		// positioned
		// appropriately.
		// Create a self-positioning chart.
		NightingaleRoseChart nc = new NightingaleRoseChart(labels, data);
		nc.setName("Plants");
		Position p = new Position();
		p.setTop(Size.percentage(50));
		nc.setPosition(p); // Position it leaving 50% space at the top

		// Second chart to add.
		BarChart bc = new BarChart(labels, data);
		bc.setName("Fruits");
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

		Format valueFormat = Format.currencyFormat("y");
		AxisLabel axisLabel = rc.getAxes().get(1).getLabel(true);
		axisLabel.setFormatter(valueFormat);
		Tooltip tooltip = getTooltip();
//		tooltip.setFormatter("%s: %s", Format.stringFormat("x"), valueFormat);
		bc.getLabel(true).setFormatter(valueFormat);
		nc.getLabel(true).setFormatter("%s: %s", Format.stringFormat("x"), valueFormat);

		ContinuousVisualMap visualMap = new ContinuousVisualMap();
		ContinuousVisualMap.InRange inRange = new ContinuousVisualMap.InRange();
		Color blue = new Color("#0000FF");
		Color red = new Color("#FF0000");
		inRange.setColors(red, blue);
		visualMap.setInRange(inRange) //
				.hide();
		addPartsSetupListener(setup -> {
			DataProvider dataProvider = setup.getParts().valueDataProviderStream().findFirst().get();
			List<Number> dataList = new ArrayList<>(dataProvider.asList());
			dataList.sort((d1, d2) -> Integer.compare((int) d2, (int) d1));
			visualMap.setMin(dataList.get(0));
			visualMap.setMax(dataList.get(dataList.size() - 1));

			String datasetName = dataProvider.datasetName();
			visualMap.setDimension(datasetName);
		});
		setVisualMap(visualMap);

		// Add the chart components to the chart display area.
		add(nc, bc, toolbox, title);

		addClickListener(click -> {
			EventDetails details = click.getDetails();
			Notification.show("seriesType=" + details.getSeriesType() + ", seriesIndex=" + details.getSeriesIndex()
					+ ", dataIndex=" + details.getDataIndex());
		});
	}
}
