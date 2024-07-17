/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author TieuSoi
 */
public class BanHang {

    public static Object getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public BanHang(int maDonHang, String maDonHangCode, String ngayBan, int maKH, int maNguoiDung) {
        this.maDonHang = maDonHang;
        this.maDonHangCode = maDonHangCode;
        this.ngayBan = ngayBan;
        this.maKH = maKH;
        this.maNguoiDung = maNguoiDung;
    }

    public BanHang() {
    }

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getMaDonHangCode() {
        return maDonHangCode;
    }

    public void setMaDonHangCode(String maDonHangCode) {
        this.maDonHangCode = maDonHangCode;
    }

    public String getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(String ngayBan) {
        this.ngayBan = ngayBan;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    private int maDonHang;
    private String maDonHangCode;
    private String ngayBan;
    private int maKH;
    private int maNguoiDung;
    private String tenNguoiDung;
    private String tenKhachHang;

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.maDonHang);
        hash = 29 * hash + Objects.hashCode(this.maDonHangCode);
        hash = 29 * hash + Objects.hashCode(this.ngayBan);
        hash = 29 * hash + Objects.hashCode(this.maKH);
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
        final BanHang other = (BanHang) obj;
        if (!Objects.equals(this.maDonHang, other.maDonHang)) {
            return false;
        }
        if (!Objects.equals(this.maDonHangCode, other.maDonHangCode)) {
            return false;
        }
        if (!Objects.equals(this.ngayBan, other.ngayBan)) {
            return false;
        }
        if (!Objects.equals(this.maKH, other.maKH)) {
            return false;
        }
        return Objects.equals(this.maNguoiDung, other.maNguoiDung);
    }

    @Override
    public String toString() {
        return "BanHang{" + "maDonHang=" + maDonHang + ",maDonHangCode=" + maDonHangCode + ", ngayBan=" + ngayBan
                + ", maKH=" + maKH + ", maNguoiDung=" + maNguoiDung + '}';
    }
    public BanHang(int maDonHang, String maDonHangCode, String ngayBan, String tenKhachHang, String tenNguoiDung) {
        this.maDonHang = maDonHang;
        this.maDonHangCode = maDonHangCode;
        this.ngayBan = ngayBan;
        this.tenKhachHang = tenKhachHang;
        this.tenNguoiDung = tenNguoiDung;
    }

}
