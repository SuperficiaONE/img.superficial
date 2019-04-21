var baseUrl = ""

// http://localhost:8100

function post(uri, formData, success) {
    $.ajax({
        type: "POST",
        url: baseUrl + uri,
        data: formData,
        success: success,
        error: function () {
            showError("网络异常，稍后重新尝试")
        }
    });
}
function showError(msg) {
    showMsg(msg,2)
}
function showSuccess(msg) {
 showMsg(msg,1)
}
function  showMsg(msg,icon) {
    layui.use(['layer'],function () {
        var layer = layui.layer;
        layer.msg(msg, {icon: icon});
    })
}
function renderForm() {
    layui.use('form', function () {
        var form = layui.form;
        form.render();
    });
}

function getAsync(uri, isAnsy, success) {
    $.ajax({
        type: "GET",
        url: baseUrl + uri,
        async: isAnsy,
        success: success,
        error: function () {
            showError("网络异常，稍后重新尝试")
        }
    });
}

/**
 * 从服务器获取如下格式数据
 * {
 *     state：1 成功
 *     data: [{tagName:,list:{value:,text:''}}]
 *     msg:
 *     }
 *     elementIds 为 form-item 的id
 * @param url
 * @param elementIds
 */
function initSelects(url) {
    layui.use(['layer'], function () {
        getAsync(url, false, function (res) {
            if (res.state == 1) {

                addWaiting();

                if (res.state == 1) {
                    var list = res.data;
                    if (list != undefined) {
                        for (var i = 0; i < list.length; i++) {
                            var data = list[i]
                            addSelect(data)
                        }
                        renderForm();
                    }
                    removeWaiting()

                } else {
                    layer.alert(res.msg)
                }
            } else {
                layer.alert(res.msg);
            }

        })
    })


}

function addSelect(data) {
    if (data == undefined) {
        return;
    }
    $("#" + data.elementId).html("");
    $("#" + data.elementId).append(" <label class=\"layui-form-label\">" + data.labelText + "</label>\n" +
        "            <div class=\"layui-input-inline\" >\n" +
        "                <select class=\"layui-select\" lay-filter=\""+data.elementId+"\" style=\"height: 30px; width: 200px;\" name=\"" + data.elementId + "\">\n" +
        "                </select>\n" +
        "            </div> ")
    var list = data.list;
    if (list != undefined) {
        for (var i = 0; i < list.length; i++) {
            var item = list[i]
            $("select[name='" + data.elementId + "']").append("<option value='" + item.dictValue + "'   >" + item.dictText + "</option>")
        }
    }
}

/**
 * 适用单个select 的初始化 并绑定传递绑定change方法
 * @param url
 * @param elementId
 * @param changeFnc
 */
function initSelect(url, changeFnc) {
    layui.use(['layer','form'], function () {
        var  form = layui.form;
        addWaiting();
        var layer = layui.layer
        getAsync(url, false, function (res) {
            if (res.state == 1) {
                var data = res.data;
                if (data != undefined) {
                    addSelect(data);
                    if (changeFnc != undefined) {
                        changeFnc();
                       form.on("select("+data.elementId+")",changeFnc);
                    }
                    renderForm();
                }
                removeWaiting()

            } else {
                layer.alert(res.msg)
            }
        })
    })

}

/**
 * 获取select对象
 */
function getSelectObject() {

}

/**
 *  data的数据类型为
 *   {
 *       templateId:"",
 *       data:{ }
 *   }
 * @param data
 */
function  getTemplateHtml(data) {
    return template(data.templateId,data);
}
function  replaceOldHtml(data,clear) {
    $("#"+data.id).replaceWith("<div id='"+data.id+"' style='overflow-y: auto'></div>")
    if(clear){
        $("#"+data.id).html("")
    }
    var templateVO = data.templateVO;
    templateVO.id = data.id
    $("#"+data.id).html(getTemplateHtml(templateVO))
}
function  bindWidth(id) {
    var w = $("."+id+"_master_width").width();
    $("."+id+"_slaver_width").css("width",w);
}
/**
 * 数据结构：
 *
 * @param data
 */
function  renderBody(data) {
      post(data.url,data,function (res) {
          // 加工数据
         if(res.state == 1){
             var rs = res.data;
             var  templateVOData = []
             if(rs!=undefined && rs.templateVO!=undefined){
                 var id= rs.id;
                 var  templateVOList
                 if(data.openPage){
                     templateVOList   = rs.templateVO.data.data;
                 }else {
                     templateVOList   = rs.templateVO.data;
                 }
                 for (var i = 0; i < templateVOList.length; i++) {
                     var  templateVO = {};
                     templateVO.data =templateVOList[i]
                     var fieldList = [];
                     if(data.list!=undefined){
                         for (var j = 0; j < data.list.length; j++) {
                             var filed = data.list[j]
                             filed.w = $("#"+filed.field+"_th").width();
                             fieldList.push(filed)
                         }
                     }
                     templateVO.fieldList = fieldList;
                     templateVOData.push(templateVO)
                 }
             }
             var tbodyData = {}
             tbodyData.id=rs.id;
             tbodyData.templateId = rs.templateVO.templateId
             tbodyData.data = templateVOData
             renderBodyData(tbodyData)
             tipsBind("showAll")
             bindWidth(rs.id)
             $(window).resize(data.list,function () {
                // console.log("xxx")
                 bindWidth(rs.id)
                 for (var j = 0; j < data.list.length; j++) {
                     bindWidth(data.list[j].field);
                 }
             })
             if(data.openPage){
                 var count = rs.count;

             }
         }else {
             showError(res.msg)
         }
      })
}
function  renderBodyData(data) {
     var tableBody = document.getElementById(data.id+"_div");
     if(tableBody != null || tableBody!=undefined){
       $(tableBody).replaceWith(template(data.templateId,data.templateVO));
     }else{
         $("#"+data.id).append(template(data.templateId,data))
     }
}
function postAsyn(uri, formData, isAnsy, success) {
    $.ajax({
        type: "POST",
        url: baseUrl + uri,
        data: formData,

        async: isAnsy,
        success: success,
        error: function () {
            showError("网络异常，稍后重新尝试")
        }
    });
}

