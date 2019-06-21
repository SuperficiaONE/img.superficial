<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>生成token</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <#include  "/commonCss.ftl">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->、
    <style>
        th,td{
            width: 100px;
            padding: 5px;
            text-align: center;
        }
    </style>
</head>
<body>
   <button id="addImg">添加图片</button>
   <table id="table" >
       <thead>
           <th style="width: 100px">操作</th>
           <th style="width: 100px">序列</th>
           <th style="height: 100px;">图片</th>
       </thead>
       <tbody>
       </tbody>
   </table>

  <#include  "/commonJS.ftl">
   <script type="">
       function  deleteTr(elem) {
           $(elem).parents('tr').remove()
       }
   </script>

   <script type="text/html" id="tableTr">
       <tr>
           <td><button onclick="deleteTr(this)">删除 </td>
           <td><%=length+1%></td>
           <td>
           <div style="border: 1px solid #3d3d3d;border-radius: 10px; width: 100px;height: 100px;z-index: 99;"  id='<%=upload_id%>'>
           <img onclick='' hidden='hidden' style='border-radius: 10px;width:100%;height:100%;'/>
           </div>
       </td>
       </tr>
</script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    function bindUpload(id){
        layui.use('upload', function(){
            var $ = layui.jquery
                    ,upload = layui.upload;

            //普通图片上传
            var uploadInst = upload.render({
                elem: '#'+id
                ,url: '/api/layImg/upload'
                ,before: function(obj){
                }
                ,done: function(res){
                    //如果上传失败
                    if(res.code== 0){
                        $("#"+id).children('img').attr("src",res.data.src)
                        $("#"+id).children('img').show()
                    }
                    //上传成功
                }
                ,error: function(){
                }
            })
        })
    }
    $(function () {
        $("#addImg").click(function () {
            var length = $("#table tbody tr").length;
            var upload_id="upload"+length
            $("#table tbody").append(template("tableTr",{'length':length,'upload_id':upload_id}))
            bindUpload(upload_id)
        })
    })

</script>

</body>
</html>

</html>
