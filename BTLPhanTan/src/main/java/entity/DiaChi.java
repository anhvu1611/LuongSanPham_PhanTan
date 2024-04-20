package entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "DiaChi")
public class DiaChi implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8521375717110623899L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maDiaChi", nullable = false)
    private int maDiaChi;
    @Column(nullable = true, columnDefinition = "NVARCHAR(255)")
    private String soNha;
    @Column(nullable = true, columnDefinition = "NVARCHAR(255)")
    private String tenThiXa;
    @Column(nullable = true, columnDefinition = "NVARCHAR(255)")
    private String tenQuanHuyen;
    @Column(nullable = true, columnDefinition = "NVARCHAR(255)")
    private String tenTinhThanh;
	public int getMaDiaChi() {
		return maDiaChi;
	}
	
	public DiaChi() {
		// TODO Auto-generated constructor stub
	}
	public DiaChi(String soNha, String tenThiXa, String tenQuanHuyen, String tenTinhThanh) {
		super();
		this.soNha = soNha;
		this.tenThiXa = tenThiXa;
		this.tenQuanHuyen = tenQuanHuyen;
		this.tenTinhThanh = tenTinhThanh;
	}


	public void setMaDiaChi(int maDiaChi) {
		this.maDiaChi = maDiaChi;
	}
	public String getSoNha() {
		return soNha;
	}
	public void setSoNha(String soNha) {
		this.soNha = soNha;
	}
	public String getTenThiXa() {
		return tenThiXa;
	}
	public void setTenThiXa(String tenThiXa) {
		this.tenThiXa = tenThiXa;
	}
	public String getTenQuanHuyen() {
		return tenQuanHuyen;
	}
	public void setTenQuanHuyen(String tenQuanHuyen) {
		this.tenQuanHuyen = tenQuanHuyen;
	}
	public String getTenTinhThanh() {
		return tenTinhThanh;
	}
	public void setTenTinhThanh(String tenTinhThanh) {
		this.tenTinhThanh = tenTinhThanh;
	}

	@Override
	public String toString() {
		return soNha + " "+tenThiXa +" "+tenQuanHuyen+" "+tenTinhThanh;
	}
    
    

}