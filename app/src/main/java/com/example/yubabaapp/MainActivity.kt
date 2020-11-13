package com.example.yubabaapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import com.example.yubabaapp.ui.YubabaAppTheme


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YubabaAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    YubabaTalk()
                }
            }
        }
    }
}

@Composable
fun YubabaTalk(yubabaViewModel: YubabaViewModel = viewModel()){

    val isSend: Boolean = yubabaViewModel.sendState
    val inputName: String = yubabaViewModel.inputName
    val yubabaNaming: String = yubabaViewModel.yubabaNaming

    Column(
        modifier = Modifier.padding(16.dp),
    ) {
        Text(
            text = "契約書だよ。そこに名前を書きな。",
            style = typography.h5,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        val textState = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            value = textState.value,
            onValueChange = {
                if (!isSend) {
                    textState.value = it
                }
            },
            backgroundColor = if (isSend) Color.Transparent else Color.DarkGray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        when {
            !isSend -> {
                Button(
                    onClick = {
                        yubabaViewModel.onSendStateChanged(true)
                        yubabaViewModel.onNameSend(textState.value.text)
                    },
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text("名前を教える")
                }
            }
            isSend -> {
                Text(
                    text = "フン。${inputName}というのかい。贅沢な名だねぇ。",
                    style = typography.h5,
                    modifier = Modifier.padding(bottom = 8.dp)

                )
                Text(
                    text = "今からお前の名前は${yubabaNaming}だ。いいかい、${yubabaNaming}だよ。分かったら返事をするんだ、${yubabaNaming}!!",
                    style = typography.h4
                )
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    YubabaTalk()
}

