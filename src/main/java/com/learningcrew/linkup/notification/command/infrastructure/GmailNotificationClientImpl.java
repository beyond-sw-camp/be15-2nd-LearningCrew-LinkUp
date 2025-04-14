package com.learningcrew.linkup.notification.command.infrastructure;

import com.learningcrew.linkup.linker.command.domain.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;



@Slf4j
@Service
@RequiredArgsConstructor
public class GmailNotificationClientImpl implements GmailNotificationClient {

    private final JavaMailSender mailSender;

    private final UserRepository userRepository;


    @Override
    public void sendEmailNotification(String userId, String subject, String content) {
        String to = userRepository.findById(Integer.valueOf(userId))
                .map(user -> user.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자 이메일을 찾을 수 없습니다."));

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);

            // 🔽 템플릿 파일 로드 후 치환
            String htmlTemplate = loadHtmlTemplate();
            String htmlBody = htmlTemplate
                    .replace("{{title}}", subject)
                    .replace("{{content}}", content);

            helper.setText(htmlBody, true); // HTML 모드

            String fromAddress = "jangwh9466@gmail.com";
            String fromName = "LinkUp 알림센터";
            helper.setFrom(new InternetAddress(fromAddress, fromName));

            mailSender.send(message);
            log.info("[Gmail] HTML 이메일 전송 완료 → {}, 제목: {}", to, subject);

        } catch (MessagingException e) {
            throw new RuntimeException("메일 전송 실패", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private String loadHtmlTemplate() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("templates/notification.html")) {
            assert inputStream != null;
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("메일 템플릿을 불러오는 데 실패했습니다.", e);
        }
    }
}
