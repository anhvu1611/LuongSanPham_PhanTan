package dao.impl;

import dao.CongDoanDao;
import entity.CongDoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.ArrayList;

public class CongDoanImpl implements CongDoanDao {
    private EntityManager em;

    public CongDoanImpl() {
        em = Persistence.createEntityManagerFactory("BTLPhanTan")
                .createEntityManager();
    }
    @Override
    public CongDoan timCongDoanTheoMa(String maCongDoan) {
        return em.find(CongDoan.class, maCongDoan);
    }

    @Override
    public CongDoan timCongDoanKhiCoMa(String maCongDoan) {
        return null;
    }

    @Override
    public ArrayList<CongDoan> layDanhSachCongDoanKhiCoMaSanPham(String maSanPham) {
        return null;
    }

    @Override
    public boolean themCongDoan(CongDoan congDoan) {
        return false;
    }

    @Override
    public boolean suaCongDoan(CongDoan congDoan) {
        return false;
    }

    @Override
    public boolean updateTrangThaiCongDoan() {
        return false;
    }

    @Override
    public boolean updateTrangThaiSanPham(String maSanPham) {
        return false;
    }
}
