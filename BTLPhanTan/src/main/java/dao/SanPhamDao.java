package dao;

import java.util.Date;
import java.util.List;
import entity.SanPham;

public interface SanPhamDao {
	boolean them(SanPham sp);
	boolean sua(SanPham sp);
	List<SanPham> layDanhSach();
	String layMaSanPhamTiepTheo();
	SanPham timSanPhamTheoMa(String id);
	void taoHamLayMaSanPhamTiepTheo();
	List<SanPham> timSanPhamDaTieuChi(String ma, String ten, String khoiLuong, String soLuong, String loaiGo, String soCongDoan, String dai, String rong, String cao, Date ngay, String gia);
}
