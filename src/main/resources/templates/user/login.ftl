<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="/static/log/css/styles.css">
</head>
<body>
<div class="htmleaf-container">
    <div class="wrapper">
        <div class="container">
            <h1>欢迎</h1>

            <form class="form" id="userForm" action="/user/checkUser" method="post">
                <input type="text" placeholder="用户名" name="userName">
                <input type="password" placeholder="密码" name="password">
                <button type="submit" id="login_button">登陆</button>
            </form>
            <span id="helpBlock" class="help-block">${msg!''}</span>

        </div>

        <ul class="bg-bubbles">
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </div>
</div>

<script src="/static/log/js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script>
    // $('#login-button').click(function (event) {
    //     event.preventDefault();
    //     $('form').fadeOut(300);
    //     $('.wrapper').addClass('form-success');
    // });


</script>

<#--<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';color:#000000">-->
    <#--<h1>管理系统</h1>-->
<#--</div>-->
</body>
</html>