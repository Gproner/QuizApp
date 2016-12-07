package george.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {
    TextView question, iscorrect;
    Quiz quiz;
    Button answer;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Intent intent = getIntent();
        quiz = intent.getParcelableExtra("quiz");
        question = (TextView) findViewById(R.id.txtQuestion);
        question.setText(quiz.questionList.get(0));
        count = 0;
        answer = (Button) findViewById(R.id.btnSaveAnswer);
        iscorrect = (TextView) findViewById(R.id.txtIsAnswerCorrect);

        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public boolean AnswerQuestion(){

        RadioGroup answer = (RadioGroup) findViewById(R.id.Answer);
        int userAnswer = answer.getCheckedRadioButtonId();
        if(userAnswer == quiz.multipleChoiceAnswerList.get(count)){
            iscorrect.setText("ANSWER CORRECT!");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.getLocalizedMessage();
            }
            return true;
        }
        else{
            iscorrect.setText("WRONG ANSWER!");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.getLocalizedMessage();
            }
            return false;
        }

    }
}
