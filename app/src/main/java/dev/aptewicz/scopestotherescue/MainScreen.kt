package dev.aptewicz.scopestotherescue

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dbschenker.mobile.compose.uicomponents.preview.DefaultPreview
import com.dbschenker.mobile.compose.uicomponents.theme.spacing
import com.dbschenker.mobile.compose.uicomponents.ui.button.StandardButton
import dev.aptewicz.scopestotherescue.app.theme.ScopesToTheRescueTheme
import dev.aptewicz.scopestotherescue.binarycounter.ui.BinaryCounterScreen
import dev.aptewicz.scopestotherescue.decimalcounter.ui.DecimalCounterScreen

enum class Screen {
    DecimalCounter,
    BinaryCounter,
    Menu,
}

@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf(Screen.Menu) }
    when (currentScreen) {
        Screen.DecimalCounter -> {
            DecimalCounterScreen()
        }

        Screen.BinaryCounter -> {
            BinaryCounterScreen()
        }

        Screen.Menu -> {
            MenuScreen(
                onDecimalCounterClick = { currentScreen = Screen.DecimalCounter },
                onBinaryCounterClick = { currentScreen = Screen.BinaryCounter },
            )
        }
    }

    BackHandler(enabled = currentScreen != Screen.Menu) {
        currentScreen = Screen.Menu
    }
}

@Composable
private fun MenuScreen(
    onDecimalCounterClick: () -> Unit = {},
    onBinaryCounterClick: () -> Unit = {},
) {
    ScopesToTheRescueTheme {
        Scaffold { innerPadding ->
            Column(
                modifier =
                    Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("MENU", style = MaterialTheme.typography.headlineLarge)
                Spacer(Modifier.height(MaterialTheme.spacing.medium))
                StandardButton(label = "Decimal counter", onClick = onDecimalCounterClick)
                Spacer(Modifier.height(MaterialTheme.spacing.medium))
                StandardButton(label = "Binary counter", onClick = onBinaryCounterClick)
            }
        }
    }
}

@DefaultPreview
@Composable
fun MenuScreenPreview() {
    MenuScreen()
}
