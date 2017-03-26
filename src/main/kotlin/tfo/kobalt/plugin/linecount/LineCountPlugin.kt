package tfo.kobalt.plugin.linecount

import com.beust.kobalt.TaskResult
import com.beust.kobalt.api.BasePlugin
import com.beust.kobalt.api.KobaltContext
import com.beust.kobalt.api.Project
import com.beust.kobalt.api.annotation.Task
import com.beust.kobalt.misc.log
import java.nio.file.*
import java.nio.file.attribute.BasicFileAttributes

fun main(argv: Array<String>) {
    com.beust.kobalt.main(argv)
}

@Suppress("unused")
class LineCountPlugin : BasePlugin() {
    override val name = "kobalt-line-count"

    override fun apply(project: Project, context: KobaltContext) {
        println("*** Applying plugin $name with project $project.")
    }

    // Main.kt
    @Task(name = "lineCount", description = "Count the lines", runBefore = arrayOf("compile"))
    fun lineCount(project: Project): TaskResult {
        var fileCount = 0
        var lineCount: Long = 0
        val matcher = FileSystems.getDefault().getPathMatcher("glob:**.kt")
        project.sourceDirectories.forEach {
            val path = Paths.get(it)
            if (Files.isDirectory(path)) {
                Files.walkFileTree(path, object : SimpleFileVisitor<Path>() {
                    override fun visitFile(path: Path, attrs: BasicFileAttributes): FileVisitResult {
                        if (matcher.matches(path)) {
                            fileCount++
                            lineCount += Files.lines(path).count()
                        }
                        return FileVisitResult.CONTINUE
                    }
                })
            }
        }
        log(1, "Found $lineCount lines in $fileCount files")
        return TaskResult()
    }
}