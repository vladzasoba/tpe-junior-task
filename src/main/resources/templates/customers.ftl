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
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Age</th>
                    <th> </th>
                    <th> </th>
                </tr>
                </thead>
                <tbody>
            <#list customers as customer>
            <tr>
                <td>${customer.customerId}</td>
                <td>${customer.firstName}</td>
                <td>${customer.lastName}</td>
                <td>${customer.age}</td>
                <td>
                    <a href="/customers/${customer.customerId}/accounts" class="waves-effect waves-light btn">Accounts</a>
                </td>
                <td>
                    <button class="red btn waves-effect waves-light" type="delete" onclick="deleteCustomer(${customer.customerId})" name="delete">Delete
                    </button>
                </td>
            </tr>
            </#list>
                </tbody>
            </table>
        </div>
        <div class="col s1"></div>
    </div>

    <div class="row">
            <form class="col s12 z-depth-2" method="post" action="/api/customers">
                <div class="row">
                    <p class="col s12">Add a new customer</p>
                </div>
                <div class="row">
                    <div class="input-field col s4">
                        <input placeholder="Placeholder" name="first_name" id="first_name" type="text" class="validate">
                        <label for="first_name">First Name</label>
                    </div>
                    <div class="input-field col s4">
                        <input id="last_name" type="text" name="last_name" class="validate">
                        <label for="last_name">Last Name</label>
                    </div>
                    <div class="input-field col s2">
                        <input id="age" type="text" name="age" class="validate">
                        <label for="age">Age</label>
                    </div>
                    <div class="col s2">
                        <button class="btn waves-effect waves-light" type="submit" name="action">Submit
                            <i class="material-icons right">send</i>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="/js/lib/jquery-3.3.1.js"></script>
<script src="/js/lib/jquery.dataTables.min.js"></script>
<script src="/js/lib/materialize.min.js"></script>
<script src="/js/main.js"></script>
</body>
</html>