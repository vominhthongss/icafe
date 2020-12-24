//CÓ 5 CÂU SQL
package doanhqtcsdl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Siu_Shock
 */
public class Kho extends javax.swing.JFrame
{

    //KetNoi ketNoi = new KetNoi();
    Connection conn;
    Statement stmt = null; 
    ResultSet rs = null; 
    
    String tenDangNhap;
    String hoTen;
    
    String tenNguyenLieu;
    
    DefaultTableModel dtm;
    public Kho(String tenDangNhap, String hoTen,Connection conn)
    {
        initComponents();  
        
        this.tenDangNhap=tenDangNhap;
        this.hoTen=hoTen;
        this.conn=conn;
        
        txtHoTen.setText(hoTen);
        txtTenDangNhap.setText(tenDangNhap);
        
        dtm = new DefaultTableModel();
        tbKho.setModel(dtm);
        
        dtm.addColumn("Tên nguyên liệu");
        dtm.addColumn("Số lượng");
        dtm.addColumn("Đơn vị");
        dtm.addColumn("Đơn giá");
        
        
       
        layKho();
        chamMauTin();
        //kiemTraVaiTro(this.tenDangNhap);
        
        
        
    }
    public Kho()
    {
        initComponents();
    }
    public void kiemTraVaiTro(String username)
            
