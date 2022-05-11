

function LoginController(){
    const loginPath = $("#loginPath").val();
    $("button").click(function(){
        var username = $("#username").val();
        var password = $("#password").val();
        const loginSystem = LoginController.prototype.callAPILogin(username, password);
    })
}

LoginController.prototype.callAPILogin = function(username, password){
        $.ajax({
                method:"post",
                url: `login`,
                data:{"username": username, "password": password},
                dataType:"application/json"
            }).fail(function(response){
                if(response.status==200){
                    console.log(response);
                    localStorage.setItem("loginResponse",response.responseText );
                    window.location.replace('/api/home');
                }else{
                    alert("user not existed or password is wrong");
                    window.location.reload();
                }
            });
}

var loginController;
$(document).ready(function(){
         loginController = new LoginController();
    });