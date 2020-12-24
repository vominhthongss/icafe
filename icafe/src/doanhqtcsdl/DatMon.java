//CÓ 4 CÂU SQL
package doanhqtcsdl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Siu_Shock
 */
public class DatMon extends javax.swing.JFrame
{

    /**
     * Creates new form DatMon
     * 
     */
    //KetNoi ketNoi = new KetNoi();
    Connection conn;
    Statement stmt = null; 
    ResultSet rs = null; 
    //
    private String soBan;
    private String tenDangNhap;
    private String hoTen;
    String maHoaDon;
    //
    int tongTien=0;
    //
    String tenMon;
    int donGia;
    int soLuong;
    int thanhTien;
    int giaTien;
    //
    String ngay;

    //
    DefaultTableModel dtm;
    //
    boolean coHoaDon;
    public DatMon(String soBan, String tenDangNhap, String hoTen,Connection conn)
    {
        initComponents();
       
        dtm = new DefaultTableModel();
        tbDatMon.setModel(dtm);
        dtm.addColumn("Tên món");
        dtm.addColumn("Đơn giá");
        dtm.addColumn("Số lượng");
        dtm.addColumn("Thành tiền");
        hienNgayThangNam();
       
        this.soBan=soBan;
        this.tenDangNhap=tenDangNhap;
        this.hoTen=hoTen;
        this.conn=conn;
        txtBanSo.setText(soBan);
        txtTenDangNhap.setText(tenDangNhap);
        txtHoTen.setText(hoTen);
        
        layMaHoaDonNgayNhien();
        chamMauTin();
 
    }
    private DatMon()
    {
       initComponents();
    }
    public int layGiaTien(String tenMon)
    {
        
        String sql="SELECT LAY_GIA_TIEN('"+tenMon+"')";
        
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
                giaTien=rs.getInt(1);
            }
        } 
        catch (SQLException ex)
        {    
            System.out.println("Lỗi ở layGiaTien"); 
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
        return giaTien;
    }
    public boolean coHoaDonChua()
            
    {
          //5
        //String sql="SELECT ban FROM trangthai WHERE ban='"+txtBanSo.getText()+"'";
        String sql="SELECT CO_HD_CHUA('"+txtBanSo.getText()+"')";
        
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
    public void chamMauTin()
    {
        ListSelectionModel lsm = tbDatMon.getSelectionModel();
        lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsm.addListSelectionListener(new ListSelectionListener()
        {

            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                int row = tbDatMon.getSelectedRow();
                if(row>-1)
                {
                    tenMon=String.valueOf(tbDatMon.getValueAt(row, 0));
                    donGia=Integer.parseInt(String.valueOf(tbDatMon.getValueAt(row, 1)));
                    soLuong=Integer.parseInt(String.valueOf(tbDatMon.getValueAt(row, 2)));
                    thanhTien=Integer.parseInt(String.valueOf(tbDatMon.getValueAt(row, 3)));
                   
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
        maHoaDon=uuid.substring(0, 8);
        txtMaHoaDon.setText(maHoaDon);
    }
    public void ghiHoaDonVaoBan()
    {
        //3
        //String sql ="INSERT INTO trangthai (ban,mahoadon) VALUES('"+txtBanSo.getText()+"','"+maHoaDon+"')";
        String sql ="CALL GHI_HOA_DON_VAO_BAN('"+txtBanSo.getText()+"','"+maHoaDon+"')";         
        
        try 
        {     
            stmt =conn.createStatement();  
            stmt.execute(sql);

        } 
        catch (SQLException ex)
        {    
            System.out.println("Lỗi ở ghiHoaDonVaoBan"); 
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
   public void ghiTongTien(int tien)
   {
       tongTien+=tien;
       txtTongTien.setText(""+tongTien);
   }
   public void upHoaDon()
   {
       //2
//        String sql ="INSERT INTO hoadon (mahoadon,tongtien,ngay,thanhtoan,tendangnhap)"
//                + "VALUES ('"+maHoaDon+"',"+tongTien+",'"+ngay+"',0,'"+tenDangNhap+"' )";
        String sql ="CALL UP_HOA_DON('"+maHoaDon+"',"+tongTien+",'"+ngay+"',0,'"+tenDangNhap+"' )";
       
               
        
        try 
        {     
            stmt = conn.createStatement();  
            stmt.execute(sql);

        } 
        catch (SQLException ex)
        {    
            System.out.println("Lỗi ở upHoaDon"); 
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
   public void upChiTietHoaDon(String maHoaDon, String tenMon, int donGia, int soLuong,int thanhTien)
   {
              //4
//        String sql ="INSERT INTO chitiethoadon (mahoadon,tenmon,dongia,soluong,thanhtien)"
//                + "VALUES ('"+maHoaDon+"','"+tenMon+"',"+donGia+","+soLuong+","+thanhTien+")";
               
        String sql ="CALL UP_CHI_TIET_HOA_DON('"+maHoaDon+"','"+tenMon+"',"+donGia+","+soLuong+","+thanhTien+")";     
        try 
        {     
            stmt = conn.createStatement();  
            stmt.execute(sql);

        } 
        catch (SQLException ex)
        {    
            System.out.println("Lỗi ở upChiTietHoaDon"); 
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
        btnMatchaDaXay = new javax.swing.JButton();
        btnCookieDaXay = new javax.swing.JButton();
        btnMiloDaXay = new javax.swing.JButton();
        btnCamEp = new javax.swing.JButton();
        btnMiaCam = new javax.swing.JButton();
        btnCacaoDua = new javax.swing.JButton();
        btnCacaoSua = new javax.swing.JButton();
        btnSmothieXoai = new javax.swing.JButton();
        btnDuaHauDao = new javax.swing.JButton();
        btnYaourtDaXay = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDatMon = new javax.swing.JTable();
        jButton13 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtHoTen = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        banso = new javax.swing.JLabel();
        txtBanSo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNgay = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setLocation(new java.awt.Point(350, 50));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("ĐẶT MÓN");

        btnMatchaDaXay.setText("Matcha đá xay");
        btnMatchaDaXay.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnMatchaDaXayActionPerformed(evt);
            }
        });

        btnCookieDaXay.setText("Cookie đá xay");
        btnCookieDaXay.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCookieDaXayActionPerformed(evt);
            }
        });

        btnMiloDaXay.setText("Milo đá xay");
        btnMiloDaXay.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnMiloDaXayActionPerformed(evt);
            }
        });

        btnCamEp.setText("Cam ép");
        btnCamEp.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCamEpActionPerformed(evt);
            }
        });

        btnMiaCam.setText("Mía cam");
        btnMiaCam.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnMiaCamActionPerformed(evt);
            }
        });

        btnCacaoDua.setText("Cacao dừa");
        btnCacaoDua.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCacaoDuaActionPerformed(evt);
            }
        });

        btnCacaoSua.setText("Cacao sữa");
        btnCacaoSua.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCacaoSuaActionPerformed(evt);
            }
        });

        btnSmothieXoai.setText("Smothie xoài");
        btnSmothieXoai.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSmothieXoaiActionPerformed(evt);
            }
        });

        btnDuaHauDao.setText("Dưa hấu đào");
        btnDuaHauDao.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDuaHauDaoActionPerformed(evt);
            }
        });

        btnYaourtDaXay.setText("Yaourt đá xay");
        btnYaourtDaXay.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnYaourtDaXayActionPerformed(evt);
            }
        });

        tbDatMon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Tên món", "Đơn giá", "Số lượng", "Thành tiền"
            }
        ));
        jScrollPane1.setViewportView(tbDatMon);

        jButton13.setText("<<");
        jButton13.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton13ActionPerformed(evt);
            }
        });

        jPanel2.setPreferredSize(new java.awt.Dimension(200, 100));

        txtHoTen.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtHoTen.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtHoTen.setText("...............................................");

        txtTenDangNhap.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtTenDangNhap.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTenDangNhap.setText("...............................................");

        jLabel3.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        jLabel3.setText("Xin chào !");

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
                        .addComponent(jLabel3))
                    .addComponent(txtTenDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHoTen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenDangNhap)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel1.setPreferredSize(new java.awt.Dimension(200, 100));

        banso.setText("Bàn số: ");

        txtBanSo.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        txtBanSo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtBanSo.setText("............................");

        jLabel2.setText("Mã hóa đơn:");

        txtMaHoaDon.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        txtMaHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtMaHoaDon.setText("............................");

        jLabel4.setText("Ngày:");

        txtNgay.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        txtNgay.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtNgay.setText("............................");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(banso, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBanSo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(banso)
                    .addComponent(txtBanSo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaHoaDon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNgay))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jButton12.setText("XOÁ MÓN");
        jButton12.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel5.setText("TỔNG CỘNG:");

        txtTongTien.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        txtTongTien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTongTien.setText("0");

        jButton11.setText("XÁC NHẬN");
        jButton11.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel6.setText("VND");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12)
                    .addComponent(jLabel5)
                    .addComponent(txtTongTien)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnCacaoDua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnMatchaDaXay, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnCacaoSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCookieDaXay, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSmothieXoai, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMiloDaXay, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCamEp, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDuaHauDao, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnMiaCam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnYaourtDaXay, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton13)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMatchaDaXay, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCookieDaXay, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMiloDaXay, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCamEp, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMiaCam, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCacaoDua, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCacaoSua, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnYaourtDaXay, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSmothieXoai, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDuaHauDao, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton11ActionPerformed
    {//GEN-HEADEREND:event_jButton11ActionPerformed
        // TODO add your handling code here:
        if(coHoaDonChua()==false)
        {
            
            upHoaDon();       
            for (int row=0;row<tbDatMon.getRowCount();row++)
            {
                String tenmon = String.valueOf(tbDatMon.getValueAt(row, 0));
                int dongia = Integer.parseInt(String.valueOf(tbDatMon.getValueAt(row, 1)));
                int soluong = Integer.parseInt(String.valueOf(tbDatMon.getValueAt(row, 2)));
                int thanhtien = Integer.parseInt(String.valueOf(tbDatMon.getValueAt(row, 3)));
            
                upChiTietHoaDon(maHoaDon,tenmon,dongia,soluong,thanhtien);
                
            }
            JOptionPane.showMessageDialog(this,"Đã ghi thành công hóa đơn","Thông báo",JOptionPane.INFORMATION_MESSAGE);  
            ghiHoaDonVaoBan();
            
            //reset lại bảng
            tongTien=0;
            txtTongTien.setText("0");
            while (dtm.getRowCount()!=0)
            {
                dtm.removeRow(0);
            }
            
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bàn đã có hóa đơn... vui lòng chọn ĐẶT THÊM","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
        
        
        
     
    }//GEN-LAST:event_jButton11ActionPerformed

    private void btnMatchaDaXayActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnMatchaDaXayActionPerformed
    {//GEN-HEADEREND:event_btnMatchaDaXayActionPerformed
        // TODO add your handling code here:
        int n=0;
        String x ;
        
        while (true)
        {
            
            x = JOptionPane.showInputDialog(this,"Nhập số lượng","Nhập",JOptionPane.DEFAULT_OPTION);
         
            
        try
        {
            Integer.parseInt(x);      
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Phải là số !");
        }
           n = Integer.parseInt(x);               
            if(n>0)
            {
                break;
            }


            
        }
        String ten=btnMatchaDaXay.getText();
        int gia=layGiaTien(ten);
        dtm.addRow(new Object []{ten,gia,n,n*gia});
        ghiTongTien(n*gia);
        

        
           
    }//GEN-LAST:event_btnMatchaDaXayActionPerformed

    private void btnCookieDaXayActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCookieDaXayActionPerformed
    {//GEN-HEADEREND:event_btnCookieDaXayActionPerformed
        // TODO add your handling code here:
        int n=0;
        String x; 
        while (true)
        {
            x = JOptionPane.showInputDialog(this,"Nhập số lượng","Nhập",JOptionPane.DEFAULT_OPTION);
             try
        {
            Integer.parseInt(x);      
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Phải là số !");
        }
                n = Integer.parseInt(x);               
                if(n>0)
                {
                    break;
                }
          
        }
        String ten=btnCookieDaXay.getText();
        int gia=layGiaTien(ten);
        dtm.addRow(new Object []{ten,gia,n,n*gia});
        ghiTongTien(n*gia);

    }//GEN-LAST:event_btnCookieDaXayActionPerformed

    private void btnMiloDaXayActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnMiloDaXayActionPerformed
    {//GEN-HEADEREND:event_btnMiloDaXayActionPerformed
        // TODO add your handling code here:
        int n=0;
        String x; 
        while (true)
        {
            x = JOptionPane.showInputDialog(this,"Nhập số lượng","Nhập",JOptionPane.DEFAULT_OPTION);
             try
        {
            Integer.parseInt(x);      
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Phải là số !");
        }
                n = Integer.parseInt(x);               
                if(n>0)
                {
                    break;
                }
           
        }
        String ten=btnMiloDaXay.getText();
        int gia=layGiaTien(ten);
        dtm.addRow(new Object []{ten,gia,n,n*gia});
        ghiTongTien(n*gia);
        
    }//GEN-LAST:event_btnMiloDaXayActionPerformed

    private void btnCamEpActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCamEpActionPerformed
    {//GEN-HEADEREND:event_btnCamEpActionPerformed
        // TODO add your handling code here:
        int n=0;
        String x; 
        while (true)
        {
            x = JOptionPane.showInputDialog(this,"Nhập số lượng","Nhập",JOptionPane.DEFAULT_OPTION);
            try
        {
            Integer.parseInt(x);      
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Phải là số !");
        }
                n = Integer.parseInt(x);               
                if(n>0)
                {
                    break;
                }
           
        }
        String ten=btnCamEp.getText();
        int gia=layGiaTien(ten);
        dtm.addRow(new Object []{ten,gia,n,n*gia});
        ghiTongTien(n*gia);
    }//GEN-LAST:event_btnCamEpActionPerformed

    private void btnMiaCamActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnMiaCamActionPerformed
    {//GEN-HEADEREND:event_btnMiaCamActionPerformed
        // TODO add your handling code here:
        int n=0;
        String x; 
        while (true)
        {
            x = JOptionPane.showInputDialog(this,"Nhập số lượng","Nhập",JOptionPane.DEFAULT_OPTION);
              try
        {
            Integer.parseInt(x);      
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Phải là số !");
        }
                n = Integer.parseInt(x);               
                if(n>0)
                {
                    break;
                }
          
        }
        String ten=btnMiaCam.getText();
        int gia=layGiaTien(ten);
        dtm.addRow(new Object []{ten,gia,n,n*gia});
        ghiTongTien(n*gia);
    }//GEN-LAST:event_btnMiaCamActionPerformed

    private void btnCacaoDuaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCacaoDuaActionPerformed
    {//GEN-HEADEREND:event_btnCacaoDuaActionPerformed
        // TODO add your handling code here:
        int n=0;
        String x; 
        while (true)
        {
            x = JOptionPane.showInputDialog(this,"Nhập số lượng","Nhập",JOptionPane.DEFAULT_OPTION);
              try
        {
            Integer.parseInt(x);      
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Phải là số !");
        }
                n = Integer.parseInt(x);               
                if(n>0)
                {
                    break;
                }
        
        }
        String ten=btnCacaoDua.getText();
        int gia=layGiaTien(ten);
        dtm.addRow(new Object []{ten,gia,n,n*gia});
        ghiTongTien(n*gia);
    }//GEN-LAST:event_btnCacaoDuaActionPerformed

    private void btnCacaoSuaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCacaoSuaActionPerformed
    {//GEN-HEADEREND:event_btnCacaoSuaActionPerformed
        // TODO add your handling code here:
        int n=0;
        String x; 
        while (true)
        {
            x = JOptionPane.showInputDialog(this,"Nhập số lượng","Nhập",JOptionPane.DEFAULT_OPTION);
             try
        {
            Integer.parseInt(x);      
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Phải là số !");
        }
                n = Integer.parseInt(x);               
                if(n>0)
                {
                    break;
                }
           
        }
        String ten=btnCacaoSua.getText();
        int gia=layGiaTien(ten);
        dtm.addRow(new Object []{ten,gia,n,n*gia});
        ghiTongTien(n*gia);
    }//GEN-LAST:event_btnCacaoSuaActionPerformed

    private void btnSmothieXoaiActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSmothieXoaiActionPerformed
    {//GEN-HEADEREND:event_btnSmothieXoaiActionPerformed
        // TODO add your handling code here:
        int n=0;
        String x; 
        while (true)
        {
            x = JOptionPane.showInputDialog(this,"Nhập số lượng","Nhập",JOptionPane.DEFAULT_OPTION);
              try
        {
            Integer.parseInt(x);      
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Phải là số !");
        }
                n = Integer.parseInt(x);               
                if(n>0)
                {
                    break;
                }
          
        }
        String ten=btnSmothieXoai.getText();
        int gia=layGiaTien(ten);
        dtm.addRow(new Object []{ten,gia,n,n*gia});
        ghiTongTien(n*gia);
    }//GEN-LAST:event_btnSmothieXoaiActionPerformed

    private void btnDuaHauDaoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDuaHauDaoActionPerformed
    {//GEN-HEADEREND:event_btnDuaHauDaoActionPerformed
        // TODO add your handling code here:
        int n=0;
        String x; 
        while (true)
        {
            x = JOptionPane.showInputDialog(this,"Nhập số lượng","Nhập",JOptionPane.DEFAULT_OPTION);
              try
        {
            Integer.parseInt(x);      
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Phải là số !");
        }
                n = Integer.parseInt(x);               
                if(n>0)
                {
                    break;
                }
          
        }
        String ten=btnDuaHauDao.getText();
        int gia=layGiaTien(ten);
        dtm.addRow(new Object []{ten,gia,n,n*gia});
        ghiTongTien(n*gia);
    }//GEN-LAST:event_btnDuaHauDaoActionPerformed

    private void btnYaourtDaXayActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnYaourtDaXayActionPerformed
    {//GEN-HEADEREND:event_btnYaourtDaXayActionPerformed
        // TODO add your handling code here:
        int n=0;
        String x; 
        while (true)
        {
            x = JOptionPane.showInputDialog(this,"Nhập số lượng","Nhập",JOptionPane.DEFAULT_OPTION);
             try
        {
            Integer.parseInt(x);      
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Phải là số !");
        }
                n = Integer.parseInt(x);               
                if(n>0)
                {
                    break;
                }
          
        }
        String ten=btnYaourtDaXay.getText();
        int gia=layGiaTien(ten);
        dtm.addRow(new Object []{ten,gia,n,n*gia});
        ghiTongTien(n*gia);
  
    }//GEN-LAST:event_btnYaourtDaXayActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton12ActionPerformed
    {//GEN-HEADEREND:event_jButton12ActionPerformed
        // TODO add your handling code here:
        int row = tbDatMon.getSelectedRow();
        if(row<=-1)
        {
            JOptionPane.showMessageDialog(this,"Chưa chọn dòng xóa !","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            int x = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa ?");       
            if(x==JOptionPane.YES_OPTION)
            {   
                
                         
                dtm.removeRow(row);
                ghiTongTien((-1)*thanhTien);
            
            }
            
        }
        
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton13ActionPerformed
    {//GEN-HEADEREND:event_jButton13ActionPerformed
        // TODO add your handling code here:
        
        String ban;
        ban=txtBanSo.getText();
        
        if(ban.equals("Mang đi"))
        {
            ban="0";
        }
        
        
        if(ban.equals("0"))
        {
                MangDi md = new MangDi(tenDangNhap,hoTen,conn);
                md.setVisible(true);
                System.out.println("Về mang đi");
                this.setVisible(false);
                
        }
        if(Integer.parseInt(ban)>=1 && Integer.parseInt(ban) <=6)
        {
            System.out.println("Về lau 1");
            Lau1 lau1 = new Lau1(tenDangNhap,hoTen,conn);
            lau1.setVisible(true);
            this.setVisible(false);
        }
        if(Integer.parseInt(ban)>=7 && Integer.parseInt(ban) <=12)
        {
            System.out.println("Về lau 2");
            Lau2 lau2 = new Lau2(tenDangNhap,hoTen,conn);
            lau2.setVisible(true);
            this.setVisible(false);
            
        }
      
        
    }//GEN-LAST:event_jButton13ActionPerformed

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
            java.util.logging.Logger.getLogger(DatMon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(DatMon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(DatMon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(DatMon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new DatMon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel banso;
    private javax.swing.JButton btnCacaoDua;
    private javax.swing.JButton btnCacaoSua;
    private javax.swing.JButton btnCamEp;
    private javax.swing.JButton btnCookieDaXay;
    private javax.swing.JButton btnDuaHauDao;
    private javax.swing.JButton btnMatchaDaXay;
    private javax.swing.JButton btnMiaCam;
    private javax.swing.JButton btnMiloDaXay;
    private javax.swing.JButton btnSmothieXoai;
    private javax.swing.JButton btnYaourtDaXay;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbDatMon;
    private javax.swing.JLabel txtBanSo;
    private javax.swing.JLabel txtHoTen;
    private javax.swing.JLabel txtMaHoaDon;
    private javax.swing.JLabel txtNgay;
    private javax.swing.JLabel txtTenDangNhap;
    private javax.swing.JLabel txtTongTien;
    // End of variables declaration//GEN-END:variables
}
