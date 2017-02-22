/**
 * 生成右边的qtip
 * @param myContent 需要呈现的内容
 * @returns {___anonymous59_271}
 */
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

