function BaseController(){
    // load data
    $(function loadData(){
        if(window.localStorage.getItem('loginResponse')!=null){
            var loginResponse = JSON.parse(localStorage.getItem("loginResponse"));
        }else{
            alert ("Login plz");
            window.location.replace("/login");
        }});
        var loginResponse = JSON.parse(localStorage.getItem("loginResponse"));
        $("#btn-account").click(function(){
                BaseController.prototype.AccountPageRequest(loginResponse);
        });
        $("#btn-popular").click(function(){
                BaseController.prototype.HomePageRequest(loginResponse);
        });
        $("#btn-newest").click(function(){
                BaseController.prototype.NewestPageRequest(loginResponse);
        });
        $("#btn-bestseller").click(function(){
                BaseController.prototype.BestSeller(loginResponse);
        });
        $("#btn-sale").click(function(){
                BaseController.prototype.SalePageRequest(loginResponse);
        });
}


BaseController.prototype.HomePageRequest =  function(loginResponse){
        $.ajax({
            method:"get",
            url: `http://localhost:8080/api/home`,
            headers:{
                "Authorization": `Bearer ${loginResponse.accessToken}`,
            }
        }).done(function(){
            window.location.replace(`/api/home`);
        })
        .fail(function(response){
            alert('Error');
        });
};
BaseController.prototype.SalePageRequest =  function(loginResponse){
        $.ajax({
            method:"get",
            url: `http://localhost:8080/api/sale/${loginResponse.id}`,
            headers:{
                "Authorization": `Bearer ${loginResponse.accessToken}`,
            }
        }).done(function(){
            window.location.replace(`/api/sale/${loginResponse.id}`);
        })
        .fail(function(response){
            alert('Error');
        });
};
BaseController.prototype.NewestPageRequest =  function(loginResponse){
        $.ajax({
            method:"get",
            url: `http://localhost:8080/api/newest`,
            headers:{
                "Authorization": `Bearer ${loginResponse.accessToken}`,
            }
        }).done(function(){
            window.location.replace(`/api/newest`);
        })
        .fail(function(response){
            alert('Error');
        });
};
BaseController.prototype.BestSeller =  function(loginResponse){
        $.ajax({
            method:"get",
            url: `http://localhost:8080/api/best_seller`,
            headers:{
                "Authorization": `Bearer ${loginResponse.accessToken}`,
            }
        }).done(function(){
            window.location.replace(`/api/best_seller`);
        })
        .fail(function(response){
            alert('Error');
        });
};
BaseController.prototype.AccountPageRequest =  function(loginResponse){
        $.ajax({
            method:"get",
            url: `http://localhost:8080/api/account/${loginResponse.id}`,
            headers:{
                "Authorization": `Bearer ${loginResponse.accessToken}`,
            }
        }).done(function(){
            window.location.replace(`/api/account/${loginResponse.id}`);
        })
        .fail(function(response){
            alert('Error');
        });
};

var baseController;
$(document).ready(function(){
      baseController = new BaseController();
})