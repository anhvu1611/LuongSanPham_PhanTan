package dao.impl;

import java.io.Serializable;

import dao.BangLuongNhanVienDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BangLuongNhanVienImpl implements Serializable, BangLuongNhanVienDao{

	/**
	 * 
	 */
	EntityManager em;
	public BangLuongNhanVienImpl(EntityManager em) {
		this.em = em;
		taoHamTinhLuongChoMotNhanVien();
		taoHamTinhLuongChoNhieuNhanVien();
	}
	private static final long serialVersionUID = 1502240698754488280L;

	@Override
	public void taoHamTinhLuongChoMotNhanVien() {
		String sql = "IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[taoBangLuongNhanVien]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))\r\n"
				+ "BEGIN\r\n"
				+ "    EXEC('\r\n"
				+ "        create function [dbo].[taoBangLuongNhanVien](\r\n"
				+ "            @maNhanVien nvarchar(6),\r\n"
				+ "            @maBangLuong nvarchar(8),\r\n"
				+ "            @thang int, \r\n"
				+ "            @nam int\r\n"
				+ "        )returns @bangLuongNhanVien table(\r\n"
				+ "            maBangLuongNhanVien nvarchar(8),\r\n"
				+ "            ngayLap date,\r\n"
				+ "            soNgayDiLam tinyint,\r\n"
				+ "            soNgayLamCaNgay tinyint,\r\n"
				+ "            soNgayLamNuaNgay tinyint,\r\n"
				+ "            soNgayVangCoPhep tinyint,\r\n"
				+ "            soNgayVangKhongPhep tinyint,\r\n"
				+ "            soNgayTre tinyint,\r\n"
				+ "            soGioTangCa tinyint default 0, \r\n"
				+ "            maNhanVien nvarchar(6)\r\n"
				+ "        )\r\n"
				+ "        as \r\n"
				+ "        begin\r\n"
				+ "            Declare @ngayLap date\r\n"
				+ "            set @ngayLap = getDate()\r\n"
				+ "            declare @soNgayLamToanThoiGian int\r\n"
				+ "            set @soNgayLamToanThoiGian = (select count(*) from BangChamCongNhanVien where caLam = N''Full-time'' and (trangThai = N''Đi làm'' or trangThai = N''Đi trễ'') and maNhanVienDuocChamCong = @maNhanVien and month(ngayChamCong) = @thang and year(ngayChamCong) = @nam)\r\n"
				+ "            declare @soNgayLamNuaThoiGian int\r\n"
				+ "            set @soNgayLamNuaThoiGian = (select count(*) from BangChamCongNhanVien where caLam = N''Part-time'' and (trangThai = N''Đi làm'' or trangThai = N''Đi trễ'') and maNhanVienDuocChamCong = @maNhanVien and month(ngayChamCong) = @thang and year(ngayChamCong) = @nam)\r\n"
				+ "            --tổng số ngày đi làm\r\n"
				+ "            declare @tongSoNgayDiLam int\r\n"
				+ "            set @tongSoNgayDiLam = @soNgayLamNuaThoiGian/2 + @soNgayLamToanThoiGian\r\n"
				+ "            if(@tongSoNgayDiLam = 0)\r\n"
				+ "            begin\r\n"
				+ "                return\r\n"
				+ "            end\r\n"
				+ "\r\n"
				+ "            declare @soNgayVangCoPhep int \r\n"
				+ "            set @soNgayVangCoPhep = (select count(*) from BangChamCongNhanVien \r\n"
				+ "                                    where (caLam = N''Part-time'' or caLam = N''Full-time'') and (trangThai = N''Nghỉ có phép'') \r\n"
				+ "                                    and maNhanVienDuocChamCong = @maNhanVien\r\n"
				+ "                                    and month(ngayChamCong) = @thang and year(ngayChamCong) = @nam)\r\n"
				+ "\r\n"
				+ "            declare @soNgayVangKhongPhep int \r\n"
				+ "            set @soNgayVangKhongPhep = (select count(*) from BangChamCongNhanVien where (caLam = N''Part-time'' or caLam = N''Full-time'') \r\n"
				+ "                                        and (trangThai = N''Nghỉ không phép'')\r\n"
				+ "                                        and maNhanVienDuocChamCong = @maNhanVien\r\n"
				+ "                                        and month(ngayChamCong) = @thang and year(ngayChamCong) = @nam)\r\n"
				+ "\r\n"
				+ "            declare @soNgayTre int\r\n"
				+ "            set @soNgayTre = (select count(*) from BangChamCongNhanVien where (caLam = N''Part-time'' or caLam = N''Full-time'') and (trangThai = N''Đi trễ'') and maNhanVienDuocChamCong = @maNhanVien)\r\n"
				+ "\r\n"
				+ "            declare @soGioTangCa int\r\n"
				+ "            set @soGioTangCa = (select sum(soGioTangCa) from BangChamCongNhanVien where\r\n"
				+ "                                maNhanVienDuocChamCong = @maNhanVien\r\n"
				+ "                                and month(ngayChamCong) = @thang and year(ngayChamCong) = @nam)\r\n"
				+ "            if(@soGioTangCa is null)\r\n"
				+ "                set @soGioTangCa = 0\r\n"
				+ "\r\n"
				+ "            insert @bangLuongNhanVien\r\n"
				+ "            values(@maBangLuong, @ngayLap, @tongSoNgayDiLam, @soNgayLamToanThoiGian,@soNgayLamNuaThoiGian, @soNgayVangCoPhep, @soNgayVangKhongPhep, @soNgayTre,@soGioTangCa, @maNhanVien)\r\n"
				+ "            return\r\n"
				+ "        end\r\n"
				+ "    ')\r\n"
				+ "END\r\n";
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.createNativeQuery(sql).executeUpdate();
		tx.commit();
	}

	@Override
	public void taoHamTinhLuongChoNhieuNhanVien() {
		String sql = "IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tinhLuongToanBoNhanVienTheoThangVaNam]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))\r\n"
				+ "BEGIN\r\n"
				+ "    EXEC('\r\n"
				+ "        create function [dbo].[tinhLuongToanBoNhanVienTheoThangVaNam](\r\n"
				+ "	        @thang int,\r\n"
				+ "	        @nam int\r\n"
				+ "        )returns @bangLuongNhanVien table(\r\n"
				+ "	        maBangLuongNhanVien nvarchar(8),\r\n"
				+ "	        ngayLap date,\r\n"
				+ "	        soNgayDiLam tinyint,\r\n"
				+ "	        soNgayLamCaNgay tinyint,\r\n"
				+ "	        soNgayLamNuaNgay tinyint,\r\n"
				+ "	        soNgayVangCoPhep tinyint,\r\n"
				+ "	        soNgayVangKhongPhep tinyint,\r\n"
				+ "	        soNgayTre tinyint,\r\n"
				+ "	        soGioTangCa tinyint default 0,\r\n"
				+ "	        maNhanVien nvarchar(6))\r\n"
				+ "        as\r\n"
				+ "        begin\r\n"
				+ "            DECLARE @maNhanVien NVARCHAR(8) = ''NV0001''\r\n"
				+ "            declare @maBangLuong nvarchar(8)\r\n"
				+ "            set @maBangLuong = (select max(maBangLuong) from BangLuongNhanVien)\r\n"
				+ "            if(@maBangLuong is null)\r\n"
				+ "            begin\r\n"
				+ "                set @maBangLuong = ''BLNV0000''\r\n"
				+ "            end\r\n"
				+ "            WHILE @maNhanVien <= (select max(maNhanSu) from NhanVien)\r\n"
				+ "            BEGIN\r\n"
				+ "                set @maBangLuong = ''BLNV'' + RIGHT(''0000'' + CAST(CAST(SUBSTRING(@maBangLuong, 5, LEN(@maBangLuong) - 2) AS INT) + 1 AS NVARCHAR(10)), 4);\r\n"
				+ "                insert @bangLuongNhanVien\r\n"
				+ "                select * from [taoBangLuongNhanVien](@maNhanVien,@maBangLuong, @thang, @nam)\r\n"
				+ "                SET @maNhanVien = ''NV'' + RIGHT(''0000'' + CAST(CAST(SUBSTRING(@maNhanVien, 3, LEN(@maNhanVien) - 2) AS INT) + 1 AS NVARCHAR(10)), 4);\r\n"
				+ "            END;\r\n"
				+ "            return\r\n"
				+ "        end\r\n"
				+ "    ')\r\n"
				+ "END\r\n"
				+ "\r\n";
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.createNativeQuery(sql).executeUpdate();
		tx.commit();
	}

}
