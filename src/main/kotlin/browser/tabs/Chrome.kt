@file:JsQualifier("browser.browser.getTabs")

package browser.tabs

import org.w3c.dom.events.Event
import org.w3c.dom.events.EventTarget
import kotlin.js.Promise


external fun query(queryInfo: QueryInfo) :Promise<Array<Tab>>

//external fun onActivated(type: String, noinline listener: (Event) -> Unit) =
//    addEventListener(type, listener)
////
//external fun executeScript(
//    tabId: Int? = definedExternally,
//    details: ExecuteScriptDetails
//): Promise<Array<dynamic>>


//chrome.browser.getTabs.onActivated.addListener(function callback)
//Parameters
//function	callback
//The callback parameter should be a function that looks like this:
//
//function(object activeInfo) {...};
//object	activeInfo
//integer	tabId
//The ID of the tab that has become active.
//
//integer	windowId
//The ID of the window the active tab changed inside of.



external interface QueryInfo {
    /** Whether the browser.getTabs are active in their windows */
    var active: Boolean?

    var currentWindow: Boolean?

    // other attributes
}

external interface Tab {
    /** The URL the tab is displaying */
    val url: String?

    // other attributes
}

external interface ActiveInfo {

//    The ID of the tab that has become active.
    val tabId: Int

//    The ID of the window the active tab changed inside of.
    val windowId: Int
}