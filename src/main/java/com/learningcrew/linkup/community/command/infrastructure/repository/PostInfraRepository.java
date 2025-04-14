package com.learningcrew.linkup.community.command.infrastructure.repository;

import com.learningcrew.linkup.community.command.domain.aggregate.Post;
import com.learningcrew.linkup.community.command.domain.repository.PostRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostInfraRepository extends PostRepository, JpaRepository<Post, Integer> {
}
