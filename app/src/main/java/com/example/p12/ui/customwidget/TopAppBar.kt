package com.example.p12.ui.customwidget

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
  title: String,
  canNavigateBack: Boolean,
  modifier: Modifier = Modifier,
  scrollBehacior: TopAppBarScrollBehavior? = null,
  navigateUp: ()-> Unit = {},
  onRefresh: () -> Unit = {},
){}