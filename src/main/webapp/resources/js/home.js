function fire_ajax_submit(username, flowername) {
    let ordered = document.getElementById(flowername).value;
    // let elementsByClassName = document.getElementsByClassName("amount_value");
    let amount_elem = $('span[itemprop=' + flowername + ']');
    if (parseInt(amount_elem.text()) - parseInt(ordered) >= 0)
        amount_elem.text(parseInt(amount_elem.text()) - parseInt(ordered));
    console.log(amount_elem.text());
    if ($(amount_elem).text() == '0') {
        let button = $('#btn' + flowername);
        button.prop("disabled", true);
        console.log(button.text());
    }

    var JSONObject = {
        'username': username,
        'flowername': flowername,
        'amount': ordered
    };

    $.ajax({
        type: "PUT",
        url: "/home",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(JSONObject),
        dataType: "json",
        success: function (data) {
            if (data.success == false)
                alert(data.response)
            console.log("SUCCESS : ", data.success);
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    })
}


function addDeposit(username) {
    let dep = $("#deposit_amount").val();

    var JSONObject = {
        'username': username,
        'entered': dep
    };

    $.ajax({
        type: "POST",
        url: "/putOnBalance",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(JSONObject),
        success: function (data) {
            $("#balance").text(data.balance);
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    })
}

function closeSession() {

    console.log('session is closing...');

    $.ajax({
        type: "GET",
        url: "/closeSession",
        success: function (data) {
            console.log("SUCCESS : ", data);
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    })
}

$(function () {

    var elements = document.getElementsByClassName("flower_button");
    console.log(document.getElementById("username").textContent);


    // for (let i = 0; i < elements.length; i++) {
    //     var button = elements[i];
    //     $(button).onclick = function (event) {
    //         alert('Спасибо!');
    //         flowername = button.id.substr(3);
    //
    //         amount = document.getElementById(flowername).value;
    //         //stop submit the form, we will post it manually.
    //         event.preventDefault();
    //         fire_ajax_submit(username, flowername, amount);
    //     };
    // }
});

$(function () {
    $("#mimosa").on("change", function () {
        var val = this.value;
        if ($(this).val() == "5") {
            alert('Value is 5');
        }
        $("#test_value").text($(this).val());

    });


});