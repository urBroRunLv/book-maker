$('.J-btn-del').click(function () {
    $.ajax({
        url: "/manage/user/untying",
        type: "POST",
        data: {
            userId: $(this).attr('data-userId')
        },
        dataType: "json",
        success: function (result) {
            if (result.code === '10001') {
                alert('解绑成功！');
                window.location.reload();
            } else {

            }
        },
        error: function () {
            alert('请求失败，请刷新页面！')
        }
    })
});