<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/5/4
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<head>
    <script src="js/head.js"></script>
    <title>图片管理</title>
</head>
<body>
<div class="container" style="height: 1000px;background-color: white">
    <div class="row">
        <button type="button" class="btn btn-primary pull-left" style="margin-left: 2%;" onclick="delPicture()">删除</button>
    </div>
    <div class="row">
        <table class="table table-bordered" style=" margin: 10px" id="pictureTable">
            <thead>
            <tr>
                <td type="checkBox" data-key="pictureId"></td>
                <td type="normal" data-key="pictureId">图片标识</td>
                <td type="normal" data-key="picturePath">图片</td>
                <td type="normal" data-key="pictureType">图片类型</td>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        <div class="text-center">
            <ul class="pagination" id="groupShowPage"></ul>
        </div>
    </div>
</div>
<script>
    var houseId;
    $(document).ready(function () {
        houseId = getParam("houseId");
        getTableData(0,10,true);
    });

    function getTableData(begin,end,isFirst) {
        beginLoad("","",5000);
        doPostAjaxAndLoad("housePictureManage_action",{houseId:houseId,begin:begin,end:end,actionType:1},function (data) {
            if(data.result == "Y"){
                var specialData = {
                    picturePath:{
                        specialFunction:function (value) {
                            return "<img class='img-thumbnail' layer-src='showImage?imageFile="+value.picturePath+"' style='width: 100px;height: 60px' src='showImage?imageFile="+value.picturePath+"'/>";
                        }
                    }
                };
                createTableHtmlUtil("pictureTable",data.pictureList,specialData);
                layer.photos({
                    photos: '#pictureTable'
                    ,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
                });
                if(isFirst){
                    setPage(data.count,function (begin,end) {
                        getTableData(begin,end,false);
                    });
                }
            }
        });

    }
    function delPicture() {
        var array = getTableCheckData("pictureTable");
        if(array == undefined){
            return;
        }
        beginLoad("删除成功","删除失败",5000,function () {
            location.reload();
        });
        doPostAjaxAndLoad("housePictureManage_action",{houseId:houseId,pictureList:array,actionType:2},function (data) {
            if(data.result == "Y"){

            }
        });
    }
</script>
</body>
</html>
