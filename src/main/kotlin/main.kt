import browser.tabs.QueryInfo
import kotlin.browser.document
import kotlinx.coroutines.*

@Suppress("UNCHECKED_CAST_TO_NATIVE_INTERFACE")
inline fun QueryInfo(block: QueryInfo.() -> Unit) =
    (js("{}") as QueryInfo).apply(block)

//@Suppress("UNCHECKED_CAST_TO_NATIVE_INTERFACE")
//inline fun ExecuteScriptDetails(block: ExecuteScriptDetails.() -> Unit) =
//    (js("{}") as ExecuteScriptDetails).apply(block)

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


//        val tabs = browser.tabs.query(queryInfo).await()
//
//        println("browser.getTabs ${tabs.size}")
//
//        val url = getCurrentTabUrlAsync().await()
//
//        println("Current tab url is $url")
//
//

    }

//    browser.browser.getTabs.onA
}
