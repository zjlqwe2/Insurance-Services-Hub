package dao;

import CommonUtils.JDBCUtils;
import com.alibaba.fastjson.JSONObject;
import domain.UserDomain;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;

public class UserProcessDao {
    static DataSource dataSource = null;

    static Connection conn = null;

    static {

    }

    public static ArrayList<UserDomain>  getUserList() {
        System.out.println("into getUserlist");
        dataSource =JDBCUtils.getDataSource();
        JdbcTemplate template = new JdbcTemplate(dataSource );
        System.out.println("1");

        ArrayList<UserDomain> returnArray =new ArrayList<UserDomain>();
        String sql = "select * from userinfo  ";
        returnArray= (ArrayList<UserDomain>) template.query(sql,new BeanPropertyRowMapper<UserDomain>(UserDomain.class));
        System.out.println("2");

        String jsonxx = JSONObject.toJSONString(returnArray);
//        Unpacking
        System.out.println("jsonxx"+jsonxx);

        return  returnArray;
    }
}
