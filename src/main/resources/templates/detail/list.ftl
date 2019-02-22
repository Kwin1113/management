<html>
<#include "../common/header.ftl"/>
<body>
<#--商品修改模态框-->
<#--<div class="modal fade" id="productModifyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">-->
    <#--<div class="modal-dialog" role="document">-->
        <#--<div class="modal-content">-->
            <#--<div class="modal-header">-->
                <#--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>-->
                <#--</button>-->
                <#--<h4 class="modal-title" id="myModalLabel">修改商品</h4>-->
            <#--</div>-->
            <#--<form class="form-horizontal" method="post" action="/product/update">-->
                <#--<div class="modal-body">-->
                    <#--<input hidden type="text" name="productId" id="productId_modify_input">-->
                    <#--<div class="form-group">-->
                        <#--<label class="col-sm-2 control-label">款式</label>-->
                        <#--<div class="col-sm-10">-->
                            <#--<input type="text" name="productType" class="form-control" id="productType_modify_input"-->
                                   <#--placeholder="请输入商品款式...">-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="form-group">-->
                        <#--<label class="col-sm-2 control-label">规格</label>-->
                        <#--<div class="col-sm-10">-->
                            <#--<input type="text" name="productSize" class="form-control" id="productSize_modify_input"-->
                                   <#--placeholder="请输入商品规格...">-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="form-group">-->
                        <#--<label class="col-sm-2 control-label">开向</label>-->
                        <#--<div class="col-sm-4">-->
                            <#--<select class="form-control" name="productDirection">-->
                                <#--<option value="1" selected>左内开</option>-->
                                <#--<option value="2">左外开</option>-->
                                <#--<option value="3">右内开</option>-->
                                <#--<option value="4">右外开</option>-->
                            <#--</select>-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="form-group">-->
                        <#--<label class="col-sm-2 control-label">单价</label>-->
                        <#--<div class="col-sm-10">-->
                            <#--<input type="number" name="productPrice" class="form-control"-->
                                   <#--id="productPrice_modify_input"-->
                                   <#--placeholder="请输入商品单价...">-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="form-group">-->
                        <#--<label class="col-sm-2 control-label">库存</label>-->
                        <#--<div class="col-sm-10">-->
                            <#--<input type="number" name="productStock" class="form-control"-->
                                   <#--id="productStock_modify_input"-->
                                   <#--placeholder="请输入商品库存...">-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
                <#--<div class="modal-footer">-->
                    <#--<button type="submit" class="btn btn-primary" id="productModifyBtn">保存</button>-->
                    <#--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>-->
                <#--</div>-->
            <#--</form>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->
<div id="wrapper" class="toggled">
    <#--sidebar-->
    <#include "../common/nav.ftl">
    <#--content-->
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <h3 class="text-info text-center">
                        订单详情
                    </h3>
                    <table class="table table-hover table-striped table-condensed">
                        <thead>
                        <tr>
                            <th>顾客姓名</th>
                            <th>顾客地址</th>
                            <th>安装人员</th>
                            <th>型号</th>
                            <th>规格</th>
                            <th>开向</th>
                            <th>单价</th>
                            <th>数量</th>
                            <th>订单状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDetailList as orderDetail>
                            <#list orderMasterList as orderMaster>
                                <tr>
                                    <#if orderMaster.orderId == orderDetail.orderId>
                                        <td>
                                            ${orderMaster.buyerName}
                                        </td>
                                        <td>
                                            ${orderMaster.buyerAddress}
                                        </td>
                                        <#list installerList as installer>
                                            <#if orderMaster.installerId == installer.installerId>
                                                <td>
                                                    ${installer.installerName}
                                                </td>
                                            <#else>
                                                <td>空</td>
                                            </#if>
                                        </#list>
                                        <td>${orderDetail.productType}</td>
                                        <td>${orderDetail.productSize}</td>
                                        <td>
                                            ${orderDetail.getProductDirectionByCode().message}
                                        </td>
                                        <td>¥${orderDetail.productPrice}</td>
                                        <td>${orderDetail.productQuantity}</td>
                                        <td>${orderMaster.getOrderStatusByCode().message}</td>
                                        <td>
                                            <a href="/order/detail/${orderDetail.orderId}" type="button" class="btn btn-default
                                    btn-primary">详情
                                            </a>
                                        </td>
                                    </#if>
                                </tr>
                            </#list>
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
            // data: productId,
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

    // $(document).on("click", ".modify_btn", function () {
    //     getProduct($(this).attr("productId"));
    //     $("#productId_modify_input").val($(this).attr("productId"));
    //     $("#productModifyModal").modal({
    //         backdrop: "static"
    //     });
    // });
</script>
</html>