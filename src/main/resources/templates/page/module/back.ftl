<html>
<head>
    <title>后台</title>
 <#include  "/commonCss.ftl">
<#include  "/commonJS.ftl">
</head>
<body>

</body>

<script type="text/html" id="templateBtn">
<% for(var i=0;i<data.length;i++){%>
<button id="addModule" type="<%=data[i].type%>" title=""><%=data[i].title%></button>
<%}%>
<br>
<hr>
<button id="add" >新增模板</button>
</script>
<script type="text/html" id="templateTable">
  <table>
      <thead>
          <th>操作</th>
          <th>排序</th>
          <th>模块名称</th>
          <th>楼层名称</th>
          <th>模板名称</th>
      </thead>
      <tbody>


      <% for(var i=0;i<data.length;i++){%>

      <tr>
          <td>
              <button id="edite" >编辑</button>
              <%if(data[i].type==4||data[i].type==4){%>
              <button id="delete"  >删除</button>
              <%}%>
          </td>
          <td><%=data[i].order%></td>
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
    })

    // 获取模块数据
    getAsync("/api/moduleConfig/list",false,function (data) {


    })
</script>
</html>