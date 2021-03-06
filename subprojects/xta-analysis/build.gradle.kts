plugins {
    id("java-common")
}

dependencies {
    compile(project(":theta-analysis"))
    compile(project(":theta-common"))
    compile(project(":theta-core"))
    compile(project(":theta-xta"))
    testImplementation(project(":theta-solver-z3"))
}
