/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Database.DatabaseManager;
import Model.NguoiDung;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class NguoiDungDao implements DAOInterface<NguoiDung> {

    public static NguoiDungDao getInstance() {
        return new NguoiDungDao();
    }

    @Override
    public int insert(NguoiDung t) {
        int result = 0;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "INSERT INTO NguoiDung(TenDangNhap, MatKhau, HoTen, Email, Quyen) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTenDangNhap());
            pst.setString(2, t.getMatKhau());
            pst.setString(3, t.getHoTen());
            pst.setString(4, t.getEmail());
            pst.setString(5, t.getQuyen());
            result = pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cannot add user!" + t.getMaNguoiDungCode(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(NguoiDung t) {
        int result = 0;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "UPDATE NguoiDung SET TenDangNhap = ?, MatKhau = ?, HoTen = ?, Email = ?, Quyen = ? WHERE MaNguoiDung_Code = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTenDangNhap());
            pst.setString(2, t.getMatKhau());
            pst.setString(3, t.getHoTen());
            pst.setString(4, t.getEmail());
            pst.setString(5, t.getQuyen());
            pst.setString(6, t.getMaNguoiDungCode());
            result = pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cannot update user!" + t.getMaNguoiDungCode(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(NguoiDung t) {
        int result = 0;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "DELETE FROM NguoiDung WHERE MaNguoiDung_Code = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaNguoiDungCode());
            result = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<NguoiDung> selectAll() {
        ArrayList<NguoiDung> nguoiDungs = new ArrayList<>();
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT MaNguoiDung, MaNguoiDung_Code, TenDangNhap, MatKhau, HoTen, Email, Quyen FROM NguoiDung";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maNguoiDung = rs.getInt("MaNguoiDung");
                String maNguoiDungCode = rs.getString("MaNguoiDung_Code");
                String tenDangNhap = rs.getString("TenDangNhap");
                String matKhau = rs.getString("MatKhau");
                String hoTen = rs.getString("HoTen");
                String email = rs.getString("Email");
                String quyen = rs.getString("Quyen");
                NguoiDung nguoiDung = new NguoiDung(maNguoiDung, maNguoiDungCode, tenDangNhap, matKhau, hoTen, email,
                        quyen);
                nguoiDungs.add(nguoiDung);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nguoiDungs;
    }

    @Override
    public NguoiDung selectById(String t) {
        NguoiDung nguoiDung = null;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT MaNguoiDung, MaNguoiDung_Code, TenDangNhap, MatKhau, HoTen, Email, Quyen FROM NguoiDung WHERE TenDangNhap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int maNguoiDung = rs.getInt("MaNguoiDung");
                String maNguoiDungCode = rs.getString("MaNguoiDung_Code");
                String tenDangNhap = rs.getString("TenDangNhap");
                String matKhau = rs.getString("MatKhau");
                String hoTen = rs.getString("HoTen");
                String email = rs.getString("Email");
                String quyen = rs.getString("Quyen");
                nguoiDung = new NguoiDung(maNguoiDung, maNguoiDungCode, tenDangNhap, matKhau, hoTen, email, quyen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nguoiDung;
    }


       public int updatePassword(String email, String password) {
        int ketQua = 0;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "UPDATE NguoiDung SET MatKhau=? WHERE email=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, password);
            pst.setString(2, email);

            ketQua = pst.executeUpdate();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    // ==================
    public List<NguoiDung> getUserOrderCounts() {
        List<NguoiDung> userList = new ArrayList<>();
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT nd.HoTen, nd.Email, nd.TenDangNhap, nd.Quyen, " +
                    "COUNT(bh.MaDonHang) AS SoLuongDonHang " +
                    "FROM NguoiDung nd " +
                    "LEFT JOIN BanHang bh ON nd.MaNguoiDung = bh.MaNguoiDung " +
                    "GROUP BY nd.HoTen, nd.Email, nd.TenDangNhap, nd.Quyen";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String hoTen = rs.getString("HoTen");
                String email = rs.getString("Email");
                String tenDangNhap = rs.getString("TenDangNhap");
                String quyen = rs.getString("Quyen");
                int soLuongDonHang = rs.getInt("SoLuongDonHang");
                NguoiDung user = new NguoiDung(hoTen, email, tenDangNhap, quyen, soLuongDonHang);
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }
}
