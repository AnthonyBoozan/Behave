import java.util.ArrayList;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYSeries;

public class ChartHandler{
	public JFreeChart CreateChart(Child c)
	{
		ArrayList<String> dateHolder = c.getDateGraphHelper();
		ArrayList<Double> tokenNumberHolder = c.getNumberOfTokensGraphHelper();
		XYSeries series = new XYSeries("Chart");
		if(c.getListOfTokens().size() == 0)
		{
			System.out.println("No tokens to graph");
		}
		for(int i = 0; i < dateHolder.size(); i++)
		{
			double temp = tokenNumberHolder.get(i);
			
			series.add(i, temp);
		}
		
		DefaultXYDataset result = new DefaultXYDataset();
		result.addSeries("Number of tokens", series.toArray());
		JFreeChart chart = ChartFactory.createXYLineChart(c.getFirstName() + "'s Token Chart", "Date", "Number of tokens", result);
		
		XYPlot plot = (XYPlot) chart.getPlot();
	    plot.setForegroundAlpha(0.5f);


	    String[] grade =  new String[dateHolder.size()];
	    for(int i = 0; i < dateHolder.size(); i++)
	    {
	    	grade[i] = dateHolder.get(i);
	    }
	    SymbolAxis rangeAxis = new SymbolAxis("Date", grade);

	    rangeAxis.setTickUnit(new NumberTickUnit(1));
	    rangeAxis.setRange(0,grade.length);
	    plot.setDomainAxis(rangeAxis);

	    return chart;
	}
}
	
