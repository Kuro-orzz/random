package Code.Question;

public class Question {
    private String question;
    private String type;

    public Question(String question, String type) {
        this.question = question;
        this.type = type;
    }

    public Question(String[] data) {
        this.question = data[0];
        this.type = data[1];
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
