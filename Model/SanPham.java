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

public class SanPham {

    public SanPham(int maSanPham, String maSanPhamCode, String tenSanPham, String danhMuc, String tacGia,
            String nhaXuatBan, String tenNhaCungCap, int maNCC, double giaBanNhap) {
        this.maSanPham = maSanPham;
        this.maSanPhamCode = maSanPhamCode;
        this.tenSanPham = tenSanPham;
        this.danhMuc = danhMuc;
        this.tacGia = tacGia;
        this.nhaXuatBan = nhaXuatBan;
        this.tenNhaCungCap = tenNhaCungCap;
        this.maNCC = maNCC;
        this.giaBanNhap = giaBanNhap;
    }

    private int maSanPham;

    private String maSanPhamCode;

    private String tenSanPham;

    private String danhMuc;

    private String tacGia;

    private String nhaXuatBan;
    private String tenNhaCungCap;
    private int maNCC;

    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    private double giaBanNhap;

    public SanPham(int maSanPham, String maSanPhamCode, String tenSanPham, String danhMuc, String tacGia,
            String nhaXuatBan, double giaBanNhap) {
        this.maSanPham = maSanPham;
        this.maSanPhamCode = maSanPhamCode;
        this.tenSanPham = tenSanPham;
        this.danhMuc = danhMuc;
        this.tacGia = tacGia;
        this.nhaXuatBan = nhaXuatBan;
        this.giaBanNhap = giaBanNhap;
    }

    public SanPham() {
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getMaSanPhamCode() {
        return maSanPhamCode;
    }

    public void setMaSanPhamCode(String maSanPhamCode) {
        this.maSanPhamCode = maSanPhamCode;
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

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public double getGiaBanNhap() {
        return giaBanNhap;
    }

    public void setGiaBanNhap(double giaBanNhap) {
        this.giaBanNhap = giaBanNhap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.maSanPham);
        hash = 29 * hash + Objects.hashCode(this.maSanPhamCode);
        hash = 29 * hash + Objects.hashCode(this.tenSanPham);
        hash = 29 * hash + Objects.hashCode(this.danhMuc);
        hash = 29 * hash + Objects.hashCode(this.tacGia);
        hash = 29 * hash + Objects.hashCode(this.nhaXuatBan);
        hash = 29 * hash + Objects.hashCode(this.giaBanNhap);
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
        final SanPham other = (SanPham) obj;
        if (!Objects.equals(this.maSanPham, other.maSanPham)) {
            return false;
        }
        if (!Objects.equals(this.maSanPhamCode, other.maSanPhamCode)) {
            return false;
        }
        if (!Objects.equals(this.tenSanPham, other.tenSanPham)) {
            return false;
        }
        if (!Objects.equals(this.danhMuc, other.danhMuc)) {
            return false;
        }
        if (!Objects.equals(this.tacGia, other.tacGia)) {
            return false;
        }
        if (!Objects.equals(this.nhaXuatBan, other.nhaXuatBan)) {
            return false;
        }
        return Objects.equals(this.giaBanNhap, other.giaBanNhap);
    }

    @Override
    public String toString() {
        return "SanPham{" + "maSanPham=" + maSanPham + ", maSanPhamCode=" + maSanPhamCode + ", tenSanPham=" + tenSanPham
                + ", danhMuc=" + danhMuc + ", tacGia=" + tacGia + ", nhaXuatBan=" + nhaXuatBan + ", giaBanNhap="
                + giaBanNhap + '}';
    }

}
