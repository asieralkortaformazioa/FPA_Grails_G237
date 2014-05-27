
<div id="result" style="background: #aaaaaa;border:2px solid;border-radius:25px;">
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
