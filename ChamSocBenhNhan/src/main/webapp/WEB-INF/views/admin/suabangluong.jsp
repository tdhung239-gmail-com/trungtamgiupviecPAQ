<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ol class="breadcrumb mb-4">
	<li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
	<li class="breadcrumb-item active">Cập nhật lại bảng lương </li>
</ol>
${param.message}
<div class="outside"
	style="width: 80%; margin: 0 auto; padding-top: 50px; padding-bottom: 50px; min-height: 200px; background: #F5F5F5; border-radius: 10px 10px; box-shadow: 0 6px 12px 0 rgb(0 0 0/ 20%);">

	<form:form method="post" action="/ChamSocBenhNhan/luuSuaBangLuong">
		<table
			style="margin: 0 auto; padding: 20px; border-collapse: separate; border-spacing: 40px 20px;">
			<tr>
				<th colspan="2">
					<h3
						style="color: red; text-align:center; font-family: Arial; margin-bottom: 20px; font-weight: bold;">CHỈNH
						SỬA DỮ LIỆU BẢNG LƯƠNG</h3>
				</th>
			</tr>
			<tr>
				<td></td>
				<td><form:hidden path="idBangLuong" /></td>
			</tr>
			<tr>
				<td style="font-size: 18px;">Chọn nhân viên : &nbsp &nbsp</td>
				<td style="font-size: 18px; width: 120px;"><form:select
						path="maHSNV" items="${chonMaNhanVien}"
						style=" font-size: 18px; width: 191px;">
					</form:select></td>
			</tr>

			<tr>
				<td style="font-size: 18px;">Nhập mã đăng ký dịch vụ :&nbsp
					&nbsp</td>
				<td><form:input path="maDKDV" required="required" /></td>
			</tr>

			<tr>
				<td style="font-size: 18px;">Tình trạng thanh toán: &nbsp &nbsp</td>
				<td style="font-size: 18px; width: 120px;"><form:select
						path="tinhTrangThanhToan" items="${chonTinhTrangThanhToan}"
						style=" font-size: 18px; width: 191px;">
					</form:select></td>
			</tr>
			<tr>

				<th colspan="2"><a
					style="margin-left: 23%; text-decoration: none; font-weight: bold; padding: 10px; color: red; border-radius: 10px 10px; font-size: 20px; background: linear-gradient(90deg, #EEEEEE, #fab397); border: white; box-shadow: 0 6px 12px 0 rgb(0 0 0/ 20%);"
					href="<c:url value="/quan-li/bang-luong"/>">Trở về</a> <input
					style="margin-left: 10%; margin-top: 40px; font-weight: bold; padding: 10px; color: red; border-radius: 10px 10px; font-size: 20px; background: linear-gradient(90deg, #EEEEEE, #fab397); border: white; box-shadow: 0 6px 12px 0 rgb(0 0 0/ 20%);"
					type="submit" value="Cập nhật" /></th>
			</tr>
		</table>
	</form:form>
</div>
