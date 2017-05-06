/**
 * 生成右边的qtip
 * @param myContent 需要呈现的内容
 * @returns {___anonymous59_271}
 */
var pageSizeVal = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
var buttonNumVal = 6;
function getRentingTips(myContent){
	var tips = {
			content: myContent,
			show: {
			    ready: true
			},
			hide: false,
			position: {
			    my: 'left center',
			    at: 'right center'
			  },
			style: {
				classes: 'qtip-youtube'
			}
		};
	return tips;
}

/**
 * 保存密码cookie
 */
function savePaw(){
	var codeVal = codeObj.val();
	var passwordVal = passwordObj.val();
	if (!$("#saveid").attr("checked")) {
		$.cookie('codeVal', codeVal, {
			expires : 7
		});
		$.cookie('passwordVal', passwordVal, {
			expires : 7
		});
	}else{
		$.cookie('codeVal', '', {
			expires : -1
		});
		$.cookie('passwordVal', '', {
			expires : -1
		});
		codeObj.val("");
		passwordObj.val("");
	}
}

/**
 * 获取url参数
 * @param paramName 参数名
 * @returns
 */
function getParam(paramName) { 
    paramValue = "", isFound = !1;
    if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) {
        arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&"), i = 0; 
        while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++ 
    } 
    return paramValue == "" && (paramValue = null), paramValue 
}

/**
 * 延迟跳转url
 * @param url
 * @param time
 */
function delayURL(url,time){
	 setTimeout("top.location.href = '" + url + "'",time);
}

function doPostAjax(url,data,callback){
	try{
        $.ajax({
            type: "POST",
            url: url,
            data: {"DATA":JSON.stringify(data)},
            dataType:'json',
            cache: false,
            success: function(rtnData){
                if(rtnData.count != undefined){
                    count = rtnData.count;
                }
                callback.call(this,rtnData);
            }
        });
	}catch (e){
		alert(e);
	}
}

function doPostAjaxAndDealPage(url,data,callback) {
    try{
        $.ajax({
            type: "POST",
            url: url,
            data: {"DATA":JSON.stringify(data)},
            dataType:'json',
            cache: false,
            success: function(rtnData){
                callback.call(this,rtnData);
            }
        });
    }catch (e){
        alert(e);
    }
}
/**
 * 分页组件的初始化函数
 * @param pageSize 每页展示的数量
 * @param buttons 展示的按钮的数量
 * @param total 数据的总量
 * @param callback 回调的函数
 */
function setPage(total,callback){
	try{
	    debugger;
        $(".pagination").jBootstrapPage({
            pageSize : pageSizeVal,
            total : total,
            maxPageButton:buttonNumVal,
            onPageClicked: function(obj, pageIndex) {
            	var index = parseInt(pageIndex);
            	var begin = index*pageSizeVal;
            	var end = (index+1)*pageSizeVal;
                callback.call(this,begin,end);
            }
        });
	}catch(e){
		alert(e);
	}
}
function setPageById(id,total,callback) {
    try{
        debugger;
        $("#"+id).jBootstrapPage({
            pageSize : pageSizeVal,
            total : total,
            maxPageButton:buttonNumVal,
            onPageClicked: function(obj, pageIndex) {
                var index = parseInt(pageIndex);
                var begin = index*pageSizeVal;
                var end = (index+1)*pageSizeVal;
                callback.call(this,begin,end);
            }
        });
    }catch(e){
        alert(e);
    }
}
function getCurrentTimeMillis(){
    return (new Date()).valueOf();
}

function createImageHtml(pictureName,height,width){
    var style = "background: url('./showImage?imageFile="+pictureName+"');background-repeat: no-repeat;background-position: center center;background-size:"+height+"px "+width+"px;height: 100%;width: 100%";
    return style;
}

var loadLayer;
var notSuccessFlag = true;
var loadSuccessMsg = "";
var loadFailMsg = ""
var successFunction;
var failFuntion;
function beginLoad(SuccessMsg,FailMsg,time,successFunctionI,failFunctionI) {
    loadSuccessMsg = SuccessMsg;
    loadFailMsg = FailMsg;
    successFunction = successFunctionI;
    failFuntion = failFunctionI;
    loadLayer = layer.load(1, {
        shade: [0.1,'#fff'] //0.1透明度的白色背景
    });
    setTimeout(function () {
        if(notSuccessFlag){
            if(loadFailMsg !="" || loadFailMsg !=undefined){
                var confirmLayer = layer.confirm(loadFailMsg, {
                    btn: ['确定'] //按钮
                },function () {
                    if(failFuntion){
                        failFuntion.call(this);
                    }
                    layer.close(confirmLayer);
                });
            }
            layer.close(loadLayer);
        }
    }, time);
}
function endLoad(data){
    notSuccessFlag = false;
    layer.close(loadLayer);
    if(data.result == "Y"){
        if(loadSuccessMsg != ""){
            var confirmLayer = layer.confirm(loadSuccessMsg, {
                btn: ['确定'] //按钮
            },function () {
                if(successFunction != undefined){
                    successFunction.call(this);
                }
                layer.close(confirmLayer);
            });
        }
    }else if(data.result == "N"){
        layer.confirm(data.errMessage, {
            btn: ['确定'] //按钮
        });
    }
}
function doPostAjaxAndLoad(url,data,callback){
    $.ajax({
        type: "POST",
        url: url,
        data: {"DATA":JSON.stringify(data)},
        dataType:'json',
        cache: false,
        success: function(rtnData){
            endLoad(rtnData);
            callback.call(this,rtnData);
        }
    });
}

