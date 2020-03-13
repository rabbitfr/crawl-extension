import extensionTypes.InjectDetails
import kotlinx.coroutines.await
import org.w3c.dom.HTMLSpanElement
import org.w3c.dom.MutationObserver
import org.w3c.dom.MutationObserverInit
import org.w3c.dom.Text
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventTarget
import webextensions.browser
import kotlin.browser.document
import kotlin.browser.window
import kotlin.js.Promise

inline fun jsObject(init: dynamic.() -> Unit): dynamic {
    val o = js("{}")
    init(o)
    return o
}

inline fun EventTarget.onEvent(type: String, noinline listener: (Event) -> Unit) =
    addEventListener(type, listener)

inline fun EventTarget.onChangeEvent(noinline listener: (Event) -> Unit) =
    onEvent("DOMSubtreeModified", listener)

val mutationObserverInit = MutationObserverInit(
    childList = true
)

fun main() {
    println("content script has been injected !")

    browser.runtime.sendMessage(null, jsObject {
        command = "beastify"
        beastURL = url
    })
        .catch {
            println("error sending message ${it.message}")

        }

    /**
     * @See https://github.com/crawl/crawl/blob/9ac4296c3e985dce72eba138d0ba55117b7ca565/crawl-ref/source/branch-data.h for locations
     */

    val gameLocation = document.getElementById("stats_place") as? HTMLSpanElement
        ?: throw RuntimeException("the DOM has no 'stats_place' id")


//    gameLocation.app
//    println("location ${gameLocation.innerText}")
//
//    gameLocation.onChangeEvent { println("location changed") }

//    gameLocation.addEventListener("DOMCharacterDataModified", { println("location changed to $it") } )
//
//    gameLocation {
//        onChangeFunction = { event ->
//            window.alert("Kotlin!")
//        }
//    }

    gameLocation.onChangeEvent {     println("location changed to '${gameLocation.innerText}'")  }

//    val textNode = gameLocation.firstChild  as Text

//    println("text node : ${textNode.textContent}")

    val observer = MutationObserver { records, _ ->  ( println("observed ${gameLocation.textContent} oldValue ${records[0].oldValue}"))  }

    observer.observe(gameLocation, mutationObserverInit )

//    gameLocation.innerText = "test"

//    gameLocation.addEventListener(
//    gameLocation.addEventListener(
//        type = "change",
//        callback = {
//            println("location changed")
//        })



    println("beastify")

    document.addEventListener(
        type = "DOMContentLoaded",
        callback = {
            println("dom loaded")
        })

}
