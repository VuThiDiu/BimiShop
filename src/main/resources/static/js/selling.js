function SellingController(){
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
                        let figure = document.createElement("figure");
                        let figCap = document.createElement("figcaption");
                        figCap.innerText = i.name;
                        figure.appendChild(figCap);
                        reader.onload = () =>{
                            let img = document.createElement("img");
                            img.setAttribute("src", reader.result);
                            figure.insertBefore(img, figCap);
                        }
                        imageContainer.appendChild(figure);
                        reader.readAsDataURL(i);
                    }
        }else{
            alert("At most 10 files at times");
        }
    })
}

var sellingController;
$(document).ready(function(){
    sellingController = new SellingController();
})


