package com.example.note_app.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.note_app.ui.theme.*

@Entity
data class Note(
    @PrimaryKey val _id: Int? = null,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int
) {
    // List of possible colors for the note
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}