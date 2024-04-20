package dao.impl;

import java.io.Serializable;
import java.util.List;

import dao.PhongBanDao;
import entity.PhongBan;
import jakarta.persistence.EntityManager;

public class PhongBanImpl implements Serializable , PhongBanDao{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9030079583519158178L;
	private EntityManager em;

    public PhongBanImpl(EntityManager em){
        this.em = em;
    }
	@Override
	public List<PhongBan> layDanhSach() {
		return em.createNamedQuery("PhongBan.layDanhSach", PhongBan.class)
				.getResultList();
	}

}
