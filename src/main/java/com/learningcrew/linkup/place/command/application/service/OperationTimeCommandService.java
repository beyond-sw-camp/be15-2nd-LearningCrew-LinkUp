package com.learningcrew.linkup.place.command.application.service;

import com.learningcrew.linkup.exception.BusinessException;
import com.learningcrew.linkup.exception.ErrorCode;
import com.learningcrew.linkup.place.command.application.dto.request.OperationTimeRequest;
import com.learningcrew.linkup.place.command.application.dto.response.OperationTimeUpdateResponse;
import com.learningcrew.linkup.place.command.domain.aggregate.entity.OperationTime;
import com.learningcrew.linkup.place.command.domain.aggregate.entity.Place;
import com.learningcrew.linkup.place.command.domain.repository.OperationTimeRepository;
import com.learningcrew.linkup.place.command.domain.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperationTimeCommandService {

    private final OperationTimeRepository operationTimeRepository;
    private final PlaceRepository placeRepository;

    @Transactional
    public OperationTimeUpdateResponse updateOperationTimes(int placeId, List<OperationTimeRequest> operationTimeRequests) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new BusinessException(ErrorCode.PLACE_NOT_FOUND));

        for (OperationTimeRequest req : operationTimeRequests) {
            if (req.getStartTime().equals(req.getEndTime())) {
                throw new BusinessException(ErrorCode.INVALID_OPERATION_TIME);
            }
            if (req.getEndTime().isBefore(req.getStartTime())) {
                throw new BusinessException(ErrorCode.INVALID_OPERATION_TIME);
            }
        }

        for (OperationTimeRequest req : operationTimeRequests) {
            Optional<OperationTime> optionalOpTime = operationTimeRepository.findByPlaceIdAndDayOfWeek(placeId, req.getDayOfWeek());
            if (optionalOpTime.isPresent()) {
                OperationTime existingOpTime = optionalOpTime.get();
                existingOpTime.setStartTime(req.getStartTime());
                existingOpTime.setEndTime(req.getEndTime());
                operationTimeRepository.save(existingOpTime);
            } else {
                OperationTime opEntity = new OperationTime();
                opEntity.setPlaceId(placeId);
                opEntity.setDayOfWeek(req.getDayOfWeek());
                opEntity.setStartTime(req.getStartTime());
                opEntity.setEndTime(req.getEndTime());
                operationTimeRepository.save(opEntity);
            }
        }

        return OperationTimeUpdateResponse.builder()
                .message("시간 수정 완료")
                .build();
    }
}
