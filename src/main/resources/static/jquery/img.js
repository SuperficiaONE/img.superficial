//ele 为img对象
function  showLargeImg(ele) {
   var imgSrc = $(ele).attr("src");
   if(imgSrc==undefined||imgSrc==""){
       console.error("showLargeImg 不能绑定在非img元素上")
   }
 var d =   {
        "title": "", //相册标题
        "id": 123, //相册id
        "start": 0, //初始显示的图片序号，默认0
        "data": [   //相册包含的图片，数组格式
        {
            "alt": "图片名",
            "pid": 666, //图片id
            "src": imgSrc, //原图地址
            "thumb": "" //缩略图地址
        }
    ]
    }

   layui.use("layer",function () {
       var layer = layui.layer
       layer.photos({
           photos: d
           ,anim: 1 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
       });
   })
}