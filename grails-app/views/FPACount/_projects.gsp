

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
                document.getElementById("result").style.visibility="hidden";
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

          $("#editProject").click (function (){

              var projectId=$("#projects").val();
              var data = {projects : projectId};
              var url = "<g:createLink controller="projects" action="doEdit"  />";
//              var url = "/FPA_Grails_G237/projects/"; //
//              url +=projectId;

              var projectName=$("#projectName").val();

              $.ajax ({
                  url: url,
                  type:"PUT",
                  dataType:"json",
                  data: JSON.stringify ({
                      "projects":projectId,
                      "projectName": projectName
                  })
              }).done (function (result){
                  console.log (result);
//                  var obj = $.parseJSON( result);
                  if (result!=null ) {
                      alert("Project edited successfully.");
                      location.reload();
                  }
                  else {
                      alert ("Could not save project with id:"+projectId);
                  }
              }) .fail (function (xhr, status, text){
                  var response = xhr.response;
                  alert ("Status:"+status);
                  alert ("Text::"+text);
                  alert ("Error editing project:"+xhr.responseText);
                  alert ("Error: "+response.error);
              });

          });

          $("#showEditProject").click(function () {
                  var proj =$("#projects").val();
//                  $("#idProject").val(proj);
              document.getElementById("idProject").value= proj;
                      var name= $('select[id=projects] option:selected').text();
//                  $("#projectName").val(name);
                  document.getElementById("projectName").value= name;
                $("#editProject").show();
                $("#createProject").hide();
                $("#divCreateProject").dialog();

          });


          $("#showCreateProject").click(function () {

              document.getElementById("projectName").value= "";
              $("#editProject").hide();
              $("#createProject").show();
              $("#divCreateProject").dialog();
          });

//          $("#exportExcel").click(function(e) {
//              window.open('data:application/vnd.ms-excel,' + $('#divFunctionalities').html());
//              e.preventDefault();
//          });



          $("#importExcel").click(function(e) {
              var url = "<g:createLink controller="projects" action="doImportProject"  />"; //
              console.log ("Post to:" +url);

              var fileName= $("#importFile").val();

                if (fileName!=null) {
                    $.post(url, {
                                "fileName": fileName
                            },
                            function (result) {
                            })
                            .done(function (result) {
                                alert("Project created successfully.");
                                location.reload();
                            })
                            .fail(function (xhr, status, text) {
                                var response = xhr.response;
                                alert("Status:" + status);
                                alert("Text::" + text);
                                alert("Error creating project:" + xhr.responseText);
                                alert("Error: " + response.error);
                            });
                }
              else
                alert ("Please select a file.");
          });

    };


    function getProjectVariables ()
    {
        var id = $("#projects").val();
        var url = "<g:createLink controller="projects" action="getProjectConfiguration"  />/"+id; //

        $.get(url, function (){

        }).done(function (data){
            var obj = $.parseJSON( data);
            document.getElementById ("productivity").value= obj.productivity;
        }).fail(function (data){
            alert ("fail:"+ data.error);
        });

    }

    function initProjects ()
    {
        $("#divCreateProject").hide();

        attacthProjectEvents ();

        getProjectVariables ();
    }
</script>


<div>
    <p>FPA_Webapp_G237.Projects: <g:select id="projects"  name="projects" noSelection="${['-1':'Select One...']}" from="${viewBean.getProjects()}" optionValue="description" optionKey="id"/></p>
    <p>Productivity FPs per Day: <input type="text" name="productivity" id="productivity" /> </p>

    <p>
       <input type="button" name="deleteProject" id="deleteProject" value="Delete selected project" />
       <input type="button" name="showEditProject" id="showEditProject"  value="Edit Project" />
       <input type="button" name="showCreateProject" id="showCreateProject"  value="Create Project" />
       <input type="button" name="exportExcel" id="exportExcel" value="Export to Excel" />

       <input type="file" name="importFile" id="importFile" value="Imported File" />
       <input type="button" name="importExcel" id="importExcel" value="Import Excel" />
    </p>

</div>


<div id="divCreateProject" name="divCreateProject" style="border:2px solid;border-radius:25px;padding:10px;">
    <input type="hidden" name="idProject" id="idProject"/>
    <p><input type="text" name="projectName" id="projectName" />
       <input type="button" name="createProject" id="createProject"  value="Create Project" />
       <input type="button" name="editProject" id="editProject"  value="Save Project" />
    </p>
</div>



