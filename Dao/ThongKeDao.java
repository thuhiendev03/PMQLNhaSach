/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Database.DatabaseManager;
import Model.NhanVienKPI;
import Model.SanPhamStatistical;
import com.itextpdf.text.log.Level;
import common.Logger;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Nguyen Thi Thu Hien
 */
public class ThongKeDao implements ThongKeInterface{

    @Override
    public List<SanPhamStatistical> getListBySanPham() {
        try
        {
            Connection con = (Connection) DatabaseManager.getInstance().getConnection();
            ArrayList<SanPhamStatistical> list = new ArrayList<>();
            String sql = "SELECT NgayBan, SUM(SoLuong) AS TongSoLuong FROM BanHang " +
                    "JOIN ChiTietBanHang ON BanHang.MaDonHang = ChiTietBanHang.MaDonHang " +
                    "GROUP BY NgayBan ORDER BY NgayBan";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                    SanPhamStatistical sanPhamStatistical = new SanPhamStatistical();
                    sanPhamStatistical.setNgay(rs.getString("NgayBan"));
                    sanPhamStatistical.setSoLuongBan(rs.getInt("TongSoLuong"));
                    list.add(sanPhamStatistical);
                    
            }
            return list;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<SanPhamStatistical> getListBySanPhamNhap() {
        try
        {
            Connection con = (Connection) DatabaseManager.getInstance().getConnection();
            ArrayList<SanPhamStatistical> list = new ArrayList<>();
            String sql = "SELECT NgayNhap, SUM(SoLuong) AS TongSoLuong FROM LoHang " +
                    "GROUP BY NgayNhap ORDER BY NgayNhap";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                    SanPhamStatistical sanPhamStatistical = new SanPhamStatistical();
                    sanPhamStatistical.setNgay(rs.getString("NgayNhap"));
                    sanPhamStatistical.setSoLuongBan(rs.getInt("TongSoLuong"));
                    list.add(sanPhamStatistical);
                    
            }
            return list;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<NhanVienKPI> getListByNhanVien() {
        try
        {
            Connection con = (Connection) DatabaseManager.getInstance().getConnection();
            ArrayList<NhanVienKPI> list = new ArrayList<>();
            String sql = "SELECT nd.HoTen,COUNT(bh.MaDonHang) AS SoLuongDonHang " +
                    "FROM NguoiDung nd " +
                    "LEFT JOIN BanHang bh ON nd.MaNguoiDung = bh.MaNguoiDung " +
                    "GROUP BY nd.HoTen";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                    NhanVienKPI nhanVienKPI = new NhanVienKPI();
                    nhanVienKPI.setHoTen(rs.getString("HoTen"));
                    nhanVienKPI.setSoLuongDonHang(rs.getInt("SoLuongDonHang"));
                    list.add(nhanVienKPI);
                    
            }
            return list;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
