package george.quizapp;

import static android.R.attr.name;

/**
 * Created by Calvin Lai on 2016-12-06.
 */
public class Users {
    private String studentid;
    private String password;
    private long dbid;

    //upon saving
    public Users(String studentid, String password, long dbid) {
        this.studentid = studentid;
        this.dbid = dbid;
        this.password = password;

    }


    //getters and setters


    public String getStudentid() {
        return studentid;
    }


    public String getPassword() {
        return password;
    }

    public long getDbid() {
        return dbid;
    }


    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDbid(long dbid) {
        this.dbid = dbid;
    }

    public String tostring(){
        return name + " , " + password + " , " + dbid;
    }
}
