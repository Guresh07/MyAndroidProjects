package com.example.week_2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddTodo(
    text: String,
    onValueChange: (String) -> Unit,
    onSave: () -> Unit,
    onClear:() -> Unit
){
    Column(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        TextField(
            value = text,
            onValueChange = onValueChange,
            placeholder = { Text("What needs to be done?") },
            singleLine = true,
            modifier = Modifier.weight(1f),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { onSave() })
        )
    }
//        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = onSave, enabled = text.isNotBlank()) {
            Text("Add")
        }
        Spacer(Modifier.width(8.dp))
        OutlinedButton(onClick = onClear, enabled = text.isNotBlank()) {
            Text("Clear")
        }
    }
    }
}


@Composable
@Preview(showBackground = true)
fun AddTodoPreview(){
    AddTodo(
        text = "",
        onValueChange = {},
        onSave = {},
        onClear = {}
    )

}