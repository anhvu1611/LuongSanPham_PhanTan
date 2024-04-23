package dao;

import entity.BangChamCongCongNhan;

import java.util.ArrayList;

public interface BangChamCongCongNhanDao {
    int laySoLuongDaChamCongTheoMaCongDoan(String maCongDoan);

    //Tim kiem cham cong theo ma cong nhan
    ArrayList<BangChamCongCongNhan> timChamCongTheoMa(String maCongNhan);

    //tìm chấm công tháng theo mã công nhân
    ArrayList<BangChamCongCongNhan> timChamCongTheoThangVaTheoMa(String maCongNhan, int thang);

    //lấy danh sách chấm công công nhân theo tháng
    ArrayList<BangChamCongCongNhan> layDanhSachChamCongCN(int thang);

    //lấy danh sách chấm công công nhân hôm nay
    ArrayList<BangChamCongCongNhan> layDanhSachChamCongCNHomNay(String homNay);

    //Thêm chấm công công nhân
    boolean themChamcongCongNhan(BangChamCongCongNhan cccnMoi, String maNguoiChamCong);

    //Lấy mã chấm công lớn nhất
    public String layMaChamCongCaoNhat();

    //Tìm chấm công theo người nhận lương và tháng chấm
    ArrayList<BangChamCongCongNhan> timChamCongTheoNguoiNhanLuongVaThangCham(String hoTen, int i);



}
