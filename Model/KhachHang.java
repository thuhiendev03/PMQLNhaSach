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
public class KhachHang {
    private int maKH;
    private String maKHCode;
    private String hoTenKH;
    private String email;
    private String Sdt;
    private String diaChi;

    public KhachHang() {
    }

    public KhachHang(int maKH, String maKHCode, String hoTenKH, String email, String Sdt, String diaChi) {
        this.maKH = maKH;
        this.maKHCode = maKHCode;
        this.hoTenKH = hoTenKH;
        this.email = email;
        this.Sdt = Sdt;
        this.diaChi = diaChi;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getMaKHCode() {
        return maKHCode;
    }

    public void setMaKHCode(String maKHCode) {
        this.maKHCode = maKHCode;
    }

    public String getHoTenKH() {
        return hoTenKH;
    }

    public void setHoTenKH(String hoTenKH) {
        this.hoTenKH = hoTenKH;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.maKH);
        hash = 29 * hash + Objects.hashCode(this.maKHCode);
        hash = 29 * hash + Objects.hashCode(this.hoTenKH);
        hash = 29 * hash + Objects.hashCode(this.Sdt);
        hash = 29 * hash + Objects.hashCode(this.diaChi);
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
        final KhachHang other = (KhachHang) obj;
        if (!Objects.equals(this.maKH, other.maKH)) {
            return false;
        }
        if (!Objects.equals(this.maKHCode, other.maKHCode)) {
            return false;
        }
        if (!Objects.equals(this.hoTenKH, other.hoTenKH)) {
            return false;
        }
        if (!Objects.equals(this.Sdt, other.Sdt)) {
            return false;
        }
        return Objects.equals(this.diaChi, other.diaChi);
    }

    @Override
    public String toString() {
        return "KhachHang{" + "maKH=" + maKH + ", maKHCode=" + maKHCode + ",hoTenKH=" + hoTenKH + ",email = " + email
                + ", Sdt=" + Sdt + ", diaChi=" + diaChi + '}';
    }

}
