package com.sample.jetpack

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {
    @Insert
    fun insertStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)

    @Update
    fun updateStudent(student: Student)

    @Query("SELECT * FROM student")
    fun getStudentList() : List<Student>

    @Query("SELECT * FROM student WHERE id = :id")
    fun getStudentById(id: Int) : Student

    @Query("SELECT * FROM student ORDER BY age limit 1")
    fun getOldestStudent() : LiveData<Student>
}
