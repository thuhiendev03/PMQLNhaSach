/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.NguoiDungDao;
import java.util.ArrayList;
import Model.NguoiDung;
/**
 *
 * @author TieuSoi
 */

public class SearchNguoiDung {

    public static SearchNguoiDung getInstance() {
        return new SearchNguoiDung();
    }

    public ArrayList<NguoiDung> searchTatCa(String text) {
        ArrayList<NguoiDung> result = new ArrayList<>();
        ArrayList<NguoiDung> armt = NguoiDungDao.getInstance().selectAll();
        for (var nd : armt) {
            if (String.valueOf(nd.getMaNguoiDung()).contains(text.toLowerCase())
                    || nd.getMaNguoiDungCode().toLowerCase().contains(text.toLowerCase())
                    || nd.getHoTen().toLowerCase().contains(text.toLowerCase())
                    || nd.getTenDangNhap().toLowerCase().contains(text.toLowerCase())
                    || nd.getQuyen().toLowerCase().contains(text.toLowerCase())
                    || nd.getEmail().toLowerCase().contains(text.toLowerCase())) {
                result.add(nd);
            }
        }
        return result;
    }

    public ArrayList<NguoiDung> searchTenND(String text) {
        ArrayList<NguoiDung> result = new ArrayList<>();
        ArrayList<NguoiDung> armt = NguoiDungDao.getInstance().selectAll();
        for (var nd : armt) {
            if (nd.getHoTen().toLowerCase().contains(text.toLowerCase())) {
                result.add(nd);
            }
        }
        return result;
    }

    public ArrayList<NguoiDung> searchMaND(String text) {
        ArrayList<NguoiDung> result = new ArrayList<>();
        ArrayList<NguoiDung> armt = NguoiDungDao.getInstance().selectAll();
        for (var nd : armt) {
            if (nd.getMaNguoiDungCode().toLowerCase().contains(text.toLowerCase())) {
                result.add(nd);
            }
        }
        return result;
    }

    public ArrayList<NguoiDung> searchEmail(String text) {
       ArrayList<NguoiDung> result = new ArrayList<>();
       ArrayList<NguoiDung> armt = NguoiDungDao.getInstance().selectAll();
        for (var nd : armt) {
          if (nd.getEmail().toLowerCase().contains(text.toLowerCase())) {
                result.add(nd);
          }
       }
        return result;
    }

    public ArrayList<NguoiDung> searchTenDN(String text) {
        ArrayList<NguoiDung> result = new ArrayList<>();
        ArrayList<NguoiDung> armt = NguoiDungDao.getInstance().selectAll();
        for (var nd : armt) {
            if (nd.getTenDangNhap().toLowerCase().contains(text.toLowerCase())) {
                result.add(nd);
            }
        }
        return result;
    }
    
      public ArrayList<NguoiDung> searchQuyen(String text) {
        ArrayList<NguoiDung> result = new ArrayList<>();
        ArrayList<NguoiDung> armt = NguoiDungDao.getInstance().selectAll();
        for (var nd : armt) {
            if (nd.getQuyen().toLowerCase().contains(text.toLowerCase())) {
                result.add(nd);
            }
        }
        return result;
    }
}
