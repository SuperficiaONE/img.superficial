<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加菜单</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<form class="layui-form" onsubmit="return false;">
    <div style="margin-left: auto;margin-right: auto;margin-top: 30px; width: 40%; border-radius: 20px;background-color: rgba(100,100,100,0.2)">
        <h2 style="text-align: center;margin-bottom: 20px;padding-top: 10px;">添加菜单</h2>
        <div class="layui-form-item">
            <label class="layui-form-label">菜单名称：</label>
            <div class="layui-input-inline">
                <input class="layui-input" placeholder="请输入菜单名称" name="menuName"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">菜单链接：</label>
            <div class="layui-input-inline">
                <input class="layui-input" placeholder="请输入菜单链接" name="url">
            </div>
        </div>
        <div class="layui-form-item" id="menuLevel">

        </div>
        <div class="layui-form-item" id="parentId">
        </div>
        <div class="layui-form-item" id="menuBack">
        </div>
        <div class="layui-form-item" id="menuLogin">
        </div>
        <div class="layui-form-item" style="padding-bottom: 20px;">
            <div class="layui-input-block">
                <button id="submit" class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary" onclick="window.location.reload()">重置</button>
            </div>
        </div>
    </div>
</form>

<script src="/static/layui/layui.js" charset="utf-8"></script>
<script src="/static/jquery/jquery-1.9.1.min.js" charset="utf-8"></script>
<script src="/static/jquery/base.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>

    function initParentId(){
        var menuLevel = $("select[name='menuLevel']").val()
        initSelect("/webapi/menu/getParentIdSelectVo?menuLevel="+menuLevel,undefined);
    }

    layui.use(['form', 'jquery', 'layer'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        initSelects("/webapi/dict/formSelectList?dictTypes=menuLogin,menuBack")
        initSelect("/webapi/dict/formSelectList?dictTypes=menuLevel&dictKey=1",initParentId)
        $("#submit").click(function () {
            var menuName = $("input[name='menuName']").val();
            var url = $("input[name='url']").val();
            var flag = true;
            if (isEmpty(menuName)) {
                flag = false;
                layer.open({
                    title: '提示'
                    , content: "菜单名称不能为空"
                });
            }
            if (isEmpty(url)) {
                flag = false;
                layer.open({
                    title: '提示'
                    , content: "菜单链接不能为空"
                });
            }
            var isBack = $("select[name='menuBack']").val();
            var needLogin = $("select[name='menuLogin']").val();
            var menuLevel = $("select[name='menuLevel']").val();
            var parentId = $("select[name='parentId']").val();
            var formData = {};
            formData["menuName"] = menuName;
            formData["url"] = url;
            formData["menuBack"] = isBack;
            formData["menuLogin"] =  needLogin;
            formData["menuLevel"] =  menuLevel;
            if (parentId!=undefined) {
                formData["parentId"] = parentId
            }
            console.log(formData)
            if (flag) {
                post("/api/menu/add", formData, function (res) {
                            if (res.state == 1) {
                                layer.msg(res.msg, {
                                    icon: 1,
                                    time: 2000
                                }, function () {
                                });
                            } else {
                                layer.open({
                                    title: '提示'
                                    , content: res.msg
                                });
                            }
                        }
                );
            }
        })

    })
</script>

</body>
</html>

</html>
