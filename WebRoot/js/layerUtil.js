var loadLayer ;
var needCloseLoadView = true;
var successText =
function beginLoad(suceess){
    loadLayer = layer.load(1, {
    shade: [0.1,'#fff'] //0.1透明度的白色背景
    });
    setTimeout(function () {
        if(needCloseLoadView){
            layer.close(loadLayer);layer.confirm('保存失败', {btn: ['确定'] });
        }
    }, 10000);
}