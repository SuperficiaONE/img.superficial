<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>保存模板</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <#include  "/commonCss.ftl">
</head>
<body>
<form class="layui-form" onsubmit="return false;">
    <div style="margin-left: auto;margin-right: auto;margin-top: 30px; width: 45%; border-radius: 20px;background-color: rgba(100,100,100,0.2);padding-bottom: 20px">
        <h2 style="text-align: center;margin-bottom: 20px;padding-top: 10px;">保存模板</h2>
        <div class="layui-form-item" >
            <label class="layui-form-label">模板类型</label>
            <div class="layui-input-block" >
                <button class="layui-btn" style="text-align: center;"  data="${vo.templateType!}" id="templateType">${dict.dictValue!}</button>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">before脚本</label>
            <div class="layui-input-block" >
                <textarea class="layui-textarea" style="height: 100px;width: 400px" name="beforeScript">${vo.beforeScript!}</textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">模板元素</label>
            <div class="layui-input-block" >
                <textarea class="layui-textarea" style="height: 100px;width: 400px" name="templateElements">${vo.templateElements!}</textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">模板脚本</label>
            <div class="layui-input-block" >
                <textarea class="layui-textarea" style="height: 100px;width: 400px" name="templateScript">${vo.templateScript!}</textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">after脚本</label>
            <div class="layui-input-block" >
                <textarea class="layui-textarea" style="height: 100px;width: 400px" name="afterScript">${vo.afterScript!}</textarea>
            </div>
        </div>
        <div class="layui-form-item " >
            <label class="layui-form-label">数据结构</label>
            <div class="layui-input-block" >
                <textarea class="layui-textarea" style="height: 100px;width: 400px" name="templateData">${vo.templateData!}</textarea>
            </div>
        </div>

        <div class="layui-form-item" style="padding-bottom: 20px;">
            <div class="layui-input-block">
                <button id="submit" class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary" >重置</button>
            </div>
        </div>
    </div>
</form>

  <#include  "/commonJS.ftl">
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    function renderAtrTemplate(res){
        if(res.data!=undefined&&res.data.length>0){
            $("textarea[name='templateScript']").text(res.data[0]['templateScript']==undefined?"":res.data[0]['templateScript']);
            $("textarea[name='beforeScript']").text(res.data[0]['beforeScript']==undefined?"":res.data[0]['beforeScript']);
            $("textarea[name='afterScript']").text(res.data[0]['afterScript']==undefined?"":res.data[0]['afterScript']);
            $("textarea[name='templateData']").text(res.data[0]['templateData']==undefined?"":res.data[0]['templateData']);
            $("textarea[name='templateElements']").text(res.data[0]['templateElements']==undefined?"":res.data[0]['templateElements']);
        }else {
            $("textarea[name='templateScript']").text("");
            $("textarea[name='beforeScript']").text("");
            $("textarea[name='afterScript']").text("");
            $("textarea[name='templateData']").text("");
            $("textarea[name='templateElements']").text("");
        }
    }
    function  changeType(type) {
        get("/api/artTemplate/list?types="+type,function (res) {
            if(res.state==1){
                renderAtrTemplate(res)
            }else {
                showError(res.msg)
            }
        })
    }

    layui.use(['jquery','form','layer'],function () {
        var layer = layui.layer;
        var form = layui.form;

        $("#submit").click(function () {
            var templateType = $("#templateTyp").attr('data');
            var templateScript = $("textarea[name='templateScript']").val();
            var beforeScript = $("textarea[name='beforeScript']").val();
            var afterScript = $("textarea[name='afterScript']").val();
            var templateData = $("textarea[name='templateData']").val();
            var templateElements = $("textarea[name='templateElements']").val();
            var data = {};
            data['templateType'] = templateType;
            data['templateScript'] = templateScript;
            data['afterScript'] = afterScript;
            data['beforeScript'] = beforeScript;
            data['templateData'] = templateData;
            data['templateElements'] = templateElements;
            post("/api/tbArtTemplate/save",data,function (res) {
                if(res.state == 1){
                    layer.msg(res.msg)
                }else {
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
