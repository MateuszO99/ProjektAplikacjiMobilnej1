package pl.edu.uwr.remindme;

public class Question {
    private final int textId;
    private final boolean answer;

    public Question(int textId, boolean answer) {
        this.textId = textId;
        this.answer = answer;
    }

    public int getTextId() {
        return textId;
    }

    public boolean isAnswer() {
        return answer;
    }
}
