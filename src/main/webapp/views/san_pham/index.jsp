<%@page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Quản Lý Sản Phẩm</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js">
</head>
<body>
<div class="container">
    <h1 class="center">Danh Sách Sản Phẩm</h1>
    <form class="row" action="/cua-hang/san-pham/index" method="get">
        <div class="col mb-3">
            <label for="nameormasp" class="form-label">Tên/Mã Sản Phẩm</label>
            <input type="text" class="form-control" id="nameormasp" value="${nameormasp}" name="nameormasp">
        </div>
        <div class="col mb-3">
            <label for="trangThai" class="form-label">Trạng Thái</label>
            <select type="pas" class="form-control" name="trangThai" id="trangThai">
                <option value="" ${trangThai  == null ?'selected':''}>Chọn Trạng Thái</option>
                <option value="ACTIVE" ${trangThai =='ACTIVE' ?'selected':''}>Đang Hoạt Động</option>
                <option value="INACTIVE" ${trangThai ==  'INACTIVE'?'selected':''}>Ngưng Hoạt Động</option>
            </select>
        </div>
        <div class="col mt-4  mb-3">
            <div class="row">
                <button type="submit" class="col  btn btn-primary">Lọc</button>
                <c:if test="${not empty nameormasp || not empty trangThai}">
                    <button type="submit" class="col btn btn-warning">
                        <a href="/cua-hang/san-pham/index">Hủy Bộ Lọc</a>
                    </button>
                </c:if>
            </div>
        </div>

    </form>
    <table class="table table-hover table-striped">
        <thead>
        <th>STT</th>
        <th>Mã</th>
        <th>Tên</th>
        <th>Trạng Thái</th>
        <th colspan="2">Action</th>
        </thead>
        <tbody>
        <c:forEach items="${lstSanPham.content}" var="sp">
            <tr>
                <td>${sp['id']}</td>
                <td>${sp['ma']}</td>
                <td>${sp['ten']}</td>
                <td>${sp['trangThai']}</td>
                <td>
                    <a class="btn" href="/cua-hang/san-pham/edit/${sp['id']}">Edit</a>
                    <a class="btn" href="/cua-hang/san-pham/remove/${sp['id']}">Remove</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="row">
        <!-- Previous Button -->
        <div class="col">
            <a   onclick="page(${currentPage - 1})"  class="btn ${currentPage == 1 ? 'disabled' : ''}" >Previous</a>
        </div>

        <!-- Page Numbers -->
        <div class="col">
            <ul class="pagination">
                <c:forEach begin="1" end="${totalPage}" var="page">
                    <c:if test="${page == 1 || (page >= currentPage - 1 && page <= currentPage + 1) || page == totalPage}">
                        <li class="page-item ${page == currentPage ? 'active' : ''}">
                            <a  class="page-link"  href="?page=${page}">${page}</a>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>

        <!-- Next Button -->
        <div class="col">
            <a   onclick="page(${currentPage + 1})"  class="btn ${currentPage == totalPage ? 'disabled' : ''}" >Next</a>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <a class="btn btn-success "  href="/cua-hang/san-pham/create">Thêm Mới Sản Phẩm</a>
        </div>
    </div>
</div>
</body>
</html>

<script>
    var links = document.getElementsByClassName("page-link");
    // Lặp qua từng liên kết và gắn sự kiện click
    for (var i = 0; i < links.length; i++) {
        links[i].addEventListener("click", function(event) {
            event.preventDefault(); // Ngăn chặn hành vi mặc định khi click vào liên kết

            var currentPage = window.location.href; // Lấy URL hiện tại
            var url = new URL(currentPage); // Tạo một đối tượng URL từ URL hiện tại

            // Thêm tham số page vào query string
            var params = new URLSearchParams(url.search);
            params.set('page', this.innerText); // Sử dụng nội dung của liên kết làm số trang

            // Cập nhật query string trong URL
            url.search = params.toString();

            // Chuyển hướng đến URL mới
            window.location.href = url.toString();
        });
    }
    function page(page) {
        var currentPage = window.location.href; // Lấy URL hiện tại
        var url = new URL(currentPage); // Tạo một đối tượng URL từ URL hiện tại

        // Thêm tham số page vào query string
        var params = new URLSearchParams(url.search);
        params.set('page', page); // Sử dụng nội dung của liên kết làm số trang
        console.log(page)
        // Cập nhật query string trong URL
        url.search = params.toString();

        // Chuyển hướng đến URL mới
        window.location.href = url.toString();
    }
</script>