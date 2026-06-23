package com.mountainrescue.operation.service;

import com.mountainrescue.operation.controller.dto.CreateFrameRequest;
import com.mountainrescue.operation.controller.dto.CreateFrameResponse;
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
import com.mountainrescue.operation.repository.entity.Record;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperationServiceImplTest {

    @Mock
    private EquipmentRepository equipmentRepository;

    @Mock
    private MissingPersonRepository missingPersonRepository;

    @Mock
    private RescueRequestRepository rescueRequestRepository;

    @Mock
    private MissionRepository missionRepository;

    @Mock
    private RecordRepository recordRepository;

    @Mock
    private FrameRepository frameRepository;

    @InjectMocks
    private OperationServiceImpl operationService;

    @Test
    void startRecordThrowsExceptionWhenMissionIdIsNull() {
        StartRecordRequest request = new StartRecordRequest();
        request.setEquipmentId(1);

        Equipment equipment = equipment(1);
        when(equipmentRepository.findById(1)).thenReturn(Optional.of(equipment));

        assertThatThrownBy(() -> operationService.startRecord(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("missionId is required");
    }

    @Test
    void startRecordThrowsExceptionWhenMissionDoesNotExist() {
        StartRecordRequest request = new StartRecordRequest();
        request.setEquipmentId(1);
        request.setMissionId(99);

        Equipment equipment = equipment(1);
        when(equipmentRepository.findById(1)).thenReturn(Optional.of(equipment));
        when(missionRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> operationService.startRecord(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("mission not found: 99");
    }

    @Test
    void startRecordSavesRecordWithMission() {
        StartRecordRequest request = new StartRecordRequest();
        request.setEquipmentId(1);
        request.setMissionId(10);

        Equipment equipment = equipment(1);
        Mission mission = Mission.builder()
                .id(10)
                .equipment(equipment)
                .build();

        when(equipmentRepository.findById(1)).thenReturn(Optional.of(equipment));
        when(missionRepository.findById(10)).thenReturn(Optional.of(mission));
        when(recordRepository.save(any(Record.class))).thenAnswer(invocation -> {
            Record record = invocation.getArgument(0);
            ReflectionTestUtils.setField(record, "id", 100);
            return record;
        });

        StartRecordResponse response = operationService.startRecord(request);

        assertThat(response.recordId()).isEqualTo(100);
        assertThat(response.equipmentId()).isEqualTo(1);
        assertThat(response.missionId()).isEqualTo(10);
        assertThat(response.startTime()).isNotNull();

        verify(recordRepository).save(any(Record.class));
    }

    @Test
    void createFrameThrowsExceptionWhenRecordIdIsNull() {
        CreateFrameRequest request = new CreateFrameRequest();

        assertThatThrownBy(() -> operationService.createFrame(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("recordId is required");
    }

    @Test
    void createFrameSavesFrameWithRecord() {
        CreateFrameRequest request = new CreateFrameRequest();
        request.setRecordId(100);
        request.setSimX(123.4);
        request.setSimY(87.6);
        request.setSimZ(421.0);
        request.setRoll(1.2);
        request.setPitch(-0.4);
        request.setYaw(35.0);
        request.setBatteryPct(82.5);

        Record record = Record.builder()
                .id(100)
                .equipment(equipment(1))
                .build();

        when(recordRepository.findById(100)).thenReturn(Optional.of(record));
        when(frameRepository.save(any(Frame.class))).thenAnswer(invocation -> {
            Frame frame = invocation.getArgument(0);
            ReflectionTestUtils.setField(frame, "id", 200L);
            return frame;
        });

        CreateFrameResponse response = operationService.createFrame(request);

        assertThat(response.recordId()).isEqualTo(100);
        assertThat(response.frameId()).isEqualTo(200L);
        assertThat(response.time()).isNotNull();

        verify(frameRepository).save(any(Frame.class));
    }

    private Equipment equipment(Integer id) {
        Equipment equipment = new Equipment();
        ReflectionTestUtils.setField(equipment, "id", id);
        return equipment;
    }
}
