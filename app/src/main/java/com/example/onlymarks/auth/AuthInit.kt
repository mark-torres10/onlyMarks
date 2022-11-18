package com.example.onlymarks.auth

import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

class AuthInit(viewModel: AuthViewModel, signInLauncher: ActivityResultLauncher<Intent>) {
    companion object {}

    init {
        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())

            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(false)
                .setAvailableProviders(providers)
                .build()
            signInLauncher.launch(signInIntent)
        } else {
            Log.d("XXX", "user ${user.displayName} email ${user.email}")
        }
    }
}