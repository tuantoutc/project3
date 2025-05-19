<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="BuildingAPI" value="/api/building"/>
<html>
<head>
    <title>Thêm hoặc sửa tòa nhà</title>
</head>
<body>
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try { ace.settings.check('breadcrumbs', 'fixed') } catch (e) { }
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Trang Chủ</a>
                    </li>
                    <li class="active">Quản lý tòa nhà</li>
                    <li class="active">Thêm hoặc sửa tòa nhà</li>
                </ul><!-- /.breadcrumb -->
            </div>

            <div class="page-content">
                <div class="page-header">
                    <h1>Thêm hoặc sửa tòa nhà</h1>
                </div>

                <!-- /.ace-settings-container -->
                <!-- /.page-header -->
                <div class="row" style="margin-top: 20px; font-family: 'Times New Roman', Times, serif;">
                    <form:form class="form-horizontal"  id="list-form" style="margin-bottom: 6em;text-align: center;" modelAttribute="buildingEdit" method="get"  >
                        <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->

                            <div class="form-group">
                                <label class="col-xs-3">Tên tòa nhà</label>
                                <div class="col-xs-6">
                                    <form:input path="name" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Phường</label>
                                <div class="col-xs-6">
                                    <form:input path="ward"  class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Đường</label>
                                <div class="col-xs-6">
                                    <form:input path="street"  class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Quận</label>
                                <div  class="col-xs-4">
                                    <form:select class="form-control" path="district">
                                        <form:option value="">---Chọn quận---</form:option>
                                        <form:options items="${districts}" ></form:options>

                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Số tầng hầm</label>
                                <div class="col-xs-6">
                                    <form:input path="numberOfBasement"  class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Diện tích sàn</label>
                                <div class="col-xs-6">
                                    <form:input path="floorArea"  class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Kết cấu</label>
                                <div class="col-xs-6">
                                    <form:input path="structure"  class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Hướng</label>
                                <div class="col-xs-6">
                                    <form:input path="direction"  class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Hạng</label>
                                <div class="col-xs-6">
                                    <form:input path="level"  class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Diện tích thuê</label>
                                <div class="col-xs-6">
                                    <form:input path="rentArea"  class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Giá thuê</label>
                                <div class="col-xs-6">
                                    <form:input path="rentPrice"  class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Mô tả giá </label>
                                <div class="col-xs-6">
                                    <form:input path="rentPriceDescription"  class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Phí dịch vụ </label>
                                <div class="col-xs-6">
                                    <form:input path="serviceFee"  class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Phí ô tô</label>
                                <div class="col-xs-6">
                                    <form:input path="carFee"  class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Phí mô tô</label>
                                <div class="col-xs-6">
                                    <form:input path="motoFee"  class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Phí ngoài giờ</label>
                                <div class="col-xs-6">
                                    <form:input path="overtimeFee"  class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Thanh toán</label>
                                <div class="col-xs-6">
                                    <form:input path="payment"  class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Thời hạn thuê</label>
                                <div class="col-xs-6">
                                    <form:input path="rentTime"  class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Đặt cọc</label>
                                <div class="col-xs-6">
                                    <form:input path="deposit"  class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Thời gian trang trí</label>
                                <div class="col-xs-6">
                                    <form:input path="decorationTime"  class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Tên quản lý</label>
                                <div class="col-xs-6">
                                    <form:input path="managerName"  class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">SDT quản lý</label>
                                <div class="col-xs-6">
                                    <form:input path="managerPhone"  class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Ghi chú</label>
                                <div class="col-xs-6">
                                    <form:input path="note"  class="form-control" />
                                </div>
                            </div>
                            <c:if test="${ not empty buildingEdit.id}">
                            <div class="form-group " id="takeTypecode">
                                <label class="col-xs-3"> Loại tòa nhà</label>
                                <div class="col-xs-6">
                                    <c:forEach var="item" items="${typeCodeData}">
                                        <label class="checkbox-inline" style="margin: 10px">
                                            <input type="checkbox" name="typeCode" value="${item.code}" ${item.checked}> ${item.name}
                                        </label>
                                    </c:forEach>
                                </div>
                            </div>
                            </c:if>
                            <c:if test="${ empty buildingEdit.id}">
                            <div class="form-group ">
                                <label class="col-xs-3"> Loại tòa nhà</label>
                                <div class="col-xs-6">
                                    <form:checkboxes path="typeCode" items="${typeCodes}" style="margin: 10px  " class="checkbox-inline" />
                                </div>
                            </div>
                            </c:if>
                            <div class="form-group">
                                <label class="col-xs-3"></label>
                                <div class="col-xs-6" style="justify-items: center;">
                                <c:if test="${ not empty buildingEdit.id}">
                                     <button id="btnAddOrUpdateBuilding" type="button" class="btn btn-info btn-bold" style="min-width: 120px; border-radius: 4px;">
                                        <i class="ace-icon fa fa-floppy-o bigger-120"></i>
                                        Cập nhật
                                    </button>
                                    <button type="button" class="btn btn-danger btn-bold" id="btnCancel" style="min-width: 120px; border-radius: 4px;">
                                        <i class="ace-icon fa fa-times bigger-120"></i>
                                        Hủy
                                    </button>

                                    </c:if>
                                    <c:if test="${ empty buildingEdit.id}">
                                     <button id="btnAddOrUpdateBuilding" type="button" class="btn btn-info btn-bold" style="min-width: 120px; border-radius: 4px;">
                                        <i class="ace-icon fa fa-floppy-o bigger-120"></i>
                                        Thêm tòa nhà
                                    </button>
                                     <button type="button" class="btn btn-danger btn-bold" id="btnCancel" style="min-width: 120px; border-radius: 4px;">
                                        <i class="ace-icon fa fa-times bigger-120"></i>
                                        Hủy
                                    </button>
                                    </c:if>

                                </div>
                            </div>
                        <!-- bảng danh sách -->
                        <form:hidden path="id" id="buildingId"/>
                    </div><!-- /.col -->
                    </form:form>

                </div><!-- /.row -->
            </div><!-- /.page-content -->
        </div><!-- /.main-content-inner -->
    </div><!-- /.main-content -->
    <script>
    <%--function loadTypeCodeBuilding(buildingid) {--%>
    <%--     $.ajax({--%>
    <%--            type: "GET" ,--%>
    <%--            url: "${BuildingAPI}/"+"type/"+buildingid+"",--%>
    <%--            // data: JSON.stringify(data),--%>
    <%--            contentType: "application/json",--%>
    <%--            dataType: "JSON",--%>
    <%--            success: function (response) {--%>
    <%--                var row='';--%>
    <%--                $.each(response.data, function (index, item) {--%>

    <%--                    row+='<td class="center"> <input type="checkbox"  id="checkbox_'+ item.name  +' " '+ item.checked +' >';--%>

    <%--                });--%>
    <%--                $('#takeTypecode div ').html(row);--%>
    <%--                console.info("Success");--%>

    <%--            },--%>
    <%--            error: function (error) {--%>
    <%--                console.log(error);--%>
    <%--                alert("lay loai toa nha that bai thất bại!");--%>
    <%--                window.location.href = "<c:url value="/admin/building-list?message= error" />";--%>

    <%--            }--%>
    <%--        });--%>
    <%--}--%>
        $('#btnAddOrUpdateBuilding').click(function () {
            var data ={};
            var typeCode =[];
            var formdata = $('#list-form').serializeArray();
            $.each(formdata, function (i, v) {
                if(v.name != 'typeCode'){
                    data[""+v.name+""] = v.value;
                }else{
                    typeCode.push(v.value);
                }
            }) ;
            data['typeCode'] = typeCode;
            if(data['name'] != "" && data['ward'] != ""&& typeCode != ""){
            addOrUpdateBuilding(data);
            }
            else
                {
                   window.location.href = "<c:url value="/admin/building-edit?fill-in-the-information"/>";

                }

            });
            // call api
            function addOrUpdateBuilding(data){
                $.ajax({
                type: "POST" ,
                url: "${BuildingAPI}",
                data: JSON.stringify(data),
                contentType: "application/json",
                dataType: "JSON",
                success: function (response) {
                    console.log("SUCCESS");
                    alert("Thêm tòa nhà thành công!");
                },
                error: function (error) {
                    console.log(error);
                    alert("Thêm tòa nhà thất bại!");
                }
            });
            }

        $('#btnCancel').click(function (e) {
        e.preventDefault(); // Ngăn chặn sự kiện submit form
        window.location.href = "/admin/building-list";
        });
    </script>
</body>
</html>