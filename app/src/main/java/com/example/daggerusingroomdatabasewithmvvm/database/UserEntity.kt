package com.example.daggerusingroomdatabasewithmvvm.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.daggerusingroomdatabasewithmvvm.database.UserEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID_COLUMN_NAME)
    val id: Int = 0,

    @ColumnInfo(name = NAME_COLUMN_NAME)
    val name: String
) {
    companion object {
        const val TABLE_NAME = "user_entity"

        const val ID_COLUMN_NAME = "id"
        const val NAME_COLUMN_NAME = "desc"
    }
}