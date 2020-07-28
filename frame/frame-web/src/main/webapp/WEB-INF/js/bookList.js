$(function () {
    $('.J-book-del').on('click', function () {
        var $this = $(this);
        var index = $this.parents('.J-book-li').index();
        var bookId = $this.attr('data-bookId');
        $.ajax({
            type: 'POST',
            url: '//www.book.com/book/delete',
            data: {
                bookId: bookId
            },
            cache: false,
            success: function (result) {
                if (result.code === '10001') {
                    alert('删除成功！');
                    $('.J-book-li').eq(index).remove();
                } else {
                    alert('删除失败，请重试！');
                }
            }
        });
    })
});