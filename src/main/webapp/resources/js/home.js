function fire_ajax_submit(username, flowername) {
    let ordered = document.getElementById(flowername).value;
    // let elementsByClassName = document.getElementsByClassName("amount_value");
    let amount_elem = $('span[itemprop=' + flowername + ']');
    amount_elem.text(parseInt(amount_elem.text()) - parseInt(ordered));
    console.log(amount_elem.text());
    if ($(amount_elem).text() == '0') {
        //let button = document.getElementById("btn" + flowername);
        //button.prop("disabled", true);
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

// function gotoCart() {
//     let username = document.getElementById("username").textContent;
//     $.ajax({
//         type: "GET",
//         url: "/cart",
//         contentType: "application/json; charset=utf-8",
//         data: JSON.stringify(username),
//         dataType: "jsonp",
//         success: function (data) {
//             console.log("SUCCESS : ", data);
//         },
//         error: function (e) {
//             console.log("ERROR : ", e);
//         }
//     })
// }

$(function () {

    var elements = document.getElementsByClassName("flower_button");
    console.log(document.getElementById("username").textContent);

    //TODO
    //GET ALL BUTTON AND WHEN 0 -> DISABLE IT



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