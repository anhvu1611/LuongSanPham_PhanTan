package dao.impl;

import java.io.Serializable;
import java.util.List;

import dao.DiaChiDao;
import entity.DiaChi;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DiaChiImpl implements Serializable, DiaChiDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6149394008068050036L;
	EntityManager em;
	
	public DiaChiImpl(EntityManager em) {
		// TODO Auto-generated constructor stub
		this.em = em;
	}

	@Override
	public String[] layDanhSachTinhThanh() {
	    @SuppressWarnings("unchecked")
		List<String> result = em.createNativeQuery("SELECT t.ten FROM TinhThanh t", String.class)
	                            .getResultList();
	    return result.toArray(new String[0]);
	}

	@Override
	public String[] layDanhSachQuanHuyen(String tinhThanh) {
		@SuppressWarnings("unchecked")
		List<String> result = em.createNativeQuery("select Q.ten from QuanHuyen Q join TinhThanh t on t.tinh_id = q.tinh_id "
				+ "where t.ten = :ten", String.class)
				.setParameter("ten", tinhThanh)
                .getResultList();
		return result.toArray(new String[0]);
	}

	@Override
	public String[] layDanhSachPhuong(String tinhThanh, String quanHuyen) {
	    @SuppressWarnings("unchecked")
	    List<String> result = em.createNativeQuery("select P.ten from Phuong P \r\n"
	            + "join QuanHuyen Q on Q.quanHuyen_id = P.quanHuyen_id\r\n"
	            + "where Q.ten = :ten2", String.class)
	            .setParameter("ten2", quanHuyen)
	            .getResultList();
	    return result.toArray(new String[0]);
	}

	@Override
	public void themDiaChi(DiaChi dc) {
		EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.persist(dc);
            tx.commit();
        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();

        }
	}


}
