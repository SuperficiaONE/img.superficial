<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加模板</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <#include  "/commonCss.ftl">
</head>
<body>
<form class="layui-form" onsubmit="return false;">
    <div style="margin-left: auto;margin-right: auto;margin-top: 30px; width: 45%; border-radius: 20px;background-color: rgba(100,100,100,0.2);padding-bottom: 20px">
        <h2 style="text-align: center;margin-bottom: 20px;padding-top: 10px;">保存模板</h2>
        <div class="layui-form-item">
            <label class="layui-form-label">模板类型</label>
            <div class="layui-input-block" >
               <button class="layui-btn" style="text-align: center;"  data="" id="templateType">请选择类型</button>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">before脚本</label>
            <div class="layui-input-block" >
                <textarea class="layui-textarea" style="height: 100px;width: 400px" name="beforeScript"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">模板元素</label>
            <div class="layui-input-block" >
                <textarea class="layui-textarea" style="height: 100px;width: 400px" name="templateElements"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">模板脚本</label>
            <div class="layui-input-block" >
                <textarea class="layui-textarea" style="height: 100px;width: 400px" name="templateScript"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">after脚本</label>
            <div class="layui-input-block" >
                <textarea class="layui-textarea" style="height: 100px;width: 400px" name="afterScript"></textarea>
            </div>
        </div>
        <div class="layui-form-item " >
            <label class="layui-form-label">数据结构</label>
            <div class="layui-input-block" >
                <textarea class="layui-textarea" style="height: 100px;width: 400px" name="templateData"></textarea>
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
    var index
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
    function  reloadTable() {
        layui.use(['table'],function () {
            var table = layui.table ;
            var option = {
                elem: '#dictType'
                , height: 300
                , width: 600
                ,limit: 5
                ,limits: [5,10,20,50]
                , url: "/api/tbArtTemplate/dictList?searchText="+$("input[name='searchText']").val()+"&create="+$("select[name='create']").val() //数据接口
                , page: true //开启分页
                , cols: [[ //表头
                    /*templateType
                templateName
                isCreate*/
                    {field: 'templateType', title: '模板类型id', width:200, fixed: 'left'}
                    ,{field: 'templateName', title: '模板名称', width:200}
                    ,{field: 'isCreate', title: '是否已创建', width:200}

                ]]
                , parseData: function (res) { //res 即为原始返回的数据
                    return {
                        "code": res.code, //解析接口状态
                        "msg": res.message, //解析提示文本
                        "count": res.count, //解析数据长度
                        "data": res.data //解析数据列表
                    };
                }
                , done: function () {
                    $("div[lay-id='dictType']").css("text-align", "center");
                    $("div[lay-id='dictType']").css("margin-left", "auto")
                    $("div[lay-id='dictType']").css("margin-right", "auto");
                    $("div[lay-id='dictType'] th").css("background-color", "#5FB878")
                    $("div[lay-id='dictType'] th").css("text-align", "center")

                }
            }
            table.render(option)
            table.on('row(test)', function(obj){
                $("#templateType").text(obj.data.templateName)
                changeType(obj.data.templateType)
                console.log(obj.data)
                layer.close(index)
            });
        })
    }

    function showDictTypeTableByLayer(){
          layui.use(['layer','table'],function () {
              var layer = layui.layer;
              var table = layui.table;
              index= layer.open({
                  title:'选择需要添加的模板',
                  content: "<div style='width: 100%;text-align: center;'> <label style=\"height: 30px;font-size:20px; \">搜索模板：</label>\n" +
                          "    <input name=\"searchText\" style=\"height: 30px;width: 220px\" placeholder=\"请输入要搜索模板类型\">\n"
                          +" <select name=\"create\" class='layui-select'  style='margin-left:5px;margin-right: 5px; border: 1px solid grey;border-radius: 4px; '>\n" +
                          "  <option value='' >全部</option>\n" +
                          "  <option value='false'>否</option>\n" +                            "  <option value='true'>是</option>\n" +
                          "</select>     "+
                          "    <button class=\"layui-btn\" id=\"search\">搜索</button></div>  <table  lay-filter=\"test\" id='dictType' style='display: inline-block; margin-left: auto;margin-right: auto; '></table>"
                  ,offset: '100px'
                  ,area:"700px"
              });
             reloadTable()
              $('#search').click(function () {
                  reloadTable()
              })
          })
    }

    layui.use(['jquery','form','layer'],function () {
        var layer = layui.layer;
        $('#templateType').click(function () {
            showDictTypeTableByLayer()
        })

        $("#submit").click(function () {
            var templateType = $("#templateType").attr("data");
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
