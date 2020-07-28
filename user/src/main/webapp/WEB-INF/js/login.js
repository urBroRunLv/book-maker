$(document).ready(function () {

    /**
     * 校验
     * */

    $.validator.addMethod("truePhone", function (value, element) {
        var tel = /^1[3|4|5|7|8]\d{9}$/;
        return this.optional(element) || (tel.test(value));
    });

    $(".J-login-form").validate({
        rules: {
            userPhone: {
                required: true,
                truePhone: true
            },
            userPassword: {
                required: true
            }

        },
        messages: {
            userPhone: {
                required: "请输入手机号码",
                truePhone: "请输入正确的手机号"
            },
            userPassword: {
                required: "请输入密码"
            }
        },
        ignore: ".J-ignore input",
        submitHandler: function (form) {
            form.submit();
        }

    });

});