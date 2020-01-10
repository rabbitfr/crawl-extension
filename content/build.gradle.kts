
plugins {
    id("org.jetbrains.kotlin.js")
}

apply {
    plugin("kotlin-dce-js")
}

group = "com.rabbit"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven ("https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.3.3")
//    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.6.12")
    implementation("com.github.cypressious.kotlin-webextensions-declarations:webextensions-declarations:v0.4")
}

group = "com.rabbit"
version = "1.0-SNAPSHOT"
