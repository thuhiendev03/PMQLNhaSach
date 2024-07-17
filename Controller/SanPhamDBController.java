/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.BanHangDao;
import Dao.ChiTietBanHangDao;
import Dao.KhachHangDao;
import Dao.LoHangDao;
import Dao.NhapKhoDao;
import Model.BanHang;
import Model.ChiTietBanHang;
import Model.KhachHang;
import Model.LoHang;
import Model.NhapKho;
import View.FormBanHang;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author TieuSoi
 */
public class SanPhamDBController {
      public static SanPhamDBController getInstance() {
        return new SanPhamDBController();
    }
    
      
     public BanHang insertBanHang(int maNguoiDung, int maKhachHang, String NgayTao, ArrayList<ChiTietBanHang> dsSanPham) {
        
          BanHang banHang = new BanHang();
        banHang.setMaKH(maKhachHang);
        banHang.setMaNguoiDung(maNguoiDung);
        banHang.setNgayBan(NgayTao);
        BanHangDao.getInstance().insert(banHang);
        banHang = BanHangDao.getInstance().selectByAll(maNguoiDung, maKhachHang, NgayTao);
        if (banHang != null)
            for (ChiTietBanHang chiTietBanHang : dsSanPham) {
               LoHang loHang = LoHangDao.getInstance().selectById(chiTietBanHang.getMaLoHangCode());
               if(chiTietBanHang.getSoLuong() > loHang.getSoLuong())
               {
                   JOptionPane.showMessageDialog(null, "Số lượng không đủ !", "Lỗi", JOptionPane.ERROR_MESSAGE);
                   return null;
               }else 
               {
                   chiTietBanHang.setMaDonHang(banHang.getMaDonHang());
                   ChiTietBanHangDao.getInstance().insert(chiTietBanHang);
                   loHang.setSoLuong(loHang.getSoLuong() - chiTietBanHang.getSoLuong());
                   LoHangDao.getInstance().update(loHang);
               }
            }
        else 
                  JOptionPane.showMessageDialog(null, "không tìm thấy banHang!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return banHang;
    }
     
     
     public boolean insertNhapHang(int maNguoiDung, int maKhachHang, String NgayTao, ArrayList<LoHang> dsSanPham) {
        
         NhapKho nhapKho = new NhapKho();
         nhapKho.setMaNhaCungCap(maKhachHang);
         nhapKho.setMaNguoiDung(maNguoiDung);
         nhapKho.setNgayNhap(NgayTao);
         
         NhapKhoDao.getInstance().insert(nhapKho);
         nhapKho = NhapKhoDao.getInstance().selectByAll(maNguoiDung, maKhachHang, NgayTao);
        if (nhapKho != null)
            for (LoHang chiTietBanHang : dsSanPham) {
                chiTietBanHang.setNgayNhap(nhapKho.getNgayNhap());
                chiTietBanHang.setMaNhapKho(nhapKho.getMaNhapKho());
                LoHangDao.getInstance().insert(chiTietBanHang);
            }
        else 
            JOptionPane.showMessageDialog(null, "không tìm thấy banHang!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return true;
    }
}
