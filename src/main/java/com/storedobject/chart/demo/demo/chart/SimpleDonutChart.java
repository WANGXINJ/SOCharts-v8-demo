package com.storedobject.chart.demo.demo.chart;

import com.storedobject.chart.CategoryData;
import com.storedobject.chart.Color;
import com.storedobject.chart.Data;
import com.storedobject.chart.DonutChart;
import com.storedobject.chart.InnerLabelPieChart;
import com.storedobject.chart.LineStyle;
import com.storedobject.chart.Position;
import com.storedobject.chart.SOChart;
import com.storedobject.chart.Size;
import com.storedobject.chart.Title;
import com.storedobject.chart.Toolbox;
import com.storedobject.chart.property.ItemStyleProperty;
import com.storedobject.chart.property.LabelProperty;
import com.storedobject.chart.property.PolarProperty;

public class SimpleDonutChart extends SOChart {
	private static final long serialVersionUID = -3925288601291977314L;

	public SimpleDonutChart() {
		setSize("800px", "500px");
		setMinWidth("300px");
		setMinHeight("200px");

		// Let us define some inline data.
		CategoryData plantsLabels = new CategoryData("Banana", "Apple", "Orange", "Grapes");
		Data plantsData = new Data(25, 40, 20, 30);
		CategoryData animalsLabels = new CategoryData("Mouse", "Cow", "Tiger", "Rabbit", "Dragon", "Snake");
		Data animalsData = new Data(20, 30, 20, 30, 35, 15);

		int animalsSize = animalsData.stream().mapToInt(number -> (Integer) number).sum();
		int plantsSize = plantsData.stream().mapToInt(number -> (Integer) number).sum();
		CategoryData pieLabels = new CategoryData("Plants", "Animals");
		Data pieData = new Data(plantsSize, animalsSize);

		CategoryData donutLabels = new CategoryData();
		donutLabels.addAll(plantsLabels.asList());
		donutLabels.addAll(animalsLabels.asList());
		Data donutData = new Data();
		donutData.addAll(plantsData.asList());
		donutData.addAll(animalsData.asList());

		Position position = new Position();
		position.setTop(Size.percentage(10));
		position.setWidth(Size.pixels(600));
		position.setHeight(Size.pixels(400));

		ItemStyleProperty itemStyle = new ItemStyleProperty();
		itemStyle.setBorderColor(new Color("#ffffff"));
		itemStyle.setBorderWidth(1);
		itemStyle.setBorderType(LineStyle.Type.SOLID);

		InnerLabelPieChart pie = new InnerLabelPieChart();
		pie.setItemNames(pieLabels);
		pie.setData(pieData);
		pie.setPosition(position);
		pie.setItemStyleProperty(itemStyle);
		PolarProperty piePolar = pie.getPolarProperty(true);
		piePolar.setRadius(Size.percentage(50));
		piePolar.setInnerRadius(Size.percentage(0));
		LabelProperty pieLabel = pie.getLabelProperty();
		pieLabel.setFontSize(10);
		String pieCustomLabel = "'formatter': '{b}\\n{per|{d}%}'," //
				+ "     'rich': {" //
				+ "         'b': {" //
				+ "             'color': '#4C5058'," //
				+ "             'fontSize': 14," //
				+ "             'fontWeight': 'bold'," //
				+ "             'height': 22" //
				+ "         }," //
				+ "         'per': {" //
				+ "             'color': '#fff'," //
				+ "             'backgroundColor': '#4C5058'," //
				+ "             'padding': [" //
				+ "                 3," //
				+ "                 4" //
				+ "           ]," //
				+ "			'borderRadius': 4" //
				+ "        }" //
				+ "     }";

		pieLabel.addCustomProperty(pieCustomLabel);

		DonutChart donut = new DonutChart();
		donut.setItemNames(donutLabels);
		donut.setData(donutData);
		donut.setPosition(position); // Position it leaving 50% space at the top
		donut.setItemStyleProperty(itemStyle);
		donut.setHoleRadius(Size.percentage(50));
		LabelProperty donutLabel = donut.getLabelProperty();
//		String donutCustomLabel = "'formatter': '{a|{a}}{abg|}\\n{hr|}\\n  {b|{b}：}{c}  {per|{d}%}  '," //
		String donutCustomLabel = "'formatter': '{b| {c}：} {per|{d}%}'," //
				+ "     'backgroundColor': '#F6F8FC'," //
				+ "     'borderColor': '#8C8D8E'," //
				+ "     'borderWidth': 1," //
				+ "     'borderRadius': 4," //
				+ "     'fontSize': 10," //
				+ "     'rich': {" //
				+ "         'a': {" //
				+ "             'color': '#6E7079'," //
				+ "             'lineHeight': 22," //
				+ "             'align': 'center'" //
				+ "         }," //
				+ "         'hr': {" //
				+ "  			'borderColor': '#8C8D8E'," //
				+ "             'width': '100%'," //
				+ "  			'borderWidth': 1," //
				+ "             'height': 0" //
				+ "         }," //
				+ "         'b': {" //
				+ "             'color': '#4C5058'," //
				+ "             'fontSize': 12," //
				+ "         }," //
				+ "         'per': {" //
				+ "             'color': '#fff'," //
				+ "             'backgroundColor': '#4C5058'," //
				+ "             'height': 22," //
				+ "             'padding': [" //
				+ "                 3," //
				+ "                 4" //
				+ "           ]," //
				+ "			'borderRadius': 4" //
				+ "        }" //
				+ "     }";
		donutLabel.addCustomProperty(donutCustomLabel);
		// this setFontSize is override by custom label property above, 'fontSize': 11,"
		donutLabel.setFontSize(14);

		Toolbox toolbox = new Toolbox();
		toolbox.addButton(new Toolbox.Download(), new Toolbox.Zoom());

		Title title = new Title("My DonutPie Chart");
		title.setSubtext("2nd Line of the Title");

		// Add the chart components to the chart display area.
		add(donut, pie, toolbox, title);

		disableDefaultLegend();
	}
}
