package dao.impl;

import dao.BangChamCongNhanVienDao;
import entity.BangChamCongNhanVien;
import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import Enum.*;

import java.lang.reflect.Array;
import java.util.*;

public class BangChamCongNhanVienImpl implements BangChamCongNhanVienDao {

    private EntityManager em;

    public BangChamCongNhanVienImpl() {
        em = Persistence.createEntityManagerFactory("BTLPhanTan")
                .createEntityManager();
    }

    @Override
    public ArrayList<BangChamCongNhanVien> layDanhSachChamCongNV() {
        ArrayList<BangChamCongNhanVien> dsCCNV = new ArrayList<>();
        List<?> list = em.createNativeQuery("SELECT * FROM BangChamCongNhanVien").getResultList();
        list.stream()
                .map(e -> (Object[]) e)
                .forEach(e -> {
                    String maChamCong = (String) e[5];
                    Date ngayChamCong = (Date) e[3];
                    String maNhanVien = (String) e[6];
                    NhanVien nv = em.find(NhanVien.class, maNhanVien);
                    TrangThai trangThai = null;
                    String trangThaiNV = (String) e[2];
                    if(trangThaiNV.equalsIgnoreCase("Đi làm")) {
                        trangThai = TrangThai.COLAM;
                    }else if(trangThaiNV.equalsIgnoreCase("Đi trễ")) {
                        trangThai = TrangThai.DITRE;
                    }else if(trangThaiNV.equalsIgnoreCase("Nghỉ có phép")) {
                        trangThai = TrangThai.NGHICOPHEP;
                    }else {
                        trangThai = TrangThai.NGHIKHONGPHEP;
                    }
                    String ghiChu = (String) e[3];
                    CaLamNhanVien caLam = null;
                    String caLamNhanVien = (String) e[0];
                    if(caLamNhanVien.equalsIgnoreCase("Full-time")) {
                        caLam = CaLamNhanVien.FULLTIME;
                    }else if(caLamNhanVien.equalsIgnoreCase("Part-time")) {
                        caLam = CaLamNhanVien.PARTTIME;
                    }else {
                        caLam = CaLamNhanVien.TANGCA;
                    }
                    NhanVien nvCham = em.find(NhanVien.class, em.find(NhanVien.class, (String) e[7]));

                    BangChamCongNhanVien bangChamCong = new BangChamCongNhanVien(maChamCong,  ngayChamCong,  nv, trangThai, ghiChu, caLam, nvCham);

                    dsCCNV.add(bangChamCong);
                });
        return dsCCNV;
    }

    @Override
    public ArrayList<BangChamCongNhanVien> layDanhSachChamCongTheoPhongBanHomNay(String phongBan) {
        ArrayList<BangChamCongNhanVien> dsCCNV = new ArrayList<>();
        String sql = "select caLam, soGioTangCa, trangThai, ngayChamCong, ghiChu, maChamCong, maNhanVienChamCong, maNhanVienDuocChamCong\r\n"
                + "from BangChamCongNhanVien bccnv\r\n"
                + "left join NhanVien nv on bccnv.maNhanVienDuocChamCong = nv.maNhanVien\r\n"
                + "left join PhongBan pb on nv.maPhongBan = pb.maPhongBan\r\n"
                + "where ngayChamCong=FORMAT(GETDATE(), 'yyyy-MM-dd') and pb.tenPhongBan = ?1";
        List<?> list = em.createNativeQuery(sql)
                .setParameter(1, phongBan)
                .getResultList();
        list.stream()
                .map(e -> (Object[]) e)
                .forEach(e -> {
                    String maChamCong = (String) e[5];
                    Date ngayChamCong = (Date) e[3];
                    String maNhanVien = (String) e[6];
                    NhanVien nv = em.find(NhanVien.class, maNhanVien);
                    TrangThai trangThai = null;
                    String trangThaiNV = (String) e[2];
                    if(trangThaiNV.equalsIgnoreCase("Đi làm")) {
                        trangThai = TrangThai.COLAM;
                    }else if(trangThaiNV.equalsIgnoreCase("Đi trễ")) {
                        trangThai = TrangThai.DITRE;
                    }else if(trangThaiNV.equalsIgnoreCase("Nghỉ có phép")) {
                        trangThai = TrangThai.NGHICOPHEP;
                    }else {
                        trangThai = TrangThai.NGHIKHONGPHEP;
                    }
                    String ghiChu = (String) e[3];
                    CaLamNhanVien caLam = null;
                    String caLamNhanVien = (String) e[0];
                    if(caLamNhanVien.equalsIgnoreCase("Full-time")) {
                        caLam = CaLamNhanVien.FULLTIME;
                    }else if(caLamNhanVien.equalsIgnoreCase("Part-time")) {
                        caLam = CaLamNhanVien.PARTTIME;
                    }else {
                        caLam = CaLamNhanVien.TANGCA;
                    }
                    NhanVien nvCham = em.find(NhanVien.class, em.find(NhanVien.class, (String) e[7]));

                    BangChamCongNhanVien bangChamCong = new BangChamCongNhanVien(maChamCong,  ngayChamCong,  nv, trangThai, ghiChu, caLam, nvCham);

                    dsCCNV.add(bangChamCong);
                });
        return null;
    }

