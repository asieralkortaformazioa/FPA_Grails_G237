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

    <body>

        <p>Function Point Analysis</p>

        <g:form action="doCount" >
            <p> ILF: </p>
            <p> RET Count: <g:textField id="ilfRetCount" name="ilfRetCount" /></p>
            <p> DET Count: <g:textField id="ilfDetCount" name="ilfDetCount" /></p>

            <br/>
            <p> EIF: </p>
            <p> RET Count: <g:textField id="eifRetCount" name="eifRetCount" /></p>
            <p> DET Count: <g:textField id="eifDetCount" name="eifDetCount" /></p>

            <g:submitButton name="Calculate" value="Calculate"/>

        </g:form>

    </body>
</html>


