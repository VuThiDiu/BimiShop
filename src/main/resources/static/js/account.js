function HomePageController(){
    // load data
    $(function loadData(){
        if(window.localStorage.getItem('loginResponse')!=null){
            var loginResponse = JSON.parse(localStorage.getItem("loginResponse"));
        }else{
            alert ("Login plz");
            window.location.replace("/login");
        }});
        var loginResponse = JSON.parse(localStorage.getItem("loginResponse"));
        $("#btn-logout").click(function(){
              localStorage.clear();
              window.location.replace("/login");
        });

}
HomePageController.prototype.callAPI =  function(loginResponse){
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

var homePageController;
$(document).ready(function(){
      homePageController = new HomePageController();
})