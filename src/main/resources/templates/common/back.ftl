<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>后台设置首页布局</title>
    <style>
        .choose {
            background-color: #00A7AA;
            opacity: 0.2;
        }

        .border-style {
            border: 1px solid grey;
        }

        .choose2 {
            border: 2px solid #5bb8ff;
        }
    </style>
  <#include  "/commonCss.ftl">

</head>
<body>
<div id="layout">
</div>

</body>

<script type="text/html" id="select-module">
    <div id="select-module" style="{{$data}}">
        <div>
            <form class="layui-form" action="">
                <div class="layui-form-item">
                    <label class="layui-form-label">选中样式</label>
                    <div class="layui-input-block">
                        <select lay-filter="s" style="width: 200px;" name="select-css" lay-verify="required">
                            <option value="0">背景色</option>
                            <option value="1">边框</option>
                        </select>
                    </div>
                </div>
            </form>

        </div>
        <div id="select-list">

            <form class="layui-form" onsubmit="return false;"  disabled="true">
                <div class="layui-form-item" style="margin-bottom: 0px;float: right">
                   <button class="layui-btn">添加</button>
                    <button class="layui-btn">取消</button>
                </div>
            </form>
        </div>

    </div>
</script>

  <#include  "/commonJS.ftl">
<script>

    get("/api/artTemplate/list?types=17", function (res) {
        if (res.state == 1) {
            addAndShowTemplate(res, 'layout')
        } else {
            showError(res.msg)
        }
    })
    var select_flag = 0;

    function showSelectModule() {
        layui.use(['layer'], function () {
            if (select_flag == 1) {
                return;
            }
            var t = {};
            select_flag = 1;
            t['templateId'] = 'select-module';
            t['data'] = {};
            var areaW = '500px';
            var areaH = '400px';
            var h = getTemplateHtml(t);
            var layer = layui.layer;
            layer.open({
                type: 1,
                shade: 0,
                title: 'hello world',
                area: [areaW, areaH],
                content: h,
                yes: function () {
                    cancel();
                }, cancel: function () {
                    select_flag = 0;
                    cancel();
                }, success: function (layero, index) {
                    $('.layui-layer-content').css({'height': areaH})
                }
            });
            $("select[name='select-css']").val(css_use);
            renderForm();
        })
    }

    function cancel() {
        recover();
    }

    function renderAll() {

    }

    var css_use = 0;

    function changeChooseStyle() {
        css_use = $("select[name='select-css']").val();
        if (css_use == undefined) {
            css_use = 0;
        }
        if (css_use == '0') {
            $('.choose2').addClass('choose')
            $('.choose2').removeClass('choose2')
        } else if (css_use == '1') {
            $('.choose').addClass('choose2')
            $('.choose').removeClass('choose')
        }
    }

    function closeAll() {
        layui.use(['layer'], function () {
            var layer = layui.layer;
            layer.closeAll();
        })
    }

    function renderForm() {
        layui.use('form', function () {
            var form = layui.form;
            form.on('select(s)', function (data) {
                changeChooseStyle();
            })
            form.render();
        });
    }
</script>
</html>