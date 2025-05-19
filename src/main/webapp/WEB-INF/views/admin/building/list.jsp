<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingListURL" value="/admin/building-list"/>
<c:url var="BuildingAPI" value="/api/building"/>

<head>
    <title>Danh sách tòa nhà</title>
</head>
<body>
<div class="main-content" >
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Trang Chủ</a>
                    </li>
                    <li class="active">Quản lý tòa nhà</li>
                    <li class="active">Danh sách toàn nhà</li>
                </ul><!-- /.breadcrumb -->

                <div class="nav-search" id="nav-search">
                    <form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
                    </form>
                </div><!-- /.nav-search -->
            </div>

            <div class="page-content">
                <!-- /.ace-settings-container -->
                <div class="page-header">
                    <div class="col-xs-12 " bis_skin_checked="1">
                        <div class="widget-box" bis_skin_checked="1">
                            <div class="widget-header" bis_skin_checked="1">
                                <h4 class="widget-title">Tìm kiếm</h4>

                                <div class="widget-toolbar" bis_skin_checked="1">
                                    <a href="#" data-action="collapse">
                                        <i class="ace-icon fa fa-chevron-up"></i>
                                    </a>

                                    <!-- <a href="#" data-action="close">
                                        <i class="ace-icon fa fa-times"></i>
                                    </a> -->
                                </div>
                            </div>

                            <div class="widget-body" style="font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;" >
                                <div class="widget-main"  >
                                    <form:form  id="listForm" modelAttribute="modelSearch" action="${buildingListURL}" method="get"   >
                                        <div class="row ">
                                            <div class ="form-group " >
                                                <div class="col-xs-6">
                                                    <label class="name" >Tên tòa nhà</label>
<%--                                                    <input id="name" name="name" type="text" value="${modelSearch.name}" placeholder="Nhập tên tòa nhà" class="form-control" />--%>
                                                        <form:input class="form-control" path="name"/>  
                                                </div>
                                                <div class="col-xs-6">
                                                    <label class="name">Diện tích sàn</label>
<%--                                                    <input type="number" placeholder="Nhập diện tích sàn" name="floorArea" value="${modelSearch.floorArea}" class="form-control" min="0" />--%>
                                                        <form:input class="form-control" path="floorArea"/>
                                                </div>
                                            </div>
                                            <div class="form-group ">
                                                <div class="col-xs-2">
                                                    <label class="name">Quận</label>
                                                    <form:select  class="form-control" path="district" >
                                                        <form:option value="">---Chọn quận---</form:option>
                                                        <form:options items="${districts}"></form:options>

                                                    </form:select>
                                                </div>

                                                <div class="col-xs-5">
                                                    <label class="name">Phường</label>
