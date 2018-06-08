<html>
<head>
    <link rel="stylesheet" href="/css/lib/materialize.min.css">
</head>
<body>
<nav>
    <div class="nav-wrapper">
        <a href="#" class="brand-logo">Logo</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="/customers">Customers</a></li>
            <li><a href="/transactions">Transactions</a></li>
        </ul>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col s12">
            <table class="highlight">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Customer Name</th>
                    <th>Amount</th>
                </tr>
                </thead>
                <tbody>
        <#list accounts as account>
        <tr>
            <td>${account.accountId}</td>
            <td>${account.customer.firstName} ${account.customer.lastName}</td>
            <td>$${account.amount}</td>
        </tr>
        </#list>
                </tbody>
            </table>
        </div>
    </div>

    <#assign tx_types=["Transfer", "Deposit", "Charge"]>

    <form action="/api/accounts" method="post">

    </form>

    <form class="col s12 z-depth-2" method="post" action="/api/transactions">
        <div class="row">
                <div class="input-field col s4">
                    <select>
                        <option value="" disabled selected>Choose transaction type</option>
                        <option value="1">Transfer</option>
                        <option value="2">Deposit</option>
                        <option value="3">Charge</option>
                    </select>
                    <label>Transaction type</label>
                </div>
                <div class="input-field col s4">
                    <select>
                        <option value="" disabled selected>Choose your account</option>
                    <#list accounts as account>
                    <option value="1">ID: ${account.accountId}</option>
                    </#list>
                    </select>
                    <label>Your account</label>
                </div>
        </div>
        <div class="row">
            <div class="input-field col s4">
                <input type="text" id="autocomplete-input" class="autocomplete">
                <label for="autocomplete-input">Account Holder</label>
            </div>
            <div class="input-field col s2">
                <input id="account_id" type="text" class="validate">
                <label for="account_id">Account ID</label>
            </div>
            <div class="col s2">
                <button class="btn waves-effect waves-light" type="submit" name="action">Submit
                    <i class="material-icons right">Perform</i>
                </button>
            </div>
        </div>
    </form>


    </div>
</div>


<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="/js/lib/materialize.min.js"></script>
<script src="js/main.js"></script>
<script>
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
</script>
</body>
</html>