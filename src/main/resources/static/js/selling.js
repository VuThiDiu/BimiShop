function SellingController(){
    $(function loadData(){
            if(window.localStorage.getItem('loginResponse')!=null){
                var loginResponse = JSON.parse(localStorage.getItem("loginResponse"));
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

                                    reader.onload = () =>{
                                        let img = document.createElement("img");
                                        img.setAttribute("src", reader.result);
                                        img.classList.add('center');
                                        figure.appendChild(img);
                                        figure.appendChild(cite);
                                    }
                                    imageContainer.appendChild(figure);
                                    reader.readAsDataURL(i);
                                    let formData = new FormData();
                                    formData.append("file", i);
                                    SellingController.prototype.AutoTaggingAPI(formData, loginResponse, figCap, figCapColor);
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
                                // upload image into firebase

                            }

                        })
            }else{
                alert ("Login plz");
                window.location.replace("/login");
            }});


}
SellingController.prototype.validateForm = function(){
    if(document.querySelectorAll("image_tag").length <= 0){
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
//                    let color = node.getElementsByClassName("color_tag")[0].style.backgroundColor;
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
           tagColors.appendChild(labelColor);
       }
       }
SellingController.prototype.AutoTaggingAPI = function(fileInput, loginResponse, figCap, figCapColor){
    $.ajax({
            method:"post",
            url: "http://localhost:8080/api/get_tagImage",
            headers:{
                "Authorization": `Bearer ${loginResponse.accessToken}`,
            },
            data:fileInput,
            processData: false,
            contentType: false
        })
        .done(function(response){
            figCap.innerText = response.tagCategory;
            figCapColor.style.backgroundColor = response.tagColor;
            SellingController.prototype.tagController();
            SellingController.prototype.buttonReload();
        })
        .fail(function(response){
            alert("fail to upload image");
            SellingController.prototype.buttonReload();
        });

}

var sellingController;
$(document).ready(function(){
    sellingController = new SellingController();

})




