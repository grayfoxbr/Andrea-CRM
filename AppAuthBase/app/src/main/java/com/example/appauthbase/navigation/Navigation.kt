package com.example.appauthbase.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.appauthbase.presentation.AuthViewModel
import com.example.appauthbase.presentation.CompanyFormViewModel
import com.example.appauthbase.presentation.CompanyFormViewModelFactory
import com.example.appauthbase.presentation.CompanyListViewModel
import com.example.appauthbase.presentation.CompanyListViewModelFactory
import com.example.appauthbase.presentation.RegisterViewModel
import com.example.appauthbase.ui.CompanyFormScreen
import com.example.appauthbase.ui.CompanyListScreen
import com.example.appauthbase.ui.HomeScreen
import com.example.appauthbase.ui.LoginScreen
import com.example.appauthbase.ui.RegisterScreen

@Composable
fun MainNavigation(

  authViewModel: AuthViewModel,

  registerViewModel: RegisterViewModel,

  onLoginClick: () -> Unit

) {

  val uiState by
  authViewModel
    .uiState
    .collectAsState()

  val backStack =
    rememberNavBackStack(Login)

  LaunchedEffect(
    uiState.isLoggedIn
  ) {

    if (
      uiState.isLoggedIn &&
      backStack.lastOrNull() != Home
    ) {

      backStack.clear()

      backStack.add(
        Home
      )
    }

    if (
      !uiState.isLoggedIn &&
      backStack.lastOrNull() == Home
    ) {

      backStack.clear()

      backStack.add(
        Login
      )
    }
  }

  NavDisplay(

    backStack =
      backStack,

    onBack = {

      backStack
        .removeLastOrNull()
    },

    entryProvider =

      entryProvider {

        entry<Login> {

          LoginScreen(

            authViewModel =
              authViewModel,

            onLoginClick =
              onLoginClick,

            onRegisterClick = {

              backStack
                .add(
                  Register
                )
            }
          )
        }

        entry<Register> {

          RegisterScreen(

            registerViewModel =
              registerViewModel,

            onBack = {

              backStack
                .removeLastOrNull()
            }
          )
        }

        // Navigation.kt, dentro de entry<Home>
        entry<Home> {

          HomeScreen(

            onLogout = {
              authViewModel.logout()
            },

            onCompaniesClick = {
              backStack.add(CompanyList)
            },

            onNewCompanyClick = {
              backStack.add(CompanyForm())
            }
          )
        }

        entry<CompanyList> {

          val companyListViewModel: CompanyListViewModel =
            viewModel(factory = CompanyListViewModelFactory())

          CompanyListScreen(
            viewModel = companyListViewModel,
            onAddClick = {
              backStack.add(CompanyForm())
            },
            onCompanyClick = { id ->
              backStack.add(CompanyForm(companyId = id))
            },
            onBack = {
              backStack.removeLastOrNull()
            }
          )
        }

        entry<CompanyForm> { key ->

          val companyFormViewModel: CompanyFormViewModel =
            viewModel(
              key = "company_form_${key.companyId}",
              factory = CompanyFormViewModelFactory(companyId = key.companyId)
            )

          CompanyFormScreen(
            viewModel = companyFormViewModel,
            onSaveSuccess = {
              backStack.removeLastOrNull()
            },
            onBack = {
              backStack.removeLastOrNull()
            }
          )
        }
      }
  )
}