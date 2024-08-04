package fpoly.thanhntph47592.truthordarecustom.model;

public class QuestionGroup {

    private int id;
    private String name;

    public QuestionGroup() {
    }

    public QuestionGroup(String ten) {
        this.name = ten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
