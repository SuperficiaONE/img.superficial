<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加模板</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <#include  "/commonCss.ftl">
</head>

<body>
<div style="width: 100;height: 200px;"></div>
<table id="table"></table>
<#include  "/commonJS.ftl">

<script>
    layui.use(['layer'],function () {
        var layer = layui.layer;
        initArtTemplate("/api/templateScript/list?types=2,1");
        var url ="/api/tbArtTemplate/list?";
        post("/webapi/tb/tableHeaderVoList?type=2&templateId=tableHeader&elementId=table&openPage=true",{'url':url},function (res) {
            if(res.state == 1){
                if(res.data != undefined){
                    var id=res.data.id;
                     replaceOldHtml(res.data)
                    // 判断page
                    var bodyData = {};
                     bodyData.openPage = res.data.page;
                     bodyData.url = res.data.url;
                     bodyData.pageSize = 10;
                     bodyData.curr = 1;
                     bodyData.elementId= res.data.id
                     bodyData.templateId='tableBody';
                     bodyData.list = res.data.templateVO.data;
                    template.defaults.imports.handler = function(data) {
                        if(data.url == undefined){
                            return {'url':'http://www.baidu.com','title':data}
                        }else {
                            return data;
                        }
                    }
                     renderBody(bodyData)
                }else {
                    $("#table").html("<h3 style='color:lightslategrey;text-align: center;'>无数据</h3>")
                }
            }else {
                layer.msg(res.msg);
            }
        })
    })
</script>
<#--   {id:xxx , data:xxx}-->
<script type="text/html" id="tableBody">
     <div class="{{id}}_div" style="overflow: auto;height: 40px;">
         <table id="{{id}}_body " class="layui-table {{id}}_slaver_width">
             <tbody>
             {{each data}}
                   {{include "tableTr" $value}}
             {{/each}}
             </tbody>

         </table>
     </div>
</script>
 <#--  id :
      fieldList :xxx
      data:xxx
 -->
<script type="text/html" id="tableTr">
    <tr class="<%=data.parentId%>">
        <% for(var i = 0 ; i < fieldList.length ; i ++){%>
        <% var field = fieldList[i].field %>
        <% var w = fieldList[i].w %>
        <td class="<%=field%>_td {{field}}_slaver_width" style="width: <%= w %>px;display: inline-block; " >   <div class="showAll <%=field%>_td" style="box-sizing: border-box;
    text-overflow: ellipsis;
    overflow: hidden;
   white-space: nowrap;margin:0;padding: 0;">
            <% var c={"title":data[field]} %>
          {{include "menuName"  $imports.handler(data[field], handler) }}
        <div> </td>
        <%};%>
    </tr>

</script>
<script type="text/html" id="test">
    <% if(url!= undefined){%>
      <a href="{{url}}" style="color: #01AAED;" target="_blank">{{title}}</a>
   <%}else{%>
    {{title}}
   <%}%>
</script>
 </body>

</html>

</html>
