package com.learningcrew.linkup.community.command.domain.repository;

import com.learningcrew.linkup.community.command.domain.aggregate.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Optional<Post> findByPostId(Integer postId);

    Optional<Post> findByPostIdAndPostIsDeleted(Integer postId, String IsDeleted);


//    Optional<Post> findByPostIdAndPostIsDeleted(int postId, String postIsDeleted);
//
//    void deleteByPostId(Integer postId);

}