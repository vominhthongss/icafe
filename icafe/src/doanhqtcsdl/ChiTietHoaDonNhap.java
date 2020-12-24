//CÓ 3 CÂU SQL
package doanhqtcsdl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Siu_Shock
 */
public class ChiTietHoaDonNhap extends javax.swing.JFrame
{

    /**
     * Creates new form ChiTietHoaDonNhap
     */
    Connection conn;
    Statement stmt = null;
    ResultSet rs = null;
    
    String tenNguoiGhi;
    String maHoaDonGhi;
    String ngayGhi;
    String tongTienGhi;

    //
    DefaultTableModel dtm;
    public ChiTietHoaDonNhap(String tenNguoiGhi,String maHoaDonGhi,String ngayGhi,String tongTienGhi, Connection conn)
    {
        initComponents();
        
        this.tenNguoiGhi=tenNguoiGhi;
        this.maHoaDonGhi = maHoaDonGhi;
        this.ngayGhi=ngayGhi;
        this.tongTienGhi=tongTienGhi;
        this.conn=conn;
        
        
        txtTenNguoiGhi.setText(tenNguoiGhi);
        txtMaHoaDonGhi.setText(maHoaDonGhi);
        txtNgayGhi.setText(ngayGhi);
        txtTongTienGhi.setText(tongTienGhi);
        //
        dtm = new DefaultTableModel();
        tbChiTietHoaDonNhap.setModel(dtm);
        dtm.addColumn("Tên nguyên liệu");
        dtm.addColumn("Số lượng");
        dtm.addColumn("Đơn vị");
        dtm.addColumn("Đơn giá");
        dtm.addColumn("Thành tiền");
        
        layChiTietHoaDon();
        layTrangThaiHoaDon();
        if(txtThanhToan.getText().equals("Đã thanh toán"))
        {
            btnThanhToan.setVisible(false);
        }
        
    }
    
