package millionaires;

public class SingleAnswer {

    private boolean isCorrect;
    private String text;

    public SingleAnswer(String text, boolean isCorrect){
        this.text=text;
        this.isCorrect=isCorrect;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public String getText() {
        return text;
    }
}


