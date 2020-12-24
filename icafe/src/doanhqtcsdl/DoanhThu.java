/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//CÓ 3 CÂU SQL
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

public class DoanhThu extends javax.swing.JFrame
{

    //KetNoi ketNoi = new KetNoi();
    Connection conn;
    Statement stmt = null;
    ResultSet rs = null;
   //
    String maHoaDonGhi;
    String ngayGhi;
    String tenNguoiGhi;
    String tongTienGhi;
    //
    String ngay;
    String thang;
    String nam;
    //
    
    String _ngay="";
    String _thang="";
    String _nam="";
    DefaultTableModel dtm;
    //
    int tongTien=0; 
    //
    String tenDangNhap;
    String hoTen;
    
    
    public DoanhThu(String tenDangNhap, String hoTen,Connection conn)
    {
        initComponents();
        dtm = new DefaultTableModel();
        tbDoanhThu.setModel(dtm);
        dtm.addColumn("Mã hóa đơn");
        dtm.addColumn("Tổng tiền");
        dtm.addColumn("Ngày");
        dtm.addColumn("Nhân viên");
        
        this.tenDangNhap=tenDangNhap;
        this.hoTen=hoTen;
        this.conn=conn;
        txtTenDangNhap.setText(tenDangNhap);
        txtHoTen.setText(hoTen);
        
        chamMauTin();
        kiemTraVaiTro(this.tenDangNhap);
        //ketNoi.MySQLConnect();
        
        
    }
      public DoanhThu()
    {
        initComponents();
       
        
        
    }
      public void resetThongTin()
      {
            maHoaDonGhi="";
            txtMaHoaDon.setText("");
            ngayGhi="";                   
            tenNguoiGhi="";
            tongTienGhi="";
      }
    public void kiemTraVaiTro(String username)
            
