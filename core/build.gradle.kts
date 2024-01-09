
plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("com.vanniktech.maven.publish")
    id("org.jetbrains.dokka")
    id("org.jetbrains.kotlinx.kover")
    `maven-publish`
    id("kotlinx-atomicfu")
}

kotlin {
    androidTarget()
    jvm()
    iosArm64()
    iosX64()
    linuxX64()
    iosSimulatorArm64()
    js {
        browser()
        nodejs()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlin.stdlib)
            }
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/visionLab-de/Store")
            credentials {
                username = (project.findProperty("GITHUB_USERNAME") ?: System.getenv("GITHUB_USERNAME")).toString()
                password = (project.findProperty("GITHUB_TOKEN") ?: System.getenv("GITHUB_TOKEN")).toString()
            }
        }
    }
}

android {

    compileSdk = 33

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}
