
<script lang="javascript">


    function deleteFunctionality (id)
    {
        var data ={
                id: id
            };

        $.ajax ({
            url: "../functionalities/"+id,
            type: "DELETE",
            data: data,
            success: function (){
                $("#functionsTable").dataTable().fnClearTable();
                loadFunctionalities (document.getElementById("projects").value)

                alert ("Object Deleted successfully");
            },
            error: function (data){
                alert ("Got error: "+data.error+" "+data.message);
            },
            fail: function (data){
                alert ("Failed: "+data.error+" "+data.message);
            }

        });
    }


    function createFunctionality()
    {
        var data ={
                type: document.getElementById("types").value,
                description: document.getElementById("description").value,
                hCount: document.getElementById("hCount").value,
                vCount: document.getElementById("vCount").value,
                idProj :document.getElementById("projects").value
        };

        $.post("../functionalities",
                    data, function (){
                    alert ("Done");
                }).done (function (res){
                alert ("object created successfully");
                   if (res!=undefined && res!=null) {
                       loadFunctionalities(document.getElementById("projects").value);
                   }
        }).error (function (data){
            alert ("Got error: "+data.error+" "+data.message);
            });
    }


    function loadFunctionalities (idProj)
    {
        $("#functionsTable").dataTable().fnClearTable();
        var url = getFunctionalitiesUrl();
        var resData= null;
        var obj = null;
        $.get(url,function (data){
            //alert ("finished");
            console.log ("ResultData:"+data);
            resData = data;
            //obj= $.parseJSON(resData);

            var iter= null;
//            obj.each (iter , function (){
            for (var i= 0, l= resData.length;i<l;i++)
            {
                var arr ;
                arr=resData[i];
                arr.push("<a href=\"javascript:deleteFunctionality("+arr[0]+")\">delete</a>");
                $('#functionsTable').dataTable().fnAddData(arr);
            }

        })
                //.success (function (){alert ("success");})
                .fail (function (){alert ("fail");})
                //.always (function (){alert ("always");})
        ;

    }

    function getFunctionalitiesUrl ()
    {
        var idProj = document.getElementById("projects").value;
        return "../functionalities/listProjectFunctionalities/"+idProj;
    }

// DataTable:
            $(document).ready( function () {

                $('#functionsTable').DataTable({
//                    "aaData":[["1","A"],["2","B"]],
                    "aoColumnDefs":[
                        { "sTitle":"A"},
                        { "sTitle":"b"}
                    ]

                    %{--FindFuctionalities--}%
                    %{--,"sAjaxSource": "<g:createLink resource='api/projects'/>"--}%
                    ,"sAjaxSource": getFunctionalitiesUrl ()
                });
            } );

</script>

<div style="border:2px solid;border-radius:25px;padding:10px">

    <p><h2>Functionality List</h2></p>
<table id="functionsTable">
    <!-- --><thead><th>id</th> </th><th>Description</th><th>type</th><th>Hcount</th><th>Vcount</th><th>Delete</th></thead>
</table>


<div style="border:2px solid;border-radius:25px;padding:10px">

<!--optionKey="id"-->
 <p>Types: <g:select id="types"  name="types" noSelection="${['null':'Select One...']}" from="${viewBean.getFunctions()}" value="" /></p>
 <p> Description: <g:textField id="description" name="description" /></p>
 <p> Horizontal count: <g:textField id="hCount" name="hCount" /></p>
 <p> Vertical count: <g:textField id="vCount" name="vCount" /></p>
<input type="button" onclick="javascript:createFunctionality()" value="Create"/>
</div>


</div>
