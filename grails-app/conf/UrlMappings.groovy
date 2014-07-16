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
        //"/projects"(resources:'FPA_Webapp_G237.FPA_Webapp_G237.FPA_Webapp_G237.Projects')
        "/projects" (controller: "Projects") {
                action = [GET: "list" , POST: "create"]
        }


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




    }

}
