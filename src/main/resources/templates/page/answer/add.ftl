<html>
<head>
<title>添加答案</title>
<#include  "/commonCss.ftl">
</head>
<body>
<div>
    <label>添加答案:</label> <input id="content" placeholder="请输入答案">
</div>
<div>
    <label>添加英文:</label> <input id="englishContent" placeholder="请输入英文答案">
</div>
<div>
    <button id="add">添加答案</button> <button id="clear">清空答案</button>
</div>
</body>
<#include  "/commonJS.ftl">
<script>
    $(function () {
        console.log("hello")
    })
    var divs = document.getElementsByTagName("div")
    var inputs = document.getElementsByTagName("input")
    var btns = document.getElementsByTagName("button")
    if(divs!=undefined && divs.length>0){
        for (var i = 0;i<divs.length;i++){
            divs[i].style.textAlign = "center"
            divs[i].style.marginTop = "20px"
        }
    }
    if(inputs!=undefined && inputs.length>0){
        for (var i = 0;i<inputs.length;i++){
            inputs[i].style.width = "300px"
            inputs[i].style.height = "38px"
            inputs[i].style.marginLeft = "20px"
            inputs[i].style.fontSize = "20"
        }
    }
    if(btns!=undefined && btns.length>0){
        for (var i = 0;i<btns.length;i++){
            btns[i].style.fontSize = "16"
        }
    }
    var add  = document.getElementById("add")
    var clear = document.getElementById("clear")

    layui.use(['layer'],function () {
        var layer = layui.layer;
        add.addEventListener("click",function (ev) {
            var uri = "/api/answer/add?content="+$("#content").val()+"&englishContent="+$("#englishContent").val()
            get(uri,function (data) {
                if(data.state == 1){
                    layer.msg(data.msg)
                }else {
                    layer.alert(data.msg)
                }
            })
        })
        clear.addEventListener("click",function (ev) {
            $("input").val("")
        })
    })



</script>
</html>