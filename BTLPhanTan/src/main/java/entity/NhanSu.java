package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import Enum.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "NhanSu")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class NhanSu implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -917600731642306915L;

	@Id
    @Column(name = "maNhanSu", nullable = false)
    protected String maNhanSu;
	@Column(columnDefinition = "NVARCHAR(255)")
    protected String ho;
	@Column(columnDefinition = "NVARCHAR(255)")
    protected String ten;
    protected String soDienThoai;
    protected Date ngaySinh;
    protected Date ngayVaoLam;
    protected String CCCD;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "maDiaChi")
    protected DiaChi diaChi;
	@Column(columnDefinition = "INT")
    protected PhuCap phuCap;
	@Column(columnDefinition = "NVARCHAR(8)")
    protected String gioiTinh;
    protected String anh;
	public String getMaNhanSu() {
		return maNhanSu;
	}
	public void setMaNhanSu(String maNhanSu) {
		this.maNhanSu = maNhanSu;
	}
	public String getHo() {
		return ho;
	}
	public void setHo(String ho) {
		this.ho = ho;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public Date getNgayVaoLam() {
		return ngayVaoLam;
	}
	public void setNgayVaoLam(Date ngayVaoLam) {
		this.ngayVaoLam = ngayVaoLam;
	}
	public String getCCCD() {
		return CCCD;
	}
	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}
	public DiaChi getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(DiaChi diaChi) {
		this.diaChi = diaChi;
	}
	public PhuCap getPhuCap() {
		return phuCap;
	}
	public void setPhuCap(PhuCap phuCap) {
		this.phuCap = phuCap;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getAnh() {
		return anh;
	}
	public void setAnh(String anh) {
		this.anh = anh;
	}
	public NhanSu(String maNhanSu, String ho, String ten, String soDienThoai, Date ngaySinh, Date ngayVaoLam,
			String cCCD, DiaChi diaChi, PhuCap phuCap, String gioiTinh, String anh) {
		super();
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
	}
	public NhanSu() {
		// TODO Auto-generated constructor stub
	}
    
    



}