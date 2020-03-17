function pay_for_order(username, orderId) {
    var JSONObject = {
        'orderId': orderId
    };

    $.ajax({
        type: "POST",
        url: "/procedurePay",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(JSONObject),
        dataType: "json",
        success: function (data) {
            if(data.success == true) {
                let button = $('#btn' + orderId);
                button.prop("disabled", true);
                button.text('PAYED');
            } else {
                alert('')
            }

        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    })

}


function changeOrderStatus(id, orderStatus) {
    var JSONObject = {
        'id': id,
        'status': orderStatus
    };

    $.ajax({
        type: "POST",
        url: "/applyStatus",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(JSONObject),
        dataType: "json",
        success: function (object) {
            let closedate = $('span[itemprop=' + object.id + ']');
            closedate.text(object.date);
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}