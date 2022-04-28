function BaseController(){
    // load data
    $(function loadData(){
        if(window.localStorage.getItem('loginResponse')!=null){
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
            var color = null;
            var category = null;
            var priceBtn = document.forms[0];
            for (let i = 0 ; i< priceBtn.length; i++){
                priceBtn[i].addEventListener('click', function(){
                    if(i == 3){
                        priceBtn[4].disabled =  false;
                        priceBtn[5].disabled =  false;
                    }
                    if(i < 3){
                        priceBtn[4].disabled =  true;
                        priceBtn[5].disabled =  true;
                    }
                    if(i>=13){
                        priceBtn[13].nextElementSibling.style.border = "1px solid #e8caca";
                        for(let i = 14; i < priceBtn.length; i++){
                            priceBtn[i].nextElementSibling.style.border = "none";
                        }
                        priceBtn[i].nextElementSibling.style.border = "2px solid #337ab7";
                        color = priceBtn[i].defaultValue;
                    }

                })
            }
            var costFrom = 0;
            var costTo = 0;
            $("button.search").on("click", function(){
                var price = $('input[name="shop-filter__price"]:checked').val();
                if($('input[name="shop-filter__radio"]:checked').length > 0)
                 category = $('input[name="shop-filter__radio"]:checked').val();
                switch (price){
                    case "<100" : costTo = 100000 ; break;
                    case "100<x<200" : costFrom = 100000; costTo = 200000; break;
                    case "200<x<500" : costFrom = 200000; costTo = 500000; break;
                    case "specify" : costFrom = $("#shop-filter-price_from").val();
                                     costTo = $("#shop-filter-price_to").val();
                }
                if (category == null && costTo == 0 && color == null){
                    alert("Không có thông tin tìm kiếm");
                }else {
                    window.location.replace(`home?costFrom=${costFrom}&costTo=${costTo}&category=${category}&color=${color}`);
                }
            })
        }else{
            alert ("Login plz");
            window.location.replace("/login");
        }});

}
BaseController.prototype.HomePageRequest =  function(loginResponse){
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
BaseController.prototype.SalePageRequest =  function(loginResponse){
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
BaseController.prototype.NewestPageRequest =  function(loginResponse){
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
BaseController.prototype.BestSeller =  function(loginResponse){
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
BaseController.prototype.AccountPageRequest =  function(loginResponse){
        $.ajax({
            method:"get",
            url: `account/${loginResponse.id}`,
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