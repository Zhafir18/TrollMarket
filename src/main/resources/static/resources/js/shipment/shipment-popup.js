(function () {
    addSubmitButtonListener();
    addUpdateButtonListener();
    addCloseButtonListener();
    addInsertButtonListener();
} ());

function addCloseButtonListener() {
    $('.close-button').click(function(event) {
        $('.modal-layer').removeClass('modal-layer--opened');
        $('.popup-dialog').removeClass('popup-dialog--opened');
    });
}

function addInsertButtonListener() {
    $('.create-button').click(function(event) {
        $('.modal-layer').addClass('modal-layer--opened');
        $('.form-dialog').addClass('popup-dialog--opened');
    });
}

function addUpdateButtonListener() {
    $('.update-button').click(function(event) {
        let id = $(this).attr("data-id");
        $.ajax({
            url: `/api/shipment/failed/${id}`,
            success: function(response) {
                $.ajax({
                    url: `/api/shipment/${id}`,
                    success: function(response) {
                        $('.modal-layer').addClass('modal-layer--opened');
                        $('.form-dialog').addClass('popup-dialog--opened');
                        $('.form-dialog .id').val(response.id);
                        $('.form-dialog .name').val(response.name);
                        $('.form-dialog .price').val(response.price);
                        $('.form-dialog .isService').val(response.isService);
                    }
                })
            },
            error: function({responseText}) {
                $('.dependencies').text(responseText);
                $('.modal-layer').addClass('modal-layer--opened');
                $('.failed-dialog').addClass('popup-dialog--opened');
            }
        });
    })
}


function addSubmitButtonListener() {
    $('.form-dialog button').click(function(event) {
        event.preventDefault();
        let id = $('.form-dialog .id').val();

            let dto = {
                id: (id === null) ? null : id,
                name: $('.form-dialog .name').val(),
                price: $('.form-dialog .price').val(),
                isService: $('.form-dialog .isService').val()
            }

            let methodType = (dto.id === null) ? "POST" : "PUT";

            $.ajax({
                method: methodType,
                url: "/api/shipment",
                data: JSON.stringify(dto),
                contentType: "application/json",
                success: function(response) {
                    location.reload();
                },
                error: function({status, responseJSON}) {
                    if(status === 422) {
                        writeValidationMessage(responseJSON);
                    }
                }
            })
    })
}

function writeValidationMessage(errorMessage) {
        for(let error of errorMessage) {
            let {field, message} = error;
            $(`.form-dialog [data-for=${field}]`).text(message);
        }
    }