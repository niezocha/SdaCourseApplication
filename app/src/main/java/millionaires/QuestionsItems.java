package millionaires;

import java.util.List;

public class QuestionsItems {

    private int id;
    private String question;
    private List<SingleAnswer> answers;

    public QuestionsItems(String question, List<SingleAnswer> answers){
        this.question=question;
        this.answers=answers;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public List<SingleAnswer> getAnswers() {
        return answers;
    }
}
