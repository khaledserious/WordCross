package minhal.edu.khaled.wordcross;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    TextView playing , score , sharing;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playing = (TextView) findViewById(R.id.play);
        score = (TextView) findViewById(R.id.score);
        sharing = (TextView) findViewById(R.id.share);


        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            //Say hello
            Toast.makeText(MainActivity.this, "Welcome, " + currentUser.getEmail(), Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

    }




    public void showDetails(View view) {
      // ViewParent parent = view.getParent();
        Context c = getApplicationContext();
        Intent aIntent = new Intent(c, QuestionDynamicActivity.class);

        Intent bIntent = new Intent(c, ScoreActivity.class);

        Intent cIntent = new Intent(c, ShareActivity.class);


        //put extra data
        switch (view.getId()) {

            case R.id.play:
                aIntent.putExtra("PlayIndex", 0);
                startActivity(aIntent);
                break;

            case R.id.score:
                bIntent.putExtra("ScoreIndex", 1);
                startActivity(bIntent);
                break;
            case R.id.share:
                cIntent.putExtra("ShareIndex", 2);
                startActivity(cIntent);
                break;

        }

    }



}
