package com.learningcrew.linkup.linker.command.application.service;

import com.learningcrew.linkup.linker.command.application.dto.request.UserCreateRequest;
import com.learningcrew.linkup.linker.command.application.dto.request.WithdrawUserRequest;
import com.learningcrew.linkup.linker.command.application.dto.response.RegisterResponse;

public interface AccountCommandService {

    RegisterResponse registerUser(UserCreateRequest request);

    void withdrawUser(String password,int userId);

    // void verifyEmail(...);
    // void updateProfile(...);
    // void deleteUser(...);
    // void sendResetToken(...);
    // void resetPassword(...);
}

