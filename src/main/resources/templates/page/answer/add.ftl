<html>
<header>

</header>
<body>
<div>
    <label>添加答案:</label> <input placeholder="请输入答案">
</div>
<div>
    <label>添加英文:</label> <input placeholder="请输入英文答案">
</div>
<div>
    <button id="add">添加答案</button> <button id="clear">清空答案</button>
</div>
</body>
<script>
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

    add.addEventListener("click",function (ev) {
        console.log("点击添加")
    })
    clear.addEventListener("click",function (ev) {
        console.log("点击清除")
    })
</script>
</html>