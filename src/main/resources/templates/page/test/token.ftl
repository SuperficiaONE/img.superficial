<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>生成token</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <#include  "/commonCss.ftl">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<form class="layui-form" onsubmit="return false;">
    <div style="margin-left: auto;margin-right: auto;margin-top: 30px; width: 45%; border-radius: 20px;background-color: rgba(100,100,100,0.2);padding-bottom: 20px">
        <h2 style="text-align: center;margin-bottom: 20px;padding-top: 10px;">生成token</h2>
        <div class="layui-form-item">
            <label class="layui-form-label">userAgentId:</label>
            <div class="layui-input-inline">
                <input class="layui-input" style="height: 30px;" name="userAgentId"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">dataId:</label>
            <div class="layui-input-inline">
                <input class="layui-input" style="height: 30px;" name="dataId"/>
            </div>
        </div>
        <div class="layui-form-item ">
            <label class="layui-form-label">loginName:</label>
            <div class="layui-input-inline">
                <input class="layui-input" style="height: 30px;" name="loginName"/>
            </div>
        </div>
        <div class="layui-form-item ">
            <label class="layui-form-label">tokenVersion:</label>
            <div class="layui-input-inline">
                <input class="layui-input" style="height: 30px;" name="tokenVersion"/>
            </div>
        </div>
        <div class="layui-form-item ">
            <label class="layui-form-label">userType:</label>
            <div class="layui-input-inline">
                <input class="layui-input" style="height: 30px;" name="userType"/>
            </div>
        </div>
        <div class="layui-form-item ">
            <label class="layui-form-label">stationId:</label>
            <div class="layui-input-inline">
                <input class="layui-input" style="height: 30px;" name="stationId"/>
            </div>
        </div>
        <div class="layui-form-item ">
            <label class="layui-form-label">linkName:</label>
            <div class="layui-input-inline">
                <input class="layui-input" style="height: 30px;" name="linkName"/>
            </div>
        </div>
        <div class="layui-form-item" style="padding-bottom: 20px;">
            <div class="layui-input-block">
                <button id="submit" class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>
</form>

  <#include  "/commonJS.ftl">
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use(['jquery', 'form', 'layer'], function () {
        var layer = layui.layer;
        $("#submit").click(function () {
            var userAgentId = $("input[name='userAgentId']").val();
            var dataId = $("input[name='dataId']").val();
            var loginName = $("input[name='loginName']").val();
            var tokenVersion = $("input[name='tokenVersion']").val();
            var userType = $("input[name='userType']").val();
            var stationId = $("input[name='stationId']").val();
            var linkName = $("input[name='linkName']").val();

            var data = {};
            data['userAgentId'] = userAgentId;
            data['dataId'] = dataId;
            data['loginName'] = loginName;
            data['tokenVersion'] = tokenVersion;
            data['userType'] = userType;
            data['stationId'] = stationId;
            data['linkName'] = linkName;


            post("/api/test/getToken", data, function (res) {
                if (res.state == 1) {
                    layer.open({
                        title: '生成的token：',
                        area: '500px',
                        content: "<div ><textarea style='width: 450px;height:120px;'>"+res.data.token+"</textarea> </div>"
                    });
                } else {
                    layer.open({
                        title: '提示'
                        , content: res.msg
                    });
                }
            })
        })
    })
</script>

</body>
</html>

</html>
