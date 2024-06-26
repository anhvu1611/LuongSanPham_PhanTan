/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ACER
 */
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "PhongBan")
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
	@NamedQuery(name = "PhongBan.layDanhSach", query = "select c from PhongBan c")
})
public class PhongBan implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 778945132218850228L;
	@Id
    @Column(name = "maPhongBan", nullable = false)
    private String maPhongBan;
	@Column(columnDefinition = "NVARCHAR(255)")
    private String tenPhongBan;
    private int soLuongNhanVien;
    private LocalDate ngayThanhLap;
    private String soDienThoai;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String moTa;

    // Getters and Setters
    public String getMaPhongBan() {
        return maPhongBan;
    }

    public void setMaPhongBan(String maPhongBan) {
        this.maPhongBan = maPhongBan;
    }

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }

    public int getSoLuongNhanVien() {
        return soLuongNhanVien;
    }

    public void setSoLuongNhanVien(int soLuongNhanVien) {
        this.soLuongNhanVien = soLuongNhanVien;
    }

    public LocalDate getNgayThanhLap() {
        return ngayThanhLap;
    }

    public void setNgayThanhLap(LocalDate ngayThanhLap) {
        this.ngayThanhLap = ngayThanhLap;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return tenPhongBan;
    }

    
}
