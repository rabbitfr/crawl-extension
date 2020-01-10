import extensionTypes.InjectDetails
import kotlinx.coroutines.await
import webextensions.browser
import kotlin.browser.document
import kotlin.js.Promise

inline fun jsObject(init: dynamic.() -> Unit): dynamic {
    val o = js("{}")
    init(o)
    return o
}

fun main() {
    println("content script has been injected")

    browser.runtime.sendMessage( null, jsObject {
        command = "beastify"
        beastURL = url
    })
        .catch {
        println("error sending message ${it.message}")

    }

    println("beastify")

}
