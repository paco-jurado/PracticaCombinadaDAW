//DBMetaData.java

import java.io.*;
import java.sql.*;

public class DBMetaData {

	public static void main (String args[ ]) throws SQLException,ClassNotFoundException {

		String url = "jdbc:mysql://localhost:3306/sakila", username = "root", password = "29701349";
		Connection con=DriverManager.getConnection(url,username,password);
		
		DatabaseMetaData dbmd=con.getMetaData();

		System.out.println("database version:" + dbmd.getDatabaseProductVersion() + "\n");
		System.out.println("database name:"+dbmd.getDatabaseProductName()+"\n");
		System.out.println("sql keywords:" +dbmd.getSQLKeywords() +"\n");
		System.out.println("numeric functions: "+dbmd.getNumericFunctions () +"\n") ;
		System.out.println("String functions: "+dbmd.getStringFunctions ()+"\n" );

		System.out.println("System functions:" +dbmd.getSystemFunctions()+"\n") ;
		System.out.println("Searc string Escape:" +dbmd.getSearchStringEscape ()+"\n");
		System.out.println("getMaxRowSize: "+dbmd.getMaxRowSize()+"\n");
		System.out.println("getMaxstatementLength:"+dbmd.getMaxStatementLength() +"\n");
		System.out.println("get maximum tables in a select query:"+dbmd.getMaxTablesInSelect()+"\n");
		System.out.println("get maximum length of table name:"+dbmd.getMaxTableNameLength()+"\n");
		System.out.println("jdbcapi version is :"+dbmd.getJDBCMajorVersion()+"\n");

	}

}
