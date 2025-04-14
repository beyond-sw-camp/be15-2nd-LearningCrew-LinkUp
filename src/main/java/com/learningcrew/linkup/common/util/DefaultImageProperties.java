package com.learningcrew.linkup.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefaultImageProperties {
    @Value("${image.default-profile}")
    private String defaultProfileImage;

    public String getDefaultProfileImage() {
        return defaultProfileImage;
    }
}