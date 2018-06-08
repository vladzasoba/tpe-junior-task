var dataJSON = {};
$(document).ready(function () {
    $.get("/api/transactions", function(data, status){
        window.darr = [];
        console.log(data);

        for (var i = 0; i < data.length; i++) {
            darr[i] = [];
            darr[i].push(data[i]["srcAccount"]["customer"]["firstName"] + " "
                + data[i]["srcAccount"]["customer"]["lastName"]);
            darr[i].push(data[i]["srcAccount"]["accountId"]);

            if ( data[i]["dstAccount"]) {
                darr[i].push(data[i]["dstAccount"]["customer"]["firstName"] + " "
                    + data[i]["dstAccount"]["customer"]["lastName"]);
                darr[i].push(data[i]["dstAccount"]["accountId"])
            } else {
                darr[i].push(" ");
                darr[i].push(" ");
            }
            darr[i].push("$" + data[i]["amount"]);
            darr[i].push(new Date(data[i]["transacationDate"]).toDateString());
            darr[i].push(data[i]["transactionType"]);
        }

        console.log(darr);

        var table = $('#tx-tables').DataTable({
            data: darr,
            columns: [
                { title: "FROM" },
                { title: "FROM ID" },
                { title: "TO" },
                { title: "TO ID" },
                { title: "AMOUNT" },
                { title: "DATE" },
                {title: "TYPE"}
            ]
        });
    });
});
