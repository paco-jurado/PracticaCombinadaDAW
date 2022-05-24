
//ResuleSetMetaDataTest
import java.sql.*;
import java.util.*;

public class ResultSetMetaDataTest {
	private static final String SQL = "SELECT actor_id, first_Name, last_Name FROM actor WHERE actor_id=? AND first_Name LIKE ?";
	
	public static void main(String args[]) throws SQLException,ClassNotFoundException  {
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
	    ParameterMetaData pmd = null;
	    int i,j;
		
		String url = "jdbc:mysql://localhost:3306/sakila", username = "root", password = "29701349";

		try (Connection cn = DriverManager.getConnection(url, username, password);) {

			DatabaseMetaData dbmd = cn.getMetaData();		// Info del SGBD  y la conexión

			System.out.println("Database name: "+dbmd.getDatabaseProductName()+" Database version: " + dbmd.getDatabaseProductVersion());
			
			int majorVersion = dbmd.getDatabaseMajorVersion();
			int minorVersion = dbmd.getDatabaseMinorVersion();
			System.out.printf("Database Major y Minus version: %d.%d\n", majorVersion, minorVersion);
			
			String driverName = dbmd.getDriverName();
			String driverVersion = dbmd.getDriverVersion();
			System.out.println("Driver Info: " + driverName + " - " + driverVersion);

			int jdbcMajorVersion = dbmd.getJDBCMajorVersion();
			int jdbcMinorVersion = dbmd.getJDBCMinorVersion();
			System.out.println("JDBC Version: " + jdbcMajorVersion + "." + jdbcMinorVersion);
			
			System.out.println("get maximum tables in a select query: "+dbmd.getMaxTablesInSelect());
			System.out.println("get maximum length of table name: "+dbmd.getMaxTableNameLength());
			System.out.println("get maximun statement Length: "+dbmd.getMaxStatementLength());
			
			System.out.println("sql keywords: " +dbmd.getSQLKeywords());
			
			System.out.println("numeric functions: "+dbmd.getNumericFunctions ());
			System.out.println("String functions: "+dbmd.getStringFunctions ());
			
			System.out.println("System functions: " +dbmd.getSystemFunctions());
			System.out.println("Searc string Escape:" +dbmd.getSearchStringEscape () + "getMaxRowSize: "+dbmd.getMaxRowSize());
			
	
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter table name: ");  String tname=sc.next(); sc.close();

			st=cn.createStatement();
			rs=st.executeQuery ("select * from "+tname);
			ResultSetMetaData rsmd=rs.getMetaData();						// Info de la tabla
			int colcnt=rsmd.getColumnCount();								
			System.out.println("Catalog Name: " + rsmd.getCatalogName(1));
			System.out.println("Table Name: " + rsmd.getTableName(1)+" with Number of Columns: " + colcnt);

			System.out.print("CON rsmd.getColumnLabel(i)--> Columns present in the table") ;
			System.out.println("y CON rsmd.getColumnTypeName(i)--> Datatypes of the columns are: ");
			System.out.println("\n");
			for(i=1;i<=colcnt;i++) {
				System.out.println("Class Name: " + rsmd.getColumnClassName(i));
				System.out.print(" Name: "+ rsmd.getColumnLabel(i)+" " ) ;
				System.out.print(" Type: "+rsmd.getColumnTypeName(i)+",  ");
			}

			System.out.println("\n");

			System.out.println("rs.getString(x)...records of the table are: ");
			j =0;
			while(rs.next() && j<10){
				for (i=1;  i<=colcnt; ++i){
					System.out.print(rs.getString(i) +"\t\t\t") ;
				}
				System.out.println(""); j++;
			}

			pstmt = cn.prepareStatement(SQL);
			pstmt.setInt(1,5);
			pstmt.setString(2, "S*");
			pstmt.execute();
			
	        pmd = pstmt.getParameterMetaData();
	        if (pmd != null) {
	        	int np =pmd.getParameterCount();
	        	System.out.println("Prepared Statement Parameter Count: " + np);
//	            for (i=1; i<=np ;i++) {  NO VA 
//	            	System.out.println("Parameter Class: " + pmd.getParameterClassName(i));
//	            	System.out.println("Parameter Type: " + pmd.getParameterTypeName(i));
//	            	System.out.println("Parameter Type: " + pmd.getParameterType(i));
//	            }
	        } 
	        else {
	            System.out.println("ParameterMetadata not supported by the database");
	        }

			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}  finally {
			rs.close();
			st.close();
		}
	}
}