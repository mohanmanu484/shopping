package com.project.shopping.shoppingapp.ViewModel;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Patterns;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.project.shopping.shoppingapp.model.User;
import com.project.shopping.shoppingapp.viewcallbacks.BaseViewCallback;
import com.project.shopping.shoppingapp.viewcallbacks.MainViewModel;
import com.project.shopping.shoppingapp.viewcallbacks.SignUpViewCallback;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by mohan on 21/05/17.
 */

public class SignupModel implements MainViewModel{


    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    FirebaseDatabase mDatabase=FirebaseDatabase.getInstance();
    private SignUpViewCallback.View view;

    public ObservableField<String> observableUserMail=new ObservableField<>("");
    public ObservableField<String> observableUserPassword=new ObservableField<>("");
    public ObservableField<String> observableFieldName=new ObservableField<>("");







    private void sendEmailVerification() {

        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(view.getActivityInstance(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
                       // findViewById(R.id.verify_email_button).setEnabled(true);
                        if(view==null){
                            return;
                        }

                        if (task.isSuccessful()) {
                            view.showMessage("Email verification mail has been sent, Please check your inbox ");
                        } else {
                           // Log.e(TAG, "sendEmailVerification", task.getException());
                            view.showMessage(task.getException().getMessage());
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }





    public void createAccount() {
        view.showProgressBar();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(observableUserMail.get(), observableUserPassword.get())
                .addOnCompleteListener( view.getActivityInstance(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            User newUser=new User(observableFieldName.get(),observableUserMail.get());
                            mDatabase.getReference("users").child(user.getUid()).setValue(newUser);
                            view.showMessage("User created");
                            sendEmailVerification();

                        } else {
                            view.showMessage(task.getException().getMessage());
                        }

                        view.hideProgressBar();
                    }
                });
        // [END create_user_with_email]
    }


    @Override
    public void onAtttach(BaseViewCallback baseViewCallback) {
        view= (SignUpViewCallback.View) baseViewCallback;
    }

    @Override
    public void onDestroy() {
        view=null;
    }

    public boolean enableButton(String email,String password,String name){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length()>4 && name.length()>0;
    }

    public void showInfragment(){
        view.showInFragment();
    }




}
