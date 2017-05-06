/**
 * 校验是否是邮箱
 * @param email_address
 * @returns {Boolean}
 */
function checkMail(email_address) {
	var regex = /^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/g;
	if (regex.test(email_address)) {
		return true;
	} else {
		return false;
	}
}
/**
 * 校验是否是手机号码
 * @param phone_number
 * @returns {Boolean}
 */
function checkPhone(phone_number) {
	var re=new RegExp(/^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)$/);
    var retu=phone_number.match(re);
    if(retu){
        return true;
    }else{
        return false;
    }
}
/**
 * 校验时间
 * @param time1
 * @param time2
 * @returns {Boolean}
 */
function checkTime(time1,time2){
	var date1 = new Date(time1);
	var date2 = new Date(time2);
	if(date1.getTime()>=date2.getTime()){
		return false;
	}
	return true;
}
/**
 * 校验数字
 * @param time1
 * @param time2
 * @returns {Boolean}
 */
function validateNum(obj1) {
    var obj = $(obj1).val();
    if(obj!="" && obj!=undefined){
        if(isNaN(obj)){
            layer.alert('请输入数字', {
                skin: 'layui-layer-molv' //样式类名
                ,closeBtn: 0
            });
            $(obj1).val("")
            return;
        }
    }
}


function validForm(){
    var success = true;
    $.each($(".has-check"),function (index,value,array) {
        var labelText = $(value).find('.control-label').text();
        var index = labelText.indexOf(':');
        labelText = labelText.substr(0,index);

        var checkComponent = $(value).find('.form-control');
        var checkComponentVal = checkComponent.val();
        if(checkComponentVal ==  undefined || checkComponentVal ==""){
            layer.msg(labelText+'必须要填写');
            checkComponent.focus();
            success = false;
            return false;
        }
    });
    return success;
}