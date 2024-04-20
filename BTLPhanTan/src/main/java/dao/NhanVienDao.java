package dao;

import entity.ChucVu;
import entity.NhanVien;
import entity.PhongBan;

import java.util.List;

public interface NhanVienDao {
	String layEmailNhanVien(String maNhanVien);
	boolean them(NhanVien nv);
	List<NhanVien> layDanhSach();
	NhanVien timKiem(String id);
	String layMaNhanVienTiepTheo();
	boolean sua(NhanVien nv);
	List<NhanVien> timNhanVienTheoTieuChi( String ma, String ten, String ho, String cccd,
            String sdt, double thamNien, double heSo, ChucVu chucVu,
            String gt, PhongBan ban, String tinh, String quanHuyen,
            String thiXa, java.util.Date ngaySinh, java.util.Date ngayVaoLam, 
            String email, boolean bhyt, boolean bhxh, int lc);
	void hamTaoMaNhanVien();
}
