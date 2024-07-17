/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Objects;

/**
 *
 * @author Nguyen Thi Thu Hien
 */
public class LoHang {

    private int maLoHang;
    private String maLoHangCode;
    private int maSanPham;
    private int soLuong;
    private int maNhapKho;


    

    private double giaBan;
    private double giaBanNhap;
    private String ngayNhap;
    private String tenSanPham;
    private String danhMuc;
    private String nhaXuatBan;
    private String maSanPhamCode;
    private double tongTienNhap;

    private int soLuongNhap;
    private int soLuongBan;
    private String ngayBan;
    private double thanhTien;

    public LoHang(int maLoHang, String maLoHangCode, int maSanPham, int soLuong, double giaBan, String ngayNhap) {
        this.maLoHang = maLoHang;
        this.maLoHangCode = maLoHangCode;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.ngayNhap = ngayNhap;
    }
    
    public LoHang(int maLoHang, String maLoHangCode, String tenSanPham, int soLuong, double giaBan, String ngayNhap) {
        this.maLoHang = maLoHang;
        this.maLoHangCode = maLoHangCode;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.ngayNhap = ngayNhap;
    }

    public LoHang(int maLoHang, String maLoHangCode, int maSanPham, int soLuong, double giaBan, double giaBanNhap,
            String ngayNhap, String tenSanPham, String danhMuc, String nhaXuatBan) {
        this.maLoHang = maLoHang;
        this.maLoHangCode = maLoHangCode;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.giaBanNhap = giaBanNhap;
        this.ngayNhap = ngayNhap;
        this.tenSanPham = tenSanPham;
        this.danhMuc = danhMuc;
        this.nhaXuatBan = nhaXuatBan;
    }

    

    public LoHang(int maLoHang, String maLoHangCode, int maSanPham, int soLuong, double giaBan, double giaBanNhap,
            String ngayNhap, String tenSanPham, String danhMuc, String nhaXuatBan, String maSanPhamCode,
            int soLuongNhap, int soLuongBan) {
        this.maLoHang = maLoHang;
        this.maLoHangCode = maLoHangCode;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.giaBanNhap = giaBanNhap;
        this.ngayNhap = ngayNhap;
        this.tenSanPham = tenSanPham;
        this.danhMuc = danhMuc;
        this.nhaXuatBan = nhaXuatBan;
        this.maSanPhamCode = maSanPhamCode;
        this.soLuongNhap = soLuongNhap;
        this.soLuongBan = soLuongBan;
    }

    public LoHang(String maLoHangCode, String tenSanPham, int soLuong, String ngay) {
        this.maLoHangCode = maLoHangCode;
        this.soLuongNhap = soLuong;
        this.ngayNhap = ngay;
        this.tenSanPham = tenSanPham;
    }
    public LoHang(String maLoHangCode, String tenSanPham, int soLuong, String ngay, double thanhTien) {
        this.maLoHangCode = maLoHangCode;
        this.soLuongBan = soLuong;
        this.ngayBan = ngay;
        this.tenSanPham = tenSanPham;
        this.thanhTien = thanhTien;
    }


    public LoHang() {
    }

    public int getMaLoHang() {
        return maLoHang;
    }

    public void setMaLoHang(int maLoHang) {
        this.maLoHang = maLoHang;
    }

    public String getMaLoHangCode() {
        return maLoHangCode;
    }

    public void setMaLoHangCode(String maLoHangCode) {
        this.maLoHangCode = maLoHangCode;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public double getGiaBanNhap() {
        return giaBanNhap;
    }

    public void setGiaBanNhap(double giaBanNhap) {
        this.giaBanNhap = giaBanNhap;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    // ===================================
    public int getSoLuongNhap() {
        return soLuongNhap;
    }

    public void setSoLuongNhap(int soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }

    public int getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(int soLuongBan) {
        this.soLuongBan = soLuongBan;
    }

    public String getMaSanPhamCode() {
        return maSanPhamCode;
    }

    public void setMaSanPhamCode(String maSanPhamCode) {
        this.maSanPhamCode = maSanPhamCode;
    }

    public double getTongTienNhap() {
        return tongTienNhap;
    }

    public void setTongTienNhap(double tongTienNhap) {
        this.tongTienNhap = tongTienNhap;
    }
    public int getMaNhapKho() {
        return maNhapKho;
    }

    public void setMaNhapKho(int maNhapKho) {
        this.maNhapKho = maNhapKho;
    }

    public String getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(String ngayBan) {
        this.ngayBan = ngayBan;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.maLoHang);
        hash = 29 * hash + Objects.hashCode(this.maLoHangCode);
        hash = 29 * hash + Objects.hashCode(this.maSanPham);
        hash = 29 * hash + Objects.hashCode(this.soLuong);
        hash = 29 * hash + Objects.hashCode(this.giaBan);
        hash = 29 * hash + Objects.hashCode(this.ngayNhap);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LoHang other = (LoHang) obj;
        if (!Objects.equals(this.maLoHang, other.maLoHang)) {
            return false;
        }
        if (!Objects.equals(this.maLoHangCode, other.maLoHangCode)) {
            return false;
        }
        if (!Objects.equals(this.maSanPham, other.maSanPham)) {
            return false;
        }

        if (!Objects.equals(this.soLuong, other.soLuong)) {
            return false;
        }
        if (!Objects.equals(this.giaBan, other.giaBan)) {
            return false;
        }
        return Objects.equals(this.ngayNhap, other.ngayNhap);
    }

    @Override
    public String toString() {
        return "LoHang{" + "maLoHang=" + maLoHang + ", maLoHangCode=" + maLoHangCode + ", maSanPham=" + maSanPham
                + ", soLuong=" + soLuong + ", giaBan=" + giaBan + ",ngayNhap=" + ngayNhap + '}';
    }

}
