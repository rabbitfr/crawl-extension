plugins {
    id("org.jetbrains.kotlin.js") version "1.3.61"
}

apply {
    plugin("kotlin-dce-js")
}

group = "com.rabbit"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.3.3")
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.6.12")
}

tasks.register<Copy>("copyWeb") {
    from(file("src/main/web"))
    into("$buildDir/extension")
}

tasks.register<Copy>("copyBundle") {
    from(files(
        "$buildDir/kotlin-js-min/main/crawl-extension-2.js",
        "$buildDir/kotlin-js-min/main/kotlin.js",
        "$buildDir/kotlin-js-min/main/kotlinx-coroutines-core.js"))

    into("$buildDir/extension")
}

tasks.named("assemble") {               // (5)
    dependsOn(":copyWeb")
    dependsOn(":copyBundle")
}

kotlin.target.browser { }