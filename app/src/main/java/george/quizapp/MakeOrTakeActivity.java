package george.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MakeOrTakeActivity extends AppCompatActivity {
    Button takeQuiz, makeQuiz;
    Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_or_take);

        takeQuiz = (Button) findViewById(R.id.btnTakeQuiz);
        makeQuiz = (Button) findViewById(R.id.btnMakeQuiz);
        quiz = null;
        takeQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTakeQuiz();

            }
        });

    }
    public void onTakeQuiz(){
        Intent takeQuizIntent = new Intent(getApplicationContext(), QuizActivity.class);

        //takeQuizIntent.putExtra("quiz", quiz);
        this.startActivity(takeQuizIntent);

    }

}
