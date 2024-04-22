/*
 * @ (#) CongDoanImpl.java 1.0 22/04/2024
 * copyright (c) 2024 KhanhNguyen. All right reserved
 */

package dao.impl;

import dao.CongDoanDao;
import entity.CongDoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.ParameterMode;

import java.util.List;

/*
 * @description: class CongDoanImpl thực thi interface CongDoanDao
 * @author: NguyenKhanhAn
 * @date: 22/04/2024
 * @version: 1.0
 */
public class CongDoanImpl implements CongDoanDao {
    private EntityManager em;
    public CongDoanImpl(EntityManager em){
        this.em = em;
        // tao ma cong doan tiep theo
    }

    @Override
    public boolean them(CongDoan cd) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(cd);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean sua(CongDoan cd) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(cd);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public CongDoan timKiem(String id) {
        return em.find(CongDoan.class, id);
    }

    @Override
    public List<CongDoan> layDanhSachTheoMaSanPham(String maSanPham) {
        return em.createNativeQuery("SELECT * FROM CongDoan WHERE maSanPham = ?", CongDoan.class)
                .setParameter(1, maSanPham)
                .getResultList();
    }

    @Override
    public boolean capNhatTrangThaiCongDoan() {
        String sql = "UPDATE CongDoan\r\n"
                + "SET trangThai = 1\r\n"
                + "FROM (\r\n"
                + "    SELECT maCongDoan, SUM(soLuongHoanThanh) as tongSoLuong\r\n"
                + "    FROM BangChamCongCongNhan\r\n"
                + "    GROUP BY maCongDoan\r\n"
                + ") AS subquery\r\n"
                + "WHERE CongDoan.maCongDoan = subquery.maCongDoan\r\n"
                + "AND subquery.tongSoLuong = CongDoan.soLuongThanhPhan";
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.createNativeQuery(sql).executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean capNhatTrangThaiSanPham() {
        String sql = "UPDATE SanPham\r\n"
                + "SET trangThaiSanPham = 1\r\n"
                + "WHERE maSanPham = ?\r\n"
                + "AND (SELECT COUNT(*) FROM CongDoan WHERE maSanPham = ? AND trangThai = 1) = 4;\r\n"
                + "";
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.createNativeQuery(sql).executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String layMaCongDoanTiepTheo(String ma) {
        String maCongDoan = null;
        try {
            String sql = "{ ? = call dbo.layMaCongDoanTiepTheo(?) }";
            maCongDoan = (String) em.createStoredProcedureQuery(sql)
                    .registerStoredProcedureParameter(1, String.class, ParameterMode.OUT)
                    .setParameter(2, ma)
                    .getOutputParameterValue(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maCongDoan;
    }

    @Override
    public int laySoLuongThanhPhanTheoMaCongDoan(String maCongDoan) {
        int soLuongThanhPhan = 0;
        String sql = "SELECT [soLuongThanhPhan] FROM [CongDoan] WHERE [maCongDoan] = ?";
        try {
            soLuongThanhPhan = (int) em.createNativeQuery(sql)
                    .setParameter(1, maCongDoan)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soLuongThanhPhan;
    }
}
