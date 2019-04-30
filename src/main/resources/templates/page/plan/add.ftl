<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加计划</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
   <#include  "/commonCss.ftl">
   <#include  "/commonEditorCSS.ftl">


    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<form class="layui-form" style="" onsubmit="return false;">
    <div style="margin-left: auto;margin-right: auto;margin-top: 30px; width: 45%; border-radius: 20px;padding-bottom: 20px">
        <h2 style="text-align: center;margin-bottom: 20px;padding-top: 10px;">添加计划</h2>
        <div class="layui-form-item" style="">
            <label class="layui-form-label">计划名称：</label>
            <div class="layui-input-inline">
                <input id="planName" class="layui-input" style="height: 30px;" name="planName"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">计划开始时间：</label>
            <div class="layui-input-inline">
                <input id="planStartTime" class="layui-input" style="height: 30px;" name="planStartTime"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">计划结束时间：</label>
            <div class="layui-input-inline">
                <input id="planEndTime" class="layui-input" style="height: 30px;" name="planEndTime"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">计划内容</label>
            <div class="">
                <textarea id="planContent" style="width: 120%;" name="planContent"></textarea>
            </div>
        </div>
        <div class="layui-form-item" style="padding-bottom: 20px;">
            <div class="layui-input-block">
                <button id="submit" class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button id="reset" type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>
</form>
  <#include  "/commonJS.ftl">
 <#include  "/commonEdtitorJS.ftl">
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<#--$('#editor').froalaEditor("destroy")-->
<script>
    layui.use(['form', 'laydate', 'jquery', 'layedit'], function () {
        var layedit = layui.layedit;
        var laydate = layui.laydate;
        var $ = layui.jquery;
        laydate.render({
            elem: '#planStartTime' //指定元素
            ,type: 'datetime'
            ,zIndex: 99999999
        });
        laydate.render({
            elem: '#planEndTime' //指定元素
            ,type: 'datetime'
            ,zIndex: 99999999
        });
        $('#planContent').froalaEditor({
            imageUploadURL : "/api/img/upload",
        });
        $("#submit").click(function () {
            var html = $("#planContent").froalaEditor("html.get");
            console.log(html)
        })

        $("#reset").click(function () {
            $("#planContent").html("")
            $("#planStartTime").val("")
            $("#planEndTime").val("")
            $("#planName").val("")

        })
    })
</script>

</body>
</html>

</html>
