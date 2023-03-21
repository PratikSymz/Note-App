package com.example.note_app.feature_note.presentation.notes

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.note_app.feature_note.presentation.notes.components.NoteItem
import com.example.note_app.feature_note.presentation.notes.components.OrderingSection
import com.example.note_app.feature_note.presentation.util.Screens
import kotlinx.coroutines.launch

@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {
    // Reference for View model state
    val state = viewModel.state.value

    // Reference for Scaffold state to show SnackBars
    val scaffoldState = rememberScaffoldState()

    // Reference for coroutine scope for SnackBar
    val scope = rememberCoroutineScope()

    // Primary screen layout
    Scaffold(
        // FAB at bottom to add new note
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screens.AddEditNotesScreen.route) },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
            }
        },
        scaffoldState = scaffoldState,
    ) { padding ->
        // 1 Column for entire screen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            // R1: Heading and 'show filters' button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Your notes", style = MaterialTheme.typography.h4)

                IconButton(onClick = { viewModel.onEvent(NotesEvent.ToggleOrderSection) }) {
                    Icon(imageVector = Icons.Default.Sort, contentDescription = "Sort")
                }
            }

            // Animation based on whether order section visibility parameter
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                // Show the ordering section with animation
                OrderingSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    noteOrder = state.noteOrder,
                    onOrderChange = {
                        viewModel.onEvent(NotesEvent.Order(it))
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lazy list of all notes
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.notes) { note ->
                    NoteItem(
                        note = note,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Screens.AddEditNotesScreen.route
                                            + "?noteI=${note._id}&noteColor=${note.color}"
                                )
                            },
                        onDeleteClick = {
                            // Delete the note
                            viewModel.onEvent(NotesEvent.DeleteNote(note))
                            // Show SB with 'undo' option
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Note deleted",
                                    actionLabel = "Undo"
                                )

                                // If 'undo' button was clicked
                                if (result == SnackbarResult.ActionPerformed) {
                                    // Restore the note
                                    viewModel.onEvent(NotesEvent.RestoreNote)
                                }
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}