import browser.runtime.MessageInfo
import browser.tabs.ExecuteScriptDetails
import browser.tabs.QueryInfo
import browser.tabs.sendMessage
import kotlin.browser.document
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.callbackFlow

@Suppress("UNCHECKED_CAST_TO_NATIVE_INTERFACE")
inline fun QueryInfo(block: QueryInfo.() -> Unit) =
    (js("{}") as QueryInfo).apply(block)

@Suppress("UNCHECKED_CAST_TO_NATIVE_INTERFACE")
inline fun MessageInfo(block: MessageInfo.() -> Unit) =
    (js("{}") as MessageInfo).apply(block)


@Suppress("UNCHECKED_CAST_TO_NATIVE_INTERFACE")
inline fun ExecuteScriptDetails(block: ExecuteScriptDetails.() -> Unit) =
    (js("{}") as ExecuteScriptDetails).apply(block)

//
 fun getCurrentTabUrlAsync()  =

     GlobalScope.async {

         val queryInfo = QueryInfo {
             active = true
             currentWindow = true
         }

         val tabs = browser.tabs.query(queryInfo).await()

         println("browser.getTabs $tabs")

         val url = tabs[0].url

         url ?: throw RuntimeException("tab.url should be a string")
 }

fun changeBackgroundColor(color: String) {
    val script = """document.body.style.backgroundColor="$color";"""
    // See https://developer.mozilla.org/en-US/docs/Mozilla/Add-ons/WebExtensions/API/tabs/executeScript.
    // browser.tabs.executeScript allows us to programmatically inject JavaScript
    // into a page. Since we omit the optional first argument "tabId", the script
    // is inserted into the active tab of the current window, which serves as the
    // default.
    browser.tabs.executeScript(
        details = ExecuteScriptDetails {
            code = script
        }
    )
}


fun main() {

//    document.addEventListener(
//        type = "DOMContentLoaded",
//        callback = {
//
//            getCurrentTabUrl {  url ->
//                document.getElementById("message")?.textContent = "Hello from $url"
//            }
//        })
//    )
//
//    document.addEventListener(
//        type = "DOMContentLoaded",
//        callback = {
//
//
//            println("hello 2")
//        })
//
//    println("hello 3")

    document.onContentLoadedEventAsync {

        println("document loaded")

        val queryInfo = QueryInfo {
            active = true
            currentWindow = true
        }


        val tabs = browser.tabs.query(queryInfo).await()

        println("browser.getTabs ${tabs.size}")

        val url = getCurrentTabUrlAsync().await()

        println("Current tab url is $url")

//        browser.tabs.executeScript(
//            details = ExecuteScriptDetails {
//                code = """document.body.style.backgroundColor="red";"""
//            }
//        )

        val messageInfo = MessageInfo {
            message = "hello"
        }

        browser.tabs.executeScript(
            details = ExecuteScriptDetails {
                code = """
                    chrome.runtime.onMessage.addListener(
                      function(request, sender, sendResponse) {
                        console.log(sender.tab ?
                                    "from a content script:" + sender.tab.url :
                                    "from the extension");
                        if (request.message == "hello")
                          sendResponse({farewell: "goodbye"});
                      })
                """.trimIndent()
            }
        ).await()

        val result = browser.runtime.sendMessage(messageInfo)

        println("results ${result.await()}")
//        browser.runtime.onConnect.addListener(callback = {
//            println("received a message")
//        })

//        browser.runtime.on
//        println(browser.runtime.s)

//        browser.runtime.onMessage.addListener(callback = {
//            println("Received a message")
//        })

//        browser.tabs.executeScript(
//            details = ExecuteScriptDetails {
//                code = """
//                    chrome.runtime.sendMessage({greeting: "hello"}, function(response) {
//                      console.log(response);
//                    });
//                """.trimIndent()
//            }
//        )

//        changeBackgroundColor("red")
//
//        browser.tabs.onActivated.addListener(callback = {
//            println("New tab activated")
//        })

    }

//    browser.browser.getTabs.onA
}
