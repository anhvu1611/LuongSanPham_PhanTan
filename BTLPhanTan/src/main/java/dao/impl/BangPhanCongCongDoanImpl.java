/*
 * @ (#) BangPhanCongCongDoanImpl.java 1.0 24/04/2024
 * copyright (c) 2024 KhanhNguyen. All right reserved
 */

package dao.impl;

import dao.BangPhanCongCongDoanDao;
import entity.BangPhanCongCongDoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * @description: class BangPhanCongCongDoanImpl thực thi interface BangPhanCongCongDoanDao thao tác với bảng BangPhanCongCongDoan trong database
 * @author: NguyenKhanhAn
 * @date: 24/04/2024
 * @version: 1.0
 */
public class BangPhanCongCongDoanImpl implements BangPhanCongCongDoanDao {
    private EntityManager em;
    public BangPhanCongCongDoanImpl(EntityManager em){
        this.em = em;
    }
    
    @Override
	public int laySoLuongDaChamCongTheoMaCongDoan(String maCongDoan) {
		// TODO Auto-generated method stub
    	int soLuong = 0;
    	String sql = "select sum(soLuongHoanThanh)  from BangChamCongCongNhan where maCongDoan = ?";
    	try {
    		soLuong = (int) em.createNativeQuery(sql)
                .setParameter(1, maCongDoan)
                .getSingleResult();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
		return 0;
	}
    
    @Override
    public void themPhanCongCongDoan(BangPhanCongCongDoan phanCong) {
//        EntityTransaction tx = em.getTransaction();
//        try {
//            tx.begin();
//            em.persist(phanCong);
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//            e.printStackTrace();
//        }
    	String sql = "INSERT INTO BangPhanCongCongDoan" +
	                " VALUES (?, ?, ?, ?, ?)";
    	EntityTransaction tx = em.getTransaction();
    	try {
    		tx.begin();
    		em.createNativeQuery(sql)
    				.setParameter(1, java.sql.Date.valueOf(phanCong.getNgayPhanCong()))
    				.setParameter(2, phanCong.getSoLuongConLai())
    				.setParameter(3, phanCong.getSoLuongPhanCong())
    				.setParameter(4, phanCong.getCongDoan().getMaCongDoan())
    				.setParameter(5, phanCong.getCongNhanDamNhan().getMaNhanSu())
    				.executeUpdate();
    		tx.commit();
    	} catch (Exception e) {
    		tx.rollback();
    		e.printStackTrace();
    	}
    	
    }

    @Override
    public void suaPhanCongCongDoan(BangPhanCongCongDoan phanCong) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(phanCong);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void xoaPhanCongCongDoanVaKhoiDs(String maCongDoan, String maCongNhan) {
        String sql = "DELETE FROM [QuanLyLuongSanPham].[dbo].[BangPhanCongCongDoan] " +
                "WHERE [maCongDoan] = ? AND [maCongNhan] = ?";
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.createNativeQuery(sql)
                    .setParameter(1, maCongDoan)
                    .setParameter(2, maCongNhan)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<BangPhanCongCongDoan> layDanhSachPhanCongCongDoanTheoMaCongDoan(String maCongDoan) {
        String sql = "SELECT [maCongDoan], [maNhanSu], [soLuongConLai], [soLuongPhanCong], [ngayPhanCong] FROM BangPhanCongCongDoan WHERE maCongDoan = ?";
        try {
            return (ArrayList<BangPhanCongCongDoan>) em.createNativeQuery(sql, BangPhanCongCongDoan.class)
                    .setParameter(1, maCongDoan)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BangPhanCongCongDoan> layDanhSachPhanCongCN() {
        String sql = "select * from BangPhanCongCongDoan where soLuongConLai >0";
        try {
            return em.createNativeQuery(sql, BangPhanCongCongDoan.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int laySoLuongPhanCong(String maCongDoan) {
        String sql = "SELECT COUNT(*) AS soDong FROM BangPhanCongCongDoan WHERE maCongDoan = ?";
        try {
            return (int) em.createNativeQuery(sql)
                    .setParameter(1, maCongDoan)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public BangPhanCongCongDoan timPhanCongCongDoanTheoMaCongNhan(String maCongNhan) {
        String sql = "select * from BangPhanCongCongDoan where maCongNhan = ?";
        try {
            return (BangPhanCongCongDoan) em.createNativeQuery(sql, BangPhanCongCongDoan.class)
                    .setParameter(1, maCongNhan)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BangPhanCongCongDoan timPhanCongCongDoanTheoMaCongNhanVaNgayCham(String maCongNhan, Date ngayCham) {
        String sql = "select * from BangPhanCongCongDoan where maCongNhan = ? ";
        try {
            return (BangPhanCongCongDoan) em.createNativeQuery(sql, BangPhanCongCongDoan.class)
                    .setParameter(1, maCongNhan)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BangPhanCongCongDoan timTheoMaCongNhanVaMaCongDoan(String maCongNhan, String maCongDoan) {
        String sql = "select * from BangPhanCongCongDoan where maCongNhan = ? and maCongDoan = ?";
        try {
            return (BangPhanCongCongDoan) em.createNativeQuery(sql, BangPhanCongCongDoan.class)
                    .setParameter(1, maCongNhan)
                    .setParameter(2, maCongDoan)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean capNhatSoLuongBangCongDoan(int soLuongConLai, String maCongDoan, String maCongNhan) {
        String sql = "update BangPhanCongCongDoan set soLuongConLai = ? where maCongDoan = ? and maCongNhan = ?";
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.createNativeQuery(sql)
                    .setParameter(1, soLuongConLai)
                    .setParameter(2, maCongDoan)
                    .setParameter(3, maCongNhan)
                    .executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<BangPhanCongCongDoan> layDanhSachPhanCongCongDoan() {
        String sql = "SELECT TOP (1000) [maCongDoan]\r\n"
                + "      ,[maCongNhan]\r\n"
                + "      ,[tenCongDoan]\r\n"
                + "      ,[tenCongNhan]\r\n"
                + "      ,[soLuongPhanCong]\r\n"
                + "      ,[ngayPhanCong]\r\n"
                + "      ,[maSanPham]\r\n"
                + "      ,[tenSanPham]\r\n"
                + "  FROM [QuanLyLuongSanPham].[dbo].[BangPhanCongCongDoan]";
        try {
            return (ArrayList<BangPhanCongCongDoan>) em.createNativeQuery(sql, BangPhanCongCongDoan.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
}
