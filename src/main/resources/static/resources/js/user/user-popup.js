(function () {
    addCloseButtonListener();
    addSubmitButtonListener();
    addTopupButtonListener();
} ());

function addCloseButtonListener() {
    $('.close-button').click(function(event) {
        $('.modal-layer').removeClass('modal-layer--opened');
        $('.popup-dialog').removeClass('popup-dialog--opened');
    });
}

function addTopupButtonListener() {
    $('.topup-button').click(function(event) {
        $('.modal-layer').addClass('modal-layer--opened');
        $('.popup-dialog').addClass('popup-dialog--opened');
    });
}

function addSubmitButtonListener() {
    $('.topup-dialog button').click(function(event) {
        event.preventDefault();

        let dto = {
            username: $('.topup-dialog .username').val(),
            topup: $('.topup-dialog .topup').val()
        }

        $.ajax({
            method: "POST",
            url: "/api/account/topup",
            data: JSON.stringify(dto),
            contentType: "application/json",
            success: function(response) {
                location.reload();
            },
            error: function({status, responseJSON}){
                if(status === 422){
                    writeValidationMessage(responseJSON);
                }
            }
        })
    })
}

function writeValidationMessage(errorMessages){
    for(let error of errorMessages){
        let {field, message} = error;
        $(`.topup-dialog [data-for=${field}]`).text(message);
    }
}