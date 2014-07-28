
<script lang="javascript">

    var editAction=null;
    var dtDataSet=null;

    function getFunctionalitiesUrl ()
    {
        var idProj = document.getElementById("projects").value;
        return "../functionalities/listProjectFunctionalities/"+idProj;
    }


    function initFunctionalities ()
    {
        $("#CUDiv").hide();


        createLoadFunctionalities ();
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
            done: function (data){
                $("#functionsTable").dataTable().fnDestroy();
//                $("#functionsTable").dataTable().fnClearTable();
                loadFunctionalities (document.getElementById("projects").value)

                alert ("Object Deleted successfully");
            },
            fail: function (data){
                alert ("Failed: "+data.error+" "+data.message);
            }

        });

//        createLoadFunctionalities ();
        location.reload();
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
                vCount: vCount,
                action:"PUT"
            };

        $.ajax({
            url: "../functionalities",
            type:'PUT',
                data:data})
                .done (function (resData){
                    var obj= $.parseJSON(resData);
                    alert ("FPA_Webapp_G237.FPA_Webapp_G237.Functionality stored successfully.");
                hideCreateFunctionality();
                })
            .fail( function  (xhr, ajaxOptions, thrownError) {
                alert(xhr.status);
                alert(thrownError);
            });
//        createLoadFunctionalities ();
        location.reload();
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


    function getFunctionalities ()
    {
        var idProj = document.getElementById ("projects").value;

        var dtData = null;

        if (idProj!=undefined && idProj!=null && idProj!="null" && idProj !="-1")  {
//        console.log("Loading fucntionalities for :"+idProj);
            //var dataTable = $("#functionsTable").dataTable();
            //dataTable.fnClearTable();
            var url = getFunctionalitiesUrl();
            var resData = null;
            var obj = null;

//        var dataTable = $('#functionsTable');
//        console.log("Clearing...");
//        console.log(dataTable);
            //dataTable.fnClearTable();


            $.get(url, function (data) {

            })
                    .done(function (data) {
                        //            alert ("done");
                        resData = data;
                        var dataSet = null;
                        var dataRes = new Array(0);

                        //            obj.each (iter , function (){
                        console.log("Length:" + resData.length);
                        for (var i = 0, l = resData.length; i < l; i++) {
                            var arr = new Array(0);
                            var cell = resData[i];
                            // Add table cells:
                            for (var j = 0; j < cell.length; j++) {
                                arr.push(cell[j]);
                            }
                            arr.push("<a href=\"javascript:showEditFunctionality(" + arr[0] + ",'" + arr[2] + "','" + arr[1] + "', " + arr[3] + "," + arr[4] + ")\">edit</a>");
                            arr.push("<a href=\"javascript:deleteFunctionality(" + arr[0] + ")\">delete</a>");
                            //                dataTable.fnAddData(arr);
                            dataRes.push(arr);
                            //                dataRes [i]= arr;
                        }
                        dtDataSet = dataRes;
                        console.log(dataRes);
                        dtData = dataRes;

                        console.log(dtDataSet);


                        $('#functionsTable').dataTable().fnDestroy();

                        document.getElementById("divFunctionalitiesDataTable").innerHTML = '<table cellpadding="0" cellspacing="0" border="0" class="display" id="functionsTable"></table>';

                        console.log("DataSet to Draw:" + dataSet);
                        var dataTable = $('#functionsTable').dataTable({
                            "data": dtDataSet,
                            "iDisplayLength": 50,
                            "columns": [
                                { "title": "id" },
                                { "title": "Description" },
                                { "title": "type" },
                                { "title": "HCount", "class": "center" },
                                { "title": "VCount", "class": "center" },
                                { "title": "Edit", "class": "center" },
                                { "title": "Delete", "class": "center" }
                            ],
                            "sDom": 'T<"clear">lfrtip'
                            , "oTableTools": {
                                "sSwfPath": "../swf/copy_csv_xls.swf",
                                "aButtons": [
                                    {
                                        "sExtends": "xls",
                                        "mColumns": [1,2, 3, 4, 5, 6, 7],
                                        "sPdfOrientation": "landscape",
                                        "sButtonText": "Export to Excel",
                                        "oSelectorOpts": { filter: 'applied', order: 'current',page:'current' }
                                    }
                                ]
                            }
                        });
                        console.log("Created Datatable");
                        console.log(dataTable);

                        $("#divFunctionalities").append(dataTable);


                    })
                    .fail(function (data) {
                        alert("error:" + data.error + "msg: " + data.message);
                    });

            console.log("after ajax");
            console.log(dtData);

            console.log(dtDataSet);
        }
        return dtData;

    }



    function createLoadFunctionalities() {


        var dataSet = getFunctionalities();
//
//        $('#functionsTable').dataTable().fnDestroy();
//
//        document.getElementById("divFunctionalitiesDataTable").innerHTML='<table cellpadding="0" cellspacing="0" border="0" class="display" id="functionsTable"></table>';
//
//        console.log ("DataSet to Draw:"+dataSet);
//        var dataTable = $('#functionsTable').dataTable( {
//            "data": dtDataSet,
//            "columns": [
//                { "title": "id" },
//                { "title": "Description" },
//                { "title": "type" },
//                { "title": "HCount", "class": "center" },
//                { "title": "VCount", "class": "center" },
//                { "title": "Edit", "class": "center" },
//                { "title": "Delete", "class": "center" }
//            ]
//        } );
//        console.log ("Created Datatable");
//        console.log (dataTable);
//
//        $("#divFunctionalities").append (dataTable);

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
//                    alert ("Done");
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

    $(function() {
        $( "#dialog" ).dialog();
    });

</script>

<div id="divFunctionalities" style="border:2px solid;border-radius:25px;padding:10px">

    <p><h2>FPA_Webapp_G237.Functionality List</h2></p>
    <!-- -->
    <div id="divFunctionalitiesDataTable" >
        <table id="functionsTable">
            <thead><th>id</th> </th><th>Description</th><th>type</th><th>Hcount</th><th>Vcount</th><th>edit</th><th>Delete</th></thead>
        </table>
    </div>

<input type="button" onclick="javascript:showCreateFunctionality();" value="Create"/>


<div id="CUDiv" name="CUDiv" style="border:2px solid;border-radius:25px;padding:10px;">

<!--optionKey="id"-->
 <p>Types: <g:select id="types"  name="types" noSelection="${['-1':'Select One...']}" from="${viewBean.getFunctions()}" value="" /></p>
 <p> Description: <g:textField id="description" name="description" /></p>
 <p> Horizontal count: <g:textField id="hCount" name="hCount" /></p>
 <p> Vertical count: <g:textField id="vCount" name="vCount" /></p>
 <p> id: <g:textField id="idFunctionality" name="idFunctionality" /></p>
<input type="button" onclick="javascript:storeFunctionality()" value="Create FPA_Webapp_G237.Functionality"/>
</div>



</div>
