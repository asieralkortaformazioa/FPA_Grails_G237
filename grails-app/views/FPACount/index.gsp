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
</head>

viewBean:${viewBean}
    <body>

        <p><h1>Function Point Analysis</h1></p>

        <p><h2>Data Functions:</h2></p>

            <g:form action="doCount" >
                <p> ILF: </p>
                <p> RET Count: <g:textField id="ilfRetCount" name="ilfRetCount" value="${viewBean?.ilfRetCount}"  /></p>
                <p> DET Count: <g:textField id="ilfDetCount" name="ilfDetCount" value="${viewBean?.ilfDetCount}" /></p>

                <br/>
                <p> EIF: </p>
                <p> RET Count: <g:textField id="eifRetCount" name="eifRetCount" value="${viewBean?.eifRetCount}" /></p>
                <p> DET Count: <g:textField id="eifDetCount" name="eifDetCount" value="${viewBean?.eifDetCount}" /></p>

                <br/>



                <p><h2>Transactional Funcitons:</h2></p>

                <p>EIs:</p>
                <p> FTR Count: <g:textField id="eiFtrCount" name="eiFtrCount" value="${viewBean?.eiFtrCount}" /></p>
                <p> DET Count: <g:textField id="eiDetCount" name="eiDetCount" value="${viewBean?.eiDetCount}" /></p>

                <p>EOs:</p>
                <p> FTR Count: <g:textField id="eoFtrCount" name="eoFtrCount" value="${viewBean?.eoFtrCount}" /></p>
                <p> DET Count: <g:textField id="eoDetCount" name="eoDetCount" value="${viewBean?.eoDetCount}" /></p>

                <p>EQs:</p>
                <p> FTR Count: <g:textField id="eqFtrCount" name="eqFtrCount" value="${viewBean?.eqFtrCount}" /></p>
                <p> DET Count: <g:textField id="eqDetCount" name="eqDetCount" value="${viewBean?.eqDetCount}" /></p>

                <g:submitButton name="Calculate" value="Calculate"/>

            </g:form>


<div id="result" style="background: #aaaaaa">
    <p><b><H3>Result</H3></b></p>

    <p><h4>Data Functions:</h4></p>

    <g:if test="${  viewBean?.ilfCount!= null }">
        <p>ilfCount: ${viewBean?.ilfCount}</p>
    </g:if>

    <g:if test="${  viewBean?.eifCount != null }">
        <p>eifCount: ${viewBean?.eifCount} </p>
    </g:if>

    <p><h4>Transactional Functions:</h4></p>

    <g:if test="${  viewBean?.eiCount !=null }">
        <p>eiCount: ${viewBean?.eiCount} </p>
    </g:if>

    <g:if test="${  viewBean?.eoCount !=null }">
        <p>eoCount: ${viewBean?.eoCount} </p>
    </g:if>

    <g:if test="${ viewBean?.eqCount !=null }">
        <p>eqCount: ${viewBean?.eqCount} </p>
    </g:if>

    <p><b>UnadjustedFPs:</b>${viewBean?.unadjustedFps} </p>



    <p><b>AdjustedFPs:</b>${viewBean?.adjustedFps}</p>

</div>
    </body>
</html>


