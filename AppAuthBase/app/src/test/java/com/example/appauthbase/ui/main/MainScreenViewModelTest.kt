package com.example.appauthbase.ui.main

import com.example.appauthbase.domain.repository.AuthRepository
import com.example.appauthbase.presentation.RegisterViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RegisterViewModelTest {
    @Test
    fun register_emptyEmail_returnsError() = runTest {
        val viewModel = RegisterViewModel(FakeAuthRepository())
        viewModel.register("", "123456", "123456")
        val state = viewModel.ui.first()
        assertEquals("E-mail não pode ser vazio", state.error)
    }

    @Test
    fun register_shortPassword_returnsError() = runTest {
        val viewModel = RegisterViewModel(FakeAuthRepository())
        viewModel.register("test@example.com", "123", "123")
        val state = viewModel.ui.first()
        assertEquals("Senha deve ter pelo menos 6 caracteres", state.error)
    }

    @Test
    fun register_passwordMismatch_returnsError() = runTest {
        val viewModel = RegisterViewModel(FakeAuthRepository())
        viewModel.register("test@example.com", "123456", "654321")
        val state = viewModel.ui.first()
        assertEquals("As senhas não coincidem", state.error)
    }
}

private class FakeAuthRepository : AuthRepository {
    override suspend fun register(email: String, password: String): Result<Unit> {
        return Result.success(Unit)
    }
}
