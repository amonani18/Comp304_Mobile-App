package com.mishra.sanidhya_aniket_ex2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.mishra.sanidhya_aniket_ex2.ui.Course
import com.mishra.sanidhya_aniket_ex2.ui.CourseDetailsScreen
import  com.mishra.sanidhya_aniket_ex2.ui.CourseListScreen
import com.mishra.sanidhya_aniket_ex2.ui.Program
import  com.mishra.sanidhya_aniket_ex2.ui.ProgramListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val selectedProgram = remember { mutableStateOf<Program?>(null) }
    val selectedCourse = remember { mutableStateOf<Course?>(null) }

    BackHandler(enabled = selectedProgram.value != null || selectedCourse.value != null) {
        when {
            selectedCourse.value != null -> selectedCourse.value = null
            selectedProgram.value != null -> selectedProgram.value = null
        }
    }

    when {
        selectedCourse.value != null -> CourseDetailsScreen(selectedCourse.value!!)
        selectedProgram.value != null -> CourseListScreen(
            program = selectedProgram.value!!,
            onCourseSelected = { course -> selectedCourse.value = course }
        )
        else -> ProgramListScreen { program -> selectedProgram.value = program }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}
