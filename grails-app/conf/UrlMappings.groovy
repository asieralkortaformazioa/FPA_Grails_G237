class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        //"/projects"(resources:'Projects')
        "/projects" (controller: "Projects") {
                action = [GET: "list" , POST: "create"]
        }

        /*
        "/projects/$id" (controller: "Functionalities") {
            action = [GET: "show", PUT: "update", DELETE: "delete"]
        }
        */

        //Functionalities
        "/functionalities/listProjectFunctionalities/$idProject" (controller: "Functionalities") {
            action = [GET: "listProjectFunctionalities"]
        }


    }
}
