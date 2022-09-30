package com.surodeev.currenciestestapplication.ui.widget.filter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.surodeev.currenciestestapplication.ui.viewmodel.FilterViewModel

@ExperimentalMaterialApi
@Composable
fun FilterBottomSheet(
    initialSortVariant: FilterViewModel.SortVariant,
    changeSortState: (sortVariant: FilterViewModel.SortVariant) -> Unit
) {
    val viewModel: FilterViewModel = viewModel()

    viewModel.currentSortState = initialSortVariant
    Column(
        Modifier.padding(start = 32.dp, end = 32.dp, top = 16.dp, bottom = 32.dp)
    ) {
        BottomSheetPuller()
        Spacer(Modifier.height(24.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Сортировка",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(Modifier.height(24.dp))
        Text(
            text = "По алфавиту:",
            style = MaterialTheme.typography.body1,
            fontSize = 18.sp
        )
        Column(
            Modifier.padding(start = 16.dp)
        ) {
            CheckboxItem(
                checked = viewModel.currentSortState == FilterViewModel.SortVariant.ALPHABET_ASC,
                title = "По возрастанию"
            ) {
                viewModel.currentSortState = FilterViewModel.SortVariant.ALPHABET_ASC
                changeSortState(viewModel.currentSortState)
            }
            CheckboxItem(
                checked = viewModel.currentSortState == FilterViewModel.SortVariant.ALPHABET_DESC,
                title = "По убыванию"
            ) {
                viewModel.currentSortState = FilterViewModel.SortVariant.ALPHABET_DESC
                changeSortState(viewModel.currentSortState)
            }
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text = "По значению:",
            style = MaterialTheme.typography.body1,
            fontSize = 18.sp
        )
        Column(
            Modifier.padding(start = 16.dp)
        ) {
            CheckboxItem(
                checked = viewModel.currentSortState == FilterViewModel.SortVariant.VALUE_ASC,
                title = "По возрастанию"
            ) {
                viewModel.currentSortState = FilterViewModel.SortVariant.VALUE_ASC
                changeSortState(viewModel.currentSortState)
            }
            CheckboxItem(
                checked = viewModel.currentSortState == FilterViewModel.SortVariant.VALUE_DESC,
                title = "По убыванию"
            ) {
                viewModel.currentSortState = FilterViewModel.SortVariant.VALUE_DESC
                changeSortState(viewModel.currentSortState)
            }
        }
    }

}