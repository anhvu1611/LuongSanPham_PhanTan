package dao;

import entity.CongDoan;

import java.util.List;

/*
 * @description: interface CongDoanDao chứa các phương thức thao tác với bảng CongDoan trong database
 * @author: NguyenKhanhAn
 * @date: 22/04/2024
 * @version: 1.0
 */

public interface CongDoanDao {
    boolean them(CongDoan cd);
    boolean sua(CongDoan cd);
    CongDoan timKiem(String id);
    List<CongDoan> layDanhSachTheoMaSanPham(String maSanPham);
    boolean capNhatTrangThaiCongDoan();
    boolean capNhatTrangThaiSanPham();
    String layMaCongDoanTiepTheo(String ma);
    public int laySoLuongThanhPhanTheoMaCongDoan(String maCongDoan);
}
