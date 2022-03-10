function SellingController(){
    $(function loadData(){
            if(window.localStorage.getItem('loginResponse')!=null){
                var loginResponse = JSON.parse(localStorage.getItem("loginResponse"));

                let fileInput = document.getElementById("file-input");
                let imageContainer = document.getElementById("images");
                let numOfFiles = document.getElementById("num-of-files");

                $("#file-input").on('change', function(){
                // limit file upload
                    if(fileInput.files.length <= 10){
                        imageContainer.innerHtml = "";
                                numOfFiles.textContent = `${fileInput.files.length} Files Selected`;
                                for (i of fileInput.files){
                                    let reader = new FileReader();
                                    let figure = document.createElement("div");
                                    figure.classList.add("image_tag");

                                    let cite = document.createElement("cite");
                                    let figCap = document.createElement("h4");
                                    let figCapColor = document.createElement("label");
                                    figCap.classList.add("text-center");
                                    figCapColor.classList.add("color_tag");

                                    cite.appendChild(figCap);
                                    cite.appendChild(figCapColor);

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
                })
            }else{
                alert ("Login plz");
                window.location.replace("/login");
            }});
}

SellingController.prototype.AutoTaggingAPI = function(fileInput, loginResponse, figCap, figCapColor){
    $.ajax({
            method:"post",
            url: "http://localhost:8080/api/upload_image",
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

        })
        .fail(function(response){
            alert("fail to upload image");
        });
}

var sellingController;
$(document).ready(function(){
    sellingController = new SellingController();
})


