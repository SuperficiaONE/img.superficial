<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>获取项目链接</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/layui/css/layui.css"  media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>

  <div style="text-align:center;margin-left: auto;margin-right: auto;margin-top: 30px;">
      <label class="">添加链接</label>
      <input name="menuName" />
      <input name="url">
      <select style="height: 30px;" name="isBack">
          <option value="0">非后台链接</option>
          <option value="1">后台链接</option>
      </select>
      <select style="height: 30px;" name="needLogin">
          <option value="0">不需要登录</option>
          <option value="1">需要登录</option>
      </select>
  </div>

<script src="/static/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>

    layui.use(['form','jquery'], function(){
        var $ = layui.jquery;

    })
</script>

</body>
</html>

</html>
