package com.gelecegiyazanlar.ceptediyetisyen.utils;

public interface UploadProgressView {

    void showUploadProgressDialog();

    void hideUploadProgressDialog();

    void showUploadProgress(int progress);

    void showMessage(String errorMessage);

}
