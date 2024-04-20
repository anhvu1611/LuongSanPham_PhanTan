package dao.impl;

import dao.GenericDao;
import dao.TaiKhoanDao;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;

public class TaiKhoanImpl implements Serializable, TaiKhoanDao, GenericDao<TaiKhoan> {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5466875990998021136L;
	private EntityManager em;

    public TaiKhoanImpl(EntityManager em){
        this.em = em;
    }
    @Override
    public TaiKhoan login(String tk, String mk) {
        return em.createNamedQuery("TaiKhoan.login", TaiKhoan.class)
                .setParameter("tk", tk)
                .setParameter("mk", mk)
                .getResultList()
                .stream()
                .findFirst().orElse(null);
    }

    @Override
    public boolean doMatKhau(String tk, String mkMoi) {
        TaiKhoan khoan = timKiem(tk);
        khoan.setMatKhau(mkMoi);
        return sua(khoan);
        
    }
	@Override
	public TaiKhoan timTheoTenTaiKhoan(String tk) {
		return em.createNamedQuery("TaiKhoan.timTheoTenTaiKhoan", TaiKhoan.class)
				.setParameter("tk", tk)
				.getResultList()
                .stream()
                .findFirst().orElse(null);
	}
	@Override
	public boolean them(TaiKhoan entity) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean sua(TaiKhoan entity) {
		EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            em.merge(entity);

            tx.commit();

            return true;
        }catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        return false;
	}
	@Override
	public boolean xoa(String id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public TaiKhoan timKiem(String id) {
		return em.find(TaiKhoan.class, id);
	}
	@Override
	public List<TaiKhoan> layDanhSach() {
		// TODO Auto-generated method stub
		return null;
	}
}
