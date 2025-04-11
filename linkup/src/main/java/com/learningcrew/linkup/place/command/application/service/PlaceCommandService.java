package com.learningcrew.linkup.place.command.application.service;

import com.learningcrew.linkup.common.service.FileStorage;
import com.learningcrew.linkup.exception.BusinessException;
import com.learningcrew.linkup.exception.ErrorCode;
import com.learningcrew.linkup.place.command.application.dto.request.OperationTimeRequest;
import com.learningcrew.linkup.place.command.application.dto.request.PlaceCreateRequest;
import com.learningcrew.linkup.place.command.application.dto.request.PlaceUpdateRequest;
import com.learningcrew.linkup.place.command.domain.aggregate.entity.OperationTime;
import com.learningcrew.linkup.place.command.domain.aggregate.entity.Place;
import com.learningcrew.linkup.place.command.domain.aggregate.entity.PlaceImage;
import com.learningcrew.linkup.place.command.domain.repository.OperationTimeRepository;
import com.learningcrew.linkup.place.command.domain.repository.PlaceImageRepository;
import com.learningcrew.linkup.place.command.domain.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceCommandService {

    private final PlaceRepository placeRepository;
    private final OperationTimeRepository operationTimeRepository;
    private final PlaceImageRepository placeImageRepository;
    private final FileStorage fileStorage;
    private final ModelMapper modelMapper;

    @Value("${image.image-url}")
    private String IMAGE_URL;

    /* 장소 등록 */
    @Transactional
    public int createPlace(PlaceCreateRequest placeCreateRequest, List<MultipartFile> placeImgs) {
        String mainImageFilename = "";
        // 이미지 파일 리스트가 있다면
        if (placeImgs != null && !placeImgs.isEmpty()) {
            // 대표 이미지로 첫 번째 파일 선택 (원하는 방식에 따라 다르게 처리 가능)
            MultipartFile mainFile = placeImgs.get(0);
            if (!mainFile.isEmpty()) {
                mainImageFilename = fileStorage.storeFile(mainFile);
            }
        }

        // Place 엔티티 생성 (DTO -> Entity 매핑)
        Place newPlace = modelMapper.map(placeCreateRequest, Place.class);


        // 장소 INSERT 및 생성된 placeId 획득
        Place savedPlace = placeRepository.save(newPlace);
        int generatedPlaceId = savedPlace.getPlaceId();

        // 운영시간 정보가 있다면 operation_time 테이블에 삽입
        if (placeCreateRequest.getOperationTimes() != null && !placeCreateRequest.getOperationTimes().isEmpty()) {
            for (OperationTimeRequest opReq : placeCreateRequest.getOperationTimes()) {
                OperationTime opEntity = new OperationTime();
                opEntity.setPlaceId(generatedPlaceId);
                opEntity.setDayOfWeek(opReq.getDayOfWeek());
                opEntity.setStartTime(opReq.getStartTime());
                opEntity.setEndTime(opReq.getEndTime());
                operationTimeRepository.save(opEntity);
            }
        }

        // 모든 이미지 파일에 대해 place_image 테이블에 저장
        if (placeImgs != null && !placeImgs.isEmpty()) {
            for (MultipartFile file : placeImgs) {
                if (!file.isEmpty()) {
                    // 파일 저장 (대표 이미지는 이미 저장되어 있을 수 있으므로, 중복 저장을 피하고 싶으면 조건 처리 가능)
                    String storedFilename = fileStorage.storeFile(file);
                    PlaceImage placeImage = new PlaceImage();
                    placeImage.setPlaceId(generatedPlaceId);
                    placeImage.setImageUrl(IMAGE_URL + storedFilename);
                    placeImageRepository.save(placeImage);
                }
            }
        }

        return generatedPlaceId;
    }

    /*장소 수정*/
    @Transactional
    public void updatePlace(int placeId, PlaceUpdateRequest placeUpdateRequest) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new BusinessException(ErrorCode.PLACE_NOT_FOUND));
        place.updatePlaceDetails(
                placeUpdateRequest.getSportId(),
                placeUpdateRequest.getPlaceName(),
                placeUpdateRequest.getAddress(),
                placeUpdateRequest.getDescription(),
                placeUpdateRequest.getEquipment(),
                placeUpdateRequest.getIsActive(),
                placeUpdateRequest.getRentalCost()
        );
        }
}
