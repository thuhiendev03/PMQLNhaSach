/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package View;

import Controller.ConvertDate;
import Controller.PDFController;
import Controller.SanPhamDBController;
import Controller.SearchLoHang;
import Controller.SearchSanPham;
import Controller.UserController;
import Dao.LoHangDao;
import Dao.NhaCungCapDao;
import Dao.NhapKhoDao;
import Dao.SanPhamDao;
import Model.ChiTietBanHang;
import Model.LoHang;
import Model.NhaCungCap;
import Model.NhapKho;
import Model.SanPham;
import java.awt.HeadlessException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TieuSoi
 */
public class FormNhapHang extends javax.swing.JInternalFrame {

    /**
     * Creates new form FormNhapHang
     */
    private DefaultTableModel tblModel;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    private ArrayList<SanPham> allProduct;
    private String MaPhieu;
    private NhaCungCap nhaCungCap;
    private String previousSelection = null;
    private ArrayList<LoHang> CTPhieu;
    private static final ArrayList<NhaCungCap> arrNcc = NhaCungCapDao.getInstance().selectAll();

    public FormNhapHang() {
        initComponents();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

        allProduct = SanPhamDao.getInstance().selectAll();
        initTable();
        loadDataToTableProduct(allProduct);
        loadNccToComboBox();
        tblSanPham.setDefaultEditor(Object.class, null);
        tblNhapHang.setDefaultEditor(Object.class, null);
        this.setNguoiNhapHang(UserController.getInstance().getHoTen());
        // CTPhieu = new ArrayList<ChiTietPhieu>();
        int selectedIndex = 0;
        nhaCungCap = arrNcc.get(selectedIndex);
    }

    private void loadNccToComboBox() {
        for (NhaCungCap i : arrNcc) {
            cboNhaCungCap.addItem(i.getTenNhaCungCap());
        }
    }

    public final void initTable() {
        tblModel = new DefaultTableModel();
        String[] headerTbl = new String[] { "Mã SP", "Tên SP", "Danh Mục", "Giá Nhập" };
        tblModel.setColumnIdentifiers(headerTbl);
        tblSanPham.setModel(tblModel);
        tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(5);
        tblSanPham.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblSanPham.getColumnModel().getColumn(2).setPreferredWidth(5);
        tblNhapHang.getColumnModel().getColumn(0).setPreferredWidth(5);
        tblNhapHang.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblNhapHang.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblNhapHang.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblNhapHang.getColumnModel().getColumn(4).setPreferredWidth(50);
        tblNhapHang.getColumnModel().getColumn(5).setPreferredWidth(50);

    }

    private void loadDataToTableProduct(ArrayList<SanPham> arrProd) {
        try {
            tblModel.setRowCount(0);
            for (var i : arrProd) {
                tblModel.addRow(new Object[] {
                        i.getMaSanPhamCode(), i.getTenSanPham(), i.getDanhMuc(),
                        formatter.format(i.getGiaBanNhap()) + "đ"
                });
            }
        } catch (Exception e) {
        }
    }

    public LoHang findCTPhieu(String maSp) {
        if (CTPhieu != null) {
            for (var i : CTPhieu) {
                if (maSp.equals(i.getMaSanPhamCode())) {
                    return i;
                }
            }
        }
        return null;
    }

    public void loadDataToTableNhapHang() {
        double sum = 0;
        try {
            DefaultTableModel tblNhapHangmd = (DefaultTableModel) tblNhapHang.getModel();
            tblNhapHangmd.setRowCount(0);

            for (int i = 0; i < CTPhieu.size(); i++) {
                tblNhapHangmd.addRow(new Object[] {
                        i + 1, CTPhieu.get(i).getMaSanPhamCode(), CTPhieu.get(i).getTenSanPham(),
                        CTPhieu.get(i).getSoLuong(), formatter.format(CTPhieu.get(i).getGiaBan()) + "đ",
                        formatter.format(CTPhieu.get(i).getGiaBanNhap()) + "đ",
                        formatter.format(CTPhieu.get(i).getTongTienNhap()) + "đ"
                });
                sum += CTPhieu.get(i).getGiaBanNhap() * CTPhieu.get(i).getSoLuong();
            }
        } catch (Exception e) {
        }
        textTongTien.setText(formatter.format(sum) + "đ");
    }

