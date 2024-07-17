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
public class NhapKho {
    private int maNhapKho;
    private String maNhapKhoCode;
    private String ngayNhap;
    private int maNhaCungCap;
    private int maNguoiDung;
    // ===================================
    private String maLoHangCode;
    private String tenSanPham;
    private int soLuong;
    private double giaBan;
    private String tenNhaCungCap;
    private String tenNguoiDung;

    // ===================================
//    private double tongTien = 0;


    public NhapKho() {
    }

    public NhapKho(int maNhapKho, String maNhapKhoCode, String ngayNhap, int maNhaCungCap, int maNguoiDung) {
        this.maNhapKho = maNhapKho;
        this.maNhapKhoCode = maNhapKhoCode;
        this.ngayNhap = ngayNhap;
        this.maNhaCungCap = maNhaCungCap;
        this.maNguoiDung = maNguoiDung;
    }

    public NhapKho(String maNhapKhoCode, String maLoHangCode, String tenSanPham, int soLuong, double giaBan,
            String ngayNhap, String tenNhaCungCap, String tenNguoiDung) {
        this.maNhapKhoCode = maNhapKhoCode;
        this.maLoHangCode = maLoHangCode;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.ngayNhap = ngayNhap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.tenNguoiDung = tenNguoiDung;
    }
    //  Phiếu Nhập
    public NhapKho(String maNhapKhoCode, String ngayNhap, String tenNhaCungCap, String tenNguoiDung)
    {
        this.maNhapKhoCode = maNhapKhoCode;
        this.ngayNhap = ngayNhap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.tenNguoiDung = tenNguoiDung;
    }
    public NhapKho(int maNhapKho, String maNhapKhoCode, String ngayNhap, String tenNhaCungCap, String tenNguoiDung)
    {
        this.maNhapKho = maNhapKho;
        this.maNhapKhoCode = maNhapKhoCode;
        this.ngayNhap = ngayNhap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.tenNguoiDung = tenNguoiDung;
    }

    public int getMaNhapKho() {
        return maNhapKho;
    }

    public void setMaNhapKho(int maNhapKho) {
        this.maNhapKho = maNhapKho;
    }

    public String getMaNhapKhoCode() {
        return maNhapKhoCode;
    }

    public void setMaNhapKhoCode(String maNhapKhoCode) {
        this.maNhapKhoCode = maNhapKhoCode;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public int getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(int maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }
    // =========================================================================Neww
    // by Thu Hien==============

    public String getMaLoHangCode() {
        return maLoHangCode;
    }

    public void setMaLoHangCode(String maLoHangCode) {
        this.maLoHangCode = maLoHangCode;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
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

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }
    // ==============================================================================================

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.maNhapKho);
        hash = 29 * hash + Objects.hashCode(this.maNhapKhoCode);
        hash = 29 * hash + Objects.hashCode(this.ngayNhap);
        hash = 29 * hash + Objects.hashCode(this.maNhaCungCap);
        hash = 29 * hash + Objects.hashCode(this.maNguoiDung);
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
        final NhapKho other = (NhapKho) obj;
        if (!Objects.equals(this.maNhapKho, other.maNhapKho)) {
            return false;
        }
        if (!Objects.equals(this.maNhapKhoCode, other.maNhapKhoCode)) {
            return false;
        }
        if (!Objects.equals(this.ngayNhap, other.ngayNhap)) {
            return false;
        }
        if (!Objects.equals(this.maNhaCungCap, other.maNhaCungCap)) {
            return false;
        }
        return Objects.equals(this.maNguoiDung, other.maNguoiDung);
    }

    @Override
    public String toString() {
        return "NhapKho{" + "maNhapKho=" + maNhapKho + ", maNhapKhoCode=" + maNhapKhoCode + ", ngayNhap=" + ngayNhap
                + ", maNhaCungCap=" + maNhaCungCap + ", maNguoiDung=" + maNguoiDung + '}';
    }

}
