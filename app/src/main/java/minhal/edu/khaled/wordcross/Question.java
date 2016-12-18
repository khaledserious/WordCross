package minhal.edu.khaled.wordcross;

/**
 * Created by khaled on 30/11/2016.
 */
public class Question {

    public String questText ;
    public String ansText ;
    public int ansLen ;

    public Question(String questText, String ansText, int ansLen) {
        this.questText = questText;
        this.ansText = ansText;
        this.ansLen = ansLen;
    }

    public String getQuestText() {
        return questText;
    }

    public int getAnsLen() {
        return ansLen;
    }


    public String getAnsText() {
        return ansText;
    }









}
