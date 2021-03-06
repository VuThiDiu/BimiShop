

function SellingController(){
    $(function loadData(){
            if(window.localStorage.getItem('loginResponse')!=null){
                var loginResponse = JSON.parse(localStorage.getItem("loginResponse"));

                // load for autoTaggingSystemToken
                var urlPath = $('#autoTaggingSystemPath').val();
                let fileInput = document.getElementById("file-input");
                let imageContainer = document.getElementById("images");
                let numOfFiles = document.getElementById("num-of-files");
                $("#file-input").on('change', function(){
                    if(fileInput.files.length <= 10){
                        imageContainer.innerHtml = "";
                                numOfFiles.textContent = `${fileInput.files.length} Files Selected`;
                                for (i of fileInput.files){
                                    let reader = new FileReader();
                                    let figure = document.createElement("div");
                                    figure.classList.add("image_tag");
                                    let id = new Date().getTime();
                                    figure.setAttribute("id", id);
                                    let cite = document.createElement("cite");
                                    cite.classList.add("cite");
                                    let figCap = document.createElement("h4");
                                    let figCapColor = document.createElement("label");
                                    figCap.classList.add("text-center");
                                    figCapColor.classList.add("color_tag");
                                    cite.appendChild(figCap);
                                    cite.appendChild(figCapColor);
                                    $("<div class=\"option\"> "+
                                                                       "<span class=\"button_edit\"  data-toggle=\"modal\"  data-target=\"#exampleModal\" ><i class=\"fa fa-pencil-square-o fa-lg\"></i></span> "+
                                                                       "<span class=\"button_delete\" data-toggle=\"tooltip\" data-placement=\"bottom\"><i class=\"fa fa-trash-o fa-lg\"></i></span> "+
                                                                   "</div>").insertAfter(figCapColor);
                                    // option for edit and remove
                                    let img = document.createElement("img");
                                    reader.onload = () =>{
                                        img.setAttribute("src", reader.result);
                                        img.classList.add('center');
                                        figure.appendChild(img);
                                        figure.appendChild(cite);
                                    }
                                    imageContainer.appendChild(figure);
                                    reader.readAsDataURL(i);
//                                    imageURL = URL.createObjectURL(i);
//                                    $("#preview").attr("src", imageURL);
                                    let formData = new FormData();
                                    formData.append("file", i);
                                    SellingController.prototype.AutoTaggingAPI(formData, loginResponse, urlPath, img,  figCap, figCapColor);
                            }
                    }else{
                        alert("At most 10 files at times");
                    }

                });
                        $("button.save_change").on("click", function (){
                                var tagCategoryChange = $("#edit_tagCategory :selected").val();
                                var tagColorChange = "";
                                var id = $(".modal-body#id").val();
                                let spanDot = document.querySelectorAll("span.dot");
                                if(spanDot.length > 0){
                                    var tagColorChange = spanDot[0].parentNode.getElementsByTagName("label")[0].style.color;
                                    SellingController.prototype.saveChangeTag1(id, tagCategoryChange, tagColorChange);
                                }else{
                                    SellingController.prototype.saveChangeTag2(id, tagCategoryChange);
                                }
                                SellingController.prototype.tagController();
                            }
                            );

                        $("button#saveButton").on("click", function(){
                            if(SellingController.prototype.validateForm() == true){
                                // all information about product
                               let description = $("#description").val();
                               let quantityInStock = parseInt($("#quantityInStock").val());
                               let cost = parseInt($("#cost").val());
                               let address = $("#address").val();
                               let discount = parseInt($("#discount").val());
                               let userId = loginResponse.id;
                               let title = $("#title").val();
                               var uploadProduct ={
                                   "userId" : userId,
                                   "description" : description,
                                   "quantityInStock" : quantityInStock,
                                   "price" : cost,
                                   "dateTime" : new Date(),
                                   "discount" : discount,
                                   "address" : "Ha Noi",
                                   "title": title
                               }
                               SellingController.prototype.uploadProduct(loginResponse, uploadProduct);
                            }
                        });
            }else{
                alert ("Login plz");
                window.location.replace("/login");
            }});


}
SellingController.prototype.uploadProduct = function(loginResponse, uploadProduct){
    $.ajax({
                method:"post",
                url: `/api/upload_product`,
                headers:{
                    "Authorization": `Bearer ${loginResponse.accessToken}`,
                },
                data:JSON.stringify(uploadProduct),
                dataType:"json",
                contentType: 'application/json',
            })
            .fail(function(response){
                if(response.status==200){
                    let productId = response.responseText;
                    let allImage = document.images;
                    var images = [];
                    for(image of allImage){
                        let dataUrl = image.src;
                        let node = image.parentNode.getElementsByClassName("cite")[0];
                        let tagCategory = node.getElementsByClassName("text-center")[0].innerText;
                        let tagColor = node.getElementsByClassName("color_tag")[0].style.backgroundColor;
                        var uploadImage ={
                           "imageUrl" : dataUrl,
                           "productId" : productId,
                           "tagCategory" : tagCategory,
                           "tagColor" : tagColor
                       }
                       images.push(uploadImage);
                    }
                    SellingController.prototype.uploadImage(loginResponse, images);
                }else{
                    alert("Error");
                }
            });
}

