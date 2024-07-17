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
public class ChiTietBanHang {

    private int maChiTietBanHang;
    private String maChiTietBanHangCode;
    private String maLoHangCode;
    private String tenSanPham;
    private int maDonHang;
    private int maLoHang;
    private int soLuong;
    private double tongTien;
    private double giaBan;

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public ChiTietBanHang(int maChiTietBanHang, String maChiTietBanHangCode, int maDonHang, int maLoHang, int soLuong,
            double tongTien) {
        this.maChiTietBanHang = maChiTietBanHang;
        this.maChiTietBanHangCode = maChiTietBanHangCode;
        this.maDonHang = maDonHang;
        this.maLoHang = maLoHang;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }

    public ChiTietBanHang(int maChiTietBanHang, String maChiTietBanHangCode, String maLoHangCode, String tenSanPham,
            int maDonHang, int maLoHang, int soLuong, double tongTien) {
        this.maChiTietBanHang = maChiTietBanHang;
        this.maChiTietBanHangCode = maChiTietBanHangCode;
        this.maLoHangCode = maLoHangCode;
        this.tenSanPham = tenSanPham;
        this.maDonHang = maDonHang;
        this.maLoHang = maLoHang;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }

    public ChiTietBanHang() {
    }

    public ChiTietBanHang(String MaPhieu, String maLoHangCode, String tenSanPham, int enteredQuantity, double d) {
        this.maLoHangCode = maLoHangCode;
        this.tenSanPham = tenSanPham;
        this.soLuong = enteredQuantity;
        this.tongTien = d;
    }

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

    public int getMaChiTietBanHang() {
        return maChiTietBanHang;
    }

    public void setMaChiTietBanHang(int maChiTietBanHang) {
        this.maChiTietBanHang = maChiTietBanHang;
    }

    public String getMaChiTietBanHangCode() {
        return maChiTietBanHangCode;
    }

    public void setMaChiTietBanHangCode(String maChiTietBanHangCode) {
        this.maChiTietBanHangCode = maChiTietBanHangCode;
    }

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
    }

    public int getMaLoHang() {
        return maLoHang;
    }

    public void setMaLoHang(int maLoHang) {
        this.maLoHang = maLoHang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.maChiTietBanHang);
        hash = 29 * hash + Objects.hashCode(this.maChiTietBanHangCode);
        hash = 29 * hash + Objects.hashCode(this.maDonHang);
        hash = 29 * hash + Objects.hashCode(this.maLoHang);
        hash = 29 * hash + Objects.hashCode(this.soLuong);
        hash = 29 * hash + Objects.hashCode(this.tongTien);
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
        final ChiTietBanHang other = (ChiTietBanHang) obj;
        if (!Objects.equals(this.maChiTietBanHang, other.maChiTietBanHang)) {
            return false;
        }
        if (!Objects.equals(this.maChiTietBanHangCode, other.maChiTietBanHangCode)) {
            return false;
        }
        if (!Objects.equals(this.maDonHang, other.maDonHang)) {
            return false;
        }
        if (!Objects.equals(this.maLoHang, other.maLoHang)) {
            return false;
        }
        if (!Objects.equals(this.soLuong, other.soLuong)) {
            return false;
        }
        return Objects.equals(this.tongTien, other.tongTien);
    }

    @Override
    public String toString() {
        return "ChiTietBanHang{" + "maChiTietBanHang=" + maChiTietBanHang + ", maChiTietBanHangCode="
                + maChiTietBanHangCode + ",maDonHang=" + maDonHang + ", maLoHang=" + maLoHang + ", soLuong=" + soLuong
                + ", tongTien=" + tongTien + '}';
    }
}
