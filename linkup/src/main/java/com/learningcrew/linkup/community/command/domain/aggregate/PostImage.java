package com.learningcrew.linkup.community.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@Entity
@Table(name = "post_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int imageId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "post_id", referencedColumnName = "post_id", nullable = false)
    private int postId;

    @Column(name = "image_url", nullable = false, columnDefinition = "TEXT")
    private String imageUrl;

}