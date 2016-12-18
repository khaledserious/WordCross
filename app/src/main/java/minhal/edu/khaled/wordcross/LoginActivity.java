package minhal.edu.khaled.wordcross;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {
    EditText etPlayerName ;
    private FirebaseAuth.AuthStateListener mAuthListiener;
    boolean isFirst = true;
    private boolean isReady;
    private View btnPlay;
    private ObjectAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etPlayerName = (EditText) findViewById(R.id.etPlayerName);
        btnPlay = findViewById(R.id.btn_Play);
        mAuthListiener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null && isFirst) {
                    FirebaseAuth.getInstance().signInAnonymously();
                    isFirst = false;
                }
                else if (firebaseAuth.getCurrentUser()!=null){
                    isReady = true;
                }
            }
        };

        animator = ObjectAnimator.ofFloat(btnPlay, "ScaleX", 1f, 1.2f, 1.3f, 1.4f, 1.3f, 1.1f, 0.8f, 1);
        animator.setDuration(1000);
        animator.setInterpolator(new BounceInterpolator());

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        etPlayerName.animate().rotation(360);
        animator.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListiener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        FirebaseAuth.getInstance().removeAuthStateListener(mAuthListiener);
    }


    public void login(View view) {
        MediaPlayer.create(this, R.raw.staple).start();
        final String playerName = etPlayerName.getText().toString();

        //ref to a table.
        DatabaseReference referenceRef = FirebaseDatabase.getInstance().getReference("Users");

        referenceRef.push().setValue("Tomer");
        referenceRef.addListenerForSingleValueEvent(new ValueEventListener() {
            public boolean nameTaken;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String name = child.getValue(String.class);
                    Log.d("Tomer", name);
                    if (name.equals(playerName)){
                        nameTaken = true;
                        etPlayerName.setError("Name Taken");
                    }
                }
                if (!nameTaken){
                    Toast.makeText(LoginActivity.this, "Sababa", Toast.LENGTH_SHORT).show();
                    //gotoMain();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showError(Exception e) {
        Snackbar.make(etPlayerName, e.getLocalizedMessage(), Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void gotoMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
