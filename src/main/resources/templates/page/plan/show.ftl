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
    <div class="layui-tab">
        <ul class="layui-tab-title" style="width: 60%">
            <% for(var i =0;i<titles.length;i++){%>
               <% if(i==0){ %>
            <li  id="now" class="layui-this"><%=titles[i]%></li>
             <%}else{%>
            <li  id ="tab_<%=titles[i]%>" ><%=titles[i]%>  年度计划</li>
            <%}%>
            <% }%>
        </ul>
        <div class="layui-tab-content">

            <% for(var i =0;i<titles.length;i++){%>
            <% if(i==0){ %>
            <div class="layui-tab-item layui-show">
                <ul id="now_div" class="layui-timeline">
                </ul>
            </div>
            <%}else{%>
            <div class="layui-tab-item ">
                <ul id="now_div" class="layui-timeline">
                    <%=titles[i]%>
                </ul>
            </div>
            <%}%>
            <% }%>
        </div>
    </div>


</script>
<script>
    getAsync("/api/plan/showVo",true,function (res) {
        if(res.state==1){
            $("body").append(template("planTab", res.data))
        }else {
            showError(res.msg);
        }
    })
    layui.use(['element', 'flow'], function () {
        var element = layui.element
        var flow = layui.flow;
        flow.load({
            elem: '#now'+"_div"
            , done: function (page, next) {
                var lis = [];
                $.get('/api/plan/showList?page=' + page, function (res) {
                    layui.each(res.data.list, function(index, item){
                        lis.push(template("cc_tab_content_div_list_li",item));
                    });
                    next(lis.join(''),page<res.pages)
                });
            }
        })
    })
</script>
<script type="text/html" id="cc_tab">
    <div class="layui-tab">
        {{include "cc_tab_title" $data}}
    </div>
</script>
<script type="text/html" id="cc_tab_title">
    <ul class="layui-tab-title">
        <% for(var i=0;i
        <titles.length
                ;i++){ %>
            <% if(i==0){%>
            <li class="layui-this"><%=titles[i]%></li>
            <%}else{%>
            <li><%=titles[i]%></li>
            <%}%>
            <%}%>
    </ul>
    <div class="layui-tab-content">
        <% for(var i=0;i
        <contents.length
                ;i++){ %>
            <% if(i==0){%>
            <div class="layui-tab-item layui-show">
                <ul id="demo" class="layui-timeline">
                    {{include "cc_tab_content_div_list_li" $contents[i]}}
                </ul>
            </div>
            <%}else{%>
            <div class="layui-tab-item">
                <ul id="demo" class="layui-timeline">
                    {{include "cc_tab_content_div_list_li" $contents[i]}}
                </ul>
            </div>
            <%}%>
            <%}%>
    </div>
</script>

<script type="text/html" id="cc_tab_content_div_list_li">
    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
        <div class="layui-timeline-content layui-text">
            <a style="color: #01AAED;" title="查看详细" target="_blank" href="http://www.baidu.com">
                <h3 class="layui-timeline-title">创建时间：{{createAt}} 计划名称:{{planName}} 开始时间：{{planStartTime}} 是否延时：<span class="layui-icon layui-icon-face-smile-fine
" style="color: orange;"/> /<span class="layui-icon layui-icon-face-cry
" style="color: orange;"/> 延时原因：xxx </h3>
                <h3>预计完成时间：xxx</h3>
            </a>

            <div class="layui-progress layui-progress" lay-showPercent="true" style="width: 60%; ">
                <div class="layui-progress-bar layui-bg-cyan" lay-percent="80%"></div>
            </div>
           {{@planContent}}
        </div>
    </li>
</script>
</body>
</html>

</html>