    public double tinhTongTien() {
        double tt = 0;
        for (var i : CTPhieu) {
            tt += (i.getTongTienNhap());
        }
        return tt;
    }

    public void setNguoiNhapHang(String name) {
        txtNguoiTao.setText(name);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cboNhaCungCap = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtNguoiTao = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhapHang = new javax.swing.JTable();
        btnNhapHang = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        textTongTien = new javax.swing.JLabel();
        deleteProduct = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        resetPhieuNhap = new javax.swing.JButton();
        deleteProduct2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        addProduct = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnReset = new javax.swing.JButton();
        txtGiaBan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1200, 765));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Nhà cung cấp");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        cboNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNhaCungCapActionPerformed(evt);
            }
        });
        jPanel2.add(cboNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 390, 36));

        jLabel3.setText("Người tạo phiếu");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        txtNguoiTao.setEditable(false);
        jPanel2.add(txtNguoiTao, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 390, 36));

        tblNhapHang.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "STT", "Mã SP", "Tên SP", "Số lượng", "Giá Bán", "Giá Nhập", "Thành tiền"
                }));
        jScrollPane1.setViewportView(tblNhapHang);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 580, 460));

        btnNhapHang.setBackground(new java.awt.Color(23, 87, 183));
        btnNhapHang.setForeground(new java.awt.Color(255, 255, 255));
        btnNhapHang.setText("Nhập hàng");
        btnNhapHang.setBorder(null);
        btnNhapHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapHangActionPerformed(evt);
            }
        });
        jPanel2.add(btnNhapHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 690, 123, 37));

        jLabel5.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel5.setText("Tổng tiền:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 690, 120, 30));

        textTongTien.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        textTongTien.setForeground(new java.awt.Color(255, 0, 0));
        textTongTien.setText("0đ");
        jPanel2.add(textTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 690, 190, 30));

        deleteProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/error_icon_24.png"))); // NOI18N
        deleteProduct.setText("Xoá sản phẩm ");
        deleteProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProductActionPerformed(evt);
            }
        });
        jPanel2.add(deleteProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 610, 160, 40));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/write_icon_24.png"))); // NOI18N
        jButton1.setText("Sửa số lượng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 610, -1, 40));

        resetPhieuNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/remove_icon_24.png"))); // NOI18N
        resetPhieuNhap.setText("Reset Phiếu");
        resetPhieuNhap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        resetPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetPhieuNhapActionPerformed(evt);
            }
        });
        jPanel2.add(resetPhieuNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, 140, 40));

        deleteProduct2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/excel_icon_24.png"))); // NOI18N
        deleteProduct2.setText("Nhập excel");
        deleteProduct2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteProduct2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProduct2ActionPerformed(evt);
            }
        });
        jPanel2.add(deleteProduct2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 610, -1, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 620, 750));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null }
                },
                new String[] {
                        "Mã SP", "Tên SP", "Danh Mục", "Giá Nhập"
                }));
        jScrollPane2.setViewportView(tblSanPham);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Số lượng");

        txtSoLuong.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSoLuong.setText("1");

        addProduct.setBackground(new java.awt.Color(23, 87, 183));
        addProduct.setForeground(new java.awt.Color(255, 255, 255));
        addProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/add_icon_24.png"))); // NOI18N
        addProduct.setText("Thêm");
        addProduct.setBorder(null);
        addProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_refesh.png"))); // NOI18N
        btnReset.setText("Làm mới");
        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap(17, Short.MAX_VALUE)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 352,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 129,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)));
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)));

        txtGiaBan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGiaBan.setText("1");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Giá bán(%)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap(13, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout
                                                .createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 95,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 95,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(addProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 115,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))));
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 525,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel6))
                                        .addGroup(jPanel3Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel4)
                                                .addComponent(addProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(29, Short.MAX_VALUE)));

        jLabel6.getAccessibleContext().setAccessibleName("lbGiaBan");
        jLabel6.getAccessibleContext().setAccessibleDescription("");

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 750));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNhapHangActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnNhapHangActionPerformed
        // TODO add your handling code here:

        if (nhaCungCap != null && CTPhieu != null && !CTPhieu.isEmpty()) {
            int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn Nhập hàng ?", "Xác nhận nhập hàng",
                    JOptionPane.YES_NO_OPTION);
            Date ngayLap = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(ngayLap);
            System.err.println(formattedDate);
            ConvertDate converter = ConvertDate.getInstance();
            SanPhamDBController.getInstance().insertNhapHang(UserController.getInstance().getMaNguoiDung(), nhaCungCap.getMaNhaCungCap(), formattedDate, CTPhieu);
            NhapKho nhapKho = NhapKhoDao.getInstance().selectByAll(UserController.getInstance().getMaNguoiDung(), nhaCungCap.getMaNhaCungCap(), formattedDate);
            int selectedIndex = cboNhaCungCap.getSelectedIndex();
            nhaCungCap = null;
            CTPhieu.clear();
            loadDataToTableNhapHang();
            
            int res = JOptionPane.showConfirmDialog(this, "Bạn có muốn xuất file pdf ?");
            if (res == JOptionPane.YES_OPTION) {
                PDFController writepdf = new PDFController();
                writepdf.writePhieuNhap(nhapKho.getMaNhapKhoCode());
            }
        } else {
            JOptionPane.showConfirmDialog(this, "Vui lòng điền đầy đủ thông tin?" , "Cảnh báo",
                    JOptionPane.YES_NO_OPTION);
        }
    }// GEN-LAST:event_btnNhapHangActionPerformed

    private void deleteProductActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_deleteProductActionPerformed
        // TODO add your handling code here:
        int i_row = tblNhapHang.getSelectedRow();
        if (i_row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xoá khỏi bảng bán hàng !");
        } else {
            CTPhieu.remove(i_row);
            loadDataToTableNhapHang();
            textTongTien.setText(formatter.format(tinhTongTien()) + "đ");
        }
    }// GEN-LAST:event_deleteProductActionPerformed

    public void getNhaCungCapSelect(int soLuong, double discountPercent) {
        int i_row = tblNhapHang.getSelectedRow();
        NhaCungCap ncc = NhaCungCapDao.getInstance().selectAll().get(i_row);
        CTPhieu.get(i_row).setSoLuong(soLuong);
        double originalPrice = findCTPhieu(CTPhieu.get(i_row).getMaSanPhamCode()).getGiaBan();
        double discountedPrice = originalPrice * ((100 - discountPercent) / 100);
        CTPhieu.get(i_row).setTongTienNhap(soLuong * discountedPrice);
        loadDataToTableNhapHang();
        textTongTien.setText(formatter.format(tinhTongTien()) + "đ");
        loadDataToTableNhapHang();
        textTongTien.setText(formatter.format(tinhTongTien()) + "đ");
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int i_row = tblNhapHang.getSelectedRow();
        if (i_row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để sửa số lượng và giảm giá!");
        } else {
            UpdatePhieuNhap up = new UpdatePhieuNhap(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this),
                    rootPaneCheckingEnabled);
            up.setVisible(true);
        }
    }// GEN-LAST:event_jButton1ActionPerformed

    private void resetPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_resetPhieuNhapActionPerformed
        // TODO add your handling code here:
        if (CTPhieu != null && CTPhieu.isEmpty()) {
            int confirmed = JOptionPane.showConfirmDialog(null,
                    "Bạn có chắc chắn muốn reset phiếu nhập hàng không?", "Xác nhận",
                    JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                CTPhieu.clear();
                loadDataToTableNhapHang();
            }
        }
    }// GEN-LAST:event_resetPhieuNhapActionPerformed

    private void addProductActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addProductActionPerformed

        if (CTPhieu == null)
            CTPhieu = new ArrayList<>();
        int i_row = tblSanPham.getSelectedRow();
        if (i_row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xuất hàng !");
            return;
        }

        String maLoHangCode = tblSanPham.getValueAt(i_row, 0).toString();

        SanPham selectedProduct = SearchSanPham.getInstance().searchMaSPCode(maLoHangCode);

        int enteredQuantity;
        double enteredGiaBan;
        try {
            enteredQuantity = Integer.parseInt(txtSoLuong.getText().trim());
            if (enteredQuantity <= 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng lớn hơn 0");
                return;
            }

            enteredGiaBan = Integer.parseInt(txtGiaBan.getText().trim());
            if (enteredQuantity <= 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng lớn hơn 0");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng ở dạng số nguyên!");
            return;
        }

        LoHang existingProduct = findCTPhieu(selectedProduct.getMaSanPhamCode());
        if (existingProduct != null) {
            existingProduct.setSoLuong(existingProduct.getSoLuong() + enteredQuantity);
            existingProduct.setGiaBan(
                    (existingProduct.getGiaBanNhap() * (enteredGiaBan / 100)) + existingProduct.getGiaBanNhap());
            existingProduct.setTongTienNhap(existingProduct.getSoLuong() * selectedProduct.getGiaBanNhap());
        } else {
            LoHang newOrderDetail = new LoHang();
            newOrderDetail.setMaSanPhamCode(selectedProduct.getMaSanPhamCode());
            newOrderDetail.setMaSanPham(selectedProduct.getMaSanPham());
            newOrderDetail.setTenSanPham(selectedProduct.getTenSanPham());
            newOrderDetail.setGiaBanNhap(selectedProduct.getGiaBanNhap());
            newOrderDetail.setSoLuong(enteredQuantity);
            newOrderDetail.setGiaBan(
                    (selectedProduct.getGiaBanNhap() * (enteredGiaBan / 100)) + selectedProduct.getGiaBanNhap());
            newOrderDetail.setTongTienNhap(selectedProduct.getGiaBanNhap() * enteredQuantity);
            CTPhieu.add(newOrderDetail);
        }

        loadDataToTableNhapHang();
    }// GEN-LAST:event_addProductActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        String selectedValue = cboNhaCungCap.getSelectedItem().toString();
        String txtKeyWord = txtSearch.getText();
        allProduct = SearchSanPham.getInstance().searchNCCandALL(selectedValue, txtKeyWord);
        loadDataToTableProduct(allProduct);
    }// GEN-LAST:event_txtSearchKeyReleased

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        String selectedValue = cboNhaCungCap.getSelectedItem().toString();
        allProduct = SearchSanPham.getInstance().searchNCC(selectedValue);
        loadDataToTableProduct(allProduct);
    }// GEN-LAST:event_btnResetActionPerformed

    private void cboNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cboNhaCungCapActionPerformed
        // TODO add your handling code here:
        // Get the new selected value
        if (CTPhieu != null && !CTPhieu.isEmpty()) {

            String selectedValue = cboNhaCungCap.getSelectedItem().toString();
            // Get the selected index from the JComboBox
            int selectedIndex = cboNhaCungCap.getSelectedIndex();

            // Check if there is a change in selection
            if (previousSelection == null || !previousSelection.equals(selectedValue)) {
                // Show confirmation dialog
                int response = JOptionPane.showConfirmDialog(
                        null,
                        "Bạn có chắc chắn muốn thay đổi nhà cung cấp?\n"
                                + "Lưu ý khi thay đổi nhà cung cấp thì các sản phẩm nhập sẽ bị xóa !",
                        "Xác nhận thay đổi",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                // If user confirms the change
                if (response == JOptionPane.YES_OPTION) {
                    // Update the previous selection
                    previousSelection = selectedValue;

                    // Perform the necessary actions for the change
                    nhaCungCap = arrNcc.get(selectedIndex);
                    allProduct = SearchSanPham.getInstance().searchNCC(selectedValue);
                    loadDataToTableProduct(allProduct);

                    // Clear CTPhieu
                    CTPhieu.clear();
                    loadDataToTableNhapHang();
                }
            } else {
                // Revert to the previous selection
                cboNhaCungCap.removeActionListener(this::cboNhaCungCapActionPerformed);
                cboNhaCungCap.setSelectedItem(previousSelection);
                cboNhaCungCap.addActionListener(this::cboNhaCungCapActionPerformed);
            }
        }

    }// GEN-LAST:event_cboNhaCungCapActionPerformed

    private void deleteProduct2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_deleteProduct2ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_deleteProduct2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProduct;
    private javax.swing.JButton btnNhapHang;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<String> cboNhaCungCap;
    private javax.swing.JButton deleteProduct;
    private javax.swing.JButton deleteProduct2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton resetPhieuNhap;
    private javax.swing.JTable tblNhapHang;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JLabel textTongTien;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtNguoiTao;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
