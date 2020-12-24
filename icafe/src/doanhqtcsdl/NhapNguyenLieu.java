/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doanhqtcsdl;

import com.sun.org.apache.xml.internal.dtm.DTM;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Siu_Shock
 */
public class NhapNguyenLieu extends javax.swing.JFrame
{

    /**
     * Creates new form NhapNguyenLieu
     */
    //KetNoi ketNoi = new KetNoi();
    Connection conn;
    Statement stmt = null; 
    ResultSet rs = null; 
    //
    String tenDangNhap;
    String hoTen;
    //
    String maHoaDonNhap;
    String ngay;
    DefaultComboBoxModel dcb;
    DefaultTableModel dtm;
    
    String _donvi;
    int _dongia;
    //
    String tenNguyenLieu;
    int soLuong;
    String donVi;
    int donGia;
    int thanhTien;
    
    int tongTien=0;
    public NhapNguyenLieu(String tenDangNhap, String hoTen,Connection conn)
    {
        initComponents();
        //ketNoi.MySQLConnect();
        
        
        
        this.tenDangNhap= tenDangNhap;
        this.hoTen=hoTen;
        this.conn=conn;
        txtHoTen.setText(hoTen);
        txtTenDangNhap.setText(tenDangNhap);
        
        dcb = new DefaultComboBoxModel();
        cbTenNguyenLieu.setModel(dcb);
        dtm = new DefaultTableModel();
        tbNhapNguyenLieu.setModel(dtm);
        dtm.addColumn("Tên nguyên liệu");
        dtm.addColumn("Số lượng");
        dtm.addColumn("Đơn vị");
        dtm.addColumn("Đơn giá");
        dtm.addColumn("Thành tiền");
        
        
        
        
        layMaHoaDonNgayNhien();
        hienNgayThangNam();
        chamMauTin();
        //
        layTenNguyenLieu();
    }
    
    
    public NhapNguyenLieu()
    {
        initComponents();
    }
    public void ghiTongTien(int tien)
    {
        tongTien+=tien;
        txtTongTien.setText(""+tongTien);
    }
    public void layTenNguyenLieu()
    {
        //String sql="SELECT tennguyenlieu FROM kho ORDER BY tennguyenlieu ASC";
        String sql="CALL LAY_TEN_NGUYEN_LIEU()";
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
                dcb.addElement(rs.getString(1));
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
    public void chamMauTin()
    {
        ListSelectionModel lsm = tbNhapNguyenLieu.getSelectionModel();
        lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsm.addListSelectionListener(new ListSelectionListener()
        {

            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                int row = tbNhapNguyenLieu.getSelectedRow();
                if(row>-1)
                {
                    tenNguyenLieu=String.valueOf(tbNhapNguyenLieu.getValueAt(row, 0)); 
                    soLuong=Integer.parseInt(String.valueOf(tbNhapNguyenLieu.getValueAt(row, 1)));
                    donVi=String.valueOf(tbNhapNguyenLieu.getValueAt(row, 2));
                    donGia=Integer.parseInt(String.valueOf(tbNhapNguyenLieu.getValueAt(row, 3)));
                    thanhTien=Integer.parseInt(String.valueOf(tbNhapNguyenLieu.getValueAt(row, 4)));
                   
                }
            }
        });
    }
    public void hienNgayThangNam()
    {
        ngay = String.valueOf(java.time.LocalDate.now());

        String _ngay = ngay.substring(8,10)+"/"+ngay.substring(5,7)+"/"+ngay.substring(0,4);
        
        txtNgay.setText(_ngay);
        
    }
    public void layMaHoaDonNgayNhien()
    {
        String uuid = UUID.randomUUID().toString();
        maHoaDonNhap=uuid.substring(0, 8);
        txtMaHoaDonNhap.setText(maHoaDonNhap);
    }
    public void layDonViDonGia (String tenNguyenLieu)
    {
        //String sql="SELECT donvi,dongia FROM kho WHERE tennguyenlieu='"+tenNguyenLieu+"' ";
        String sql="CALL LAY_DON_VI_DON_GIA('"+tenNguyenLieu+"')";
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
                _donvi=rs.getString(1);
                _dongia=rs.getInt(2);
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
    public void upHoaDonNhap()
    {
       //câu lệnh chèn data
//        String sql ="INSERT INTO hoadonnhap (mahoadonnhap,tongtien,ngay,thanhtoan,tendangnhap)"
//                + "VALUES ('"+maHoaDonNhap+"',"+tongTien+",'"+ngay+"',0,'"+tenDangNhap+"' )";
        String sql="CALL UP_HOA_DON_NHAP('"+maHoaDonNhap+"',"+tongTien+",'"+ngay+"',0,'"+tenDangNhap+"')";

        try 
        {     
            stmt = conn.createStatement();  
            stmt.execute(sql);

        } 
        catch (SQLException ex)
        {    
            System.out.println("Lỗi up hoa don nhap"); 
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
     public void capNhatSoLuong(String tenNguyenLieu,int soLuong)
    {
       //câu lệnh chèn data
        //String sql ="UPDATE kho SET soluong=soluong+"+soLuong+" WHERE tennguyenlieu='"+tenNguyenLieu+"' ";
        String sql="CALL CAP_NHAT_SO_LUONG('"+tenNguyenLieu+"',"+soLuong+")";

        try 
        {     
            stmt = conn.createStatement();  
            stmt.execute(sql);

        } 
        catch (SQLException ex)
        {    
            System.out.println("Lỗi Cập nhật số lượng của "+tenNguyenLieu); 
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
   public void upChiTietHoaDonNhap(String maHoaDonNhap, String tenNguyenLieu, int soLuong, String donVi,int donGia,int thanhTien)
   {
              //câu lệnh chèn data
//        String sql ="INSERT INTO chitiethoadonnhap (mahoadonnhap,tennguyenlieu,soluong,donvi,dongia,thanhtien)"
//                + "VALUES ('"+maHoaDonNhap+"','"+tenNguyenLieu+"',"+soLuong+",'"+donVi+"',"+donGia+","+thanhTien+")";
        String sql ="CALL UP_CHI_TIET_HOA_DON_NHAP('"+maHoaDonNhap+"','"+tenNguyenLieu+"',"+soLuong+",'"+donVi+"',"+donGia+","+thanhTien+")";       
        
        try 
        {     
            stmt = conn.createStatement();  
            stmt.execute(sql);

        } 
        catch (SQLException ex)
        {    
            System.out.println("Loi up chi tiet hoa don"); 
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

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        cbTenNguyenLieu = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbNhapNguyenLieu = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtTongTien = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtHoTen = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaHoaDonNhap = new javax.swing.JLabel();
        txtNgay = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(350, 50));
        setMaximumSize(new java.awt.Dimension(636, 650));
        setMinimumSize(new java.awt.Dimension(636, 650));
        setPreferredSize(new java.awt.Dimension(636, 650));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("NHẬP NGUYÊN LIỆU");

        jButton1.setText("<<");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("XÁC NHẬN");
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });

        cbTenNguyenLieu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tên nguyên liệu" }));

        jLabel9.setText("Chọn nguyên liệu");

        jLabel10.setText("Nhập số lượng cần thêm");

        tbNhapNguyenLieu.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbNhapNguyenLieu);

        jButton3.setText("THÊM");
        jButton3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("XÓA DÒNG");
        jButton4.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton4ActionPerformed(evt);
            }
        });

