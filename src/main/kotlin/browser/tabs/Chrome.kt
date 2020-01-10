@file:JsQualifier("browser.tabs")

package browser.tabs

import org.w3c.dom.events.EventTarget
import kotlin.js.Promise

external interface OnActivatedActiveInfoType {
    var tabId: Number
    var previousTabId: Number?
    var windowId: Number
}

external interface Event<T : Function<*>> {
    fun addListener(callback: T, vararg params: Any)
    fun removeListener(callback: T)
    fun hasListener(callback: T): Boolean
    fun hasListeners(): Boolean
}

external var onActivated:Event<(activeInfo: OnActivatedActiveInfoType) -> Unit>

external fun query(queryInfo: QueryInfo) :Promise<Array<Tab>>

external fun executeScript(
    tabId: Int? = definedExternally,
    details: ExecuteScriptDetails
): Promise<Array<dynamic>>

external fun sendMessage(extensionId: Int? = definedExternally, message: String, options: Any? = definedExternally): Promise<Array<dynamic>>


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

external interface MutedInfo {

    /**
     * Whether the tab is prevented from playing sound (but hasn't necessarily recently produced sound).
     * Equivalent to whether the muted audio indicator is showing.
     */
    val muted: Boolean

    /**
     * The reason the tab was muted or unmuted.
     * Not set if the tab's mute state has never been changed.
     */
    val reason: String?

    /**
     * The ID of the extension that changed the muted state.
     * Not set if an extension was not the reason the muted state last changed.
     */
    val extensionId: String?
}

external interface ExecuteScriptDetails {

    /**
     * JavaScript or CSS code to inject.
     *
     * Warning:
     * Be careful using the code parameter.
     * Incorrect use of it may open your extension to cross site scripting attacks.
     */
    var code: String?

    /** JavaScript or CSS file to inject. */
    var file: String?

    /**
     * If allFrames is true, implies that the JavaScript or CSS should be injected into all frames of current page.
     * By default, it's false and is only injected into the top frame.
     * If true and frameId is set, then the code is inserted in the selected frame and all of its child frames.
     */
    var allFrames: Boolean?

    /**
     * The frame where the script or CSS should be injected. Defaults to 0 (the top-level frame).
     * Since Chrome 39.
     */
    var frameId: Int?

    /**
     * If matchAboutBlank is true, then the code is also injected in about:blank and about:srcdoc frames if your extension has access to its parent document.
     * Code cannot be inserted in top-level about:-frames. By default it is false.
     * Since Chrome 39.
     */
    var matchAboutBlank: Boolean?

    /**
     * The soonest that the JavaScript or CSS will be injected into the tab. Defaults to "document_idle".
     */
    var runAt: String?
}