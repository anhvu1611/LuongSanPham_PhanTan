package entity;

import java.io.Serializable;


public class BangPhanCongCongDoan_ID implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4431047187508892273L;
	private CongDoan congDoan;
    private CongNhan congNhanDamNhan;
	public CongDoan getCongDoan() {
		return congDoan;
	}
	public void setCongDoan(CongDoan congDoan) {
		this.congDoan = congDoan;
	}
	public CongNhan getCongNhanDamNhan() {
		return congNhanDamNhan;
	}
	public void setCongNhanDamNhan(CongNhan congNhanDamNhan) {
		this.congNhanDamNhan = congNhanDamNhan;
	}
	public BangPhanCongCongDoan_ID() {
		super();
	}
    
}
