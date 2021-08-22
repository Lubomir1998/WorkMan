package com.example.workman

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.workman.ui.theme.WorkManTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkManTheme {
                MainUI(modifier = Modifier.fillMaxSize()) {
                    doSomeWork()
                }
            }
        }
    }


    private fun doSomeWork() {
        val uploadWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<WorkClass>()
                .build()

        WorkManager
            .getInstance(applicationContext)
            .enqueue(uploadWorkRequest)
    }
}




@Composable
fun MainUI(
    modifier: Modifier = Modifier,
    btnClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Button(onClick = btnClick) {
            Text(
                text = "Work",
                fontSize = 20.sp
            )
        }
    }
}
