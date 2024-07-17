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

public class NhaCungCap {
    private int maNhaCungCap;
    private String maNhaCungCapCode;
    private String tenNhaCungCap;
    private String nguoiLienHe;
    private String soDienThoai;
    private String Email;

    public NhaCungCap() {
    }

    public NhaCungCap(int maNhaCungCap, String maNhaCungCapCode, String tenNhaCungCap, String nguoiLienHe,
            String soDienThoai, String Email) {
        this.maNhaCungCap = maNhaCungCap;
        this.maNhaCungCapCode = maNhaCungCapCode;
        this.tenNhaCungCap = tenNhaCungCap;
        this.nguoiLienHe = nguoiLienHe;
        this.soDienThoai = soDienThoai;
        this.Email = Email;
    }

    public NhaCungCap(String maNhaCungCapCode, String tenNhaCungCap, String nguoiLienHe, String soDienThoai,
            String Email) {
        this.maNhaCungCap = maNhaCungCap;
        this.maNhaCungCapCode = maNhaCungCapCode;
        this.tenNhaCungCap = tenNhaCungCap;
        this.nguoiLienHe = nguoiLienHe;
        this.soDienThoai = soDienThoai;
        this.Email = Email;
    }

    public int getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(int maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getMaNhaCungCapCode() {
        return maNhaCungCapCode;
    }

    public void setMaNhaCungCapCode(String maNhaCungCapCode) {
        this.maNhaCungCapCode = maNhaCungCapCode;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getNguoiLienHe() {
        return nguoiLienHe;
    }

    public void setNguoiLienHe(String nguoiLienHe) {
        this.nguoiLienHe = nguoiLienHe;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.maNhaCungCap);
        hash = 29 * hash + Objects.hashCode(this.maNhaCungCapCode);
        hash = 29 * hash + Objects.hashCode(this.tenNhaCungCap);
        hash = 29 * hash + Objects.hashCode(this.nguoiLienHe);
        hash = 29 * hash + Objects.hashCode(this.soDienThoai);
        hash = 29 * hash + Objects.hashCode(this.Email);
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
        final NhaCungCap other = (NhaCungCap) obj;
        if (!Objects.equals(this.maNhaCungCap, other.maNhaCungCap)) {
            return false;
        }
        if (!Objects.equals(this.maNhaCungCapCode, other.maNhaCungCapCode)) {
            return false;
        }
        if (!Objects.equals(this.tenNhaCungCap, other.tenNhaCungCap)) {
            return false;
        }
        if (!Objects.equals(this.nguoiLienHe, other.nguoiLienHe)) {
            return false;
        }
        if (!Objects.equals(this.soDienThoai, other.soDienThoai)) {
            return false;
        }
        return Objects.equals(this.Email, other.Email);
    }

    @Override
    public String toString() {
        return "NhaCungCap{" + "maNhaCungCap=" + maNhaCungCap + ", maNhaCungCapCode=" + maNhaCungCapCode
                + ", tenNhaCungCap=" + tenNhaCungCap + ", nguoiLienHe=" + nguoiLienHe + ", soDienThoai=" + soDienThoai
                + ",Email=" + Email + '}';
    }

}