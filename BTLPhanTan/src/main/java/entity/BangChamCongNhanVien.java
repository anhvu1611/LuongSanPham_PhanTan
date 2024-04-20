/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


import java.io.Serializable;
import java.util.Date;

import Enum.CaLamNhanVien;
import Enum.TrangThai;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "BangChamCongNhanVien")
@NoArgsConstructor
public class BangChamCongNhanVien implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4825609987405284599L;
	@Id
    @Column(name = "maChamCong")
    private String maChamCong;
    private Date ngayChamCong;
    @ManyToOne
    @JoinColumn(name = "maNhanVienChamCong")
    private NhanVien doiTuongChamCong;
    private TrangThai trangThai;
    private String ghiChu;
    private CaLamNhanVien caLam;
    @ManyToOne
    @JoinColumn(name = "maNhanVienDuocChamCong")
    private NhanVien nhanVienDuocChamCong;
    private int soGioTangCa;

    public BangChamCongNhanVien(String maChamCong, Date ngayChamCong, NhanVien doiTuongChamCong, TrangThai trangThai, String ghiChu, CaLamNhanVien caLam, NhanVien nhanVienDuocChamCong) {
        this.maChamCong = maChamCong;
        this.ngayChamCong = ngayChamCong;
        this.doiTuongChamCong = doiTuongChamCong;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
        this.caLam = caLam;
        this.nhanVienDuocChamCong = nhanVienDuocChamCong;
    }
    
    
    
    public int getSoGioTangCa() {
		return soGioTangCa;
	}



	public void setSoGioTangCa(int soGioTangCa) {
		this.soGioTangCa = soGioTangCa;
	}



	public BangChamCongNhanVien(String maChamCong, Date ngayChamCong, NhanVien doiTuongChamCong, TrangThai trangThai,
			String ghiChu, CaLamNhanVien caLam, NhanVien nhanVienDuocChamCong, int soGioTangCa) {
		super();
		this.maChamCong = maChamCong;
		this.ngayChamCong = ngayChamCong;
		this.doiTuongChamCong = doiTuongChamCong;
		this.trangThai = trangThai;
		this.ghiChu = ghiChu;
		this.caLam = caLam;
		this.nhanVienDuocChamCong = nhanVienDuocChamCong;
		this.soGioTangCa = soGioTangCa;
	}



	public String getMaChamCong() {
        return maChamCong;
    }

    public void setMaChamCong(String maChamCong) {
        this.maChamCong = maChamCong;
    }

    public Date getNgayChamCong() {
        return ngayChamCong;
    }

    public void setNgayChamCong(Date ngayChamCong) {
        this.ngayChamCong = ngayChamCong;
    }

    public NhanVien getDoiTuongChamCong() {
        return doiTuongChamCong;
    }

    public void setDoiTuongChamCong(NhanVien doiTuongChamCong) {
        this.doiTuongChamCong = doiTuongChamCong;
    }

    public TrangThai getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThai trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public CaLamNhanVien getCaLam() {
        return caLam;
    }

    public void setCaLam(CaLamNhanVien caLam) {
        this.caLam = caLam;
    }

    public NhanVien getNhanVienDuocChamCong() {
        return nhanVienDuocChamCong;
    }

    public void setNhanVienDuocChamCong(NhanVien nhanVienDuocChamCong) {
        this.nhanVienDuocChamCong = nhanVienDuocChamCong;
    }


}
