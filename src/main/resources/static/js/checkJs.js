$('select#iddadcats').on('change', function () {
    $('input[name="idDad"]').val(this.value);
});
$('select#idmamcats').on('change', function () {
    $('input[name="idMam"]').val(this.value);
});

function check() {

    if (confirm('Вы уверены?') == false) {
        return false;
    } else {
        return true
    }
}
function x1() {
    document.getElementById("id").value = document.getElementById("idcat").value;
    document.getElementById("delete").value = document.getElementById("idcat").value;
    document.getElementById("name").value = document.getElementById("nameCat").value;
    document.getElementById("idad").value = document.getElementById("idDad").value;
    document.getElementById("imam").value = document.getElementById("idMam").value;
}