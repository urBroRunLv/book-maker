$(function () {
    /*radio change*/
    $('.J-form-type').on('change', 'input[name=radioType]', function () {
        var $this = $(this);
        var formTypeCntIndex = $this.val();
        var $formTypeCnt = $('.form-type-cnt');
        $formTypeCnt.hide();
        $('.'+formTypeCntIndex).show();
    });

});

$(function () {
    $('.J-chapter-del').on('click', function (e) {
        e.preventDefault();
        var $this = $(this);
        var chapterId = $this.attr('data-chapterId');
        $.ajax({
            type: 'POST',
            url: '//www.book.com/chapter/delete',
            data: {
                chapterId: chapterId
            },
            cache: false,
            success: function (result) {
                if (result.code === '10001') {
                    alert('删除成功！');
                    window.location.reload();
                } else {
                    alert('删除失败，请重试！');
                }
            }
        });
    })
});