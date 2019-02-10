<html>
<#include "../common/header.ftl"/>
<body>
<#--安装人员新增模态框-->
<div class="modal fade" id="installerAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新增安装人员</h4>
            </div>
            <form class="form-horizontal" method="post" action="/installer/add">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <input type="text" name="installerName" class="form-control" id="installerName_add_input"
                                   placeholder="请输入姓名...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系方式</label>
                        <div class="col-sm-10">
                            <input type="text" name="installerPhone" class="form-control" id="installerPhone_add_input"
                                   placeholder="请输入联系方式...">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" id="installerAddBtn">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </form>
        </div>
    </div>
</div>
<#--安装人员修改模态框-->
<div class="modal fade" id="installerModifyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">修改安装人员</h4>
            </div>
            <form class="form-horizontal" method="post" action="/installer/update">
                <div class="modal-body">
                    <input hidden type="text" name="installerId" id="installerId_modify_input">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <input type="text" name="installerName" class="form-control" id="installerName_modify_input"
                                   placeholder="请输入姓名...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系方式</label>
                        <div class="col-sm-10">
                            <input type="text" name="installerPhone" class="form-control"
                                   id="installerPhone_modify_input"
                                   placeholder="请输入联系方式...">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" id="installerModifyBtn">保存</button>
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
                        安装人员详情
                    </h3>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#installerAddModal">
                        安装人员新增
                    </button>
                    <table class="table table-hover table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>姓名</th>
                            <th>联系方式</th>
                            <th colspan="2" style="vertical-align: middle !important;text-align: center;">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list installerList as installer>
                            <tr>
                                <td>${installer.installerName}</td>
                                <td>${installer.installerPhone}</td>
                                <td>
                                    <button type="button" class="btn btn-default
                                    btn-primary modify_btn" installerId="${installer.installerId}">修改
                                    </button>
                                </td>
                                <td>
                                    <a href="/installer/delete/${installer.installerId}" type="button" class="btn
                                    btn-default
                                    btn-danger">删除</a>
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
    function getInstaller(installerId) {
        $.ajax({
            url: "/installer/selectOne/"+installerId,
            type: "GET",
            async: true,
            datatype: "json",
            success: function (result) {
                $("#installerName_modify_input").val(result.installer.installerName);
                $("#installerPhone_modify_input").val(result.installer.installerPhone);
            }
        });
    }

    $(document).on("click", ".modify_btn", function () {
        getInstaller($(this).attr("installerId"));
        $("#installerId_modify_input").val($(this).attr("installerId"));
        $("#installerModifyModal").modal({
            backdrop: "static"
        });
    });
</script>
</html>