        txtTongTien.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        txtTongTien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTongTien.setText("0");

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

        jPanel1.setPreferredSize(new java.awt.Dimension(200, 100));

        jLabel2.setText("Mã hóa đơn: ");

        txtMaHoaDonNhap.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        txtMaHoaDonNhap.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtMaHoaDonNhap.setText("......................");

        txtNgay.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        txtNgay.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtNgay.setText("..........................");

        jLabel3.setText("Ngày:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMaHoaDonNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaHoaDonNhap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNgay))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jLabel4.setText("VND");

        jLabel5.setText("TỔNG CỘNG:");

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
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbTenNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                                .addComponent(jButton3))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTenNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(txtTongTien)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton3ActionPerformed
    {//GEN-HEADEREND:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try
        {
            layDonViDonGia(String.valueOf(cbTenNguyenLieu.getSelectedItem()));
            dtm.addRow(new Object []{cbTenNguyenLieu.getSelectedItem(),txtSoLuong.getText(),_donvi,_dongia,_dongia*Integer.parseInt(txtSoLuong.getText())});
            ghiTongTien(_dongia*Integer.parseInt(txtSoLuong.getText()));
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(this,"Phải là số và không được trống !","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            txtSoLuong.setText("");
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton4ActionPerformed
    {//GEN-HEADEREND:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int row = tbNhapNguyenLieu.getSelectedRow();
        if(row<-1)
        {
            JOptionPane.showMessageDialog(this,"Chưa chọn dòng xóa !","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            int x = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa ?");       
            if(x==JOptionPane.YES_OPTION)
            {   
                
                         
                dtm.removeRow(row);
                ghiTongTien(-thanhTien);
                
            
            }
            
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
        // TODO add your handling code here:
        upHoaDonNhap();       
            for (int row=0;row<tbNhapNguyenLieu.getRowCount();row++)
            {
                String tennguyenlieu = String.valueOf(tbNhapNguyenLieu.getValueAt(row, 0));
                int soluong = Integer.parseInt(String.valueOf(tbNhapNguyenLieu.getValueAt(row, 1)));
                String donvi = String.valueOf(tbNhapNguyenLieu.getValueAt(row, 2));
                int dongia = Integer.parseInt(String.valueOf(tbNhapNguyenLieu.getValueAt(row, 3)));
                int thanhtien = Integer.parseInt(String.valueOf(tbNhapNguyenLieu.getValueAt(row, 4)));
            
                upChiTietHoaDonNhap(maHoaDonNhap, tennguyenlieu, soluong, donvi, dongia, thanhtien);
                System.out.println("Up xong dòng "+(row+1));
                capNhatSoLuong(tennguyenlieu, soluong);
                System.out.println("Đã cập nhật số lượng ");
            }
            JOptionPane.showMessageDialog(this,"Đã nhập thành công !","Thông báo",JOptionPane.INFORMATION_MESSAGE); 
            
            layMaHoaDonNgayNhien();
            
            tongTien=0;
            txtTongTien.setText("0");
            txtSoLuong.setText("");
            while (dtm.getRowCount()!=0)
            {
                dtm.removeRow(0);
            }
           
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(NhapNguyenLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(NhapNguyenLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(NhapNguyenLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(NhapNguyenLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new NhapNguyenLieu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbTenNguyenLieu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbNhapNguyenLieu;
    private javax.swing.JLabel txtHoTen;
    private javax.swing.JLabel txtMaHoaDonNhap;
    private javax.swing.JLabel txtNgay;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JLabel txtTenDangNhap;
    private javax.swing.JLabel txtTongTien;
    // End of variables declaration//GEN-END:variables
}
