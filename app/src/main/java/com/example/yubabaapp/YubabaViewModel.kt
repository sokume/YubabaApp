package com.example.yubabaapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class YubabaViewModel: ViewModel() {

    private val _sendState = MutableLiveData(false)
    val sendState: LiveData<Boolean> = _sendState

    private val _inputName = MutableLiveData("")
    val inputName: LiveData<String> = _inputName

    private val _yubabaNaming = MutableLiveData("")
    val yubabaNaming: LiveData<String> = _yubabaNaming

    fun onSendStateChanged(sendState: Boolean) {
        _sendState.value = sendState
    }
    fun onNameSend(name: String) {
        _inputName.value = name
        val newNameIndex: Int = Random.nextInt(name.length)
        val editName = name.substring(newNameIndex, newNameIndex + 1)
        _yubabaNaming.value = editName
    }
}
