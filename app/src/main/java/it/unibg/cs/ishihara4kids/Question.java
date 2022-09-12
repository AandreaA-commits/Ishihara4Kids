package it.unibg.cs.ishihara4kids;

public class Question {

    private String solution; //nome dell'immagine nella cartella drawable
    private String[] colors; //array di esadecimali che identificano un colore

    private String userSelectedOption; //selezione dello user

    public Question(String[] colors, String solution){
        this.colors = colors;
        this.solution = solution;

        //inizializzazione a "" di default
        this.userSelectedOption = "";

    }

    //metodo che restiruisce la soluzione della domanda
    public String getSolution() {
        return solution;
    }

    //metodo che restituisce i colori della domanda
    public String[] getColors() {
        return colors;
    }

    //metodo che restituisce la risposta selezionata dall'utente
    public String getUserSelectedOption() {
        return userSelectedOption;
    }

    // metodo per settare  la risposta dell'utente
    public void setUserSelectedOption(String userSelectedOption) {
        this.userSelectedOption = userSelectedOption;
    }
}
