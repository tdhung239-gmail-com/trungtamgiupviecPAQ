package ChamSocBenhNhan.Dao.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import ChamSocBenhNhan.Entity.TimKiemTenDichVuMapper;
import ChamSocBenhNhan.Entity.User.ChonDichVu;
import ChamSocBenhNhan.Entity.User.ChonGiatriDichVuMapper;
import ChamSocBenhNhan.Entity.User.LienHe;
import ChamSocBenhNhan.Entity.User.ListDangKyDichVu;
import ChamSocBenhNhan.Entity.User.ListDichVuMapper;
import ChamSocBenhNhan.Entity.User.ListNhanVienMapper;
import ChamSocBenhNhan.Entity.User.ListNhanVienvaDichVu;
import ChamSocBenhNhan.Entity.User.TongDonDangKyDichVuMapper;
import ChamSocBenhNhan.Entity.User.TuyenDung;
import ChamSocBenhNhan.Entity.User.TuyenDungMapper;
import ChamSocBenhNhan.Entity.User.chonDichVuMapper;
import ChamSocBenhNhan.Entity.User.list_EmInBangLuong;
import ChamSocBenhNhan.Entity.User.list_EmInBangLuongMapper;

@Repository
public class HomeUsserDao extends BaseDaoUser { 

	public List<ListNhanVienvaDichVu> getListCardE() {

		List<list_EmInBangLuong> list_EmInBl = new ArrayList<list_EmInBangLuong>();
		String sqlEmInBl = "Select bangluong.maHSNV, bangluong.maDKDV, dangkydichvu.ngayBatDau, dangkydichvu.ngayKetThuc from bangluong,dangkydichvu,hosonhanvien where bangluong.maHSNV = hosonhanvien.maHSNV and bangluong.maDKDV = dangkydichvu.maDKDV";
		list_EmInBl = _jdbcTemplate.query(sqlEmInBl, new list_EmInBangLuongMapper());

		List<ListNhanVienvaDichVu> listnv = new ArrayList<ListNhanVienvaDichVu>();
		String sqlnv = "SELECT hosonhanvien.maDichVu,hosonhanvien.maHSNV,dichvu.tenDichVu,hoTen,sdt,namSinh,queQuan,noiCuTruHienTai,hinhanh,tinhTrangLamViec,tinhTrangDuyetHoSo FROM `hosonhanvien`,dichvu WHERE hosonhanvien.maDichVu = dichvu.maDichVu AND hosonhanvien.tinhTrangDuyetHoSo='xong'";
		listnv = _jdbcTemplate.query(sqlnv, new ListNhanVienMapper());

		for (int i = 0; i < listnv.size(); i++) {

			String maHSNV = listnv.get(i).toString();
			String sql_updateEm = "UPDATE `hosonhanvien` SET `tinhTrangLamViec`='ranh' WHERE maHSNV = " + maHSNV + "";
			// sau khi anh insert vào thì anh phải cập nhật lại tình trạng làm việc của hsnv
			// là chưa
			_jdbcTemplate.update(sql_updateEm);
		}

		for (int i = 0; i < list_EmInBl.size(); i++) {

			String maHSNV = list_EmInBl.get(i).toString().substring(0, list_EmInBl.get(i).toString().indexOf(","));

			// String maDKDV =
			// list_EmInBl.get(i).toString().substring(list_EmInBl.get(i).toString().indexOf(",")
			// + 1,
			// list_EmInBl.get(i).toString().indexOf("-"));

			String ngayBatDau = list_EmInBl.get(i).toString().substring(list_EmInBl.get(i).toString().indexOf("-") + 1,
					list_EmInBl.get(i).toString().lastIndexOf(","));

			String ngayKetThuc = list_EmInBl.get(i).toString().substring(
					list_EmInBl.get(i).toString().lastIndexOf(",") + 1, list_EmInBl.get(i).toString().length());
			Date newDate = new Date();
			java.sql.Date newDateSql = new java.sql.Date(newDate.getTime());
			String newDateS = newDateSql.toString();

			if ((Compare.Contanin1DatesIn2Date(newDateS, ngayBatDau, ngayKetThuc))
					|| (Compare.checkDateEqual(newDateS, ngayBatDau))) {
				System.out.println("bannnnnnnnnnnnnnn");
				String sql_updateEm = "UPDATE `hosonhanvien` SET `tinhTrangLamViec`='ban' WHERE maHSNV = " + maHSNV
						+ "";
				// sau khi anh insert vào thì anh phải cập nhật lại tình trạng làm việc của hsnv
				// là chưa
				_jdbcTemplate.update(sql_updateEm);
			} else {
				System.out.println("k bannnnnnnnnnnnnnn");
				String sql_updateEm = "UPDATE `hosonhanvien` SET `tinhTrangLamViec`='ranh' WHERE maHSNV = " + maHSNV
						+ "";
				// sau khi anh insert vào thì anh phải cập nhật lại tình trạng làm việc của hsnv
				// là chưa
				_jdbcTemplate.update(sql_updateEm);
			}

		}

		List<ListNhanVienvaDichVu> list = new ArrayList<ListNhanVienvaDichVu>();
		String sql = "SELECT hosonhanvien.maDichVu,hosonhanvien.maHSNV,dichvu.tenDichVu,hoTen,sdt,namSinh,queQuan,noiCuTruHienTai,hinhanh,tinhTrangLamViec,tinhTrangDuyetHoSo FROM `hosonhanvien`,dichvu WHERE hosonhanvien.maDichVu = dichvu.maDichVu AND hosonhanvien.tinhTrangDuyetHoSo='xong'";
		list = _jdbcTemplate.query(sql, new ListNhanVienMapper());
		return list;
	}

