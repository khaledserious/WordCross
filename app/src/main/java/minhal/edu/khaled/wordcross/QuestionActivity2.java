package minhal.edu.khaled.wordcross;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuestionActivity2 extends AppCompatActivity {


    TextView tvQuestion;

    ArrayList<TextView> freeTextViews = new ArrayList<>();
    ArrayList<Question> questions = new ArrayList<>();
    Question currentQuestion;
    int j = 0;
    int free = 0;
    int score = 0;
    String answer = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trivia_dynamic_layout);
        tvQuestion = (TextView) findViewById(R.id.tvQuestion);


        addQuestions();
        setupTextViewLetters();


        setQuestionText(currentQuestion);


    }


    private void addQuestions() {

        Question q1 = new Question("Who invite the electric light bulb ? ", "FFFFFF", 6);
        Question q2 = new Question("What is the capital of Italy ?", "RRRR", 4);
        Question q3 = new Question("What is the name of this car ? ", "BMW", 3);
        Question q4 = new Question("What was the prior last name of shimon peres ? ", "presky", 3);

        questions.add(q1);
        questions.add(q2);
        questions.add(q3);//
        questions.add(q4);


    }


    public void setQuestionText(Question q) {

        tvQuestion.setText(q.getQuestText());

    }


    public void letterClicked(View view) {
        TextView tvClicked = (TextView) view;
        String s = tvClicked.getText().toString();
        answer = answer + s;
        freeTextViews.get(free).setText(s);
       // freeTextViews.get(free).setBackgroundColor(Integer.parseInt("#F59330")); ???
        free++;
        for (int i = free; i < freeTextViews.size(); i++) {
            if (freeTextViews.get(i).getText().toString().length() >= 1) {
                free++;
            }
        }
        if (free >= freeTextViews.size())
            if (currentQuestion.getAnsText().equals(answer)) {
                Toast.makeText(QuestionActivity2.this, "Correct", Toast.LENGTH_SHORT).show();
                score++;
                j++;
                free = 0;
                answer = "";
                clearAllTextViews();

                currentQuestion = questions.get(j);
                setQuestionText(currentQuestion);

                setupTextViewLetters();


            } else {
                Toast.makeText(QuestionActivity2.this, "Wrong", Toast.LENGTH_SHORT).show();
                free = 0;
                answer = "";
                clearAllTextViews();
            }


    }

    private void clearAllTextViews() {
        for (int i = 0; i < freeTextViews.size(); i++) {
            TextView textView = freeTextViews.get(i);
            textView.setText("");
        }
    }


    public void clear(View view) {

        TextView tv = (TextView) view;
        tv.setText("");
        int i = freeTextViews.indexOf(tv);
        if (i != -1 && i < freeTextViews.size())
            free = i;


    }




    public void setupTextViewLetters() {
        currentQuestion = questions.get(j);
        LinearLayout layout = (LinearLayout) findViewById(R.id.questionLetterContainer);
        layout.removeAllViews();
        freeTextViews.clear();
        for (int i = 0; i < currentQuestion.ansLen; i++) {
            TextView tv = (TextView) getLayoutInflater().inflate(R.layout.letter_item, layout, false);

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clear(view);
                }
            });


            layout.addView(tv);
            freeTextViews.add(tv);
        }
    }
}





