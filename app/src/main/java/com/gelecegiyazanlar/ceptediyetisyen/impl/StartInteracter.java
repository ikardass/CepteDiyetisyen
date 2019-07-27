package com.gelecegiyazanlar.ceptediyetisyen.impl;

import android.util.Log;
import androidx.annotation.NonNull;
import com.gelecegiyazanlar.ceptediyetisyen.base.mvp.callback.ICallbackListener;
import com.gelecegiyazanlar.ceptediyetisyen.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import static com.gelecegiyazanlar.ceptediyetisyen.impl.DBFields.USERS;


public class StartInteracter {

    private final String TAG = StartInteracter.class.getSimpleName();
    private final FirebaseAuth mAuth;
    private final DatabaseReference mDatabase;

    public StartInteracter() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }


    public void doSignup(final User userModel, final String password, @NonNull final ICallbackListener<String> callbackListener) {
        mAuth.createUserWithEmailAndPassword(userModel.getEmail(), password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            writeNewUser(user.getUid(), userModel);
                            callbackListener.onSuccess(user.getEmail());
                        } else
                            callbackListener.onFailure(new Exception("User can not be created"));
                    } else {
                        callbackListener.onFailure(task.getException());
                    }
                });
    }

    private void writeNewUser(String userId, User userModel) {
        //Map<String, String> user = new HashMap<String, String>();
        //user.put("name", signUpModel.getName());
        //user.put("surName", signUpModel.getSurname());
        mDatabase.child(USERS).child(userId).setValue(userModel);
    }

    public void doLogin(final String mail, final String password, @NonNull final ICallbackListener<String> callbackListener) {
        mAuth.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "loginUserWithEmail:success");
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        callbackListener.onSuccess(firebaseUser != null ? firebaseUser.getEmail() : "");
                    } else {
                        callbackListener.onFailure(task.getException());
                    }
                });
    }
}

