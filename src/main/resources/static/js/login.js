//function LoginController(){
//    $(document).on("click", "button.btn-login", function(){
//        var form = $("#loginForm")[0];
//        var formData = new FormData(form);
//        LoginController.prototype.callLoginAPI(formData);
//    });
//}
//LoginController.prototype.callLoginAPI = function(formData){
//    $.ajax({
//        type: "POST",
//        url : "/login",
//        data : formData,
//        cache : false,
//        processData : false,
//        contentType: false,
//        beforeSend: function(){
//            alert("hoeme");
//        },
//        success : function(response){
//            alert("success");
//            localStorage.setItem("loginResponse",response.body );
//            window.location.replace('/home');
//        },
//        error : function(err){
//            alert(error);
//            window.location.reload();
//        }
//
//    })
//}
//var loginController;
//$(document).ready(function(){
//    loginController = new LoginController();
//})


function LoginController(){
    $("button").click(function(){
        var username = $("#username").val();
        var password = $("#password").val();
        LoginController.prototype.callAPILogin(username, password);
    })
}


LoginController.prototype.callAPILogin = function(username, password){
        $.ajax({
                method:"post",
                url: "http://localhost:8080/login",
                data:{"username": username, "password": password},
                dataType:"application/json"
            }).fail(function(response){
                if(response.status==200){
                    localStorage.setItem("loginResponse",response.body );
                    window.location.replace('/home');
                }else{
                    alert("user not existed or password is wrong");
                }
            });
}
var loginController;
$(document).ready(function(){
         loginController = new LoginController();
    });