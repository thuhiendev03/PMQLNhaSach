/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package View;

import Controller.ConvertDate;
import Controller.QuanLyThongKe;
import Controller.SearchLoHang;
import Controller.SearchNguoiDung;
import Controller.SearchSanPham;
import Dao.LoHangDao;
import Dao.NguoiDungDao;
import Dao.NhapKhoDao;
import Dao.SanPhamDao;
import Database.DatabaseManager;
import Model.LoHang;
import Model.NguoiDung;
import Model.NhapKho;
import Model.SanPham;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TieuSoi
 */
public class FromThongKe extends javax.swing.JInternalFrame {

    DefaultTableModel tblModel = new DefaultTableModel();
    DefaultTableModel tblModel1 = new DefaultTableModel();
    DefaultTableModel tblModel2 = new DefaultTableModel();
    private static ArrayList<NguoiDung> armtND;
    private static ArrayList<LoHang> armtNhap;
    private static ArrayList<LoHang> armtBan;
    private JDateChooser toDateLoHang;
    private JDateChooser toDateLoHang1;
    private JLabel slNhap;
    private JLabel slBan;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    
    

    /**
     * Creates new form FromThongKe
     */
    public FromThongKe() {
        initComponents();
        initTableND();
        armtNhap = LoHangDao.getInstance().selectAllWithQuantitiesNhap();
        loadDataToTableNhapHang(armtNhap);
        armtBan = LoHangDao.getInstance().selectAllWithQuantitiesBan();
        loadDataToTableBanHang(armtBan);
        armtND = (ArrayList<NguoiDung>) NguoiDungDao.getInstance().getUserOrderCounts();
        loadDataToTableND(armtND);
        //===========
        tblModel1.addTableModelListener(e -> refreshTableDataNhap());

        // Gọi refreshTableData() khi khởi tạo để cập nhật nhãn
        refreshTableDataNhap();
        tblModel2.addTableModelListener(e -> refreshTableDataBan());

        // Gọi refreshTableData() khi khởi tạo để cập nhật nhãn
        refreshTableDataBan();

    }
    
    
    
    
    public final void initTableND() {
        tblModel = new DefaultTableModel();
        String[] headerTblND = new String[] { "Họ và tên", "Email", "Tài khoản", "Vai trò", "Số Lượng Đơn Hàng" };
        tblModel.setColumnIdentifiers(headerTblND);
        tblAccount.setModel(tblModel);
        tblAccount.getColumnModel().getColumn(0).setPreferredWidth(150);
        tblAccount.getColumnModel().getColumn(1).setPreferredWidth(300);
        tblAccount.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblAccount.getColumnModel().getColumn(3).setPreferredWidth(70);
        tblAccount.getColumnModel().getColumn(4).setPreferredWidth(150);
        // ======================================================================
        tblModel1 = new DefaultTableModel();
        String[] headerTblSP = new String[] { "Mã", "Tên sản phẩm", "SL Nhập", "Ngày nhập" };
        tblModel1.setColumnIdentifiers(headerTblSP);
        tblThongKeProduct.setModel(tblModel1);
        tblThongKeProduct.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblThongKeProduct.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblThongKeProduct.getColumnModel().getColumn(2).setPreferredWidth(70);
        tblThongKeProduct.getColumnModel().getColumn(3).setPreferredWidth(70);
        // ======================================================================
        String[] headerTblPN = new String[] { "Mã", "Tên sản phẩm", "SL Bán", "Ngày bán","Thành tiền" };
        tblModel2.setColumnIdentifiers(headerTblPN);
        tblBanHang.setModel(tblModel2);
        tblBanHang.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblBanHang.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblBanHang.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblBanHang.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblBanHang.getColumnModel().getColumn(4).setPreferredWidth(100);
        //=================
        //refreshTableData();
    }

