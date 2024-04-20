package dao.impl;


import java.util.ArrayList;
import java.util.List;
import dao.SanPhamDao;
import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class SanPhamimpl implements SanPhamDao{
	EntityManager em;
	
	public SanPhamimpl(EntityManager em) {
		// TODO Auto-generated constructor stub
		this.em = em;
		taoHamLayMaSanPhamTiepTheo();
	}

	@Override
	public boolean them(SanPham sp) {
		EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.persist(sp);
            tx.commit();
            return true;
        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();

        }
        return false;
	}

	@Override
	public List<SanPham> layDanhSach() {
        return em.createNamedQuery("SanPham.getAll", SanPham.class).getResultList();
	}

	@Override
	public String layMaSanPhamTiepTheo() {
		return em.createNativeQuery("SELECT [dbo].[layMaSanPhamTiepTheo]()")
				.getSingleResult().toString();
	}

	@Override
	public SanPham timSanPhamTheoMa(String id) {
		return em.find(SanPham.class, id);
	}

	@Override
	public boolean sua(SanPham sp) {
		EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.merge(sp);
            tx.commit();
            return true;
        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();

        }
        return false;
	}
	
	private String createSqlFindProduct(String ma, String ten, String khoiLuong, String soLuong, String loaiGo,
			String soCongDoan, String dai, String rong, String cao, java.util.Date ngay, String gia) {
		StringBuilder queryBuilder = new StringBuilder
        		("select sp.maSanPham, giaThanh, tenSP, chatGo, anh, kichThuoc, thoiGianSanXuatDuKien, khoiLuong, sp.trangThai, soLuong from SanPham sp full join CongDoan cd on cd.maSanPham = sp.maSanPham where 1 = 1");

        if (ma != null && !ma.isEmpty()) {
            queryBuilder.append(" AND sp.maSanPham = '").append(ma.trim()).append("'");
        }
        if (ten != null && !ten.isEmpty()) {
            queryBuilder.append(" AND tenSP = N'").append(ten.trim()).append("'");
        }
        if (gia != null && !gia.isEmpty()) {
            queryBuilder.append(" AND giaThanh = ").append(gia.trim());
        }
        if(loaiGo != null) {
        	if(loaiGo.equalsIgnoreCase("Gỗ Hương")) {
        		queryBuilder.append(" AND chatGo = '0").append("'");
        	}else if(loaiGo.equalsIgnoreCase("Gỗ Sao")) {
        		queryBuilder.append(" AND chatGo = '1").append("'");
        	}else {
            	queryBuilder.append(" AND chatGo = '2").append("'");
        	}
        }
        if(ngay != null) {
            queryBuilder.append(" AND thoiGianSanXuatDuKien = '").
            append(new java.sql.Date(ngay.getTime())).append("'");
        }
        if (khoiLuong != null && !khoiLuong.isEmpty()) {
            queryBuilder.append(" AND khoiLuong = ").append(khoiLuong.trim());
        }
        if (soLuong != null && !soLuong.isEmpty()) {
            queryBuilder.append(" AND soLuongSanPham = ").append(soLuong.trim());
        }
        if(dai!=null && !dai.isEmpty()) {
        	queryBuilder.append(" and PARSENAME(REPLACE(kichThuoc, 'X', '.'), 3) = ").append(dai.trim());
        }
        if(rong!=null && !rong.isEmpty()) {
        	queryBuilder.append(" and PARSENAME(REPLACE(kichThuoc, 'X', '.'), 2) = ").append(rong.trim());
        }
        if(cao!=null && !cao.isEmpty()) {
        	queryBuilder.append(" and PARSENAME(REPLACE(kichThuoc, 'X', '.'), 1) = ").append(cao.trim());
        }
        queryBuilder.append(" group by sp.maSanPham, giaThanh, tenSP, chatGo, anh, kichThuoc, thoiGianSanXuatDuKien, khoiLuong, sp.trangThai, soLuong having count(cd.maSanPham)="+soCongDoan);
        String sql = queryBuilder.toString();
        System.out.println(sql);
        return sql;
	}

	@Override
	public List<SanPham> timSanPhamDaTieuChi(String ma, String ten, String khoiLuong, String soLuong, String loaiGo,
			String soCongDoan, String dai, String rong, String cao, java.util.Date ngay, String gia) {
		List<SanPham> dsSanPham = new ArrayList<SanPham>();
		String sql = createSqlFindProduct(ma, ten, khoiLuong, soLuong, loaiGo, soCongDoan, dai, rong, cao, ngay, gia);
		List<?> ds = em.createNativeQuery(sql).getResultList();
		ds
			.stream()
			.map(o->(Object[]) o)
			.forEach(e->dsSanPham.add(em.find(SanPham.class, e[0])));
		return dsSanPham;
	}

	@Override
	public void taoHamLayMaSanPhamTiepTheo() {
		String sql = "IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tinhLuongToanBoNhanVienTheoThangVaNam]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))\r\n"
				+ "BEGIN\r\n"
				+ "    EXEC('\r\n"
				+ "CREATE FUNCTION [dbo].[layMaSanPhamTiepTheo]()\r\n"
				+ "RETURNS NVARCHAR(6)\r\n"
				+ "AS\r\n"
				+ "BEGIN\r\n"
				+ "    DECLARE @nextId NVARCHAR(6);\r\n"
				+ "    DECLARE @lastId NVARCHAR(6);\r\n"
				+ "    SELECT @lastId = MAX(maSanPham) FROM SanPham;\r\n"
				+ "    IF @lastId IS NULL\r\n"
				+ "        SET @nextId = ''SP0001'';\r\n"
				+ "    ELSE\r\n"
				+ "    BEGIN\r\n"
				+ "        -- Tăng mã nhân viên cuối cùng lên 1 đơn vị và định dạng lại chuỗi kết quả\r\n"
				+ "        SET @nextId = ''SP'' + RIGHT(''0000'' + CAST(CAST(SUBSTRING(@lastId, 3, LEN(@lastId) - 2) AS INT) + 1 AS NVARCHAR(10)), 4);\r\n"
				+ "    END\r\n"
				+ "\r\n"
				+ "    RETURN @nextId;\r\n"
				+ "END\r\n"
				+ "GO\r\n"
				+ "    ')\r\n"
				+ "END\r\n"
				+ "\r\n";
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.createNativeQuery(sql).executeUpdate();
		tx.commit();	
	}

}
