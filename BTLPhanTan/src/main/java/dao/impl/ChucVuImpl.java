package dao.impl;

import java.util.List;

import dao.ChucVuDao;
import entity.ChucVu;
import jakarta.persistence.EntityManager;

public class ChucVuImpl implements ChucVuDao {
	private EntityManager em;

    public ChucVuImpl(EntityManager em){
        this.em = em;
    }
	@Override
	public List<ChucVu> layDanhSachChucVu() {
		return em.createNamedQuery("ChucVu.findAll", ChucVu.class).getResultList();
	}

}
