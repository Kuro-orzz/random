package Code.Question;

public class Question {
    private String question;
    private String topic;

    public Question(String question, String topic) {
        this.question = question;
        this.topic = topic;
    }

    public Question(String[] data) {
        this.question = data[0];
        this.topic = data[1];
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String type) {
        this.topic = type;
    }
}
