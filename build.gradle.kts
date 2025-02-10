plugins {
    alias(libs.plugins.kotlin)
}

version = "1.0"

kotlin {
    jvmToolchain(11)
}

dependencies {
    compileOnly(libs.arc)
    compileOnly(libs.mindustry)
}

tasks {
    register<Exec>("jarAndroid") {
        dependsOn("jar")

        doLast {
            val sdkRoot = System.getenv("ANDROID_HOME") ?: System.getenv("ANDROID_SDK_ROOT")

            if (sdkRoot == null || !File(sdkRoot).exists()) {
                logger.error("No valid Android SDK found. Ensure that ANDROID_HOME is set to your Android SDK directory.")
            }

            val platformRoot = File("$sdkRoot/platforms").listFiles()
                ?.sortedDescending()
                ?.find { File(it, "android.jar").exists() }
                ?: throw GradleException("No android.jar found. Ensure that you have an Android platform installed.")

            val dependencies = (configurations.compileClasspath.get().files +
                    configurations.runtimeClasspath.get().files +
                    File(platformRoot, "android.jar"))
                .joinToString(" ") { "--classpath ${it.path}" }

            commandLine(
                "d8", dependencies, "--min-api", "14", "--output",
                "${project.name}Android.jar", "${project.name}Desktop.jar"
            )

            workingDir = file("${layout.buildDirectory}/libs")
        }
    }

    jar {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        archiveFileName = "${project.name}Desktop.jar"

        from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })

        from("${projectDir}/assets/") {
            include("**")
        }
    }

    register<Jar>("deploy") {
        dependsOn("jarAndroid", "jar")

        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        archiveFileName.set("${project.name}.jar")

        from(
            zipTree("${layout.buildDirectory}/libs/${project.name}Desktop.jar"),
            zipTree("${layout.buildDirectory}/libs/${project.name}Android.jar")
        )

        doLast {
            delete(
                "${layout.buildDirectory}/libs/${project.name}Desktop.jar",
                "${layout.buildDirectory}/libs/${project.name}Android.jar"
            )
        }
    }
}