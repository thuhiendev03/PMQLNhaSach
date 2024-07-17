/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Database.DatabaseManager;
import Model.NhaCungCap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author TieuSoi
 */
public class NhaCungCapDao implements DAOInterface<NhaCungCap> {

    public static NhaCungCapDao getInstance() {
        return new NhaCungCapDao();
    }

    @Override
    public int insert(NhaCungCap t) {
        int ketQua = 0;
        try {
            java.sql.Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "INSERT INTO NhaCungCap (TenNhaCungCap, NguoiLienHe, SoDienThoai, Email) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTenNhaCungCap());
            pst.setString(2, t.getNguoiLienHe());
            pst.setString(3, t.getSoDienThoai());
            pst.setString(4, t.getEmail());
            ketQua = pst.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "Không thêm được nhà cung cấp " + t.getMaNhaCungCap(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(NhaCungCap t) {
        int ketQua = 0;
        try {
            java.sql.Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "UPDATE NhaCungCap SET TenNhaCungCap=?, NguoiLienHe=?, SoDienThoai=?, Email = ? WHERE MaNhaCungCap_Code=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTenNhaCungCap());
            pst.setString(2, t.getNguoiLienHe());
            pst.setString(3, t.getSoDienThoai());
            pst.setString(4, t.getEmail());
            pst.setString(5, t.getMaNhaCungCapCode());
            ketQua = pst.executeUpdate();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;

    }

    @Override
    public int delete(NhaCungCap t) {
        int ketQua = 0;
        try {
            java.sql.Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "DELETE FROM NhaCungCap WHERE MaNhaCungCap_Code=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaNhaCungCapCode());
            ketQua = pst.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ArrayList<NhaCungCap> selectAll() {
        ArrayList<NhaCungCap> ketQua = new ArrayList<NhaCungCap>();
        try {
            java.sql.Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT * FROM NhaCungCap";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maNhaCungCap = rs.getInt("MaNhaCungCap");
                String maNhaCungCapCode = rs.getString("MaNhaCungCap_Code");
                String tenNhaCungCap = rs.getString("TenNhaCungCap");
                String nguoiLienHe = rs.getString("NguoiLienHe");
                String soDienThoai = rs.getString("SoDienThoai");
                String Email = rs.getString("Email");
                NhaCungCap ncc = new NhaCungCap(maNhaCungCap, maNhaCungCapCode, tenNhaCungCap, nguoiLienHe, soDienThoai,
                        Email);
                ketQua.add(ncc);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public NhaCungCap selectById(String t) {
        NhaCungCap ketQua = null;
        try {

            java.sql.Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT * FROM NhaCungCap WHERE MaNhaCungCap_Code=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maNhaCungCap = rs.getInt("MaNhaCungCap");
                String maNhaCungCapCode = rs.getString("MaNhaCungCap_Code");
                String tenNhaCungCap = rs.getString("TenNhaCungCap");
                String nguoiLienHe = rs.getString("NguoiLienHe");
                String soDienThoai = rs.getString("SoDienThoai");
                String Email = rs.getString("Email");
                ketQua = new NhaCungCap(maNhaCungCap, maNhaCungCapCode, tenNhaCungCap, nguoiLienHe, soDienThoai, Email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
}
