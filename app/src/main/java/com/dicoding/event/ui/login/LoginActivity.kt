package com.dicoding.event.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.dicoding.event.R
import com.dicoding.event.data.remote.model.UserResponse
import com.dicoding.event.ext.invisible
import com.dicoding.event.ext.launchActivity
import com.dicoding.event.ext.visible
import com.dicoding.event.ui.home.HomeActivity
import com.dicoding.event.utils.*
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(getUserPreference().email.isNotEmpty()) {
            launchActivity<HomeActivity>()
            finish()
        }

        loginViewModel.observerState.observe(this, Observer {
            when(it) {
                is Loading -> {
                    activityLoginButtonLogin.invisible()
                    activityLoginProgressBar.visible()
                }

                is Success<*> -> {
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                    val auth = it.data as UserResponse.Auth
                    activityLoginButtonLogin.visible()
                    activityLoginProgressBar.invisible()

                    with(UserPreference()) {
                        id = auth.id
                        email = auth.email
                        username = auth.username
                        setUserPreference(this)
                    }

                    launchActivity<HomeActivity>()
                    finish()
                }

                is Error -> {
                    Toast.makeText(this, "Login Failed: ${it.message}", Toast.LENGTH_LONG).show()
                    activityLoginButtonLogin.visible()
                    activityLoginProgressBar.invisible()
                }
            }
        })

        activityLoginButtonLogin.setOnClickListener {
            loginViewModel.doLogin(
                email = activityLoginEtEmail.text.toString(),
                password = activityLoginEtPassword.text.toString()
            )
        }
    }
}
