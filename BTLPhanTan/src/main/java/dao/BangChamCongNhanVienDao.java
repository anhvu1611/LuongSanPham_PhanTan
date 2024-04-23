package dao;

import entity.BangChamCongNhanVien;
import entity.NhanVien;

import java.util.ArrayList;

public interface BangChamCongNhanVienDao {
    //lấy danh sách tất cả chấm công nhân viên
    ArrayList<BangChamCongNhanVien> layDanhSachChamCongNV();

    //lấy danh sách tất cả chấm công nhân viên
    ArrayList<BangChamCongNhanVien> layDanhSachChamCongTheoPhongBanHomNay(String phongBan);

    //lấy danh sách chấm công nhân viên hôm nay
    ArrayList<BangChamCongNhanVien> layDanhSachChamCongHomNay(String homNay);

    //Lấy danh sách nhân viên chưa chấm công hôm nay
    ArrayList<NhanVien> layDanhSachChuaCham();

    //Lấy danh sách tất cả mã nhân viên
    ArrayList<String> layDanhSachMaNhanVien();

    //Lấy danh sách mã nhân viên đã chấm hôm nay
    ArrayList<String> layDanhSachMaNhanVienDaChamCong();

    //Lấy danh sách mã nhân viên chưa chấm hôm nay
    ArrayList<String> layDanhSachMaNhanVienChuaChamCong();

    //Thêm chấm công nhân viên
    boolean themChamCongNhanVien(BangChamCongNhanVien ccnvMoi);

    //Xóa chấm công nhân viên
    boolean xoaChamCongNhanVien(String maChamCong);

    //cập nhật trạng thái chấm công
    boolean capNhatChamCongNV(BangChamCongNhanVien nhanVienDuocCapNhat);

    //Lấy mã chấm công lớn nhất
    String layMaChamCongCaoNhat();

    //Lấy danh sách tất cả mã nhân viên theo phòng ban
    ArrayList<String> layDanhSachMaNhanVienTheoPhongBan(String tenPhongBan);

    //Lấy danh sách mã nhân viên chưa chấm hôm nay
    ArrayList<String> layDanhSachMaNhanVienChuaChamCongTheoPhongBan(String tenPhongBan);

    //Lấy danh sách nhân viên chưa chấm công hôm nay
    ArrayList<NhanVien> layDanhSachChuaChamTheoPhongBan(String tenPhongBan);

}
