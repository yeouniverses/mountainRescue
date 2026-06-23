package com.mountainrescue.operation.service;

import com.mountainrescue.operation.controller.dto.CreateFrameRequest;
import com.mountainrescue.operation.controller.dto.CreateFrameResponse;
import com.mountainrescue.operation.controller.dto.CreateMissionRequest;
import com.mountainrescue.operation.controller.dto.CreateMissionResponse;
import com.mountainrescue.operation.controller.dto.CreateRescueRequestRequest;
import com.mountainrescue.operation.controller.dto.CreateRescueRequestResponse;
import com.mountainrescue.operation.controller.dto.StartRecordRequest;
import com.mountainrescue.operation.controller.dto.StartRecordResponse;
import com.mountainrescue.operation.repository.EquipmentRepository;
import com.mountainrescue.operation.repository.FrameRepository;
import com.mountainrescue.operation.repository.MissionRepository;
import com.mountainrescue.operation.repository.MissingPersonRepository;
import com.mountainrescue.operation.repository.RecordRepository;
import com.mountainrescue.operation.repository.RescueRequestRepository;
import com.mountainrescue.operation.repository.entity.Equipment;
import com.mountainrescue.operation.repository.entity.Frame;
import com.mountainrescue.operation.repository.entity.Mission;
import com.mountainrescue.operation.repository.entity.MissingPerson;
import com.mountainrescue.operation.repository.entity.Record;
import com.mountainrescue.operation.repository.entity.RescueRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class OperationServiceImpl implements OperationService {

    private static final String REQUEST_STATUS_RECEIVED = "received";

    private final EquipmentRepository equipmentRepository;
    private final MissingPersonRepository missingPersonRepository;
    private final RescueRequestRepository rescueRequestRepository;
    private final MissionRepository missionRepository;
    private final RecordRepository recordRepository;
    private final FrameRepository frameRepository;

    @Override
    public CreateRescueRequestResponse createRescueRequest(CreateRescueRequestRequest request) {
        CreateRescueRequestRequest.MissingPersonRequest missingPersonRequest = request.getMissingPerson();
        if (missingPersonRequest == null) {
            throw new IllegalArgumentException("missingPerson is required");
        }

        MissingPerson missingPerson = MissingPerson.builder()
                .name(missingPersonRequest.getName())
                .age(missingPersonRequest.getAge())
                .gender(missingPersonRequest.getGender())
                .appearance(missingPersonRequest.getAppearance())
                .lastKnownLocation(missingPersonRequest.getLastKnownLocation())
                .simX(missingPersonRequest.getSimX())
                .simY(missingPersonRequest.getSimY())
                .simZ(missingPersonRequest.getSimZ())
                .missingSince(missingPersonRequest.getMissingSince())
                .notes(missingPersonRequest.getNotes())
                .build();
        MissingPerson savedMissingPerson = missingPersonRepository.save(missingPerson);

        RescueRequest rescueRequest = RescueRequest.builder()
                .missingPerson(savedMissingPerson)
                .reporterType(request.getReporterType())
                .simX(request.getSimX())
                .simY(request.getSimY())
                .simZ(request.getSimZ())
                .status(REQUEST_STATUS_RECEIVED)
                .requestedAt(OffsetDateTime.now())
                .notes(request.getNotes())
                .build();
        RescueRequest savedRescueRequest = rescueRequestRepository.save(rescueRequest);

        return new CreateRescueRequestResponse(
                savedMissingPerson.getId(),
                savedRescueRequest.getId(),
                savedRescueRequest.getStatus(),
                savedRescueRequest.getRequestedAt()
        );
    }

    @Override
    public CreateMissionResponse createMission(CreateMissionRequest request) {
        Equipment equipment = getEquipment(request.getEquipmentId());
        RescueRequest rescueRequest = null;
        if (request.getRescueRequestId() != null) {
            rescueRequest = rescueRequestRepository.findById(request.getRescueRequestId())
                    .orElseThrow(() -> new IllegalArgumentException("rescueRequest not found: " + request.getRescueRequestId()));
        }

        Mission mission = Mission.builder()
                .rescueRequest(rescueRequest)
                .equipment(equipment)
                .simX(request.getSimX())
                .simY(request.getSimY())
                .simZ(request.getSimZ())
                .createdAt(OffsetDateTime.now())
                .build();
        Mission savedMission = missionRepository.save(mission);

        return new CreateMissionResponse(
                savedMission.getId(),
                rescueRequest == null ? null : rescueRequest.getId(),
                equipment.getId()
        );
    }

    @Override
    public StartRecordResponse startRecord(StartRecordRequest request) {
        Equipment equipment = getEquipment(request.getEquipmentId());
        Mission mission = getMission(request.getMissionId());

        Record record = Record.builder()
                .equipment(equipment)
                .mission(mission)
                .startTime(OffsetDateTime.now())
                .build();
        Record savedRecord = recordRepository.save(record);

        return new StartRecordResponse(
                savedRecord.getId(),
                equipment.getId(),
                mission.getId(),
                savedRecord.getStartTime()
        );
    }

    @Override
    public CreateFrameResponse createFrame(CreateFrameRequest request) {
        Record record = getRecord(request.getRecordId());


        OffsetDateTime time = OffsetDateTime.now();

        Frame frame = Frame.builder()
                .record(record)
                .simX(request.getSimX())
                .simY(request.getSimY())
                .simZ(request.getSimZ())
                .roll(request.getRoll())
                .pitch(request.getPitch())
                .yaw(request.getYaw())
                .batteryPct(request.getBatteryPct())
                .time(time)
                .build();
        Frame savedFrame = frameRepository.save(frame);

        return new CreateFrameResponse(
                record.getId(),
                savedFrame.getId(),
                savedFrame.getTime()
        );
    }

    private Record getRecord(Integer recordId) {
        if (recordId == null) {
            throw new IllegalArgumentException("recordId is required");
        }
        return recordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("record not found: " + recordId));
    }

    private Mission getMission(Integer missionId) {
        if (missionId == null) {
            throw new IllegalArgumentException("missionId is required");
        }
        return missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("mission not found: " + missionId));
    }

    private Equipment getEquipment(Integer equipmentId) {
        if (equipmentId == null) {
            throw new IllegalArgumentException("equipmentId is required");
        }
        return equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new IllegalArgumentException("equipment not found: " + equipmentId));
    }
}
