
<script lang="javascript">

    var editAction=null;

    function initFunctionalities ()
    {
        $("#CUDiv").hide();
    }


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


    function editFunctionality ()
    {

        var id =document.getElementById("idFunctionality").value;
        var desc =document.getElementById("description").value;
        var hCount =document.getElementById("hCount").value;
        var vCount =document.getElementById("vCount").value;
        var types = document.getElementById("types").value;

        var data ={
                id:id,
                types: types,
                description: desc,
                hCount: hCount,
                vCount: vCount
            };

        $.ajax({
            url: "../functionalities",
            type:"PUT",
                data:data,
                success:function (resData){
                    var obj= $.parseJSON(resData);
                    alert ("FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.Functionality stored successfully.");

                },
            error: function  (xhr, ajaxOptions, thrownError) {
                alert(xhr.status);
                alert(thrownError);
            }
        });
    }


    function showCreateFunctionality(){
        editAction="CREATE";
        $("#CUDiv").dialog();

    }

    function hideCreateFunctionality(){
        $("#CUDiv").hide();
    }

    function storeFunctionality (id)
    {
        if  (editAction=="UPDATE")
        {
            editFunctionality();
        }
        else
            createFunctionality();

    }



    function showEditFunctionality(id, type, description, hcount, vcount)
    {
        editAction="UPDATE"
        $("#CUDiv").dialog();
//        $("#types").text(type);
        console.log (document.getElementById ("types"));
        $('#types').val(type);

        document.getElementById("idFunctionality").value=id;

        $("idFunctionality").prop('disabled', true);
        document.getElementById("idFunctionality").disabled=true;

        document.getElementById("description").value=description;
        document.getElementById("hCount").value=hcount;
        document.getElementById("vCount").value=vcount;

        /*
        $("#idFunctionality").text(id);
        $("#description").text(description);
        $("#hCount").text(hcount);
        $("#vCount").text(vcount);
        */

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
            hideCreateFunctionality ();
        }).error (function (data){
            alert ("Got error: "+data.error+" "+data.message);
            });
    }


    function loadFunctionalities (idProj)
    {
        console.log("Loading fucntionalities for :"+idProj);
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
                arr.push("<a href=\"javascript:showEditFunctionality("+arr[0]+",'"+arr[2]+"','"+arr[1]+"', "+arr[3]+","+arr[4]+")\">edit</a>");
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

                initFunctionalities();

            } );



    $(function() {
        $( "#dialog" ).dialog();
    });

</script>

<div style="border:2px solid;border-radius:25px;padding:10px">

    <p><h2>FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.Functionality List</h2></p>
<table id="functionsTable">
    <!-- --><thead><th>id</th> </th><th>Description</th><th>type</th><th>Hcount</th><th>Vcount</th><th>edit</th><th>Delete</th></thead>
</table>



<input type="button" onclick="javascript:showCreateFunctionality();" value="Create"/>


<div id="CUDiv" name="CUDiv" style="border:2px solid;border-radius:25px;padding:10px;">

<!--optionKey="id"-->
 <p>Types: <g:select id="types"  name="types" noSelection="${['-1':'Select One...']}" from="${viewBean.getFunctions()}" value="" /></p>
 <p> Description: <g:textField id="description" name="description" /></p>
 <p> Horizontal count: <g:textField id="hCount" name="hCount" /></p>
 <p> Vertical count: <g:textField id="vCount" name="vCount" /></p>
 <p> id: <g:textField id="idFunctionality" name="idFunctionality" /></p>
<input type="button" onclick="javascript:storeFunctionality()" value="Create Functionality"/>
</div>



</div>
