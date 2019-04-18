<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加字典</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <#include  "/commonCss.ftl">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<form class="layui-form" onsubmit="return false;">
    <div style="margin-left: auto;margin-right: auto;margin-top: 30px; width: 45%; border-radius: 20px;background-color: rgba(100,100,100,0.2);padding-bottom: 20px">
        <h2 style="text-align: center;margin-bottom: 20px;padding-top: 10px;">添加字典</h2>
        <div class="layui-form-item" id="dictType">

        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">添加的value</label>
            <div class="layui-input-inline" >
                <input class="layui-input" style="height: 30px;" name="dictValue"/>
            </div>
        </div>
        <div class="layui-form-item chineseText" >
            <label class="layui-form-label">添加的含义</label>
            <div class="layui-input-inline" >
                <input class="layui-input" style="height: 30px;" name="chineseText"/>
            </div>
        </div>
        <div class="layui-form-item" style="padding-bottom: 20px;">
            <div class="layui-input-block">
                <button id="submit" class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary" style="display: none" onclick="window.location.reload()">重置</button>
            </div>
        </div>
    </div>
</form>

  <#include  "/commonJS.ftl">
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    function changeDataKeyShow() {
        var  dictType = $(" select[name='dictType'] ").val();
        if(dictType == undefined || dictType == ""){
            $("input[name='dictValue']").val("dict_type")
            $("input[name='chineseText']").val("字典类型")
            $("input[name='chineseText']").attr("disabled","true")
            $("input[name='dictValue']").attr("disabled","true")
            $("button[type='reset']").hide()
        }else {
            $("input[name='dictValue']").val("")
            $("input[name='dictValue']").removeAttr("disabled")
            $("input[name='chineseText']").removeAttr("disabled")
            $("button[type='reset']").show()
        }
        if( dictType==undefined || dictType=="" || dictType == "dict_type"){
            $(".chineseText").show()
        }else {
            $(".chineseText").hide()
        }

    }
    function reloaddictTypeSelect() {
        initSelect("/webapi/dict/formSelectList?dictTypes=11&dictKey=0",changeDataKeyShow)
    }


    
    layui.use(['layer','form', 'jquery'], function () {
       // var $ = layer.jquery;

        var w = document.documentElement.clientWidth;
        var h = document.documentElement.clientHeight;
        var form  = layui.form;
        var layer = layui.layer;
        reloaddictTypeSelect()
        form.on('select(search_type)',function(data){
           changeDataKeyShow()
        });

        $("#submit").click(function () {
            var  dictType = $(" select[name='dictType'] ").val();
            var  dictValue = $("input[name='dictValue']").val()
            if(dictType==undefined){
                dictType = ""
            }
            var formData = {};
            formData['dictType'] = dictType;
            formData['dictValue'] = dictValue;
            if( dictType==undefined || dictType=="" || dictType == "dict_type"){
                var  chineseText = $("input[name='chineseText']").val()
                formData['chineseText'] = chineseText;
            }
            post("/api/dict/add",formData,function (res) {
                if(res.state == 1){
                    var  dictType = $(" select[name='dictType'] ").val();
                    if( dictType==undefined || dictType=="" || dictType == "dict_type"){
                      //  window.location.href="/page/dict/add.htm"
                        reloaddictTypeSelect()
                    }
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
