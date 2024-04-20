/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "ChuyenMon")
@NoArgsConstructor
@NamedQueries({
	@NamedQuery(name = "ChuyenMon.findAll", query = "select c from ChuyenMon c")
})
public class ChuyenMon implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4946211015818098371L;
	@Id
    @Column(name = "maChuyenMon", nullable = false)
    private String maChuyenMon;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String tenChuyenMon;
    private float heSoLuong;

    // Constructor
    public ChuyenMon(String maChuyenMon, String tenChuyenMon, float heSoLuong) {
        this.maChuyenMon = maChuyenMon;
        this.tenChuyenMon = tenChuyenMon;
        this.heSoLuong = heSoLuong;
    }

    // Getters and Setters
    public String getMaChuyenMon() {
        return maChuyenMon;
    }

    public void setMaChuyenMon(String maChuyenMon) {
        this.maChuyenMon = maChuyenMon;
    }

    public String getTenChuyenMon() {
        return tenChuyenMon;
    }

    public void setTenChuyenMon(String tenChuyenMon) {
        this.tenChuyenMon = tenChuyenMon;
    }

    public float getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(float heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    @Override
    public String toString() {
    	return tenChuyenMon;
    }

	public ChuyenMon() {
		super();
	}
    
}
