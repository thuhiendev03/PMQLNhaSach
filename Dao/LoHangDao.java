package Dao;

import Controller.ConvertDate;
import Database.DatabaseManager;
import Model.LoHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class LoHangDao implements DAOInterface<LoHang> {

    public static LoHangDao getInstance() {
        return new LoHangDao();
    }

    @Override
    public int insert(LoHang t) {
        int result = 0;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "INSERT INTO LoHang(MaNhapKho, MaSanPham, SoLuong, GiaBan, NgayNhap) VALUES ( ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getMaNhapKho());
            pst.setInt(2, t.getMaSanPham());
            pst.setInt(3, t.getSoLuong());
            pst.setDouble(4, t.getGiaBan());
            ConvertDate converter = ConvertDate.getInstance();
            java.util.Date parsedDate = converter.changeFrom(t.getNgayNhap());
            java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(parsedDate.getTime());
            // JOptionPane.showMessageDialog(null, "Thêm thành công
            // "+sqlTimestamp.toString() , "Thông báo", JOptionPane.CANCEL_OPTION);
            pst.setTimestamp(5, sqlTimestamp);
            result = pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cannot add batch!" + t.getMaLoHangCode(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(LoHang t) {
        int result = 0;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "UPDATE LoHang SET MaSanPham = ?, SoLuong = ?, GiaBan = ?, NgayNhap = ? WHERE MaLoHang_Code = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getMaSanPham());
            pst.setInt(2, t.getSoLuong());
            pst.setDouble(3, t.getGiaBan());
            pst.setString(4, t.getNgayNhap());
            pst.setString(5, t.getMaLoHangCode());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cannot update batch!" + t.getMaLoHangCode(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    @Override
    public int delete(LoHang t) {
        int result = 0;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "DELETE FROM LoHang WHERE MaLoHang_Code = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaLoHangCode());
            result = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<LoHang> selectAll() {
        ArrayList<LoHang> loHangs = new ArrayList<>();
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT "
                    + "    LoHang.MaLoHang,"
                    + "    LoHang.MaLoHang_Code,"
                    + "    LoHang.MaSanPham,"
                    + "    SanPham.TenSanPham,"
                    + "    LoHang.SoLuong,"
                    + "    LoHang.GiaBan,"
                    + "    SanPham.GiaBanNhap,"
                    + "    LoHang.NgayNhap,"
                    + "    SanPham.DanhMuc,"
                    + "    SanPham.NhaXuatBan"
                    + " FROM "
                    + "    SanPham "
                    + " INNER JOIN "
                    + "    LoHang ON SanPham.MaSanPham = LoHang.MaSanPham;";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maLoHang = rs.getInt("MaLoHang");
                String maLoHang_Code = rs.getString("MaLoHang_Code");
                int maSanPham = rs.getInt("MaSanPham");
                int soLuong = rs.getInt("SoLuong");
                double giaBan = rs.getDouble("GiaBan");
                double giaBanNhap = rs.getDouble("GiaBanNhap");
                String ngayNhap = rs.getString("NgayNhap");
                String tenSanPham = rs.getString("TenSanPham");
                String danhMuc = rs.getString("DanhMuc");
                String nhaXuatBan = rs.getString("NhaXuatBan");
                LoHang loHang = new LoHang(maLoHang, maLoHang_Code, maSanPham, soLuong, giaBan, giaBanNhap, ngayNhap,
                        tenSanPham, danhMuc, nhaXuatBan);
                loHangs.add(loHang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loHangs;
    }

    @Override
    public LoHang selectById(String t) {
        LoHang loHang = null;
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT MaLoHang, MaLoHang_Code, MaSanPham, SoLuong, GiaBan, NgayNhap FROM LoHang WHERE MaLoHang_Code = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int maLoHang = rs.getInt("MaLoHang");
                String maLoHang_Code = rs.getString("MaLoHang_Code");
                int maSanPham = rs.getInt("MaSanPham");
                int soLuong = rs.getInt("SoLuong");
                double giaBan = rs.getDouble("GiaBan");
                String ngayNhap = rs.getString("NgayNhap");
                loHang = new LoHang(maLoHang, maLoHang_Code, maSanPham, soLuong, giaBan, ngayNhap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loHang;
    }

    public ArrayList<LoHang> selectAllWithQuantitiesNhap() {
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            ArrayList<LoHang> sanPhamChiTiets = new ArrayList<>();
            String sql = "SELECT LoHang.MaLoHang_Code, SanPham.TenSanPham, LoHang.SoLuong as SoLuongNhap, "
                    + "LoHang.NgayNhap "
                    + "FROM LoHang "
                    + "JOIN SanPham ON LoHang.MaSanPham = SanPham.MaSanPham "
                    + "GROUP BY LoHang.MaLoHang_Code, SanPham.TenSanPham, LoHang.SoLuong, "
                    + "LoHang.NgayNhap";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maSanPham = rs.getString("MaLoHang_Code");
                String tenSanPham = rs.getString("TenSanPham");
                int soLuongNhap = rs.getInt("SoLuongNhap");
                String ngayNhap = rs.getString("NgayNhap");

                LoHang sanPhamThongKe = new LoHang(maSanPham, tenSanPham, soLuongNhap, ngayNhap);
                sanPhamChiTiets.add(sanPhamThongKe);
            }
            return sanPhamChiTiets;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<LoHang> selectAllWithChiTietPhieuNhap(int id) {
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            ArrayList<LoHang> sanPhamChiTiets = new ArrayList<>();

            String sql = "SELECT "
                    + "    LoHang.MaLoHang, "
                    + "    LoHang.MaLoHang_Code, "
                    + "    LoHang.MaSanPham, "
                    + "    SanPham.TenSanPham, "
                    + "    LoHang.SoLuong, "
                    + "    LoHang.GiaBan, "
                    + "    SanPham.GiaBanNhap, "
                    + "    LoHang.NgayNhap, "
                    + "    SanPham.DanhMuc, "
                    + "    SanPham.NhaXuatBan "
                    + "FROM "
                    + "    LoHang "
                    + "INNER JOIN "
                    + "    SanPham ON SanPham.MaSanPham = LoHang.MaSanPham "
                    + "WHERE "
                    + "    LoHang.MaNhapKho = ?;";  // Ensure the table alias is correct and there is a space before WHERE

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maLoHang = rs.getInt("MaLoHang");
                String maLoHang_Code = rs.getString("MaLoHang_Code");
                int maSanPham = rs.getInt("MaSanPham");
                String tenSanPham = rs.getString("TenSanPham");
                int soLuong = rs.getInt("SoLuong");
                double giaBan = rs.getDouble("GiaBan");
                double giaBanNhap = rs.getDouble("GiaBanNhap");
                String ngayNhap = rs.getString("NgayNhap");
                String danhMuc = rs.getString("DanhMuc");
                String nhaXuatBan = rs.getString("NhaXuatBan");
                LoHang sanPhamThongKe = new LoHang(maLoHang, maLoHang_Code, maSanPham, soLuong, giaBan, giaBanNhap, ngayNhap, tenSanPham, danhMuc, nhaXuatBan);
                sanPhamChiTiets.add(sanPhamThongKe);
            }
            return sanPhamChiTiets;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<LoHang> selectAllWithQuantitiesBan() {
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            ArrayList<LoHang> sanPhamChiTiets = new ArrayList<>();
            String sql = " SELECT LoHang.MaLoHang_Code, SanPham.TenSanPham,"
                    + "ISNULL(ChiTietBanHang.SoLuong,0) as SoLuongBanHang, BanHang.NgayBan, ChiTietBanHang.TongTien "
                    + "FROM LoHang "
                    + "JOIN SanPham ON LoHang.MaSanPham = SanPham.MaSanPham "
                    + "LEFT JOIN ChiTietBanHang ON LoHang.MaLoHang = ChiTietBanHang.MaLoHang "
                    + "LEFT JOIN BanHang ON BanHang.MaDonHang = ChiTietBanHang.MaDonHang "
                    + "GROUP BY LoHang.MaLoHang_Code, SanPham.TenSanPham, ChiTietBanHang.SoLuong,BanHang.NgayBan, ChiTietBanHang.TongTien";

            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maSanPham = rs.getString("MaLoHang_Code");
                String tenSanPham = rs.getString("TenSanPham");
                int soLuongBanHang = rs.getInt("SoLuongBanHang");
                String ngayBan = rs.getString("NgayBan");
                double thanhTien = rs.getDouble("TongTien");

                if (ngayBan != null) {
                    LoHang sanPhamThongKeBan = new LoHang(maSanPham, tenSanPham, soLuongBanHang, ngayBan, thanhTien);
                    sanPhamChiTiets.add(sanPhamThongKeBan);
                }
            }
            return sanPhamChiTiets;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //========================================LỌC NGÀY THÔNG THƯỜNG====================================

    public List<LoHang> filterProductsByDate(String startDate, String endDate) {
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            ArrayList<LoHang> list = new ArrayList<>();
            String sql = "SELECT LoHang.MaLoHang_Code, SanPham.TenSanPham, LoHang.SoLuong as SoLuongNhap, "
                    + "LoHang.NgayNhap "
                    + "FROM LoHang "
                    + "JOIN SanPham ON LoHang.MaSanPham = SanPham.MaSanPham "
                    + "WHERE LoHang.NgayNhap BETWEEN ? AND ? "
                    + "GROUP BY LoHang.MaLoHang_Code, SanPham.TenSanPham, LoHang.SoLuong, "
                    + "LoHang.NgayNhap";
            PreparedStatement pst = con.prepareStatement(sql);

            // Định dạng ngày tháng cho câu truy vấn
            SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd-MM-yyyy");

            String formattedStartDate = dbDateFormat.format(inputDateFormat.parse(startDate));
            String formattedEndDate = dbDateFormat.format(inputDateFormat.parse(endDate));

            pst.setString(1, formattedStartDate);
            pst.setString(2, formattedEndDate);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maLoHangCode = rs.getString("MaLoHang_Code");
                String tenSanPham = rs.getString("TenSanPham");
                int soLuongNhap = rs.getInt("SoLuongNhap");
                String ngayNhap = rs.getString("NgayNhap");

                LoHang thongKe = new LoHang(maLoHangCode, tenSanPham, soLuongNhap, ngayNhap);
                list.add(thongKe);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //==========================Lọc trước tìm sau FORM NHẬP =================================
    public List<LoHang> filterProductsByDateAndSearchNhap(String startDate, String endDate, String searchText) {
        try {
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Kiểm tra giá trị ngày tháng hợp lệ
            Date startDateParsed, endDateParsed;
            try {
                startDateParsed = inputDateFormat.parse(startDate);
                endDateParsed = inputDateFormat.parse(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
                System.err.println("Lỗi định dạng ngày tháng. Vui lòng nhập ngày tháng hợp lệ.");
                return null;
            }

            Connection con = DatabaseManager.getInstance().getConnection();
            ArrayList<LoHang> list = new ArrayList<>();
            String sql = "SELECT LoHang.MaLoHang_Code, SanPham.TenSanPham, LoHang.SoLuong as SoLuongNhap, "
                    + "LoHang.NgayNhap "
                    + "FROM LoHang "
                    + "JOIN SanPham ON LoHang.MaSanPham = SanPham.MaSanPham "
                    + "WHERE LoHang.NgayNhap BETWEEN ? AND ? "
                    + "AND (SanPham.TenSanPham LIKE ? OR LoHang.MaLoHang_Code LIKE ?) "
                    + "GROUP BY LoHang.MaLoHang_Code, SanPham.TenSanPham, LoHang.SoLuong, LoHang.NgayNhap";

            PreparedStatement pst = con.prepareStatement(sql);

            // Định dạng ngày tháng cho câu truy vấn
            String formattedStartDate = dbDateFormat.format(startDateParsed);
            String formattedEndDate = dbDateFormat.format(endDateParsed);

            pst.setString(1, formattedStartDate);
            pst.setString(2, formattedEndDate);

            // Tìm kiếm theo tên sản phẩm hoặc mã lô hàng
            pst.setString(3, "%" + searchText + "%");
            pst.setString(4, "%" + searchText + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maLoHangCode = rs.getString("MaLoHang_Code");
                String tenSanPham = rs.getString("TenSanPham");
                int soLuongNhap = rs.getInt("SoLuongNhap");
                String ngayNhap = rs.getString("NgayNhap");

                LoHang thongKe = new LoHang(maLoHangCode, tenSanPham, soLuongNhap, ngayNhap);
                list.add(thongKe);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //===============================Tìm sản phẩm trước rồi lọc FORM NHẬP=================
    public List<LoHang> searchProductNhap(String searchText) {
        List<LoHang> list = new ArrayList<>();
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT LoHang.MaLoHang_Code, SanPham.TenSanPham, LoHang.SoLuong as SoLuongNhap, "
                    + "LoHang.NgayNhap "
                    + "FROM LoHang "
                    + "JOIN SanPham ON LoHang.MaSanPham = SanPham.MaSanPham "
                    + "WHERE SanPham.TenSanPham LIKE ? OR LoHang.MaLoHang_Code LIKE ?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, "%" + searchText + "%");
            pst.setString(2, "%" + searchText + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maLoHangCode = rs.getString("MaLoHang_Code");
                String tenSanPham = rs.getString("TenSanPham");
                int soLuongNhap = rs.getInt("SoLuongNhap");
                String ngayNhap = rs.getString("NgayNhap");

                LoHang thongKe = new LoHang(maLoHangCode, tenSanPham, soLuongNhap, ngayNhap);
                list.add(thongKe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //=========Lọc trước tìm sau FORM NHẬP
    public List<LoHang> filterAfterAndSearchNhap(List<LoHang> products, String startDate, String endDate) {
        List<LoHang> filteredList = new ArrayList<>();
        SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date start = inputDateFormat.parse(startDate);
            Date end = inputDateFormat.parse(endDate);

            for (LoHang product : products) {
                Date productDate = dbDateFormat.parse(product.getNgayNhap());
                if (!productDate.before(start) && !productDate.after(end)) {
                    filteredList.add(product);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return filteredList;
    }

    //    //=====================================lỌC trước tìm sau FORM BÁN=============
    public List<LoHang> filterProductsByDateAndSearchBan(String startDate, String endDate, String searchText) {
        try {
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Kiểm tra giá trị ngày tháng hợp lệ
            Date startDateParsed, endDateParsed;
            try {
                startDateParsed = inputDateFormat.parse(startDate);
                endDateParsed = inputDateFormat.parse(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
                System.err.println("Lỗi định dạng ngày tháng. Vui lòng nhập ngày tháng hợp lệ.");
                return null;
            }

            Connection con = DatabaseManager.getInstance().getConnection();
            ArrayList<LoHang> list = new ArrayList<>();
            String sql = "SELECT LoHang.MaLoHang_Code, SanPham.TenSanPham, ChiTietBanHang.SoLuong as SoLuongBanHang, BanHang.NgayBan , ChiTietBanHang.TongTien "
                    + "FROM LoHang "
                    + "JOIN SanPham ON LoHang.MaSanPham = SanPham.MaSanPham "
                    + "LEFT JOIN ChiTietBanHang ON LoHang.MaLoHang = ChiTietBanHang.MaLoHang "
                    + "LEFT JOIN BanHang ON BanHang.MaDonHang = ChiTietBanHang.MaDonHang "
                    + "WHERE BanHang.NgayBan BETWEEN ? AND ? "
                    + "AND (SanPham.TenSanPham LIKE ? OR LoHang.MaLoHang_Code LIKE ?) "
                    + "GROUP BY LoHang.MaLoHang_Code, SanPham.TenSanPham, ChiTietBanHang.SoLuong , BanHang.NgayBan, ChiTietBanHang.TongTien";

            PreparedStatement pst = con.prepareStatement(sql);

            // Định dạng ngày tháng cho câu truy vấn
            String formattedStartDate = dbDateFormat.format(startDateParsed);
            String formattedEndDate = dbDateFormat.format(endDateParsed);

            pst.setString(1, formattedStartDate);
            pst.setString(2, formattedEndDate);

            // Tìm kiếm theo tên sản phẩm hoặc mã lô hàng
            pst.setString(3, "%" + searchText + "%");
            pst.setString(4, "%" + searchText + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maLoHangCode = rs.getString("MaLoHang_Code");
                String tenSanPham = rs.getString("TenSanPham");
                int soLuongBan = rs.getInt("SoLuongBanHang");
                String ngayBan = rs.getString("NgayBan");
                double thanhTien = rs.getDouble("TongTien");
                LoHang thongKe = new LoHang(maLoHangCode, tenSanPham, soLuongBan, ngayBan, thanhTien);
                list.add(thongKe);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    //=====================================Tìm trước lọc sau FORM BÁN=============
    public List<LoHang> searchProductBan(String searchText) {
        List<LoHang> list = new ArrayList<>();
        try {
            Connection con = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT LoHang.MaLoHang_Code, SanPham.TenSanPham, ChiTietBanHang.SoLuong as SoLuongBanHang, BanHang.NgayBan , ChiTietBanHang.TongTien "
                    + "FROM LoHang "
                    + "JOIN SanPham ON LoHang.MaSanPham = SanPham.MaSanPham "
                    + "LEFT JOIN ChiTietBanHang ON LoHang.MaLoHang = ChiTietBanHang.MaLoHang "
                    + "LEFT JOIN BanHang ON BanHang.MaDonHang = ChiTietBanHang.MaDonHang "
                    + "WHERE SanPham.TenSanPham LIKE ? OR LoHang.MaLoHang_Code LIKE ? "
                    + "GROUP BY LoHang.MaLoHang_Code, SanPham.TenSanPham, ChiTietBanHang.SoLuong, BanHang.NgayBan, ChiTietBanHang.TongTien";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, "%" + searchText + "%");
            pst.setString(2, "%" + searchText + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maLoHangCode = rs.getString("MaLoHang_Code");
                String tenSanPham = rs.getString("TenSanPham");
                int soLuongBan = rs.getInt("SoLuongBanHang");
                String ngayBan = rs.getString("NgayBan");
                double thanhTien = rs.getDouble("TongTien");
                LoHang thongKe = new LoHang(maLoHangCode, tenSanPham, soLuongBan, ngayBan, thanhTien);
                list.add(thongKe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<LoHang> filterAfterAndSearchBan(List<LoHang> products, String startDate, String endDate) {
        List<LoHang> filteredList = new ArrayList<>();
        SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date start = inputDateFormat.parse(startDate);
            Date end = inputDateFormat.parse(endDate);

            for (LoHang product : products) {
                String ngayBan = product.getNgayBan();
                if (ngayBan != null) {
                    Date productDate = dbDateFormat.parse(ngayBan);
                    if (!productDate.before(start) && !productDate.after(end)) {
                        filteredList.add(product);
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return filteredList;
    }

}
