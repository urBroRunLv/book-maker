$(function () {

    $('.J-edit-name').click(function () {
        $('.J-dialog').show();
    });
    $('.J-dialog-close').click(function () {
        $('.J-dialog').hide();
    });

    var ajaxFun = function (url, data, mes) {
        $.ajax({
            url: url,
            type: "POST",
            data: data,
            dataType: "json",
            success: function (result) {
                if (result.code === '10001') {
                    if (mes) {
                        alert(mes);
                    }
                    window.location.reload();
                } else {

                }
            },
            error: function () {
                alert('请求失败，请刷新页面！')
            }
        })
    };

    /*修改用户名*/
    $('.J-dialog-btn').click(function () {
        var url = "/manage/user/change-name";
        var data = {
            id: $('.J-user-id').text(),
            name: $('input[name=newName]').val()
        };
        ajaxFun(url, data)
    });

});