    @Override
    public ArrayList<BangChamCongNhanVien> layDanhSachChamCongHomNay(String homNay) {
        return null;
    }

    @Override
    public ArrayList<NhanVien> layDanhSachChuaCham() {
        return null;
    }

    @Override
    public ArrayList<String> layDanhSachMaNhanVien() {
        return null;
    }

    @Override
    public ArrayList<String> layDanhSachMaNhanVienDaChamCong() {
        return null;
    }

    @Override
    public ArrayList<String> layDanhSachMaNhanVienChuaChamCong() {
        return null;
    }

    @Override
    public boolean themChamCongNhanVien(BangChamCongNhanVien ccnvMoi) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(ccnvMoi);
            tx.commit();
            return true;
        } catch (Exception e) {
            em.close();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean xoaChamCongNhanVien(String maChamCong) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            BangChamCongNhanVien ccnv = em.find(BangChamCongNhanVien.class, maChamCong);
            em.remove(ccnv);
            tx.commit();
            return true;
        } catch (Exception e) {
            em.close();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean capNhatChamCongNV(BangChamCongNhanVien nhanVienDuocCapNhat) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(nhanVienDuocCapNhat);
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
        return em.createQuery("SELECT MAX(maChamCong) FROM BangChamCongNhanVien").getResultList().stream().findFirst().orElse(null).toString();
    }

    @Override
    public ArrayList<String> layDanhSachMaNhanVienTheoPhongBan(String tenPhongBan) {
        ArrayList<String> dsMaNhanVienTheoPhongBan = new ArrayList<>();
        em.createQuery("select nv.maNhanSu from NhanVien nv left join PhongBan pb on nv.ban.maPhongBan = pb.maPhongBan where pb.tenPhongBan = ?1")
                .setParameter(1, tenPhongBan)
                .getResultList()
                .forEach(e -> dsMaNhanVienTheoPhongBan.add(e.toString()));
        return dsMaNhanVienTheoPhongBan;
    }

    @Override
    public ArrayList<String> layDanhSachMaNhanVienChuaChamCongTheoPhongBan(String tenPhongBan) {
        ArrayList<String> dsMaNhanVienTheoPhongBan = layDanhSachMaNhanVienTheoPhongBan(tenPhongBan);
        ArrayList<String> dsMaNhanVienDaChamCong = layDanhSachMaNhanVienDaChamCong();
        ArrayList<String> dsMaNhanVienChuaChamCong = new ArrayList<>();
        if(dsMaNhanVienDaChamCong.isEmpty()) {
            return dsMaNhanVienTheoPhongBan;
        }else {
            dsMaNhanVienChuaChamCong = new ArrayList<>(new HashSet<>(dsMaNhanVienTheoPhongBan));
            dsMaNhanVienChuaChamCong.removeAll(dsMaNhanVienDaChamCong);
            Collections.sort(dsMaNhanVienChuaChamCong);
            return dsMaNhanVienChuaChamCong;
        }

    }

    @Override
    public ArrayList<NhanVien> layDanhSachChuaChamTheoPhongBan(String tenPhongBan) {
        ArrayList<String> dsMaChuaCham = layDanhSachMaNhanVienChuaChamCongTheoPhongBan(tenPhongBan);
        ArrayList<NhanVien> dsNhanVienChuaCham = new ArrayList<>();
        for(String nvChuaCham : dsMaChuaCham) {
            NhanVien nv = em.find(NhanVien.class, nvChuaCham);
            if(dsMaChuaCham.contains(nv)) {
                dsNhanVienChuaCham.remove(nv);
            }
            dsMaChuaCham.add(nvChuaCham);
        }
        return dsNhanVienChuaCham;
    }
}
