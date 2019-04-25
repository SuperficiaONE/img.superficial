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
<div style="width: 100;height: 200px;"></div>
<table id="table" style="width: 80%;"></table>
<#include  "/commonJS.ftl">

<script>
    layui.use(['layer'],function () {
        var layer = layui.layer;
       initArtTemplate("/api/templateScript/list?types=3,4");
        var bodyUrl ="/api/tbArtTemplate/list?templateId=tableBody";
        var headerUrl = "/webapi/tb/tableHeaderVoList?type=2&templateId=tableHeader&elementId=table"
       initTable("table",headerUrl,bodyUrl,true,1,10,"120%",300)
    })
</script>

 </body>

</html>

</html>
