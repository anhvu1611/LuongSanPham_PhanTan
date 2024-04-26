
package sever;

import dao.BangLuongNhanVienDao;
import dao.BangPhanCongCongDoanDao;
import dao.ChucVuDao;
import dao.ChuyenMonDao;
import dao.CongDoanDao;
import dao.CongNhanDao;
import dao.DiaChiDao;
import dao.NhanVienDao;
import dao.PhongBanDao;
import dao.SanPhamDao;
import dao.TaiKhoanDao;
import dao.ThongKeLuongNhanVienDao;
import dao.impl.BangLuongNhanVienImpl;
import dao.impl.BangPhanCongCongDoanImpl;
import dao.impl.ChucVuImpl;
import dao.impl.ChuyenMonImpl;
import dao.impl.CongDoanImpl;
import dao.impl.CongNhanImpl;
import dao.impl.DiaChiImpl;
import dao.impl.NhanVienImpl;
import dao.impl.PhongBanImpl;
import dao.impl.SanPhamimpl;
import dao.impl.TaiKhoanImpl;
import dao.impl.ThongKeLuongNhanVienImpl;
import entity.BangPhanCongCongDoan;
import entity.ChucVu;
import entity.ChuyenMon;
import entity.CongDoan;
import entity.CongNhan;
import entity.DiaChi;
import entity.LocalDateAdapter;
import entity.NhanVien;
import entity.PhongBan;
import entity.SanPham;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import utils.Email;
import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Sever {
    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(7878)){

            System.out.println("Server is listening on port 7878");

            while (true) {
                Socket socket = server.accept();
                System.out.println("Client connected");
                System.out.println("Client address: " + socket.getInetAddress().getHostName());
                Thread t = new Thread(new ClientHandler(socket));
                t.start();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class ClientHandler implements Runnable {
    private Socket socket;
    private TaiKhoanDao taiKhoanDao;
    private NhanVienDao nhanVienDao;
    private SanPhamDao sanPhamDao;
    private DiaChiDao diaChiDao;
    private ChucVuDao chucVuDao;
    private PhongBanDao phongBanDao;
    private CongNhanDao congNhanDao;
    private ChuyenMonDao chuyenMonDao;
    private BangLuongNhanVienDao bangLuongNhanVienDao;
    private ThongKeLuongNhanVienDao thongKeLuongNhanVienDao;
    private CongDoanDao congDoanDao;
    private BangPhanCongCongDoanDao bangPhanCongCongDoanDao;
    private EntityManager em;
    private Gson gson;

    public ClientHandler(Socket socket) {
        em = Persistence.createEntityManagerFactory("BTLPhanTan").createEntityManager();
        this.socket = socket;
        taiKhoanDao = new TaiKhoanImpl(em);
        nhanVienDao = new NhanVienImpl(em);
        sanPhamDao = new SanPhamimpl(em);
        diaChiDao = new DiaChiImpl(em);
        chucVuDao = new ChucVuImpl(em);
        congNhanDao = new CongNhanImpl(em);
        thongKeLuongNhanVienDao = new ThongKeLuongNhanVienImpl(em);	
        phongBanDao = new PhongBanImpl(em);
        chuyenMonDao = new ChuyenMonImpl(em);
        bangLuongNhanVienDao = new BangLuongNhanVienImpl(em);
        bangPhanCongCongDoanDao = new BangPhanCongCongDoanImpl(em);
        gson = new GsonBuilder()
        	    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        	    .create();
        congDoanDao = new CongDoanImpl(em);

    }

    @Override
    public void run() {

        try {
            int key = 0;
            String value = "";
            DataInputStream in = new DataInputStream(socket.getInputStream());
//            ObjectInputStream inObj = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            while (true) {

            	value = in.readUTF();
            	System.out.println(value);
                switch (value) {                	
                    case "GD_DANGNHAP":
                    	key = in.readInt();
                    	System.out.println(key);
                        switch (key){
                            case 1:
                                String tk = in.readUTF();
                                String mk = in.readUTF();
                                TaiKhoan taiKhoan = taiKhoanDao.login(tk, mk);
                                out.writeObject(taiKhoan);
                                System.out.println(taiKhoan);
                                if(taiKhoan!=null) {
                                	NhanVien nv = nhanVienDao.timKiem(taiKhoan.getTenTaiKhoan());
                                	System.out.println(nv);
                                	out.writeObject(nv);
                                }
                                out.flush();
                                break;
                            case 2:
                            	tk = in.readUTF();
                                String email = nhanVienDao.layEmailNhanVien(tk);
                                if(email.isEmpty() || email == null) {
                                	out.writeObject(null);
                                }else {
                                	String mxn = Email.guiMaXacNhanChoNguoiDung(email);
                                	System.err.println(mxn);
                                	out.writeObject(mxn);
                                }
                                out.flush();
                                break;
                                                           
                        }
                        break;

                    case "GD_SANPHAM_CAPNHAT":
                    		key = in.readInt();
                    		System.out.println();
                    		if(key == 0) {
                    			break;
                    		}
                            switch (key){
                            	case 1://Hien danh sach san pham
                            		List<SanPham> dsSanPham = sanPhamDao.layDanhSach();
                            		out.writeObject(dsSanPham);
                            		out.flush();
                            		break;
                            	case 2://Them San Pham
                            		String json = in.readUTF();
                            		SanPham pham = gson.fromJson(json, SanPham.class);
                            		boolean isSuccess = sanPhamDao.them(pham);
                            		out.writeObject(isSuccess);
                            		out.flush();
                            		break;
                            	case 3:
                            		String maSP = sanPhamDao.layMaSanPhamTiepTheo();
                            		out.writeUTF(maSP);
                            		out.flush();
                            		break;
                            	case 4: // tim san pham theo ma
                            		maSP = in.readUTF();
                            		pham = sanPhamDao.timSanPhamTheoMa(maSP);
                            		out.writeObject(pham);
                            		out.flush(); 
                            		break;
                            	case 5://Them San Pham
                            		json = in.readUTF();
                            		pham = gson.fromJson(json, SanPham.class);
                            		isSuccess = sanPhamDao.sua(pham);
                            		out.writeBoolean(isSuccess);
                            		out.flush();
                            		break;                      		
                            }
                    	break;
                    
                    	
                    	
                    	
                    case "GD_NHANVIEN_CN":                  		
                			key = in.readInt();
	                		System.out.println(key);
	                		switch(key) {
	                			case 1:
	                				String[] dsTinhThanh = diaChiDao.layDanhSachTinhThanh();
	                				out.writeObject(dsTinhThanh);
	                				break;
	                			case 2:
	                				String tinhThanh = in.readUTF();
	                				String[] dsQuanHuyen = diaChiDao.layDanhSachQuanHuyen(tinhThanh);
	                				out.writeObject(dsQuanHuyen);
	                				break;
	                			case 3:
	                				tinhThanh = in.readUTF();
	                				String quanHuyen = in.readUTF();
	                				String[] dsThiXa = diaChiDao.layDanhSachPhuong(tinhThanh, quanHuyen);
	                				for(int i= 0; i< dsThiXa.length;i++) {
	                					System.out.println(dsThiXa[i]);
	                				}
	                				out.writeObject(dsThiXa);
	                				
	                				break;
	                			case 4:
	                				List<NhanVien> dsNhanVien = nhanVienDao.layDanhSach();
	                				out.writeObject(dsNhanVien);
	                				out.flush();
	                				break;
	                			case 5:
	                				List<ChucVu> dsChucVu = chucVuDao.layDanhSachChucVu();
	                				out.writeObject(dsChucVu);
	                				System.out.println(dsChucVu);
	                				out.flush();
	                				break;
	                			case 6: // lay danh sach phong ban
	                				List<PhongBan> dsPhongBan = phongBanDao.layDanhSach();
	                				out.writeObject(dsPhongBan);
	                				out.flush();
	                				break;
	                			case 7: // ma nhan vien tiep theo
	                				String maNhanVienTT = nhanVienDao.layMaNhanVienTiepTheo();
	                				out.writeUTF(maNhanVienTT);
	                				out.flush();
	                				break;
	                			case 8:// theme nhan vien
	                				String json = in.readUTF();
	                				NhanVien nv = gson.fromJson(json, NhanVien.class);
	                				boolean isSuccess = nhanVienDao.them(nv);
	                				out.writeObject(isSuccess);
	                				out.flush();
	                				break;
	                			case 9:
	                				json = in.readUTF();
	                				DiaChi dc = gson.fromJson(json, DiaChi.class);
	                				diaChiDao.themDiaChi(dc);
	                				break;
	                			case 10:// nhana vien theo ma
	                				String id = in.readUTF();
	                				nv = nhanVienDao.timKiem(id);
	                				out.writeObject(nv);
	                				out.flush();
	                				break;
	                			case 11:
	                				json = in.readUTF();
	                				nv = gson.fromJson(json, NhanVien.class);
	                				isSuccess = nhanVienDao.sua(nv);
	                				out.writeObject(isSuccess);
	                				out.flush();
	                				break; 
                		}
	                	break;
                    case "CONGNHAN":
                    	key = in.readInt();
                		System.out.println(key);
                		switch (key) {
							case 1: {//lay cong nhan theo ma
								String maCongNhan = in.readUTF();
								CongNhan cn = congNhanDao.timKiem(maCongNhan);
								out.writeObject(cn);
								out.flush();
								break;
							}
							case 2:{//danh sach cong nhan
								List<CongNhan> dsCongNhan = congNhanDao.layDanhSach();
								out.writeObject(dsCongNhan);
								out.flush();
								break;
							}
							case 3:{// ma cong nhan tipe theo
								String id = congNhanDao.layMaCongNhanTiepTheo();
								out.writeUTF(id);
								out.flush();
								break;
							}
							case 4:{
								String json = in.readUTF();
								CongNhan cn = gson.fromJson(json, CongNhan.class);
								boolean isSuccess = congNhanDao.them(cn);
								out.writeObject(isSuccess);
								out.flush();
								break;
							}
							case 5:{
								String json = in.readUTF();
								CongNhan cn = gson.fromJson(json, CongNhan.class);
								boolean isSuccess = congNhanDao.sua(cn);
								out.writeObject(isSuccess);
								out.flush();
								break;
							}
						}
						break;
                    case "CHUYENMON":
                    	key = in.readInt();
                    	switch (key) {
							case 1: {
								List<ChuyenMon> cm = chuyenMonDao.layDanhSach();
								out.writeObject(cm);
								break;
							}
                    	}
                    	break;
                    case "GDSANPHAM_TIMKIEM":
                    	key = in.readInt();
                    	switch (key) {
							case 1: {
								String json = in.readUTF();
								Object[] data = gson.fromJson(json, Object[].class);
								List<SanPham> dsSanPham = sanPhamDao.timSanPhamDaTieuChi((String)data[0], (String)data[1], (String)data[2], (String)data[3], (String)data[4],(String) data[5], (String)data[6], (String)data[7], (String)data[8], (Date)data[9], (String)data[10]);
								out.writeObject(dsSanPham);
								out.flush();
								break;
							}
                    	}
                    	break;
                    case "GDNHANVIEN_TIMKIEM":
                    	key = in.readInt();
                    	switch (key) {
							case 1: {
								String json = in.readUTF();
								Object[] data = gson.fromJson(json, Object[].class);
								List<NhanVien> dsNhanVien = nhanVienDao.timNhanVienTheoTieuChi(
										(String)data[0], (String)data[1], (String)data[2], (String)data[3], 
										(String)data[4],(double) data[5], (double)data[6], 
										(ChucVu)data[7], (String)data[8], (PhongBan)data[9], 
										(String)data[10], (String)data[11], (String)data[12],
										(Date)data[10], (Date)data[11], (String)data[12],
										(boolean)data[10], (boolean)data[11], (int)data[13]);
								out.writeObject(dsNhanVien);
								out.flush();
								break;
							}
                    	}
                    	break;
                    case "GD_DOIMATKHAU":
                    	key = in.readInt();
                    	switch (key) {
							case 1: {
								String ma = in.readUTF();
								String mk = in.readUTF();
								boolean isSuccess = taiKhoanDao.doMatKhau(ma, mk);
								out.writeObject(isSuccess);
								out.flush();
								break;
							}
                    	}
                    	break;
                	
                    case "GD_CONGDOAN": 
                    	key = in.readInt();
                    	switch (key) {
                    	// lấy danh sách sản phẩm
	                    	case 1: {
	                    		ArrayList<SanPham> ds = (ArrayList<SanPham>) sanPhamDao.layDanhSach();
	                    		out.writeObject(ds);
	                    		out.flush();
	                    		break;
	                    	}

							// Thêm công đoạn
							case 2: {
								System.out.println("Thêm công đoạn: ");
								String json = in.readUTF();
                        		CongDoan congDoan = gson.fromJson(json, CongDoan.class);
                        		// kiểm tra công đoạn đã tồn tại chưa 
                        		CongDoan cd = congDoanDao.timKiem(congDoan.getMaCongDoan());
                        		boolean result = false; 
								if (cd != null) {
									congDoanDao.sua(congDoan);
								}else 
									result = congDoanDao.them(congDoan);
								System.err.println("server: them cong doan" + result);
								out.writeObject(result); 
								out.flush();
							}

							// lấy danh sách công đoạn
							case 3: { 
								System.out.println("lấy danh sách công đoạn theo mã sp: ");
								String maSanPham = in.readUTF();
								System.out.println("Mã sp: " + maSanPham);
								if(maSanPham.equals("GD_CONGDOAN")) {
									in.readInt(); 
									maSanPham = in.readUTF();
								}
								System.out.println("Mã sp: " + maSanPham);
								ArrayList<CongDoan> ds = (ArrayList<CongDoan>) congDoanDao.layDanhSachTheoMaSanPham(maSanPham);
								System.out.println("dssp: " + ds.size());
								if(ds.size() > 0)
									System.out.println("dssp[0]:" + ds.get(0));
								out.writeObject(ds);
								out.flush();
								break;
							}
							
							// timCongDoanKhiCoMa(maCongDoan);
							
							case 4: { 
								System.out.println("tìm cd khi có mã congdoan:");
								String maCongDoan = in.readUTF();
								System.out.println("Mã cd: " + maCongDoan);
                                CongDoan congDoan = congDoanDao.timKiem(maCongDoan);
                                System.out.println("cd tim thay, getLuongTren1Sp: " + congDoan.getLuongTren1Sp());
                                out.writeObject(congDoan);
                                out.flush();
                                break;
                            }
							
							
							
                    	}
                    	break; 	
                    	
                    case "GD_PHANCONG": 
                    	key = in.readInt();
                    	switch (key) {
                    	// tinhTongSoLuongPhanCongTheoMaCongDoan
	                    	case 1: {
	                    		String maCongDoan = in.readUTF();
	                    		System.out.println("tính tổng số lượng phân công theo mã công đoạn:" + maCongDoan);
	                    		int result = BangPhanCongCongDoanDao.tinhTongSoLuongPhanCongTheoMaCongDoan(maCongDoan);
	                    		out.writeObject(result);
	                    		out.flush();
	                    		break;
	                    	}
	                    	
	                    //daoCongDoan.laySoLuongThanhPhanTheoMaCongDoan(maCongDoan)
	                    	case 2: {
	                    		String maCongDoan = in.readUTF();
	                    		System.out.println("lấy số lượng thành phần theo mã công đoạn:" + maCongDoan);
                        		int result = congDoanDao.laySoLuongThanhPhanTheoMaCongDoan(maCongDoan);
                        		out.writeObject(result);
                        		out.flush();
                        		break;
                        	
	                    	}
	                    	
	                    	case 3: {
	                    		String maCongDoan = in.readUTF();
	                    		System.out.println("lấy số lượng phan cong theo mã công đoạn:" + maCongDoan);
                        		int result = bangPhanCongCongDoanDao.laySoLuongPhanCong(maCongDoan);
                        		out.writeObject(result);
                        		out.flush();
                        		break;
                        	
	                    	}
	                    	
//	                    	bangPhanCongCongDoan.themPhanCongCongDoan(phanCong);
	                    	case 4: { 
	                    		System.out.println("thêm phân công công đoạn:");
                        		String json = in.readUTF();
                        		BangPhanCongCongDoan phanCong = gson.fromJson(json, BangPhanCongCongDoan.class);
                        		System.out.println("phan cong: " + phanCong);
                        		bangPhanCongCongDoanDao.themPhanCongCongDoan(phanCong);
                        		break;
	                    	}
	                    	
	                    	//bangChamCongCongNhan_Dao.laySoLuongDaChamCongTheoMaCongDoan(maCongDoanCatGo) -
	                    	case 5: { 
	                    		String maCongDoan = in.readUTF();
	                    		System.out.println("laySoLuongDaChamCongTheoMaCongDoan" + maCongDoan);
                        		int result = bangPhanCongCongDoanDao.laySoLuongDaChamCongTheoMaCongDoan(maCongDoan);
                        		out.writeObject(result);
                        		out.flush();
                        		break;
	                    	}
	                    	
	                    	//xoaPhanCongCongDoanVaKhoiDs(maCongDoan, maCongNhan)
	                    	case 6: {
	                    		String maCongDoan = in.readUTF();
                        		String maCongNhan = in.readUTF();
                        		System.out.println("xoaPhanCongCongDoanVaKhoiDs:" + maCongDoan + " " + maCongNhan);
                        		bangPhanCongCongDoanDao.xoaPhanCongCongDoanVaKhoiDs(maCongDoan, maCongNhan);
                        		break;
	                    	}
	                    	
	                    	//layDanhSachPhanCongCongDoanTheoMaCongDoan(maCongDoan);
	                    	case 7: { 
	                    		String maCongDoan = in.readUTF();
                        		System.out.println("layDanhSachPhanCongCongDoanTheoMaCongDoan:" + maCongDoan);
                        		ArrayList<BangPhanCongCongDoan> result = bangPhanCongCongDoanDao.layDanhSachPhanCongCongDoanTheoMaCongDoan(maCongDoan);
                        		out.writeObject(result);
                        		out.flush();
                        		break;
	                    	}
	                    	
	                    	case 8: { 
	                    		System.out.println("Sửa phân công công đoạn:");
                        		String json = in.readUTF();
                        		BangPhanCongCongDoan phanCong = gson.fromJson(json, BangPhanCongCongDoan.class);
                        		System.out.println("phan cong: " + phanCong);
                        		bangPhanCongCongDoanDao.suaPhanCongCongDoan(phanCong);
                        		break;
	                    	}
                    	}	
					}
            }
        } catch (Exception e) {
            e.printStackTrace();

        }


    }
}