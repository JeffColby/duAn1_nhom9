package fpoly.thanhntph47592.truthordarecustom.model;

public class BoCauHoi {

    private int maBoCauHoi;
    private String tenBoCauHoi;

    public BoCauHoi() {
    }

    public BoCauHoi(String ten) {
        this.tenBoCauHoi = ten;
    }

    public int getMaBoCauHoi() {
        return maBoCauHoi;
    }

    public void setMaBoCauHoi(int maBoCauHoi) {
        this.maBoCauHoi = maBoCauHoi;
    }

    public String getTenBoCauHoi() {
        return tenBoCauHoi;
    }

    public void setTenBoCauHoi(String tenBoCauHoi) {
        this.tenBoCauHoi = tenBoCauHoi;
    }
}
