package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {
    
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            conn = DB.getConnection();
            
            
           /* st = conn.prepareStatement(
                "INSERT INTO seller " +
                "(Name, Email, BirthDate, BaseSalary, DepartmentId) " +  
                "VALUES " +
                "(?, ?, ?, ?, ?)"  
            , Statement.RETURN_GENERATED_KEYS);
            
            st.setString(1, "Lucian Alfred");
            st.setString(2, "la@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("11/11/2001").getTime()));
            st.setDouble(4, 3000.0);
            st.setInt(5, 4);
            */
            st = conn.prepareStatement(
            		"insert into department (Name) values ('D1'), ('D2')", Statement.RETURN_GENERATED_KEYS
            		);
            
            
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
            	ResultSet rs = st.getGeneratedKeys();
            	while (rs.next()) {
            		int id = rs.getInt(1);
            		System.out.print("Done! Id = "+id+"\n");
            		
            	}
            	System.out.println("Done! Rows affected: " + rowsAffected);
            }else {
            	System.out.println("Done! No Rows affected: ");
            }
            
            
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        } 
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
