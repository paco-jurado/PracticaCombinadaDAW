import java.sql.*;
 
/**
 * This program shows an example of reading database metadata
 * such as product name and version.
 *
 * @author www.codejava.net
 */
public class ReadDatabaseInfoExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/sakila";
        String username = "root";
        String password = "29701349";
 
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
 
            DatabaseMetaData meta = conn.getMetaData();
 
            String productName = meta.getDatabaseProductName();
            String productVersion = meta.getDatabaseProductVersion();
            System.out.println(productName +  " " + productVersion);
 
            int majorVersion = meta.getDatabaseMajorVersion();
            int minorVersion = meta.getDatabaseMinorVersion();
            System.out.printf("Database version: %d.%d\n", majorVersion, minorVersion);
 
            String driverName = meta.getDriverName();
            String driverVersion = meta.getDriverVersion();
 
            System.out.println("Driver Info: " + driverName + " - " + driverVersion);
 
            int jdbcMajorVersion = meta.getJDBCMajorVersion();
            int jdbcMinorVersion = meta.getJDBCMinorVersion();
 
            System.out.println("JDBC Version: " + jdbcMajorVersion + "." + jdbcMinorVersion);
 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
 
    }
}
