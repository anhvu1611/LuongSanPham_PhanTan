package dao.impl;

import dao.BangPhanCongCongDoanDao;
import dao.CongDoanDao;
import dao.CongNhanDao;
import entity.BangPhanCongCongDoan;
import entity.CongDoan;
import entity.CongNhan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class BangPhanCongCongDoanImpl implements BangPhanCongCongDoanDao {
    private EntityManager em;
    private CongNhanDao congNhanDao = new CongNhanImpl();
    private CongDoanDao congDoanDao = new CongDoanImpl();


    public BangPhanCongCongDoanImpl() {
        em = Persistence.createEntityManagerFactory("BTLPhanTan")
                .createEntityManager();
    }

    @Override
    public List<BangPhanCongCongDoan> layDanhSachBangPhanCongCN() {
        return null;
    }

    @Override
    public BangPhanCongCongDoan timPhanCongCongDoanTheoMaCongNhan(String maCongNhan) {
        AtomicReference<BangPhanCongCongDoan> bangPhanCongCongDoan = null;
        String sql = "select * from BangPhanCongCongDoan where maNhanSu = :maCongNhan" ;
        List<?> list = em.createNativeQuery(sql)
                .setParameter("maCongNhan", maCongNhan)
                .getResultList();
        list.stream()
                .map(e -> (Object[]) e)
                .forEach(e -> {
                    Date ngayPhanCong = (Date) e[0];
                    int soLuongConLai = (int) e[1];
                    int soLuongPhanCong = (int) e[2];
                    String maCongDoan = (String) e[3];
                    CongDoan congDoan = congDoanDao.timCongDoanTheoMa(maCongDoan);
                    String maCongNhan1 = (String) e[4];
                    CongNhan congNhan = congNhanDao.timKiem(maCongNhan1);
                    BangPhanCongCongDoan bangPhanCongCongDoan1 = new BangPhanCongCongDoan(soLuongPhanCong, congDoan, congNhan , soLuongConLai);
                    bangPhanCongCongDoan.set(bangPhanCongCongDoan1);
                });
        return bangPhanCongCongDoan.get();
    }

    @Override
    public BangPhanCongCongDoan timPhanCongCongDoanTheoMaCongNhanVaNgayCham(String maCongNhan, Date ngayCham) {
        return null;
    }

    @Override
    public BangPhanCongCongDoan timTheoMaCongNhanVaMaCongDoan(String maCongNhan, String maCongDoan) {
        return null;
    }

    @Override
    public boolean capNhatSoLuongBangCongDoan(int soLuongConLai, String maCongDoan, String maCongNhan) {
        return false;
    }

    @Override
    public ArrayList<BangPhanCongCongDoan> layDanhSachPhanCongCongDoan() {
        return null;
    }
}
