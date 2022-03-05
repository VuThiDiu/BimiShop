function LoginController(){
    $(document).on("click", "button.btn-login", function(){
        var form = $("#loginForm")[0];
        var formData = new FormData(form);
        LoginController.prototype.callLoginAPI(formData);
    });
}
LoginController.prototype.callLoginAPI = function(formData){
    $.ajax({
        type: "POST",
        url : "/login",
        data : formData,
        cache : false,
        processData : false,
        contentType: false,
        beforeSend: function(){
            alert("hoeme");
        },
        success : function(response){
            alert("success");
//            localStorage.setItem("loginResponse",response.body );
            window.location.replace('/home');
        },
        error : function(err){
            alert(error);
            window.location.reload();
        }

    })
}
var loginController;
$(document).ready(function(){
    loginController = new LoginController();
})