    {
          //câu lệnh lấy data từ bảng
        //String sql="SELECT vaitro FROM nguoidung WHERE tendangnhap='"+username+"' ";
        String sql = "CALL KIEM_TRA_VAI_TRO('"+username+"')";
        try 
        {     
           // stmt = ketNoi.conn.createStatement();  
            stmt = conn.createStatement();  
            rs = stmt.executeQuery(sql); 
            if (stmt.execute(sql)) 
            {         
                rs = stmt.getResultSet();     
            }
        //thao tác trên tập kết quả trả về rs.... 
            while (rs.next()) 
            {   
                if(rs.getString(1).equals("admin"))
                {
                    btnXoa.setVisible(true);
                }
                else
                {
                    btnXoa.setVisible(false);
                }
         
            }
        } 
        catch (SQLException ex)
        {    
            System.out.println("Lỗi ở kiemTraVaiTro"); 
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
    public void capNhatSoLuong(String tenNguyenLieu, int soLuong)
    {
        //String sql ="UPDATE kho SET soluong="+soLuong+" WHERE tennguyenlieu='"+tenNguyenLieu+"' ";       
        String sql = "CALL GHI_LAI_SO_LUONG_KHO ('"+tenNguyenLieu+"',"+soLuong+") ";
        try 
        {     
            stmt =conn.createStatement();  
            stmt.execute(sql);

        } 
        catch (SQLException ex)
        {    
            System.out.println("Lỗi ở capNhatSoLuong"); 
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
     public void themNguyenLieuMoi()
    {
        //câu lệnh chèn data
//        String sql ="INSERT INTO kho (tennguyenlieu,soluong,donvi,dongia)"
//                + "VALUES ('"+txtTenNguyenLieu.getText()+"',0,'"+txtDonVi.getText()+"',"+txtDonGia.getText()+") ";
        String sql ="CALL THEM_NGUYEN_LIEU_MOI('"+txtTenNguyenLieu.getText()+"',0,'"+txtDonVi.getText()+"',"+txtDonGia.getText()+")";       
        
        try 
        {     
            stmt =conn.createStatement();  
            stmt.execute(sql);

        } 
        catch (SQLException ex)
        {    
            System.out.println("Lỗi ở themNguyenLieuMoi"); 
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
     public void xoaNguyenLieu()
    {
        //câu lệnh chèn data
        //String sql ="DELETE FROM kho WHERE tennguyenlieu='"+tenNguyenLieu+"' ";
        String sql ="CALL XOA_NGUYEN_LIEU('"+tenNguyenLieu+"')";       
        
        try 
        {     
            stmt =conn.createStatement();  
            stmt.execute(sql);

        } 
        catch (SQLException ex)
        {    
            System.out.println("Lỗi xoaNguyenLieu"); 
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
    public void layKho()
    {
       // String sql="SELECT * FROM kho ORDER BY tennguyenlieu ASC";
        String sql="CALL LAY_KHO()";
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
                dtm.addRow(new Object []{rs.getString(1),rs.getInt(2),rs.getString(3),rs.getInt(4)});
            }
        }
        
        catch (SQLException ex)
        {    
            System.out.println("Lỗi layKho"); 
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
    public void chamMauTin()
    {
        ListSelectionModel lsm = tbKho.getSelectionModel();
        lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsm.addListSelectionListener(new ListSelectionListener()
        {

            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                int row = tbKho.getSelectedRow();
                if(row>-1)
                {
                    tenNguyenLieu=String.valueOf(tbKho.getValueAt(row, 0));
                    txtChonNguyenLieu.setText(tenNguyenLieu);
                   
                }
            }
        });
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

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbKho = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTenNguyenLieu = new javax.swing.JTextField();
        txtDonVi = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtGhiLaiSoLuong = new javax.swing.JTextField();
        btnCapNhatSoLuong = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtChonNguyenLieu = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        txtHoTen = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(350, 50));
        setMaximumSize(new java.awt.Dimension(636, 650));
        setMinimumSize(new java.awt.Dimension(636, 650));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("KHO");

        jButton1.setText("<<");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        tbKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Tên nguyên liệu", "Số lượng", "Đơn vị", "Đơn giá"
            }
        ));
        jScrollPane1.setViewportView(tbKho);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("THÊM NGUYÊN LIỆU MỚI");

        jLabel3.setText("Tên nguyên liệu");

        jLabel4.setText("Đơn vị");

        jLabel5.setText("Đơn giá");

        jButton2.setText("THÊM NGUYÊN LIỆU");
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });

        btnXoa.setText("XÓA NGUYÊN LIỆU");
        btnXoa.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel6.setText("Ghi lại số lượng");

        btnCapNhatSoLuong.setText("Cập nhập số lượng");
        btnCapNhatSoLuong.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCapNhatSoLuongActionPerformed(evt);
            }
        });

        jLabel7.setText("Bạn đang chọn:");

        txtChonNguyenLieu.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtChonNguyenLieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtChonNguyenLieu.setText(" ");

        jPanel2.setPreferredSize(new java.awt.Dimension(200, 100));

        txtHoTen.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtHoTen.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtHoTen.setText("...............................................");

        txtTenDangNhap.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtTenDangNhap.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTenDangNhap.setText("...............................................");

        jLabel8.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        jLabel8.setText("Xin chào !");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8))
                    .addComponent(txtTenDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHoTen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenDangNhap)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTenNguyenLieu)
                                    .addComponent(txtDonVi)
                                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1)
                            .addComponent(jSeparator1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnXoa)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(7, 7, 7)
                        .addComponent(txtChonNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGhiLaiSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCapNhatSoLuong)
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(6, 6, 6)
                .addComponent(btnXoa)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(txtGhiLaiSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhatSoLuong)
                    .addComponent(txtChonNguyenLieu))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTenNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        // TODO add your handling code here:
        VaoKho vk = new VaoKho(tenDangNhap,hoTen,conn);
        vk.setVisible(true);
        
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        while (dtm.getRowCount()!=0)
        {
            dtm.removeRow(0);
        }
        themNguyenLieuMoi();
        layKho();
        txtTenNguyenLieu.setText("");
        txtDonVi.setText("");
        txtDonGia.setText("");
        txtChonNguyenLieu.setText("");
        JOptionPane.showMessageDialog(this,"Thêm thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnXoaActionPerformed
    {//GEN-HEADEREND:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int row = tbKho.getSelectedRow();
        if(row<=-1)
        {
            JOptionPane.showMessageDialog(this,"Chưa chọn dòng xóa !","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            xoaNguyenLieu();
            while (dtm.getRowCount()!=0)
            {
                dtm.removeRow(0);
            }
            layKho();
            txtChonNguyenLieu.setText("");
            JOptionPane.showMessageDialog(this,"Xóa thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhatSoLuongActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCapNhatSoLuongActionPerformed
    {//GEN-HEADEREND:event_btnCapNhatSoLuongActionPerformed
        // TODO add your handling code here:
        if(txtChonNguyenLieu.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this,"Chưa có chọn nguyên liệu !","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
        try
        {
            capNhatSoLuong(txtChonNguyenLieu.getText(),Integer.parseInt(txtGhiLaiSoLuong.getText()));
       
            while (dtm.getRowCount()!=0)
            {
                dtm.removeRow(0);
            }
            layKho();
            JOptionPane.showMessageDialog(this,"Cập nhật thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            txtGhiLaiSoLuong.setText("");
            txtChonNguyenLieu.setText("");
            
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(this,"Phải là số và không được trống !","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            txtGhiLaiSoLuong.setText("");
            txtChonNguyenLieu.setText("");
        }

        }
        
    }//GEN-LAST:event_btnCapNhatSoLuongActionPerformed

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
            java.util.logging.Logger.getLogger(Kho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Kho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Kho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Kho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new Kho().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatSoLuong;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tbKho;
    private javax.swing.JLabel txtChonNguyenLieu;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtDonVi;
    private javax.swing.JTextField txtGhiLaiSoLuong;
    private javax.swing.JLabel txtHoTen;
    private javax.swing.JLabel txtTenDangNhap;
    private javax.swing.JTextField txtTenNguyenLieu;
    // End of variables declaration//GEN-END:variables
}
