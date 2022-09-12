package it.unibg.cs.ishihara4kids;

public class Question {

    private String solution; //nome dell'immagine nella cartella drawable
    private String[] colors; //array di esadecimali che identificano un colore

    private String userSelectedOption;

    public Question(String[] colors, String solution){
        this.colors = colors;
        this.solution = solution;
        //this.anomalyType = anomalyType;

        //per default inizializzo a null la selezione dello user
        this.userSelectedOption = "";

    }

    public String getSolution() {
        return solution;
    }

    public String[] getColors() {
        return colors;
    }

    public String getUserSelectedOption() {
        return userSelectedOption;
    }

    /*public String getAnomalyType() {
        return anomalyType;
    }*/

    public void setUserSelectedOption(String userSelectedOption) {
        this.userSelectedOption = userSelectedOption;
    }
}
