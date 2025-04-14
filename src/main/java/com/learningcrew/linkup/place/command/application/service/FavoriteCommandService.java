package com.learningcrew.linkup.place.command.application.service;

import com.learningcrew.linkup.exception.BusinessException;
import com.learningcrew.linkup.exception.ErrorCode;
import com.learningcrew.linkup.place.command.application.dto.request.FavoriteCreateRequest;
import com.learningcrew.linkup.place.command.application.dto.response.FavoriteCommandResponse;
import com.learningcrew.linkup.place.command.domain.aggregate.entity.Favorite;
import com.learningcrew.linkup.place.command.domain.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FavoriteCommandService {

    private final FavoriteRepository favoriteRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public FavoriteCommandResponse createFavorite(int placeId, FavoriteCreateRequest favoriteCreateRequest) {
        int memberId = favoriteCreateRequest.getMemberId();
        Favorite favorite = new Favorite(memberId, placeId);

        if (favoriteRepository.existsById(favorite.getId())) {
            // 이미 존재하는 경우, 예외 대신 등록된 상태임을 알리는 응답 반환
            return FavoriteCommandResponse.builder()
                    .message("이미 즐겨찾기가 된 장소입니다. ")
                    .build();
        }
        favoriteRepository.save(favorite);

        return FavoriteCommandResponse.builder()
                .message("장소가 성공적으로 등록되었습니다. ")
                .build();
    }
}

