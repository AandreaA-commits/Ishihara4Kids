package it.unibg.cs.ishihara4kids;

import java.util.ArrayList;
import java.util.List;

public class QuizDB {

    //colori per i vari tipi di daltonismo
    private String[][] colors;
    private String[] shapes;
    private int i = 0;
    static final int numDomande = 12;

    public QuizDB(){
        shapes = new String[]{"circle", "heart", "square", "star", "triangle"};
        colors =new String[][]{
                {"#f3d589", "#f8b684", "#ed8b71", "#dfa3aa", "#c0cb7e", "#a5bda2", "#899f88"},
                {"#cb9a76", "#cdaca2", "#e3bb81", "#f1d27b", "#9b9c8d", "#b7bc9d", "#cec379"},
                {"#db5e29", "#fd8244", "#e58063", "#f7a656", "#207c5f", "#676b24", "#a3a655"},
                {"#6b7425", "#879331", "#547c2e", "#c8cd6a", "#d16e68", "#ab5629", "#d6a560"}
        };
    }

    // 4 colori per l'esterno e 3 colori per l'interno, tutti nello stesso vettore

    public List<Question> getQuestions(){
        final List<Question> questionList = new ArrayList<>();

        //aggiungere i metodi randomici per la creazione di quiz differenti
        while(i < numDomande){
            String s = shapes[(int)Math.floor((shapes.length)*Math.random())];
            String[] c = colors[(int)Math.floor((colors.length)*Math.random())];

            i++;
            Question question = new Question(c,s);
            questionList.add(question);
        }

        return questionList;

    }



}
