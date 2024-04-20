/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


import java.util.Date;
import java.util.Vector;

import Enum.CaLamCongNhan;
import Enum.PhuCap;
import jakarta.persistence.*;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "CongNhan")
@NamedQueries({
	@NamedQuery(name = "CongNhan.findAll", query = "select c from CongNhan c")
})
public class CongNhan extends NhanSu{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8853112627467975679L;
	private boolean trangThai;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "maChuyenMon")
	private ChuyenMon chuyenMon;
    private CaLamCongNhan caLam;
    
    
    
    public CongNhan() {
		super();
	}

	public CongNhan(String maNhanSu, String ho, String ten, String soDienThoai, Date ngaySinh, Date ngayVaoLam,
			String cCCD, DiaChi diaChi, PhuCap phuCap, String gioiTinh, String anh, boolean trangThai,
			ChuyenMon chuyenMon, CaLamCongNhan caLam) {
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
		this.trangThai = trangThai;
		this.chuyenMon = chuyenMon;
		this.caLam = caLam;
	}

	public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public ChuyenMon getChuyenMon() {
        return chuyenMon;
    }
    
    
    public void setChuyenMon(ChuyenMon chuyenMon) {
        this.chuyenMon = chuyenMon;
    }

	public CaLamCongNhan getCaLam() {
		return caLam;
	}

	public void setCaLam(CaLamCongNhan caLam) {
		this.caLam = caLam;
	}

	public Vector<String> duLieuFormCapNhat() {
		Vector<String> data = new Vector<>();
		data.add(maNhanSu);
		data.add(ho);
		data.add(ten);
		data.add(soDienThoai);
		data.add(CCCD);
		data.add(diaChi.toString());
		data.add(gioiTinh);
		data.add(chuyenMon.getTenChuyenMon());
		data.add((trangThai==true)?"Tham Gia":"Không tham gia");
		return data;
	}
    
	
}
