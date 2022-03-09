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
                                    // read image
                                    let reader = new FileReader();
                                    let figure = document.createElement("figure");
                                    let figCap = document.createElement("h4");
                                    figCap.classList.add("text-center");
                                    figure.appendChild(figCap);
                                    reader.onload = () =>{
                                        let img = document.createElement("img");
                                        img.setAttribute("src", reader.result);
                                        figure.insertBefore(img, figCap);
                                    }
                                    imageContainer.appendChild(figure);
                                    reader.readAsDataURL(i);

                                    let formData = new FormData();
                                    formData.append("file", i);
                                    SellingController.prototype.AutoTaggingAPI(formData, loginResponse, figCap);
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

SellingController.prototype.AutoTaggingAPI = function(fileInput, loginResponse, figCap){
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
            figCap.innerText = response;
        })
        .fail(function(response){
            alert("fail to upload image");
        });
}

var sellingController;
$(document).ready(function(){
    sellingController = new SellingController();
})


