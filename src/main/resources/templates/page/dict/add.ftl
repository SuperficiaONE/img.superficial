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
    <div style="margin-left: auto;margin-right: auto;margin-top: 30px; width: 35%; border-radius: 20px;background-color: rgba(100,100,100,0.2);padding-bottom: 20px">
        <h2 style="text-align: center;margin-bottom: 20px;padding-top: 10px;">添加字典</h2>
        <div class="layui-form-item">
            <label class="layui-form-label">字典类型</label>
            <div class="layui-input-block">
                <button class="layui-btn" style="text-align: center;" data="" id="dictType">请选择类型</button>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">添加的value</label>
            <div class="layui-input-inline">
                <input class="layui-input" style="height: 30px;" name="dictValue"/>
            </div>
        </div>
        <div class="layui-form-item chineseText">
            <label class="layui-form-label">添加的含义</label>
            <div class="layui-input-inline">
                <input class="layui-input" style="height: 30px;" name="chineseText"/>
            </div>
        </div>
        <div class="layui-form-item" style="padding-bottom: 20px;">
            <div class="layui-input-block">
                <button id="submit" class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary" style="display: none"
                        onclick="window.location.reload()">重置
                </button>
            </div>
        </div>
    </div>
</form>

  <#include  "/commonJS.ftl">
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    var index;

    function changeDataKeyShow() {
        var dictType = $("#dictType").attr("data");
        if (dictType == undefined || dictType == "") {
            $("input[name='dictValue']").val("dict_type")
            $("input[name='chineseText']").val("字典类型")
            $("input[name='chineseText']").attr("disabled", "true")
            $("input[name='dictValue']").attr("disabled", "true")
            $("button[type='reset']").hide()
        } else {
            $("input[name='dictValue']").val("")
            $("input[name='dictValue']").removeAttr("disabled")
            $("input[name='chineseText']").removeAttr("disabled")
            $("button[type='reset']").show()
        }
        if (dictType == undefined || dictType == "" || dictType == "dict_type") {
            $(".chineseText").show()
        } else {
            $(".chineseText").hide()
        }

    }

    function showDictTable() {
        layui.use(['table', "layer"], function () {
                    var table = layui.table;
                    var option = {
                        elem: '#dictTable'
                        ,
                        height: 300
                        ,
                        width: 600
                        ,
                        limit: 5
                        ,
                        limits: [5, 10, 20, 50]
                        ,
                        url: "/api/dict/getAddDictList?searchText=" + $("input[name='searchText']").val()//数据接口
                        ,
                        page: true //开启分页
                        ,
                        cols: [[ //表头
                            {field: 'dictId', title: '字典id', width: 200, fixed: 'left'}
                            , {field: 'dictType', title: '字典类型', width: 200}
                            , {field: 'dictValue', title: '含义', width: 200}
                            , {field: 'dictCount', title: '创建数量', width: 200}
                        ]]
                        ,
                        parseData: function (res) { //res 即为原始返回的数据
                            return {
                                "code": res.code, //解析接口状态
                                "msg": res.message, //解析提示文本
                                "count": res.count, //解析数据长度
                                "data": res.data //解析数据列表
                            };
                        }
                        ,
                        done: function () {
                            $("div[lay-id='dictTable']").css("text-align", "center");
                            $("div[lay-id='dictTable']").css("margin-left", "auto")
                            $("div[lay-id='dictTable']").css("margin-right", "auto");
                            $("div[lay-id='dictTable'] th").css("background-color", "#5FB878")
                            $("div[lay-id='dictTable'] th").css("text-align", "center")

                        }
                    }
                    table.render(option)
                    table.on('row(test)', function (obj) {
                        $("#dictType ").attr("data",obj.data.dictType);
                        $("#dictType ").text(obj.data.dictValue);
                        changeDataKeyShow();
                        layer.close(index)
                    });
                    $("#search").click(function () {
                        showDictTable();
                    })
                }
        )
    }

    /**
     * 显示dictKey 为0的字典
     */
    function showDictLayer() {
        layui.use(['table', "layer"], function () {
            var layer = layui.layer;
            index = layer.open({
                title: '选择需要添加的模板',
                content: "<div style='width: 100%;text-align: center'> <label style=\"height: 30px;font-size:20px; \">搜索字典：</label>\n" +
                        "    <input name=\"searchText\" style=\"height: 30px;width: 220px\" placeholder=\"请输入要搜索字典类型\">\n"
                        +
                        "    <button class=\"layui-btn\" id=\"search\">搜索</button></div>  <table  lay-filter=\"test\" id='dictTable' style='display: inline-block; margin-left: auto;margin-right: auto; '></table>"
                , offset: '100px'
                , area: "700px"
            });
            //展示选择列表
            showDictTable();
        })

    }




    layui.use(['layer', 'form', 'jquery'], function () {

        var w = document.documentElement.clientWidth;
        var h = document.documentElement.clientHeight;
        var form = layui.form;
        var layer = layui.layer;
        $("#dictType").click(function () {
          showDictLayer();
        })

        $("#submit").click(function () {
            var dictType = $("#dictType ").attr("data");
            var dictValue = $("input[name='dictValue']").val()
            if (dictType == undefined) {
                dictType = ""
            }
            var formData = {};
            formData['dictType'] = dictType;
            formData['dictValue'] = dictValue;
            if (dictType == undefined || dictType == "" || dictType == "dict_type") {
                var chineseText = $("input[name='chineseText']").val()
                formData['chineseText'] = chineseText;
            }
            post("/api/dict/add", formData, function (res) {
                if (res.state == 1) {
                    var dictType = $(" select[name='dictType'] ").val();
                    if (dictType == undefined || dictType == "" || dictType == "dict_type") {

                    }
                    layer.msg(res.msg)
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
