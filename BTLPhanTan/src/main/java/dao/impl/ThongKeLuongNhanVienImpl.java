package dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import dao.ThongKeLuongNhanVienDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ThongKeLuongNhanVienImpl implements ThongKeLuongNhanVienDao {
	EntityManager em;
	
	public ThongKeLuongNhanVienImpl(EntityManager em) {
		this.em = em;
		taoDanhSachThongKeTuThangVaNam();
		taoHamThongKeTheoThoiGian();
	}
	@Override
	public void taoDanhSachThongKeTuThangVaNam() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		String sql = "	IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tinhLuongToanBoNhanVienTheoThangVaNam]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))\r\n"
				+ "	BEGIN\r\n"
				+ "		EXEC('\r\n"
				+ "create function [dbo].[danhSachThongKe](\r\n"
				+ "	@thang int,\r\n"
				+ "	@nam int\r\n"
				+ ")RETURNS @ResultTable TABLE\r\n"
				+ "(\r\n"
				+ "    LuongThucNhan INT,\r\n"
				+ "    NgayLam INT,\r\n"
				+ "    LuongCoBan INT,\r\n"
				+ "    HeSoLuong FLOAT,\r\n"
				+ "    LuongTruocThue INT,\r\n"
				+ "    KhauTruBaoHiem INT,\r\n"
				+ "    MaNhanVien NVARCHAR(8)\r\n"
				+ ")\r\n"
				+ "as\r\n"
				+ "begin\r\n"
				+ "	\r\n"
				+ "	declare @maNhanVien Nvarchar(8)\r\n"
				+ "	set @maNhanVien = ''NV0001''\r\n"
				+ "	while @maNhanVien <= (select max(maNhanSu) from NhanVien)\r\n"
				+ "	begin\r\n"
				+ "		if @maNhanVien in (select distinct maNhanVien from BangLuongNhanVien where month(ngayLap) = @thang and year(ngayLap) = @nam and soNgayDiLam > 0)\r\n"
				+ "		begin\r\n"
				+ "			declare @tongNgayLam int, @caNgay int, @nuaNgay int, @coPhep int, @khongPhep int,@tangCa int,@tre int,@luongCoBan int, @heSoLuong float, @tongThuNhapTheoNgayCong int, @phuCap int, @bhyt nvarchar(8),@bhxh nvarchar(8)\r\n"
				+ "			select @caNgay = soNgayLamCaNgay,\r\n"
				+ "			@nuaNgay = soNgayLamNuaNgay,\r\n"
				+ "			@coPhep = soNgayVangCoPhep,\r\n"
				+ "			@khongPhep = soNgayVangKhongPhep,\r\n"
				+ "			@tangCa = soGioTangCa,\r\n"
				+ "			@tre = soNgayTre,\r\n"
				+ "			@luongCoBan = cv.luongCungTheoChucVu,\r\n"
				+ "			@heSoLuong = nv.[heSoLuong],\r\n"
				+ "			@phuCap = phuCap,\r\n"
				+ "			@bhxh = BHXH,\r\n"
				+ "			@bhyt = BHYT,\r\n"
				+ "			@tongNgayLam = soNgayDiLam\r\n"
				+ "			from BangLuongNhanVien bl\r\n"
				+ "			join NhanVien nv on nv.maNhanVien = bl.maNhanVien\r\n"
				+ "			join ChucVu cv on cv.maChucVu = nv.maChucVu\r\n"
				+ "			where soNgayDiLam > 0 and month(ngayLap) = @thang and year(ngayLap) = @nam and bl.maNhanVien = @maNhanVien\r\n"
				+ "\r\n"
				+ "			if @coPhep <=2\r\n"
				+ "				begin\r\n"
				+ "					set @coPhep = 0\r\n"
				+ "				end\r\n"
				+ "\r\n"
				+ "			declare @luong1Ngay int\r\n"
				+ "			set @luong1Ngay = (@luongCoBan*@heSoLuong)/26\r\n"
				+ "			set @tongThuNhapTheoNgayCong = @luong1Ngay*(@tongNgayLam - @coPhep - @khongPhep) - @tre* 50000 + @tangCa*(@luong1Ngay/8*1.5)\r\n"
				+ "			declare @luongTruocThue int\r\n"
				+ "			set @luongTruocThue = @tongThuNhapTheoNgayCong + @phuCap\r\n"
				+ "			declare @khauTruBaoHiem int, @bhytFloat float, @bhxhFloat float\r\n"
				+ "\r\n"
				+ "			if @bhyt = N''Tham Gia''\r\n"
				+ "				begin\r\n"
				+ "					set @bhytFloat = 0.015\r\n"
				+ "				end\r\n"
				+ "			else\r\n"
				+ "				begin\r\n"
				+ "					set @bhytFloat = 0;\r\n"
				+ "				end\r\n"
				+ "			if @bhxh = N''Tham Gia''\r\n"
				+ "				begin\r\n"
				+ "					set @bhxhFloat = 0.08\r\n"
				+ "				end\r\n"
				+ "			else\r\n"
				+ "				begin\r\n"
				+ "					set @bhxhFloat = 0;\r\n"
				+ "				end\r\n"
				+ "			set @khauTruBaoHiem = @luongTruocThue*@bhxhFloat +@luongTruocThue * @bhytFloat\r\n"
				+ "\r\n"
				+ "			declare @luongThuc int\r\n"
				+ "			set @luongThuc = @luongTruocThue - @khauTruBaoHiem\r\n"
				+ "			insert @ResultTable\r\n"
				+ "			select @luongThuc as LuongThucNhan, ngayLam = @tongNgayLam, @luongCoBan, @heSoLuong, @luongTruocThue, @khauTruBaoHiem, @maNhanVien\r\n"
				+ "		\r\n"
				+ "		end\r\n"
				+ "		SET @maNhanVien = ''NV'' + RIGHT(''0000'' + CAST(CAST(SUBSTRING(@maNhanVien, 3, LEN(@maNhanVien) - 2) AS INT) + 1 AS NVARCHAR(10)), 4);\r\n"
				+ "	end\r\n"
				+ "	return\r\n"
				+ "end\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "		')\r\n"
				+ "	END\r\n"
				+ "\r\n"
				+ "";
		em.createNativeQuery(sql).executeUpdate();
		tx.commit();
	}
	@Override
	public void taoHamThongKeTheoThoiGian() {
		String sql = "	IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tinhLuongToanBoNhanVienTheoThangVaNam]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))\r\n"
				+ "	BEGIN\r\n"
				+ "				EXEC('\r\n"
				+ "		create function [dbo].[thongKeTheoThoiGian](\r\n"
				+ "			@tuThang int,\r\n"
				+ "			@tuNam int,\r\n"
				+ "			@denThang int,\r\n"
				+ "			@denNam int\r\n"
				+ "		)RETURNS @ResultTable TABLE\r\n"
				+ "		(\r\n"
				+ "			LuongThucNhan INT,\r\n"
				+ "			NgayLam INT,\r\n"
				+ "			LuongCoBan INT,\r\n"
				+ "			HeSoLuong FLOAT,\r\n"
				+ "			LuongTruocThue INT,\r\n"
				+ "			KhauTruBaoHiem INT,\r\n"
				+ "			MaNhanVien NVARCHAR(8)\r\n"
				+ "		)\r\n"
				+ "		as\r\n"
				+ "		begin\r\n"
				+ "			while @tuNam <= @denNam\r\n"
				+ "			begin\r\n"
				+ "				if @tuNam = @denNam\r\n"
				+ "				begin\r\n"
				+ "					while @tuThang <= @denThang\r\n"
				+ "					begin\r\n"
				+ "						insert @ResultTable\r\n"
				+ "						select * from [dbo].[danhSachThongKe](@tuThang, @tuNam)\r\n"
				+ "						set @tuThang = @tuThang + 1\r\n"
				+ "					end\r\n"
				+ "				end\r\n"
				+ "				else\r\n"
				+ "					begin\r\n"
				+ "						while @tuThang <= 12\r\n"
				+ "						begin\r\n"
				+ "							insert @ResultTable\r\n"
				+ "							select * from [dbo].[danhSachThongKe](@tuThang, @tuNam)\r\n"
				+ "							set @tuThang = @tuThang + 1\r\n"
				+ "						end\r\n"
				+ "					end\r\n"
				+ "\r\n"
				+ "					set @tuNam = @tuNam + 1\r\n"
				+ "					set @tuThang = 1 \r\n"
				+ "			end\r\n"
				+ "			return\r\n"
				+ "		end\r\n"
				+ "		GO\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "		')\r\n"
				+ "	END\r\n"
				+ "\r\n";
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.createNativeQuery(sql).executeUpdate();
		tx.commit();
	}
	@Override
	public List<Vector<String>> layDanhSachThongKeTopNNhanVien(int tuThang, int tuNam, int toiThang, int toiNam,
			int soLuongThongKe, String kieuTangGiam, String tieuChi) {
		List<Vector<String>> dsThongKe = new ArrayList<Vector<String>>();
		String sql = "SELECT TOP " +soLuongThongKe+" "+
	            "SUM(LuongThucNhan) AS LuongThucNhan, " +
	            "SUM(NgayLam) AS NgayLam, " +
	            "LuongCoBan, " +
	            "HeSoLuong, " +
	            "SUM(KhauTruBaoHiem) AS KhauTru, " +
	            "SUM(LuongTruocThue) AS LuongTruocThue, " +
	            "MaNhanVien " +
	            "FROM dbo.[thongKeTheoThoiGian](?, ?, ?, ?) " +
	            "GROUP BY MaNhanVien, LuongCoBan, HeSoLuong " +
	            "ORDER BY "+tieuChi+" "+ kieuTangGiam;
		List<?> ds = em.createNamedQuery(sql)
				.setParameter("tuThang", tuThang)
				.setParameter("tuNam", tuNam)
				.setParameter("toiThang", toiThang)
				.setParameter("toiNam", toiNam)
				.getResultList();
		ds.stream().map(o->(Object[]) o).forEach(e->{
			String luongThucNhan = (String) e[0];
			String ngayLam = (String) e[1];
			String luongCoBan = (String) e[2];
			String heSoLuong = (String) e[3];
			String khauTru = (String) e[4];
			String luongTruocThue = (String) e[5];
			String maNhanVien = (String) e[6];
			Vector<String> data =new Vector<String>();
			data.add(luongThucNhan);
			data.add(ngayLam);
			data.add(luongCoBan);
			data.add(heSoLuong);
			data.add(khauTru);
			data.add(luongTruocThue);
			data.add(maNhanVien);
			dsThongKe.add(data);
		});
		return dsThongKe;
	}

}
