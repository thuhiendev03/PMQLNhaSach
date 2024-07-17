/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Database.DatabaseManager;
import Model.LoHang;
import Model.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Nguyen Thi Thu Hien
 */
public class SanPhamDao implements DAOInterface<SanPham> {

    public static SanPhamDao getInstance() {
        return new SanPhamDao();
    }

    @Override
    public int insert(SanPham t) {
        int ketQua = 0;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "INSERT INTO SanPham(TenSanPham, DanhMuc, TacGia, NhaXuatBan, GiaBanNhap, MaNhaCungCap) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTenSanPham());
            pst.setString(2, t.getDanhMuc());
            pst.setString(3, t.getTacGia());
            pst.setString(4, t.getNhaXuatBan());
            pst.setDouble(5, t.getGiaBanNhap());
            pst.setInt(6, t.getMaNCC());
            ketQua = pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không thêm được sản phẩm!" + t.getTenSanPham(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(SanPham t) {
        int ketQua = 0;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "UPDATE SanPham SET TenSanPham=?, DanhMuc=?, TacGia=?, NhaXuatBan=?, GiaBanNhap=?, MaNhaCungCap=? WHERE MaSanPham_Code=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTenSanPham());
            pst.setString(2, t.getDanhMuc());
            pst.setString(3, t.getTacGia());
            pst.setString(4, t.getNhaXuatBan());
            pst.setDouble(5, t.getGiaBanNhap());
            pst.setInt(6, t.getMaNCC());
            pst.setString(7, t.getMaSanPhamCode());
            ketQua = pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không Update được sản phẩm!" + t.getTenSanPham(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(SanPham t) {
        int ketQua = 0;
        try {
            java.sql.Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "DELETE FROM SANPHAM WHERE MaSanPham_Code=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaSanPhamCode());
            ketQua = pst.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ArrayList<SanPham> selectAll() {
        ArrayList<SanPham> sanPhams = new ArrayList<>();
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "Select MaSanPham, MaSanPham_Code,TenSanPham, DanhMuc, "
                    + "TacGia, NhaXuatBan, GiaBanNhap, sanPham.MaNhaCungCap, TenNhaCungCap "
                    + " from sanPham, NhaCungCap "
                    + " Where SanPham.MaNhaCungCap = NhaCungCap.MaNhaCungCap ";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maSanPham = rs.getInt("MaSanPham");
                String maSanPham_Code = rs.getString("MaSanPham_Code");
                String tenSanPham = rs.getString("TenSanPham");
                String danhMuc = rs.getString("DanhMuc");
                String tacGia = rs.getString("TacGia");
                String nxb = rs.getString("NhaXuatBan");
                Double giaNhap = rs.getDouble("GiaBanNhap");
                int maNCC = rs.getInt("MaNhaCungCap");
                String tenNCC = rs.getString("TenNhaCungCap");
                SanPham sanPham = new SanPham(maSanPham, maSanPham_Code, tenSanPham, danhMuc, tacGia, nxb, tenNCC,
                        maNCC, giaNhap);
                sanPhams.add(sanPham);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sanPhams;
    }

    @Override
    public SanPham selectById(String t) {
        SanPham sanPham = null;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "Select distinct MaSanPham, MaSanPham_Code, TenSanPham, DanhMuc, "
                    + "TacGia, NhaXuatBan, GiaBanNhap, sanPham.MaNhaCungCap, TenNhaCungCap "
                    + "FROM sanPham, NhaCungCap "
                    + "WHERE SanPham.MaNhaCungCap = NhaCungCap.MaNhaCungCap and MaSanPham_Code =?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maSanPham = rs.getInt("MaSanPham");
                String maSanPham_Code = rs.getString("MaSanPham_Code");
                String tenSanPham = rs.getString("TenSanPham");
                String danhMuc = rs.getString("DanhMuc");
                String tacGia = rs.getString("TacGia");
                String nxb = rs.getString("NhaXuatBan");
                Double giaNhap = rs.getDouble("GiaBanNhap");
                int maNCC = rs.getInt("MaNhaCungCap");
                String tenNCC = rs.getString("TenNhaCungCap");
                sanPham = new SanPham(maSanPham, maSanPham_Code, tenSanPham, danhMuc, tacGia, nxb, tenNCC, maNCC,
                        giaNhap);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sanPham;
    }

}
