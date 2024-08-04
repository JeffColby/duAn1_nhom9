package fpoly.thanhntph47592.truthordarecustom.model;

public class GamePlayData {

    private int id, numPlayer;
    private String time, playerName, question;

    public GamePlayData() {
    }

    public GamePlayData(String time, int numPlayer, String playerName, String question) {
        this.numPlayer = numPlayer;
        this.time = time;
        this.playerName = playerName;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumPlayer() {
        return numPlayer;
    }

    public void setNumPlayer(int numPlayer) {
        this.numPlayer = numPlayer;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
