class Project {
    var name: String = ""
    var appId: String = ""
    fun getFlavorName(): String = name.toLowerCase().replace(" ", "_")
}

inline fun projects(body: MutableList<Project>.() -> Unit) : List<Project> {
    val projects = mutableListOf<Project>()
    projects.body()
    return projects
}

private inline fun MutableList<Project>.create(constructor: Project.() -> Unit) : Project {
    val project = Project()
    project.constructor()
    add(project)
    return project
}

val projects = projects {
    create {
        name = "Dev"
        appId = "com.dev"
    }
}
