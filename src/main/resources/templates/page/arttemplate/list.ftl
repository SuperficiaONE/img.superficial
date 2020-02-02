<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>模板列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <#include  "/commonCss.ftl">
</head>
<body>

<#include  "/commonJS.ftl">

<script>
    get("/api/artTemplate/list?types=5",function (res) {
        if(res.state==1){
            addAndShowTemplate(res)
        }else {
            showError(res.msg)
        }
    })
</script>
</body>

</html>

</html>
