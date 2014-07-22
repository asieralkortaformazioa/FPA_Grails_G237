

<script lang="text/javascript">


    //$(document).ready( function () {
      function attacthProjectEvents (){
        $("#projects").change(function () {
//            alert("ProjectSelected");
            var idProj = document.getElementById("projects").value;
//            alert ("Loading functionality...");
            if (idProj!= undefined && idProj!=null && idProj!="null") {
                console.log("Loading functionality...");
//            loadFunctionalities (idProj);
                createLoadFunctionalities();
                loadAdjustmentFactors(idProj);
                clearResults();
            }
        });

        $("#createProject").click ( function (){
//            $("#pageForm").setAttribute("action","doCreateProject");

            var projectName=$("#projectName").val();
            var data = {projectName : projectName};
//            var url ="../projects";
//            var url="/FPA_Grails_G237/projects";
            %{--var url = "<g:createLink controller="projects"   />"; //action="doCreateProject"--}%
            var url = "<g:createLink controller="projects" action="doCreateProject"  />"; //
            console.log ("Post to:" +url);
            $.post (url,{
                        "projectName": projectName
                    },
                    function (result){
                    })
                    .done (function (result){
                        alert ("Project created successfully.");
                        location.reload();
                    })
                    .fail (function (xhr, status, text){
                        var response = xhr.response;
                        alert ("Status:"+status);
                        alert ("Text::"+text);
                        alert ("Error creating project:"+xhr.responseText);
                        alert ("Error: "+response.error);
            });

        });

          $("#deleteProject").click ( function (){
//              $("#pageForm").setAttribute("action","deleteProject");

              var projectId=$("#projects").val();
              var data = {projects : projectId};
              //var url = "<g:createLink controller="projects" action="deleteProject"  />"; //
              var url = "/FPA_Grails_G237/projects/"; //
              url +=projectId;

              $.ajax ({
                          url: url,
                          type:"DELETE",
                          data:{"projects":projectId}
                          //dataType:"json",
//                          "projects": projectId

                      })
                      .done (function (result){
                        var obj = $.parseJSON( result);
                        if (obj!=null && obj.result) {
                            alert("Project deleted successfully.");
                            location.reload();
                        }
                        else {
                            alert ("Could not delete project with id:"+projectId);
                        }
                        })
                      .fail (function (xhr, status, text){
                          var response = xhr.response;
                          alert ("Status:"+status);
                          alert ("Text::"+text);
                          alert ("Error deleting project:"+xhr.responseText);
                          alert ("Error: "+response.error);
              });

          });

          $("#showCreateProject").click(function (){
              $("#divCreateProject").dialog();
          });
    };


    function initProjects ()
    {
        $("#divCreateProject").hide();

        attacthProjectEvents ();
    }
</script>


<div>
    <p>Projects: <g:select id="projects"  name="projects" noSelection="${['-1':'Select One...']}" from="${viewBean.getProjects()}" optionValue="description" optionKey="id"/></p>
    <p><input type="button" name="deleteProject" id="deleteProject" value="Delete selected project" /></p>
    <input type="button" name="showCreateProject" id="showCreateProject"  value="Create Project" />
</div>

<div id="divCreateProject" name="divCreateProject" style="border:2px solid;border-radius:25px;padding:10px;">
    <p><g:textField name="projectName" id="projectName" />
        <input type="button" name="createProject" id="createProject"  value="Create Project" />
    </p>
</div>