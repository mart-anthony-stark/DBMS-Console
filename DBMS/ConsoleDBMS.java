package DBMS;

import java.sql.*;
/*Import Scanner Class so as to take input from the user*/
import java.util.Scanner;

public class ConsoleDBMS {

	static Connection connection;
	static Scanner scan;
	static Statement statement;
	static ResultSet resultSet;
	static ResultSetMetaData resultSetMetaData;

	public static void main(String args[]) throws SQLException {


		scan = new Scanner(System.in);

		try {
			
		
			
			System.out.println("Connection established");
			
		
			statement = connection.createStatement();

		} catch (Exception e) {

			System.out.println("Check your connection again.");

		}

		while (true) {

		
			executeQuery();

		}

	}

	public static void executeQuery() {

		System.out.print("$> ");

		try {
			

			String query = scan.nextLine();
			

			String[] queryBifercation = query.split(" ");

			
			if (queryBifercation[0].equalsIgnoreCase("SELECT") || queryBifercation[0].equalsIgnoreCase("SHOW")) {

				resultSet = statement.executeQuery(query);
				
		
				printResultSet();

			} else {
				
			
				statement.executeUpdate(query);

			}

		} catch (Exception e) {

			System.out.println("Statement Cannot be executed.");

		}

	}

	public static void printResultSet() throws SQLException {
		
	
		resultSetMetaData = resultSet.getMetaData();
		
	
		for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
		
			if (i > 1)
				System.out.print("\t");
			String columnName = resultSetMetaData.getColumnName(i);
			System.out.print(columnName);
		}
		System.out.println();
		while (resultSet.next()) {
			for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
				if (i > 1)
					System.out.print("\t");
				String columnValue = resultSet.getString(i);
				System.out.print(columnValue);
			}
			System.out.println("");
		}
	}
	
}

class GetConnection {

	static Connection conn;
	static String USERNAME;
	static String PASSWORD;
	static String CONNECTION;

	public static Connection getConnection() {
		
		USERNAME = "root"; // enter username here
		PASSWORD = ""; // enter password here 
		CONNECTION = "jdbc:mysql://localhost/dbms"; // link

		try {
			
			
			Class.forName("com.mysql.jdbc.Driver");
			
		
			conn = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
			
		
			return conn;

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}