package domain;

public class UserDomain {
    private int userid;
    private String username;
    private String password;
    private String repassword;
    private String usertype;
    private String status;

    public UserDomain(int userid, String username, String password, String repassword, String usertype, String status) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.repassword = repassword;
        this.usertype = usertype;
        this.status = status;
    }
    public UserDomain(){

    }


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
