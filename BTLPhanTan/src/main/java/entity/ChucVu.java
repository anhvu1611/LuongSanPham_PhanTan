/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author ACER
 */
@Table(name = "ChucVu")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
	@NamedQuery(name = "ChucVu.findAll", query = "select c from ChucVu c")
})
public class ChucVu implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7181731679919357917L;
	@Id
    @Column(name = "maChucVu", nullable = false)
    private String maChucVu;
	@Column(columnDefinition = "NVARCHAR(255)")
    private String tenChucVu;
    private float luongCungTheoChucVu;

    // Constructor

    // Getters and Setters
    public String getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }

    public float getHeSoLuong() {
        return luongCungTheoChucVu;
    }

    public void setHeSoLuong(float heSoLuong) {
        this.luongCungTheoChucVu = heSoLuong;
    }

    @Override
    public String toString() {
        return tenChucVu;
    }
     

}
