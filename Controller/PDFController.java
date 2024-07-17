/*
 * Licensed under the default license template available at:
 * nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * To modify this template, visit:
 * nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java
 */
package Controller;

import Dao.BanHangDao;
import Dao.ChiTietBanHangDao;
import Dao.KhachHangDao;
import Dao.LoHangDao;
import Dao.NhapKhoDao;
import Model.*;
import Model.ChiTietBanHang;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import java.awt.Desktop;
import java.awt.FileDialog;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * PDFController class handles the creation of PDF documents for invoices and
 * receipts. This class uses iText library to generate PDFs.
 *
 * Note: Ensure the required fonts and iText library are available in the
 * classpath.
 *
 * Usage: PDFController pdfController = new PDFController();
 * pdfController.writePhieuXuat(banHang);
 *
 * @autor TieuSoi
 */
public class PDFController {

    DecimalFormat formatter = new DecimalFormat("###,###,###");
    SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY HH:mm");
    Document document = new Document();
    FileOutputStream file;
    JFrame jf = new JFrame();
    FileDialog fd = new FileDialog(jf, "Xuất pdf", FileDialog.SAVE);
    Font fontData;
    Font fontTitle;
    Font fontHeader;

    public PDFController() {
        try {
            fontData = new Font(BaseFont.createFont("lib/Roboto/Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 11, Font.NORMAL);
            fontTitle = new Font(BaseFont.createFont("lib/Roboto/Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 25, Font.NORMAL);
            fontHeader = new Font(BaseFont.createFont("lib/Roboto/Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 11, Font.BOLD);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    public void chooseURL(String url) {
        try {
            document.close();
            document = new Document();
            file = new FileOutputStream(url);
            PdfWriter.getInstance(document, file);
            document.open();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy đường dẫn file " + url);
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Không mở được document!");
        }
    }

    public void setTitle(String title) {
        try {
            Paragraph pdfTitle = new Paragraph(new Phrase(title, fontTitle));
            pdfTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(pdfTitle);
            document.add(Chunk.NEWLINE);
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
    }

    private String getFile(String name) {
        fd.pack();
        fd.setSize(800, 600);
        fd.validate();
        Rectangle rect = jf.getContentPane().getBounds();
        double width = fd.getBounds().getWidth();
        double height = fd.getBounds().getHeight();
        double x = rect.getCenterX() - (width / 2);
        double y = rect.getCenterY() - (height / 2);
        Point leftCorner = new Point();
        leftCorner.setLocation(x, y);
        fd.setLocation(leftCorner);
        fd.setFile(name + ".pdf");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("null")) {
            return null;
        }
        return url;
    }

    public void writePhieuXuat(BanHang banHang) {
        String url = "";
        try {
            fd.setTitle("IN HÓA ĐƠN");
            fd.setLocationRelativeTo(null);
            url = getFile(banHang.getMaDonHangCode());
            if (url == null) {
                return;
            }
            String mapn = String.valueOf(banHang.getMaDonHang());
            file = new FileOutputStream(url);
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();

            // Logo and company information (adjust positioning as needed)
            String logoPath = "D:\\DATA\\2023-2024\\ky-2\\JAVA\\QuanLyNhaSach\\src\\Assets\\Logo.png";
            Image logo = Image.getInstance(logoPath);
            logo.setAlignment(Element.ALIGN_CENTER);
            logo.scaleToFit(200, 200); // Adjust size as needed
            document.add(logo);

            // Title and document information
            setTitle("HOÁ ĐƠN BÁN HÀNG");
            Paragraph info = new Paragraph();
            info.setFont(fontData);
            info.add("Mã hóa đơn: " + banHang.getMaDonHangCode() + "\n");
            info.add("Ngày bán: " + banHang.getNgayBan() + "\n");
            info.add("Khách hàng: " + KhachHangDao.getInstance().selectById(banHang.getMaKH()).getHoTenKH() + "\n");
            document.add(info);

            // Table for invoice details
            PdfPTable table = new PdfPTable(new float[]{1, 3, 1, 1, 2});
            table.setWidthPercentage(100);
            table.setSpacingBefore(20f);
            table.setSpacingAfter(20f);

            // Headers
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Mã SP", fontHeader));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Tên SP", fontHeader));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Đơn giá", fontHeader));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("SL", fontHeader));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Thành tiền", fontHeader));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            // Data rows
            double totalAmount = 0;
            for (ChiTietBanHang ctpn : ChiTietBanHangDao.getInstance().selectAllDetailByIdDH(mapn)) {
                table.addCell(ctpn.getMaLoHangCode());
                table.addCell(ctpn.getTenSanPham());
                table.addCell(formatter.format(ctpn.getGiaBan()) + "đ");
                table.addCell(String.valueOf(ctpn.getSoLuong()));
                table.addCell(formatter.format(ctpn.getTongTien()) + "đ");
                totalAmount += ctpn.getTongTien();
            }

            document.add(table);

            // Total amount
            Paragraph total = new Paragraph();
            total.setIndentationLeft(400);
            total.setFont(fontData);
            total.add("Tổng cộng: " + formatter.format(totalAmount) + "đ");
            document.add(total);

            // Footer (e.g., signature lines)
            Paragraph footer = new Paragraph();
            footer.setSpacingBefore(50f); // Space from last content
            footer.setFont(fontData);
            footer.setAlignment(Element.ALIGN_CENTER);
            footer.add("\n\n\n");
            footer.add("Người lập phiếu\n\n\n\n");
            footer.add(UserController.getInstance().getHoTen());
            document.add(footer);

            document.close();
            JOptionPane.showMessageDialog(null, "Hóa đơn đã được lưu thành công: " + url);
            openFile(url);

        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi lưu hóa đơn: " + ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc file logo: " + ex.getMessage());
        }
    }

    private void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Không thể mở file: " + e.getMessage());
        }
    }

