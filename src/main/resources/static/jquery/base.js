var baseUrl = "http://localhost:8100"

function post(uri, formData, success) {
    $.ajax({
        type: "POST",
        url: baseUrl + uri,
        data: formData,
        success: success,
        error: function () {
            alert("网络异常")
        }
    });
}

function get(uri, success) {
    $.ajax({
        type: "GET",
        url: baseUrl + uri,
        success: success,
        error: function () {
            alert("网络异常")
        }
    });
}


function toast(msg,duration){
    duration=isNaN(duration)?3000:duration;
    var m = document.createElement('div');
    m.innerHTML = "<img src='/static/home/waiting.gif' style='text-align: center; margin: 5px auto; width: 20px;height: 20px;'> <br>  <span style='margin-left: 20px;height: 20px;'>"+msg+"</span>";
   // m.style.cssText="max-width:60%;min-width: 150px;padding:0 14px;height: 40px;color: rgb(255, 255, 255);line-height: 40px;text-align: center;border-radius: 4px;position: fixed;top: 50%;left: 50%;transform: translate(-50%, -50%);z-index: 999999;background: rgba(0, 0, 0,.7);font-size: 16px;";
    m.style.cssText=" position: fixed;top: 50%; margin-left:auto;margin-right:auto; border-radius:4px;background-color:grey; padding-right:10px; display:block; margin-left:40%;"
    document.body.appendChild(m);
    setTimeout(function() {
        var d = 0.5;
        m.style.webkitTransition = '-webkit-transform ' + d + 's ease-in, opacity ' + d + 's ease-in';
        m.style.opacity = '0';
        setTimeout(function() { document.body.removeChild(m) }, d * 1000);
    }, duration);
}
var waitingClassName = "waiting" ;
function  addWaiting() {
    var width =  document.documentElement.clientWidth;
    var height =  document.documentElement.clientHeight
    var mask = document.createElement('div');
    mask.style.cssText = "position: fixed;top: 0;width:"+width+"px;height:"+height+"px;margin:0;padding:0;z-index: 999999; background:rgba(200,200,200,0.4);"

    var m = document.createElement('div');
    $(mask).attr("class",waitingClassName);
    m.innerHTML = "<img src='/static/home/waiting.gif' style='text-align: left;margin: 5px;width: 50px;height: 50px '>";
    m.style.cssText="max-width:60%;min-width: 150px;padding:0 14px;color: rgb(255, 255, 255);text-align: center;border-radius: 4px;position: fixed;top: 40%;left: 50%;transform: translate(-50%, -50%);z-index: 999999;font-size: 16px;";
   mask.appendChild(m)
    document.body.appendChild(mask);
}
function  removeWaiting() {
    var m = $("."+waitingClassName);
    if(m!=undefined){
        $(m).remove();
    }
}
function  isEmpty(str) {
    if(str==undefined){
        return true;
    }
    if(str == ""){
        return true;
    }
    return false;
}
function  addMenuUrl(url,menu_name) {
    if(isEmpty(url)){
        return
    }
    var m = document.createElement('a');
    $(m).attr("href",url);
    $(m).css("height","20px")
    $(m).css("border-radius","5px")
    $(m).css("background-color","red");
    $(m).css("margin","20px")
    $(m).css("text-decoration","none")
    $(m).css("padding-left","5px")
    $(m).css("padding-right","5px")

    $(m).css("color","white")
    $(m).html("<span>"+menu_name+"</span>")
    document.body.appendChild(m);

}

