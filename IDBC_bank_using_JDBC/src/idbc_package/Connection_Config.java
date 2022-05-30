package idbc_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection_Config
{
	Connection con=null;
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		//System.out.println("Driver loded Successfull");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/idbcbankdatabase","root","Ateek123@");
		//System.out.println("Connection Established Successfully");
		return con;
	}
}
