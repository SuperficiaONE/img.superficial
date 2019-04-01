<html>
<head>
    <title>错误页面</title>
</head>
<body>
<h1>错误代码:${code!}</h1>
</body>
<script>
   var el =  document.getElementsByTagName("h1");
   if(el!=undefined && el.length>0){
       el[0].style.textAlign = "center";
   }
</script>
</html>