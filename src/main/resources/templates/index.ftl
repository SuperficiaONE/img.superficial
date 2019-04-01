<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>获取项目链接</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
    <style>

    </style>
</head>
<body>
<div style="text-align:center;margin-left: auto;margin-right: auto;margin-top: 30px;">
    <label style="height: 30px;font-size:20px; ">搜索链接：</label>
    <input name="searchText" style="height: 30px;width: 220px" placeholder="请输入要搜索的名称">
    <button class="layui-btn" id="search">搜索</button>
    <select name="isBack" style="height: 30px;">
        <option value="1">后台链接</option>
        <option value="0">非后台链接</option>
    </select>
    <select name="needLogin" style="height: 30px;">
        <option value="1">需要登录</option>
        <option value="0">不需要登录</option>
    </select>
</div>


<div>
    <table id="dataTable" class="layui-table ">
    </table>
</div>


<script src="/static/layui/layui.js" charset="utf-8"></script>
<script src="/static/jquery/jquery-1.9.1.min.js" charset="utf-8"></script>

<script src="/static/jquery/base.js" charset="utf-8"></script>

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
            , height: h*0.6
            , width: w*0.6
            , url: '/webapi/menu/search' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'id', title: 'ID', width: 80, sort: false, fixed: 'left', style: "margin-left:100px"}
                , {field: 'menuName', title: '菜单名称', width: 100}
                , {field: 'url', title: '链接', width: 200, sort: false}
                , {field: 'login', title: '需要登录', width: 100 ,templet: function (d) {
                        if(d.needLogin == 1){
                            return "是"
                        }else {
                            return "不是"
                        }
                    }}
                , {field: 'back', title: '后台接口', width: 100 , templet: function (d) {
                        if(d.isBack == 1){
                            return "是"
                        }else {
                            return "不是"
                        }
                    }}
                , {field: 'order', title: '排序', width: 60, sort: false}
                , {field: 'createAt', title: '创建时间', width: 140, sort: false}
                , {field: 'updateAt', title: '更新时间', width: 140}
                , {
                    field: 'op', title: '操作', width: 135, templet: function (d) {
                        return " <a href=" + d.url + " class=\"layui-table-link\" target='_blank'>" + d.menuName + "</a>"
                    }, sort: false
                }
            ]]
            , parseData: function (res) { //res 即为原始返回的数据
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.count, //解析数据长度
                    "data": res.data //解析数据列表
                };
            }
            , done: function () {
                console.log("渲染完成")
                $(".layui-table-view").css("text-align", "center");
                $(".layui-table-view").css("margin-left", "auto")
                $(".layui-table-view").css("margin-right", "auto");

            }
        }
        $("#search").click(function () {
            var searchText = $("input[name='searchText']").val()
            var isBack = $("select[name='isBack']").val()
            var needLogin = $("select[name='needLogin']").val()
            var option = {};
            var where = {};
            where["searchText"] = searchText;
            where["isBack"] = isBack;
            where["needLogin"] = needLogin;
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