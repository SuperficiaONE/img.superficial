<html>
<head>
    <title>前端展示</title>
 <#include  "/commonCss.ftl">
<#include  "/commonJS.ftl">
</head>
<body>

</body>
<script>

    $(function () {
        getAsync("/api/moduleConfig/getSiteModule?siteId=1", false, function (data) {
            for (var i = 0; i < data.length; i++) {
                  $("body").append(data[i].moduleJson)
                eval("render" + data[i].moduleType + "(" + JSON.stringify(data[i].moduleConfigId) + ")")
                if (data[i].dataJson != undefined) {
                    var s = JSON.stringify("{\"id\":\""+data[i].moduleConfigId+"\",\"data\":" +data[i].dataJson+ "}")
                      eval("renderByData"+data[i].moduleType+"("+s+")")
                }
            }
        })
    })
</script>

</html>