    public ChiTietHoaDonNhap()
    {
        initComponents();
    }
    public void layChiTietHoaDon()
    {
        //String sql="SELECT * FROM chitiethoadonnhap WHERE mahoadonnhap='"+maHoaDonGhi+"' "; 
        String sql="CALL LAY_CHI_TIET_HOA_DON_NHAP('"+maHoaDonGhi+"') ";
        try 
        {     
            stmt = conn.createStatement();  
            rs = stmt.executeQuery(sql); 
            if (stmt.execute(sql)) 
            {         
                rs = stmt.getResultSet();     
            }
        //thao tác trên tập kết quả trả về rs.... 
            while (rs.next()) 
            {   
                dtm.addRow(new Object [] {rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getInt(6)});

            }
        }
 
        catch (SQLException ex)
        {    
            System.out.println("SQLException: " + ex.getMessage()); 
        } 
        finally 
        {     //giải phóng tài nguyên khi không sử dụng nữa     
            if (rs != null) 
            {         
                try 
                {             
                    rs.close();         
                } 
                catch (SQLException sqlEx){} 
                rs = null;
            }
            if (stmt != null) 
            {         
                try 
                {             
                    stmt.close();         
                } 
                catch (SQLException sqlEx){}  
                stmt = null;     
            }
        }
    }
    public void layTrangThaiHoaDon()
    {
        //String sql="SELECT thanhtoan FROM hoadonnhap WHERE mahoadonnhap='"+maHoaDonGhi+"' "; 
        String sql = "CALL LAY_TRANG_THAI_HOA_DON_NHAP ('"+maHoaDonGhi+"') ";
        try 
        {     
            stmt = conn.createStatement();  
            rs = stmt.executeQuery(sql); 
            if (stmt.execute(sql)) 
            {         
                rs = stmt.getResultSet();     
            }
        //thao tác trên tập kết quả trả về rs.... 
            while (rs.next()) 
            {   
                if(rs.getInt(1)==1)
                {
                    txtThanhToan.setText("Đã thanh toán");
                }
                else
                {
                    txtThanhToan.setText("Chưa thanh toán");
                }

            }
        }
 
        catch (SQLException ex)
        {    
            System.out.println("SQLException: " + ex.getMessage()); 
        } 
        finally 
        {     //giải phóng tài nguyên khi không sử dụng nữa     
            if (rs != null) 
            {         
                try 
                {             
                    rs.close();         
                } 
                catch (SQLException sqlEx){} 
                rs = null;
            }
            if (stmt != null) 
            {         
                try 
                {             
                    stmt.close();         
                } 
                catch (SQLException sqlEx){}  
                stmt = null;     
            }
        }
    }
    public void capNhatTrangThaiHoaDon()
    {
        //câu lệnh chèn data
        //String sql ="UPDATE hoadonnhap SET thanhtoan=1 WHERE mahoadonnhap='"+maHoaDonGhi+"' ";
        String sql = "CALL CAP_NHAT_TRANG_THAI_HOA_DON_NHAP('"+maHoaDonGhi+"') ";      
        
        try 
        {     
            stmt =conn.createStatement();  
            stmt.execute(sql);

        } 
        catch (SQLException ex)
        {    
            System.out.println("Không thể cập nhật trạng thái hóa đơn nhập !"); 
        } 
        finally 
        {     //giải phóng tài nguyên khi không sử dụng nữa     

            if (stmt != null) 
            {         
                try 
                {             
                    stmt.close();         
                } 
                catch (SQLException sqlEx){}  
                stmt = null;     
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbChiTietHoaDonNhap = new javax.swing.JTable();
        btnThanhToan = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtThanhToan = new javax.swing.JLabel();
        txtTongTienGhi = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaHoaDonGhi = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNgayGhi = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTenNguoiGhi = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setLocation(new java.awt.Point(350, 50));
        setMaximumSize(new java.awt.Dimension(636, 650));
        setMinimumSize(new java.awt.Dimension(636, 650));
        setResizable(false);

        tbChiTietHoaDonNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String []
            {
                "Tên nguyên liệu", "Số lượng", "Đơn vị", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane1.setViewportView(tbChiTietHoaDonNhap);

        btnThanhToan.setText("XÁC NHẬN THANH TOÁN");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnThanhToanActionPerformed(evt);
            }
        });

        jLabel1.setText("Trạng thái:");

        txtThanhToan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtThanhToan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtThanhToan.setText(" ");

        txtTongTienGhi.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        txtTongTienGhi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTongTienGhi.setText("0");

        jPanel1.setPreferredSize(new java.awt.Dimension(200, 100));

        jLabel2.setText("Mã hóa đơn:");

        txtMaHoaDonGhi.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtMaHoaDonGhi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtMaHoaDonGhi.setText("...........................");

        jLabel3.setText("Ngày ghi:");

        txtNgayGhi.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtNgayGhi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtNgayGhi.setText("............................");

        jLabel4.setText("Người ghi");

        txtTenNguoiGhi.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtTenNguoiGhi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTenNguoiGhi.setText("...........................");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenNguoiGhi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaHoaDonGhi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNgayGhi, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaHoaDonGhi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNgayGhi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTenNguoiGhi))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("CHI TIẾT HÓA ĐƠN NHẬP");

        jLabel6.setText("VND");

        jLabel7.setText("TỔNG TIỀN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTongTienGhi, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThanhToan)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(26, 26, 26)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongTienGhi)
                    .addComponent(jLabel1)
                    .addComponent(txtThanhToan)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(32, 32, 32)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnThanhToanActionPerformed
    {//GEN-HEADEREND:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        capNhatTrangThaiHoaDon();
        JOptionPane.showMessageDialog(this,"Cập nhật xong","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        txtThanhToan.setText("Đã thanh toán");
        btnThanhToan.setVisible(false);
    }//GEN-LAST:event_btnThanhToanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(ChiTietHoaDonNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(ChiTietHoaDonNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(ChiTietHoaDonNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(ChiTietHoaDonNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new ChiTietHoaDonNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbChiTietHoaDonNhap;
    private javax.swing.JLabel txtMaHoaDonGhi;
    private javax.swing.JLabel txtNgayGhi;
    private javax.swing.JLabel txtTenNguoiGhi;
    private javax.swing.JLabel txtThanhToan;
    private javax.swing.JLabel txtTongTienGhi;
    // End of variables declaration//GEN-END:variables
}
