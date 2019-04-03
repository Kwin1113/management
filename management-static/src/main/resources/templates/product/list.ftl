<html>
<#include "../common/header.ftl"/>
<body>
<#--商品新增模态框-->
<div class="modal fade" id="productAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新增商品</h4>
            </div>
            <form class="form-horizontal" method="post" action="/product/add" id="productAddForm">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">款式</label>
                        <div class="col-sm-10">
                            <input type="text" name="productType" class="form-control" id="productType_add_input"
                                   placeholder="请输入商品款式...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">规格</label>
                        <div class="col-sm-10">
                            <input type="text" name="productSize" class="form-control" id="productSize_add_input"
                                   placeholder="请输入商品规格...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">开向</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="productDirection">
                                <option value="1" selected>左内开</option>
                                <option value="2">左外开</option>
                                <option value="3">右内开</option>
                                <option value="4">右外开</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">单价</label>
                        <div class="col-sm-10">
                            <input type="number" name="productPrice" class="form-control"
                                   id="productPrice_add_input"
                                   placeholder="请输入商品单价...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">库存</label>
                        <div class="col-sm-10">
                            <input type="number" name="productStock" class="form-control"
                                   id="productStock_add_input"
                                   placeholder="请输入商品库存...">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" id="productAddBtn">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </form>
        </div>
    </div>
</div>
<#--商品修改模态框-->
<div class="modal fade" id="productModifyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">修改商品</h4>
            </div>
            <form class="form-horizontal" method="post" action="/product/update" id="productModifyForm">
                <div class="modal-body">
                    <input hidden type="text" name="productId" id="productId_modify_input">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">款式</label>
                        <div class="col-sm-10">
                            <input type="text" name="productType" class="form-control" id="productType_modify_input"
                                   placeholder="请输入商品款式...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">规格</label>
                        <div class="col-sm-10">
                            <input type="text" name="productSize" class="form-control" id="productSize_modify_input"
                                   placeholder="请输入商品规格...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">开向</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="productDirection">
                                <option value="1" selected>左内开</option>
                                <option value="2">左外开</option>
                                <option value="3">右内开</option>
                                <option value="4">右外开</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">单价</label>
                        <div class="col-sm-10">
                            <input type="number" name="productPrice" class="form-control"
                                   id="productPrice_modify_input"
                                   placeholder="请输入商品单价...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">库存</label>
                        <div class="col-sm-10">
                            <input type="number" name="productStock" class="form-control"
                                   id="productStock_modify_input"
                                   placeholder="请输入商品库存...">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" id="productModifyBtn" onsubmit="return updateProduct
                    ()" onclick="return modifyConfirm()">保存
                    </button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div id="wrapper" class="toggled">
    <#--sidebar-->
    <#include "../common/nav.ftl">
    <#--content-->
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <h3 class="text-info text-center">
                        商品详情
                    </h3>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#productAddModal">
                        商品新增
                    </button>
                    <table class="table table-hover table-striped table-condensed">
                        <thead>
                        <tr>
                            <th>型号</th>
                            <th>规格</th>
                            <th>开向</th>
                            <th>单价</th>
                            <th>库存</th>
                            <th colspan="2" style="vertical-align: middle !important;text-align: center;">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list productList as product>
                            <tr>
                                <td>${product.productType}</td>
                                <td>${product.productSize}</td>
                                <td>
                                    ${product.getProductDirectionByCode().message}
                                </td>
                                <td>¥${product.productPrice}</td>
                                <td>${product.productStock}</td>
                                <td>
                                    <button type="button" class="btn btn-default
                                    btn-primary modify_btn" productId="${product.productId}">修改
                                    </button>
                                </td>
                                <td>
                                    <a href="/product/delete/${product.productId}" type="button" class="btn btn-default
                                    btn-danger" onclick="return deleteConfirm()">删除</a>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function getProduct(productId) {
        $.ajax({
            url: "/product/selectOne/" + productId,
            type: "GET",
            async: true,
            datatype: "json",
            success: function (result) {
                $("#productType_modify_input").val(result.product.productType);
                $("#productSize_modify_input").val(result.product.productSize);
                $("#productModifyModal select").val(result.product.productDirection);
                $("#productPrice_modify_input").val(result.product.productPrice);
                $("#productStock_modify_input").val(result.product.productStock);
            }
        });
    }

    $(document).on("click", ".modify_btn", function () {
        getProduct($(this).attr("productId"));
        $("#productId_modify_input").val($(this).attr("productId"));
        $("#productModifyModal").modal({
            backdrop: "static"
        });
    });

    //增加重复商品
    $(document).ready(function () {
        var options = {
            error: function () {
                alert("操作失败");
            },
            success: function (result) {
                if (result.msg != "成功") {
                    alert(result.msg);
                } else {
                    location.reload();
                }
            },
            dataType: 'json'
        };
        $("#productAddForm").submit(function () {
            $(this).ajaxSubmit(options);
            return false;
        });
    });

    //修改重复商品
    $(document).ready(function () {
        var options = {
            error: function () {
                alert("操作失败");
            },
            success: function (result) {
                if (result.msg != "成功") {
                    alert(result.msg);
                } else {
                    location.reload();
                }
            },
            dataType: 'json'
        };
        $("#productModifyForm").submit(function () {
            $(this).ajaxSubmit(options);
            return false;
        });
    });

    //提交update页面不跳转
    function updateProduct() {
        $("#productModifyForm").ajaxSubmit(function () {
        });
        return false;
    }
</script>
<#include "../common/js.ftl">
</html>