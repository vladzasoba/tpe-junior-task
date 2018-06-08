function deleteCustomer(customerId) {
    console.log("deleting");
    $.ajax({
            url: `/api/customers/${customerId}`,
            type: 'DELETE',
            success: function () {
                window.location.href = `${window.location.origin}/customers`;
            }
    });
}

function addCustomer() {
    $.post("demo_test.asp", function(data, status){

    });
}
