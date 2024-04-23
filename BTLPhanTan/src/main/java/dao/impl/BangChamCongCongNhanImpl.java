package dao.impl;

import dao.BangChamCongCongNhanDao;
import dao.BangPhanCongCongDoanDao;
import dao.CongNhanDao;
import dao.NhanVienDao;
import entity.BangChamCongCongNhan;
import entity.BangPhanCongCongDoan;
import entity.CongNhan;
import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import Enum.CaLamCongNhan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BangChamCongCongNhanImpl implements BangChamCongCongNhanDao {
    private EntityManager em;
    private NhanVienDao nhanVien_Dao = new NhanVienImpl();
    private CongNhanDao congNhan_Dao = new CongNhanImpl();
    private BangPhanCongCongDoanDao bangPhanCongCongDoanDao = new BangPhanCongCongDoanImpl();


    public BangChamCongCongNhanImpl() {
        em = Persistence.createEntityManagerFactory("BTLPhanTan")
                .createEntityManager();
    }

    @Override
    public int laySoLuongDaChamCongTheoMaCongDoan(String maCongDoan) {
        int soLuong = 0;
        em.createNativeQuery("select  from BangChamCongCongNhan b");
        return 0;
    }

    @Override
    public ArrayList<BangChamCongCongNhan> timChamCongTheoMa(String maCongNhan) {
        ArrayList<BangChamCongCongNhan> dsCCCN = new ArrayList<>();
        String query = "select * from BangChamCongCongNhan where maCongNhan= :maCongNhan and soLuongSanPhamHoanThanh!=0";

        List<?> list = em.createNativeQuery(query).setParameter("maCongNhan", maCongNhan).getResultList();

        list.stream()
                .map(o -> (Object[]) o)
                .forEach(obj -> {
                    //BangChamCongCongNhan bcccn = new BangChamCongCongNhan();
                    //bcccn.setMaBangChamCong(obj[0].toString());
                    String maCCCN = obj[4].toString();
                    Date ngayChamCong = (Date) obj[3];
                    boolean trangThai = (boolean) obj[2];
                    int soLuongSanPhamHoanThanh = (int) obj[1];
                    String maNV = obj[6].toString();
                    NhanVien nguoiCham = nhanVien_Dao.timKiem(maNV);
                    CongNhan congNhan = congNhan_Dao.timKiem(maCongNhan);
                    CaLamCongNhan caLam = null;
                    String caLamConggNhan = obj[0].toString();
                    if(caLamConggNhan.equalsIgnoreCase("Ca Một")){
                        caLam = CaLamCongNhan.CAMOT;
                    }else if(caLamConggNhan.equalsIgnoreCase("Ca Hai")){
                        caLam = CaLamCongNhan.CAHAI;
                    }else {
                        caLam = CaLamCongNhan.CABA;
                    }
                    BangPhanCongCongDoan bangPhanCong = bangPhanCongCongDoanDao.timPhanCongCongDoanTheoMaCongNhan(maCCCN);
                    BangChamCongCongNhan bcccn = new BangChamCongCongNhan(maCCCN, ngayChamCong, trangThai, soLuongSanPhamHoanThanh, nguoiCham, congNhan, caLam, bangPhanCong);
                    dsCCCN.add(bcccn);
                });
        return dsCCCN;
    }

    @Override
    public ArrayList<BangChamCongCongNhan> timChamCongTheoThangVaTheoMa(String maCongNhan, int thang) {
        ArrayList<BangChamCongCongNhan> dsCCCN = new ArrayList<>();
        String query = "select * from BangChamCongCongNhan where maNhanSu= :maCongNhan and soLuongSanPhamHoanThanh!=0 and MONTH(ngayChamCong)= :thang";
        List<?> list = em.createNativeQuery(query)
                .setParameter("maCongNhan", maCongNhan)
                .setParameter("thang", thang)
                .getResultList();
        list.stream()
                .map(o -> (Object[]) o)
                .forEach(obj -> {
                    String maCCCN = obj[4].toString();
                    Date ngayChamCong = (Date) obj[3];
                    boolean trangThai = (boolean) obj[2];
                    int soLuongSanPhamHoanThanh = (int) obj[1];
                    String maNV = obj[6].toString();
                    NhanVien nguoiCham = nhanVien_Dao.timKiem(maNV);
                    CongNhan congNhan = congNhan_Dao.timKiem(maCongNhan);
                    CaLamCongNhan caLam = null;
                    String caLamConggNhan = obj[0].toString();
                    if(caLamConggNhan.equalsIgnoreCase("Ca Một")){
                        caLam = CaLamCongNhan.CAMOT;
                    }else if(caLamConggNhan.equalsIgnoreCase("Ca Hai")){
                        caLam = CaLamCongNhan.CAHAI;
                    }else {
                        caLam = CaLamCongNhan.CABA;
                    }
                    BangPhanCongCongDoan bangPhanCong = bangPhanCongCongDoanDao.timPhanCongCongDoanTheoMaCongNhan(maCCCN);
                    BangChamCongCongNhan bcccn = new BangChamCongCongNhan(maCCCN, ngayChamCong, trangThai, soLuongSanPhamHoanThanh, nguoiCham, congNhan, caLam, bangPhanCong);
                    dsCCCN.add(bcccn);
                });

        return dsCCCN;
    }

    @Override
    public ArrayList<BangChamCongCongNhan> layDanhSachChamCongCN(int thang) {
        String query = "select * from BangChamCongCongNhan where MONTH(ngayChamCong)= :thang";

        ArrayList<BangChamCongCongNhan> dsCCCN = new ArrayList<>();
        List<?> list = em.createNativeQuery(query)
                .setParameter("thang", thang)
                .getResultList();
        list.stream()
                .map(o -> (Object[]) o)
                .forEach(obj -> {
                    String maCCCN = obj[4].toString();
                    Date ngayChamCong = (Date) obj[3];
                    boolean trangThai = (boolean) obj[2];
                    int soLuongSanPhamHoanThanh = (int) obj[1];
                    String maNV = obj[6].toString();
                    NhanVien nguoiCham = nhanVien_Dao.timKiem(maNV);
                    String maCongNhan = obj[5].toString();
                    CongNhan congNhan = congNhan_Dao.timKiem(maCongNhan);
                    CaLamCongNhan caLam = null;
                    String caLamConggNhan = obj[0].toString();
                    if(caLamConggNhan.equalsIgnoreCase("Ca Một")){
                        caLam = CaLamCongNhan.CAMOT;
                    }else if(caLamConggNhan.equalsIgnoreCase("Ca Hai")){
                        caLam = CaLamCongNhan.CAHAI;
                    }else {
                        caLam = CaLamCongNhan.CABA;
                    }
                    BangPhanCongCongDoan bangPhanCong = bangPhanCongCongDoanDao.timPhanCongCongDoanTheoMaCongNhan(maCCCN);
                    BangChamCongCongNhan bcccn = new BangChamCongCongNhan(maCCCN, ngayChamCong, trangThai, soLuongSanPhamHoanThanh, nguoiCham, congNhan, caLam, bangPhanCong);
                    dsCCCN.add(bcccn);
                });
        return dsCCCN;
    }

    @Override
    public ArrayList<BangChamCongCongNhan> layDanhSachChamCongCNHomNay(String homNay) {
        String query = "select * from BangChamCongCongNhan where ngayChamCong= :homNay";
        ArrayList<BangChamCongCongNhan> dsCCCN = new ArrayList<>();
        List<?> list = em.createNativeQuery(query)
                .setParameter("homNay", homNay)
                .getResultList();
        list.stream()
                .map(o -> (Object[]) o)
                .forEach(obj -> {
                    String maCCCN = obj[4].toString();
                    Date ngayChamCong = (Date) obj[3];
                    boolean trangThai = (boolean) obj[2];
                    int soLuongSanPhamHoanThanh = (int) obj[1];
                    String maNV = obj[6].toString();
                    NhanVien nguoiCham = nhanVien_Dao.timKiem(maNV);
                    String maCongNhan = obj[5].toString();
                    CongNhan congNhan = congNhan_Dao.timKiem(maCongNhan);
                    CaLamCongNhan caLam = null;
                    String caLamConggNhan = obj[0].toString();
                    if(caLamConggNhan.equalsIgnoreCase("Ca Một")){
                        caLam = CaLamCongNhan.CAMOT;
                    }else if(caLamConggNhan.equalsIgnoreCase("Ca Hai")){
                        caLam = CaLamCongNhan.CAHAI;
                    }else {
                        caLam = CaLamCongNhan.CABA;
                    }
                    BangPhanCongCongDoan bangPhanCong = bangPhanCongCongDoanDao.timPhanCongCongDoanTheoMaCongNhan(maCCCN);
                    BangChamCongCongNhan bcccn = new BangChamCongCongNhan(maCCCN, ngayChamCong, trangThai, soLuongSanPhamHoanThanh, nguoiCham, congNhan, caLam, bangPhanCong);
                    dsCCCN.add(bcccn);
                });
        return null;
    }

    @Override
    public boolean themChamcongCongNhan(BangChamCongCongNhan cccnMoi, String maNguoiChamCong) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(cccnMoi);
            tx.commit();
            return true;
        } catch (Exception e) {
            em.close();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String layMaChamCongCaoNhat() {
        String query = "select max(maBangChamCong) from BangChamCongCongNhan";
        return em.createNativeQuery(query).getParameterValue(0).toString();
    }

    @Override
    public ArrayList<BangChamCongCongNhan> timChamCongTheoNguoiNhanLuongVaThangCham(String hoTen, int thang) {
        ArrayList<BangChamCongCongNhan> dsCCCN = new ArrayList<>();
        String query = "select * from BangChamCongCongNhan where ho+ ' '+ten = :hoTen and soLuongSanPhamHoanThanh>0 and MONTH(ngayChamCong)= :thang";
        List<?> list = em.createNativeQuery(query)
                .setParameter("hoTen", hoTen)
                .setParameter("thang", thang)
                .getResultList();
        list.stream()
                .map(o -> (Object[]) o)
                .forEach(obj -> {
                    String maCCCN = obj[4].toString();
                    Date ngayChamCong = (Date) obj[3];
                    boolean trangThai = (boolean) obj[2];
                    int soLuongSanPhamHoanThanh = (int) obj[1];
                    String maNV = obj[6].toString();
                    NhanVien nguoiCham = nhanVien_Dao.timKiem(maNV);
                    String maCongNhan = obj[5].toString();
                    CongNhan congNhan = congNhan_Dao.timKiem(maCongNhan);
                    CaLamCongNhan caLam = null;
                    String caLamConggNhan = obj[0].toString();
                    if(caLamConggNhan.equalsIgnoreCase("Ca Một")){
                        caLam = CaLamCongNhan.CAMOT;
                    }else if(caLamConggNhan.equalsIgnoreCase("Ca Hai")){
                        caLam = CaLamCongNhan.CAHAI;
                    }else {
                        caLam = CaLamCongNhan.CABA;
                    }
                    BangPhanCongCongDoan bangPhanCong = bangPhanCongCongDoanDao.timPhanCongCongDoanTheoMaCongNhan(maCCCN);
                    BangChamCongCongNhan bcccn = new BangChamCongCongNhan(maCCCN, ngayChamCong, trangThai, soLuongSanPhamHoanThanh, nguoiCham, congNhan, caLam, bangPhanCong);
                    dsCCCN.add(bcccn);
                });
        return dsCCCN;
    }
}
