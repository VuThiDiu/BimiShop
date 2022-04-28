

function LoginController(){
    const loginPath = $("#loginPath").val();
    const user1 = $("#name1").val();
    const pass1 = $("#pass1").val() ;
    $("button").click(function(){
        var username = $("#username").val();
        var password = $("#password").val();
        const json = {
            "username" : user1,
            "password" : pass1,
        }
        const loginAuto = LoginController.prototype.callAPIAutomaticTaggingSystem(json, loginPath)
        const loginSystem = LoginController.prototype.callAPILogin(username, password);
    })
}



LoginController.prototype.callAPIAutomaticTaggingSystem = function(json, loginPath){
        $.ajax({
                type:'POST',
                url: `${loginPath}`,
                data:JSON.stringify(json),
                contentType: "application/json",
                beforeSend: function(xhr){
                },
                dataType: 'json',
                cache: false,
                processData: false,
                success : function(data, status){
                    if(status=='success'){
                        console.log(data);
                        localStorage.setItem("autoTaggingSystemToken",JSON.stringify(data) );
                    }else{
                        alert("Error");
                        window.location.reload();
                    }
                },
                error: function (e){
                    alert("Error");
                    window.location.reload();
                }
            });
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