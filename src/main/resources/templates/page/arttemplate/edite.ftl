<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>保存模板</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <#include  "/commonCss.ftl">
    <#include  "/codeMirrorCSS.ftl">
</head>
<body>
<form class="layui-form" onsubmit="return false;">
    <div style="margin-left: auto;margin-right: auto;margin-top: 30px; width: 55%; border-radius: 20px;background-color: rgba(100,100,100,0.2);padding-bottom: 20px">
        <h2 style="text-align: center;margin-bottom: 20px;padding-top: 10px;">保存模板</h2>
        <div class="layui-form-item" >
            <label class="layui-form-label">模板类型</label>
            <div class="layui-input-block" >
                <button class="layui-btn" style="text-align: center;"  data="${vo.templateType!}" id="templateType">${dict.dictValue!}</button>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">before脚本</label>
            <div class="layui-input-block" >
                <textarea  style="height: 100px;width: 400px" id="beforeScript" name="beforeScript">${vo.beforeScript!}</textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">模板元素</label>
            <div class="layui-input-block" >
                <textarea  style="height: 100px;width: 400px" id="templateElements" name="templateElements">${vo.templateElements!}</textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">模板脚本</label>
            <div class="layui-input-block" >
                <textarea  style="height: 100px;width: 400px" id="templateScript" name="templateScript">${vo.templateScript!}</textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">after脚本</label>
            <div class="layui-input-block" >
                <textarea  style="height: 100px;width: 400px" id="afterScript" name="afterScript">${vo.afterScript!}</textarea>
            </div>
        </div>
        <div class="layui-form-item " >
            <label class="layui-form-label">数据结构</label>
            <div class="layui-input-block" >
                <textarea  style="height: 100px;width: 400px" id="templateData" name="templateData">${vo.templateData!}</textarea>
            </div>
        </div>

        <div class="layui-form-item" style="padding-bottom: 20px;">
            <div class="layui-input-block">
                <button id="submit" class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button id="reset" class="layui-btn layui-btn-primary" >重置</button>
            </div>
        </div>
    </div>
</form>

  <#include  "/commonJS.ftl">
  <#include  "/codeMirrorJS.ftl">
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>


    var templateScriptEditor = initEditor("templateScript")
    var afterScriptEditor =  initEditor("afterScript")
    var beforeScriptEditor = initEditor("beforeScript")
    var templateElementsEditor = initEditor("templateElements")
    var templateDataEditor = initEditor("templateData")



    function  initEditor(idName) {
        var editor = CodeMirror.fromTextArea(document.getElementById(idName), {
            lineNumbers: true,     // 显示行数
            indentUnit: 4,         // 缩进单位为4
            styleActiveLine: true, // 当前行背景高亮
            matchBrackets: true,   // 括号匹配
            mode: 'htmlmixed',     // HMTL混合模式
            lineWrapping: true,    // 自动换行
            theme: 'monokai'
        });
        editor.setOption("extraKeys", {
            // Tab键换成4个空格
            Tab: function(cm) {
                var spaces = Array(cm.getOption("indentUnit") + 1).join(" ");
                cm.replaceSelection(spaces);
            },
            // F11键切换全屏
            "F1": function(cm) {
                cm.setOption("fullScreen", !cm.getOption("fullScreen"));
            },
            // Esc键退出全屏
            "Esc": function(cm) {
                if (cm.getOption("fullScreen")) cm.setOption("fullScreen", false);
            }
        });
        editor.setSize('auto','200px');
        return editor;
    }

    function renderAtrTemplate(res){
        if(res.data!=undefined&&res.data.length>0){
           templateScriptEditor.setValue(res.data[0]['templateScript']==undefined?"":res.data[0]['templateScript']);
           beforeScriptEditor.setValue(res.data[0]['beforeScript']==undefined?"":res.data[0]['beforeScript']);
           afterScriptEditor.setValue(res.data[0]['afterScript']==undefined?"":res.data[0]['afterScript']);
           templateDataEditor.setValue(res.data[0]['templateData']==undefined?"":res.data[0]['templateData']);
           templateElementsEditor.setValue(res.data[0]['templateElements']==undefined?"":res.data[0]['templateElements']);
        }else {
            templateScriptEditor.setValue("");
            beforeScriptEditor.setValue("");
            afterScriptEditor.setValue("");
            templateDataEditor.setValue("");
            templateElementsEditor.setValue("");
        }
    }
    function  changeType(type) {
        get("/api/artTemplate/list?types="+type,function (res) {
            if(res.state==1){
                renderAtrTemplate(res)
            }else {
                showError(res.msg)
            }
        })
        $("#templateType").attr("data",type);
    }

    layui.use(['jquery','form','layer'],function () {
        var layer = layui.layer;
        var form = layui.form;

        $("#submit").click(function () {
            var templateType = $("#templateTyp").attr('data');
            var templateScript = templateScriptEditor.getValue()
            var beforeScript = beforeScriptEditor.getValue()
            var afterScript = afterScriptEditor.getValue()
            var templateData = templateDataEditor.getValue()
            var templateElements = templateElementsEditor.getValue()
            var data = {};
            data['templateType'] = templateType;
            data['templateScript'] = templateScript;
            data['afterScript'] = afterScript;
            data['beforeScript'] = beforeScript;
            data['templateData'] = templateData;
            data['templateElements'] = templateElements;
            post("/api/tbArtTemplate/save",data,function (res) {
                if(res.state == 1){
                    layer.msg(res.msg)
                }else {
                    layer.open({
                        title: '提示'
                        , content: res.msg
                    });
                }
            })
        })
    })
</script>

</body>
</html>

</html>