    public void loadDataToTableND(ArrayList<NguoiDung> nd) {
        try {
            tblModel.setRowCount(0);
            for (NguoiDung i : nd) {
                tblModel.addRow(new Object[] {
                        i.getHoTen(), i.getEmail(), i.getTenDangNhap(), i.getQuyen(), i.getSoLuongDonHang()
                });
            }

        } catch (Exception e) {
        } finally {
            try {
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void loadDataToTableNhapHang(ArrayList<LoHang> sp) {
        try {
            tblModel1.setRowCount(0);
            for (LoHang i : sp)
            {
                String formattedDateNhap;
                formattedDateNhap = ConvertDate.getInstance().changeFromToString(i.getNgayNhap());
                tblModel1.addRow(new Object[] {
                        i.getMaLoHangCode(), i.getTenSanPham(), i.getSoLuongNhap(), formattedDateNhap
                });
            }
        } catch (Exception e) {
        }

    }

    public void loadDataToTableBanHang(ArrayList<LoHang> nk) {
        try {
            tblModel2.setRowCount(0);
            for (LoHang i : nk) {
                String formattedDateBan;
                formattedDateBan = ConvertDate.getInstance().changeFromToString(i.getNgayBan());
                tblModel2.addRow(new Object[] {
                        i.getMaLoHangCode(), i.getTenSanPham(), i.getSoLuongBan(), formattedDateBan, i.getThanhTien()
                });
            }
        } catch (ParseException e) {
        }

    }
    //=================================================Lọc==================================================
       //===Lọc trước, tìm sau
    public void filterProductsByDateNhap() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String startDate = "";
        String endDate = "";
        String searchText = txtSearchSP.getText();

        if (ToDateLoHang1.getDate() != null) {
            startDate = dateFormat.format(ToDateLoHang1.getDate());
        }

        if (ToDateLoHang.getDate() != null) {
            endDate = dateFormat.format(ToDateLoHang.getDate());
        }

       if (!startDate.isEmpty() && !endDate.isEmpty()) {
            armtNhap = (ArrayList<LoHang>) LoHangDao.getInstance().filterProductsByDate(startDate, endDate);
        } else {
            armtNhap = (ArrayList<LoHang>) LoHangDao.getInstance().selectAllWithQuantitiesNhap();
        }

        if (!searchText.isEmpty()&& !startDate.isEmpty() && !endDate.isEmpty()) {
            armtNhap = (ArrayList<LoHang>) LoHangDao.getInstance().filterProductsByDateAndSearchNhap(startDate, endDate,searchText);
        }

        loadDataToTableNhapHang(armtNhap);
    }
        //Tìm trước, lọc sau
    public void filterProductsBySearchAndDateNhap() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String searchText = txtSearchSP.getText();
        String startDate = "";
        String endDate = "";

        if (ToDateLoHang1.getDate() != null) {
            startDate = dateFormat.format(ToDateLoHang1.getDate());
        }

        if (ToDateLoHang.getDate() != null) {
            endDate = dateFormat.format(ToDateLoHang.getDate());
        }

        if (!searchText.isEmpty()) {
            List<LoHang> searchedProducts = LoHangDao.getInstance().searchProductNhap(searchText);
            if (!startDate.isEmpty() && !endDate.isEmpty()) {
                try {
                    // Kiểm tra giá trị ngày tháng hợp lệ
                    dateFormat.parse(startDate);
                    dateFormat.parse(endDate);
                    armtNhap = (ArrayList<LoHang>) LoHangDao.getInstance().filterAfterAndSearchNhap(searchedProducts, startDate, endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi định dạng ngày tháng. Vui lòng nhập ngày tháng hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                armtNhap = (ArrayList<LoHang>) searchedProducts;
            }
        } else {
            if (!startDate.isEmpty() && !endDate.isEmpty()) {
                try {
                    // Kiểm tra giá trị ngày tháng hợp lệ
                    dateFormat.parse(startDate);
                    dateFormat.parse(endDate);
                    armtNhap = (ArrayList<LoHang>) LoHangDao.getInstance().filterAfterAndSearchNhap(LoHangDao.getInstance().selectAllWithQuantitiesNhap(), startDate, endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi định dạng ngày tháng. Vui lòng nhập ngày tháng hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                armtNhap = (ArrayList<LoHang>) LoHangDao.getInstance().selectAllWithQuantitiesNhap();
            }
        }

        loadDataToTableNhapHang(armtNhap);
    }
    //============
    public void filterProductsByDateBan() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String startDate = "";
        String endDate = "";
        String searchText = txtSearchSPBan.getText();

        if (ToDateLoHang3.getDate() != null) {
            startDate = dateFormat.format(ToDateLoHang3.getDate());
        }

        if (ToDateLoHang2.getDate() != null) {
            endDate = dateFormat.format(ToDateLoHang2.getDate());
        }

       if (!startDate.isEmpty() && !endDate.isEmpty()) {
            armtBan = (ArrayList<LoHang>) LoHangDao.getInstance().filterProductsByDate(startDate, endDate);
        } else {
            armtBan = (ArrayList<LoHang>) LoHangDao.getInstance().selectAllWithQuantitiesBan();
        }

        if (!searchText.isEmpty()&& !startDate.isEmpty() && !endDate.isEmpty()) {
            armtBan = (ArrayList<LoHang>) LoHangDao.getInstance().filterProductsByDateAndSearchBan(startDate, endDate,searchText);
        }

        loadDataToTableBanHang(armtBan);
    }
        //Tìm trước, lọc sau
    public void filterProductsBySearchAndDateBan() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String searchText = txtSearchSPBan.getText();
        String startDate = "";
        String endDate = "";

        if (ToDateLoHang3.getDate() != null) {
            startDate = dateFormat.format(ToDateLoHang3.getDate());
        }

        if (ToDateLoHang2.getDate() != null) {
            endDate = dateFormat.format(ToDateLoHang2.getDate());
        }

        if (!searchText.isEmpty()) {
            List<LoHang> searchedProducts = LoHangDao.getInstance().searchProductBan(searchText);
            if (!startDate.isEmpty() && !endDate.isEmpty()) {
                try {
                    // Kiểm tra giá trị ngày tháng hợp lệ
                    dateFormat.parse(startDate);
                    dateFormat.parse(endDate);
                    armtBan = (ArrayList<LoHang>) LoHangDao.getInstance().filterAfterAndSearchBan(searchedProducts, startDate, endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi định dạng ngày tháng. Vui lòng nhập ngày tháng hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                armtBan = (ArrayList<LoHang>) searchedProducts;
            }
        } else {
            if (!startDate.isEmpty() && !endDate.isEmpty()) {
                try {
                    // Kiểm tra giá trị ngày tháng hợp lệ
                    dateFormat.parse(startDate);
                    dateFormat.parse(endDate);
                    armtBan = (ArrayList<LoHang>) LoHangDao.getInstance().filterAfterAndSearchBan(LoHangDao.getInstance().selectAllWithQuantitiesBan(), startDate, endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi định dạng ngày tháng. Vui lòng nhập ngày tháng hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                armtBan = (ArrayList<LoHang>) LoHangDao.getInstance().selectAllWithQuantitiesBan();
            }
        }

        loadDataToTableBanHang(armtBan);
    }

    //==============================================================
    public int getTotalNhap(JTable table) {
        int totalNhap = 0;
        for (int i = 0; i < table.getRowCount(); i++) {
            totalNhap += Integer.parseInt(table.getValueAt(i, 2).toString());
        }
        return totalNhap;
    }

    public int getTotalBan(JTable table) {
        int totalBan = 0;
        for (int i = 0; i < table.getRowCount(); i++) {
            totalBan += Integer.parseInt(table.getValueAt(i, 2).toString());
        }
        return totalBan;
    }
    public int getTotalTongTien(JTable table) {
        int total = 0;
        for (int i = 0; i < table.getRowCount(); i++) {
            total += Double.parseDouble(table.getValueAt(i, 4).toString());
        }
        return total;
    }

    public void updateLabelsFromTableNhap(JTable table) {
        int totalNhap = getTotalNhap(table);
        TongNhap.setText(""+totalNhap+" Sản phẩm");
    }
    public void updateLabelsFromTableBan(JTable table) {
        int totalBan = getTotalBan(table);
        TongBan.setText( ""+totalBan+" Sản phẩm");
        
        double total = getTotalTongTien(table);
        labelTongTien.setText("" + formatter.format(total) + "đ");
    }
    

    public void refreshTableDataNhap() {
        updateLabelsFromTableNhap(tblThongKeProduct);
        
    }
    public void refreshTableDataBan() {
        updateLabelsFromTableBan(tblBanHang);     
    }
    //=========================================================================
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        txtSearchSP = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblThongKeProduct = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnResetThongKePr = new javax.swing.JButton();
        ToDateLoHang = new com.toedter.calendar.JDateChooser();
        ToDateLoHang1 = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        TongNhap = new javax.swing.JLabel();
        btnTKNhapHang = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        txtSearchSPBan = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblBanHang = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnResetThongKePrBan = new javax.swing.JButton();
        ToDateLoHang2 = new com.toedter.calendar.JDateChooser();
        ToDateLoHang3 = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        TongBan = new javax.swing.JLabel();
        btnTKBanHang1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        labelTongTien = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        cbxLuaChonTK = new javax.swing.JComboBox<>();
        txtSearchTK = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAccount = new javax.swing.JTable();
        btnXemChartNV = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1200, 765));

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1180, 770));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtSearchSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchSPActionPerformed(evt);
            }
        });
        txtSearchSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchSPKeyReleased(evt);
            }
        });
        jPanel16.add(txtSearchSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 350, 40));

        tblThongKeProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        tblThongKeProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongKeProductMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblThongKeProduct);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc theo ngày"));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Đến");
        jPanel13.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 40, 20));

        jLabel8.setText("Từ");
        jPanel13.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 20, 20));

        btnResetThongKePr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_refesh.png"))); // NOI18N
        btnResetThongKePr.setText("Làm mới");
        btnResetThongKePr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetThongKePrMouseClicked(evt);
            }
        });
        btnResetThongKePr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetThongKePrActionPerformed(evt);
            }
        });
        jPanel13.add(btnResetThongKePr, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 140, 40));

        ToDateLoHang.setBackground(new java.awt.Color(255, 255, 255));
        ToDateLoHang.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ToDateLoHangPropertyChange(evt);
            }
        });
        ToDateLoHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ToDateLoHangKeyReleased(evt);
            }
        });
        jPanel13.add(ToDateLoHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 170, -1));

        ToDateLoHang1.setBackground(new java.awt.Color(255, 255, 255));
        ToDateLoHang1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ToDateLoHang1PropertyChange(evt);
            }
        });
        ToDateLoHang1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ToDateLoHang1KeyReleased(evt);
            }
        });
        jPanel13.add(ToDateLoHang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 170, -1));

        jLabel9.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel9.setText("Tổng nhập:");

        TongNhap.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        TongNhap.setForeground(new java.awt.Color(255, 0, 51));
        TongNhap.setText("0");

        btnTKNhapHang.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnTKNhapHang.setText("Xem chi tiết");
        btnTKNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKNhapHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TongNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128)
                .addComponent(btnTKNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1198, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TongNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTKNhapHang))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jPanel13.getAccessibleContext().setAccessibleName("Lọc theo số lương");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(518, 518, 518))
        );

        jTabbedPane1.addTab("Nhập hàng", jPanel12);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtSearchSPBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchSPBanActionPerformed(evt);
            }
        });
        txtSearchSPBan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchSPBanKeyReleased(evt);
            }
        });
        jPanel18.add(txtSearchSPBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 350, 40));

        tblBanHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        tblBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBanHangMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblBanHang);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc theo ngày"));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setText("Đến");
        jPanel14.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 40, 20));

        jLabel12.setText("Từ");
        jPanel14.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 20, 20));

        btnResetThongKePrBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_refesh.png"))); // NOI18N
        btnResetThongKePrBan.setText("Làm mới");
        btnResetThongKePrBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetThongKePrBanMouseClicked(evt);
            }
        });
        btnResetThongKePrBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetThongKePrBanActionPerformed(evt);
            }
        });
        jPanel14.add(btnResetThongKePrBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 140, 40));

        ToDateLoHang2.setBackground(new java.awt.Color(255, 255, 255));
        ToDateLoHang2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ToDateLoHang2PropertyChange(evt);
            }
        });
        ToDateLoHang2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ToDateLoHang2KeyReleased(evt);
            }
        });
        jPanel14.add(ToDateLoHang2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 170, -1));

        ToDateLoHang3.setBackground(new java.awt.Color(255, 255, 255));
        ToDateLoHang3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ToDateLoHang3PropertyChange(evt);
            }
        });
        ToDateLoHang3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ToDateLoHang3KeyReleased(evt);
            }
        });
        jPanel14.add(ToDateLoHang3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 170, -1));

        jLabel14.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel14.setText("Tổng bán:");

        TongBan.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        TongBan.setForeground(new java.awt.Color(255, 0, 51));
        TongBan.setText("0");

        btnTKBanHang1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnTKBanHang1.setText("Xem chi tiết");
        btnTKBanHang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKBanHang1ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel15.setText("Tổng tiền:");

        labelTongTien.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        labelTongTien.setForeground(new java.awt.Color(255, 0, 51));
        labelTongTien.setText("0");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(TongBan, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148)
                .addComponent(btnTKBanHang1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(167, 167, 167)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(labelTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TongBan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTKBanHang1))
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(45, 45, 45))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(607, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Bán hàng", jPanel1);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxLuaChonTK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Họ và tên", "Tên người dùng" }));
        cbxLuaChonTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxLuaChonTKActionPerformed(evt);
            }
        });
        cbxLuaChonTK.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbxLuaChonTKPropertyChange(evt);
            }
        });
        jPanel7.add(cbxLuaChonTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 210, 40));

        txtSearchTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchTKActionPerformed(evt);
            }
        });
        txtSearchTK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchTKKeyReleased(evt);
            }
        });
        jPanel7.add(txtSearchTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 320, 40));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_refesh.png"))); // NOI18N
        jButton1.setText("Làm mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, 160, 40));

        tblAccount.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Họ và tên", "Email", "Tên người dùng", "Vai trò", "Tình trạng"
            }
        ));
        tblAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAccountMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblAccount);

        btnXemChartNV.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnXemChartNV.setText("Xem chi tiết");
        btnXemChartNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXemChartNVMouseClicked(evt);
            }
        });
        btnXemChartNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemChartNVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 1163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(460, 460, 460)
                .addComponent(btnXemChartNV, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXemChartNV)
                .addContainerGap(146, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(510, 510, 510))
        );

        jTabbedPane1.addTab("Tài khoản", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1216, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 109, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ToDateLoHangPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ToDateLoHangPropertyChange
        try {
            // TODO add your handling code here:
            filterProductsByDateNhap();
        } catch (ParseException ex) {
            Logger.getLogger(FromThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ToDateLoHangPropertyChange

    private void ToDateLoHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ToDateLoHangKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_ToDateLoHangKeyReleased

    private void ToDateLoHang1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ToDateLoHang1PropertyChange
        try {
            // TODO add your handling code here:
            filterProductsByDateNhap();
        } catch (ParseException ex) {
            Logger.getLogger(FromThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ToDateLoHang1PropertyChange

    private void ToDateLoHang1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ToDateLoHang1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ToDateLoHang1KeyReleased

    private void btnResetThongKePrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetThongKePrMouseClicked
        // TODO add your handling code here:
        loadDataToTableNhapHang(armtNhap);
    }//GEN-LAST:event_btnResetThongKePrMouseClicked

    private void btnTKNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKNhapHangActionPerformed
                // TODO add your handling code here:
        ThongKeNhapHangChart chartPanel = new ThongKeNhapHangChart();
        try {
            chartPanel.setChart();
        } catch (ParseException ex) {
            Logger.getLogger(FromThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    // Tạo một JFrame mới để chứa JPanel
        JFrame frame = new JFrame("Biểu đồ nhập hàng");

        // Thêm JPanel vào JFrame
        frame.getContentPane().add(chartPanel, BorderLayout.CENTER);

        // Đặt kích thước của JFrame
        frame.setSize(1300, 800); // Thay đổi kích thước theo nhu cầu của bạn

        // Đặt JFrame hiển thị ở trung tâm màn hình
        frame.setLocationRelativeTo(null);

        // Hiển thị JFrame
        frame.setVisible(true);
                
    }//GEN-LAST:event_btnTKNhapHangActionPerformed

    private void txtSearchSPBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchSPBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchSPBanActionPerformed

    private void txtSearchSPBanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchSPBanKeyReleased
        // TODO add your handling code here:
        String searchContent = txtSearchSPBan.getText();
        ArrayList<LoHang> result = new ArrayList<>();
        result = SearchLoHang.getInstance().searchLoHangSP(searchContent);
        loadDataToTableBanHang(result);
        filterProductsBySearchAndDateBan();
    }//GEN-LAST:event_txtSearchSPBanKeyReleased

    private void tblBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBanHangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblBanHangMouseClicked

    private void btnResetThongKePrBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetThongKePrBanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnResetThongKePrBanMouseClicked

    private void btnResetThongKePrBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetThongKePrBanActionPerformed
        // TODO add your handling code here:
        ToDateLoHang2.setDate(null);
        ToDateLoHang3.setDate(null);
        txtSearchSPBan.setText("");
        loadDataToTableNhapHang(LoHangDao.getInstance().selectAllWithQuantitiesBan());
    }//GEN-LAST:event_btnResetThongKePrBanActionPerformed

    private void ToDateLoHang2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ToDateLoHang2PropertyChange
        // TODO add your handling code here:
       try {
            // TODO add your handling code here:
            filterProductsByDateBan();
        } catch (ParseException ex) {
            Logger.getLogger(FromThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ToDateLoHang2PropertyChange

    private void ToDateLoHang2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ToDateLoHang2KeyReleased
        // TODO add your handling code here:
//        try {
//            // TODO add your handling code here:
//            filterProductsByDateBan();
//        } catch (ParseException ex) {
//            Logger.getLogger(FromThongKe.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_ToDateLoHang2KeyReleased

    private void ToDateLoHang3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ToDateLoHang3PropertyChange
        try {
            // TODO add your handling code here:
            filterProductsByDateBan();
        } catch (ParseException ex) {
            Logger.getLogger(FromThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ToDateLoHang3PropertyChange

    private void ToDateLoHang3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ToDateLoHang3KeyReleased
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            filterProductsByDateBan();
        } catch (ParseException ex) {
            Logger.getLogger(FromThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ToDateLoHang3KeyReleased

    private void btnTKBanHang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKBanHang1ActionPerformed
        // TODO add your handling code here:
         ThongKeBanHangChart chartPanel = new ThongKeBanHangChart();
        try {
            chartPanel.setChart();
        } catch (ParseException ex) {
            Logger.getLogger(FromThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    // Tạo một JFrame mới để chứa JPanel
        JFrame frame = new JFrame("Biểu đồ bán hàng");

        // Thêm JPanel vào JFrame
        frame.getContentPane().add(chartPanel, BorderLayout.CENTER);

        // Đặt kích thước của JFrame
        frame.setSize(1300, 800); // Thay đổi kích thước theo nhu cầu của bạn

        // Đặt JFrame hiển thị ở trung tâm màn hình
        frame.setLocationRelativeTo(null);

        // Hiển thị JFrame
        frame.setVisible(true);
    }//GEN-LAST:event_btnTKBanHang1ActionPerformed

    private void btnXemChartNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXemChartNVMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnXemChartNVMouseClicked

    private void btnXemChartNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChartNVActionPerformed
        // TODO add your handling code here:
         ThongKeKPINhanVien chartPanel = new ThongKeKPINhanVien();
        try {
            chartPanel.setChart();
        } catch (ParseException ex) {
            Logger.getLogger(FromThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    // Tạo một JFrame mới để chứa JPanel
        JFrame frame = new JFrame("Biểu đồ KPI Nhân viên");

        // Thêm JPanel vào JFrame
        frame.getContentPane().add(chartPanel, BorderLayout.CENTER);

        // Đặt kích thước của JFrame
        frame.setSize(1300, 800); // Thay đổi kích thước theo nhu cầu của bạn

        // Đặt JFrame hiển thị ở trung tâm màn hình
        frame.setLocationRelativeTo(null);

        // Hiển thị JFrame
        frame.setVisible(true);
    }//GEN-LAST:event_btnXemChartNVActionPerformed

    private void txtSearchSPKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtSearchSPKeyReleased
        String searchContent = txtSearchSP.getText();
        ArrayList<LoHang> result = new ArrayList<>();
        result = SearchLoHang.getInstance().searchLoHangSPBan(searchContent);
        loadDataToTableNhapHang(result);
        filterProductsBySearchAndDateNhap();
    }// GEN-LAST:event_txtSearchSPKeyReleased

    private void tblThongKeProductMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblThongKeProductMouseClicked
        // TODO add your handling code here:
    }// GEN-LAST:event_tblThongKeProductMouseClicked

    private void btnResetThongKePrActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnResetThongKePrActionPerformed
        // TODO add your handling code here:
        ToDateLoHang.setDate(null);
        ToDateLoHang1.setDate(null);
        txtSearchSP.setText("");
        loadDataToTableNhapHang(LoHangDao.getInstance().selectAllWithQuantitiesNhap());

    }// GEN-LAST:event_btnResetThongKePrActionPerformed

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDetailActionPerformed
        // TODO add your handling code here:

    }// GEN-LAST:event_btnDetailActionPerformed

    private void jComboBoxLuaChonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBoxLuaChonActionPerformed

    }// GEN-LAST:event_jComboBoxLuaChonActionPerformed

    private void jComboBoxLuaChonPropertyChange(java.beans.PropertyChangeEvent evt) {// GEN-FIRST:event_jComboBoxLuaChonPropertyChange

    }// GEN-LAST:event_jComboBoxLuaChonPropertyChange

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jTextFieldSearchKeyReleased

    }// GEN-LAST:event_jTextFieldSearchKeyReleased
// GEN-LAST:event_jTextFieldSearchKeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
//        jComboBoxLuaChon.setSelectedIndex(0);
//        jTextFieldSearch.setText("");
//        jDateChooserFrom.setCalendar(null);
//        jDateChooserTo.setCalendar(null);
//        giaDen.setText("");
//        giaTu.setText("");
    }

    private void jDateChooserFromKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jDateChooserFromKeyReleased

    }// GEN-LAST:event_jDateChooserFromKeyReleased

    private void jDateChooserToPropertyChange(java.beans.PropertyChangeEvent evt) {// GEN-FIRST:event_jDateChooserToPropertyChange

    }// GEN-LAST:event_jDateChooserToPropertyChange

    private void jDateChooserToKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jDateChooserToKeyReleased

    }// GEN-LAST:event_jDateChooserToKeyReleased

    private void giaTuActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_giaTuActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_giaTuActionPerformed

    private void giaTuKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_giaTuKeyReleased

    }// GEN-LAST:event_giaTuKeyReleased

    private void giaDenActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_giaDenActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_giaDenActionPerformed

    private void giaDenKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_giaDenKeyReleased

    }// GEN-LAST:event_giaDenKeyReleased

    private void cbxLuaChonTKActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbxLuaChonTKActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_cbxLuaChonTKActionPerformed

    private void cbxLuaChonTKPropertyChange(java.beans.PropertyChangeEvent evt) {// GEN-FIRST:event_cbxLuaChonTKPropertyChange
        // TODO add your handling code here:
    }// GEN-LAST:event_cbxLuaChonTKPropertyChange

    private void txtSearchTKKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtSearchTKKeyReleased
        // TODO add your handling code here:
        String luachon = (String) cbxLuaChonTK.getSelectedItem();
        String searchContent = txtSearchTK.getText();
        ArrayList<NguoiDung> result = new ArrayList<>();
        switch (luachon) {
            case "Tất cả":
                result = SearchNguoiDung.getInstance().searchTatCa(searchContent);
                break;
            case "Họ và tên":
                result = SearchNguoiDung.getInstance().searchTenND(searchContent);
                break;
            case "Tên người dùng":
                result = SearchNguoiDung.getInstance().searchTenDN(searchContent);
                break;
        }
        loadDataToTableND(result);
    }// GEN-LAST:event_txtSearchTKKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        cbxLuaChonTK.setSelectedIndex(0);
        txtSearchTK.setText("");
        loadDataToTableND(NguoiDungDao.getInstance().selectAll());
    }// GEN-LAST:event_jButton1ActionPerformed

    private void tblAccountMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblAccountMouseClicked
        // TODO add your handling code here:

    }// GEN-LAST:event_tblAccountMouseClicked

    private void txtSearchTKActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtSearchTKActionPerformed
        // TODO add your handling code here:

    }// GEN-LAST:event_txtSearchTKActionPerformed

    private void txtSearchSPActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtSearchSPActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtSearchSPActionPerformed

    private void txtLocDenActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtLocDenActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtLocDenActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser ToDateLoHang;
    private com.toedter.calendar.JDateChooser ToDateLoHang1;
    private com.toedter.calendar.JDateChooser ToDateLoHang2;
    private com.toedter.calendar.JDateChooser ToDateLoHang3;
    private javax.swing.JLabel TongBan;
    private javax.swing.JLabel TongNhap;
    private javax.swing.JButton btnResetThongKePr;
    private javax.swing.JButton btnResetThongKePrBan;
    private javax.swing.JButton btnTKBanHang1;
    private javax.swing.JButton btnTKNhapHang;
    private javax.swing.JButton btnXemChartNV;
    private javax.swing.JComboBox<String> cbxLuaChonTK;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelTongTien;
    private javax.swing.JTable tblAccount;
    private javax.swing.JTable tblBanHang;
    private javax.swing.JTable tblThongKeProduct;
    private javax.swing.JTextField txtSearchSP;
    private javax.swing.JTextField txtSearchSPBan;
    private javax.swing.JTextField txtSearchTK;
    // End of variables declaration//GEN-END:variables
}
