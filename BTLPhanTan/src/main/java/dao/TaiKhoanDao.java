package dao;

import entity.TaiKhoan;

public interface TaiKhoanDao   {
    TaiKhoan login(String tk, String mk);
    boolean doMatKhau(String tk, String mkMoi);
    TaiKhoan timTheoTenTaiKhoan(String tk);
	boolean sua(TaiKhoan taiKhoan);
	
}
