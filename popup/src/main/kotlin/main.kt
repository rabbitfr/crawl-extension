import extensionTypes.InjectDetails
import org.w3c.dom.Audio
import org.w3c.dom.AudioTrack
import webextensions.browser
import kotlin.browser.document
import kotlin.js.Promise

fun main() {
    println(">> popup 6 main")

    Promise.all(arrayOf(
        browser.tabs.executeScript(
            details = InjectDetails(file = "kotlin.js")
        ),
        browser.tabs.executeScript(
            details = InjectDetails(file = "declarations.js")
        ),
        browser.tabs.executeScript(
            details = InjectDetails(file = "browser-polyfill.min.js")
        ),
        browser.tabs.executeScript(
            details = InjectDetails(file = "content.js")
        )
    ))
        .catch(::reportExecuteScriptError)

    println("<< popup 6 main")

    browser.runtime.onMessage.addListener { message,_,_ ->

        println("onMessage");
        if (message.command === "beastify") {
//            insertBeast(message.beastURL as String)
            println("received message ")
        } else if (message.command === "reset") {
//            removeExistingBeasts()
        }
    }

    val test = Audio("https://sounds.tabletopaudio.com/194_Tarrasque_Interior.mp3")

    test.play()
}


fun reportExecuteScriptError(error: Throwable) {
    document.querySelector("#popup-content")?.classList?.add("hidden")
    document.querySelector("#error-content")?.classList?.remove("hidden")
    console.error("Failed to execute beastify content script: ${error.message}")
}