package it.unibg.cs.ishihara4kids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import it.unibg.cs.ishihara4kids.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //comando per eliminare barra in alto
        getSupportActionBar().hide();

        //settaggio html del titolo
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


    }

    //metodo chiamato quando viene premuto il pulsante per cominciare il quiz
    public void launchQuiz(View view) {
        Intent i = new Intent(this, QuizActivity.class);
        startActivity(i);
    }
}