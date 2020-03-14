function fire_ajax_submit(username, flowername) {
    let amount = document.getElementById(flowername).value;

    var JSONObject = {
        'username': username,
        'flowername': flowername,
        'amount': amount
    };

    $.ajax({
        type: "PUT",
        url: "/home",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(JSONObject),
        success: function (data) {
            console.log("SUCCESS : ", data);
            $("#btndandelion").prop("disabled", true);
        },
        error: function (e) {
            console.log("ERROR : ", e);
            $("#btnbuttercup").prop("disabled", true);
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