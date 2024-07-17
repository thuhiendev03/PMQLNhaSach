/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.ThongKeService;
import Dao.ThongKeServiceSP;
import Model.NhanVienKPI;
import Model.SanPhamStatistical;
import View.ThongKeBanHangChart;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;



/**
 *
 * @author Nguyen Thi Thu Hien
 */
public class QuanLyThongKe {
    
    private ThongKeService thongKeService = null;
    public QuanLyThongKe()
    {
        thongKeService = new ThongKeServiceSP();
        
    }
    public void setDateChartBan(JPanel jpnItem) throws ParseException
    {
        List<SanPhamStatistical> listItem = thongKeService.getListBySanPham();
        if(listItem != null)
        {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for(SanPhamStatistical item :listItem)
            {
                String ngay;
                ngay = ConvertDate.getInstance().changeFromToString(item.getNgay());
                dataset.addValue(item.getSoLuongBan(),"Số lượng", ngay);
                
            }
            
            
            
            JFreeChart chart = ChartFactory.createBarChart("THỐNG KÊ SÔ LƯỢNG BÁN HÀNG", "Thời gian", "Số lượng", dataset, PlotOrientation.VERTICAL, true, true, true);
            
            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            //=======Giam độ rộng của cột
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setMaximumBarWidth(0.05);
            
            //====Hien thi so luong tren moi cot
            renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
            renderer.setBaseItemLabelsVisible(true);
            
            //=======Giam khoang cách giữa các cột
            plot.getDomainAxis().setCategoryMargin(0.01);
            
            //=======Truc tung la so nguyen
            NumberAxis yAxits = (NumberAxis) plot.getRangeAxis();
            yAxits.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            
            //========Ngay theo dinh dang dd-MM-yyyy
            
            
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(),500));
            jpnItem.removeAll();
            jpnItem.setLayout(new CardLayout());
            jpnItem.add(chartPanel);
            jpnItem.validate();
            jpnItem.repaint();
        }
        
    }
    public void setDateChartNhap(JPanel jpnItem) throws ParseException
    {
        List<SanPhamStatistical> listItem = thongKeService.getListBySanPhamNhap();
        if(listItem != null)
        {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for(SanPhamStatistical item :listItem)
            {
                String ngay;
                ngay = ConvertDate.getInstance().changeFromToString(item.getNgay());
                dataset.addValue(item.getSoLuongBan(),"Số lượng", ngay);
                
            }
            
            
            
            JFreeChart chart = ChartFactory.createBarChart("THỐNG KÊ SỐ LƯỢNG NHẬP HÀNG", "Thời gian", "Số lượng", dataset, PlotOrientation.VERTICAL, true, true, true);
            
            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            //=======Giam độ rộng của cột
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setMaximumBarWidth(0.05);
            
            //====Hien thi so luong tren moi cot
            renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
            renderer.setBaseItemLabelsVisible(true);
            
            //=======Giam khoang cách giữa các cột
            plot.getDomainAxis().setCategoryMargin(0.01);
            
            //=======Truc tung la so nguyen
            NumberAxis yAxits = (NumberAxis) plot.getRangeAxis();
            yAxits.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            
            //========Ngay theo dinh dang dd-MM-yyyy
            
            
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(),500));
            jpnItem.removeAll();
            jpnItem.setLayout(new CardLayout());
            jpnItem.add(chartPanel);
            jpnItem.validate();
            jpnItem.repaint();
        }
        
    }

    public void setDateChartNhanVien(JPanel jpnItem) throws ParseException
    {
        List<NhanVienKPI> listItem = thongKeService.getListByNhanVien();
        if(listItem != null)
        {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for(NhanVienKPI item :listItem)
            {
                dataset.addValue(item.getSoLuongDonHang(),"Số lượng", item.getHoTen());
                
            }
            
            
            
            JFreeChart chart = ChartFactory.createBarChart("THỐNG KÊ KPI NHÂN VIÊN", "Nhân viên", "Số lượng", dataset, PlotOrientation.VERTICAL, true, true, true);
            
            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            //=======Giam độ rộng của cột
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setMaximumBarWidth(0.05);
            
            //====Hien thi so luong tren moi cot
            renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
            renderer.setBaseItemLabelsVisible(true);
            
            //=======Giam khoang cách giữa các cột
            plot.getDomainAxis().setCategoryMargin(0.01);
            
            //=======Truc tung la so nguyen
            NumberAxis yAxits = (NumberAxis) plot.getRangeAxis();
            yAxits.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            
            //========Ngay theo dinh dang dd-MM-yyyy
            
            
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(),500));
            jpnItem.removeAll();
            jpnItem.setLayout(new CardLayout());
            jpnItem.add(chartPanel);
            jpnItem.validate();
            jpnItem.repaint();
        }
        
    }

   
}
