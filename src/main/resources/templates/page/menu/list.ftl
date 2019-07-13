<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>获取项目链接</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <#include  "/commonCss.ftl">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<div style="text-align:center;margin-left: auto;margin-right: auto;margin-top: 30px;">
    <label style="height: 30px;font-size:20px; ">搜索链接：</label>
    <input name="searchText" style="height: 30px;width: 220px" placeholder="请输入要搜索的名称">
    <button class="layui-btn" id="search">搜索</button>
    <select name="menuBack" style="height: 30px;">
        <option value="">所有</option>
        <option value="2">非后台链接</option>
        <option value="1">后台链接</option>
    </select>
    <select name="menuLogin" style="height: 30px;">
        <option value="">所有</option>
        <option value="2">不需要登录</option>
        <option value="1">需要登录</option>
    </select>
</div>


<div>
    <table id="dataTable" class="layui-table ">
    </table>
</div>
  <#include  "/commonJS.ftl">
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script type="text/html" id="titleTpl">
    <a href="url" class="layui-table-link">{{menuName}}</a>
</script>
<script>

    layui.use(['form', 'jquery', 'table'], function () {
        var $ = layui.jquery;
        var table = layui.table;
        var w =  document.documentElement.clientWidth;
        var h =  document.documentElement.clientHeight;

        var options = {
            elem: '#dataTable'
            ,height: h*0.6
            , width: w
            , url: '/webapi/menu/search' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'menuId', title: 'ID', sort: false, fixed: 'left'}
                , {field: 'menuName', title: '菜单名称', templet: function (d) {
                        return " <a href=" + d.url + " class=\"layui-table-link\" target='_blank'>" + d.menuName + "</a>"
                    }}
                , {field: 'menuType', title: '类型'}
                , {field: 'url', title: '链接', sort: false}
                , {field: 'menuLogin', title: '需要登录', templet: function (d) {
                        if(d.menuLogin == 1){
                            return "是"
                        }else {
                            return "不是"
                        }
                    }}
                , {field: 'back', title: '后台接口', templet: function (d) {
                        if(d.menuBack == 1){
                            return "是"
                        }else {
                            return "不是"
                        }
                    }}
                , {field: 'menuOrder', title: '排序', sort: false}
                , {field: 'createAt', title: '创建时间',  sort: false}
                , {field: 'updateAt', title: '更新时间',}
                , {
                    field: 'op', title: '操作',  templet: function (d) {
                        return " <a href=" + d.url + " class=\"layui-table-link\" target='_blank'>" + "访问" + "</a>"
                    }, sort: false
                }
            ]]
            ,limit:10
            ,limits:[5,10,20,50,100]
            , parseData: function (res) { //res 即为原始返回的数据
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.count, //解析数据长度
                    "data": res.data //解析数据列表
                };
            }
            , done: function () {
                $("div[lay-id='dataTable']").css("text-align", "center");
                $("div[lay-id='dataTable']").css("margin-left", "auto")
                $("div[lay-id='dataTable']").css("margin-right", "auto");
                $("div[lay-id='dataTable'] th").css("background-color","#5FB878")
                $("div[lay-id='dataTable'] th").css("text-align","center")

            }
        }
        $("#search").click(function () {
            var searchText = $("input[name='searchText']").val()
            var isBack = $("select[name='menuBack']").val()
            var needLogin = $("select[name='menuLogin']").val()
            var option = {};
            var where = {};
            where["searchText"] = searchText;
            where["menuBack"] = isBack;
            where["menuLogin"] = needLogin;
            option["where"] = where;
            addWaiting();
            setTimeout(function () {
                removeWaiting();
                table.reload("dataTable", option)
            }, 1500)

        })
        table.render(options);
    })
</script>

</body>
</html>

</html>