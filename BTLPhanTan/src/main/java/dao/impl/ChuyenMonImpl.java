package dao.impl;

import java.util.List;

import dao.ChuyenMonDao;
import entity.ChuyenMon;
import jakarta.persistence.EntityManager;

public class ChuyenMonImpl implements ChuyenMonDao{
	private EntityManager em;

    public ChuyenMonImpl(EntityManager em){
        this.em = em;
    }
	@Override
	public List<ChuyenMon> layDanhSach() {
		return em.createNamedQuery("ChuyenMon.findAll", ChuyenMon.class)
				.getResultList();
	}

}
