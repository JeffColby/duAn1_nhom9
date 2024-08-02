package fpoly.thanhntph47592.truthordarecustom.model;

public class CauHoi {

    private int maCauHoi,phanLoai,boCauHoi;
    private String noiDung;

    public CauHoi() {
    }

    public CauHoi(String noiDung, int phanLoai, int boCauHoi) {
        this.noiDung = noiDung;
        this.phanLoai = phanLoai;
        this.boCauHoi=boCauHoi;
    }

    public int getMaCauHoi() {
        return maCauHoi;
    }

    public void setMaCauHoi(int maCauHoi) {
        this.maCauHoi = maCauHoi;
    }

    public int getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(int phanLoai) {
        this.phanLoai = phanLoai;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getBoCauHoi() {
        return boCauHoi;
    }

    public void setBoCauHoi(int boCauHoi) {
        this.boCauHoi = boCauHoi;
    }
}
