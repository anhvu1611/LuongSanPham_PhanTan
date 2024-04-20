/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author ACER
 */

@Entity
@Table(name = "TaiKhoan")
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "TaiKhoan.login", query = "select c from TaiKhoan c where c.tenTaiKhoan = :tk and c.matKhau = :mk"),
        @NamedQuery(name= "TaiKhoan.timTheoTenTaiKhoan", query = "select d from TaiKhoan d where d.tenTaiKhoan = :tk")
})
public class TaiKhoan implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1024926950661736014L;
	@Id
    @Column(name = "maTaiKhoan", nullable = false)
    private String maTaiKhoan;
    private String tenTaiKhoan;
    private String matKhau;

    // Constructor

    // Getters and Setters
    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" + "maTaiKhoan=" + maTaiKhoan + ", tenTaiKhoan=" + tenTaiKhoan + ", matKhau=" + matKhau + '}';
    }

    
}
