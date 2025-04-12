package com.learningcrew.linkup.linker.command.application.service;

import com.learningcrew.linkup.exception.BusinessException;
import com.learningcrew.linkup.exception.ErrorCode;
import com.learningcrew.linkup.linker.command.domain.service.UserDomainServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final UserDomainServiceImpl userDomainService;
    private final SpringTemplateEngine templateEngine;
    private final VerificationTokenServiceImpl verificationTokenService;

    private static final int codeValidTime = 10; // 인증 코드 유효 시간 (10분)
    /* 회원가입 인증코드 전송 */
    public void sendVerificationCode(int userId, String email, String userName) {
        log.info("========인증코드 발송 서비스 - sendSignUpEmail : email: {}========", email);

        // 인증 코드 저장
        String subject = "[Linker] 회원가입 인증코드입니다.";
        String verificationCode = verificationTokenService.createAndSaveToken(userId, email, "REGISTER");

        // 인증 코드 만료 시간 설정
        LocalDateTime expireMinute = LocalDateTime.now().plusMinutes(codeValidTime);

        // 이메일 전송
        MimeMessage message = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject(subject);

            Context context = new org.thymeleaf.context.Context();
            context.setVariable("userName", userName);
            context.setVariable("verificationCode", verificationCode);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            context.setVariable("expireTime", expireMinute.format(formatter) + "분");

            String htmlContent = templateEngine.process("email/verification-code", context);
            helper.setText(htmlContent, true);

            mailSender.send(message);

        }catch (MessagingException e){
            throw new BusinessException(ErrorCode.SEND_MAIL_FAIL);
        }

    }


}
