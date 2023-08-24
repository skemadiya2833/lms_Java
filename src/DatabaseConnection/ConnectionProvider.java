/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseConnection;

import java.sql.Connection;
import java.sql.*;

/**
 *
 * @author ABC
 */
public class ConnectionProvider {
    public static Connection getCon()
    {
         
        try
        {
            Class.forName(DBInfo.DATABASE_DRIVER);
            Connection con=(Connection) DriverManager.getConnection(DBInfo.DATABASE_URL,DBInfo.DATABASE_USER,DBInfo.DATABASE_PASS);
            return con;
        }
        catch(Exception e)
        {
                    System.out.println(e);
                    return null;
        }
        
    }
    
}
