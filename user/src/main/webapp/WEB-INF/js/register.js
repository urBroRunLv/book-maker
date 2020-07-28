$(document).ready(function () {

    /**
     * 校验
     * */
    $.validator.addMethod("trueName", function (value, element) {
        var username = /^(?![0-9]*$)([\u4e00-\u9fa5]+||[a-zA-Z0-9]+)$/;
        return this.optional(element) || (username.test(value));
    });
    $.validator.addMethod("truePhone", function (value, element) {
        var tel = /^1[3|4|5|7|8]\d{9}$/;
        return this.optional(element) || (tel.test(value));
    });
    $.validator.addMethod("truePassword", function (value, element) {
        var password = $("input[name=userPassword]").val();
        return this.optional(element) || (password === value);
    });

    $(".J-login-form").validate({
        rules: {
            userName: {
                required: true,
                trueName: true
            },
            userPhone: {
                required: true,
                truePhone: true
            },
            userPassword: {
                required: true
            },
            verifyPassword: {
                required: true,
                truePassword: true
            }

        },
        messages: {
            userName: {
                required: "请输入用户名",
                trueName: "请输入正确的用户名"
            },
            userPhone: {
                required: "请输入手机号码",
                truePhone: "请输入正确的手机号"
            },
            userPassword: {
                required: "请输入密码"
            },
            verifyPassword: {
                required: "请再次输入密码",
                truePassword: "密码输入不一致"
            }
        },
        ignore: ".J-ignore input",
        submitHandler: function (form) {
            form.submit();
        }

    });

});