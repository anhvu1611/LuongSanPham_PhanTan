package dao;


import entity.BangPhanCongCongDoan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface BangPhanCongCongDoanDao {
    List<BangPhanCongCongDoan> layDanhSachBangPhanCongCN(); //Lay ds phan cong
    BangPhanCongCongDoan timPhanCongCongDoanTheoMaCongNhan(String maCongNhan); //Tìm phân công theo mã công nhân
    BangPhanCongCongDoan timPhanCongCongDoanTheoMaCongNhanVaNgayCham(String maCongNhan, Date ngayCham);
    BangPhanCongCongDoan timTheoMaCongNhanVaMaCongDoan(String maCongNhan, String maCongDoan); //Tìm phân công theo mã công nhân và mã công đoạn
    boolean capNhatSoLuongBangCongDoan(int soLuongConLai, String maCongDoan, String maCongNhan); //Update lại số lượng bảng phân công
    ArrayList<BangPhanCongCongDoan> layDanhSachPhanCongCongDoan();




}
