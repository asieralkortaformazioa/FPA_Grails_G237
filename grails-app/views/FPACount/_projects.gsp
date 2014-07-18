

<script lang="text/javascript">


    //$(document).ready( function () {

      function attacthProjectEvents (){
        $("#projects").change(function () {
//            alert("ProjectSelected");
            var idProj = document.getElementById("projects").value;
//            alert ("Loading functionality...");
            console.log ("Loading functionality...");
            loadFunctionalities (idProj);
            loadAdjustmentFactors(idProj);
            clearResults();
        });

        $("#createButton").click ( function (){
            $("#pageForm").setAttribute("action","doCreateProject");

            var projectName=$("#projectName").text();
            var data = {projectName : projectName};

            $.post ("doCreateProject",{
                        url: "${g.createLink(controller: 'ProjectsController', action:'doCreateProject', )}",
                        data: data
                    },
                    function (result){
                        alert ("success");
                    })
                    .success (function (result){
                        alert ("Project created successfully.");
                    })
                    .error (function (result){
                        alert ("Error creating project:"+result);
            });

        });

    };

</script>


<div>
    <p>FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.Projects: <g:select id="projects"  name="projects" noSelection="${['null':'Select One...']}" from="${viewBean.getProjects()}" value="" optionKey="id"/></p>
    <p><g:actionSubmit action="deleteProject" value="Delete selected project" /></p>
    <p><g:textField name="projectName" id="projectName" />
        <!-- <g:submitButton name="createButton" id="createButton" value="Create Project"/>-->
        <g:actionSubmit action="createProject" value="Create Project" />

    </p>
</div>
