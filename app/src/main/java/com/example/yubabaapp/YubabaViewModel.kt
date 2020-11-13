package com.example.yubabaapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class YubabaViewModel: ViewModel() {

    var sendState: Boolean by mutableStateOf(false)
        private set
    var inputName: String by mutableStateOf("")
        private set
    var yubabaNaming: String by mutableStateOf("")
        private set

    fun onSendStateChanged(sendState: Boolean) {
        this.sendState = sendState
    }
    fun onNameSend(name: String) {
        this.inputName = name
        val newNameIndex: Int = Random.nextInt(name.length)
        val editName = name.substring(newNameIndex, newNameIndex + 1)
        yubabaNaming = editName
    }
}
