package dao;

import entity.CongDoan;

import java.util.ArrayList;

public interface CongDoanDao {
    CongDoan timCongDoanTheoMa(String maCongDoan);
    CongDoan timCongDoanKhiCoMa(String maCongDoan);
    ArrayList<CongDoan> layDanhSachCongDoanKhiCoMaSanPham(String maSanPham);
    boolean themCongDoan(CongDoan congDoan);
    boolean suaCongDoan(CongDoan congDoan);
    boolean updateTrangThaiCongDoan();
    boolean updateTrangThaiSanPham(String maSanPham);

}
