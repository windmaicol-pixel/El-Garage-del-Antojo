plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.9.22"
    id("org.jlleitschuh.gradle.ktlint")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")

                // Serialization
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

                // DateTime
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")

                // Firebase (GitLive)
                implementation("dev.gitlive:firebase-auth:1.12.0")
                implementation("dev.gitlive:firebase-firestore:1.12.0")
                implementation("dev.gitlive:firebase-storage:1.12.0")

                // Koin for DI (SOLID - Dependency Inversion)
                implementation("io.insert-koin:koin-core:3.5.3")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
                implementation("io.insert-koin:koin-test:3.5.3")
                implementation("io.mockative:mockative:2.2.0")
                implementation("app.cash.turbine:turbine:1.0.0")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("dev.gitlive:firebase-auth:1.12.0")
                implementation("dev.gitlive:firebase-firestore:1.12.0")
                implementation("com.google.android.gms:play-services-auth:20.7.0")
            }
        }

        val iosMain by creating {
            dependsOn(commonMain)
        }

        val iosX64Main by getting {
            dependsOn(iosMain)
        }

        val iosArm64Main by getting {
            dependsOn(iosMain)
        }

        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
    }
}

android {
    namespace = "com.elgaragedelantojo"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
