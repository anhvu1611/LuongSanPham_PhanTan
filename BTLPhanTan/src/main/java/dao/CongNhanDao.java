package dao;

import java.util.List;

import entity.CongNhan;

public interface CongNhanDao {
	boolean them(CongNhan cn);
	boolean sua(CongNhan cn);
	CongNhan timKiem(String id);
	List<CongNhan> layDanhSach();
	String layMaCongNhanTiepTheo();
	void hamTaoMaCongNhanTiepTheo();
}
