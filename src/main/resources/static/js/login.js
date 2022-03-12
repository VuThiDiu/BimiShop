
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
                    console.log(response);
                    localStorage.setItem("loginResponse",response.responseText );
                    window.location.replace('/api/home');
                }else{
                    alert("user not existed or password is wrong");
                }
            });
}
var loginController;
$(document).ready(function(){
         loginController = new LoginController();
    });