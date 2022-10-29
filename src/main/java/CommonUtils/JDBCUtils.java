package CommonUtils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
    1. Declare static data source member variables
    2. Create a connection pool object
    3. Define a public method of getting the data source
    4. Define the method to get the connection object
    5. Define the method to close the resource
 */

public class JDBCUtils {
	// 1.	Declare static data source member variables
	private static DataSource ds;

	// 2. Create a connection pool object
	static {
        System.out.println("into the static code block");
		// Load data from configuration file
		InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream(
		        "druid_DataBaseConnectSet_Junlong.properties");

		Properties pp = new Properties();
		try {
            System.out.println("XX");
			pp.load(is);
            System.out.println("XX");
            System.out.println("pp"+pp.getProperty("url"));
			// Create a connection pool, using the parameters in the configuration file
			ds = DruidDataSourceFactory.createDataSource(pp);
		} catch (Exception e) {
			e.printStackTrace();
            System.out.println("connect mysql worry");
		}
	}


	// 3. Define a public method to get the data source
	public static DataSource getDataSource() {
		return ds;
	}

	// 4.  Define the method to get the connection object
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	// 5.Define a method to close a resource
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}

	// 6.Overload the close method
	public static void close(Connection conn, Statement stmt) {
		close(conn, stmt, null);
	}
}
