function HomePageController(){
    $(function loadData(){
        if(window.localStorage.getItem('loginResponse')!=null){
            var loginResponse = JSON.parse(localStorage.getItem("loginResponse"));
//            $(".close").click(function(){
//                var id = $(this).attr('id');
//                var result = confirm("Want to delete?");
//                        if (result==true) {
//                            $.ajax({
//                                method: "Delete",
//                                url: "http://localhost:8080/todolist/"+id,
//                                headers:{
//                                    "Authorization": `Bearer ${loginResponse.accessToken}`,
//                                }
//                            }).done(function(){
//                                alert("Delete successfully");
//                            }).fail(function(){
//
//                            })
//                        } else {
//
//                        }
//
            });
}

var homePageController;
$(document).ready(function(){
      homePageController = new HomePageController();
})