	// []
	public List<ListDangKyDichVu> getTotalRegisterService() {
		List<ListDangKyDichVu> list = new ArrayList<ListDangKyDichVu>();

		list = _jdbcTemplate.query("SELECT maDKDV from dangkydichvu", new TongDonDangKyDichVuMapper());

		return list;

	}

	public List<ListNhanVienvaDichVu> searchNameService(String tenDichVu) {
		List<ListNhanVienvaDichVu> list = new ArrayList<ListNhanVienvaDichVu>();
		String sql = "SELECT maDichVu,tenDichVu FROM dichvu where tenDichVu like ‘%'" + tenDichVu + "'%’";
		list = _jdbcTemplate.query(sql, new TimKiemTenDichVuMapper());
		return list;
	}

	public List<ListNhanVienvaDichVu> getListService() { 
		List<ListNhanVienvaDichVu> list = new ArrayList<ListNhanVienvaDichVu>(); 
		String sql = "SELECT * FROM dichvu"; 
		list = _jdbcTemplate.query(sql, new ListDichVuMapper()); 
		return list; 
	} 
 
	public List<TuyenDung> getListRecruitment() {
		List<TuyenDung> list = new ArrayList<TuyenDung>();
		String sql = "SELECT * FROM tuyendung";
		list = _jdbcTemplate.query(sql, new TuyenDungMapper());
		return list;
	}

	public List<TuyenDung> getRecruitmentById(int id) {
		return _jdbcTemplate.query("select * from tuyendung where maTuyenDung=" + id + "", new TuyenDungMapper());
	}

	public Map<ChonDichVu, ChonDichVu> getListValueServiceOfDetailRecruitment() {
		List<ChonDichVu> listTen = new ArrayList<ChonDichVu>();
		List<ChonDichVu> listMa = new ArrayList<ChonDichVu>();
		String sql2 = "SELECT maDichVu FROM dichvu";
		listMa = _jdbcTemplate.query(sql2, new ChonGiatriDichVuMapper());
		String sql = "SELECT tenDichVu FROM dichvu ";
		listTen = _jdbcTemplate.query(sql, new chonDichVuMapper());
		Map<ChonDichVu, ChonDichVu> phones = new HashMap<ChonDichVu, ChonDichVu>();
		for (int i = 0; i < listTen.size(); i++) {
			phones.put(listMa.get(i), listTen.get(i));

		}
		// System.out.println(phones);
		return phones;
	}

	public int saveRegisterRecruitment(ChonDichVu dv, ListNhanVienvaDichVu hsnv, String filename) {
		Date newDate2 = new Date();
		java.sql.Date newDateSql2 = new java.sql.Date(newDate2.getTime());
		if (Compare.checkDateThan16Year(hsnv.getNamSinh(), newDateSql2)) {
			if (hsnv.getSdt().toString().length() >= 9) {
				String sql = "insert into hosonhanvien(hoTen,namSinh,sdt,queQuan,noiCuTruHienTai,hinhanh, tinhTrangLamViec, tinhTrangDuyetHoSo,maDichVu)values('"
						+ hsnv.getHoTen() + "','" + hsnv.getNamSinh() + "','" + hsnv.getSdt() + "','"
						+ hsnv.getQueQuan() + "','" + hsnv.getNoiCuTruHienTai() + "','" + filename + "','ranh','chua','"
						+ dv.getMaDichVu() + "')";
				return _jdbcTemplate.update(sql);
			} else {
				return 5;
			}

		} else {
			return 0;
		}

	}

	public int saveLienHe(LienHe emp) { 
 
		String sql = "insert into lienhe(tenLienHe,Email,thongDiep)values('" + emp.getTenLienHe() + "','"
				+ emp.getEmail() + "','" + emp.getThongDiep() + "')"; 
		return _jdbcTemplate.update(sql); 

	} 

} 
