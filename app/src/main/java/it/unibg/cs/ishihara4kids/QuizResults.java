package it.unibg.cs.ishihara4kids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import it.unibg.cs.ishihara4kids.R;

public class QuizResults extends AppCompatActivity {

    private LottieAnimationView animation;

    static final int numRep = 15;

    static final int soglia = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        getSupportActionBar().hide();

        //settaggio del titolo
        TextView title = (TextView) findViewById(R.id.titleView);
        String text = "<font color=#40a4d8>I</font>" +
                "<font color=#33beb8>s</font>" +
                "<font color=#b2c225>h</font>" +
                "<font color=#fecc2f>i</font>" +
                "<font color=#f9a228>h</font>" +
                "<font color=#f6621f>a</font>" +
                "<font color=#db3838>r</font>" +
                "<font color=#ee657a>a</font>" +
                "<font color=#a363d9>4</font>" +
                "<font color=#40a4d8>K</font>" +
                "<font color=#33beb8>i</font>" +
                "<font color=#b2c225>d</font>" +
                "<font color=#fecc2f>s</font>";
        title.setText(Html.fromHtml(text));

        //settaggio della animazione lottie
        animation = findViewById(R.id.fireworks);
        animation.setRepeatCount(numRep);
        animation.playAnimation();

        final Button startNewQuizBtn = findViewById(R.id.button2);

        final TextView results = findViewById(R.id.risultati);

        final TextView comment = findViewById(R.id.commento);

        final int correctAnswers = getIntent().getIntExtra("correct", 0);

        if(correctAnswers > soglia){
            comment.setText("Nessuna anomalia visiva riscontrata!");
        } else {
            comment.setText("Riscontrata anomalia visiva, consultare uno specialista o riprovare il test!");
        }
        results.setText("Sono state riconosciute correttamente " +correctAnswers+ " immagini su 12");



        startNewQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizResults.this, MainActivity.class));
                finish();
            }
        });
    }
}