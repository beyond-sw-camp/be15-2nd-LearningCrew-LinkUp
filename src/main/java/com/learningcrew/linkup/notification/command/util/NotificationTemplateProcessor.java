package com.learningcrew.linkup.notification.command.util;

import org.apache.commons.text.StringSubstitutor;

import java.util.Map;

public class NotificationTemplateProcessor {

    // 템플릿 바인딩 메서드
    public static String process(String template, Map<String, String> variables) {
        if (variables == null || variables.isEmpty() || !needsBinding(template)) {
            return template;
        }
        return StringSubstitutor.replace(template, variables);
    }

    // 템플릿에 바인딩이 필요한지 확인
    public static boolean needsBinding(String template) {
        return template != null && template.contains("{");
    }
}