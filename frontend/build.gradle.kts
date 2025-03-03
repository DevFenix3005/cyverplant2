import com.github.gradle.node.npm.task.NpmTask
import com.github.gradle.node.npm.task.NpxTask
import com.github.gradle.node.task.NodeTask

/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    id("buildlogic.java-frontend-conventions")
}

node {
    download = true
}

tasks.create("helloWorld", NodeTask::class) {
    dependsOn("npmInstall")
    script = file("src/index.js")
}

tasks.create("init", NpmTask::class) {
    args.set(arrayListOf("init", "-y"))
}

tasks.create("installFiglet", NpmTask::class) {
    args.set(arrayListOf("install", "-y", "figlet"))
}
