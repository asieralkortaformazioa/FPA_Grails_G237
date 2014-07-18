
<script type="application/javascript">
    function clearResults()
    {
        document.getElementById("dataFunctions").innerHTML="";
        document.getElementById("txFunctions").innerHTML="";
        document.getElementById("adjustmentFactor").innerHTML="";
        document.getElementById("unadjustedFunctionPoints").innerHTML="";
        document.getElementById("adjustedFunctionPoints").innerHTML="";
    }

</script>
<div id="result" style="background: #aaaaaa;border:2px solid;border-radius:25px;">
    <br/>
    <input type="button" name="Calculate" value="Calculate" onClick="javascript:doCalculate()" />
    <p><b><H3>Result</H3></b></p>

    <p >Data Functions:</p> <p  id="dataFunctions" name="dataFunctions"></p>

<p>Transactional Functions:</p> <p  id="txFunctions" name="txFunctions" ></p>


<p > Adjustment Factor:</p> <p  id="adjustmentFactor" name="adjustmentFactor" ></p>

<p >UnadjustedFPs:</p> <p id="unadjustedFunctionPoints" name="unadjustedFunctionPoints" > </p>

<p >AdjustedFPs:</p> <p id="adjustedFunctionPoints" name="adjustedFunctionPoints"></p>

</div>
