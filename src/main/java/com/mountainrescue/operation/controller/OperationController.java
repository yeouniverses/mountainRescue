package com.mountainrescue.operation.controller;

import com.mountainrescue.operation.controller.dto.ApiResponse;
import com.mountainrescue.operation.controller.dto.CreateMissionRequest;
import com.mountainrescue.operation.controller.dto.CreateMissionResponse;
import com.mountainrescue.operation.controller.dto.CreateRescueRequestRequest;
import com.mountainrescue.operation.controller.dto.CreateRescueRequestResponse;
import com.mountainrescue.operation.controller.dto.StartRecordRequest;
import com.mountainrescue.operation.controller.dto.StartRecordResponse;
import com.mountainrescue.operation.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/operation")
public class OperationController {

    private final OperationService operationService;

    @PostMapping("/rescue-request")
    public ApiResponse<CreateRescueRequestResponse> createRescueRequest(@RequestBody CreateRescueRequestRequest request) {
        return ApiResponse.success(operationService.createRescueRequest(request));
    }

    @PostMapping("/mission")
    public CreateMissionResponse createMission(@RequestBody CreateMissionRequest request) {
        return operationService.createMission(request);
    }

    @PostMapping("/record-start")
    public ApiResponse<StartRecordResponse> startRecord(@RequestBody StartRecordRequest request) {
        return ApiResponse.success(operationService.startRecord(request));
    }
}
