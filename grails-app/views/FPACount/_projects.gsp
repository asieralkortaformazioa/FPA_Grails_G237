

<script lang="text/javascript">

    $(document).ready( function () {
        $("#projects").change(function () {
//            alert("ProjectSelected");
            var idProj = document.getElementById("projects").value
            loadFunctionalities (idProj);
        });

    });

</script>


<div>
    <p>Projects: <g:select id="projects"  name="projects" noSelection="${['null':'Select One...']}" from="${viewBean.getProjects()}" value="" optionKey="id"/></p>
</div>
