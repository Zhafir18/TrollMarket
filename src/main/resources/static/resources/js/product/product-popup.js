(function () {
    addDetailButtonListener();
    addCloseButtonListener();
    addMerchDetailButtonListener();
    addSubmitFormListener();
    addInsertButtonListener();
} ())

function addCloseButtonListener() {
    $('.close-button').click(function(event) {
        event.preventDefault();
        $('.modal-layer').removeClass('modal-layer--opened');
        $('.popup-dialog').removeClass('popup-dialog--opened');
    });
}

let rupiah = new Intl.NumberFormat('id-ID', {
    style: 'currency',
    currency: 'IDR'
})

function addDetailButtonListener() {
    $('.detail-button').click(function(event) {
        let id = $(this).attr('data-id');
        $.ajax({
            url: `/api/product/detail/${id}`,
            success:function(response) {
                $('.modal-layer').addClass('modal-layer--opened');
                $('.detail-dialog').addClass('popup-dialog--opened');
                $('.detail-dialog .name').text(response.name);
                $('.detail-dialog .category').text(response.category);
                $('.detail-dialog .description').text(response.description);
                $('.detail-dialog .price').text(rupiah.format(response.price));
                $('.detail-dialog .sellerName').text(response.sellerName);
            }
        })
    })
}
function addMerchDetailButtonListener() {
    $('.merch-button').click(function(event) {
        let id = $(this).attr('data-id');
        $.ajax({
            url: `/api/product/merchDetail/${id}`,
            success:function(response) {
                $('.modal-layer').addClass('modal-layer--opened');
                $('.merch-dialog').addClass('popup-dialog--opened');
                $('.merch-dialog .name').text(response.name);
                $('.merch-dialog .category').text(response.category);
                $('.merch-dialog .description').text(response.description);
                $('.merch-dialog .price').text(rupiah.format(response.price));
                $('.merch-dialog .isDiscontinue').text(response.isDiscontinue);
            }
        })
    })
}
function addInsertButtonListener(){
        $('.cart-button').click(function(event){
            let productId = $(this).attr('data-id');
            $('.cart-dialog .productId').val(productId);
            $('.modal-layer').addClass('modal-layer--opened');
            $('.cart-dialog').addClass('popup-dialog--opened');
        })
}

function addSubmitFormListener(){
        $('#insert').click(function(event){
            event.preventDefault();
            let dto = {
                cartId: null ,
                productId: $('.cart-dialog .productId').val(),
                buyerUsername: $('.cart-dialog .buyerUsername').val(),
                shipmentId: $('.cart-dialog .shipmentId').val(),
                quantity: $('.cart-dialog .quantity').val()
                        };
            $.ajax({
                method: 'POST',
                url: '/api/product/addToCart',
                data: JSON.stringify(dto),
                contentType: 'application/json',
                success: function(response){
                    location.reload();
                },
                error: function({status, responseJSON}){
                    if(status === 422){
                        writeValidationMessage(responseJSON);
                    }
                }
            });
        })
    }

function writeValidationMessage(errorMessages){
        for(let error of errorMessages){
            let {field, message} = error;
            $(`.cart-dialog [data-for=${field}]`).text(message);
        }

    }