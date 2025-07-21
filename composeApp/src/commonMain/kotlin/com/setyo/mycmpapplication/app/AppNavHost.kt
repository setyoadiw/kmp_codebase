package com.setyo.mycmpapplication.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.setyo.mycmpapplication.news.presentation.page.news.NewsPage
import com.setyo.mycmpapplication.news.presentation.page.news.NewsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoute.NewsPage
    ) {

        composable<AppRoute.NewsPage>(
            exitTransition = { slideOutHorizontally() },
            popEnterTransition = { slideInHorizontally() }
        ) {
            val viewModel = koinViewModel<NewsViewModel>()
            NewsPage(
                viewModel = viewModel,
            )
        }

//        composable<AppRoute.NewsPage>(
//            exitTransition = { slideOutHorizontally() },
//            popEnterTransition = { slideInHorizontally() }
//        ) {
//            val viewModel = koinViewModel<NewsViewModel>()
//            val selectedBookViewModel =
//                it.sharedKoinViewModel<SelectedBookViewModel>(navController)
//
//            LaunchedEffect(true) {
//                selectedBookViewModel.onSelectBook(null)
//            }
//
//            BookListScreenRoot(
//                viewModel = viewModel,
//                onBookClick = { book ->
//                    selectedBookViewModel.onSelectBook(book)
//                    navController.navigate(
//                        Route.BookDetail(book.id)
//                    )
//                }
//            )
//        }
    }

}

@Composable
private inline fun <reified T: ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}