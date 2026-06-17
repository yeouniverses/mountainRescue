package com.mountainrescue.operation.service;

import com.mountainrescue.operation.controller.dto.CreateMissionRequest;
import com.mountainrescue.operation.controller.dto.CreateMissionResponse;
import com.mountainrescue.operation.controller.dto.CreateRescueRequestRequest;
import com.mountainrescue.operation.controller.dto.CreateRescueRequestResponse;

public interface OperationService {

    CreateRescueRequestResponse createRescueRequest(CreateRescueRequestRequest request);

    CreateMissionResponse createMission(CreateMissionRequest request);
}
