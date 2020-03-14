function validate() {
    var a = document.getElementById("a");
    var b = document.getElementById("b");
    var c = document.getElementById("a").value;
    var d = document.getElementById("b").value;
    var valid = true;
    if (a.value.length <= 0 || b.value.length <= 0) {
        alert("Don't leave the field empty!");
        valid = false;
    } else {
        if (isNaN(c) || isNaN(d)) {
            alert("Enter a number");
            valid = false;
        }
    }
    return valid;
};
			