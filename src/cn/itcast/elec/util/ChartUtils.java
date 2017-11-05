package cn.itcast.elec.util;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis3D;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;

import cn.itcast.elec.web.form.ElecUserForm;

public class ChartUtils {

	public static String getUserBarChart(List<ElecUserForm> list) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		/*添加数据*/
		for (int i = 0; list != null && i < list.size(); i++) {
			ElecUserForm elecUserForm = list.get(i);
			dataset.setValue(Double.valueOf(elecUserForm.getJctcount()), "所属单位", elecUserForm.getJctname());
		}
		JFreeChart chart = ChartFactory.createBarChart3D(
				"用户统计报表（所属单位）", //主标题
				"所属单位名称", //x轴标签
				"数量", //Y轴标签
				dataset,  //图标显示的数据集合
				PlotOrientation.VERTICAL, //图表显示的形式 
				true,  //是否显示子标题 
				true,  //是否生成提示的标签
				true);  //是否生成url链接
		/*处理图形上的乱码*/
		/*处理主标题上的乱码*/
		chart.getTitle().setFont(new Font("宋体",Font.BOLD,18));
		/*处理子标题的乱码*/
		chart.getLegend().setItemFont(new Font("宋体",Font.BOLD,15));
		/*处理图标区域的对象乱码*/
		CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();
		CategoryAxis3D categoryAxis3D = (CategoryAxis3D) categoryPlot.getDomainAxis();
		NumberAxis3D numberAxis3D = (NumberAxis3D) categoryPlot.getRangeAxis();
		/*处理X轴上的对象乱码*/
		categoryAxis3D.setTickLabelFont(new Font("宋体",Font.BOLD,15));
		/*处理X轴外的对象乱码*/
		categoryAxis3D.setLabelFont(new Font("宋体", Font.BOLD, 15));
		/*处理Y轴上的对象乱码*/
		numberAxis3D.setTickLabelFont(new Font("宋体",Font.BOLD,15));
		/*处理Y轴外的对象乱码*/
		numberAxis3D.setLabelFont(new Font("宋体",Font.BOLD,15));
		/*设置Y轴上显示的粒度*/
		numberAxis3D.setAutoTickUnitSelection(false);
		numberAxis3D.setTickUnit(new NumberTickUnit(1));
		/*获取绘图区域对象*/
		BarRenderer3D barRenderer3D = (BarRenderer3D) categoryPlot.getRenderer();
		/*设置柱形图的宽度*/
		barRenderer3D.setMaximumBarWidth(0.03);
		/*在图形上显示数字*/
		barRenderer3D.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		barRenderer3D.setBaseItemLabelsVisible(true);
		barRenderer3D.setBaseItemLabelFont(new Font("宋体",Font.BOLD,15));
		
		ServletContext context = ServletActionContext.getServletContext();
		String realPath = context.getRealPath("/chart");
		String filename = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		/*在D盘目录下生成图片*/
		File file = new File(realPath + "\\" + filename);
		try {
			ChartUtilities.saveChartAsJPEG(file, chart, 500, 500);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filename;
	}

}
