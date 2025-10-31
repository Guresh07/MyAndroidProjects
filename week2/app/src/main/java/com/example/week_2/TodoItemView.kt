package com.example.week_2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week_2.data.model.Todo

@Composable
fun TodoItemView(
    todo: Todo,
    onChecked: (Boolean) -> Unit = {},
    onDelete: () -> Unit = {},
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ){
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = todo.completed,
                onCheckedChange = { isChecked ->
                    onChecked(isChecked)
                }
            )
            Spacer(Modifier.width(12.dp))
            Text(
                text = todo.title,
                modifier = Modifier.weight(1f),
                fontSize = 16.sp,
                color = if(todo.completed) Color.Gray else Color.Black
            )

            Spacer(Modifier.width(12.dp))

            Text(
                text = "Remove",
                modifier = Modifier.padding(end = 16.dp)
                    .clickable{ onDelete() },
                color = Color.Red
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun TodoItemViewPreview() {
    TodoItemView(
        todo = Todo(1, "Test", false),
        onChecked = {},
        onDelete = {}
    )
}