@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

val mavenUser: String by settings
val mavenPassword: String by settings

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        maven(url = "https://gilds-nexus.signintra.com/repository/maven-snapshots/") {
            credentials {
                username = mavenUser
                password = mavenPassword
            }
            content {
                includeGroupAndSubgroups("com.dbschenker")
            }
            mavenContent {
                snapshotsOnly()
            }
        }
        maven(url = "https://gilds-nexus.signintra.com/repository/maven-releases/") {
            credentials {
                username = mavenUser
                password = mavenPassword
            }
            mavenContent {
                includeGroupAndSubgroups("com.dbschenker")
                releasesOnly()
            }
        }
    }
}

rootProject.name = "scopesToTheRescue"
include(":app")
