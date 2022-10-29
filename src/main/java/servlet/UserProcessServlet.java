package servlet;

import com.alibaba.fastjson.JSONObject;
import dao.UserProcessDao;
import domain.ResultInfo;
import domain.UserDomain;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
//sdf

@WebServlet("/UserProcess")
public class UserProcessServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("into UserProcess");
        //text: build json type and return the  data
        //1.build response info object
        ResultInfo resultBox = new ResultInfo();
        //2.set erroMessage of response
        resultBox.setFlag(1);
        resultBox.setErrorMsg("text!");

        ArrayList<UserDomain> returnArray =UserProcessDao.getUserList();
        resultBox.setData(returnArray);
        String reJson = JSONObject.toJSONString(resultBox);
        System.out.println("jsonxx"+reJson);
        response.setContentType("application/JSON");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(reJson);

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
