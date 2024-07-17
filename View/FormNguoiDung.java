/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package View;

import Controller.SearchNguoiDung;
import Controller.SearchNhaCungCap;
import Dao.NguoiDungDao;
import Dao.NhaCungCapDao;
import Model.NguoiDung;
import Model.NhaCungCap;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TieuSoi
 */
public class FormNguoiDung extends javax.swing.JInternalFrame {

    private DefaultTableModel tblModel;
    private static ArrayList<NguoiDung> armt;

    /**
     * Creates new form FormNguoiDung
     */
    public FormNguoiDung() {
        initComponents();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        tblNguoiDung.setDefaultEditor(Object.class, null);
        initTable();
        armt = NguoiDungDao.getInstance().selectAll();
        loadDataToTable(armt);
    }

    public final void initTable() {
        tblModel = new DefaultTableModel();
        String[] headerTbl = new String[] { "Mã ND", "Tên Đăng Nhập", "Mật Khẩu", "Tên Người Dùng", "Email", "Quyền" };
        tblModel.setColumnIdentifiers(headerTbl);
        tblNguoiDung.setModel(tblModel);
        tblNguoiDung.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblNguoiDung.getColumnModel().getColumn(1).setPreferredWidth(300);
        tblNguoiDung.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblNguoiDung.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblNguoiDung.getColumnModel().getColumn(4).setPreferredWidth(200);
        tblNguoiDung.getColumnModel().getColumn(5).setPreferredWidth(200);
    }

    public void loadDataToTable(ArrayList<NguoiDung> ncc) {
        try {
            tblModel.setRowCount(0);
            for (NguoiDung i : ncc) {
                tblModel.addRow(new Object[] {
                        i.getMaNguoiDungCode(), i.getTenDangNhap(), i.getMatKhau(), i.getHoTen(), i.getEmail(),
                        i.getQuyen()
                });
            }
        } catch (Exception e) {
        }
    }

    public NguoiDung getNguoiDungSelect() {
        int i_row = tblNguoiDung.getSelectedRow();
        NguoiDung nd = NguoiDungDao.getInstance().selectAll().get(i_row);
        return nd;
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
        jToolBar1 = new javax.swing.JToolBar();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton6 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        cbxlLuaChon = new javax.swing.JComboBox<>();
        txtSearchForm = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNguoiDung = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));
        jToolBar1.setRollover(true);

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/add_icon_24.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.setFocusable(false);
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setMaximumSize(new java.awt.Dimension(65, 55));
        btnAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAdd);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/error_icon_24.png"))); // NOI18N
        btnDelete.setText("Xoá");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setMaximumSize(new java.awt.Dimension(65, 55));
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDelete);

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/write_icon_24.png"))); // NOI18N
        btnEdit.setText("Sửa");
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.setFocusable(false);
        btnEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEdit.setMaximumSize(new java.awt.Dimension(65, 55));
        btnEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEdit);
        jToolBar1.add(jSeparator1);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/excel_icon_24.png"))); // NOI18N
        jButton6.setText("Xuất Excel");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setMaximumSize(new java.awt.Dimension(65, 55));
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_export_excel.png"))); // NOI18N
        jButton2.setText("Nhập Excel");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setMaximumSize(new java.awt.Dimension(65, 55));
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxlLuaChon.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[] { "Tất cả", "Mã Người Dùng", "Họ Tên", "Tên Đăng Nhập", "Email", "Quyền" }));
        cbxlLuaChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxlLuaChonActionPerformed(evt);
            }
        });
        cbxlLuaChon.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbxlLuaChonPropertyChange(evt);
            }
        });
        jPanel3.add(cbxlLuaChon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 150, 40));

        txtSearchForm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchFormKeyPressed(evt);
            }

            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchFormKeyReleased(evt);
            }
        });
        jPanel3.add(txtSearchForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 360, 40));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_refesh.png"))); // NOI18N
        jButton7.setText("Làm mới");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 140, 40));

        tblNguoiDung.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Mã ND", "Tên Đăng Nhập", "Mật Khẩu", "Tên Người Dùng", "Email", "Quyền"
                }));
        jScrollPane1.setViewportView(tblNguoiDung);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 433,
                                                        Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 722,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1))
                                .addContainerGap()));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 99,
                                                Short.MAX_VALUE)
                                        .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 626,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(25, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1197, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 765, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAddActionPerformed

        AddNguoiDung a = new AddNguoiDung(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this),
                rootPaneCheckingEnabled);
        a.setVisible(true);
    }// GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if (tblNguoiDung.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn người dùng bạn muốn xoá");
        } else {
            NguoiDungDao.getInstance().delete(getNguoiDungSelect());
            JOptionPane.showMessageDialog(this, "Xóa thành công !");
            loadDataToTable(NguoiDungDao.getInstance().selectAll());
        }
    }// GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        if (tblNguoiDung.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn người dùng cần sửa");
        } else {
            UpdateNguoiDung a = new UpdateNguoiDung(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this),
                    rootPaneCheckingEnabled);
            a.setVisible(true);
        }
    }// GEN-LAST:event_btnEditActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:

    }// GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

    }// GEN-LAST:event_jButton2ActionPerformed

    private void cbxlLuaChonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbxlLuaChonActionPerformed
        // TODO add your handling code here:

    }// GEN-LAST:event_cbxlLuaChonActionPerformed

    private void cbxlLuaChonPropertyChange(java.beans.PropertyChangeEvent evt) {// GEN-FIRST:event_cbxlLuaChonPropertyChange
        // TODO add your handling code here:

    }// GEN-LAST:event_cbxlLuaChonPropertyChange

    private void txtSearchFormKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtSearchFormKeyPressed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtSearchFormKeyPressed

    private void txtSearchFormKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtSearchFormKeyReleased
        // TODO add your handling code here:
        // TODO add your handling code here:
        String luachon = (String) cbxlLuaChon.getSelectedItem();
        String searchContent = txtSearchForm.getText();
        ArrayList<NguoiDung> result = new ArrayList<>();
        switch (luachon) {
            case "Tất cả":
                result = SearchNguoiDung.getInstance().searchTatCa(searchContent);
                break;
            case "Mã Người Dùng":
                result = SearchNguoiDung.getInstance().searchMaND(searchContent);
                break;
            case "Họ Tên":
                result = SearchNguoiDung.getInstance().searchTenND(searchContent);
                break;
            case "Tên Đăng Nhập":
                result = SearchNguoiDung.getInstance().searchTenDN(searchContent);
                break;
            case "Email":
                result = SearchNguoiDung.getInstance().searchEmail(searchContent);
                break;
            case "Quyền":
                result = SearchNguoiDung.getInstance().searchQuyen(searchContent);
                break;
        }
        loadDataToTable(result);
    }// GEN-LAST:event_txtSearchFormKeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        txtSearchForm.setText("");
        cbxlLuaChon.setSelectedIndex(0);
        loadDataToTable(NguoiDungDao.getInstance().selectAll());
    }// GEN-LAST:event_jButton7ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JComboBox<String> cbxlLuaChon;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblNguoiDung;
    private javax.swing.JTextField txtSearchForm;
    // End of variables declaration//GEN-END:variables
}