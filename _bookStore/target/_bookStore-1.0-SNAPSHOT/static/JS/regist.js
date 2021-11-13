<!--静态包含公共引用代码-->
<jsp:include page="/pages/common/jquery_common.jsp"></jsp:include>

const validationRule = {
    //rules是专门写规则的键值对
    rules: {
        username:{
            required:true,
            minlength:3,
            maxlength:12
        },

        email:{
            required: true,
            email: true
        },

        password:{
            required:true,
            minlength: 3,
            maxlength: 15
        },

        repetition:{
            required:true,
            minlength:3,
            maxlength:15,
            equalTo:"[name:'password']"
        },
    },
    //message是专门写提示信息的键值对
    messages:{
        username:{
            required:"必须填写用户名",
            minlength:"用户名必须大于3位",
            maxlength:"用户名必须小于15位"
        },

        email:{
            required: "必须填写邮箱",
            email: "请填写正确的email地址"
        },

        password:{
            required:"必须填写密码",
            minlength: "密码必须大于3位",
            maxlength: "密码必须小于15位"
        },

        repetition:{
            required:"重复密码",
            minlength:"两次密码不一致",
            maxlength:"两次密码不一致",
            equalTo:"两次密码不一致"
        },
    }
};

$(function (){
    $("#register").validate(validationRule);
});