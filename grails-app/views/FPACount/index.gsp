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
    <!--<script type="text/javascript" src="../js/jquery-2.1.1.min.js"></script> -->
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript" charset="utf8" src="../js/jquery.dataTables.js"></script>

</head>

viewBean:${viewBean}
    <body>

<script lang="text/javascript">
$(document).ready (function (){

// DataTable:
            $(document).ready( function () {
                $('#table_id').DataTable();
            } );



    //alert ("ready");
    $("#helpAdjustemntFactor").hide();
    $("#helpAf1").hide();
    $("#helpAf2").hide();
    $("#helpAf3").hide();
    $("#helpAf4").hide();
    $("#helpAf5").hide();
    $("#helpAf6").hide();
    $("#helpAf7").hide();
    $("#helpAf8").hide();
    $("#helpAf9").hide();
    $("#helpAf10").hide();
    $("#helpAf11").hide();
    $("#helpAf12").hide();
    $("#helpAf13").hide();
    $("#helpAf14").hide();




            $( "#txtAdjustmentFactor" ).click(function() {
                if ($("#helpAdjustemntFactor").is(":hidden"))
                    $("#helpAdjustemntFactor").show();
                else
                    $("#helpAdjustemntFactor").hide();
            });

            $( "#af1" ).click(function() {
                if ($("#helpAf1").is(":hidden"))
                    $("#helpAf1").show();
                else
                    $("#helpAf1").hide();
            });

            $( "#af2" ).click(function() {
                if ($("#helpAf2").is(":hidden"))
                    $("#helpAf2").show();
                else
                    $("#helpAf2").hide();
            });

            $( "#af3" ).click(function() {
                if ($("#helpAf3").is(":hidden"))
                    $("#helpAf3").show();
                else
                    $("#helpAf3").hide();
            });

            $( "#af4" ).click(function() {
                if ($("#helpAf4").is(":hidden"))
                    $("#helpAf4").show();
                else
                    $("#helpAf4").hide();
            });

            $( "#af5" ).click(function() {
                if ($("#helpAf5").is(":hidden"))
                    $("#helpAf5").show();
                else
                    $("#helpAf5").hide();
            });

            $( "#af6" ).click(function() {
                if ($("#helpAf6").is(":hidden"))
                    $("#helpAf6").show();
                else
                    $("#helpAf6").hide();
            });


            $( "#af7" ).click(function() {
                if ($("#helpAf7").is(":hidden"))
                    $("#helpAf7").show();
                else
                    $("#helpAf7").hide();
            });

            $( "#af8" ).click(function() {
                if ($("#helpAf8").is(":hidden"))
                    $("#helpAf8").show();
                else
                    $("#helpAf8").hide();
            });

            $( "#af9" ).click(function() {
                if ($("#helpAf9").is(":hidden"))
                    $("#helpAf9").show();
                else
                    $("#helpAf9").hide();
            });

            $( "#af10" ).click(function() {
                if ($("#helpAf10").is(":hidden"))
                    $("#helpAf10").show();
                else
                    $("#helpAf10").hide();
            });

            $( "#af11" ).click(function() {
                if ($("#helpAf11").is(":hidden"))
                    $("#helpAf11").show();
                else
                    $("#helpAf11").hide();
            });

            $( "#af12" ).click(function() {
                if ($("#helpAf12").is(":hidden"))
                    $("#helpAf12").show();
                else
                    $("#helpAf12").hide();
            });

            $( "#af13" ).click(function() {
                if ($("#helpAf13").is(":hidden"))
                    $("#helpAf13").show();
                else
                    $("#helpAf13").hide();
            });

            $( "#af14" ).click(function() {
                if ($("#helpAf14").is(":hidden"))
                    $("#helpAf14").show();
                else
                    $("#helpAf14").hide();
            });
    }
);


</script>

        <p><h1>Function Point Analysis</h1></p>

        <div style="border:2px solid;border-radius:25px;">
            <p><h2>Data Functions:</h2></p>

            <g:form action="doCount" >
                <p> ILF: </p>
                <p> RET Count: <g:textField id="ilfRetCount" name="ilfRetCount" value="${viewBean?.ilfRetCount}"  /></p>
                <p> DET Count: <g:textField id="ilfDetCount" name="ilfDetCount" value="${viewBean?.ilfDetCount}" /></p>

                <br/>
                <p> EIF: </p>
                <p> RET Count: <g:textField id="eifRetCount" name="eifRetCount" value="${viewBean?.eifRetCount}" /></p>
                <p> DET Count: <g:textField id="eifDetCount" name="eifDetCount" value="${viewBean?.eifDetCount}" /></p>

        </div>
                <br/>


        <div style="border:2px solid;border-radius:25px;">
                <p><h2>Transactional Functions:</h2></p>

                <p>EIs:</p>
                <p> FTR Count: <g:textField id="eiFtrCount" name="eiFtrCount" value="${viewBean?.eiFtrCount}" /></p>
                <p> DET Count: <g:textField id="eiDetCount" name="eiDetCount" value="${viewBean?.eiDetCount}" /></p>

                <p>EOs:</p>
                <p> FTR Count: <g:textField id="eoFtrCount" name="eoFtrCount" value="${viewBean?.eoFtrCount}" /></p>
                <p> DET Count: <g:textField id="eoDetCount" name="eoDetCount" value="${viewBean?.eoDetCount}" /></p>

                <p>EQs:</p>
                <p> FTR Count: <g:textField id="eqFtrCount" name="eqFtrCount" value="${viewBean?.eqFtrCount}" /></p>
                <p> DET Count: <g:textField id="eqDetCount" name="eqDetCount" value="${viewBean?.eqDetCount}" /></p>
        </div>

                <g:render template="adjustment" />

                <g:submitButton name="Calculate" value="Calculate"/>

            </g:form>


        <g:render template="result"/>

    </body>
</html>


