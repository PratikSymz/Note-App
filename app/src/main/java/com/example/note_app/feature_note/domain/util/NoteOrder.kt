package com.example.note_app.feature_note.domain.util

/**
 * This class defines the ways of ordering a list of notes -> by Title, Data or Color
 */
sealed class NoteOrder(val orderType: OrderType) {
    /*
        Order by 'Title', ASC or DESC
     */
    class Title(orderType: OrderType) : NoteOrder(orderType)

    /*
        Order by 'Date', ASC or DESC
     */
    class Date(orderType: OrderType) : NoteOrder(orderType)

    /*
        Order by 'Color', ASC or DESC
     */
    class Color(orderType: OrderType) : NoteOrder(orderType)

    /*
        Copy function to return a new NoteOrder using the provided OrderType
     */
    fun copy(orderType: OrderType): NoteOrder {
        return when (this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}