SellingController.prototype.uploadImage = function(loginResponse, uploadImage){
    $.ajax({
                method:"post",
                url: `/api/upload_image`,
                headers:{
                    "Authorization": `Bearer ${loginResponse.accessToken}`,
                },
                data:JSON.stringify(uploadImage),
                dataType:"json",
                contentType: 'application/json',
            })
            .fail(function(response){
                if(response.status == 200){
                    alert("create product successfully");
                    var productId = response.responseText;
                    SellingController.prototype.productDetail(loginResponse, productId);
                }else{
                    alert("fail to upload images");
                }

            });
}
SellingController.prototype.productDetail = function(loginResponse, productId){
    $.ajax({
        type:"get",
        url: `/api/detail/${productId}`,
        headers:{
            "Authorization": `Bearer ${loginResponse.accessToken}`,
        }
    }).done(function(){
        window.location.replace(`/api/detail/${productId}`);
    })
    .fail(function(response){
        alert('Error');
    });
}
SellingController.prototype.validateForm = function(){
    if(document.querySelectorAll(".image_tag").length <= 0){
        alert("Missing photos");
        return false;
    }
    if($("#description").val() == ""){
        alert("Missing Description");
        return false;
    }
    if($("#quantityInStock").val() == ""){
        alert("Missing QuantityInStock");
        return false;
    }
    if($("#cost").val() == ""){
        alert("Missing Cost");
        return false;
    }
    return true;
}
SellingController.prototype.saveChangeTag1 = function(id, category, color){
       let node = document.getElementById(`${id}`).getElementsByClassName('cite')[0];
       node.getElementsByClassName("text-center")[0].textContent = category;
       node.getElementsByClassName("color_tag")[0].style.backgroundColor = color;
       $('#exampleModal').modal('hide');
}

SellingController.prototype.saveChangeTag2 = function(id, category){
       let node = document.getElementById(`${id}`).getElementsByClassName('cite')[0];
       node.getElementsByClassName("text-center")[0].textContent = category;
       $('#exampleModal').modal('hide');
}
// reload remove and edit button
SellingController.prototype.buttonReload = function(){
        let deleteButton = document.querySelectorAll("span.button_delete");
            deleteButton.forEach(button =>  {
                button.addEventListener('click', function(){
                    this.parentNode.parentNode.parentNode.remove();
                    SellingController.prototype.tagController();
                })
            })
            let editButton = document.querySelectorAll("span.button_edit");
            editButton.forEach(button =>  {
                button.addEventListener('click', function(){
                let re = document.querySelectorAll(".dot");
                    for(item of re){
                        item.remove();
                    }
                    let node = this.parentNode.parentNode.parentNode;
                    let category = node.getElementsByClassName('text-center')[0].textContent;
                    let id = node.id;
                    $(".modal-body #edit_tagCategory").val(category);
                    $(".modal-body#id").val(id);
                })
            });

            let colorButton = document.querySelectorAll(".shop-filter__color");
            colorButton.forEach(button => {
                button.addEventListener('click', function(){
                    let re = document.querySelectorAll(".dot");
                    for(item of re){
                        item.remove();
                    }
                    let node = this.getElementsByTagName("label")[0];
                    let color = node.style.color;
                    $("<span class=\"dot\"></span>").insertAfter(node);
                })
            })
}

// update when upload  or  edit tag
SellingController.prototype.tagController = function(){
       var cites = document.getElementsByTagName("cite");
       let tagCategories = document.getElementById("categories");
       tagCategories.textContent="";
       let tagColors = document.getElementById("colors");
       tagColors.textContent = "";
       var tagColorInit = new Set();
       var tagCategoryInit = new Set();
       for(cite of cites){
          tagCategoryInit.add(cite.childNodes[0].textContent);
          tagColorInit.add(cite.childNodes[1].style.backgroundColor);
       }

       for(category of tagCategoryInit){
           let tagCategory = document.createElement("a");
           tagCategory.classList.add("badge");
           tagCategory.setAttribute("href", "javascript:void();");
           tagCategory.innerText = category;
           tagCategories.appendChild(tagCategory);
       }
       for(color of tagColorInit){
            let labelColor = document.createElement("label");
           labelColor.classList.add("shop-filter__color");
           labelColor.style.backgroundColor = color;
           if(color == "white") labelColor.classList.add("color-white");
           tagColors.appendChild(labelColor);
       }
       }

// using json
SellingController.prototype.AutoTaggingAPI = function(fileInput, loginResponse,urlPath , image,  figCap, figCapColor){
    $.ajax({
            method:"post",
            url: urlPath,
            headers:{
                "Authorization": `Bearer ${loginResponse.accessTokenAutomaticTaggingSystem}`,
                "Accept" : "application/json"
            },

            data:fileInput,
            processData: false,
            contentType: false,
            success : function(response){
                    figCap.innerText = response.tagCategory;
                    color = getColor(response.tagColor);
                    image.src = response.imageURL;
//                    $("#preview").attr("src", response.imageURL);
                    figCapColor.style.backgroundColor = color;
                    SellingController.prototype.tagController();
                    SellingController.prototype.buttonReload();
            },
            error: function(e){
                    alert("fail to upload image");
                    SellingController.prototype.buttonReload();
            }
        })
}

//
function getColor(color){
    switch (color){
        case "tr???ng" : return "white";
        case "h???ng" : return "pink";
        case "?????" : return "red";
        case "v??ng" : return "yellow";
        case "xanh d????ng" : return "blue";
        case "x??m" : return "gray";
        case "??en" : return "black";
        case "xanh l??" : return "green";
    }
}


var sellingController;
$(document).ready(function(){
    sellingController = new SellingController();

})




