package com.gelecegiyazanlar.ceptediyetisyen.impl;

import android.util.Log;

import androidx.annotation.NonNull;
import com.gelecegiyazanlar.ceptediyetisyen.base.mvp.callback.IFirebaseCallbackListener;
import com.gelecegiyazanlar.ceptediyetisyen.model.HealtyRecipe;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class HealtyRecipeInteractor {

    private static final String TAG = HealtyRecipeInteractor.class.getSimpleName();
    private final DatabaseReference mDatabase;
    private final Query query;
    private final IFirebaseCallbackListener<HealtyRecipe> callbackListener;
    private ChildEventListener childEventListener = new ChildEventListener() {

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
            Log.i(TAG, "onChildAdded : " + prevChildKey);
            HealtyRecipe healtyRecipe = dataSnapshot.getValue(HealtyRecipe.class);
            if (healtyRecipe != null) {
                healtyRecipe.setId(dataSnapshot.getKey());
                callbackListener.childAdded(healtyRecipe);
            }
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
            Log.i(TAG, "onChildChanged : " + prevChildKey);
            HealtyRecipe healtyRecipe = dataSnapshot.getValue(HealtyRecipe.class);
            if (healtyRecipe != null) {
                healtyRecipe.setId(dataSnapshot.getKey());
                callbackListener.childChanged(healtyRecipe);
            }
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            Log.i(TAG, "onChildRemoved");
            HealtyRecipe healtyRecipe = dataSnapshot.getValue(HealtyRecipe.class);
            if (healtyRecipe != null) {
                healtyRecipe.setId(dataSnapshot.getKey());
                callbackListener.childRemoved(healtyRecipe);
            }
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            //---
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            callbackListener.onFailure(new Exception(databaseError.getMessage()));
        }
    };

    public HealtyRecipeInteractor(@NonNull IFirebaseCallbackListener<HealtyRecipe> callbackListener) {
        this.callbackListener = callbackListener;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        query = mDatabase.child(DBFields.HEALTY_RECIPE);
    }

    public void findList() {
        query.removeEventListener(childEventListener);
        query.orderByKey().addChildEventListener(childEventListener);
    }
}