function get(uri, success) {
    $.ajax({
        type: "GET",
        url: baseUrl + uri,
        success: success,
        error: function () {
            showError("网络异常，稍后重新尝试")
        }
    });
}
function  handler(data) {
    if(data.url == undefined){
        return {'title':data}
    }else {
        return data;
    }
}

function toast(msg, duration) {
    duration = isNaN(duration) ? 3000 : duration;
    var m = document.createElement('div');
    m.innerHTML = "<img src='/static/home/waiting.gif' style='text-align: center; margin: 5px auto; width: 20px;height: 20px;'> <br>  <span style='margin-left: 20px;height: 20px;'>" + msg + "</span>";
    // m.style.cssText="max-width:60%;min-width: 150px;padding:0 14px;height: 40px;color: rgb(255, 255, 255);line-height: 40px;text-align: center;border-radius: 4px;position: fixed;top: 50%;left: 50%;transform: translate(-50%, -50%);z-index: 999999;background: rgba(0, 0, 0,.7);font-size: 16px;";
    m.style.cssText = " position: fixed;top: 50%; margin-left:auto;margin-right:auto; border-radius:4px;background-color:grey; padding-right:10px; display:block; margin-left:40%;"
    document.body.appendChild(m);
    setTimeout(function () {
        var d = 0.5;
        m.style.webkitTransition = '-webkit-transform ' + d + 's ease-in, opacity ' + d + 's ease-in';
        m.style.opacity = '0';
        setTimeout(function () {
            document.body.removeChild(m)
        }, d * 1000);
    }, duration);
}

var waitingClassName = "waiting";

function addWaiting() {
    var width = document.documentElement.clientWidth;
    var height = document.documentElement.clientHeight
    var mask = document.createElement('div');
    mask.style.cssText = "position: fixed;top: 0;width:" + width + "px;height:" + height + "px;margin:0;padding:0;z-index: 999999; background:rgba(200,200,200,0.4);"

    var m = document.createElement('div');
    $(mask).attr("class", waitingClassName);
    m.innerHTML = "<img src='/static/home/waiting.gif' style='text-align: left;margin: 5px;width: 50px;height: 50px '>";
    m.style.cssText = "max-width:60%;min-width: 150px;padding:0 14px;color: rgb(255, 255, 255);text-align: center;border-radius: 4px;position: fixed;top: 40%;left: 50%;transform: translate(-50%, -50%);z-index: 999999;font-size: 16px;";
    mask.appendChild(m)
    document.body.appendChild(mask);
}

function removeWaiting() {
    var m = $("." + waitingClassName);
    if (m != undefined) {
        $(m).remove();
    }
}

function isEmpty(str) {
    if (str == undefined) {
        return true;
    }
    if (str == "") {
        return true;
    }
    return false;
}

function addMenuUrl(url, menu_name) {
    if (isEmpty(url)) {
        return
    }
    var m = document.createElement('a');
    $(m).attr("href", url);
    $(m).css("height", "20px")
    $(m).css("border-radius", "5px")
    $(m).css("background-color", "red");
    $(m).css("margin", "20px")
    $(m).css("text-decoration", "none")
    $(m).css("padding-left", "5px")
    $(m).css("padding-right", "5px")

    $(m).css("color", "white")
    $(m).html("<span>" + menu_name + "</span>")
    document.body.appendChild(m);

}
function  initArtTemplate(url) {
    getAsync(url,false, function (res) {
          if(res.state==1){
              if(res.data!=undefined){
                  for (var i = 0; i < res.data.length; i++) {
                     $("body").append(res.data[i])
                  }
              }
          }else{
              showError(res.msg)
          }
    })
}
function  tipsBind(className) {

$("."+className).hover(function() {
    var self = this;
    var width = $(self).width()
    var scrollWidth = self.scrollWidth;
    if(scrollWidth > width) {
        $.pt({
            target: $(self),
            position: 't', // 默认属性值
            align: 'c',
            content: "<div style='overflow:hidden'>" + $(self).html() + "</div>",
            leaveClose: true
        });
    }
    },function (){
        var self = this;
        var width = $(self).width()
        var scrollWidth = self.scrollWidth;
        if(scrollWidth > width) {
            $(".pt").css("display","none")
        }
    });
}