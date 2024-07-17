/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.LoHangDao;
import Dao.SanPhamDao;
import java.text.ParseException;
import Model.LoHang;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Model.SanPham;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;


/**
 *
 * @author TieuSoi
 */
public class SearchLoHang {
    public static SearchLoHang getInstance() {
        return new SearchLoHang();
    }
     public ArrayList<LoHang> lstDanhSachVanPhongPham() {
        ArrayList<LoHang> result = new ArrayList<>();
        ArrayList<LoHang> armt = LoHangDao.getInstance().selectAll();
        String text = "Văn Phòng Phẩm";
        for (var ncc : armt) {
            if (ncc.getDanhMuc().toLowerCase().contains(text.toLowerCase())) {
                result.add(ncc);
            }
        }
        return result;
    }
    public ArrayList<LoHang> searchNXB(String text) {
        ArrayList<LoHang> result = new ArrayList<>();
        ArrayList<LoHang> armt = LoHangDao.getInstance().selectAll();
        for (var nd : armt) {
            if (nd.getNhaXuatBan().toLowerCase().contains(text.toLowerCase())) {
                result.add(nd);
            }
        }
        return result;
    }
    public ArrayList<LoHang> searchTenSP(String text) {
        ArrayList<LoHang> result = new ArrayList<>();
        ArrayList<LoHang> armt = LoHangDao.getInstance().selectAll();
        for (var nd : armt) {
            if (nd.getTenSanPham().toLowerCase().contains(text.toLowerCase())) {
                result.add(nd);
            }
        }
        return result;
    }
    public ArrayList<LoHang> searchDanhMuc(String text) {
        ArrayList<LoHang> result = new ArrayList<>();
        ArrayList<LoHang> armt = LoHangDao.getInstance().selectAll();
        for (var nd : armt) {
            if (nd.getDanhMuc().toLowerCase().contains(text.toLowerCase())) {
                result.add(nd);
            }
        }
        return result;
    }
    
     public ArrayList<LoHang> searchByMaLoHangCode(String text) {
        ArrayList<LoHang> result = new ArrayList<>();
        ArrayList<LoHang> armt = LoHangDao.getInstance().selectAll();
        for (var nd : armt) {
            if (nd.getMaLoHangCode().toLowerCase().contains(text.toLowerCase())
                   ) {
                result.add(nd);
            }
        }
        return result;
    }
    
    public LoHang searchByMaLoHangCodeItem(String text) {
        LoHang result = null;
        ArrayList<LoHang> armt = LoHangDao.getInstance().selectAll();
        for (var nd : armt) {
            if (nd.getMaLoHangCode().toLowerCase().contains(text.toLowerCase())
                   ) {
                result = nd;
            }
        }
        return result;
    }
     
     public ArrayList<LoHang> searchTatCaLoHang(String text) {
        ArrayList<LoHang> result = new ArrayList<>();
        ArrayList<LoHang> armt = LoHangDao.getInstance().selectAll();
        for (var ncc : armt) {
            if (ncc.getTenSanPham().toLowerCase().contains(text.toLowerCase())
                    || ncc.getMaLoHangCode().toLowerCase().contains(text.toLowerCase())
                    || ncc.getDanhMuc().toLowerCase().contains(text.toLowerCase())
                   ) {
                result.add(ncc);
            }
        }
        return result;
    }
     
    public ArrayList<LoHang> searchGiaNhap(Double gia1, Double gia2) {
        ArrayList<LoHang> result = new ArrayList<>();
        ArrayList<LoHang> armt = LoHangDao.getInstance().selectAll();

        for (var loHang : armt) {
            // So sánh giá bán nhập của sản phẩm với giá tối thiểu (gia1) và giá tối đa (gia2)
            if (loHang.getGiaBanNhap() >= gia1 && loHang.getGiaBanNhap() <= gia2) {
                result.add(loHang);
            }
        }
        return result;
    }

    public ArrayList<LoHang> searchDate(Date date1, Date date2) {
            ArrayList<LoHang> result = new ArrayList<>();
            ArrayList<LoHang> armt = LoHangDao.getInstance().selectAll();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            for (var nd : armt) {
                try {
                    String dt = nd.getNgayNhap();
                    Date ngayNhap = dateFormat.parse(dt);

                    if ((ngayNhap.equals(date1) || ngayNhap.after(date1)) && 
                        (ngayNhap.equals(date2) || ngayNhap.before(date2))) {
                        result.add(nd);
                    }
                } catch (ParseException e) {
                    e.printStackTrace(); // Handle exception as needed
                }
            }
            return result;
    }
     
    public ArrayList<LoHang> lsDanhSachSach() {
        ArrayList<LoHang> result = new ArrayList<>();
        ArrayList<LoHang> armt = LoHangDao.getInstance().selectAll();
        String text = "Sách";
        for (var ncc : armt) {
            if (ncc.getDanhMuc().toLowerCase().contains(text.toLowerCase())) {
                result.add(ncc);
            }
        }
        return result;
    }
    public ArrayList<LoHang> searchLoHangSP(String text) {
        ArrayList<LoHang> result = new ArrayList<>();
        ArrayList<LoHang> armt = LoHangDao.getInstance().selectAllWithQuantitiesNhap();
        for (var ncc : armt) {
            if (ncc.getTenSanPham().toLowerCase().contains(text.toLowerCase())
                    || ncc.getMaLoHangCode().toLowerCase().contains(text.toLowerCase())
                   ) {
                result.add(ncc);
            }
        }
        return result;
    }
    
    public ArrayList<LoHang> searchLoHangSPBan(String text) {
        ArrayList<LoHang> result = new ArrayList<>();
        ArrayList<LoHang> armt = LoHangDao.getInstance().selectAllWithQuantitiesBan();
        for (var ncc : armt) {
            if (ncc.getTenSanPham().toLowerCase().contains(text.toLowerCase())
                    || ncc.getMaLoHangCode().toLowerCase().contains(text.toLowerCase())
                   ) {
                result.add(ncc);
            }
        }
        return result;
    }
}
