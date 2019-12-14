package com.dicoding.event.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.dicoding.event.R
import com.dicoding.event.ext.invisible
import com.dicoding.event.ext.visible
import com.dicoding.event.utils.Error
import com.dicoding.event.utils.Loading
import com.dicoding.event.utils.Success
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel.observerState.observe(this, Observer {
            when(it) {
                is Loading -> {
                    activityLoginButtonLogin.invisible()
                    activityLoginProgressBar.visible()
                }

                is Success<*> -> {
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                    activityLoginButtonLogin.visible()
                    activityLoginProgressBar.invisible()
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
