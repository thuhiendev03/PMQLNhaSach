/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Database.DatabaseManager;
import Model.ChiTietBanHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Patrick paul
 */
public class ChiTietBanHangDao implements DAOInterface<ChiTietBanHang> {
    
    public static ChiTietBanHangDao getInstance() {
        return new ChiTietBanHangDao();
    }

    @Override
    public int insert(ChiTietBanHang t) {
        int ketQua = 0;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "INSERT INTO ChiTietBanHang ( maDonHang, maLoHang, soLuong, tongTien) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getMaDonHang());
            pst.setInt(2, t.getMaLoHang());
            pst.setInt(3, t.getSoLuong());
            pst.setDouble(4, t.getTongTien());
            ketQua = pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không thêm được chi tiết bán hàng! " + t.getMaChiTietBanHang(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(ChiTietBanHang t) {
        int ketQua = 0;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "UPDATE ChiTietBanHang SET  maDonHang = ?, maLoHang = ?, soLuong = ?, tongTien = ? WHERE maChiTietBanHang_Code = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getMaDonHang());
            pst.setInt(2, t.getMaLoHang());
            pst.setInt(3, t.getSoLuong());
            pst.setDouble(4, t.getTongTien());
            pst.setString(5, t.getMaChiTietBanHangCode());
            ketQua = pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không cập nhật được chi tiết bán hàng! " + t.getMaChiTietBanHang(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(ChiTietBanHang t) {
        int ketQua = 0;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "DELETE FROM ChiTietBanHang WHERE maChiTietBanHang = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getMaChiTietBanHang());
            ketQua = pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không xóa được chi tiết bán hàng! " + t.getMaChiTietBanHang(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ArrayList<ChiTietBanHang> selectAll() {
        ArrayList<ChiTietBanHang> ketQua = new ArrayList<>();
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT * FROM ChiTietBanHang";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ChiTietBanHang chiTietBanHang = new ChiTietBanHang();
                chiTietBanHang.setMaChiTietBanHang(rs.getInt("maChiTietBanHang"));
                chiTietBanHang.setMaChiTietBanHangCode(rs.getString("maChiTietBanHang_Code"));
                chiTietBanHang.setMaDonHang(rs.getInt("maDonHang"));
                chiTietBanHang.setMaLoHang(rs.getInt("maLoHang"));
                chiTietBanHang.setSoLuong(rs.getInt("soLuong"));
                chiTietBanHang.setTongTien(rs.getDouble("tongTien"));
                ketQua.add(chiTietBanHang);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không truy vấn được danh sách chi tiết bán hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ChiTietBanHang selectById(String id) {
        ChiTietBanHang ketQua = null;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT * FROM ChiTietBanHang WHERE maChiTietBanHang = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                ketQua = new ChiTietBanHang();
                ketQua.setMaChiTietBanHang(rs.getInt("maChiTietBanHang"));
                ketQua.setMaChiTietBanHangCode(rs.getString("maChiTietBanHang_Code"));
                ketQua.setMaDonHang(rs.getInt("maDonHang"));
                ketQua.setMaLoHang(rs.getInt("maLoHang"));
                ketQua.setSoLuong(rs.getInt("soLuong"));
                ketQua.setTongTien(rs.getDouble("tongTien"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không tìm được chi tiết bán hàng với mã: " + id, "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }
    public ArrayList<ChiTietBanHang> selectAllByIdDH(int id) {
        ArrayList<ChiTietBanHang> ketQua = new ArrayList<>();
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT * FROM ChiTietBanHang WHERE maDonHang = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ChiTietBanHang chiTietBanHang = new ChiTietBanHang();
                chiTietBanHang.setMaChiTietBanHang(rs.getInt("maChiTietBanHang"));
                chiTietBanHang.setMaChiTietBanHangCode(rs.getString("maChiTietBanHang_Code"));
                chiTietBanHang.setMaDonHang(rs.getInt("maDonHang"));
                chiTietBanHang.setMaLoHang(rs.getInt("maLoHang"));
                chiTietBanHang.setSoLuong(rs.getInt("soLuong"));
                chiTietBanHang.setTongTien(rs.getDouble("tongTien"));
                ketQua.add(chiTietBanHang);
            }
            return ketQua;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không tìm được chi tiết bán hàng với mã: " + id, "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return null;
    }
    
       public ArrayList<ChiTietBanHang> selectAllDetailByIdDH(String id) {
        ArrayList<ChiTietBanHang> ketQua = new ArrayList<>();
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "select  ChiTietBanHang.MaChiTietBanHang,  ChiTietBanHang.MaChiTietBanHang_Code,  ChiTietBanHang.MaLoHang,"
                    +           " ChiTietBanHang.SoLuong, LOHANG.MaLoHang_Code, SanPham.TenSanPham, LOHANG.GiaBan, ChiTietBanHang.TongTien \n" +
                            " from ChiTietBanHang, LoHang, SanPham \n" +
                            " where ChiTietBanHang.MaLoHang = LoHang.MaLoHang \n" +
                            "		AND SanPham.MaSanPham = LoHang.MaSanPham \n" +
                            "		AND MaDonHang = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ChiTietBanHang chiTietBanHang = new ChiTietBanHang();
                chiTietBanHang.setMaChiTietBanHang(rs.getInt("MaChiTietBanHang"));
                chiTietBanHang.setMaChiTietBanHangCode(rs.getString("MaChiTietBanHang_Code"));
                chiTietBanHang.setMaLoHang(rs.getInt("MaLoHang"));
                chiTietBanHang.setMaLoHangCode(rs.getString("MaLoHang_Code"));
               chiTietBanHang.setTenSanPham(rs.getString("TenSanPham"));
                chiTietBanHang.setSoLuong(rs.getInt("SoLuong"));
                chiTietBanHang.setGiaBan(rs.getDouble("GiaBan"));
                chiTietBanHang.setSoLuong(rs.getInt("soLuong"));
                chiTietBanHang.setTongTien(rs.getDouble("tongTien"));
                ketQua.add(chiTietBanHang);
            }
            return ketQua;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không tìm được chi tiết bán hàng với mã: " + id, "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return null;
    }
    
}
