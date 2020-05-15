function s1() {
    $('input:radio:checked').each(function () {
        document.getElementById("delete").value = $(this).val();
        document.getElementById("id_cat").value = $(this).val();
    })
}

function check() {

    if (confirm('Вы уверены?') == false) {
        return false;
    }
}

function shipment() {
    $('input:radio:checked').each(function () {
        document.getElementById("delete").value = $(this).val();
        document.getElementById("id_cat").value = $(this).val();
        var id = (String)($(this).val());
        var name = (String)("name_cat" + id);
        var dad = (String)("id_dad" + id);
        var mam = (String)("id_mam" + id);
        var gen = (String)("gender" + id);

        document.getElementById("name_cat").value = document.getElementById(name).value;
        document.getElementById("id_dad").value = document.getElementById(dad).value;
        document.getElementById("id_mam").value = document.getElementById(mam).value;
        document.getElementById("gender").value = document.getElementById(gen).value;
    })
};