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

function getQueryVariable(variable)
{
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return(false);
}

function addAndShowTemplate(res,id){
    var e = 'body'
    if(id!=undefined ){
        e='#'+id;
    }
    if(res!=undefined && res.data!=undefined && res.data.length>0){
        for (var i = 0; i <res.data.length ; i++) {
            if(res.data[i]['templateElements']!=undefined){
                $(e).append(res.data[i]['templateElements'])
            }
        }
        for (var i = 0; i <res.data.length ; i++) {
            if(res.data[i]['templateScript']!=undefined){
                $("body").append(res.data[i]['templateScript'])
            }
        }
        for (var i = 0; i <res.data.length ; i++) {
            if(res.data[i]['beforeScript']!=undefined){
                $("body").append(res.data[i]['beforeScript'])
            }
        }
        for (var i = 0; i <res.data.length ; i++) {
            if(res.data[i]['afterScript']!=undefined){
                $("body").append(res.data[i]['afterScript'])
            }
        }
    }
}

function showError(msg) {
    showMsg(msg, 2)
}

function showSuccess(msg) {
    showMsg(msg, 1)
}

function showMsg(msg, icon) {
    layui.use(['layer'], function () {
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
function getParam(paramName) {
    paramValue = "", isFound = !1;
    if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) {
        arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&"), i = 0;
        while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++
    }
    return paramValue == "" && (paramValue = null), paramValue
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
function initSelects(url,type,disable) {
    layui.use(['layer'], function () {
        getAsync(url, false, function (res) {
            if (res.state == 1) {

                addWaiting();

                if (res.state == 1) {
                    var list = res.data;
                    if (list != undefined) {
                        for (var i = 0; i < list.length; i++) {
                            var data = list[i]

                            addSelect(data,type,disable)
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

function addSelect(data,type,disable) {
    if (data == undefined) {
        return;
    }
    $("#" + data.elementId).html("");
    if(disable){
        $("#" + data.elementId).append(" <label class=\"layui-form-label\">" + data.labelText + "</label>\n" +
            "            <div class=\"layui-input-inline\" >\n" +
            "                <select  disabled='disabled' class=\"layui-select\" lay-filter=\"" + data.elementId + "\" style=\"height: 30px; width: 200px;\" name=\"" + data.elementId + "\">\n" +
            "                </select>\n" +
            "            </div> ")
    }else{
        $("#" + data.elementId).append(" <label class=\"layui-form-label\">" + data.labelText + "</label>\n" +
            "            <div class=\"layui-input-inline\" >\n" +
            "                <select class=\"layui-select\" lay-filter=\"" + data.elementId + "\" style=\"height: 30px; width: 200px;\" name=\"" + data.elementId + "\">\n" +
            "                </select>\n" +
            "            </div> ")
    }
    var list = data.list;
    if (list != undefined) {
        for (var i = 0; i < list.length; i++) {
            var item = list[i]
            if(type!=undefined&&type==item.dictValue){
                $("select[name='" + data.elementId + "']").append("<option selected='selected' value='" + item.dictValue + "'   >" + item.dictText + "</option>")
            }else {
                $("select[name='" + data.elementId + "']").append("<option value='" + item.dictValue + "'   >" + item.dictText + "</option>")

            }
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
    layui.use(['layer', 'form'], function () {
        var form = layui.form;
        addWaiting();
        var layer = layui.layer
        getAsync(url, false, function (res) {
            if (res.state == 1) {
                var data = res.data;
                if (data != undefined) {
                    addSelect(data);
                    if (changeFnc != undefined) {
                        changeFnc();
                        form.on("select(" + data.elementId + ")", changeFnc);
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
 *  data的数据类型为
 *   {
 *       templateId:"",
 *       data:{ }
 *   }
 * @param data
 */
function getTemplateHtml(data) {
    return template(data.templateId, data);
}

/**
 * 数据类型：
 *    data: {
 *     id:xxx,
 *     templateVO:{
 *         templateId:xxx,
 *         data:[{"field":"id","title":"模板id"}]
 *         }
 *     }
 *
 * @param data
 * @param clear
 */
function renderTable(id, height) {
    var style = $("#" + id).attr("style")
    $("#" + id).replaceWith("<div id='" + id + "' class='" + id + "_master_width'  style='" + style + "overflow: hidden;" + "'></div>")
    $("#" + id).css("height", height)
   // $("#" + id).attr("class", "layui-anim layui-anim-scale")
}

function initTable(id,urls, openPage, page, pageSize, scrollWith, height) {

   var headerUrl=urls['headerUrl']
    var bodyUrl=urls['bodyUrl']
    renderTable(id, height);
    var fieldList = initTableHeader(id, headerUrl, scrollWith)
    template.defaults.imports.handler=function (title,field) {
        if(title == undefined){
            return {'title':title,'field':field};
        }
        if(title.url != undefined || title.title!=undefined){

            title.field = field;
            return title;
        }
        return {'title':title,'field':field};
    }
    initTableBody(bodyUrl, id, openPage, fieldList, scrollWith, height)
}
function bindTableScroll(id) {
    var t = document.getElementById(id+"_table_body_div");
    var tableBody = document.getElementById(id+"_table_body")
   // console.log($(tableBody).height()  +"xxx"+$(t).height())
    if($(tableBody).height() > $(t).height()){
        var head = document.getElementById(id+ "_table_header_div");
        var body = document.getElementById(id+"_table_body_div")
        $(body).css("overflow-y","scroll")
        $(head).css("overflow-y","scroll")
        $(head).css("overflow-x","hidden")
    }else {
        var head = document.getElementById(id+ "_table_header_div");
        var body = document.getElementById(id+"_table_body_div")
        $(body).css("overflow-y","hidden")
        $(head).css("overflow-y","hidden")
        $(head).css("overflow-x","hidden")
    }
    t.addEventListener("scroll", function() {
        var head = document.getElementById(id+ "_table_header");
        $(head).css("margin-left",-t.scrollLeft)
    })
}

function  bindWidthByTable(id,fieldList) {
    bindWidth(id);
    for (var i = 0; i <fieldList.length ; i++) {
        bindWidth(fieldList[i].field)
    }
    $( window).resize(function () {
        bindWidth(id);
        for (var i = 0; i <fieldList.length ; i++) {
            bindWidth(fieldList[i].field)
        }
    })
}
function initTableHeader(id, url, scrollWith) {
    var templateVo = getTableHeaderData(url);
    renderTableHeader(getFormatTableHeaderData(id, scrollWith, templateVo))
    return templateVo.data;
}

function getTableHeaderData(url) {
    var data = {}
    getAsync(url, false, function (res) {
        if (res.state == 1) {
            data = res.data.templateVO;
        }
    })
    return data;
}

function getFormatTableHeaderData(id, scrollWidth, templateVo) {
    if (templateVo == undefined) {
        return {'id': id, "scrollWidth": scrollWidth};
    }
    templateVo.id = id
    templateVo.scrollWidth = scrollWidth;
    return templateVo;
}

function renderTableHeader(templateVO) {
    $("#" + templateVO.id).html(getTemplateHtml(templateVO))
}
function renderTableBody(templateVO) {
    var e = document.getElementById(templateVO.id+"_table_body_div");
    if(e != undefined){
        $("#" + templateVO.id+"_table_body_div").replaceWith(getTemplateHtml(templateVO))
    }else{
        $("#" + templateVO.id).append(getTemplateHtml(templateVO))
    }
}

function initTableBody(url, id, openPage, fieldList, scrollWidth, height) {
    var tableBodyData = getTableBodyData(url, openPage, 1, 10);
    var templateVO = getFormatTableBodyData(id, tableBodyData, openPage, fieldList, scrollWidth, height)
    templateVO.first = true;
    addWaiting()
    setTimeout(function () {
        renderTableBody(templateVO)
        renderTablePage(id,url,openPage,fieldList,scrollWidth,height,templateVO.count)
        // 统一宽度
        bindWidthByTable(id,fieldList)
        //
        bindTableScroll(id)
        // 再次同一宽度 因为滚动条的出现会再次影响宽度可能
        bindWidthByTable(id,fieldList)
        tipsBind("showAll")
        removeWaiting()

    },1200)

}

function renderTablePage(id,url,openPage,fieldList,scrollWidth,height,count) {
    if(openPage){
        layui.use(['laypage'],function () {
            var laypage = layui.laypage;
            laypage.render({
                elem: id+"_table_page_div"
                ,count: count //数据总数，从服务端得到
                   , limits: [10, 20, 30, 40, 50, 100],
                    layout: ['prev', 'page', 'next', 'limit', "skip", "count", "refresh"],
                jump: function(obj, first){
                    //首次不执行
                    if(!first){
                        var tableBodyData = getTableBodyData(url, openPage, obj.curr, obj.limit);
                        var templateVO = getFormatTableBodyData(id, tableBodyData, openPage, fieldList, scrollWidth, height)
                        templateVO.first = false;
                        obj.count = templateVO.count
                        renderTableBody(templateVO)
                        bindWidthByTable(id,fieldList)
                        //
                        bindTableScroll(id)
                        // 再次同一宽度 因为滚动条的出现会再次影响宽度可能
                        bindWidthByTable(id,fieldList)
                        tipsBind("showAll")
                    }
                }
            });
        })
    }
}

function  getTableBodyData(url, openPage, page, pageSize) {
    if (!openPage) {
        var data = {}
        getAsync(url, false, function (res) {
            if (res.state == 1) {
                data = res.data.templateVO;
            }
        })
    } else {
        getAsync(url + "&openPage=" + openPage + "&page=" + page + "&pageSize=" + pageSize, false, function (res) {
            if (res.state == 1) {
                data = res.data.templateVO;
            }
        })
    }

    return data;

}

function getFormatTableBodyData(id, tableBodyTemplateVo, openPage, fieldList, scrollWidth, height) {
    var templateVO = {}
    templateVO.scrollWidth = scrollWidth;
    templateVO.height = height;
    templateVO.id = id;
    templateVO.openPage = openPage;
    templateVO.templateId=tableBodyTemplateVo.templateId
    if (tableBodyTemplateVo!=undefined) {
        var data = []
        var list = undefined;
        if (openPage) {
            list = tableBodyTemplateVo.data.data;
            templateVO.count = tableBodyTemplateVo.data.count;
        } else {
            list = tableBodyTemplateVo.data;
        }
        if (list!=undefined && list.length>0) {
            for (var i = 0; i < list.length; i++) {
                var f = {};
                f.fieldList = fieldList;
                f.data = list[i]
                data.push(f)
            }
        }
        templateVO.data = data;
    }
    return templateVO;
}

function bindWidth(id) {
    var w = $("." + id + "_master_width").width();
    $("." + id + "_slaver_width").css("width", w);
}

/**
 * 数据结构：
 * 定义一个过滤器 将data对象 转换一下
 *
 *       {
 *
 *           {
 *               templateId:xxx,
 *               data:[
 *
 *               ]
 *           }
 *       }
 * @param data
 */
function renderBody(id, fieldList, data) {

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

function handler(data) {
    if (data.url == undefined) {
        return {'title': data}
    } else {
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

function initArtTemplate(url) {
    getAsync(url, false, function (res) {
        if (res.state == 1) {
            if (res.data != undefined) {
                for (var i = 0; i < res.data.length; i++) {
                    $("body").append(res.data[i])
                }
            }
        } else {
            showError(res.msg)
        }
    })
}

function tipsBind(className) {
    $("." + className).hover(function () {
        var self = this;
        var width = $(self).width()
        var scrollWidth = self.scrollWidth;
        if (scrollWidth > width) {
            $.pt({
                target: $(self),
                position: 't', // 默认属性值
                align: 'c',
                content: "<div style='overflow:hidden'>" + $(self).html() + "</div>",
                leaveClose: true
            });
        }
    }, function () {
        var self = this;
        var width = $(self).width()
        var scrollWidth = self.scrollWidth;
        if (scrollWidth > width) {
            $(".pt").css("display", "none")
        }
    });
}


function toBlank(elem) {
   var url=$(elem).attr("data");
   window.open(url);
}
function toSelf(elem){
    var url=$(elem).attr("data");
    get(url,function(res){
        location.reload()
    })

}