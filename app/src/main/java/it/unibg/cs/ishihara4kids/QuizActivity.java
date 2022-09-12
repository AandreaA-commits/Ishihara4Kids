package it.unibg.cs.ishihara4kids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import it.unibg.cs.ishihara4kids.R;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    //reference per il plate
    private ImageView plateIshihara;

    //reference della barra di progresso
    private ProgressBar progressBar;


    //array list contenente i 12 test
    private List<Question> questionList;

    //posizione nella questionList
    int currentQuestionPosition = 0;

    //i bottoni delle possibili soluzioni
    private ImageButton circle, triangle, square, heart, star;
    private Button home;
    private QuizDB db;

    //salvataggio della selezione dell'utente
    private String userSelectedOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        getSupportActionBar().hide();

        plateIshihara = findViewById(R.id.imageView);
      // plateIshihara = findViewById(R.id.imageView7);

        //reference dei bottoni selezionati
        circle = findViewById(R.id.circle);
        triangle = findViewById(R.id.triangle);
        square = findViewById(R.id.square);
        heart = findViewById(R.id.heart);
        star = findViewById(R.id.star);
        home = findViewById(R.id.home);

        //setting del titolo in alto
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

        //reference della progressBar
        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(currentQuestionPosition);
        progressBar.setMax(12);

        //setto a vuoto la risposta iniziale
        userSelectedOption = "";
        db = new QuizDB();
        questionList = db.getQuestions();

        //per ogni bottone imposto che al click, venga impostata la risposta dell'utente
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userSelectedOption.isEmpty()){
                    //setto la risposta dell'utente coincidente con la figuara del bottone
                    userSelectedOption = "circle";
                    questionList.get(currentQuestionPosition).setUserSelectedOption(userSelectedOption);
                }
                nextQuestion();

            }
        });

        triangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userSelectedOption.isEmpty()){
                    //setto la risposta dell'utente coincidente con la figuara del bottone
                    userSelectedOption = "triangle";
                    questionList.get(currentQuestionPosition).setUserSelectedOption(userSelectedOption);
                }
                nextQuestion();
            }
        });

        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userSelectedOption.isEmpty()){
                    //setto la risposta dell'utente coincidente con la figuara del bottone
                    userSelectedOption = "heart";
                    questionList.get(currentQuestionPosition).setUserSelectedOption(userSelectedOption);
                }
                nextQuestion();
            }
        });

        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userSelectedOption.isEmpty()){
                    //setto la risposta dell'utente coincidente con la figuara del bottone
                    userSelectedOption = "square";
                    questionList.get(currentQuestionPosition).setUserSelectedOption(userSelectedOption);
                }
                nextQuestion();
            }
        });

        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userSelectedOption.isEmpty()){
                    //setto la risposta dell'utente coincidente con la figuara del bottone
                    userSelectedOption = "star";
                    questionList.get(currentQuestionPosition).setUserSelectedOption(userSelectedOption);
                }
                nextQuestion();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizActivity.this, MainActivity.class));
                finish();
            }
        });

    }

    private void nextQuestion() {
        //incremento la posizione dell'indice nelle domande
        currentQuestionPosition++;
        progressBar.setProgress(currentQuestionPosition);

        if(currentQuestionPosition<questionList.size()){
            //riazzero la variabile in cui è tenuta la risposta
            userSelectedOption = "";

            //cambio il plate ishihara
            String colors[] = questionList.get(currentQuestionPosition).getColors();
            String image = "black_" + questionList.get(currentQuestionPosition).getSolution()+"_plate";
            buttonDrawCanvas(colors, image);

        }
        else {

            //vuol dire che il game è finito e vengono inviati i dati
            Intent i = new Intent(QuizActivity.this, QuizResults.class);
            i.putExtra("correct", getCorrectAnswer());
            startActivity(i);

            finish(); //per terminare definitivamente l'attività
        }

    }

    private int getCorrectAnswer() {

        //la prima immmagine è di prova e viene considerata sempre corretta
        int correctAnswers = 1;

        for (int i = 1; i < questionList.size(); i++) {
            final String getUserSelectedOption = questionList.get(i).getUserSelectedOption();
            final String sol = questionList.get(i).getSolution();

            //viene aggiornato ogni volta con la risposta che viene data dallo user
            if (getUserSelectedOption.equals(sol)) {
                correctAnswers++;
            }
        }
        return correctAnswers;
    }

    //da modificare e rendere tipo nextQuestion
    public void buttonDrawCanvas(String[] colors, String image){

        //image è l'immagine della soluzione corretta
        //Bitmap bitmap = Bitmap.createBitmap(plateIshihara.getWidth(), plateIshihara.getHeight(), Bitmap.Config.ARGB_8888);

        //divido i colori tra quelli della figura e quelli dello sfondo
        String[] colorOut =new String[]{
            colors[0], colors[1], colors[2], colors[3]
        };

        String[] colorIn =new String[]{
                colors[4], colors[5], colors[6]
        };

        //CODICE PER COLORARE LE PARTI GIUSTE
        int resID = getResources().getIdentifier(image, "drawable", getPackageName());

        //trovo l'immagine giusta corrispondente
        Bitmap bm1 = BitmapFactory.decodeResource(getResources(), resID);
        Bitmap bitmap = Bitmap.createScaledBitmap(bm1, plateIshihara.getWidth(), plateIshihara.getHeight(), false);
        Bitmap b = Bitmap.createBitmap(plateIshihara.getWidth(), plateIshihara.getHeight(), Bitmap.Config.ARGB_8888);

        //creazione del canvas con la bitmap
        Canvas canvas = new Canvas(b);

        //genero i cerchi sul canvas
        generateCircle2(canvas, bitmap, colorIn, colorOut);

        //setto la bitmap sulla ImageView
        plateIshihara.setImageBitmap(b);

    }


    private void generateCircle2(Canvas canvas, Bitmap bitmap, String[] colorIn, String[] colorOut){

     //  canvas.setBitmap(bm1);
        Paint p2 = new Paint();

        Paint white = new Paint();
        white.setColor(Color.WHITE);

        Rect rettangolo = new Rect(0,0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawRect(rettangolo, white);

        for (int i = 0; i < 10000; i++) {

            int x = (int) (bitmap.getWidth() * Math.random());
            int y = (int) (bitmap.getHeight() * Math.random());

            int pixel = bitmap.getPixel(x, y);


            int r = Color.red(pixel);
            int g = Color.green(pixel);
            int b = Color.blue(pixel);

            if (Color.rgb(r, g, b) == Color.BLACK) {
                p2.setColor(Color.parseColor(colorIn[(int) Math.floor((colorIn.length - 1) * Math.random())]));
                canvas.drawCircle(x, y, (float) (bitmap.getHeight() * Math.random() / 100), p2);
            } else {
                    p2.setColor(Color.parseColor(colorOut[(int) Math.floor((colorOut.length - 1) * Math.random())]));
                    canvas.drawCircle(x, y, (float) (bitmap.getHeight() * Math.random() / 100), p2);
            }
        }



    }


}


