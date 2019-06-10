<html>
<head>
    <title>后台</title>
 <#include  "/commonCss.ftl">
<#include  "/commonJS.ftl">
</head>
<body>

</body>
<script type="application/javascript" >
    function  showLay(res,commitFnc) {
        var type= res.moduleType
        var moduleJson = res.moduleJson;
        var data = JSON.parse(res.dataJson)
        var table_str = "<table id='layer_table'>"
        table_str+="<thead><th>操作</th><th>序号</th><th>图片</th></thead><tbody>"
        for (var i = 0; i <data.length ; i++) {
            table_str+="<tr><td><button class=\"layui-btn delete_row\" >删除</button></td><td><div class='double_click'>"+(i+1)+"</div></td><td><img style='width: 100px;height:100px;' src='"+data[i]+"'/></td></tr>"
        }
        table_str+="</tbody></table>"
        var content =table_str+"<button type=\"button\" class=\"layui-btn\" id=\"uploadImage\">\n" +
                "  <i class=\"layui-icon\">&#xe67c;</i>上传图片\n" +
                "</button>"
                layui.use(['layer','upload'], function(){
            var layer = layui.layer;
            var upload = layui.upload;
            layer.open({
                title:"编辑",
                maxWidth: 800,
                maxHeight: 300,
                content:content,
                btn: ['取消', '提交'],
                yes: function(index, layero){
                  layer.close(index)
                }
                ,btn2: function(index, layero){
                    console.log(getData("layer_table"))
                    return false
                }
            });
                    var uploadInst = upload.render({
                        elem: '#uploadImage'
                        ,url: '/api/layImg/upload'
                        ,size:10240
                        ,done: function(res){
                          var length =  $('#layer_table tbody').children("tr").length;
                         $("#layer_table tbody").append("<tr><td><button class=\"layui-btn delete_row\" >删除</button></td><td><div class='double_click'>"+(length+1)+"</div></td><td><img style='width: 100px;height:100px;' src='"+res.data.src+"'/></td></tr>")
                        }
                        ,error: function(){

                        }
                    });


        });

    }
    function  getData(id) {
       var ths = $("#"+id+" tbody tr")
        var arr=[]
        for (var i = 0; i <ths.length ; i++) {
            var data = {}
           var order= $(ths[i]).find(".double_click").html();
            data['order']=order
            data['imgUrl'] = $(ths[i]).find("img").attr("src")
            arr.push(data)
        }
        return arr;
    }
    function  edite(moduleConfigId) {
        get("/api/moduleConfig/getSiteModuleConfigVo?moduleConfigId="+moduleConfigId,function (res) {
            showLay(res)
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