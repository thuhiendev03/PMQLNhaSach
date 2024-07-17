/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Controller.ConvertDate;
import Database.DatabaseManager;
import Model.BanHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class BanHangDao implements DAOInterface<BanHang> {

    public static BanHangDao getInstance() {
        return new BanHangDao();
    }

    @Override
    public int insert(BanHang t) {
        int ketQua = 0;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "INSERT INTO BanHang (ngayBan, maKhachHang, maNguoiDung) VALUES (?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);

            ConvertDate converter = ConvertDate.getInstance();
            java.util.Date parsedDate = converter.changeFrom(t.getNgayBan());
            Timestamp sqlTimestamp = new Timestamp(parsedDate.getTime());

            pst.setTimestamp(1, sqlTimestamp);
            pst.setInt(2, t.getMaKH());
            pst.setInt(3, t.getMaNguoiDung());
            ketQua = pst.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không thêm được đơn hàng! " + t.getMaDonHang(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(BanHang t) {
        int ketQua = 0;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "UPDATE BanHang SET maDonHang_Code=?, ngayBan=?, maKhachHang=?, maNguoiDung=? WHERE maDonHang=?";
            PreparedStatement pst = con.prepareStatement(sql);

            ConvertDate converter = ConvertDate.getInstance();
            java.util.Date parsedDate = converter.changeFrom(t.getNgayBan());
            Timestamp sqlTimestamp = new Timestamp(parsedDate.getTime());

            pst.setString(1, t.getMaDonHangCode());
            pst.setTimestamp(2, sqlTimestamp);
            pst.setInt(3, t.getMaKH());
            pst.setInt(4, t.getMaNguoiDung());
            pst.setInt(5, t.getMaDonHang());
            ketQua = pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không cập nhật được đơn hàng! " + t.getMaDonHang(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(BanHang t) {
        int ketQua = 0;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "DELETE FROM BanHang WHERE maDonHang=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getMaDonHang());
            ketQua = pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không xóa được đơn hàng! " + t.getMaDonHang(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ArrayList<BanHang> selectAll() {
        try {
            ArrayList<BanHang> ketQua = new ArrayList<>();
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT * FROM BanHang";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maDonHang = rs.getInt("MaDonHang");
                String maDonHangCode = rs.getString("MaDonHang_Code");
                Timestamp ngayBanTimestamp = rs.getTimestamp("NgayBan");
                String ngayBan = ngayBanTimestamp.toString();
                int maKH = rs.getInt("MaKhachHang");
                int maNguoiDung = rs.getInt("MaNguoiDung");
                BanHang banHang = new BanHang(maDonHang, maDonHangCode, ngayBan, maKH, maNguoiDung);
                ketQua.add(banHang);
            }
            return ketQua;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không lấy được danh sách đơn hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public BanHang selectById(String id) {
        BanHang ketQua = null;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT * FROM BanHang WHERE maDonHang=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(id));
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int maDonHang = rs.getInt("maDonHang");
                String maDonHangCode = rs.getString("maDonHang_Code");
                Timestamp ngayBanTimestamp = rs.getTimestamp("ngayBan");
                String ngayBan = ngayBanTimestamp.toString();
                int maKH = rs.getInt("maKhachHang");
                int maNguoiDung = rs.getInt("maNguoiDung");
                ketQua = new BanHang(maDonHang, maDonHangCode, ngayBan, maKH, maNguoiDung);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không lấy được đơn hàng! " + id, "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    public BanHang selectByAll(int maND, int maKh, String ngayLap) {
        BanHang ketQua = null;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT DISTINCT * FROM BanHang WHERE ngayBan = ? AND maKhachHang = ? AND maNguoiDung = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, ngayLap);
            pst.setInt(2, maKh);
            pst.setInt(3, maND);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int maDonHang = rs.getInt("maDonHang");
                String maDonHangCode = rs.getString("maDonHang_Code");
                Timestamp ngayBanTimestamp = rs.getTimestamp("ngayBan");
                String ngayBan = ngayBanTimestamp.toString();
                int maKH = rs.getInt("maKhachHang");
                int maNguoiDung = rs.getInt("maNguoiDung");
                ketQua = new BanHang(maDonHang, maDonHangCode, ngayBan, maKH, maNguoiDung);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<BanHang> selectAllName() {
        try {
            ArrayList<BanHang> ketQua = new ArrayList<>();
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT BH.MaDonHang, BH.MaDonHang_Code, BH.NgayBan, ND.HoTen AS TenNguoiDung, KH.HoTen AS TenKhachHang \n"
                    + "	FROM BanHang BH JOIN NguoiDung ND ON BH.MaNguoiDung = ND.MaNguoiDung \n"
                    + "	JOIN KhachHang KH ON BH.MaKhachHang = KH.MaKhachHang";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maDonHang = rs.getInt("MaDonHang");
                String maDonHangCode = rs.getString("MaDonHang_Code");
                Timestamp ngayBanTimestamp = rs.getTimestamp("NgayBan");
                String ngayBan = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ngayBanTimestamp);
                String tenNguoiDung = rs.getString("TenNguoiDung");
                String tenKhachHang = rs.getString("TenKhachHang");
                BanHang banHang = new BanHang(maDonHang, maDonHangCode, ngayBan, tenKhachHang, tenNguoiDung);
                ketQua.add(banHang);
            }
            return ketQua;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không lấy được danh sách đơn hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return null;

    }
}
