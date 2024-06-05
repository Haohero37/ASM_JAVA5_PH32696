<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js">
    <title>Cập Nhật Sản Phẩm</title>
</head>
<body>
    <div class="container">
        <h1>Cập Nhật Thông Tin Sản Phẩm</h1>
        <form  action="/cua-hang/san-pham/update" method="post">
            <div class="mb-3">
                <label class="form-label" for="id">ID</label>
                <input class="form-control" type="text" id="id" name="id" value="${SanPham.id}" readonly>
            </div>
            <div class="mb-3">
                <label class="form-label" for="ma">Mã  </label>
                <input class="form-control" type="text" id="ma" name="ma" value="${SanPham.ma}" readonly>
            </div>
            <div class="mb-3">
                <label class="form-label" for="ten">Tên</label>
                <input class="form-control" type="text" id="ten" name="ten" value="${SanPham.ten}" >
                <c:if test="${not empty errors}">
                    <small class="form-text text-danger">${errors['ten']}</small>
                </c:if>
            </div>
            <div class="mb-3">
                <label class="form-label" for="trangThai" class="form-label">Trạng Thái</label>
                <select  class="form-control" name="trangThai" id="trangThai">
                    <option value="" ${SanPham.trangThai  == null ?'selected':''}>Chọn Trạng Thái</option>
                    <option value="ACTIVE" ${SanPham.trangThai =='ACTIVE' ?'selected':''}>Đang Hoạt Động</option>
                    <option value="INACTIVE" ${SanPham.trangThai ==  'INACTIVE'?'selected':''}>Ngưng Hoạt Động</option>
                </select>
                <c:if test="${not empty errors}">
                    <small  class="form-text text-danger">${errors['trangThai']}</small>
                </c:if>
            </div>
            <button class="btn btn-warning mb-3 " >Cập Nhật</button>
            <a  href="/cua-hang/san-pham/index" class="btn btn-success mb-3 ">Quay Lại Trang Chủ</a>
        </form>
    </div>
</body>
</html>