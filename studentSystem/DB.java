package studentSystem;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java. sql.Statement;

public class DB {
	public static final String DBURL = "jdbc:oracle:thin:@localhost:1521:XE";
	public static final String DBUSER = "jas2";
	public static final String DBPASS = "123";
	
	public static Connection getConnection() throws SQLException{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con=DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		Statement statement = con.createStatement();
		return con;
	}
}

