
<script lang="javascript">

    var editAction=null;

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
            error: function (data) {
                if (data.status == 404) {
                    alert("Got error: " + data.error + " " + data.message);
                }
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
                vCount: vCount
            };

        $.ajax({
            url: "../functionalities",
            type:"PUT",
                data:data,
                success:function (resData){
                    var obj= $.parseJSON(resData);
                    alert ("FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.Functionality stored successfully.");
                hideCreateFunctionality();
                },
            error: function  (xhr, ajaxOptions, thrownError) {
                alert(xhr.status);
                alert(thrownError);
            }
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


    function createLoadFunctionalities()
    {
        var url = getFunctionalitiesUrl();


        //$('#divFunctionalities').innerHTML= '<table cellpadding="0" cellspacing="0" border="0" class="display" id="functionsTable"></table>';
        var dataSet = getFunctionalities();
//        $('#functionsTable').remove();
        //document.getElementById("functionsTable").parentNode.removeChild(document.getElementById("functionsTable"));
        $('#functionsTable').dataTable().fnDestroy();

//        $('#divFunctionalitiesDataTable').innerHTML="";
//        $('#divFunctionalitiesDataTable').innerHTML= '<table cellpadding="0" cellspacing="0" border="0" class="display" id="functionsTable"></table>';
        document.getElementById("divFunctionalitiesDataTable").innerHTML='<table cellpadding="0" cellspacing="0" border="0" class="display" id="functionsTable"></table>';


        var dataTable = $('#functionsTable').dataTable( {
            "data": dataSet,
            "columns": [
                { "title": "id" },
                { "title": "Description" },
                { "title": "type" },
                { "title": "HCount", "class": "center" },
                { "title": "VCount", "class": "center" },
                { "title": "Edit", "class": "center" },
                { "title": "Delete", "class": "center" }
            ]
        } );
        console.log ("Created Datatable");
        console.log (dataTable);

//        loadFunctionalities(dataTable, document.getElementById("projects").value);
//        $("#divFunctisonalities").append (dataTable);

    }



    function getFunctionalities ()
    {

//        console.log("Loading fucntionalities for :"+idProj);
        //var dataTable = $("#functionsTable").dataTable();
        //dataTable.fnClearTable();
        var url = getFunctionalitiesUrl();
        var resData= null;
        var obj = null;

//        var dataTable = $('#functionsTable');
//        console.log("Clearing...");
//        console.log(dataTable);
        //dataTable.fnClearTable();



        var dtData = null;

        $.get(url,function (data){
//            alert ("done");
            resData = data;
            var dataRes = new Array (0);

//            obj.each (iter , function (){
            for (var i= 0, l= resData.length;i<l;i++)
            {
                var arr ;

                arr=resData[i];
                arr.push("<a href=\"javascript:showEditFunctionality("+arr[0]+",'"+arr[2]+"','"+arr[1]+"', "+arr[3]+","+arr[4]+")\">edit</a>");
                arr.push("<a href=\"javascript:deleteFunctionality("+arr[0]+")\">delete</a>");
//                dataTable.fnAddData(arr);
                dataRes.push(arr);
//                dataRes [i]= arr;
            }
            console.log(dataRes );
            dtData= dataRes;

        });
        console.log("after ajax");
        console.log(dtData );
        /*
        dtData= [
            ['Trident','Internet Explorer 4.0','Win 95+','4','X'],
            ['Trident','Internet Explorer 5.0','Win 95+','5','C'],
            ['Trident','Internet Explorer 5.5','Win 95+','5.5','A'],
            ['Trident','Internet Explorer 6','Win 98+','6','A'],
            ['Trident','Internet Explorer 7','Win XP SP2+','7','A'],
            ['Trident','AOL browser (AOL desktop)','Win XP','6','A'],
            ['Gecko','Firefox 1.0','Win 98+ / OSX.2+','1.7','A'],
            ['Gecko','Firefox 1.5','Win 98+ / OSX.2+','1.8','A'],
            ['Gecko','Firefox 2.0','Win 98+ / OSX.2+','1.8','A'],
            ['Gecko','Firefox 3.0','Win 2k+ / OSX.3+','1.9','A'],
            ['Gecko','Camino 1.0','OSX.2+','1.8','A'],
            ['Gecko','Camino 1.5','OSX.3+','1.8','A'],
            ['Gecko','Netscape 7.2','Win 95+ / Mac OS 8.6-9.2','1.7','A'],
            ['Gecko','Netscape Browser 8','Win 98SE+','1.7','A'],
            ['Gecko','Netscape Navigator 9','Win 98+ / OSX.2+','1.8','A'],
            ['Gecko','Mozilla 1.0','Win 95+ / OSX.1+',1,'A'],
            ['Gecko','Mozilla 1.1','Win 95+ / OSX.1+',1.1,'A'],
            ['Gecko','Mozilla 1.2','Win 95+ / OSX.1+',1.2,'A'],
            ['Gecko','Mozilla 1.3','Win 95+ / OSX.1+',1.3,'A'],
            ['Gecko','Mozilla 1.4','Win 95+ / OSX.1+',1.4,'A'],
            ['Gecko','Mozilla 1.5','Win 95+ / OSX.1+',1.5,'A'],
            ['Gecko','Mozilla 1.6','Win 95+ / OSX.1+',1.6,'A'],
            ['Gecko','Mozilla 1.7','Win 98+ / OSX.1+',1.7,'A'],
            ['Gecko','Mozilla 1.8','Win 98+ / OSX.1+',1.8,'A'],
            ['Gecko','Seamonkey 1.1','Win 98+ / OSX.2+','1.8','A'],
            ['Gecko','Epiphany 2.20','Gnome','1.8','A'],
            ['Webkit','Safari 1.2','OSX.3','125.5','A'],
            ['Webkit','Safari 1.3','OSX.3','312.8','A'],
            ['Webkit','Safari 2.0','OSX.4+','419.3','A'],
            ['Webkit','Safari 3.0','OSX.4+','522.1','A'],
            ['Webkit','OmniWeb 5.5','OSX.4+','420','A'],
            ['Webkit','iPod Touch / iPhone','iPod','420.1','A'],
            ['Webkit','S60','S60','413','A'],
            ['Presto','Opera 7.0','Win 95+ / OSX.1+','-','A'],
            ['Presto','Opera 7.5','Win 95+ / OSX.2+','-','A'],
            ['Presto','Opera 8.0','Win 95+ / OSX.2+','-','A'],
            ['Presto','Opera 8.5','Win 95+ / OSX.2+','-','A'],
            ['Presto','Opera 9.0','Win 95+ / OSX.3+','-','A'],
            ['Presto','Opera 9.2','Win 88+ / OSX.3+','-','A'],
            ['Presto','Opera 9.5','Win 88+ / OSX.3+','-','A'],
            ['Presto','Opera for Wii','Wii','-','A'],
            ['Presto','Nokia N800','N800','-','A'],
            ['Presto','Nintendo DS browser','Nintendo DS','8.5','C/A<sup>1</sup>'],
            ['KHTML','Konqureror 3.1','KDE 3.1','3.1','C'],
            ['KHTML','Konqureror 3.3','KDE 3.3','3.3','A'],
            ['KHTML','Konqureror 3.5','KDE 3.5','3.5','A'],
            ['Tasman','Internet Explorer 4.5','Mac OS 8-9','-','X'],
            ['Tasman','Internet Explorer 5.1','Mac OS 7.6-9','1','C'],
            ['Tasman','Internet Explorer 5.2','Mac OS 8-X','1','C'],
            ['Misc','NetFront 3.1','Embedded devices','-','C'],
            ['Misc','NetFront 3.4','Embedded devices','-','A'],
            ['Misc','Dillo 0.8','Embedded devices','-','X'],
            ['Misc','Links','Text only','-','X'],
            ['Misc','Lynx','Text only','-','X'],
            ['Misc','IE Mobile','Windows Mobile 6','-','C'],
            ['Misc','PSP browser','PSP','-','C'],
            ['Other browsers','All others','-','-','U']
        ];
        */
        console.log(dtData );
        return dtData;

    }

    function loadFunctionalities (dataTable, idProj)
    {
        console.log("Loading fucntionalities for :"+idProj);
        //var dataTable = $("#functionsTable").dataTable();
        //dataTable.fnClearTable();
        var url = getFunctionalitiesUrl();
        var resData= null;
        var obj = null;

//        var dataTable = $('#functionsTable');
        console.log("Clearing...");
        console.log(dataTable);
        //dataTable.fnClearTable();

        $.get(url,function (data){
//            alert ("done");
            resData = data;
            var dtData = new Array (resData.length);

//            obj.each (iter , function (){
            for (var i= 0, l= resData.length;i<l;i++)
            {
                var arr ;

                arr=resData[i];
                arr.push("<a href=\"javascript:showEditFunctionality("+arr[0]+",'"+arr[2]+"','"+arr[1]+"', "+arr[3]+","+arr[4]+")\">edit</a>");
                arr.push("<a href=\"javascript:deleteFunctionality("+arr[0]+")\">delete</a>");

//                dataTable.fnAddData(arr);
                dtData[i]= arr;
            }

            console.log ("data:"+dtData);

//            var oSettings = dataTable.oApi._fnSettings();
//            dataTable.oApi._fnAddData (oSettings , dtData);

            return dtData;
        });



                /*
                .success (function (data){
            alert ("success");
            //alert ("finished");
            console.log ("ResultData:"+data);
            resData = data;
            //obj= $.parseJSON(resData);



            var dtData = new Array (resData.length);

//            obj.each (iter , function (){
            for (var i= 0, l= resData.length;i<l;i++)
            {
                var arr ;

                arr=resData[i];
                arr.push("<a href=\"javascript:showEditFunctionality("+arr[0]+",'"+arr[2]+"','"+arr[1]+"', "+arr[3]+","+arr[4]+")\">edit</a>");
                arr.push("<a href=\"javascript:deleteFunctionality("+arr[0]+")\">delete</a>");
//                dataTable.fnAddData(arr);
                dtData[i]= arr;
            }

        })
                .error (function (error){
                    console.log("error:"+error.error());
                })
                .fail (function (error ){
                    alert ("fail"+error.error());
                })
                //.always (function (){alert ("always");})
        ;
        */

    }

    function getFunctionalitiesUrl ()
    {
        var idProj = document.getElementById("projects").value;
        return "../functionalities/listProjectFunctionalities/"+idProj;
    }

// DataTable:
            //$(document).ready( function () {
/*
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
*/
//                loadFunctionalities(document.getElementById("projects").value);
    /*
                createLoadFunctionalities ();
                initFunctionalities();

            } );
*/


    $(function() {
        $( "#dialog" ).dialog();
    });

</script>

<div id="divFunctionalities" style="border:2px solid;border-radius:25px;padding:10px">

    <p><h2>Functionality List</h2></p>
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
<input type="button" onclick="javascript:storeFunctionality()" value="Create Functionality"/>
</div>



</div>
