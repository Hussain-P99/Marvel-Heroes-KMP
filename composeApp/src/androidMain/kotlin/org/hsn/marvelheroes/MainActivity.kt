package org.hsn.marvelheroes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hsn.marvelheroes.ui.theme.MarvelHeroesTheme
import org.hsn.marvelheroes.ui.MainViewModel
import org.hsn.marvelheroes.ui.characterDetails.CharacterDetails
import org.hsn.marvelheroes.ui.characterList.HomeScreen
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllCharacters(false)
        setContent {
            val navController = rememberNavController()

            MarvelHeroesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable(route = "home") {
                            HomeScreen(viewModel = viewModel) {
                                navController.navigate("detail/$it")
                            }
                        }

                        composable("detail/{id}", arguments = listOf(
                            navArgument("id") {
                                type = NavType.StringType
                            }
                        )) {
                            val id = it.arguments?.getString("id") ?: ""
                            CharacterDetails(viewModel, id, Modifier.padding(horizontal = 8.dp), {
                                navController.popBackStack()
                            }, { url ->
                                if (url.isNotBlank()) {
                                    val uri = Uri.parse(url)
                                    val intent = Intent(Intent.ACTION_VIEW, uri)
                                    if (intent.resolveActivity(this@MainActivity.packageManager) != null) {
                                        startActivity(intent)
                                    }
                                }
                            })
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}