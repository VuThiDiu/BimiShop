function HomePageController(){
    // load data
    $(function loadData(){
        if(window.localStorage.getItem('loginResponse')!=null){
            var loginResponse = JSON.parse(localStorage.getItem("loginResponse"));
        }else{
            alert ("Login plz");
            window.location.replace("/login");
        }});

}

var homePageController;
$(document).ready(function(){
      homePageController = new HomePageController();
})