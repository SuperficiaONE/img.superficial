<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>创建二维码</title>
    <style>
        body,html{
            width: 100%;
            height: 100%;
        }
          #inputDiv{
              margin: 0px;
              width: 100%;
              text-align: center;
          }

    </style>
</head>
<body>
  <div id="inputDiv">
      <input id="nameInput" />
      <button id="btn" >生成二维码</button>
      </br>
      <img id="img" />
  </div>
</body>
<script>

    var btn  = document.getElementById("btn");
    btn.addEventListener("click",function (ev) {
        var img = document.getElementById("img")
        var nameInput = document.getElementById("nameInput");
        img.style.height="200px"
        img.style.width="200px"
        img.style.textAlign="center"
        img.style.marginLeft="auto"
        img.style.marginRight="auto"
        img.style.marginTop="20px"
        img.setAttribute("src","http://127.0.0.1:8100/api/common/getCenterQRCode?code="+nameInput.value);
    },false)

</script>
</html>