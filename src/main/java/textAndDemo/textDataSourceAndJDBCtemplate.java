package textAndDemo;
import CommonUtils.JDBCUtils;
import com.alibaba.fastjson.JSONObject;
import domain.TestProduct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class textDataSourceAndJDBCtemplate {
    static DataSource dataSource = null;
    static Connection conn = null;
    public static void main(String[] args) {
        dataSource = JDBCUtils.getDataSource();
        try {
             conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        testJdbcTemp();
        testJdbcTempByMultiple();
        getDiscount();
    }
    public static void testJdbcTemp() {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        ArrayList<TestProduct> returnArray =new ArrayList<TestProduct>();
        String sql = "select * from productdetails where quantity = ? ";
        returnArray= (ArrayList<TestProduct>) template.query(sql,new BeanPropertyRowMapper<TestProduct>(TestProduct.class),100);
        String jsonxx = JSONObject.toJSONString(returnArray);
//        Unpacking
        System.out.println("jsonxx"+jsonxx);
//        return template.query(sql,new BeanP`ropertyRowMapper<RouteImg>(RouteImg.class),rid);
    }
    public static void testJdbcTempByMultiple() {
        ArrayList<TestProduct> returnArray =new ArrayList<TestProduct>();
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        try {
            String sql = " select * from productdetails where productPrice = ? and productCondition = ?";
            returnArray = (ArrayList<TestProduct>) template.query(sql, new BeanPropertyRowMapper<TestProduct>(TestProduct.class), "189.99", "New");
            String jsonxx = JSONObject.toJSONString(returnArray);
            System.out.println("jsonxx"+jsonxx);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public static void getDiscount() {
        ArrayList<TestProduct> returnArray =new ArrayList<TestProduct>();
        try {
            String selectCustomerQuery = "select * from  productdetails";
            PreparedStatement pst = conn.prepareStatement(selectCustomerQuery);
            ResultSet rs = pst.executeQuery();
            System.out.println("befor into the loop");
            while (rs.next()) {
                System.out.println("into loop");
                System.out.println("sdf"+rs.getString("productName"));
                System.out.println("sdf"+rs.getString("productPrice"));
                returnArray.add(new TestProduct( rs.getString("productName"),Double.valueOf( rs.getString("productPrice")) ,
                        Integer.parseInt(rs.getString("quantity"))));
            }
            pst.execute();
        } catch (Exception e) {
            System.out.println("there is erro ");
        }
        String jsonxx = JSONObject.toJSONString(returnArray);
        System.out.println("jsonxx"+jsonxx);
    }
}
