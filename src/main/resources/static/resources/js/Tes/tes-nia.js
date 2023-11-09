(function () {
    addDetailButtonListener();
    addCloseButtonListener();
} ())

function addCloseButtonListener() {
    $('.close-button').click(function(event) {
        event.preventDefault();
        $('.modal-layer').removeClass('modal-layer--opened');
        $('.popup-dialog').removeClass('popup-dialog--opened');
    });
}

function addDetailButtonListener() {
    $('.detail-button').click(function(event) {
        event.preventDefault();
        $.ajax({
            url: `/api/tes`,
            success:function(response) {
                let data = "";

                for(let i = 0; i < response.length; i++) {
                    data += `<td> ${response[i].username}</td>
                    <td> ${response[i].totalQuantity} </td>`
                }

                $('tr').html(data);

                $('.modal-layer').addClass('modal-layer--opened');
                $('.popup-dialog').addClass('popup-dialog--opened');
            }
        })
    })
}