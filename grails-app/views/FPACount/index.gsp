<%--
  Created by IntelliJ IDEA.
  User: developer
  Date: 22/05/14
  Time: 11:10
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>FPACount</title>
    <link rel="stylesheet" type="text/css" href="../css/jquery.dataTables.css">

    <link href="../css/dataTables.tableTools.css" rel="stylesheet" />
    <!--<script type="text/javascript" src="../js/jquery-2.1.1.min.js"></script> -->
    <script type="text/javascript" src="../js/jquery.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">

    <script type="text/javascript" src="../js/jquery-ui.js"></script>
    <script type="text/javascript" charset="utf8" src="../js/jquery.dataTables.js"></script>

    <script src="../js/jquery.validate.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/additional-methods.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/ZeroClipboard.min.js"></script>
    <script src="../js/dataTables.tableTools.min.js"></script>

</head>


<body>

<script lang="text/javascript">
    $(document).ready (function () {
//    alert("Ready");
        configAdjustment();

        initFunctionalities();

        initProjects();



        loadAdjustmentFactors (document.getElementById("projects").value);

    });




    function dateToYMD(date) {
        var d = date.getDate();
        var m = date.getMonth() + 1;
        var y = date.getFullYear();
        return '' + y + '-' + (m<=9 ? '0' + m : m) + '-' + (d <= 9 ? '0' + d : d);
    }



    function getCalculateUrl ()
    {
        var idProj = document.getElementById("projects").value;
//        return "../adjustmentFactor/"+idProj;
        return "doCalculate/"+idProj;
    }

    function doCalculate()
    {

        var url = getCalculateUrl();
        var resData= null;
        var obj = null;
        console.log("Loading caculations for url :"+url);
        $.get(url,function (data){

            var res = $.parseJSON(data);

            document.getElementById("dataFunctions").innerHTML = "<b>"+res.dataFunctions+"</b>";
            document.getElementById("txFunctions").innerHTML = "<b>"+res.transactionalFunctions+"</b>";

            document.getElementById("adjustmentFactor").innerHTML =  "<b>"+res.adjustmentFactor+"</b>";
            document.getElementById("unadjustedFunctionPoints").innerHTML =  "<b>"+res.ufps+"</b>";
            document.getElementById("adjustedFunctionPoints").innerHTML =  "<b>"+res.afps+"</b>";
            var estDays = (parseFloat(res.estimateInDays))
            document.getElementById("estimateInDays").innerHTML = "<b>"+(estDays.toFixed(2))+"</b>";
            var estHours = (parseFloat(res.estimateInHours))
            document.getElementById("estimateInHours").innerHTML = "<b>"+(estHours.toFixed(2))+"</b>";

            var date= Date.parse(res.estimatedFinishDate);
            var estimatedFinishDate = dateToYMD (date);
            console.log ("Estimated Finish Date: "+estimatedFinishDate);
            console.log ("Estimated Finish Date: "+date);
            document.getElementById("estimatedFinishDate").innerHTML = "<b>"+estimatedFinishDate+"</b>";
        });

        document.getElementById("result").style.visibility= "visible";
        $("#result").show();
    }



</script>

<p><h1>Function Point Analysis</h1></p>





<g:form name="pageForm" id="pageForm" action="doCount" >

    <g:render template="projects"/>

    <!--
        render template="dataFunctions"
        render template="txFunctions"
    -->

    <g:render template="functionList" />


    <g:render template="adjustment" />




    <g:render template="result"/>


</g:form>

</body>
</html>


