<html>
<#include "../common/header.ftl"/>
<body>
<#--订单新增模态框-->
<div class="modal fade" id="orderAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新增订单</h4>
            </div>
            <form class="form-horizontal" method="post" action="/order/add">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">顾客地址</label>
                        <div class="col-sm-10">
                            <input type="text" name="buyerAddress" class="form-control" id="buyerAddress_add_input"
                                   placeholder="请输入顾客地址...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">顾客姓名</label>
                        <div class="col-sm-10">
                            <input type="text" name="buyerName" class="form-control" id="buyerName_add_input"
                                   placeholder="请输入顾客姓名...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系方式</label>
                        <div class="col-sm-10">
                            <input type="text" name="buyerPhone" class="form-control" id="buyerPhone_add_input"
                                   placeholder="请输入顾客联系方式...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">安装人员</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="installerId" id="installerIdSelect">
                                <#list installerList as installer>
                                    <option value="${installer.installerId}">
                                        ${installer.installerName}
                                    </option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" id="productGroup" name="productAddForm">
                        <label class="col-sm-2 control-label">商品</label>
                        <div class="col-sm-10" id="productAdd">
                            <button class="btn btn-info" type="button" data-toggle="tooltip" title="新增"
                                    id="addProductGrpBtn"
                                    onclick="addProductGrp(this)">
                                <span class="glyphicon glyphicon-plus"></span>
                            </button>
                        </div>
                    </div>
                    <#--<div class="form-group">-->
                        <#--<label class="col-sm-2 control-label">安装时间</label>-->
                        <#--<div class="col-sm-10">-->
                            <#--<input type="text" name="installTime" class="form-control" id="installTime_add_input"-->
                                   <#--placeholder="请输入安装时间...">-->
                        <#--</div>-->
                    <#--</div>-->

                    <div class="form-group">
                        <label class="col-sm-2 control-label">安装时间</label>
                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                              data-link-format="yyyy-mm-dd" id="installer_time_div" data-link-field="dtp_input2">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            <input class="form-control" size="16" type="text" value="" name="installerTime" readonly>
                        </div>
                        <#--<input type="hidden" id="dtp_input2" value="" name="installTime"/><br/>-->
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">预付订金</label>
                        <div class="col-sm-10">
                            <input type="number" name="orderDeposit" class="form-control"
                                   id="orderDeposit_add_input"
                                   placeholder="请输入预付订金...">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" id="orderAddBtn">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </form>
        </div>
    </div>
