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