<%--                                                    <input type="text" placeholder="Nhập tên phường" class="form-control" name="ward" value="${modelSearch.ward}" />--%>
                                                        <form:input class="form-control" path="ward" />
                                                </div>
                                                <div class="col-xs-5">
                                                    <label class="name">Đường</label>
                                                    <form:input class="form-control" path="street" />
                                                </div>
                                            </div>
                                            <div class="form-group ">
                                                <div class="col-xs-4">
                                                    <label class="name">Số tầng hầm</label>
                                                    <form:input class="form-control" path="numberOfBasement" />
                                                </div>
                                                <div class="col-xs-4">
                                                    <label class="name">Hướng</label>
                                                    <form:input class="form-control" path="direction" />
                                                </div>
                                                <div class="col-xs-4">
                                                    <label class="name">Hạng</label>
                                                    <form:input class="form-control" path="level" />
                                                </div>
                                            </div>
                                            <div class="form-group ">
                                                <div class="col-xs-3">
                                                    <label class="name">Diện tích từ</label>
                                                    <form:input class="form-control" path="areaFrom" />
                                                </div>
                                                <div class="col-xs-3">
                                                    <label class="name" >Diện tích đến</label>
                                                    <form:input class="form-control" path="areaTo" />
                                                </div>
                                                <div class="col-xs-3">
                                                    <label class="name" >Giá thuê từ</label>
                                                    <form:input class="form-control" path="rentPriceFrom" />
                                                </div>
                                                <div class="col-xs-3">
                                                    <label class="name">Giá thuê đến</label>
                                                    <form:input class="form-control" path="rentPriceTo" />
                                                </div>

                                            </div>
                                            <div class="form-group" >
                                                <div class="col-xs-4" >
                                                    <label class="name" >Tên quản lý</label>
                                                    <form:input class="form-control" path="managerName" />
                                                </div>
                                                <div class="col-xs-4">
                                                    <label class="name">Điện thoại quản lý</label>
                                                    <form:input class="form-control" path="managerPhone" />
                                                </div>
                                                <div class="col-xs-4">
                                                    <label class="name">Nhân viên phụ trách</label>
                                                    <form:select class="form-control" path="staffId">
                                                        <form:option value="" > --Chọn nhân viên-- </form:option>
                                                        <form:options items="${listStaffs}"/>

                                                    </form:select>
                                                </div>
                                            </div>
                                            <div class="form-group " >
                                                <div class ="col-xs-6"  >
                                                    <form:checkboxes path="typeCode" items="${typeCodes}" style="margin: 10px  " class="checkbox-inline" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-xs-12">
                                                    <button id="btnSearchBuilding" class="btn btn-primary" style="display: flex; align-items: center; gap: 8px; border-radius: 10px;">
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search"
                                                             viewBox="0 0 16 16">
                                                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
                                                        </svg>
                                                        Tìm kiếm
                                                    </button>
                                                </div>
                                            </div>
                                        </div>


                                    </form:form>

                                </div>
                            </div>
                        </div>
                        <div class="pull-right">
                            <a href="/admin/building-edit">
                                <button class="btn btn-info" title="Thêm tòa nhà" style="border-radius: 10px; margin-right: 1em;">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-add" style="fill: white;"
                                         viewBox="0 0 16 16">
                                        <path
                                                d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0" />
                                        <path
                                                d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z" />
                                        <path
                                                d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z" />
                                    </svg>
                                </button>
                            </a>
                            <button class="btn btn-danger" title="Xóa tòa nhà" style="border-radius: 10px; margin-right: 1em;" id="btnDeleteBuilding">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-dash"
                                     viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1" />
                                    <path
                                            d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z" />
                                    <path
                                            d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z" />
                                </svg>
                            </button>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <hr>
                        <!-- bảng danh sách -->
                        <div class="col-xs-12" bis_skin_checked="1">
                            <table id="table-rsbuilding" style="margin: 3em 0 0.5em;" class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="center">
                                        <label class="pos-rel">
                                            <input type="checkbox" class="ace">
                                            <span class="lbl"></span>
                                        </label>
                                    </th>
                                    <th>Tên tòa nhà</th>
                                    <th > Địa chỉ </th >
                                    <th>Số tầng hầm </th>
                                    <th>Tên quản lý </th>
                                    <th>Điện thoại quản lý </th>
                                    <th>D.tích sàn </th>
                                    <th>Diện tích trống </th>
                                    <th>D.tích thuê </th >
                                    <th>Phí dịch vụ </th>
                                    <th>Phí MG </th>
                                    <th>Giá thuê </th>
                                    <th>Thao tác </th>

                                </tr>
                                </thead>

                                <tbody >
                                <c:forEach var="item" items="${buildingList}" >
                                    <tr >
                                    <td class="center">
                                        <label class="pos-rel">
                                            <input type="checkbox" class="ace" name="checkList" value="${item.id}">
                                            <span class="lbl"></span>
                                        </label>
                                    </td>
                                    <td>${item.name}</td>
                                    <td>${item.address}</td>
                                    <td>${item.numberOfBasement}</td>
                                    <td>${item.managerName}</td>
                                    <td >${item.managerPhone}</td>
                                    <td>${item.floorArea}</td>
                                    <td>${item.emptyArea}</td>
                                    <td>${item.rentArea}</td>
                                    <td>${item.serviceFee}</td>
                                    <td>${item.brokerageFee}</td>
                                    <td>${item.rentPrice}</td>
                                    <td>
                                        <div class="hidden-sm hidden-xs btn-group" bis_skin_checked="1">
                                            <button class="btn btn-xs btn-success" title="Giao nhà cho nhân viên" onclick="assingmentBuilding(${item.id})">
                                                <i class="ace-icon fa fa-check bigger-120"></i>
                                            </button>

                                            <a title="Sửa thông tin" class="btn btn-xs btn-info" href="/admin/building-edit-${item.id}">
                                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                                            </a>

                                            <button title="Xóa tòa nhà" class="btn btn-xs btn-danger" onclick="deleteBuilding(${item.id})">
                                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                            </button>
                                        </div>

                                        <div class="hidden-md hidden-lg" bis_skin_checked="1">
                                            <div class="inline pos-rel" bis_skin_checked="1">
                                                <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
                                                    <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                                </button>

                                                <ul
                                                        class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                                    <li>
                                                        <a href="/admin/building-edit" class="tooltip-info" data-rel="tooltip" title="Sửa thông tin "
                                                           data-original-title="View">
																		<span class="blue">
																			<i class="ace-icon fa fa-search-plus bigger-120"></i>
																		</span>
                                                        </a>
                                                    </li>

                                                    <li>
                                                        <a href="#" class="tooltip-success" data-rel="tooltip" title="Giao nhà cho nhân viên" onclick="assingmentBuilding(${item.id})"
                                                           data-original-title= "Edit ">
																		<span class="green">
																			<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																		</span>
                                                        </a>
                                                    </li>

                                                    <li>
                                                        <a href="#" class="tooltip-error" data-rel="tooltip" title="Xóa tòa nhà"
                                                           data-original-title="Delete">
																		<span class="red">
																			<i class="ace-icon fa fa-trash-o bigger-120"></i>
																		</span>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                 </c:forEach>

                                </tbody>
                            </table>
                        </div>
                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.page-content -->
        </div>
        <div class="pull-right" bis_skin_checked="1" style="margin: 0 1.5em 0;">
            <div class="col-sm-12" bis_skin_checked="1">
                <div bis_skin_checked="1">
                    <ul class="pagination">
                        <li class="disabled">
                            <a href="#">
                                <i class="ace-icon fa fa-angle-double-left"></i>
                            </a>
                        </li>

                        <li class="active">
                            <a href="#">1</a>
                        </li>

                        <li>
                            <a href="#">2</a>
                        </li>

                        <li>
                            <a href="#">3</a>
                        </li>

                        <li>
                            <a href="#">4</a>
                        </li>

                        <li>
                            <a href="#">5</a>
                        </li>

                        <li>
                            <a href="#">
                                <i class="ace-icon fa fa-angle-double-right"></i>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div><!-- /.main-content -->



    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->

