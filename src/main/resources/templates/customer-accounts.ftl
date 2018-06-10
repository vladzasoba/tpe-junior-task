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
                <#if accounts??>
        <#list accounts as account>
        <#assign customerId = account.customer.customerId>
        <tr>
            <td>${account.accountId}</td>
            <td>${account.customer.firstName} ${account.customer.lastName}</td>
            <td>$${account.amount}</td>
        </tr>
        </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </div>


        <div class="row">
            <form class="col s21 z-depth-2" action="/api/accounts/" method="post">
                <input type="hidden" value="${customerId}" name="customer_id"/>
                <input type="hidden" value="0" name="amount"/>
                <button class="btn waves-effect waves-light" type="submit" name="action">
                    Add a new account
                </button>
            </form>
        </div>

        <div class="row">
            <form class="col s12 z-depth-2" method="post" action="/api/transactions">
                <div class="input-field col s4">
                    <select name="tx_type">
                        <option value="" disabled selected>Choose transaction type</option>
                        <option value="Transfer">Transfer</option>
                        <option value="Deposit">Deposit</option>
                        <option value="Charge">Charge</option>
                    </select>
                    <label>Transaction type</label>
                </div>
                <div class="input-field col s4">
                    <select name="src_account_id">
                        <option value="" disabled selected>Choose your account</option>
                    <#list accounts as account>
                    <option value="${account.accountId}">ID: ${account.accountId}</option>
                    </#list>
                    </select>
                    <label>Your account</label>
                </div>
            <div class="input-field col s2">
                <input id="account_id" type="text" name="amount" class="validate">
                <label for="account_id">Amount</label>
            </div>
                <div class="input-field col s4">
                    <select name="dst_account">
                        <option value="" disabled selected>Choose account owner</option>
                    <#list customers as customer>
                    <option value="${customer.customerId}">
                        ${customer.firstName} ${customer.lastName}
                    </option>
                    </#list>
                    </select>
                    <label>Account holder</label>
                </div>
                <div class="input-field col s2">
                    <input id="account_id" type="text" class="validate" name="dst_account_id">
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


<script src="/js/lib/jquery-3.3.1.min.js"></script>
<script src="/js/lib/materialize.min.js"></script>
<script src="/js/main-ac.js"></script>
</body>
</html>