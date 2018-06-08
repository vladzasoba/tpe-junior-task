<html>
<head>
    <link rel="stylesheet" href="/css/lib/jquery.dataTables.min.css">
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

            <table class="datatable display"
                   width="100%"
                   id="tx-tables"
                   data-id-field="code"
                   data-sort-name="value1"
                   data-sort-order="desc"
                   data-show-chart="false"
                   data-pagination="false"
                   data-show-pagination-switch="false">
            </table>
        </div>
    </div>
</div>



<script>
    document.addEventListener('DOMContentLoaded', function() {
        var elems = document.querySelectorAll('.collapsible');
        var instances = M.Collapsible.init(elems, options);
    });
</script>

<script src="/js/lib/jquery-3.3.1.js"></script>
<script src="/js/lib/jquery.dataTables.min.js"></script>
<script src="/js/lib/materialize.min.js"></script>
<script src="/js/main-tx.js"></script>
</body>
</html>