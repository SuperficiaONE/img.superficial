<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <title>获取项目计划列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <#include  "/commonCss.ftl">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>


  <#include  "/commonJS.ftl">
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script type="text/html" id="planTab">
    <div class="layui-tab" >
        <ul class="layui-tab-title" style="width: 60%">
            <li class="layui-this">2019 年度计划表</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <ul class="layui-timeline">
                    <li class="layui-timeline-item">
                        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                        <div class="layui-timeline-content layui-text">
                           <a style="color: #01AAED;" title="查看详细" target="_blank" href="http://www.baidu.com">
                            <h3 class="layui-timeline-title">创建时间：8月21日  计划名称:xxxx  是否延时：<span class="layui-icon layui-icon-face-smile-fine
" style="color: orange;"/> /<span class="layui-icon layui-icon-face-cry
" style="color: orange;"/> 延时原因：xxx </h3>
                           </a>

                            <div class="layui-progress layui-progress" lay-showPercent="true" style="width: 60%; ">
                                 <div class="layui-progress-bar layui-bg-cyan" lay-percent="80%" ></div>
                            </div>
                            <p>xxxx
                                <br>
                                xxxx
                                <br>
                                xxxx
                            </p>
                        </div>
                    </li>

                    <li class="layui-timeline-item">
                        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                        <div class="layui-timeline-content layui-text">
                            <a style="color: #01AAED;" target="_blank" title="查看详细" href="http://www.baidu.com">
                                <h3 class="layui-timeline-title"> 创建时间：8月21日  计划名称:xxxx  已圆满完成  </h3>
                            </a>

                            <div class="layui-progress layui-progress" lay-showPercent="true" style="width: 60%; ">
                                <div class="layui-progress-bar layui-bg-cyan" lay-percent="100%" ></div>
                            </div>
                            <p>xxxx
                                <br>
                                xxxx
                                <br>
                                xxxx
                            </p>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>

</script>
<script>
    $("body").append(template("planTab",{}))
      layui.use(['element'],function () {
          var element = layui.element

      })
</script>

</body>
</html>

</html>
