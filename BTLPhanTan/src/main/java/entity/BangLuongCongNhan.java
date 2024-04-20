package entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Entity
@Table(name = "BangLuongCongNhan")
public class BangLuongCongNhan implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2838197704958154885L;
	@Id
	@Column(name = "maBangLuongCongNhan", nullable = false)
	private String maBangLuongCongNhan;
	@ManyToOne
	@JoinColumn(name = "maCongNhan")
	private CongNhan congNhan;
	private int soLuongSanPHam;
	private double luongTheoSanPham;
	private double tongLuong;
	private Date ngayLap;
	
	public BangLuongCongNhan(String maBangLuongCongNhan, CongNhan congNhan, int soLuongSanPham, double luongTheoSanPham,
			double tongLuong, Date ngayLap) {
		super();
		this.maBangLuongCongNhan = maBangLuongCongNhan;
		this.congNhan = congNhan;
		this.soLuongSanPHam = soLuongSanPham;
		this.luongTheoSanPham = luongTheoSanPham;
		this.tongLuong = tongLuong;
		this.ngayLap = ngayLap;
	}
	public String getMaBangLuongCongNhan() {
		return maBangLuongCongNhan;
	}
	public void setMaBangLuongCongNhan(String maBangLuongCongNhan) {
		this.maBangLuongCongNhan = maBangLuongCongNhan;
	}
	public CongNhan getCongNhan() {
		return congNhan;
	}
	public void setCongNhan(CongNhan congNhan) {
		this.congNhan = congNhan;
	}
	public int getSoLuongSanPham() {
		return soLuongSanPHam;
	}
	public void setSoLuongSanPham(int soLuongSanPham) {
		this.soLuongSanPHam = soLuongSanPham;
	}
	public double getLuongTheoSanPham() {
		return luongTheoSanPham;
	}
	public void setLuongTheoSanPham(double luongTheoSanPham) {
		this.luongTheoSanPham = luongTheoSanPham;
	}
	public double getTongLuong() {
		return tongLuong;
	}
	public void setTongLuong(double tongLuong) {
		this.tongLuong = tongLuong;
	}
	public Date getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}
	
	
	
	
}