<!-- Modal -->
<div class="modal fade" id="assingmentBuildingModal" role="dialog" style="font-family: 'Times New Roman', Times, serif;">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content" style="text-align: center;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title" >Danh sách nhân viên</h4>
            </div>
            <div class="modal-body"  >
                <table id="staffList" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th class="center">
                            <label class="pos-rel">Chọn</label>
                        </th>
                        <th style="text-align: center;">Tên nhân viên</th>
                    </tr>
                    </thead>

                    <tbody>
                  <!-- noi day du lieu tu data base bang nhan vien ra -->


                    </tbody>
                </table>
                <input type="hidden" id="buildingid" name="buildingid" value="" />
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnassingment">Giao tòa nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>

    </div>
</div>

<script>
// xử lý khi ân nút giao tòa nhà sẽ hiện lên bảng danh sách nhan viên
    function assingmentBuilding(buildingid)
    {
        $('#assingmentBuildingModal').modal('show');// mở modal bảng
        loadStaffList(buildingid);
        $('#buildingid').val(buildingid);
    }
    // hàm này để lấy danh sách nhân viên ra và đổ vào bảng
    function loadStaffList(buildingid) {
         $.ajax({
                type: "GET" ,
                url: "${BuildingAPI}/"+ buildingid+"/staffs",
                // data: JSON.stringify(data),
                contentType: "application/json",
                dataType: "JSON",
                success: function (response) {
                    var row='';
                    $.each(response.data, function (index, item) {

                        row+='<tr>';
                        row+='<td class="center"> <input type="checkbox"  value="'+ item.staffId +'" id="checkbox_'+ item.staffId  +' " '+ item.checked +' >';
                        row+='</td>'
                        row+='<td> '+item.fullName+' </td>';
                        row+='</tr>';


                    });
                    $('#staffList tbody ').html(row);
                    console.info("Success");

                },
                error: function (error) {
                    console.log(error);
                    alert("lay nhan vien thất bại!");
                    window.location.href = "<c:url value="/admin/building-list?message= error" />";

                }
            });
    }