function getViewData(oldJson){
    //获取信息
    var json = {};
    try{
        $.each($(".form-radio"),function (index,value,array) {
            var valId = $(this).find("input:radio")[0].name;
            var val = $(this).find("input:radio:checked").val();
            if(val !="" && val != undefined){
                json[valId] = val;
            }
        });

        $.each($(".form-text"),function (index,value,array) {
            $(this).find('input,textarea').each(function (index,value,array) {
                var valId = $(this).attr("id");
                var val = $(this).val();
                if(val !="" && val != undefined){
                    json[valId] = val;
                }
            });
        });
        $.each($(".form-select"),function (index,value,array) {
            $(this).find('select').each(function (index,value,array) {
                var valId = $(this).attr("id");
                var val = $(this).val();
                if(val !="" && val != undefined){
                    json[valId] = val;
                }
            });
        });
        $.each($(".form-checkbox"),function (index,value,array) {
            var checkArray = new Array;
            var valId = $(this).attr("id");
            $(this).find('input:checked').each(function (index,value,array) {
                var val = $(this).val();
                checkArray.push(val);
            });
            if(checkArray.length !=0){
                json[valId] = checkArray;
            }
        });
    }catch (e){
        alert(e);
    }
    return json;
}

function setViewData(json){
    $.each(json,function (index,value,array) {
        var comp = $("#"+index);
        if(comp.length != 0 ){
            var tagName = comp[0].tagName;
            if(tagName == "INPUT" || tagName == "SELECT" || tagName=="TEXTAREA"){
                comp[0].value = value;
            }else if(tagName == "DIV"){
                $(comp).find('input').each(function (index,inputCom,array) {
                    var val  = $(this).attr("value");
                    if($.inArray(val,value) != -1){
                        $(this).attr("checked","checked");
                    }
                });
            }
        }

    });
}
/**
 * 生成表格的工具类
 * 使用方法：
 * @param tableId
 * @param dataList
 * @param specialData
 */
function createTableHtmlUtil(tableId,dataList,specialData){
    try{
        var htmlArray = new Array();//所有的表格Html数据
        $.each(dataList,function (index,rowValue,array) {
            htmlArray.push("<tr>");
            $.each($("#"+tableId+" thead tr td"),function (index,value,array) {
                var cla = $(value).attr("class");//样式
                var key = $(value).attr("data-key");//rugu
                var type = $(value).attr("type");
                if(type == 'checkBox'){
                    //生成checkbox
                    htmlArray.push("<td><input type='checkbox' value='"+rowValue[key]+"'></input></td>");
                }else if(type == 'radio'){

                }else if(type == 'normal'){
                    if(specialData&&specialData[key]){
                        htmlArray.push("<td>"+specialData[key].specialFunction(rowValue)+"</td>");
                    }else if(cla == 'hide'){
                        htmlArray.push("<td class='hide'>"+rowValue[key]+"</td>");
                    }else{
                        if(rowValue[key]){
                            htmlArray.push("<td>"+rowValue[key]+"</td>");
                        }else{
                            htmlArray.push("<td></td>");
                        }
                    }
                }
            });
            htmlArray.push("</tr>");
        });
        $("#"+tableId+" tbody").html(String.prototype.concat.apply("", htmlArray));
    }catch (e){
        alert(e);
    }
}

function openIFrame(title,content,width,height){
    var defaultOption = {
        type: 2,
        title: title,
        shadeClose: true,
        shade: false,
        maxmin: true, //开启最大化最小化按钮
        area: ['893px', '600px'],
        content:content
    };
    var option = {
        type: 2,
        title: title,
        shadeClose: true,
        shade: false,
        maxmin: true, //开启最大化最小化按钮
        content:content
    };
    if(width  != undefined && height != undefined){
        option.area=[width+'px', height+'px'];
    }
    $.extend(defaultOption,option);
    layer.open(defaultOption);
}

function getTableCheckData(id) {
    var rtnArray = new Array;
    var checked = $("#"+id+" input:checked");
    if(checked.length == 0){
        layer.confirm("至少选择一条记录", {
            btn: ['确定'] //按钮
        });
        return;
    }
    $.each(checked,function (index,value,array) {
        rtnArray.push(parseInt(value.value));
    });
    return rtnArray;
}