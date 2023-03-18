package com.example.note_app.feature_note.domain.util

/**
 * This class defines the ways of ordering notes -> ASC or DESC
 */
sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
