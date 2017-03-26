
import com.beust.kobalt.file
import com.beust.kobalt.homeDir
import com.beust.kobalt.plugin.application.application
import com.beust.kobalt.plugin.packaging.assemble
import com.beust.kobalt.project


val p = project {

    name = "linecount"
    group = "tfo.kobalt"
    artifactId = name
    version = "0.3-SNAPSHOT"

    dependencies {
        compile("org.jetbrains.kotlin:kotlin-stdlib:1.1.1")
        compile(file(homeDir(".kobalt/wrapper/dist/kobalt-1.0.26/kobalt/wrapper/kobalt-1.0.26.jar")))
    }

    dependenciesTest {
        compile("junit:junit:4.12")
        compile("com.natpryce:hamkrest:1.3.0.0")
    }

    assemble {
        jar {
        }
    }

    application {
        mainClass = "com.example.MainKt"
    }
}
