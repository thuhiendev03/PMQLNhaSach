/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.SanPhamDao;
import Model.SanPham;
import java.util.ArrayList;

/**
 *
 * @author Nguyen Thi Thu Hien
 */
public class SearchSanPham {
    public static SearchSanPham getInstance() {
        return new SearchSanPham();
    }
    public ArrayList<SanPham> searchTatCa(String text) {
        ArrayList<SanPham> result = new ArrayList<>();
        ArrayList<SanPham> armt = SanPhamDao.getInstance().selectAll();
        String lowerCaseText = text.toLowerCase();

        for (SanPham sanPham : armt) {
            if (String.valueOf(sanPham.getMaSanPham()).contains(lowerCaseText)
                    || (sanPham.getMaSanPhamCode() != null && sanPham.getMaSanPhamCode().toLowerCase().contains(lowerCaseText))
                    || (sanPham.getTenSanPham() != null && sanPham.getTenSanPham().toLowerCase().contains(lowerCaseText))
                    || (sanPham.getDanhMuc() != null && sanPham.getDanhMuc().toLowerCase().contains(lowerCaseText))
                    || (sanPham.getTacGia() != null && sanPham.getTacGia().toLowerCase().contains(lowerCaseText))
                    || (sanPham.getNhaXuatBan() != null && sanPham.getNhaXuatBan().toLowerCase().contains(lowerCaseText))
                    || String.valueOf(sanPham.getGiaBanNhap()).contains(lowerCaseText)
                    || (sanPham.getTenNhaCungCap()).contains(lowerCaseText)) {
                result.add(sanPham);
            }
        }
        return result;
    }
    public ArrayList<SanPham> searchTenSP(String text) {
        ArrayList<SanPham> result = new ArrayList<>();
        ArrayList<SanPham> armt = SanPhamDao.getInstance().selectAll();
        for (var nd : armt) {
            if (nd.getTenSanPham().toLowerCase().contains(text.toLowerCase())) {
                result.add(nd);
            }
        }
        return result;
    }

    public ArrayList<SanPham> searchMaSP(String text) {
        ArrayList<SanPham> result = new ArrayList<>();
        ArrayList<SanPham> armt = SanPhamDao.getInstance().selectAll();
        for (var nd : armt) {
            if (nd.getMaSanPhamCode().toLowerCase().contains(text.toLowerCase())) {
                result.add(nd);
            }
        }
        return result;
    }
    
      public SanPham searchMaSPCode(String text) {
        SanPham result = null;
        ArrayList<SanPham> armt = SanPhamDao.getInstance().selectAll();
        for (var nd : armt) {
            if (nd.getMaSanPhamCode().toLowerCase().contains(text.toLowerCase())) {
                result = nd;
            }
        }
        return result;
    }

    public ArrayList<SanPham> searchDanhMuc(String text) {
        ArrayList<SanPham> result = new ArrayList<>();
        ArrayList<SanPham> armt = SanPhamDao.getInstance().selectAll();
        for (var nd : armt) {
            if (nd.getDanhMuc().toLowerCase().contains(text.toLowerCase())) {
                result.add(nd);
            }
        }
        return result;
    }

    public ArrayList<SanPham> searchTacGia(String text) {
        ArrayList<SanPham> result = new ArrayList<>();
        ArrayList<SanPham> armt = SanPhamDao.getInstance().selectAll();
        for (var nd : armt) {
            String tenTG = nd.getTacGia();
            if(tenTG != null)
                if (nd.getTacGia().toLowerCase().contains(text.toLowerCase())) {
                    result.add(nd);
                }
        }
        return result;
    }

    public ArrayList<SanPham> searchNXB(String text) {
        ArrayList<SanPham> result = new ArrayList<>();
        ArrayList<SanPham> armt = SanPhamDao.getInstance().selectAll();
        for (var nd : armt) {
            String nhaXuatBan = nd.getNhaXuatBan();
            if(nhaXuatBan != null)
                if (nd.getNhaXuatBan().toLowerCase().contains(text.toLowerCase())) {
                    result.add(nd);
                }
        }
        return result;
    }

    public ArrayList<SanPham> searchGiaBanNhap(String text) {
        ArrayList<SanPham> result = new ArrayList<>();
        ArrayList<SanPham> armt = SanPhamDao.getInstance().selectAll();
        for (var nd : armt) {
            if (String.valueOf(nd.getGiaBanNhap()).contains(text.toLowerCase())) {
                result.add(nd);
            }
        }
        return result;
    }
    
    public ArrayList<SanPham> searchNCC(String text) {
        ArrayList<SanPham> result = new ArrayList<>();
        ArrayList<SanPham> armt = SanPhamDao.getInstance().selectAll();
        for (var nd : armt) {
            if (nd.getTenNhaCungCap().toLowerCase().contains(text.toLowerCase())) {
                result.add(nd);
            }
        }
        return result;
    }
    
    public ArrayList<SanPham> searchNCCandALL(String text, String keyword) {
        ArrayList<SanPham> result = new ArrayList<>();
        ArrayList<SanPham> armt = SanPhamDao.getInstance().selectAll();
        for (var nd : armt) {
            if (nd.getTenNhaCungCap().toLowerCase().contains(text.toLowerCase())
                    && (nd.getDanhMuc().toLowerCase().contains(keyword.toLowerCase())
                    || nd.getMaSanPhamCode().toLowerCase().contains(keyword.toLowerCase())
                    || nd.getMaSanPhamCode().toLowerCase().contains(keyword.toLowerCase())
                    || nd.getTenSanPham().toLowerCase().contains(keyword.toLowerCase()))
                    ) {
                result.add(nd);
            }
        }
        return result;
    }
}
