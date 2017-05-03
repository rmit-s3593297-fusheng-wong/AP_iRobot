package jitender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	public Connection SQLiteConnection() throws SQLException, ClassNotFoundException{
		Class.forName("org.sqlite.JDBC");
		Connection con=DriverManager.getConnection("jdbc:sqlite:Ozlympic.db");
		return con;
	}
}
