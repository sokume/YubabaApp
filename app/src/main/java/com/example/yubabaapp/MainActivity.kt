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
import androidx.compose.runtime.*
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
fun YubabaTalk(){
    Column(
        modifier = Modifier.padding(16.dp),
    ) {
        IntroductionText(
                modifier = Modifier.padding(bottom = 8.dp)
        )
        InputOrResult(
                modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@Composable
fun IntroductionText(
        modifier: Modifier = Modifier)
{
    Text(
            text = "契約書だよ。そこに名前を書きな。",
            style = typography.h5,
            modifier = modifier
    )
}

@Composable
fun InputOrResult(
        modifier: Modifier = Modifier)
{
    val isSend = viewModel<YubabaViewModel>().sendState
    val inputState = remember { mutableStateOf(TextFieldValue()) }
    val viewModel = viewModel<YubabaViewModel>()

    Input(isSend,inputState,modifier)
    when {
        ! isSend -> {
            send(onClick = {
                viewModel.onSendStateChanged(true)
                viewModel.onNameSend(inputState.value.text)
            })
        }
        isSend -> {
            Result()
        }
    }
}

@Composable
fun Input(
        isSend: Boolean,
        inputState: MutableState<TextFieldValue>,
        modifier: Modifier)
{
    TextField(
            value = inputState.value,
            onValueChange = {
                inputState.value = it
            },
            backgroundColor = if (isSend) Color.Transparent else Color.DarkGray,
            modifier = modifier
    )
}

@Composable
fun send(
        onClick: () -> Unit,
        modifier: Modifier = Modifier)
{
    Button(
            onClick = onClick,
            modifier = modifier
    ) {
        Text("名前を教える")
    }
}

@Composable
fun Result(
        modifier: Modifier = Modifier)
{
    val inputName = viewModel<YubabaViewModel>().inputName
    val yubabaNaming = viewModel<YubabaViewModel>().yubabaNaming
    Text(
            text = "フン。${inputName}というのかい。贅沢な名だねぇ。",
            style = typography.h5,
            modifier = modifier
    )
    Text(
            text = "今からお前の名前は${yubabaNaming}だ。いいかい、${yubabaNaming}だよ。分かったら返事をするんだ、${yubabaNaming}!!",
            style = typography.h4
    )
}

@Preview
@Composable
fun DefaultPreview() {
    YubabaTalk()
}

