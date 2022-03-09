function RegisterController(){
    $("button.btn-info").click(function(){
        var username = $("#userName").val();
        var password = $("#password").val();
        var confirmPass = $("#confirmPass").val();
        var phoneNumber = $("#phoneNumber").val();
        if(RegisterController.prototype.checkPassword(password, confirmPass)){
            RegisterController.prototype.register(username, password, phoneNumber);
        }
    })
}


RegisterController.prototype.register = function(username, password, phoneNumber){
        $.ajax({
                method:"post",
                url: "http://localhost:8080/register",
                data:{"username": username, "password": password, "phoneNumber" : phoneNumber},
                dataType:"application/json"
            }).fail(function(response){
                if(response.status==200){
                    alert("Account is created successfully!");
                    window.location.replace("/login");
                }else{
                    alert("Error");
                    window.location.reload();
                }
            });
}

RegisterController.prototype.checkPassword = function(password, confirmPass){
       if(RegisterController.prototype.validatePassword(password)){
           if(password != confirmPass){
           alert("Password not match!");
           return false;
           }
       }else{
            alert("Password must have at least 1 uppercase letter, 1 lowercase letter, 1 number, 1 special character and must be at least 8 characters")
            return false;
       }
       return true;
}
RegisterController.prototype.checkPhoneNumber = function(phoneNumber){

}

RegisterController.prototype.validatePassword = function (value) {
 return /^(?=.*?[A-Z]).{8,}$/.test(value);
};
var registerController;
$(document).ready(function(){
         registerController = new RegisterController();
    });