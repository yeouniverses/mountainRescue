package com.mountainrescue.operation.service;

import com.mountainrescue.operation.controller.dto.CreateMissionRequest;
import com.mountainrescue.operation.controller.dto.CreateMissionResponse;
import com.mountainrescue.operation.controller.dto.CreateFrameRequest;
import com.mountainrescue.operation.controller.dto.CreateFrameResponse;
import com.mountainrescue.operation.controller.dto.CreateRescueRequestRequest;
import com.mountainrescue.operation.controller.dto.CreateRescueRequestResponse;
import com.mountainrescue.operation.controller.dto.StartRecordRequest;
import com.mountainrescue.operation.controller.dto.StartRecordResponse;

public interface OperationService {

    CreateRescueRequestResponse createRescueRequest(CreateRescueRequestRequest request);

    CreateMissionResponse createMission(CreateMissionRequest request);

    StartRecordResponse startRecord(StartRecordRequest request);

    CreateFrameResponse createFrame(CreateFrameRequest request);
}
