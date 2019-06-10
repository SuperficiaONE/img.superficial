<html>
<head>
    <title>后台</title>
 <#include  "/commonCss.ftl">
<#include  "/commonJS.ftl">
</head>
<body>

</body>
<script type="application/javascript" >
    function  showLay(content,commitFnc) {
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.open({
                title:"编辑",
                content:content,
                btn: ['取消', '提交'],
                yes: function(index, layero){
                  layer.close(index)
                }
                ,btn2: function(index, layero){
                    commitFnc()
                }
            });

        });

    }
    function  edite(moduleConfigId) {
        get("/api/moduleConfig/getSiteModuleConfigVo?moduleConfigId="+moduleConfigId,function (res) {
            var content = res.moduleJson;
            showLay(content,function () {
                alert(content)
            })
        })

    }

    function  del(moduleConfigId) {
        alert("delete"+moduleConfigId)

    }
</script>
<script type="text/html" id="templateBtn">
<% for(var i=0;i<data.length;i++){%>
<button id="addModule" type="<%=data[i].type%>" title=""><%=data[i].title%></button>
<%}%>
<br>
<hr>
<button id="add" >新增模板</button>
    <div id="t"></div>
</script>
<script type="text/html" id="templateTable">
  <table>
      <thead>
          <th>操作</th>
          <th>预览图片</th>
          <th>排序</th>
          <th>模块名称</th>
          <th>楼层名称</th>
          <th>模板名称</th>
      </thead>
      <tbody>


      <% for(var i=0;i<data.length;i++){%>

      <tr>
          <td>
              <button id="edite"  onclick="edite('<%=data[i].moduleConfigId%>')">编辑</button>
              <%if(data[i].type==4||data[i].type==4){%>
              <button id="delete" onclick="del('<%=data[i].moduleConfigId%>')"  >删除</button>
              <%}%>
          </td>
          <td><img style="width:100px;height: 60px;" src="<%=data[i].modulePreviewImg%>"/></td>
          <td><div class="double_click" data="<%=data[i].moduleConfigId%>" name="moduleOrder"><%=data[i].moduleOrder%></div></td>
          <td><%=data[i].typeName%></td>
          <td><%=data[i].moduleName%></td>
          <td><%=data[i].moduleTitle%></td>
      </tr>
      <%}%>
      </tbody>
  </table>
</script>
<script>

    //0.顶部广告模板 1.商品模板 2.图片楼层 3.首页翅膀 4.轮播图 5.导航栏
    $(function () {
        var d = [{type:0,title:"添加顶部广告模板"},{type:1,title:"添加商品模板"},{type:2,title:"添加图片楼层"},{type:3,title:"添加首页翅膀"},{type:4,title:"添加轮播图"},{type:5,title:"添加导航栏"}]
        $("body").append(template("templateBtn",{data:d}))
        // 获取模块数据
        getAsync("/api/moduleConfig/getBackModuleList?siteId=1",false,function (d) {
            $("#t").append(template("templateTable",{'data':d}))
            $("body").on('click','div',function(){
                $(".double_click").click(function () {
                    var dd = $(this).attr("data");
                    var name =$(this).attr("name");
                    var val= $(this).html();
                    var className =$(this).attr("class");
                    var s = "<input  name=\""+name+"\"  data='"+dd+"' value='"+val+"'></input>"
                    $(this).replaceWith(s);
                    $("input[name='"+name+"']").blur(function () {
                        var  val = $(this).val();
                        if(val!=""){
                            $(this).replaceWith("<div class=\""+className+"\" data=\""+dd+"\" name=\""+name+"\">"+val+"</div>");
                        }else {
                            showError("输入的内容不能为空")
                        }
                    })
                })

            })

        })

    })


</script>
</html>