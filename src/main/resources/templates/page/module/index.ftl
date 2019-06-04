<html>
<head>
    <title>前端展示</title>
 <#include  "/commonCss.ftl">
<#include  "/commonJS.ftl">
</head>
<body>


<script>

    $(function () {
        getAsync("/api/module/list",false,function (data) {
            for (var i = 0; i < data.length; i++) {
               $("body").append(data[i].moduleJson)
                renderCarousel(undefined)
            }
        })
    })
</script>
</body>


</html>