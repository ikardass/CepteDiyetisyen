package com.gelecegiyazanlar.ceptediyetisyen.base.mvp.validator;

public interface IFormValidator {
    boolean validate();

    void onValidationSuccess();

    void onValidationError(String errorMessage);
}
