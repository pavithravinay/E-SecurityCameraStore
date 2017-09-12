import java.sql.*; 

public class DBConnection {  

    private Connection conn;  
    private String url ="jdbc:oracle:thin:@fourier.cs.iit.edu:1521:ORCL"; 
    private String user = "pvinay";;  
    private String password = "Donotreveal";
    
    public Connection getDBConnection()  {  
        try {  
        	Class.forName("oracle.jdbc.driver.OracleDriver");
        }
	    catch (ClassNotFoundException e) {  
	        e.printStackTrace();
	    }
	    
	    try {
	        url = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:ORCL";
	        user = "pvinay";
	        password = "Donotreveal";
	        conn = DriverManager.getConnection(url, user, password);
	    }
        catch (SQLException e) {
            e.printStackTrace();
        }
		return conn;
    }
}