// hàm này được xây dựng để xử lý sự kiện khi ấn vào nút giao sau khi đã chọn nhân viên quản lý cho tòa nhà hoặc sửa
    $('#btnassingment').click(function(e) {
        e.preventDefault();// Ngăn chặn hành động mặc định của nút submit
        var data ={};
        data['buildingId'] = $('#buildingid').val();
        var staffs =$('#staffList').find('tbody input[type="checkbox"]:checked').map(function() {
            return $(this).val();
        }).get();
        data['staffs'] = staffs;
        if(data['staffs'] != "")
            {
                assingment(data);
            }
        else
            {
                  window.location.href = "<c:url value="/admin/building-list?message= error" />";
                }
    });
    function assingment(data)
    {
    $.ajax({
                type: "POST" ,
                url: "${BuildingAPI}/"+ "assingment",
                data: JSON.stringify(data),
                contentType: "application/json",
                dataType: "JSON",
                success: function (response) {
                    console.log("SUCCESS");
                    alert("Giao tòa nhà thành công!");
                },
                error: function (error) {
                    console.info("Giao toa nha khong thanh cong")

                    window.location.href = "<c:url value="/admin/building-list?message= error" />";

                }
            });
    }





//nút này để đẩy dữ liệu lên param
    $('#btnSearchBuilding').click(function(e) {
        e.preventDefault();// sẽ tự hủy nếu gọi nhầm api , gọi nhầm trang
        $('#listForm').submit();
    });
    // hàm để xoá 1 tòa nhà
    function deleteBuilding(id){
        var buildingid = [id];
        deleteBuidings(buildingid);
    }
    // xử lý sau khi ấn nút xóa và giử về id
    $('#btnDeleteBuilding').click(function(e) {
        e.preventDefault();// Ngăn chặn hành động mặc định của nút submit
        var buildingIds =$('#table-rsbuilding').find('tbody input[type="checkbox"]:checked').map(function() {
            return $(this).val();
        }).get();
        deleteBuidings(buildingIds);
    });
    function deleteBuidings(data){
        $.ajax({
                type: "delete" ,
                url: "${BuildingAPI}/"+ data,
                data: JSON.stringify(data),
                contentType: "application/json",
                dataType: "JSON",
                success: function (response) {
                    console.log("SUCCESS");
                    alert("Xoá tòa nhà thành công!");
                },
                error: function (error) {
                    console.log(error);
                    alert("Xoá tòa nhà thất bại!");
                }
            });
    }
</script>



</body>
</html>
