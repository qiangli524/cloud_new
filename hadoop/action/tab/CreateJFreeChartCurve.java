package action.tab;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class CreateJFreeChartCurve {
	
	 /**  
     * 创建JFreeChart LineXY Chart（折线图）  
     */  
    public static void main(String[] args) {   
        //步骤1：创建XYDataset对象（准备数据）   
        XYDataset dataset = createXYDataset();   
        //步骤2：根据Dataset 生成JFreeChart对象，以及做相应的设置   
        createChart(dataset);      
    }   
   
    // 根据XYDataset创建JFreeChart对象   
    public static JFreeChart createChart(XYDataset dataset) {
    	String title = "价格走势图";
    	String x = "时间";
    	String y = "价格";
    	JFreeChart chart = ChartFactory.createXYLineChart(
    			title, // 标题   
                x, // categoryAxisLabel （category轴，横轴，X轴标签）   
                y, // valueAxisLabel（value轴，纵轴，Y轴的标签）   
                dataset, // dataset   
                PlotOrientation.VERTICAL,
                true, // legend   
                false, // tooltips   
                false); // URLs   

        // 使用CategoryPlot设置各种参数。以下设置可以省略。   
        XYPlot plot = (XYPlot) chart.getPlot();   
        // 背景色 透明度   
        plot.setBackgroundAlpha(0.5f);   
        // 前景色 透明度   
        plot.setForegroundAlpha(0.5f);  

		TextTitle subtitle = new TextTitle("", new Font("黑体",Font.BOLD, 12));
		chart.addSubtitle(subtitle);
		chart.setTitle(new TextTitle(title, new Font("隶书", Font.ITALIC, 15)));
		chart.setBackgroundPaint(new GradientPaint(0, 0, Color.white, 0, 1000,Color.blue));

		// 输出文件到指定目录
        String rfname = Math.random()*1000+".png";
		String fileName = "D:/jfreechart" + rfname;
		try {
			//年的用600*400;月的用800*400,用PNG生生成的图片比较清楚
			ChartUtilities.saveChartAsPNG(new File(fileName), chart, 600, 400);
		} catch (IOException exz) {
			System.out.print("....Cant’t Create image File");
		}
		
        return chart;   
    }   

    private static XYDataset createXYDataset() {
		// 创建时间数据源
		XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
		
		XYSeries xyserie1 = new XYSeries("中华");
		for(int i=1;i<13;i++){
			xyserie1.add(i,new Double(200 + Math.random() * 100));
		}
		xySeriesCollection.addSeries(xyserie1);
		
		XYSeries xyserie2 = new XYSeries("芙蓉王");
		for(int i=1;i<13;i++){
			xyserie2.add(i,new Double(300 + Math.random() * 100));
		}
		xySeriesCollection.addSeries(xyserie2);
		
		XYSeries xyserie3 = new XYSeries("苏烟");
		for(int i=1;i<13;i++){
			xyserie3.add(i,new Double(500 + Math.random() * 100));
		}
		xySeriesCollection.addSeries(xyserie3);
		
		return xySeriesCollection;
	}
}
