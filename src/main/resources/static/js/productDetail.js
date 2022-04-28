function ProductDetailController(){
    // load data
    $(function loadData(){
        if(window.localStorage.getItem('loginResponse')!=null){
            var loginResponse = JSON.parse(localStorage.getItem("loginResponse"));
            $("#btn-account").click(function(){
                    ProductDetailController.prototype.AccountPageRequest(loginResponse);
            });
            $("#btn-popular").click(function(){
                    ProductDetailController.prototype.HomePageRequest(loginResponse);
            });
            $("#btn-newest").click(function(){
                    ProductDetailController.prototype.NewestPageRequest(loginResponse);
            });
            $("#btn-bestseller").click(function(){
                    ProductDetailController.prototype.BestSeller(loginResponse);
            });
            $("#btn-sale").click(function(){
                    ProductDetailController.prototype.SalePageRequest(loginResponse);
            });
            $(".image_preview").onClick


        }else{
            alert ("Login plz");
            window.location.replace("/login");
        }});

}
ProductDetailController.prototype.HomePageRequest =  function(loginResponse){
        $.ajax({
            method:"get",
            url: `/api/home`,
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
ProductDetailController.prototype.SalePageRequest =  function(loginResponse){
        $.ajax({
            method:"get",
            url: `/api/sale`,
            headers:{
                "Authorization": `Bearer ${loginResponse.accessToken}`,
            }
        }).done(function(){
            window.location.replace(`/api/sale`);
        })
        .fail(function(response){
            alert('Error');
        });
};
ProductDetailController.prototype.NewestPageRequest =  function(loginResponse){
        $.ajax({
            method:"get",
            url: `/api/newest`,
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
ProductDetailController.prototype.BestSeller =  function(loginResponse){
        $.ajax({
            method:"get",
            url: `/api/best_seller`,
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
ProductDetailController.prototype.AccountPageRequest =  function(loginResponse){
        $.ajax({
            method:"get",
            url: `/api/account/${loginResponse.id}`,
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
var productDetailController;

$(document).ready(function(){
      productDetailController = new ProductDetailController();
})