</div>
<#--订单修改模态框-->
<div class="modal fade" id="orderModifyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">修改订单</h4>
            </div>
            <form class="form-horizontal" method="post" action="/order/update">
                <div class="modal-body">
                    <input hidden type="text" name="orderId" id="orderId_modify_input">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">顾客地址</label>
                        <div class="col-sm-10">
                            <input type="text" name="buyerAddress" class="form-control" id="buyerAddress_modify_input"
                                   placeholder="请输入顾客地址...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">顾客姓名</label>
                        <div class="col-sm-10">
                            <input type="text" name="buyerName" class="form-control" id="buyerName_modify_input"
                                   placeholder="请输入顾客姓名...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系方式</label>
                        <div class="col-sm-10">
                            <input type="text" name="buyerPhone" class="form-control" id="buyerPhone_modify_input"
                                   placeholder="请输入顾客联系方式...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">安装人员</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="installerId" id="installerIdSelect">
                                <#list installerList as installer>
                                    <option value="${installer.installerId}">
                                        ${installer.installerName}
                                    </option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <#--<div class="form-group">-->
                        <#--<label class="col-sm-2 control-label">安装时间</label>-->
                        <#--<div class="col-sm-10">-->
                            <#--<input type="text" name="installTime" class="form-control" id="installTime_modify_input"-->
                                   <#--placeholder="请输入安装时间...">-->
                        <#--</div>-->
                    <#--</div>-->
                    <div class="form-group">
                        <label class="col-sm-2 control-label">安装时间</label>
                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-format="yyyy-mm-dd" id="installer_time_div" data-link-field="dtp_input2">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            <input class="form-control" size="16" type="text" value="" name="installerTime"
                                   id="installTime_modify_input" readonly>
                        </div>
                        <#--<input type="hidden" id="dtp_input2" value="" name="installTime"/><br/>-->
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">预付订金</label>
                        <div class="col-sm-10">
                            <input type="number" name="orderDeposit" class="form-control"
                                   id="orderDeposit_modify_input"
                                   placeholder="请输入预付订金...">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" id="orderAddBtn" onclick="return modifyConfirm()">保存
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
                        订单详情
                    </h3>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#orderAddModal">
                        订单新增
                    </button>
                    <table class="table table-hover table-striped table-condensed">
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
                            <th>查看详情</th>
                            <th colspan="3" style="vertical-align: middle !important;text-align: center;">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderMasterList as order>
                            <tr>
                                <td>${order.buyerAddress}</td>
                                <td>${order.buyerName}</td>
                                <td>${order.buyerPhone}</td>
                                <#list installerList as installer>
                                    <#if order.installerId == installer.installerId>
                                        <td>${installer.installerName}</td>
                                    <#else>
                                        <td>空</td>
                                    </#if>
                                <#else>
                                </#list>
                                <td>${order.installTime?string('yyyy-MM-dd')}</td>
                                <td>¥${order.orderDeposit}</td>
                                <td>¥${order.orderAmount}</td>
                                <td>${order.getOrderStatusByCode().message}</td>
                                <td>
                                    <a href="/order/detail/${order.orderId}" type="button" class="btn btn-default
                                    btn-success">详情</a>
                                </td>
                                <#if order.getOrderStatusByCode().message == "新下单">
                                    <td>
                                        <button type="button" class="btn btn-default btn-primary modify_btn"
                                                orderId="${order.orderId}">修改
                                        </button>
                                    </td>
                                    <td>
                                        <a href="/order/finish/${order.orderId}" type="button"
                                           class="btn btn-default btn-success" onclick="return finishConfirm()">完成</a>
                                    </td>
                                    <td>
                                        <a href="/order/cancel/${order.orderId}" type="button"
                                           class="btn btn-default btn-danger" onclick="return cancelConfirm()">取消</a>
                                    </td>
                                <#else>
                                    <td>
                                        <button type="button" class="btn btn-default btn-primary" disabled>修改</button>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-default btn-success" disabled>完成</button>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-default btn-danger" disabled>取消</button>
                                    </td>
                                </#if>
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
<script>
    function addProductGrp(obj) {
        html =
            '<div class="input-group productAdd" style="width:100%;padding:0 0 1px 0;">' +

            '<div class="input-group productAdd" style="width:100%;padding:0 0 1px 0;">' +
            '<label class="input-group-addon" style="width:15%;padding:0 0 1px 0;">型号</label>' +
            '<input type="text" class="form-control" id="productType' + num + '" name="productAddFormList[' + num + '].productType" style="max-width:100%;">' +
            '<label class="input-group-addon" style="width:15%;padding:0 0 1px 0;">规格</label>' +
            '<input type="text" class="form-control" id="productSize' + num + '" name="productAddFormList[' + num + ']' +
            '.productSize" style="max-width:100%;">' +
            '</div>' +

            '<div class="input-group productAdd" style="width:100%;padding:0 0 1px 0;">' +
            '<label class="input-group-addon" style="width:15%;padding:0 0 1px 0;">开向</label>' +
            '<select class="form-control" id="productDirection' + num + '" name="productAddFormList[' + num + ']' +
            '.productDirection" ' +
            'style="min-width:100%;' +
            '">' +
            '<option value="1" selected>左内开</option>' +
            '<option value="2">左外开</option>' +
            '<option value="3">右内开</option>' +
            '<option value="4">右外开</option>' +
            '</select>' +
            '<label class="input-group-addon" style="width:15%;padding:0 0 1px 0;">数量</label>' +
            '<input type="text" class="form-control" id="productQuantity' + num + '" ' +
            'name="productAddFormList[' + num + '].productQuantity" style="max-width:100%;">' +
            '<span class="input-group-btn">' +
            '<button class="btn btn-info" type="button" data-toggle="tooltip" title="删除" ' +
            'id="delProductGrp"><span class="glyphicon glyphicon-minus"></span></button>' +
            '</span>' +
            '</div>' +

            '</div>'
        ;
        num += 1;
        obj.insertAdjacentHTML('beforebegin', html)
    }

    $(document).on('click', '#delProductGrp', function () {
        var el = this.parentNode.parentNode.parentNode;
        el.parentNode.removeChild(el);
        return
    })

    // 初始化一个数字，用于向后台list传值
    var num = 0;

    function getOrder(orderId) {
        $.ajax({
            url: "/order/selectOne/" + orderId,
            type: "GET",
            async: true,
            datatype: "json",
            success: function (result) {
                $("#buyerAddress_modify_input").val(result.order.buyerAddress);
                $("#buyerName_modify_input").val(result.order.buyerName);
                $("#buyerPhone_modify_input").val(result.order.buyerPhone);
                $("#installerId_modify_input").val(result.order.installerId);
                $("#installTime_modify_input").val(result.order.installTime);
                $("#orderDeposit_modify_input").val(result.order.orderDeposit);
                $("#installerIdSelect").val(result.order.installerId);
            }
        });
    }

    $(document).on("click", ".modify_btn", function () {
        getOrder($(this).attr("orderId"));
        $("#orderId_modify_input").val($(this).attr("orderId"));
        $("#orderModifyModal").modal({
            backdrop: "static"
        });
    });

</script>
<script type="text/javascript" src="/static/datatimepicker/bootstrap/js/bootstrap-datetimepicker.js"
        charset="UTF-8"></script>
<script type="text/javascript" src="/static/datatimepicker/bootstrap/js/locales/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script type="text/javascript">
    $('.form_date').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
</script>
<#include "../common/js.ftl">
</html>