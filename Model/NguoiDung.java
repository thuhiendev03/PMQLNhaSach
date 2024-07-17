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
public class NguoiDung {
    private int maNguoiDung;
    private String maNguoiDungCode;
    private String tenDangNhap;
    private String matKhau;
    private String hoTen;
    private String email;
    private String quyen;
    private int SoLuongDonHang;

    public NguoiDung() {
    }

    public NguoiDung(int maNguoiDung, String maNguoiDungCode, String tenDangNhap, String matKhau, String hoTen,
            String email, String quyen) {
        this.maNguoiDung = maNguoiDung;
        this.maNguoiDungCode = maNguoiDungCode;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.email = email;
        this.quyen = quyen;
    }

    public NguoiDung(String hoTen, String email, String tenDangNhap, String quyen, int SoLuongDonHang) {
        this.hoTen = hoTen;
        this.email = email;
        this.tenDangNhap = tenDangNhap;
        this.quyen = quyen;
        this.SoLuongDonHang = SoLuongDonHang;
    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getMaNguoiDungCode() {
        return maNguoiDungCode;
    }

    public void setMaNguoiDungCode(String maNguoiDungCode) {
        this.maNguoiDungCode = maNguoiDungCode;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }
    // ==========================================================================

    public int getSoLuongDonHang() {
        return SoLuongDonHang;
    }

    public void setSoLuongDonHang(int SoLuongDonHang) {
        this.SoLuongDonHang = SoLuongDonHang;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.maNguoiDung);
        hash = 29 * hash + Objects.hashCode(this.maNguoiDungCode);
        hash = 29 * hash + Objects.hashCode(this.tenDangNhap);
        hash = 29 * hash + Objects.hashCode(this.matKhau);
        hash = 29 * hash + Objects.hashCode(this.hoTen);
        hash = 29 * hash + Objects.hashCode(this.email);
        hash = 29 * hash + Objects.hashCode(this.quyen);
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
        final NguoiDung other = (NguoiDung) obj;
        if (!Objects.equals(this.maNguoiDung, other.maNguoiDung)) {
            return false;
        }
        if (!Objects.equals(this.maNguoiDungCode, other.maNguoiDungCode)) {
            return false;
        }
        if (!Objects.equals(this.tenDangNhap, other.tenDangNhap)) {
            return false;
        }
        if (!Objects.equals(this.matKhau, other.matKhau)) {
            return false;
        }
        if (!Objects.equals(this.hoTen, other.hoTen)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.quyen, other.quyen);
    }

    @Override
    public String toString() {
        return "NguoiDung{" + "maNguoiDung=" + maNguoiDung + ", maNguoiDungCode=" + maNguoiDungCode + ", tenDangNhap="
                + tenDangNhap + ", matKhau=" + matKhau + ",hoTen=" + hoTen + ",email=" + email + ", quyen=" + quyen
                + '}';
    }
}
