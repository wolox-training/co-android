package ar.com.wolox.android.example.ui.auth.login

import ar.com.wolox.android.example.model.LoginResponse
import ar.com.wolox.android.example.network.repository.LoginRepository
import ar.com.wolox.android.example.utils.Extras
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.tests.CoroutineTestRule
import ar.com.wolox.wolmo.core.tests.WolmoPresenterTest
import ar.com.wolox.wolmo.networking.retrofit.handler.NetworkResponse
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import retrofit2.Response
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.nhaarman.mockitokotlin2.doReturn
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class AuthPresenterTest : WolmoPresenterTest<AuthView, AuthPresenter>() {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule(runOnAllTests = true)

    @Mock
    lateinit var userSession: UserSession

    @Mock
    lateinit var loginRepository: LoginRepository

    override fun getPresenterInstance() = AuthPresenter(loginRepository, userSession)

    @Test
    fun `When the email is empty`() = runBlocking {
        presenter.onLoginButtonClicked("", Extras.MockTesting.PASSWORD).join()
        val errors: List<LoginFormErrors> = listOf(
            LoginFormErrors.INVALID_EMAIL,
            LoginFormErrors.EMPTY_EMAIL
        )

        verify(view, times(1)).setErrors(errors)
    }

    @Test
    fun `When the email is invalid`() = runBlocking {
        presenter.onLoginButtonClicked(Extras.MockTesting.INVALID_EMAIL, Extras.MockTesting.PASSWORD).join()
        val errors: List<LoginFormErrors> = listOf(LoginFormErrors.INVALID_EMAIL)

        verify(view, times(1)).setErrors(errors)
    }

    @Test
    fun `When the password is empty`() = runBlocking {
        presenter.onLoginButtonClicked(Extras.MockTesting.EMAIL, "").join()
        val errors: List<LoginFormErrors> = listOf(LoginFormErrors.EMPTY_PASSWORD)

        verify(view, times(1)).setErrors(errors)
    }

    @Test
    fun `When the password and email is empty`() = runBlocking {
        presenter.onLoginButtonClicked("", "").join()
        val errors: List<LoginFormErrors> = listOf(
            LoginFormErrors.INVALID_EMAIL,
            LoginFormErrors.EMPTY_PASSWORD,
            LoginFormErrors.EMPTY_EMAIL
        )

        verify(view, times(1)).setErrors(errors)
    }

    @Test
    fun `When credentials are wrong`() = runBlocking {
        val userLoginData = Extras.MockTesting.FAIL_USERDATA
        val response = mock(Response::class.java) as Response<LoginResponse>
        whenever(loginRepository.loginUser(userLoginData)).doReturn(NetworkResponse.Error(response))

        presenter.onLoginButtonClicked(
            Extras.MockTesting.FAIL_EMAIL,
            Extras.MockTesting.FAIL_PASSWORD
        ).join()

        verify(view, times(1)).showLoader(true)
        verify(view, times(1)).showLoader(false)
        verify(view, times(1)).showErrorLogin(ResponseStatus.ERROR_CREDENTIALS)
    }

    @Test
    fun `When the credentials are correct`() = runBlocking {
        val userLoginData = Extras.MockTesting.USERDATA
        val response = mock(Response::class.java) as Response<LoginResponse>
        whenever(loginRepository.loginUser(userLoginData)).doReturn(NetworkResponse.Success(response))

        presenter.onLoginButtonClicked(Extras.MockTesting.EMAIL, Extras.MockTesting.PASSWORD).join()

        verify(userSession, times(1)).username = userLoginData.email
        verify(userSession, times(1)).password = userLoginData.password
        verify(view, times(1)).setLoginUser()
    }

    @Test
    fun `When the api is not responding`() = runBlocking {
        val userLoginData = Extras.MockTesting.USERDATA
        whenever(loginRepository.loginUser(userLoginData)).doReturn(NetworkResponse.Failure(
            Throwable()
        ))

        presenter.onLoginButtonClicked(Extras.MockTesting.EMAIL, Extras.MockTesting.PASSWORD).join()

        verify(view, times(1)).showErrorLogin(ResponseStatus.DEFAULT_ERROR)
    }
}