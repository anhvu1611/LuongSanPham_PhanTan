package dao;

public interface ThongKeBangLuongCongNhanDao {
/**
 * 
 * 
 * 
 * select * from BangLuongCongNhan bl
join CongNhan cn on cn.maNhanSu = bl.maCongNhan
join BangPhanCongCongDoan bpc on bpc.maNhanSu = cn.maNhanSu
join CongDoan cd on cd.maCongDoan = bpc.maCongDoan
join SanPham sp on sp.maSanPham = cd.maSanPham
 * 
 * 
 * */
}
