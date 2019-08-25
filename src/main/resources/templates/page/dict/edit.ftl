<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加字典</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <#include  "/commonCss.ftl">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<form class="layui-form" onsubmit="return false;">
    <div style="margin-left: auto;margin-right: auto;margin-top: 30px; width: 35%; border-radius: 20px;background-color: rgba(100,100,100,0.2);padding-bottom: 20px">
        <h2 style="text-align: center;margin-bottom: 20px;padding-top: 10px;">更新字典</h2>
        <input hidden="hidden" value="${d.dictId!}" name="dictId"/>
        <div class="layui-form-item">
            <label class="layui-form-label">字典类型</label>
            <div class="layui-input-block">
                <input disabled="disabled" class="layui-btn" style="text-align: center;" value="${d.dictType!}"
                       id="dictType"></input>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">字典的值</label>
            <div class="layui-input-block">
                <input class="layui-btn" disabled="disabled" style="text-align: center;" value="${d.dictKey!}"
                       id="dictType"></input>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">添加的value</label>
            <div class="layui-input-inline">
                <input class="layui-input" style="height: 30px;" name="dictValue" value="${d.dictValue!}"/>
            </div>
        </div>

            <#if d.dictType??&& d.dictType =="dict_type" >
     <div class="layui-form-item chineseText">
      <label class="layui-form-label">添加的含义</label>
         <div class="layui-input-inline">
            <input class="layui-input" style="height: 30px;" name="chineseText" value="${d.chineseText}"/>
          </div>
        </div>
            </#if>

        <div class="layui-form-item" style="padding-bottom: 20px;">
            <div class="layui-input-block">
                <button id="submit" class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary" style="display: none"
                        onclick="window.location.reload()">重置
                </button>
            </div>
        </div>
    </div>
</form>

  <#include  "/commonJS.ftl">
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>

</script>

</body>
</html>

</html>
