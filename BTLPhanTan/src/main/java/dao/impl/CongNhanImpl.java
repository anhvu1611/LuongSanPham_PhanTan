package dao.impl;

import java.util.List;

import dao.CongNhanDao;
import entity.CongNhan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class CongNhanImpl implements CongNhanDao{
	private EntityManager em;

    public CongNhanImpl(EntityManager em){
        this.em = em;
        hamTaoMaCongNhanTiepTheo();
    }
	@Override
	public boolean them(CongNhan cn) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(cn);
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean sua(CongNhan cn) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(cn);
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public CongNhan timKiem(String id) {
		return em.find(CongNhan.class, id);
	}
	@Override
	public List<CongNhan> layDanhSach() {
		return em.createNamedQuery("CongNhan.findAll", CongNhan.class)
				.getResultList();
	}
	@Override
	public String layMaCongNhanTiepTheo() {
		return em.createNativeQuery("SELECT [dbo].[layMaCongNhanTiepTheo]()")
				.getSingleResult().toString();
	}
	@Override
	public void hamTaoMaCongNhanTiepTheo() {
		String sql = "IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tinhLuongToanBoNhanVienTheoThangVaNam]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))\r\n"
				+ "BEGIN\r\n"
				+ "    EXEC('\r\n"
				+ "		CREATE FUNCTION [dbo].[layMaCongNhanTiepTheo]()\r\n"
				+ "		RETURNS NVARCHAR(6)\r\n"
				+ "		AS\r\n"
				+ "		BEGIN\r\n"
				+ "			DECLARE @nextId NVARCHAR(6);\r\n"
				+ "			DECLARE @lastId NVARCHAR(6);\r\n"
				+ "			SELECT @lastId = MAX(maNhanSu) FROM CongNhan;\r\n"
				+ "			IF @lastId IS NULL\r\n"
				+ "				SET @nextId = ''CN0001'';\r\n"
				+ "			ELSE\r\n"
				+ "			BEGIN\r\n"
				+ "				-- Tăng mã nhân viên cuối cùng lên 1 đơn vị và định dạng lại chuỗi kết quả\r\n"
				+ "				SET @nextId = ''CN'' + RIGHT(''0000'' + CAST(CAST(SUBSTRING(@lastId, 3, LEN(@lastId) - 2) AS INT) + 1 AS NVARCHAR(10)), 4);\r\n"
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
