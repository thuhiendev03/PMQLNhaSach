/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.NhanVienKPI;
import Model.SanPhamStatistical;
import java.util.List;

/**
 *
 * @author Nguyen Thi Thu Hien
 */
public class ThongKeServiceSP implements ThongKeService{

    
    private ThongKeInterface thongKeInterface = null;
    
    public ThongKeServiceSP()
    {
        thongKeInterface = new ThongKeDao();
    }
    @Override
    public List<SanPhamStatistical> getListBySanPham() {
        return thongKeInterface.getListBySanPham();
    }
    
    @Override
    public List<SanPhamStatistical> getListBySanPhamNhap() {
        return thongKeInterface.getListBySanPhamNhap();
    }
    
    @Override
    public List<NhanVienKPI> getListByNhanVien() {
        return thongKeInterface.getListByNhanVien();
    }
    
    
}
