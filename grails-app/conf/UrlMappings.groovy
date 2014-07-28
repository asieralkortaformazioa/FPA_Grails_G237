class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }


        /*
        "/projects/$id" (controller: "Functionalities") {
            action = [GET: "show", PUT: "update", DELETE: "delete"]
        }
        */


        "/"(view:"/index")
        "500"(view:'/error')
        //"/projects"(resources:'FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.Projects')
//        "/projects" (controller: "ProjectsController") {
//                action = [GET: "list" , POST: "doCreateProject"]
//        }


        //adjustmentFactor
        "/adjustmentFactor/$idProject" (controller: "AdjustmentFactor") {
            action = [POST: "save"]
        }


/* */
        //Functionalities
        "/functionalities/listProjectFunctionalities/$idProject" (controller: "Functionalities") {
            action = [GET: "listProjectFunctionalities"]
        }

        "/functionalities" (controller: "Functionalities") {
            action = [POST: "create", PUT:"edit"]
        }

        "/functionalities/$id" (controller: "Functionalities") {
            action = [DELETE: "delete"]
        }


        "/projects/getProjectConfiguration/$id" (controller: "Projects") {
            action = [GET: "getProjectConfiguration"]
        }

        "/projects/doEdit" (controller: "Projects") {
            action = [PUT:"doEdit"]
        }

        "/projects/doImportProject" (controller: "Projects") {
            action = [POST:"doImportProject"]
        }


        "/projects/$projects" (controller: "Projects") {
            action = [DELETE: "deleteProject", POST: "doCreateProject"]
        }


    }

}
