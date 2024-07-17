/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author TieuSoi
 */
public class UserController {

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

    public UserController(int maNguoiDung, String maNguoiDungCode, String tenDangNhap, String matKhau, String hoTen, String email, String quyen) {
        this.maNguoiDung = maNguoiDung;
        this.maNguoiDungCode = maNguoiDungCode;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.email = email;
        this.quyen = quyen;
    }
    private int maNguoiDung;
    private String maNguoiDungCode;
    private String tenDangNhap;
    private String matKhau;
    private String hoTen;
    private String email;
    private String quyen;
    
     private static UserController instance;
     
       public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }
       
       
    public UserController()
    {}
}
