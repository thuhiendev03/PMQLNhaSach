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
public interface ThongKeInterface {
    
    public List<SanPhamStatistical> getListBySanPham();
    
    public List<SanPhamStatistical> getListBySanPhamNhap();
    
    public List<NhanVienKPI> getListByNhanVien();
    
}
