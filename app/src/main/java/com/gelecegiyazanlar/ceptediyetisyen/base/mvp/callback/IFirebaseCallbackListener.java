package com.gelecegiyazanlar.ceptediyetisyen.base.mvp.callback;

public interface IFirebaseCallbackListener<T> {

    void childAdded(T trip);

    void childChanged(T trip);

    void childRemoved(T trip);

    void onFailure(Throwable t);
}
