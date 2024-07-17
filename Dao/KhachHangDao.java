/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Database.DatabaseManager;
import Model.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Nguyen Thi Thu Hien
 */
public class KhachHangDao implements DAOInterface<KhachHang> {

    public static KhachHangDao getInstance() {
        return new KhachHangDao();
    }

    @Override
    public int insert(KhachHang t) {
        int ketQua = 0;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "INSERT INTO KhachHang(HoTen, Email, SoDienThoai, DiaChi) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getHoTenKH());
            pst.setString(2, t.getEmail());
            pst.setString(3, t.getSdt());
            pst.setString(4, t.getDiaChi());
            ketQua = pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không thêm được khách hàng!" + t.getHoTenKH(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(KhachHang t) {
        int ketQua = 0;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "UPDATE KhachHang SET HoTen =?, Email=?, SoDienThoai=?, DiaChi=? WHERE MaKhachHang_Code=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getHoTenKH());
            pst.setString(2, t.getEmail());
            pst.setString(3, t.getSdt());
            pst.setString(4, t.getDiaChi());
            pst.setString(5, t.getMaKHCode());
            ketQua = pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không update được khách hàng!" + t.getHoTenKH(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(KhachHang t) {
        int ketQua = 0;
        try {
            java.sql.Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "DELETE FROM KhachHang WHERE MaKhachHang_Code=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaKHCode());
            ketQua = pst.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ArrayList<KhachHang> selectAll() {
        ArrayList<KhachHang> khachHangs = new ArrayList<>();
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "Select MaKhachHang, MaKhachHang_Code,HoTen, Email, SoDienThoai, DiaChi "
                    + "from KhachHang ";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maKhachHang = rs.getInt("MaKhachHang");
                String maKhachHang_Code = rs.getString("MaKhachHang_Code");
                String hoTen = rs.getString("HoTen");
                String email = rs.getString("Email");
                String soDienThoai = rs.getString("SoDienThoai");
                String diaChi = rs.getString("DiaChi");
                KhachHang khachHang = new KhachHang(maKhachHang, maKhachHang_Code, hoTen, email, soDienThoai, diaChi);
                khachHangs.add(khachHang);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return khachHangs;
    }

    @Override
    public KhachHang selectById(String t) {
        KhachHang khachHang = null;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "Select MaKhachHang, MaKhachHang_Code,HoTen, Email, SoDienThoai, DiaChi"
                    + " From KhachHang "
                    + " WHERE MakhachHang_Code =?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maKhachHang = rs.getInt("MaKhachHang");
                String maKhachHang_Code = rs.getString("MaKhachHang_Code");
                String hoTen = rs.getString("HoTen");
                String email = rs.getString("Email");
                String soDienThoai = rs.getString("SoDienThoai");
                String diaChi = rs.getString("DiaChi");
                khachHang = new KhachHang(maKhachHang, maKhachHang_Code, hoTen, email, soDienThoai, diaChi);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return khachHang;
    }
    
    
    public KhachHang selectById(int t) {
        KhachHang khachHang = null;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "Select MaKhachHang, MaKhachHang_Code,HoTen, Email, SoDienThoai, DiaChi"
                    + " From KhachHang "
                    + " WHERE MaKhachHang =?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maKhachHang = rs.getInt("MaKhachHang");
                String maKhachHang_Code = rs.getString("MaKhachHang_Code");
                String hoTen = rs.getString("HoTen");
                String email = rs.getString("Email");
                String soDienThoai = rs.getString("SoDienThoai");
                String diaChi = rs.getString("DiaChi");
                khachHang = new KhachHang(maKhachHang, maKhachHang_Code, hoTen, email, soDienThoai, diaChi);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return khachHang;
    }

}
