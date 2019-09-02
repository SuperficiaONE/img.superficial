<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <#include  "/commonCss.ftl">
    <#include  "/mdCSS.ftl">
</head>
<body>


  <#include  "/commonJS.ftl">
 <#include  "/mdJS.ftl">
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    get("/api/artTemplate/list?types=8",function (res) {
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
