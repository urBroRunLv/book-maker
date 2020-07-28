$(function () {

    $('.J-add-comment,.J-btn-replay').click(function () {
        var $this = $(this);
        var $dialogBtn = $('.J-dialog-btn');
        var $replayText = $('textarea[name=replayText]');
        if ($this.parents('.content-li').length > 0) {
            $dialogBtn.text('回复');
            $replayText.attr('placeholder', '回复内容');
            var index = $this.parents('.content-li').index();
            $('input[name=replayIndex]').val(index)
        } else {
            $dialogBtn.text('评论');
            $replayText.attr('placeholder', '评论内容');
        }
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
                alert('请求失败')
            }
        })
    };

    /*评论/回复*/
    $('.J-dialog-btn').click(function () {
        var $this = $(this);
        var url = "/chat/add";
        var data = {};
        if($this.text() === '评论'){
            data = {
                chapterId: $('.J-add-comment').attr('data-chapterId'),
                text: $('textarea[name=replayText]').val()
            }
        }else {
            var index = $('input[name=replayIndex]').val();
            var $contentCurLi = $('.J-content-li').eq(index);
            data = {
                chapterId: $contentCurLi.find('.J-btn-replay').attr('data-chapterId'),
                text: $('textarea[name=replayText]').val(),
                linkChatId: $contentCurLi.find('input[name=linkChatId]').val()
            }
        }
        ajaxFun(url, data)
    });

    /*delete*/
    $('.J-btn-del').click(function () {
        var chatId = $(this).attr('data-chatId');
        var url = "/chat/delete";
        var data = {
            chatId: chatId
        };
        ajaxFun(url, data, '删除成功！')
    });

    /*zan*/
    $('.J-btn-zan').click(function () {
        var $this = $(this);
        var $btnNum = $this.parent().find('.J-btn-num');
        var num = $btnNum.text();
        var chatId = $(this).attr('data-chatId');
        if($this.hasClass('J-btn-zan-active')) {
            $this.removeClass('J-btn-zan-active');
            this.src = "../../../img/zan.png";
            num = Number(num) - 1;
        }else {
            $this.addClass('J-btn-zan-active');
            this.src = "../../../img/zan_active.png";
            num = Number(num) + 1;
        }
        $btnNum.html(num);
        var url = "/chat/top";
        var data = {
            chatId: chatId,
            num: num
        };
        ajaxFun(url, data)
    });

    /*switch*/
    $('.J-switch').click(function () {
        var $this = $(this);
        var $li = $this.parents('.J-manage-li');
        var index = $li.index();
        $this.toggleClass('swich-on');
        var switchChecked = $this.hasClass('swich-on');

        var url = "/manage/discuss/openFlag";
        var data = {
            openFlag: !!switchChecked ? 1 : 0,
            chapterId: $li.attr('data-chapterId')
        };
        ajaxFun(url,data);


    })

});