package com.mishra.sanidhya_aniket_ex2.ui

data class Course(val name: String, val description: String)
data class Program(val name: String, val courses: List<Course>)
