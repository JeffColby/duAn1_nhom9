package fpoly.thanhntph47592.truthordarecustom.model;

public class Question {

    private int id, type, questionGroup;
    private String content;

    public Question() {
    }

    public Question(String content, int type, int questionGroup) {
        this.content = content;
        this.type = type;
        this.questionGroup = questionGroup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getQuestionGroup() {
        return questionGroup;
    }

    public void setQuestionGroup(int questionGroup) {
        this.questionGroup = questionGroup;
    }
}
