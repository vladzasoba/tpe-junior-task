$(document).ready(function(){
    $('select').formSelect();
});

$(document).ready(function(){
    $.get("/api/customers", function(_data, status){
        window.autocompleteData = {};
        for (var i = 0; i < _data.length; i++) {
            var attr = _data[i]["firstName"] + " " + _data[i]["lastName"] + ": " + _data[i]["customerId"];
            autocompleteData[attr] = null;
        }
        console.log(autocompleteData);
        $('input.autocomplete').autocomplete({
            data: autocompleteData
        });
    });

});