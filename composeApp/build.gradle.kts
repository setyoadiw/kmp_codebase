import com.android.build.api.dsl.androidLibrary
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.android.kotlin.multiplatform.library)

    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

kotlin {
//    androidTarget {
//        compilations.all {
//            compileTaskProvider.configure {
//                compilerOptions {
//                    jvmTarget.set(JvmTarget.JVM_1_8)
//                }
//            }
//        }
//    }

    
    androidLibrary {
        namespace = "com.setyo.mycmpapplication.shared"
        compileSdk = 35

        //sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
        //sourceSets["main"].res.srcDirs("src/androidMain/res")
        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compilations.configureEach {
            compilerOptions.configure {
                jvmTarget.set(
                    JvmTarget.JVM_1_8
                )
            }
        }
        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "composeApp"
            isStatic = true
        }
    }

    room {
        schemaDirectory("$projectDir/schemas")
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            //lifecycle
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.jetbrains.compose.navigation)

            implementation(libs.kotlinx.serialization.json)

            //room
            implementation(libs.androidx.room.runtime)
            implementation(libs.sqlite.bundled)

            //koin
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            api(libs.koin.core)

            //ktor
            implementation(libs.bundles.ktor)

            //coil
            implementation(libs.bundles.coil)
        }
        androidMain.dependencies {
            api(compose.preview)

            api(libs.androidx.core.splashscreen)
            api(libs.androidx.activity.compose)
            api(libs.androidx.core.ktx)

            api(libs.koin.android)
            api(libs.koin.androidx.compose)


            api(libs.ktor.serialization.kotlinx.json)

            // Firebase
//            api(libs.kotlinx.coroutines.play.services)
//            api(project.dependencies.platform(libs.firebase.bom))
//            api(libs.firebase.analytics.ktx)
//            api(libs.firebase.crashlytics.ktx)
//            api(libs.firebase.inappmessaging.ktx)
//            api(libs.firebase.inappmessaging.display.ktx)
//            api(libs.firebase.database.ktx)
//            api(libs.firebase.auth.ktx)
//            api(libs.firebase.messaging)
//            api(libs.firebase.config.ktx)

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        dependencies {
            ksp(libs.androidx.room.compiler)
        }
    }
}