function HomePageController(){
    // load data
    $(function loadData(){
        if(window.localStorage.getItem('loginResponse')!=null){
            var loginResponse = JSON.parse(localStorage.getItem("loginResponse"));
            var userId = $("#userId").val();
                    $("#btn-logout").click(function(){
                          localStorage.clear();
                          window.location.replace("/login");
                    });
                    $("#btn-myProduct").click(function(){
                        HomePageController.prototype.myProduct(loginResponse, userId);
                    })
        }else{
            alert ("Login plz");
            window.location.replace("/login");
        }});
}


HomePageController.prototype.myProduct =  function(loginResponse, userId){
        $.ajax({
            type:"get",
            url: `/api/myProduct/${userId}`,
            headers:{
                "Authorization": `Bearer ${loginResponse.accessToken}`,
            }
        }).done(function(){
            window.location.replace(`/api/myProduct/${userId}`);
        })
        .fail(function(response){
            alert('Error');
        });
};

var homePageController;
$(document).ready(function(){
      homePageController = new HomePageController();
})