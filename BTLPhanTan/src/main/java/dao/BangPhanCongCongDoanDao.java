package dao;

import entity.BangPhanCongCongDoan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * @description: interface BangPhanCongCongDoanDao chứa các phương thức thao tác với bảng BangPhanCongCongDoan trong database
 * @author: NguyenKhanhAn
 * @date: 24/04/2024
 * @version: 1.0
 */

public interface BangPhanCongCongDoanDao {
	
	public int laySoLuongDaChamCongTheoMaCongDoan(String maCongDoan);
	
    public void themPhanCongCongDoan(BangPhanCongCongDoan phanCong);

    public void suaPhanCongCongDoan(BangPhanCongCongDoan phanCong);
   

    public void xoaPhanCongCongDoanVaKhoiDs(String maCongDoan, String maCongNhan);

    public ArrayList<BangPhanCongCongDoan> layDanhSachPhanCongCongDoanTheoMaCongDoan(String maCongDoan);
    public List<BangPhanCongCongDoan> layDanhSachPhanCongCN() ;
    public int laySoLuongPhanCong(String maCongDoan);

    public static int tinhTongSoLuongPhanCongTheoMaCongDoan(String maCongDoan) {
        return 0;
    }

    public BangPhanCongCongDoan timPhanCongCongDoanTheoMaCongNhan(String maCongNhan);
    public BangPhanCongCongDoan timPhanCongCongDoanTheoMaCongNhanVaNgayCham(String maCongNhan, Date ngayCham);
    public BangPhanCongCongDoan timTheoMaCongNhanVaMaCongDoan(String maCongNhan, String maCongDoan) ;
    public boolean capNhatSoLuongBangCongDoan(int soLuongConLai, String maCongDoan, String maCongNhan) ;
    public ArrayList<BangPhanCongCongDoan> layDanhSachPhanCongCongDoan() ;
}
