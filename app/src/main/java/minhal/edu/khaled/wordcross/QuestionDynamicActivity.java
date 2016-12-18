package minhal.edu.khaled.wordcross;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuestionDynamicActivity extends AppCompatActivity {


    TextView tvQuestion;
    TextView bt1, bt2, bt3, bt4, bt5, bt6;
    ArrayList<TextView> freeTextViews = new ArrayList<>();
    ArrayList<Question> questions = new ArrayList<>();
    Question currentQuestion;
    int j = 0;
    int free = 0;
    int score = 0;
    String answers = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_layout_questions);
        tvQuestion = (TextView) findViewById(R.id.tvQuestion);


        bt1 = (TextView) findViewById(R.id.bt1);
        bt2 = (TextView) findViewById(R.id.bt2);
        bt3 = (TextView) findViewById(R.id.bt3);
        bt4 = (TextView) findViewById(R.id.bt4);
        bt5 = (TextView) findViewById(R.id.bt5);
        bt6 = (TextView) findViewById(R.id.bt6);

        freeTextViews.add(bt1);
        freeTextViews.add(bt2);
        freeTextViews.add(bt3);
        freeTextViews.add(bt4);
        freeTextViews.add(bt5);
        freeTextViews.add(bt6);


        addQuestions();
        currentQuestion = questions.get(0);

        setQuestionText(currentQuestion);


    }


    private void addQuestions() {

        Question q1 = new Question("Who invite the electric light bulb ? ", "FFFFFF", 6);
        Question q2 = new Question("What is the capital of Italy ?", "RRRRRR", 4);
        Question q3 = new Question("What was the prior last name of shimon peres ? ", "presky", 3);


        questions.add(q1);
        questions.add(q2);
        questions.add(q3);


    }


    public void setQuestionText(Question q) {

        tvQuestion.setText(q.getQuestText());

    }


    public void letterClicked(View view) {
        TextView tvClicked = (TextView) view;
        String s = tvClicked.getText().toString();
        answers = s + answers;
        freeTextViews.get(free).setText(s);
        free++;
        for (int i = free; i < freeTextViews.size(); i++) {
            if (freeTextViews.get(i).getText().toString().length() >=1){
                free++;
            }
        }
        if (free >= 6)
            if (currentQuestion.getAnsText().equals(answers)) {
                Toast.makeText(QuestionDynamicActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                score++;
                j++;
                free = 0;
                answers = "";
                bt1.setText("");
                bt2.setText("");
                bt3.setText("");
                bt4.setText("");
                bt5.setText("");
                bt6.setText("");
                currentQuestion = questions.get(j);
                setQuestionText(currentQuestion);


            } else {
                Toast.makeText(QuestionDynamicActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                free = 0;
                answers = "";
                bt1.setText("");
                bt2.setText("");
                bt3.setText("");
                bt4.setText("");
                bt5.setText("");
                bt6.setText("");

            }


    }


    public void clear(View view) {

        switch (view.getId()) {

            case R.id.bt1:
                bt1.setText("");
                free = 0;
                break;
            case R.id.bt2:
                bt2.setText("");
                free = 1;
                break;
            case R.id.bt3:
                bt3.setText("");
                free = 2;
                break;
            case R.id.bt4:
                bt4.setText("");
                free = 3;
            case R.id.bt5:
                bt5.setText("");
                free = 4;
                break;
            case R.id.bt6:
                bt6.setText("");
                free = 5;
                break;

        }

    }
}





