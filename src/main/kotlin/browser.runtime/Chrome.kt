@file:JsQualifier("browser.runtime")


package browser.runtime

import browser.tabs.*
import kotlin.js.Promise

external interface MessageInfo {
    var extensionId: String ?              // optional string

    var message: String

    var options: Any?                  // optional object

}

external interface ConnectInfo {
    var extensionId: String ?              // optional string

    var message: String

    var options: Any?                  // optional object

}


external fun sendMessage(messageInfo: MessageInfo) :Promise<Array<Tab>>


//external var onMessage: Event<(messageInfo: OnMessageInfoType) -> Unit>

external var onConnect: Event<(connectInfo: Any) -> Unit>

external fun sendMessage(extensionId: Int? = definedExternally, message: String, options: Any? = definedExternally): Promise<Array<dynamic>>

