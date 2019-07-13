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
<#include "/commonJS.ftl">
<script>

    function createObjectURL(object) {
        return (window.URL) ? window.URL.createObjectURL(object) : window.webkitURL.createObjectURL(object);
    }
    function binaryToStr(str){
        var result = [];
        var list = str.split(" ");
        for(var i=0;i<list.length;i++){
            var item = list[i];
            var asciiCode = parseInt(item,2);
            var charValue = String.fromCharCode(asciiCode);
            result.push(charValue);
        }
        return result.join("");
    }
    function  arrayBufferToBase64(buffer) {
        var binary = '';
        var bytes = new Uint8Array(buffer);
        var len = bytes.byteLength;
        for (var i = 0; i < len; i += 1) {
            binary += String.fromCharCode(bytes[i]);
        }
        return window.btoa(binary);  //base64
    };

    function UrlDecode(str){
        var ret="";
        for(var i=0;i<str.length;i++){
            var chr = str.charAt(i);
            if(chr == "+"){
                ret+=" ";
            }else if(chr=="%"){
                var asc = str.substring(i+1,i+3);
                if(parseInt("0x"+asc)>0x7f){
                    ret+=asc2str(parseInt("0x"+asc+str.substring(i+4,i+6)));
                    i+=5;
                }else{
                    ret+=asc2str(parseInt("0x"+asc));
                    i+=2;
                }
            }else{
                ret+= chr;
            }
        }
        return ret;
    }
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
        $.ajax({
            url: "/api/post/getCenterQRCode",
            type: "POST",
            data: {'code':nameInput.value},
             success: function (data) {
                $(img) .attr("src","data:image/jpg;base64,"+ data.data.imgBase64)
            }, error: function ()  {
                alert("没有获取返回数据");
            }
    })},false)

</script>
</html>