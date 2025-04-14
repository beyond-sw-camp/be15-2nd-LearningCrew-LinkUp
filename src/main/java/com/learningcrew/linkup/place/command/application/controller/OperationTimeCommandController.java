package com.learningcrew.linkup.place.command.application.controller;

import com.learningcrew.linkup.common.dto.ApiResponse;
import com.learningcrew.linkup.place.command.application.dto.request.OperationTimeRequest;
import com.learningcrew.linkup.place.command.application.dto.response.OperationTimeUpdateResponse;
import com.learningcrew.linkup.place.command.application.service.OperationTimeCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OperationTimeCommandController
{
    private final OperationTimeCommandService operationTimeCommandService;

    @PatchMapping("place/{placeId}/times")
    public ResponseEntity<ApiResponse<OperationTimeUpdateResponse>> updateOperationTimes(
            @PathVariable int placeId,
            @RequestBody @Validated List<OperationTimeRequest> operationTimeRequests) {

        OperationTimeUpdateResponse response = operationTimeCommandService.updateOperationTimes(placeId, operationTimeRequests);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
