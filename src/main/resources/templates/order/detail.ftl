<html>
<#include "../common/header.ftl"/>
<body>
<#--详情新增模态框-->
<div class="modal fade" id="detailAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新增详情</h4>
            </div>
            <form class="form-horizontal" method="post" action="/detail/add">
                <div class="modal-body">
                    <input hidden type="text" name="orderId" id="orderId_add_input">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">型号</label>
                        <div class="col-sm-10">
                            <input type="text" name="productType" class="form-control" id="productType_add_input"
                                   placeholder="请输入商品型号...">
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
                        <label class="col-sm-2 control-label">数量</label>
                        <div class="col-sm-10">
                            <input type="number" name="productQuantity" class="form-control"
                                   id="productQuantity_add_input"
                                   placeholder="请输入商品数量...">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary update_btn" id="productModifyBtn">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </form>
        </div>
    </div>
</div>
<#--详情修改模态框-->
<div class="modal fade" id="detailModifyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">修改详情</h4>
            </div>
            <form class="form-horizontal" method="post" action="/detail/update">
                <div class="modal-body">
                    <input hidden type="text" name="detailId" id="detailId_modify_input">
                    <input hidden type="text" name="orderId" id="orderId_modify_input">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">型号</label>
                        <div class="col-sm-10">
                            <input type="text" name="productType" class="form-control" id="productType_modify_input"
                                   disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">规格</label>
                        <div class="col-sm-10">
                            <input type="text" name="productSize" class="form-control" id="productSize_modify_input"
                                   disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">开向</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="productDirection" disabled>
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
                                   disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">数量</label>
                        <div class="col-sm-10">
                            <input type="number" name="productQuantity" class="form-control"
                                   id="productQuantity_modify_input"
                                   placeholder="请输入商品数量...">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" id="productModifyBtn">保存</button>
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
                    <table class="table">
                        <thead>
                        <tr>
                            <th>顾客地址</th>
                            <th>顾客姓名</th>
                            <th>联系方式</th>
                            <th>安装人员</th>
                            <th>安装时间</th>
                            <th>预付订金</th>
                            <th>订单总价</th>
                            <th>订单状态</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${orderMaster.buyerAddress}</td>
                            <td>${orderMaster.buyerName}</td>
                            <td>${orderMaster.buyerPhone}</td>
                            <#list installerList as installer>
                                <#if orderMaster.installerId == installer.installerId>
                                    <td>${installer.installerName}</td>
                                </#if>
                            <#else>
                            </#list>
                            <td>${orderMaster.installTime?string('yyyy-MM-dd')}</td>
                            <td>${orderMaster.orderDeposit}</td>
                            <td>${orderMaster.orderAmount}</td>
                            <td>${orderMaster.getOrderStatusByCode().message}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-6 column">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>型号</th>
                            <th>规格</th>
                            <th>开向</th>
                            <th>单价</th>
                            <th>数量</th>
                            <#if orderMaster.getOrderStatusByCode().message=='新下单'>
                                <th colspan="2" style="vertical-align: middle !important;text-align: center;">操作</th>
                            </#if>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDetailList as orderDetail>
                            <tr>
                                <td>${orderDetail.productType}</td>
                                <td>${orderDetail.productSize}</td>
                                <td>
                                    ${orderDetail.getProductDirectionByCode().message}
                                </td>
                                <td>¥${orderDetail.productPrice}</td>
                                <td>${orderDetail.productQuantity}</td>
                                <#if orderMaster.getOrderStatusByCode().message=='新下单'>
                                    <td>
                                        <button type="button" class="btn btn-default
                                    btn-primary modify_btn" detailId="${orderDetail.detailId}" orderId="${orderDetail
                                        .orderId}">修改
                                        </button>
                                    </td>

                                    <td>
                                        <a href="/detail/delete/${orderDetail.detailId}/${orderDetail.orderId}"
                                           type="button"
                                           class="btn btn-default btn-danger" onclick="return deleteConfirm()">删除</a>
                                    </td>
                                </#if>
                            </tr>
                        </#list>
                        <#if orderMaster.getOrderStatusByCode().message=='新下单'>
                            <td>
                                <button type="button" class="btn btn-default
                                    btn-primary add_btn" orderId="${orderMaster.orderId}">新增
                                </button>
                            </td>
                        </#if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function getDetail(detailId) {
        $.ajax({
            url: "/detail/selectOne/" + detailId,
            type: "GET",
            async: true,
            datatype: "json",
            success: function (result) {
                $("#productType_modify_input").val(result.orderDetail.productType);
                $("#productSize_modify_input").val(result.orderDetail.productSize);
                $("#detailModifyModal select").val(result.orderDetail.productDirection);
                $("#productPrice_modify_input").val(result.orderDetail.productPrice);
                $("#productQuantity_modify_input").val(result.orderDetail.productQuantity);
            }
        });
    }

    $(document).on("click", ".modify_btn", function () {
        getDetail($(this).attr("detailId"));
        $("#detailId_modify_input").val($(this).attr("detailId"));
        $("#orderId_modify_input").val($(this).attr("orderId"));
        $("#detailModifyModal").modal({
            backdrop: "static"
        });
    });

    $(document).on("click", ".add_btn", function () {
        $("#orderId_add_input").val($(this).attr("orderId"));
        $("#detailAddModal").modal({
            backdrop: "static"
        });
    });
</script>
<#include "../common/js.ftl">
</html>