    {
          //câu lệnh lấy data từ bảng
        //String sql="SELECT vaitro FROM nguoidung WHERE tendangnhap='"+username+"' ";
        String sql="CALL KIEM_TRA_VAI_TRO('"+username+"')";
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
            System.out.println("Lỗi ở kiemTraHoaDon"); 
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
        ListSelectionModel lsm = tbDoanhThu.getSelectionModel();
        lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsm.addListSelectionListener(new ListSelectionListener()
        {

            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                int row = tbDoanhThu.getSelectedRow();
                if(row>-1)
                {
                    maHoaDonGhi=String.valueOf(tbDoanhThu.getValueAt(row, 0));
                    txtMaHoaDon.setText(maHoaDonGhi);
                    ngayGhi=String.valueOf(tbDoanhThu.getValueAt(row, 2));
                    
                    tenNguoiGhi=String.valueOf(tbDoanhThu.getValueAt(row, 3));
                    tongTienGhi=String.valueOf(tbDoanhThu.getValueAt(row, 1));
                   
                }
            }
        });
    }
    public String dinhDangNgay(String ngay)
    {
        return ngay.substring(8,10)+"/"+ngay.substring(5,7)+"/"+ngay.substring(0,4);
    }
    public void layDanhSachHoaDon()
    {
        String sql="SELECT hd.mahoadon,hd.tongtien,hd.ngay,nd.hoten FROM hoadon as hd, nguoidung as nd "
                + "WHERE hd.thanhtoan=1 and hd.tendangnhap=nd.tendangnhap "+_ngay+""+_thang+""+_nam+" ORDER BY ngay ASC"; 
        
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
                dtm.addRow(new Object [] {rs.getString(1),rs.getInt(2),dinhDangNgay(rs.getString(3)),rs.getString(4)});

            }
        }
        
        catch (SQLException ex)
        {    
            System.out.println("Lỗi ở layDanhSachHoaDon "); 
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
    public void xoaChiTietHoaDon()
   {
       //câu lệnh chèn data
        //String sql ="DELETE FROM chitiethoadon WHERE mahoadon='"+maHoaDonGhi+"'";
        String sql ="CALL XOA_CHI_TIET_HOA_DON('"+maHoaDonGhi+"')";       
               
        
        try 
        {     
            stmt = conn.createStatement();  
            stmt.execute(sql);

        } 
        catch (SQLException ex)
        {    
            System.out.println("Lỗi xóa chiTietHoaDon "); 
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
    public void xoaHoaDon()
   {
       //câu lệnh chèn data
       // String sql ="DELETE FROM hoadon WHERE mahoadon='"+maHoaDonGhi+"'";
        String sql ="CALL XOA_HOA_DON('"+maHoaDonGhi+"')";          
               
        
        try 
        {     
            stmt = conn.createStatement();  
            stmt.execute(sql);

        } 
        catch (SQLException ex)
        {    
            System.out.println("Lỗi xóa hoaDon"); 
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

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDoanhThu = new javax.swing.JTable();
        cbbNgay = new javax.swing.JComboBox();
        cbbThang = new javax.swing.JComboBox();
        cbbNam = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        txtTongTien = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtHoTen = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Doanh thu");
        setLocation(new java.awt.Point(350, 50));
        setMaximumSize(new java.awt.Dimension(636, 650));
        setMinimumSize(new java.awt.Dimension(636, 650));
        setResizable(false);

        jButton1.setText("<<");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        tbDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Mã hóa đơn", "Tổng tiền", "Ngày", "Nhân viên"
            }
        ));
        jScrollPane1.setViewportView(tbDoanhThu);

        cbbNgay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chưa chọn", "Tất cả", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        cbbThang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chưa chọn", "Tất cả", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        cbbNam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chưa chọn", "Tất cả", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029" }));

        jButton2.setText("TÌM");
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });

        txtTongTien.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        txtTongTien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTongTien.setText("0 ");

        btnXoa.setText("XÓA HÓA ĐƠN");
        btnXoa.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnXoaActionPerformed(evt);
            }
        });

        jButton4.setText("Xem chi tiết hóa đơn");
        jButton4.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setText("Bạn đang chọn:");

        txtMaHoaDon.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        txtMaHoaDon.setText(" ");

        jPanel2.setPreferredSize(new java.awt.Dimension(200, 100));

        txtHoTen.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtHoTen.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtHoTen.setText("...............................................");

        txtTenDangNhap.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtTenDangNhap.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTenDangNhap.setText("...............................................");

        jLabel2.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        jLabel2.setText("Xin chào !");

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
                        .addComponent(jLabel2))
                    .addComponent(txtTenDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHoTen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenDangNhap)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("DOANH THU");

        jLabel4.setText("NGÀY");

        jLabel5.setText("THÁNG");

        jLabel6.setText("NĂM");

        jLabel7.setText("TỔNG TIỀN:");

        jLabel8.setText("VND");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton4)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnXoa)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa)
                    .addComponent(jLabel1)
                    .addComponent(txtMaHoaDon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongTien)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(53, 53, 53))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Menu menu = new Menu(tenDangNhap,hoTen,conn);
        menu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
        // TODO add your handling code here:
        tongTien=0;
        
        while (dtm.getRowCount()!=0)
        {
            dtm.removeRow(0);
        }
        
        ngay = String.valueOf(cbbNgay.getSelectedItem());       
        thang = String.valueOf(cbbThang.getSelectedItem());       
        nam = String.valueOf(cbbNam.getSelectedItem());
        
        if(ngay.equals("Chưa chọn") || thang.equals("Chưa chọn") || nam.equals("Chưa chọn"))
        {
            JOptionPane.showMessageDialog(this,"Chưa chọn ngày tháng năm","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
        
        //and day(ngay)='"+ngay+"' and month(ngay)='"+thang+"' and year(ngay)='"+nam+"' 
        else
        {
            _ngay="and day(ngay)='"+ngay+"' ";
            _thang="and month(ngay)='"+thang+"' ";
            _nam="and year(ngay)='"+nam+"' ";
            if(ngay.equals("Tất cả"))
            {
                _ngay="";
            }
            if(thang.equals("Tất cả"))
            {
                _thang="";
            }
            if(nam.equals("Tất cả"))
            {
                _nam="";
            }
            layDanhSachHoaDon();
            resetThongTin();
            
        }
        for (int row = 0;row<tbDoanhThu.getRowCount();row++)
        {
            tongTien+=Integer.parseInt(String.valueOf(tbDoanhThu.getValueAt(row, 1)));
        }
            
        txtTongTien.setText(""+tongTien);
        
       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnXoaActionPerformed
    {//GEN-HEADEREND:event_btnXoaActionPerformed
        // TODO add your handling code here:
        //txtTongTien.setVisible(false);
        int row = tbDoanhThu.getSelectedRow();
        if(row<=-1)
        {
            JOptionPane.showMessageDialog(this,"Chưa chọn dòng xóa !","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            int x = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa ?");       
            if(x==JOptionPane.YES_OPTION)
            {   
                 
                xoaChiTietHoaDon();
                xoaHoaDon();
                dtm.removeRow(row);
                resetThongTin();
            }           
        }

        tongTien=0;       
        for (int r = 0;r<tbDoanhThu.getRowCount();r++)
        {
            tongTien+=Integer.parseInt(String.valueOf(tbDoanhThu.getValueAt(r, 1)));          
        }           
        txtTongTien.setText(""+tongTien);
    }//GEN-LAST:event_btnXoaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton4ActionPerformed
    {//GEN-HEADEREND:event_jButton4ActionPerformed
        // TODO add your handling code here:
        ChiTietHoaDon ct = new ChiTietHoaDon(tenNguoiGhi, maHoaDonGhi, ngayGhi, tongTienGhi, conn);
        ct.setVisible(true);
        
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(DoanhThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(DoanhThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(DoanhThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(DoanhThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new DoanhThu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox cbbNam;
    private javax.swing.JComboBox cbbNgay;
    private javax.swing.JComboBox cbbThang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JTable tbDoanhThu;
    private javax.swing.JLabel txtHoTen;
    private javax.swing.JLabel txtMaHoaDon;
    private javax.swing.JLabel txtTenDangNhap;
    private javax.swing.JLabel txtTongTien;
    // End of variables declaration//GEN-END:variables
}
