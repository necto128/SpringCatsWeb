$('select#iddadcats').on('change', function () {
    $('input[name="idDad"]').val(this.value);
});
$('select#idmamcats').on('change', function () {
    $('input[name="idMam"]').val(this.value);
});

function x1() {
    document.getElementById("gender").value = $('input[name=radio1]:checked').val();
}