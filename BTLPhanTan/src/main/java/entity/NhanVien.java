package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

import Enum.PhuCap;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "NhanVien.findAll", query = "select c from NhanVien c"),
        @NamedQuery(name = "NhanVien.getMailTheoMaNhanVien", query = "select c.email from NhanVien c where c.maNhanSu = :ma")
})
@Table(name = "NhanVien")
@ToString
public class NhanVien extends NhanSu implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8588631762089370283L;
	@ManyToOne
    @JoinColumn(name = "maChucVu")
    private ChucVu chucVu;
    @OneToOne
    @JoinColumn(name = "maTaiKhoan")
    private TaiKhoan taiKhoan;
    private String BHYT;
    private String BHXH;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "maPhongBan", unique = false)
    private PhongBan ban;
    private String email;
    private float heSoLuong;
    private float thamNien;
	public ChucVu getChucVu() {
		return chucVu;
	}
	public void setChucVu(ChucVu chucVu) {
		this.chucVu = chucVu;
	}
	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public String getBHYT() {
		return BHYT;
	}
	public void setBHYT(String bHYT) {
		BHYT = bHYT;
	}
	public String getBHXH() {
		return BHXH;
	}
	public void setBHXH(String bHXH) {
		BHXH = bHXH;
	}
	public PhongBan getBan() {
		return ban;
	}
	public void setBan(PhongBan ban) {
		this.ban = ban;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public float getHeSoLuong() {
		return heSoLuong;
	}
	public void setHeSoLuong(float heSoLuong) {
		this.heSoLuong = heSoLuong;
	}
	public float getThamNien() {
		return thamNien;
	}
	public void setThamNien(float thamNien) {
		this.thamNien = thamNien;
	}
    
    
	public java.util.Vector<String> duLieuFormCapNhat(){
    	Vector<String> data = new Vector<String>();
    	data.add(maNhanSu);
    	data.add(ho);
    	data.add(ten);
    	data.add(CCCD);
    	data.add(soDienThoai);
    	data.add(gioiTinh);
    	data.add(ban.getTenPhongBan());
    	data.add(chucVu.getTenChucVu());
    	data.add(diaChi.toString());
    	return data;
    }
	public NhanVien(String maNhanSu, String ho, String ten, String soDienThoai, Date ngaySinh, Date ngayVaoLam,
			String cCCD, DiaChi diaChi, PhuCap phuCap, String gioiTinh, String anh, ChucVu chucVu, TaiKhoan taiKhoan,
			String bHYT, String bHXH, PhongBan ban, String email, float heSoLuong, float thamNien) {
		this.maNhanSu = maNhanSu;
		this.ho = ho;
		this.ten = ten;
		this.soDienThoai = soDienThoai;
		this.ngaySinh = ngaySinh;
		this.ngayVaoLam = ngayVaoLam;
		CCCD = cCCD;
		this.diaChi = diaChi;
		this.phuCap = phuCap;
		this.gioiTinh = gioiTinh;
		this.anh = anh;
		this.chucVu = chucVu;
		this.taiKhoan = taiKhoan;
		BHYT = bHYT;
		BHXH = bHXH;
		this.ban = ban;
		this.email = email;
		this.heSoLuong = heSoLuong;
		this.thamNien = thamNien;
	}
	

//	@Override
//	public String toString() {
//		return "NhanVien [chucVu=" + chucVu + ", taiKhoan=" + taiKhoan + ", BHYT=" + BHYT + ", BHXH=" + BHXH + ", ban="
//				+ ban + ", email=" + email + ", heSoLuong=" + heSoLuong + ", thamNien=" + thamNien + "]";
//	}
	
	
}