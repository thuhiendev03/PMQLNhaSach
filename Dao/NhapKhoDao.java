/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Controller.ConvertDate;
import Model.NhapKho;
import Database.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Nguyen Thi Thu Hien
 */
public class NhapKhoDao implements DAOInterface<NhapKho> {

    public static NhapKhoDao getInstance() {
        return new NhapKhoDao();
    }

    @Override
    public int insert(NhapKho t) {
        int ketQua = 0;
        try {
            java.sql.Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "INSERT INTO NhapKho (NgayNhap, MaNhaCungCap, MaNguoiDung) VALUES (?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);

            ConvertDate converter = ConvertDate.getInstance();
            java.util.Date parsedDate = converter.changeFrom(t.getNgayNhap());
            java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(parsedDate.getTime());
            // JOptionPane.showMessageDialog(null, "Thêm thành công
            // "+sqlTimestamp.toString() , "Thông báo", JOptionPane.CANCEL_OPTION);
            pst.setTimestamp(1, sqlTimestamp);
            pst.setInt(2, t.getMaNhaCungCap());
            pst.setInt(3, t.getMaNguoiDung());
            ketQua = pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không thêm được đơn hàng! " + t.getMaNhapKho(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(NhapKho t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(NhapKho t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<NhapKho> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public NhapKho selectById(String id) {
        NhapKho nhapKho = null;
        try {
            String sql = "SELECT NK.MaNhapKho, NK.MaNhapKho_Code, NK.NgayNhap, NK.MaNhaCungCap, NK.MaNguoiDung, ND.HoTen AS TenNguoiDung, NCC.TenNhaCungCap "
                    + "FROM NhapKho NK "
                    + "JOIN NguoiDung ND ON NK.MaNguoiDung = ND.MaNguoiDung "
                    + "JOIN NhaCungCap NCC ON NK.MaNhaCungCap = NCC.MaNhaCungCap "
                    + "WHERE NK.MaNhapKho_Code = ?";

            Connection conn = DatabaseManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int maNhapKho = rs.getInt("MaNhapKho");
                    String maNhapKhoCode = rs.getString("MaNhapKho_Code");
                    String ngayNhap = rs.getString("NgayNhap");
                    int maNhaCungCap = rs.getInt("MaNhaCungCap");
                    int maNguoiDung = rs.getInt("MaNguoiDung");
                    String tenNguoiDung = rs.getString("TenNguoiDung");
                    String tenNhaCungCap = rs.getString("TenNhaCungCap");

                    nhapKho = new NhapKho(maNhapKho, maNhapKhoCode, ngayNhap, maNhaCungCap, maNguoiDung);
                    nhapKho.setTenNguoiDung(tenNguoiDung);
                    nhapKho.setTenNhaCungCap(tenNhaCungCap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return nhapKho;
    }

    // ========================================================================================================
    public List<NhapKho> selectKhoHangformTK() {
        List<NhapKho> list = new ArrayList<>();
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT nk.MaNhapKho_Code, lh.MaLoHang_Code, sp.TenSanPham, lh.SoLuong, lh.GiaBan, "
                    + "nk.NgayNhap, ncc.TenNhaCungCap, nd.HoTen "
                    + "FROM NhapKho nk "
                    + "JOIN LoHang lh ON nk.MaNhapKho = lh.MaNhapKho "
                    + "JOIN SanPham sp ON lh.MaSanPham = sp.MaSanPham "
                    + "JOIN NhaCungCap ncc ON nk.MaNhaCungCap = ncc.MaNhaCungCap "
                    + "JOIN NguoiDung nd ON nk.MaNguoiDung = nd.MaNguoiDung";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maNhapKhoCode = rs.getString("MaNhapKho_Code");
                String maLoHangCode = rs.getString("MaLoHang_Code");
                String tenSanPham = rs.getString("TenSanPham");
                int soLuong = rs.getInt("SoLuong");
                double giaBan = rs.getDouble("GiaBan");
                String ngayNhap = rs.getString("NgayNhap");
                String tenNhaCungCap = rs.getString("TenNhaCungCap");
                String tenNguoiDung = rs.getString("HoTen");
                NhapKho nhapKho = new NhapKho(maNhapKhoCode, maLoHangCode, tenSanPham, soLuong, giaBan, ngayNhap,
                        tenNhaCungCap, tenNguoiDung);
                list.add(nhapKho);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public NhapKho selectByAll(int maNguoiDung, int mancc, String NgayTao) {
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT DISTINCT * "
                    + "FROM NhapKho "
                    + "WHERE NgayNhap = ? AND MaNhaCungCap = ? AND MaNguoiDung = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, NgayTao);
            pst.setInt(2, mancc);
            pst.setInt(3, maNguoiDung);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int maNhapKho = rs.getInt("MaNhapKho");
                String maNhapKho_code = rs.getString("MaNhapKho_Code");
                String ngayNhap = rs.getString("NgayNhap");
                int maND = rs.getInt("MaNguoiDung");
                int maNhaCungCap = rs.getInt("MaNhaCungCap");
                NhapKho nhapKho = new NhapKho();
                nhapKho.setMaNhapKho(maNhapKho);
                nhapKho.setMaNhapKhoCode(maNhapKho_code);
                nhapKho.setMaNguoiDung(maND);
                nhapKho.setMaNhaCungCap(maNhaCungCap);
                nhapKho.setNgayNhap(ngayNhap);
                return nhapKho;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<NhapKho> selectByPhieuNhap() {
        List<NhapKho> danhSachNhapKho = new ArrayList<>();
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT NhapKho.MaNhapKho_Code, NhapKho.NgayNhap, NhaCungCap.TenNhaCungCap, NguoiDung.HoTen "
                    + "FROM NhapKho "
                    + "JOIN NguoiDung ON NhapKho.MaNguoiDung = NguoiDung.MaNguoiDung "
                    + "JOIN NhaCungCap ON NhapKho.MaNhaCungCap = NhaCungCap.MaNhaCungCap";
            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maNhapKhoCode = rs.getString("MaNhapKho_Code");
                String ngayNhap = rs.getString("NgayNhap");
                String tenNhaCungCap = rs.getString("TenNhaCungCap");
                String tenNguoiDung = rs.getString("HoTen");
                NhapKho nhapKho = new NhapKho(maNhapKhoCode, ngayNhap, tenNhaCungCap, tenNguoiDung);
                danhSachNhapKho.add(nhapKho);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachNhapKho;
    }

    public List<NhapKho> selectAllPhieuNhap() {
        List<NhapKho> danhSachNhapKho = new ArrayList<>();
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT NhapKho.MaNhapKho, NhapKho.MaNhapKho_Code, NhapKho.NgayNhap, NhaCungCap.TenNhaCungCap, NguoiDung.HoTen "
                    + "FROM NhapKho "
                    + "JOIN NguoiDung ON NhapKho.MaNguoiDung = NguoiDung.MaNguoiDung "
                    + "JOIN NhaCungCap ON NhapKho.MaNhaCungCap = NhaCungCap.MaNhaCungCap";
            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maNhapKho = rs.getInt("MaNhapKho");
                String maNhapKhoCode = rs.getString("MaNhapKho_Code");
                String ngayNhap = rs.getString("NgayNhap");
                String tenNhaCungCap = rs.getString("TenNhaCungCap");
                String tenNguoiDung = rs.getString("HoTen");
                NhapKho nhapKho = new NhapKho(maNhapKho, maNhapKhoCode, ngayNhap, tenNhaCungCap, tenNguoiDung);
                danhSachNhapKho.add(nhapKho);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachNhapKho;
    }

}
