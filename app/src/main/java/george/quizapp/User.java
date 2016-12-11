package george.quizapp;

import java.util.ArrayList;

/**
 * Created by georg on 2016-12-11.
 */

public class User {
    private String name, password;
    public User(String n, String p){
        name = n;
        password = p;
    }
    public User(String LineIn){
        String[] splitLine = LineIn.split(",");
        this.name = splitLine[0];
        this.password = splitLine[1];

    }
    public String GetName(){
        return name;
    }
    public String GetPassword(){
        return password;
    }
}