    public void writePhieuNhap(String mapn) {
        String url = "";
        try {
            // Initialize PDF document and file output
            fd.setTitle("In phiếu nhập");
            fd.setLocationRelativeTo(null);
            url = getFile(mapn);
            if (url == null) {
                return;
            }
            file = new FileOutputStream(url);
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();

            // Set title for the PDF document
            setTitle("THÔNG TIN PHIẾU NHẬP");

            // Retrieve purchase order details
            NhapKho pn = NhapKhoDao.getInstance().selectById(mapn);

            // Create paragraphs for general information
            Paragraph para1 = createParagraph("Mã phiếu: " + pn.getMaNhapKhoCode() + "\nThời gian tạo: " + pn.getNgayNhap(), 40);
            Paragraph para2 = createParagraph("Người tạo: " + pn.getTenNguoiDung() + "\nNhà cung cấp: " + pn.getTenNhaCungCap(), 40);

            // Add paragraphs to the document
            document.add(para1);
            document.add(para2);
            document.add(Chunk.NEWLINE);

            // Create table for item details
            PdfPTable pdfTable = createItemDetailsTable();

            // Populate table with item details
            double sum = populateItemDetails(pdfTable, pn);

            // Add table to the document
            document.add(pdfTable);
            document.add(Chunk.NEWLINE);

//<<<<<<< Updated upstream
//            Paragraph paraTongThanhToan = new Paragraph(new Phrase("Tổng thanh toán: " + formatter.format(sum )+ "đ", fontData));
//=======
            // Add total payment information
            Paragraph paraTongThanhToan = new Paragraph("Tổng thanh toán: " + formatter.format(sum) + "đ", fontData);
//>>>>>>> Stashed changes
            paraTongThanhToan.setIndentationLeft(300);
            document.add(paraTongThanhToan);

            // Finalize document and display success message
            document.close();
            JOptionPane.showMessageDialog(null, "Ghi file thành công " + url);
            openFile(url);

        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi ghi file " + url);
            ex.printStackTrace();
        }
    }

    private Paragraph createParagraph(String content, float indentationLeft) {
        Paragraph paragraph = new Paragraph();
        paragraph.setFont(fontData);
        paragraph.add(content);
        paragraph.setIndentationLeft(indentationLeft);
        return paragraph;
    }

    private PdfPTable createItemDetailsTable() throws DocumentException {
        PdfPTable pdfTable = new PdfPTable(6);
        pdfTable.setWidths(new float[]{15f, 30f, 15f, 10f, 15f, 15f});
        pdfTable.addCell(new PdfPCell(new Phrase("Mã chi tiết phiếu nhập", fontHeader)));
        pdfTable.addCell(new PdfPCell(new Phrase("Tên mặt hàng", fontHeader)));
        pdfTable.addCell(new PdfPCell(new Phrase("Ngày nhập", fontHeader)));
        pdfTable.addCell(new PdfPCell(new Phrase("Số lượng", fontHeader)));
        pdfTable.addCell(new PdfPCell(new Phrase("Giá nhập", fontHeader)));
        pdfTable.addCell(new PdfPCell(new Phrase("Giá bán", fontHeader)));
        return pdfTable;
    }

    private double populateItemDetails(PdfPTable pdfTable, NhapKho pn) {
        double sum = 0;
        for (LoHang ctpn : LoHangDao.getInstance().selectAllWithChiTietPhieuNhap(pn.getMaNhapKho())) {
            pdfTable.addCell(new PdfPCell(new Phrase(ctpn.getMaLoHangCode(), fontData)));
            pdfTable.addCell(new PdfPCell(new Phrase(ctpn.getTenSanPham(), fontData)));
            pdfTable.addCell(new PdfPCell(new Phrase(ctpn.getNgayNhap(), fontData)));
            pdfTable.addCell(new PdfPCell(new Phrase(String.valueOf(ctpn.getSoLuong()), fontData)));
            pdfTable.addCell(new PdfPCell(new Phrase(ctpn.getGiaBanNhap() + "đ", fontData)));
            pdfTable.addCell(new PdfPCell(new Phrase(ctpn.getGiaBan() + "đ", fontData)));
            sum += ctpn.getSoLuong() * ctpn.getGiaBanNhap();
        }
        return sum;
    }

}
