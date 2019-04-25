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
<table id="table" style="width: 80%;"></table>
<#include  "/commonJS.ftl">

<script>
    layui.use(['layer'],function () {
        var layer = layui.layer;
      //  initArtTemplate("/api/templateScript/list?types=2,1");
        var bodyUrl ="/api/tbArtTemplate/list?templateId=tableBody";
        var headerUrl = "/webapi/tb/tableHeaderVoList?type=2&templateId=tableHeader&elementId=table"
       initTable("table",headerUrl,bodyUrl,true,1,10,"120%",300)
    })
</script>

<script type="text/html" id="tableHeader">
      <div  id="{{id}}_table_header_div" class=" {{id}}_slaver_width " style="height:40px;" >
          <%if(data == undefined || data.length<=0){%>
              <div style="text-align: center;width: {{scrollWidth}};overflow: auto;">
                  服务器无法拉取到数据<span class=" layui-icon-loading  layui-anim layui-anim-rotate layui-anim-loop"></span>
              </div>
          <%}else{%>
          <table id="{{id}}_table_header" class=" layui-table" style="margin: 0;width: {{scrollWidth}};">
              <thead>
              {{each data}}
                {{include "tableTh" $value}}
              {{/each}}
              </thead>
          </table>
          <%}%>
      </div>
</script>

<script type="text/html" id="tableTh">
      <th class="{{field}}_master_width  {{field}}_th" style="background-color: rgb(95, 184, 120);text-align: center; color: white;">
              {{title}}
      </th>
</script>

<script type="text/html" id="tableBody">
     <div id="{{id}}_table_body_div" class=" {{id}}_slaver_width" style=" overflow:auto;height:{{if height!=undefined && openPage }}{{height-80}}{{else if height!=undefined && openPage ==false }}{{height-40}}{{/if}}px;">
         <%if(data == undefined || data.length<=0){%>
         <div style="text-align: center;width: {{scrollWidth}}">
             服务器无法拉取到数据<span class=" layui-icon-loading  layui-anim layui-anim-rotate layui-anim-loop"></span>
         </div>
         <%}else{%>
         <table id="{{id}}_table_body" class="layui-table" style="width: {{scrollWidth}};margin: 0px;">
             <tbody>
             {{each data}}
                   {{include "tableTr" $value}}
             {{/each}}
             </tbody>
         </table>
         <%}%>
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
        <td class="<%=field%>_td {{field}}_slaver_width" style="display: inline-block; " >
            {{include "table_body_td_content" $imports.handler( data[field],field)}}
        </td>
        <%};%>
    </tr>

</script>
<script type="text/html" id="table_body_td_content">
    <div class="showAll " style="box-sizing: border-box;
    text-overflow: ellipsis;
    overflow: hidden;
    text-align: center;
   white-space: nowrap;margin:0;padding: 0;">
    <% if(url!= undefined){%>
      <a href="{{url}}" style="color: #01AAED;" target="_blank">{{title}}</a>
   <%}else{%>
    {{title}}
   <%}%><div>
</script>
 </body>

</html>

</html>
