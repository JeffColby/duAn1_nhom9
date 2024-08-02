package fpoly.thanhntph47592.truthordarecustom.model;

public class LichSu {

    private int maVanChoi,soNguoiChoi;
    private String ngayGio,tenNguoiChoi,cauHoi;

    public LichSu() {
    }

    public LichSu(String ngayGio, int soNguoiChoi, String tenNguoiChoi, String cauHoi) {
        this.soNguoiChoi = soNguoiChoi;
        this.ngayGio = ngayGio;
        this.tenNguoiChoi = tenNguoiChoi;
        this.cauHoi = cauHoi;
    }

    public int getMaVanChoi() {
        return maVanChoi;
    }

    public void setMaVanChoi(int maVanChoi) {
        this.maVanChoi = maVanChoi;
    }

    public int getSoNguoiChoi() {
        return soNguoiChoi;
    }

    public void setSoNguoiChoi(int soNguoiChoi) {
        this.soNguoiChoi = soNguoiChoi;
    }

    public String getNgayGio() {
        return ngayGio;
    }

    public void setNgayGio(String ngayGio) {
        this.ngayGio = ngayGio;
    }

    public String getTenNguoiChoi() {
        return tenNguoiChoi;
    }

    public void setTenNguoiChoi(String tenNguoiChoi) {
        this.tenNguoiChoi = tenNguoiChoi;
    }

    public String getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(String cauHoi) {
        this.cauHoi = cauHoi;
    }
}
