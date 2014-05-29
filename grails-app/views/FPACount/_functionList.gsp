
<script lang="javascript">
// DataTable:
            $(document).ready( function () {

                $('#functionsTable').DataTable({
                    "aaData":[["1","A"],["2","B"]],
                    "aoColumnDefs":[
                        { "sTitle":"A"},
                        { "sTitle":"b"}
                    ]

                    FindFuctionalities
                    ,"sAjaxSource": "<g:createLink resource='api/projects'/>"

                });
            } );

</script>

<div style="border:2px solid;border-radius:25px;padding:10px">

    <p><h2>Functionality List</h2></p>
<table id="functionsTable">
    <thead><th>t1</th><th>t2</th></thead>
</table>


</div>
