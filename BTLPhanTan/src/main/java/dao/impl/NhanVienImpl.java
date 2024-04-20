package dao.impl;

import dao.GenericDao;
import dao.NhanVienDao;
import entity.ChucVu;
import entity.NhanVien;
import entity.PhongBan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.Date;
import java.util.List;

public class NhanVienImpl implements GenericDao<NhanVien>, NhanVienDao {
    private EntityManager em;

    public NhanVienImpl(EntityManager em){
        this.em = em;
        hamTaoMaNhanVien();
    }
    @Override
    public List<NhanVien> layDanhSach() {
        return em.createNamedQuery("NhanVien.findAll", NhanVien.class).getResultList();
    }


    @Override
    public boolean them(NhanVien entity) {
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.persist(entity);
            tx.commit();
            return true;
        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean sua(NhanVien entity) {
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
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            NhanVien course = em.find(NhanVien.class, id);
            em.remove(course);

            tx.commit();

            return true;
        }catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public NhanVien timKiem(String id) {
        return em.find(NhanVien.class, id);
    }
	@Override
	public String layEmailNhanVien(String maNhanVien) {
		NhanVien nv =  em.find(NhanVien.class, maNhanVien);
		if(nv == null)
			return null;
		return nv.getEmail();
	}
	@Override
	public String layMaNhanVienTiepTheo() {
		return em.createNativeQuery("SELECT [dbo].[layMaNhanVienTiepTheo]()")
				.getSingleResult().toString();
	}
	
	private String createQueryFindNhanVien( String ma, String ten, String ho, String cccd,
            String sdt, double thamNien, double heSo, ChucVu chucVu,
            String gioiTinh, PhongBan ban, String tinh, String quanHuyen,
            String thiXa, java.util.Date ngaySinh, java.util.Date ngayVaoLam, 
            String email, boolean bhyt, boolean bhxh, int lc) {
		StringBuilder queryBuilder = new StringBuilder
        		("SELECT maNhanSu FROM NhanVien nv"
        				+ " join DiaChi dc on dc.maDiaChi = nv.maDiaChi"
        				+ " join ChucVu cv on cv.maChucVu = nv.maChucVu"
        				+ " join PhongBan pb on pb.maPhongBan = nv.maPhongBan WHERE 1=1");

        if (ma != null && !ma.isEmpty()) {
            queryBuilder.append(" AND maNhanSu = '").append(ma.trim()).append("'");
        }

        if (ten != null &&!ten.equalsIgnoreCase("Nhập Tên") &&  !ten.isEmpty()) {
            queryBuilder.append(" AND ten like N'%").append(ten.trim()).append("%'");
        }

        if (ho != null && !ho.equalsIgnoreCase("Nhập Họ") &&  !ho.isEmpty()) {
            queryBuilder.append(" AND ho like N'%").append(ho.trim()).append("%'");
        }

        if (cccd != null && !cccd.equalsIgnoreCase("Căn cước") && !cccd.isEmpty()) {
        	queryBuilder.append(" AND CCCD = '").append(cccd.trim()).append("'");
        }

        if (sdt != null && sdt.equalsIgnoreCase("Số điện thoại") &&  !sdt.isEmpty()) {
        	queryBuilder.append(" AND soDienThoai = '").append(sdt.trim()).append("'");
        }
        System.out.println("Da toi cho nay");
        
        if (thamNien!=0 ) {
        	queryBuilder.append(" AND thamNien = ").append(thamNien);
        }
        
        if (heSo!=0) {
        	queryBuilder.append(" AND heSoLuong = ").append(heSo);
        }
        
        if(chucVu!= null) {
        	queryBuilder.append(" AND cv.maChucVu = '")
        	.append(chucVu.getMaChucVu().trim()).append("'");
        }
        
        if(gioiTinh != null) {
        	queryBuilder.append(" AND gioiTinh = N'")
        	.append(gioiTinh).append("'");
        }
        
        if(ban != null) {
        	queryBuilder.append(" AND pb.maPhongBan = '")
        	.append(ban.getMaPhongBan().trim()).append("'");
        }
        
        if (tinh != null && !tinh.isEmpty()) {
            queryBuilder.append(" AND tenTinhThanh = N'")
            .append(tinh.trim()).append("'");
        }
        
        if (quanHuyen != null && !quanHuyen.isEmpty()) {
            queryBuilder.append(" AND tenQuanHuyen =N'")
            .append(quanHuyen.trim()).append("'");
        }    
        
        if (thiXa != null && !thiXa.isEmpty()) {
            queryBuilder.append(" AND tenThiXa = N'").
            append(thiXa.trim()).append("'");
        } 
        
        if(ngaySinh != null) {
        	queryBuilder.append(" AND ngaySinh = '").
        	append(new Date(ngaySinh.getTime())).append("'");
        }
        
        if(ngayVaoLam != null) {
        	queryBuilder.append(" AND ngayVaoLam = '").
        	append(new Date(ngayVaoLam.getTime())).append("'");
        }
        
        if (email != null && !email.equalsIgnoreCase("Ngập email") && !email.isEmpty()) {
            queryBuilder.append(" AND email = N'").append(email.trim()).append("'");
        }
        
        if(lc==1) {
        	if(bhxh == true) {
            	queryBuilder.append(" AND BHXH = N'").append("Tham Gia").append("'");
            }else {
            	queryBuilder.append(" AND BHXH = N'").append("Không").append("'");
            }
        	if(bhyt == true) {
            	queryBuilder.append(" AND BHYT = N'").append("Tham Gia").append("'");
            }else {
            	queryBuilder.append(" AND BHYT = N'").append("Không").append("'");
            }
        }
        return queryBuilder.toString();
	}
	@Override
	public List<NhanVien> timNhanVienTheoTieuChi(String ma, String ten, String ho, String cccd, String sdt,
			double thamNien, double heSo, ChucVu chucVu, String gt, PhongBan ban, String tinh, String quanHuyen,
			String thiXa, Date ngaySinh, Date ngayVaoLam, String email, boolean bhyt, boolean bhxh, int lc) {
		String query = createQueryFindNhanVien(ma, ten, ho, cccd, sdt, thamNien, heSo, chucVu, gt, ban, tinh, quanHuyen, thiXa, ngaySinh, ngayVaoLam, email, bhyt, bhxh, lc);
		System.out.println(query);
		return null;
	}
	@Override
	public void hamTaoMaNhanVien() {
		String sql = "IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tinhLuongToanBoNhanVienTheoThangVaNam]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))\r\n"
				+ "BEGIN\r\n"
				+ "    EXEC('\r\n"
				+ "		CREATE FUNCTION [dbo].[layMaNhanVienTiepTheo]()\r\n"
				+ "		RETURNS NVARCHAR(10)\r\n"
				+ "		AS\r\n"
				+ "		BEGIN\r\n"
				+ "			DECLARE @nextId NVARCHAR(6);\r\n"
				+ "			DECLARE @lastId NVARCHAR(6);\r\n"
				+ "			SELECT @lastId = MAX(maNhanSu) FROM NhanVien;\r\n"
				+ "			IF @lastId IS NULL\r\n"
				+ "				SET @nextId = ''NV0001'';\r\n"
				+ "			ELSE\r\n"
				+ "			BEGIN\r\n"
				+ "				-- Tăng mã nhân viên cuối cùng lên 1 đơn vị và định dạng lại chuỗi kết quả\r\n"
				+ "				SET @nextId = ''NV'' + RIGHT(''0000'' + CAST(CAST(SUBSTRING(@lastId, 3, LEN(@lastId) - 2) AS INT) + 1 AS NVARCHAR(10)), 4);\r\n"
				+ "			END\r\n"
				+ "\r\n"
				+ "			RETURN @nextId;\r\n"
				+ "		END\r\n"
				+ "		GO\r\n"
				+ "    ')\r\n"
				+ "END\r\n"
				+ "\r\n";
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.createNativeQuery(sql).executeUpdate();
		tx.commit();
	}
}
