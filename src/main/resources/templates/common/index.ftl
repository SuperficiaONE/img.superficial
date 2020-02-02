<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>首页</title>
  <#include  "/commonCss.ftl">

</head>
<body>

</body>
  <#include  "/commonJS.ftl">
<script>
    get("/api/artTemplate/list?types=17", function (res) {
        if (res.state == 1) {
            addAndShowTemplate(res)
        } else {
            showError(res.msg)
        }
    })
</script>
</html>