package com.learningcrew.linkup.community.command.infrastructure.repository;

import com.learningcrew.linkup.community.command.domain.aggregate.PostLike;
import com.learningcrew.linkup.community.command.domain.repository.PostLikeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeInfraRepository extends JpaRepository<PostLike, Integer>, PostLikeRepository {
}
