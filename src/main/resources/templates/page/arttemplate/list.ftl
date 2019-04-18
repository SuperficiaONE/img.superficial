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
<table id="table"></table>
<#include  "/commonJS.ftl">

<script>
    layui.use(['layer'],function () {
        var layer = layui.layer;
        initArtTemplate("/api/templateScript/list?types=2");
        get("/webapi/tb/tableHeaderVoList?type=2",function (res) {
            if(res.state == 1){
                $("#table").replaceWith('<div id=\'table\'></div>')
                if(res.data != undefined){
                    $("#table").html(template("tableHeader",res))
                }else {
                    $("#table").html("<h3 style='color:lightslategrey;text-align: center;'>无数据</h3>")
                }
            }else {
                layer.msg(res.msg);
            }
        })
    })
</script>


 </body>

</html>

</html>
