function ProductDetailController(){
    // load data
    $(function loadData(){
        if(window.localStorage.getItem('loginResponse')!=null){
            var loginResponse = JSON.parse(localStorage.getItem("loginResponse"));
            var id = $("#productId").val();
            ProductDetailController.prototype.UpdateViewImage(localStorage, id);

        }else{
            alert ("Login plz");
            window.location.replace("/login");
        }});

}
ProductDetailController.prototype.UpdateViewImage = function(localStorage, productId){
        $.ajax({
            method:'put',
            url : `http://localhost:8080/api/view?id=${productId}`
        })
}
var productDetailController;

$(document).ready(function(){
      productDetailController = new ProductDetailController();
})