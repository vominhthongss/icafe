package doanhqtcsdl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class KetNoi
{
    public Connection conn;
    
    public void MySQLConnect()
    {
        conn=null;
        
        try 
        {       
            Class.forName("com.mysql.jdbc.Driver").newInstance(); 
            conn = DriverManager.getConnection("jdbc:mysql://localhost/qlquancafe?"
                    +"user=root&password=");  
            System.out.println("Connected !");     
        } 
        catch (Exception ex) 
        {       
            System.out.println("Can not connect !");       
            ex.printStackTrace();     
        }
    }
}
