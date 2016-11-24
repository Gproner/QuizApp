package george.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    
    EditText edtUsername, edtPassword;
    Button btnLogIn;
    String username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnLogIn = (Button) findViewById(R.id.btnLogIn);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtUsername = (EditText) findViewById(R.id.edtUserName);
        
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username= getUserName();
                password = getPassword();
                Intent intent = new Intent(this, OptionPageFragment.class);

            }
        });

    }
    public String getUserName(){
        return edtUsername.getText().toString();
    }
    public String getPassword(){
        return edtPassword.getText().toString();
    }
    
    boolean DoesUserExist(String un,String pass){
        return true;
        //// TODO: 2016-11-14 compare password and username to either SQLite database or similar 
    }
    
}
