package com.project.shopping.shoppingapp.ViewModel;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Patterns;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.project.shopping.shoppingapp.Utility.ModuleMaster;
import com.project.shopping.shoppingapp.viewcallbacks.BaseViewCallback;
import com.project.shopping.shoppingapp.viewcallbacks.MainViewModel;
import com.project.shopping.shoppingapp.viewcallbacks.SignInViewCallback;

import static com.google.android.gms.internal.zzs.TAG;
import static com.project.shopping.shoppingapp.Utility.Utility.hideProgressDialog;

/**
 * Created by mohan on 21/05/17.
 */

public class SigninModel implements MainViewModel{

    SignInViewCallback.View signInViewCallback;
    FirebaseAuth mAuth=FirebaseAuth.getInstance();

    public ObservableField<String> observableUserMail=new ObservableField<>("");
    public ObservableField<String> observableUserPassword=new ObservableField<>("");


    public boolean enableButton(String email,String password){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length()>4;
    }




    public void signIn() {

        String email=observableUserMail.get();
        String password=observableUserPassword.get();

        Log.d(TAG, "signIn:" + email);


       signInViewCallback.showProgressBar();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(signInViewCallback.getActivityInstance(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            signInViewCallback.showMessage("SignIn successfull");
                            ModuleMaster.navigateToHomeScreen(signInViewCallback.getActivityContext());
                            signInViewCallback.finish();

                        } else {
                            // If sign in fails, display a message to the user.
                           signInViewCallback.showMessage(task.getException().getMessage());
                        }

                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    @Override
    public void onAtttach(BaseViewCallback baseViewCallback) {
        signInViewCallback= (SignInViewCallback.View) baseViewCallback;
    }

    public void showRegisterFragment(){
        signInViewCallback.showRegisterFragment();
    }

    @Override
    public void onDestroy() {
        signInViewCallback=null;
    }
}
