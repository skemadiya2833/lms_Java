/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseConnection;

/**
 *
 * @author ABC
 */
public interface DBInfo {
        public static final String DATABASE_DRIVER="com.mysql.cj.jdbc.Driver";
	public static final String DATABASE_URL="jdbc:mysql://localhost:3306/library_ms";
	public static final String DATABASE_USER="root";
	public static final String DATABASE_PASS="root";    
}
