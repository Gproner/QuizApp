package george.quizapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by georg on 2016-12-06.
 */

public class Quiz implements Parcelable{
    ArrayList<String> questionList;
    ArrayList<Integer> multipleChoiceAnswerList;

    public Quiz(ArrayList<String> qL,ArrayList<Integer> mCAL ){
        questionList = qL;
        multipleChoiceAnswerList = mCAL;
    }
    public Quiz(String... lineIn){
    for(int i = 0; i< lineIn.length; i++) {
        String[] splitLine = lineIn[i].split(":");
        this.questionList.add(splitLine[0]);
        this.multipleChoiceAnswerList.add(Integer.parseInt(splitLine[0]));
    }
    }

    protected Quiz(Parcel in) {
        questionList = in.createStringArrayList();
    }

    public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };

    public void AddMCquestion(String Q, int A){
        questionList.add(Q);
        multipleChoiceAnswerList.add(A);
    }

    public boolean isMCAnswerTrue(int index, int answer){
        if(multipleChoiceAnswerList.get(index) == answer){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(questionList);
    }
}
