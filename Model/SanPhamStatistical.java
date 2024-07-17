/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Nguyen Thi Thu Hien
 */
public class SanPhamStatistical {
    private String Ngay;
    private int soLuongBan;

    public SanPhamStatistical() {
    }

    public SanPhamStatistical(String Ngay, int soLuongBan) {
        this.Ngay = Ngay;
        this.soLuongBan = soLuongBan;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String Ngay) {
        this.Ngay = Ngay;
    }

    public int getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(int soLuongBan) {
        this.soLuongBan = soLuongBan;
    }

    
    
    
}
