package dao;

import entity.DiaChi;

public interface DiaChiDao {
	String[] layDanhSachTinhThanh();
	String[] layDanhSachQuanHuyen(String tinhThanh);
	String[] layDanhSachPhuong(String tinhThanh, String quanHuyen);
	void themDiaChi(DiaChi dc);
}
