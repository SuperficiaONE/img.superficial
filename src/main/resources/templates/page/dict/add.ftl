<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加字典</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<form class="layui-form" onsubmit="return false;">
    <div style="margin-left: auto;margin-right: auto;margin-top: 30px; width: 45%; border-radius: 20px;background-color: rgba(100,100,100,0.2);padding-bottom: 20px">
        <h2 style="text-align: center;margin-bottom: 20px;padding-top: 10px;">添加字典</h2>
        <div class="layui-form-item">
            <label class="layui-form-label">添加的类型</label>
            <div class="layui-input-inline" >
                <select class="layui-select" lay-filter="search_type" style="height: 30px;" name="dataType">
                </select>
            </div>
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

<script src="/static/layui/layui.js" charset="utf-8"></script>
<script src="/static/jquery/jquery-1.9.1.min.js" charset="utf-8"></script>

<script src="/static/jquery/base.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    function reloadDataTypeSelect() {
        addWaiting();

        getAsync("/webapi/dict/selectList",  false,function (res) {
            setTimeout(function () {
                removeWaiting()
            },1000)
            if (res.state == 1) {
                $("select[name='dateType']").empty()
                if (res.data != undefined) {
                    for (var i = 0; i < res.data.length; i++) {
                         var item =res.data[i]
                        $(" select[name='dataType'] ").append("<option value='"+item.dictValue+"'>"+item.dictText+"</option>")
                    }
                    renderForm()
                    changeDataKeyShow()

                }
            } else {

            }
        })
    }

    $(" select[name='dataType'] ").change(function () {
            changeDataKeyShow();
    })
    function changeDataKeyShow() {
      var  dataType = $(" select[name='dataType'] ").val();
      if(dataType == undefined || dataType == ""){
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
      if( dataType==undefined || dataType=="" || dataType == "dict_type"){
          $(".chineseText").show()
      }else {
          $(".chineseText").hide()
      }

    }
    
    layui.use(['form', 'jquery'], function () {
       // var $ = layer.jquery;

        var w = document.documentElement.clientWidth;
        var h = document.documentElement.clientHeight;
        var form  = layui.form;
        reloadDataTypeSelect()
        form.on('select(search_type)',function(data){
           changeDataKeyShow()
        });

        $("#submit").click(function () {
            var  dataType = $(" select[name='dataType'] ").val();
            var  dictValue = $("input[name='dictValue']").val()

            if(dataType==undefined){
                dataType = ""
            }
            var formData = {};
            formData['dataType'] = dataType;
            formData['dictValue'] = dictValue;
            if( dataType==undefined || dataType=="" || dataType == "dict_type"){
                var  chineseText = $("input[name='chineseText']").val()
                formData['chineseText'] = chineseText;
            }
            post("/api/dict/add",formData,function (res) {
                if(res.state == 1){
                    var  dataType = $(" select[name='dataType'] ").val();
                    if( dataType==undefined || dataType=="" || dataType == "dict_type"){
                        window.location.href="/page/dict/add.htm"
                    }{
                        layer.msg(res.msg)
                    }

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
