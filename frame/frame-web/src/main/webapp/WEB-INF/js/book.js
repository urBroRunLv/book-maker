function uploadPic(fileDom) {
    //判断是否支持FileReader
    if (window.FileReader) {
        var reader = new FileReader();
    } else {
        alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
    }

    //获取文件
    var file = fileDom.files[0];
    var imageType = /^image\//;

    //是否是图片
    if (!imageType.test(file.type)) {
        alert("请选择图片！");
    }

    reader.onload = function (e) {
        var filePic = document.getElementById('filePic');
        filePic.src = this.result;
        document.getElementById("bookPic").value = this.result;
    };
    reader.readAsDataURL(file);
}

function uploadPicContent(fileDom) {
    //判断是否支持FileReader
    if (window.FileReader) {
        var reader = new FileReader();
    } else {
        alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
    }

    //获取文件
    var file = fileDom.files[0];
    var imageType = /^image\//;

    //是否是图片
    if (!imageType.test(file.type)) {
        alert("请选择图片！");
    }

    reader.onload = function (e) {
        var filePic = document.getElementById('filePic');
        filePic.src = this.result;
        document.getElementById("base64Pic").value = this.result;
    };
    reader.readAsDataURL(file);
}