package dao;

import java.util.List;
import java.util.Vector;

public interface ThongKeLuongNhanVienDao {
	void taoDanhSachThongKeTuThangVaNam();
	void taoHamThongKeTheoThoiGian();
	List<Vector<String>> layDanhSachThongKeTopNNhanVien(int tuThang, int tuNam, int toiThang, int toiNam, int soLuongThongKe, String kieuTangGiam, String tieuChi);
}
