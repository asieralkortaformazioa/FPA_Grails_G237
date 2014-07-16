
<div id="result" style="background: #aaaaaa;border:2px solid;border-radius:25px;">
    <p><b><H3>Result</H3></b></p>

    <p>Data Functions:</p>

<g:if test="${  viewBean?.ilfCount!= null }">
    <p>ilfCount: ${viewBean?.ilfCount}</p>
</g:if>

<g:if test="${  viewBean?.eifCount != null }">
    <p>eifCount: ${viewBean?.eifCount} </p>
</g:if>

<p>Transactional Functions:</p>

<g:if test="${  viewBean?.eiCount !=null }">
    <p>eiCount: ${viewBean?.eiCount} </p>
</g:if>

<g:if test="${  viewBean?.eoCount !=null }">
    <p>eoCount: ${viewBean?.eoCount} </p>
</g:if>

<g:if test="${ viewBean?.eqCount !=null }">
    <p>eqCount: ${viewBean?.eqCount} </p>
</g:if>

<p id="adjustmentFactor" name="adjustmentFactor" >Adjustment Factor:</p>

<p id="unadjustedFunctionPoints" name="unadjustedFunctionPoints" >UnadjustedFPs:${viewBean?.unadjustedFps} </p>



<p id="adjustedFunctionPoints" name="adjustedFunctionPoints">AdjustedFPs:${viewBean?.adjustedFps}</p>

</div>
