/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Nguyen Thi Thu Hien
 */
public class NhanVienKPI {
    private String HoTen;
    private int SoLuongDonHang;

    public NhanVienKPI() {
    }

    public NhanVienKPI(String HoTen, int SoLuongDonHang) {
        this.HoTen = HoTen;
        this.SoLuongDonHang = SoLuongDonHang;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public int getSoLuongDonHang() {
        return SoLuongDonHang;
    }

    public void setSoLuongDonHang(int SoLuongDonHang) {
        this.SoLuongDonHang = SoLuongDonHang;
    }
    
    
}
