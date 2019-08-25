<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>字典列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
     <#include  "/commonCss.ftl">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
    <style>

    </style>
</head>
<body>
<div style='margin-right: auto;margin-left: auto;text-align: center; width: 100%;'>
    <div style="margin-top: 100px;"></div>
    <label style="height: 30px;font-size:20px; \">搜索字典：</label>
    <input name="searchText" style="height: 30px;width: 220px" placeholder="请输入要搜索字典类型">
   <button class="layui-btn" id="search">搜索</button>
</div>
<div style="text-align: center;width: 100%">
    <table  lay-filter="test" id='dictTable' style='display: inline-block; margin-left: auto;margin-right: auto; '>

    </table>
</div>



  <#include  "/commonJS.ftl">

<script>
    function showTable() {
        layui.use(['table'],function () {
            var table = layui.table ;

            var option = {
                elem: '#dictTable'
                , height: 300

                ,limit: 5
                ,limits: [5,10,20,50]
                , url: "/api/dict/getDictList?searchText="+$("input[name='searchText']").val()//数据接口
                , page: true //开启分页
                , cols: [[ //表头
                    /*templateType
                templateName
                isCreate*/
                    {field: '', title: '操作',  fixed: 'left',templet:function (d) {
                            return "<a class='layui-table-link' href='/page/dict/edit.htm?dictId="+d.dictId+"' target='_blank'>编辑</a>"+"<a class='layui-table-link' style='margin-left: 10px;' href='/api/dict/delete?dictId="+d.dictId+"'>删除</a>"
                        }}
                    ,{field: 'dictId', title: '字典id', fixed: 'left'}
                    ,{field: 'dictValue', title: '含义'}
                    ,{field: 'dictType', title: '字典类型'}
                    ,{field: 'dictKey', title: '字典值'}
                    ,{field: 'createAt', title: '创建时间'}
                    ,{field: 'updateAt', title: '更新时间'}
                    ,{field: 'createUser', title: '创建人'}
                    ,{field: 'updateUser', title: '更新人'}

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
                    $("div[lay-id='dictTable']").css("text-align", "center");
                    $("div[lay-id='dictTable']").css("margin-left", "auto")
                    $("div[lay-id='dictTable']").css("margin-right", "auto");
                    $("div[lay-id='dictTable'] th").css("background-color", "#5FB878")
                    $("div[lay-id='dictTable'] th").css("text-align", "center")

                }
            }
            table.render(option)
            table.on('row(test)', function(obj){
            });
        })

    }
    layui.use(["layer"],function () {
        showTable();
        $("#search").click(function () {
            showTable();
        })
    })
</script>

</body>
</html>

</html>