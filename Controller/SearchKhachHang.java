/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.KhachHangDao;
import Dao.KhachHangDao;
import Model.KhachHang;
import java.util.ArrayList;

/**
 *
 * @author TieuSoi
 */
public class SearchKhachHang {
    public static SearchKhachHang getInstance()
    {
        return new SearchKhachHang();
    }

    public ArrayList<KhachHang> searchTatCa(String text) {
        ArrayList<KhachHang> result = new ArrayList<>();
        ArrayList<KhachHang> armt = KhachHangDao.getInstance().selectAll();
        for (var ncc : armt) {
            if (String.valueOf(ncc.getMaKH()).contains(text.toLowerCase())
                    || ncc.getHoTenKH().toLowerCase().contains(text.toLowerCase())
                    || ncc.getEmail().toLowerCase().contains(text.toLowerCase())
                    || ncc.getSdt().toLowerCase().contains(text.toLowerCase())
                    || ncc.getDiaChi().toLowerCase().contains(text.toLowerCase())) {
                result.add(ncc);
            }
        }
        return result;
    }

    public ArrayList<KhachHang> searchTenKH(String text) {
        ArrayList<KhachHang> result = new ArrayList<>();
        ArrayList<KhachHang> armt = KhachHangDao.getInstance().selectAll();
        for (var ncc : armt) {
            if (ncc.getHoTenKH().toLowerCase().contains(text.toLowerCase())) {
                result.add(ncc);
            }
        }
        return result;
    }

    public ArrayList<KhachHang> searchMaKH(String text) {
        ArrayList<KhachHang> result = new ArrayList<>();
        ArrayList<KhachHang> armt = KhachHangDao.getInstance().selectAll();
        for (var ncc : armt) {
             if (ncc.getMaKHCode().toLowerCase().contains(text.toLowerCase())) {
                result.add(ncc);
            }
        }
        return result;
    }

    public ArrayList<KhachHang> searchEmail(String text) {
       ArrayList<KhachHang> result = new ArrayList<>();
       ArrayList<KhachHang> armt = KhachHangDao.getInstance().selectAll();
        for (var ncc : armt) {
          if (ncc.getEmail().toLowerCase().contains(text.toLowerCase())) {
                result.add(ncc);
          }
       }
        return result;
    }

    public ArrayList<KhachHang> searchSdt(String text) {
        ArrayList<KhachHang> result = new ArrayList<>();
        ArrayList<KhachHang> armt = KhachHangDao.getInstance().selectAll();
        for (var ncc : armt) {
            if (ncc.getSdt().toLowerCase().contains(text.toLowerCase())) {
                result.add(ncc);
            }
        }
        return result;
    }
     public KhachHang getKHBySdt(String text) {
        KhachHang result = new KhachHang();
        ArrayList<KhachHang> armt = KhachHangDao.getInstance().selectAll();
        for (var ncc : armt) {
            if (ncc.getSdt().toLowerCase().contains(text.toLowerCase())) {
                result = ncc;
            }
        }
        return result;
    }
    
      public KhachHang getKhachHangbySdt(String keyword) {
            KhachHang result = null;
            ArrayList<KhachHang> armt = KhachHangDao.getInstance().selectAll();
            for (var  ncc : armt) {
                if (ncc.getSdt().toLowerCase().contains(keyword.toLowerCase())) {
                    result = ncc;
                }
            }
            return result;
      }
    
}
