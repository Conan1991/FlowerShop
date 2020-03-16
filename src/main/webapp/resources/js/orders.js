function pay_for_order(username, orderId) {

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
        success: function (msg) {
            console.log(msg.data);
        }
    });
}