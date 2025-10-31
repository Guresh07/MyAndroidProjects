package com.example.week_2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week_2.data.model.Todo

@Composable
fun TodoApp(){
    val todos = remember {
        mutableStateListOf(
            Todo(
                id = 1,
                title = "Todo 1",
                completed = false
            ),
            Todo(
                id = 2,
                title = "Todo 2",
                completed = true
            )
        )
    }

    // Input text for new todo — use rememberSaveable to keep across rotation

    var newTodoTitle by rememberSaveable {
        mutableStateOf("")
    }

    // derivedStateOf calculates values based on other state and avoids expensive recomputation

    val totalCount by remember {
        derivedStateOf { todos.size }
    }

    val completedCount by remember {
        derivedStateOf { todos.count{it.completed} }
    }


// Focus manager for keyboard dismissal
    val focusManager = LocalFocusManager.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F9FC))
            .padding(16.dp)
    ) {
        Text(
            text = "Todo List",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        AddTodo(
            text = newTodoTitle,
            onValueChange = { newTodoTitle = it },
            onSave = {
                val trimmed = newTodoTitle.trim()
                if (trimmed.isNotEmpty()) {
                    val newId = (todos.maxOfOrNull { it.id } ?: 0L) + 1L
                    todos.add(Todo(newId, trimmed, false))
                    newTodoTitle = ""
                    focusManager.clearFocus()
                }
            },
            onClear = {
                newTodoTitle = ""
                focusManager.clearFocus()
            }
        )

        Spacer(modifier = Modifier.height(12.dp))


        StatsRow(totalCount, completedCount)

        Spacer(modifier = Modifier.height(12.dp))

        if(todos.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(todos, key = { _, todo -> todo.id }) { index, todo ->
                    TodoItemView(
                        todo = todo,
                        onChecked = { isChecked ->
                            todos[index] = todos[index].copy(completed = isChecked)
                        },
                        onDelete = {
                            todos.removeAt(index)
                        }
                    )
                }
            }
        }else{
            Text(text = "No todos Yet. Add one above!", color = Color.Gray)
        }
    }
}


@Composable
@Preview(showBackground = true)
fun TodoAppPreview() {
    TodoApp()
}











