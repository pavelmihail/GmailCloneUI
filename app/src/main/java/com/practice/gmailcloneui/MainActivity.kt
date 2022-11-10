package com.practice.gmailcloneui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.practice.gmailcloneui.componets.*
import com.practice.gmailcloneui.ui.theme.GmailCloneUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GmailCloneUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GmailApp()
                }
            }
        }
    }
}

@Composable
fun GmailApp() {

    val scaffoldState = rememberScaffoldState()
    val couroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val openDialog = remember {
        mutableStateOf(false)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeAppBar(
                scaffoldState = scaffoldState, scope = couroutineScope, openDialog = openDialog
            )
        },
        drawerContent = {
            GmailDrawerMenu(scrollState)
        },
        bottomBar = {
            HomeBottomMenu()
        },
        floatingActionButton = { GmailFab(scrollState) }
    ) {
        MailList(it, scrollState)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GmailCloneUITheme {
        GmailApp()
    }
}