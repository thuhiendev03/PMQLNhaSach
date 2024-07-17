/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.NhaCungCapDao;
import java.util.ArrayList;
import Model.NhaCungCap;
/**
 *
 * @author TieuSoi
 */

public class SearchNhaCungCap {

    public static SearchNhaCungCap getInstance() {
        return new SearchNhaCungCap();
    }

    public ArrayList<NhaCungCap> searchTatCa(String text) {
        ArrayList<NhaCungCap> result = new ArrayList<>();
        ArrayList<NhaCungCap> armt = NhaCungCapDao.getInstance().selectAll();
        for (var ncc : armt) {
            if (String.valueOf(ncc.getMaNhaCungCap()).contains(text.toLowerCase())
                    || ncc.getMaNhaCungCapCode().toLowerCase().contains(text.toLowerCase())
                    || ncc.getTenNhaCungCap().toLowerCase().contains(text.toLowerCase())
                    || ncc.getNguoiLienHe().toLowerCase().contains(text.toLowerCase())
                    || ncc.getSoDienThoai().toLowerCase().contains(text.toLowerCase())
                    || ncc.getEmail().toLowerCase().contains(text.toLowerCase())) {
                result.add(ncc);
            }
        }
        return result;
    }

    public ArrayList<NhaCungCap> searchTenNCC(String text) {
        ArrayList<NhaCungCap> result = new ArrayList<>();
        ArrayList<NhaCungCap> armt = NhaCungCapDao.getInstance().selectAll();
        for (var ncc : armt) {
            if (ncc.getTenNhaCungCap().toLowerCase().contains(text.toLowerCase())) {
                result.add(ncc);
            }
        }
        return result;
    }

    public ArrayList<NhaCungCap> searchMaNCC(String text) {
        ArrayList<NhaCungCap> result = new ArrayList<>();
        ArrayList<NhaCungCap> armt = NhaCungCapDao.getInstance().selectAll();
        for (var ncc : armt) {
            if (ncc.getMaNhaCungCapCode().toLowerCase().contains(text.toLowerCase())) {
                result.add(ncc);
            }
        }
        return result;
    }

    public ArrayList<NhaCungCap> searchEmail(String text) {
       ArrayList<NhaCungCap> result = new ArrayList<>();
       ArrayList<NhaCungCap> armt = NhaCungCapDao.getInstance().selectAll();
        for (var ncc : armt) {
          if (ncc.getEmail().toLowerCase().contains(text.toLowerCase())) {
                result.add(ncc);
          }
       }
        return result;
    }

    public ArrayList<NhaCungCap> searchSdt(String text) {
        ArrayList<NhaCungCap> result = new ArrayList<>();
        ArrayList<NhaCungCap> armt = NhaCungCapDao.getInstance().selectAll();
        for (var ncc : armt) {
            if (ncc.getSoDienThoai().toLowerCase().contains(text.toLowerCase())) {
                result.add(ncc);
            }
        }
        return result;
    }
    
      public ArrayList<NhaCungCap> searchNguoiLienHe(String text) {
        ArrayList<NhaCungCap> result = new ArrayList<>();
        ArrayList<NhaCungCap> armt = NhaCungCapDao.getInstance().selectAll();
        for (var ncc : armt) {
            if (ncc.getNguoiLienHe().toLowerCase().contains(text.toLowerCase())) {
                result.add(ncc);
            }
        }
        return result;
    }
}
