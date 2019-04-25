<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>获取项目链接</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <#include  "/commonCss.ftl">

    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
    <style>

    </style>
</head>
<body>
<div style="text-align:center;margin-left: auto;margin-right: auto;margin-top: 30px;">
    <label style="height: 30px;font-size:20px; ">搜索链接：</label>
    <input name="searchText" style="height: 30px;width: 220px" placeholder="请输入要搜索的名称">
    <button class="layui-btn" id="search">搜索</button>
    <div class="layui-form-item" style="width:400px;text-align: center;margin-top: 20px; margin-left: auto;margin-right: auto" id="menuBack">
    </div>
    <div class="layui-form-item" style="width:400px;text-align: center;margin-left: auto;margin-right: auto" id="menuLogin">
    </div>
</div>


<div>
    <table id="dataTable" class="layui-table ">
    </table>
</div>
<#include  "/commonJS.ftl">


<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script type="text/html" id="titleTpl">
    <a href="url" class="layui-table-link">{{menuName}}</a>
</script>
<script>
    layui.use(['layer'],function () {
        var layer = layui.layer;
        initArtTemplate("/api/templateScript/list?types=3,4");
        var bodyUrl ="/webapi/menu/search?templateId=tableBody";
        var headerUrl = "/webapi/tb/tableHeaderVoList?type=1&templateId=tableHeader&elementId=table"
        initTable("dataTable",headerUrl,bodyUrl,true,1,10,"120%",300)
    })
</script>

</body>
</html>

</html>