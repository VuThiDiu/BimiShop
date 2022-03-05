WebContext = {}

BaseController.prototype.showAlert = function (body_text) {
    $("#showDialogAlert #alert_body").text(body_text);
    $("#showDialogAlert").modal('show');
};

BaseController.prototype.showAlertFail = function (body_text) {
    $("#showDialogFailAlert #alertFail_body").text(body_text);
    $("#showDialogFailAlert").modal('show');
};
function BaseController(){
    WebContext.contextPath = /*[[@{/}]]*/'';
}

var baseController;
$(document).ready(function(){
    baseController = new BaseController();
})