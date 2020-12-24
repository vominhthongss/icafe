/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doanhqtcsdl;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


/**
 *
 * @author Siu_Shock
 */
public class Lau1 extends javax.swing.JFrame
{

    /**
     
     */
//    TrangThaiBan tt = new TrangThaiBan();
    //KetNoi ketNoi = new KetNoi();
    Connection conn;
    Statement stmt = null;
    ResultSet rs = null;
    
    boolean coHoaDon;
    String maHoaDon;
    
    private String tenDangNhap;
    private String hoTen;
    //
   
    public Lau1(String tenDangNhap, String hoTen,Connection conn)
    {
       
       
        initComponents();

        this.tenDangNhap=tenDangNhap;
        this.hoTen=hoTen;
        this.conn=conn;
        txtHoTen.setText(hoTen);
        txtTenDangNhap.setText(tenDangNhap);
        
        
        
        
        resetMau();
        layTrangThai();
       

    }
    public Lau1()
    {
        initComponents(); 

    }
 
    public boolean coHoaDonChua(int ban)
            
    {
          //câu lệnh lấy data từ bảng
        //String sql="SELECT ban FROM trangthai WHERE ban='"+ban+"'";
        String sql="SELECT CO_HD_CHUA('"+ban+"')";
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
                if(rs.getString(1).equals("0"))
                {
                    coHoaDon=false;
                }
                else
                {
                    coHoaDon=true;
                }
                
       
            }
        } 
        catch (SQLException ex)
        {    
            System.out.println("Lỗi ở coHoaDonChua"); 
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
        return coHoaDon;
    }
       public void layMaHoaDon(String ban)
            
    {
          //câu lệnh lấy data từ bảng
        //String sql="SELECT mahoadon FROM trangthai WHERE ban='"+ban+"'";
        String sql="SELECT LAY_MA_HOA_DON ('"+ban+"') ";
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
                maHoaDon=rs.getString(1);
       
            }
        } 
        catch (SQLException ex)
        {    
            System.out.println("Lỗi ở layMaHoaDon"); 
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
    public void resetMau()
    {
        pnlBan1.setBackground(Color.red);
        pnlBan2.setBackground(Color.red);
        pnlBan3.setBackground(Color.red);
        pnlBan4.setBackground(Color.red);
        pnlBan5.setBackground(Color.red);
        pnlBan6.setBackground(Color.red);
    }
    public void layTrangThai()
    {
        //câu lệnh lấy data từ bảng
        //String sql="SELECT ban FROM trangthai ORDER BY ban ASC";
        String sql="CALL LAY_TRANG_THAI()";
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
               switch (rs.getString(1))
               {
                   case "1": pnlBan1.setBackground(Color.green);break;
                   case "2": pnlBan2.setBackground(Color.green);break;
                   case "3": pnlBan3.setBackground(Color.green);break;
                   case "4": pnlBan4.setBackground(Color.green);break;
                   case "5": pnlBan5.setBackground(Color.green);break;
                   case "6": pnlBan6.setBackground(Color.green);break;
                  
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
    public void xoaHoaDonTaiBan(String ban)
    {
    
        //câu lệnh chèn data
        //String sql ="DELETE FROM trangthai WHERE ban='"+ban+"' ";
        String sql="CALL XOA_HOA_DON_TAI_BAN('"+ban+"')";       
        
        try 
        {     
            stmt = conn.createStatement();  
            stmt.execute(sql);

        } 
        catch (SQLException ex)
        {    
            System.out.println("Lỗi xoaHoaDonTaiBan"); 
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
    public void ghiHoaDonChuyenBan(int ban, String maHoaDon)
    {
        //câu lệnh chèn data
        //String sql ="UPDATE trangthai SET ban='"+ban+"' WHERE mahoadon='"+maHoaDon+"' ";
        String sql = "CALL GHI_HOA_DON_CHUYEN_BAN('"+ban+"','"+maHoaDon+"')";     
        
        try 
        {     
            stmt = conn.createStatement();  
            stmt.execute(sql);

        } 
        catch (SQLException ex)
        {    
            System.out.println("Có hóa đơn rồi... hãy chọn mục ĐẶT THÊM"); 
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

        jColorChooser1 = new javax.swing.JColorChooser();
        jButton1 = new javax.swing.JButton();
        pnlBan1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnDatMon1 = new javax.swing.JButton();
        btnHoaDon1 = new javax.swing.JButton();
        btnDoiBan1 = new javax.swing.JButton();
        btnDatThem1 = new javax.swing.JButton();
        txtSo1 = new javax.swing.JLabel();
        pnlBan2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnDatMon2 = new javax.swing.JButton();
        btnHoaDon2 = new javax.swing.JButton();
        btnDoiBan2 = new javax.swing.JButton();
        btnDatThem2 = new javax.swing.JButton();
        txtSo2 = new javax.swing.JLabel();
        pnlBan3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnDatMon3 = new javax.swing.JButton();
        btnHoaDon3 = new javax.swing.JButton();
        btnDoiBan3 = new javax.swing.JButton();
        btnDatThem3 = new javax.swing.JButton();
        txtSo3 = new javax.swing.JLabel();
        pnlBan4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnDatMon4 = new javax.swing.JButton();
        btnHoaDon4 = new javax.swing.JButton();
        btnDoiBan4 = new javax.swing.JButton();
        btnDatThem4 = new javax.swing.JButton();
        txtSo4 = new javax.swing.JLabel();
        pnlBan5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btnDatMon5 = new javax.swing.JButton();
        btnHoaDon5 = new javax.swing.JButton();
        btnDoiBan5 = new javax.swing.JButton();
        btnDatThem5 = new javax.swing.JButton();
        txtSo5 = new javax.swing.JLabel();
        pnlBan6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btnDatMon6 = new javax.swing.JButton();
        btnHoaDon6 = new javax.swing.JButton();
        btnDoiBan6 = new javax.swing.JButton();
        btnDatThem6 = new javax.swing.JButton();
        txtSo6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtHoTen = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lầu 1");
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

        pnlBan1.setToolTipText("");
        pnlBan1.setPreferredSize(new java.awt.Dimension(250, 90));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Bàn");

        btnDatMon1.setText("Đặt món");
        btnDatMon1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDatMon1ActionPerformed(evt);
            }
        });

        btnHoaDon1.setText("Hoá đơn");
        btnHoaDon1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnHoaDon1ActionPerformed(evt);
            }
        });

        btnDoiBan1.setText("Đổi bàn");
        btnDoiBan1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDoiBan1ActionPerformed(evt);
            }
        });

        btnDatThem1.setText("Đặt thêm");
        btnDatThem1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDatThem1ActionPerformed(evt);
            }
        });

        txtSo1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtSo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSo1.setText("1");

        javax.swing.GroupLayout pnlBan1Layout = new javax.swing.GroupLayout(pnlBan1);
        pnlBan1.setLayout(pnlBan1Layout);
        pnlBan1Layout.setHorizontalGroup(
            pnlBan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBan1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSo1, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlBan1Layout.createSequentialGroup()
                        .addComponent(btnHoaDon1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDoiBan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlBan1Layout.createSequentialGroup()
                        .addComponent(btnDatMon1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDatThem1)))
                .addGap(14, 14, 14))
        );
        pnlBan1Layout.setVerticalGroup(
            pnlBan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBan1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDatMon1)
                    .addComponent(btnDatThem1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(pnlBan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDoiBan1))
                .addContainerGap())
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtSo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlBan2.setToolTipText("");
        pnlBan2.setPreferredSize(new java.awt.Dimension(250, 90));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Bàn");

        btnDatMon2.setText("Đặt món");
        btnDatMon2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDatMon2ActionPerformed(evt);
            }
        });

        btnHoaDon2.setText("Hoá đơn");
        btnHoaDon2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnHoaDon2ActionPerformed(evt);
            }
        });

        btnDoiBan2.setText("Đổi bàn");
        btnDoiBan2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDoiBan2ActionPerformed(evt);
            }
        });

        btnDatThem2.setText("Đặt thêm");
        btnDatThem2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDatThem2ActionPerformed(evt);
            }
        });

        txtSo2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtSo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSo2.setText("2");

        javax.swing.GroupLayout pnlBan2Layout = new javax.swing.GroupLayout(pnlBan2);
        pnlBan2.setLayout(pnlBan2Layout);
        pnlBan2Layout.setHorizontalGroup(
            pnlBan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBan2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSo2, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlBan2Layout.createSequentialGroup()
                        .addComponent(btnHoaDon2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDoiBan2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlBan2Layout.createSequentialGroup()
                        .addComponent(btnDatMon2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDatThem2)))
                .addGap(14, 14, 14))
        );
        pnlBan2Layout.setVerticalGroup(
            pnlBan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBan2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDatMon2)
                    .addComponent(btnDatThem2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(pnlBan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHoaDon2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDoiBan2))
                .addContainerGap())
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtSo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlBan3.setToolTipText("");
        pnlBan3.setPreferredSize(new java.awt.Dimension(250, 90));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Bàn");

        btnDatMon3.setText("Đặt món");
        btnDatMon3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDatMon3ActionPerformed(evt);
            }
        });

        btnHoaDon3.setText("Hoá đơn");
        btnHoaDon3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnHoaDon3ActionPerformed(evt);
            }
        });

        btnDoiBan3.setText("Đổi bàn");
        btnDoiBan3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDoiBan3ActionPerformed(evt);
            }
        });

        btnDatThem3.setText("Đặt thêm");
        btnDatThem3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDatThem3ActionPerformed(evt);
            }
        });

        txtSo3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtSo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSo3.setText("3");

        javax.swing.GroupLayout pnlBan3Layout = new javax.swing.GroupLayout(pnlBan3);
        pnlBan3.setLayout(pnlBan3Layout);
        pnlBan3Layout.setHorizontalGroup(
            pnlBan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBan3Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSo3, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlBan3Layout.createSequentialGroup()
                        .addComponent(btnHoaDon3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDoiBan3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlBan3Layout.createSequentialGroup()
                        .addComponent(btnDatMon3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDatThem3)))
                .addGap(14, 14, 14))
        );
        pnlBan3Layout.setVerticalGroup(
            pnlBan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBan3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDatMon3)
                    .addComponent(btnDatThem3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(pnlBan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHoaDon3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDoiBan3))
                .addContainerGap())
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtSo3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlBan4.setToolTipText("");
        pnlBan4.setPreferredSize(new java.awt.Dimension(250, 90));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("Bàn");

        btnDatMon4.setText("Đặt món");
        btnDatMon4.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDatMon4ActionPerformed(evt);
            }
        });

        btnHoaDon4.setText("Hoá đơn");
        btnHoaDon4.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnHoaDon4ActionPerformed(evt);
            }
        });

        btnDoiBan4.setText("Đổi bàn");
        btnDoiBan4.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDoiBan4ActionPerformed(evt);
            }
        });

        btnDatThem4.setText("Đặt thêm");
        btnDatThem4.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDatThem4ActionPerformed(evt);
            }
        });

        txtSo4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtSo4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSo4.setText("4");

        javax.swing.GroupLayout pnlBan4Layout = new javax.swing.GroupLayout(pnlBan4);
        pnlBan4.setLayout(pnlBan4Layout);
        pnlBan4Layout.setHorizontalGroup(
            pnlBan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBan4Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSo4, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlBan4Layout.createSequentialGroup()
                        .addComponent(btnHoaDon4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDoiBan4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlBan4Layout.createSequentialGroup()
                        .addComponent(btnDatMon4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDatThem4)))
                .addGap(14, 14, 14))
        );
        pnlBan4Layout.setVerticalGroup(
            pnlBan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBan4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDatMon4)
                    .addComponent(btnDatThem4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(pnlBan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHoaDon4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDoiBan4))
                .addContainerGap())
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtSo4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlBan5.setToolTipText("");
        pnlBan5.setPreferredSize(new java.awt.Dimension(250, 90));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("Bàn");

        btnDatMon5.setText("Đặt món");
        btnDatMon5.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDatMon5ActionPerformed(evt);
            }
        });

        btnHoaDon5.setText("Hoá đơn");
        btnHoaDon5.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnHoaDon5ActionPerformed(evt);
            }
        });

        btnDoiBan5.setText("Đổi bàn");
        btnDoiBan5.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDoiBan5ActionPerformed(evt);
            }
        });

        btnDatThem5.setText("Đặt thêm");
        btnDatThem5.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDatThem5ActionPerformed(evt);
            }
        });

        txtSo5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtSo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSo5.setText("5");

        javax.swing.GroupLayout pnlBan5Layout = new javax.swing.GroupLayout(pnlBan5);
        pnlBan5.setLayout(pnlBan5Layout);
        pnlBan5Layout.setHorizontalGroup(
            pnlBan5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBan5Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSo5, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBan5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlBan5Layout.createSequentialGroup()
                        .addComponent(btnHoaDon5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDoiBan5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlBan5Layout.createSequentialGroup()
                        .addComponent(btnDatMon5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDatThem5)))
                .addGap(14, 14, 14))
        );
        pnlBan5Layout.setVerticalGroup(
            pnlBan5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBan5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBan5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDatMon5)
                    .addComponent(btnDatThem5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(pnlBan5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHoaDon5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDoiBan5))
                .addContainerGap())
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtSo5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlBan6.setToolTipText("");
        pnlBan6.setPreferredSize(new java.awt.Dimension(250, 90));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setText("Bàn");

        btnDatMon6.setText("Đặt món");
        btnDatMon6.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDatMon6ActionPerformed(evt);
            }
        });

        btnHoaDon6.setText("Hoá đơn");
        btnHoaDon6.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnHoaDon6ActionPerformed(evt);
            }
        });

        btnDoiBan6.setText("Đổi bàn");
        btnDoiBan6.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDoiBan6ActionPerformed(evt);
            }
        });

        btnDatThem6.setText("Đặt thêm");
        btnDatThem6.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDatThem6ActionPerformed(evt);
            }
        });

        txtSo6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtSo6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSo6.setText("6");

        javax.swing.GroupLayout pnlBan6Layout = new javax.swing.GroupLayout(pnlBan6);
        pnlBan6.setLayout(pnlBan6Layout);
        pnlBan6Layout.setHorizontalGroup(
            pnlBan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBan6Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSo6, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlBan6Layout.createSequentialGroup()
                        .addComponent(btnHoaDon6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDoiBan6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlBan6Layout.createSequentialGroup()
                        .addComponent(btnDatMon6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDatThem6)))
                .addGap(14, 14, 14))
        );
        pnlBan6Layout.setVerticalGroup(
            pnlBan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBan6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDatMon6)
                    .addComponent(btnDatThem6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(pnlBan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHoaDon6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDoiBan6))
                .addContainerGap())
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtSo6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

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

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("LẦU 1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlBan5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(pnlBan6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlBan3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(pnlBan4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlBan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(pnlBan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jButton1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlBan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlBan3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnlBan5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlBan6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(pnlBan4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(174, Short.MAX_VALUE))
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

    private void btnDatMon1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDatMon1ActionPerformed
    {//GEN-HEADEREND:event_btnDatMon1ActionPerformed
        // TODO add your handling code here:
        DatMon dm = new DatMon("1",tenDangNhap,hoTen,conn);
        dm.setVisible(true);
        dm.setTitle("Đặt món bàn 1");
        this.setVisible(false);
        
        
    }//GEN-LAST:event_btnDatMon1ActionPerformed

    private void btnHoaDon1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnHoaDon1ActionPerformed
    {//GEN-HEADEREND:event_btnHoaDon1ActionPerformed
        // TODO add your handling code here:
        HoaDon hd = new HoaDon("1",tenDangNhap,hoTen,conn);
        hd.setVisible(true);
        hd.setTitle("Hoá đơn bàn 1");
        this.setVisible(false);
    }//GEN-LAST:event_btnHoaDon1ActionPerformed

    private void btnDoiBan1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDoiBan1ActionPerformed
    {//GEN-HEADEREND:event_btnDoiBan1ActionPerformed

        coHoaDon=false;
        String x="";
        int n=0;
        while (true)
        {
            x = JOptionPane.showInputDialog(this,"Nhập số bàn chuyển","Nhập",JOptionPane.DEFAULT_OPTION);

                n=Integer.parseInt(x);
                if(n>0 && n<12)
                {
                    
                    if(coHoaDonChua(Integer.parseInt(x))==false)
                    {
                       
                        layMaHoaDon(txtSo1.getText());
                        if(maHoaDon==null)
                        {
                            JOptionPane.showMessageDialog(this,"Không chuyển được","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                       // xoaHoaDonTaiBan(txtSo1.getText());
                        ghiHoaDonChuyenBan(n, maHoaDon);
                        //System.out.println("Chuyen sang ban "+n);
                        resetMau();
                        layTrangThai();
                        break;
                    }
                    else
                    {
                       
                        JOptionPane.showMessageDialog(this,"Không chuyển được, bàn có khách rồi !","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                       
                        break;
                    }
                    
                }
            //System.out.println("n = "+n);
        } 
    }//GEN-LAST:event_btnDoiBan1ActionPerformed

    private void btnDatThem1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDatThem1ActionPerformed
    {//GEN-HEADEREND:event_btnDatThem1ActionPerformed
        // TODO add your handling code here:
        DatThem dt = new DatThem("1",tenDangNhap,hoTen,conn);
        dt.setVisible(true);
        dt.setTitle("Đặt món bàn 1");
        this.setVisible(false);
    }//GEN-LAST:event_btnDatThem1ActionPerformed

    private void btnDatMon2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDatMon2ActionPerformed
    {//GEN-HEADEREND:event_btnDatMon2ActionPerformed
        // TODO add your handling code here:
        DatMon dm = new DatMon("2",tenDangNhap,hoTen,conn);
        dm.setVisible(true);
        dm.setTitle("Đặt món bàn 2");
        this.setVisible(false);
    }//GEN-LAST:event_btnDatMon2ActionPerformed

    private void btnHoaDon2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnHoaDon2ActionPerformed
    {//GEN-HEADEREND:event_btnHoaDon2ActionPerformed
        // TODO add your handling code here:
        HoaDon hd = new HoaDon("2",tenDangNhap,hoTen,conn);
        hd.setVisible(true);
        hd.setTitle("Hoá đơn bàn 2");
        this.setVisible(false);
    }//GEN-LAST:event_btnHoaDon2ActionPerformed

    private void btnDoiBan2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDoiBan2ActionPerformed
    {//GEN-HEADEREND:event_btnDoiBan2ActionPerformed
        // TODO add your handling code here:
         coHoaDon=false;
         String x="";
        int n=0;
        while (true)
        {
            x = JOptionPane.showInputDialog(this,"Nhập số bàn chuyển","Nhập",JOptionPane.DEFAULT_OPTION);
            
                
                n=Integer.parseInt(x);
                if(n>0 && n<12)
                {
                    
                    if(coHoaDonChua(Integer.parseInt(x))==false)
                    {
                       
                        layMaHoaDon(txtSo2.getText());
                        if(maHoaDon==null)
                        {
                            JOptionPane.showMessageDialog(this,"Không chuyển được","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                        //xoaHoaDonTaiBan(txtSo2.getText());
                        ghiHoaDonChuyenBan(n, maHoaDon);
                        System.out.println("Chuyen sang ban "+n);
                        resetMau();
                        layTrangThai();
                        break;
                    }
                    else
                    {
                        
                        JOptionPane.showMessageDialog(this,"Không chuyển được, bàn có khách rồi !","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                        
                        break;
                    }
                    
                }
               
          
            System.out.println("n = "+n);
            
        }
    }//GEN-LAST:event_btnDoiBan2ActionPerformed

    private void btnDatThem2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDatThem2ActionPerformed
    {//GEN-HEADEREND:event_btnDatThem2ActionPerformed
        // TODO add your handling code here:
        DatThem dt = new DatThem("2",tenDangNhap,hoTen,conn);
        dt.setVisible(true);
        dt.setTitle("Đặt món bàn 2");
        this.setVisible(false);
    }//GEN-LAST:event_btnDatThem2ActionPerformed

    private void btnDatMon3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDatMon3ActionPerformed
    {//GEN-HEADEREND:event_btnDatMon3ActionPerformed
        // TODO add your handling code here:
        DatMon dm = new DatMon("3",tenDangNhap,hoTen,conn);
        dm.setVisible(true);
        dm.setTitle("Đặt món bàn 3");
        this.setVisible(false);
    }//GEN-LAST:event_btnDatMon3ActionPerformed

    private void btnHoaDon3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnHoaDon3ActionPerformed
    {//GEN-HEADEREND:event_btnHoaDon3ActionPerformed
        // TODO add your handling code here:
        HoaDon hd = new HoaDon("3",tenDangNhap,hoTen,conn);
        hd.setVisible(true);
        hd.setTitle("Hoá đơn bàn 3");
        this.setVisible(false);
    }//GEN-LAST:event_btnHoaDon3ActionPerformed

    private void btnDoiBan3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDoiBan3ActionPerformed
    {//GEN-HEADEREND:event_btnDoiBan3ActionPerformed
        // TODO add your handling code here: String x;
         coHoaDon=false;
        String x="";
        int n=0;
        while (true)
        {
            x = JOptionPane.showInputDialog(this,"Nhập số bàn chuyển","Nhập",JOptionPane.DEFAULT_OPTION);
            
                
                n=Integer.parseInt(x);
                if(n>0 && n<12)
                {
                    
                    if(coHoaDonChua(Integer.parseInt(x))==false)
                    {
                       
                        layMaHoaDon(txtSo3.getText());
                        if(maHoaDon==null)
                        {
                            JOptionPane.showMessageDialog(this,"Không chuyển được","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                        //xoaHoaDonTaiBan(txtSo3.getText());
                        ghiHoaDonChuyenBan(n, maHoaDon);
                        System.out.println("Chuyen sang ban "+n);
                        resetMau();
                        layTrangThai();
                        break;
                    }
                    else
                    {
                        
                        JOptionPane.showMessageDialog(this,"Không chuyển được, bàn có khách rồi !","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                       
                        break;
                    }
                    
                }
               
          
            System.out.println("n = "+n);
            
        }
        
        
    }//GEN-LAST:event_btnDoiBan3ActionPerformed

    private void btnDatThem3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDatThem3ActionPerformed
    {//GEN-HEADEREND:event_btnDatThem3ActionPerformed
        // TODO add your handling code here:
         DatThem dt = new DatThem("3",tenDangNhap,hoTen,conn);
        dt.setVisible(true);
        dt.setTitle("Đặt món bàn 3");
        this.setVisible(false);
    }//GEN-LAST:event_btnDatThem3ActionPerformed

    private void btnDatMon4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDatMon4ActionPerformed
    {//GEN-HEADEREND:event_btnDatMon4ActionPerformed
        // TODO add your handling code here:
        DatMon dm = new DatMon("4",tenDangNhap,hoTen,conn);
        dm.setVisible(true);
        dm.setTitle("Đặt món bàn 4");
        this.setVisible(false);
    }//GEN-LAST:event_btnDatMon4ActionPerformed

    private void btnHoaDon4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnHoaDon4ActionPerformed
    {//GEN-HEADEREND:event_btnHoaDon4ActionPerformed
        // TODO add your handling code here:
        HoaDon hd = new HoaDon("4",tenDangNhap,hoTen,conn);
        hd.setVisible(true);
        hd.setTitle("Hoá đơn bàn 4");
        this.setVisible(false);
    }//GEN-LAST:event_btnHoaDon4ActionPerformed

    private void btnDoiBan4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDoiBan4ActionPerformed
    {//GEN-HEADEREND:event_btnDoiBan4ActionPerformed
        // TODO add your handling code here:
         coHoaDon=false;
        String x="";
        int n=0;
        while (true)
        {
            x = JOptionPane.showInputDialog(this,"Nhập số bàn chuyển","Nhập",JOptionPane.DEFAULT_OPTION);

                n=Integer.parseInt(x);
                if(n>0 && n<12)
                {
                    
                    if(coHoaDonChua(Integer.parseInt(x))==false)
                    {
                       
                        layMaHoaDon(txtSo4.getText());
                        if(maHoaDon==null)
                        {
                            JOptionPane.showMessageDialog(this,"Không chuyển được","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                        //xoaHoaDonTaiBan(txtSo4.getText());
                        ghiHoaDonChuyenBan(n, maHoaDon);
                        System.out.println("Chuyen sang ban "+n);
                        resetMau();
                        layTrangThai();
                        break;
                    }
                    else
                    {
                        
                        JOptionPane.showMessageDialog(this,"Không chuyển được, bàn có khách rồi !","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                        
                        break;
                    }
                    
                }
            System.out.println("n = "+n);
        } 
    }//GEN-LAST:event_btnDoiBan4ActionPerformed

    private void btnDatThem4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDatThem4ActionPerformed
    {//GEN-HEADEREND:event_btnDatThem4ActionPerformed
        // TODO add your handling code here:
         DatThem dt = new DatThem("4",tenDangNhap,hoTen,conn);
        dt.setVisible(true);
        dt.setTitle("Đặt món bàn 4");
        this.setVisible(false);
    }//GEN-LAST:event_btnDatThem4ActionPerformed

    private void btnDatMon5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDatMon5ActionPerformed
    {//GEN-HEADEREND:event_btnDatMon5ActionPerformed
        // TODO add your handling code here:
         DatMon dm = new DatMon("5",tenDangNhap,hoTen,conn);
        dm.setVisible(true);
        dm.setTitle("Đặt món bàn 5");
        this.setVisible(false);
    }//GEN-LAST:event_btnDatMon5ActionPerformed

    private void btnHoaDon5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnHoaDon5ActionPerformed
    {//GEN-HEADEREND:event_btnHoaDon5ActionPerformed
        // TODO add your handling code here:
        HoaDon hd = new HoaDon("5",tenDangNhap,hoTen,conn);
        hd.setVisible(true);
        hd.setTitle("Hoá đơn bàn 5");
        this.setVisible(false);
    }//GEN-LAST:event_btnHoaDon5ActionPerformed

    private void btnDoiBan5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDoiBan5ActionPerformed
    {//GEN-HEADEREND:event_btnDoiBan5ActionPerformed
        // TODO add your handling code here:
         coHoaDon=false;
        String x="";
        int n=0;
        while (true)
        {
            x = JOptionPane.showInputDialog(this,"Nhập số bàn chuyển","Nhập",JOptionPane.DEFAULT_OPTION);

                n=Integer.parseInt(x);
                if(n>0 && n<12)
                {
                    
                    if(coHoaDonChua(Integer.parseInt(x))==false)
                    {
                       
                        layMaHoaDon(txtSo5.getText());
                        if(maHoaDon==null)
                        {
                            JOptionPane.showMessageDialog(this,"Không chuyển được","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                        //xoaHoaDonTaiBan(txtSo5.getText());
                        ghiHoaDonChuyenBan(n, maHoaDon);
                        System.out.println("Chuyen sang ban "+n);
                        resetMau();
                        layTrangThai();
                        break;
                    }
                    else
                    {
                        
                        JOptionPane.showMessageDialog(this,"Không chuyển được, bàn có khách rồi !","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                        
                        break;
                    }
                    
                }
            System.out.println("n = "+n);
        } 
    }//GEN-LAST:event_btnDoiBan5ActionPerformed

    private void btnDatThem5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDatThem5ActionPerformed
    {//GEN-HEADEREND:event_btnDatThem5ActionPerformed
        // TODO add your handling code here:
        DatThem dt = new DatThem("5",tenDangNhap,hoTen,conn);
        dt.setVisible(true);
        dt.setTitle("Đặt món bàn 5");
        this.setVisible(false);
    }//GEN-LAST:event_btnDatThem5ActionPerformed

    private void btnDatMon6ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDatMon6ActionPerformed
    {//GEN-HEADEREND:event_btnDatMon6ActionPerformed
        // TODO add your handling code here:
        DatMon dm = new DatMon("6",tenDangNhap,hoTen,conn);
        dm.setVisible(true);
        dm.setTitle("Đặt món bàn 6");
        this.setVisible(false);
    }//GEN-LAST:event_btnDatMon6ActionPerformed

    private void btnHoaDon6ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnHoaDon6ActionPerformed
    {//GEN-HEADEREND:event_btnHoaDon6ActionPerformed
        // TODO add your handling code here:
        HoaDon hd = new HoaDon("6",tenDangNhap,hoTen,conn);
        hd.setVisible(true);
        hd.setTitle("Hoá đơn bàn 6");
        this.setVisible(false);
    }//GEN-LAST:event_btnHoaDon6ActionPerformed

    private void btnDoiBan6ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDoiBan6ActionPerformed
    {//GEN-HEADEREND:event_btnDoiBan6ActionPerformed
        // TODO add your handling code here:
         coHoaDon=false;
        String x="";
        int n=0;
        while (true)
        {
            x = JOptionPane.showInputDialog(this,"Nhập số bàn chuyển","Nhập",JOptionPane.DEFAULT_OPTION);

                n=Integer.parseInt(x);
                if(n>0 && n<12)
                {
                    
                    if(coHoaDonChua(Integer.parseInt(x))==false)
                    {
                       
                        layMaHoaDon(txtSo6.getText());
                        if(maHoaDon==null)
                        {
                            JOptionPane.showMessageDialog(this,"Không chuyển được","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                        //xoaHoaDonTaiBan(txtSo6.getText());
                        ghiHoaDonChuyenBan(n, maHoaDon);
                        System.out.println("Chuyen sang ban "+n);
                        resetMau();
                        layTrangThai();
                        break;
                    }
                    else
                    {
                        
                        JOptionPane.showMessageDialog(this,"Không chuyển được, bàn có khách rồi !","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                        
                        break;
                    }
                    
                }
            System.out.println("n = "+n);
        } 
    }//GEN-LAST:event_btnDoiBan6ActionPerformed

    private void btnDatThem6ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDatThem6ActionPerformed
    {//GEN-HEADEREND:event_btnDatThem6ActionPerformed
        // TODO add your handling code here:
        DatThem dt = new DatThem("6",tenDangNhap,hoTen,conn);
        dt.setVisible(true);
        dt.setTitle("Đặt món bàn 6");
        this.setVisible(false);
    }//GEN-LAST:event_btnDatThem6ActionPerformed

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
            java.util.logging.Logger.getLogger(Lau1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Lau1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Lau1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Lau1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new Lau1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDatMon1;
    private javax.swing.JButton btnDatMon2;
    private javax.swing.JButton btnDatMon3;
    private javax.swing.JButton btnDatMon4;
    private javax.swing.JButton btnDatMon5;
    private javax.swing.JButton btnDatMon6;
    private javax.swing.JButton btnDatThem1;
    private javax.swing.JButton btnDatThem2;
    private javax.swing.JButton btnDatThem3;
    private javax.swing.JButton btnDatThem4;
    private javax.swing.JButton btnDatThem5;
    private javax.swing.JButton btnDatThem6;
    private javax.swing.JButton btnDoiBan1;
    private javax.swing.JButton btnDoiBan2;
    private javax.swing.JButton btnDoiBan3;
    private javax.swing.JButton btnDoiBan4;
    private javax.swing.JButton btnDoiBan5;
    private javax.swing.JButton btnDoiBan6;
    private javax.swing.JButton btnHoaDon1;
    private javax.swing.JButton btnHoaDon2;
    private javax.swing.JButton btnHoaDon3;
    private javax.swing.JButton btnHoaDon4;
    private javax.swing.JButton btnHoaDon5;
    private javax.swing.JButton btnHoaDon6;
    private javax.swing.JButton jButton1;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel pnlBan1;
    private javax.swing.JPanel pnlBan2;
    private javax.swing.JPanel pnlBan3;
    private javax.swing.JPanel pnlBan4;
    private javax.swing.JPanel pnlBan5;
    private javax.swing.JPanel pnlBan6;
    private javax.swing.JLabel txtHoTen;
    private javax.swing.JLabel txtSo1;
    private javax.swing.JLabel txtSo2;
    private javax.swing.JLabel txtSo3;
    private javax.swing.JLabel txtSo4;
    private javax.swing.JLabel txtSo5;
    private javax.swing.JLabel txtSo6;
    private javax.swing.JLabel txtTenDangNhap;
    // End of variables declaration//GEN-END:variables
}
