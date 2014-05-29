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
$(document).ready (function () {
//    alert("Ready");
    configAdjustment();

    loadFunctionalities (document.getElementById("projects").value);

});


</script>

        <p><h1>Function Point Analysis</h1></p>



        <g:render template="projects"/>


        <g:form action="doCount" >

            <!--
                render template="dataFunctions"
                render template="txFunctions"
            -->

                <g:render template="functionList" />


                <g:render template="adjustment" />


                <g:submitButton name="Calculate" value="Calculate"/>

            </g:form>


        <g:render template="result"/>

    </body>
</html>


