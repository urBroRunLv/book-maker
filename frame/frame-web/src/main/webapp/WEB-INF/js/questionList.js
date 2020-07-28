$(function () {
    $('.J-question-del').on('click', function (e) {
        e.preventDefault();
        var $this = $(this);
        var questionId = $this.attr('data-questionId');
        $.ajax({
            type: 'POST',
            url: '//www.book.com/question/delete',
            data: {
                questionId: questionId
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