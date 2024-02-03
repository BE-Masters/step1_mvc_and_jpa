package com.example.be_study.service.user.service;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.service.user.enums.UserSignUpResponseCode;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Slf4j
@Service
public class EmailService {
//
//    private final JavaMailSender javaMailSender;
//
//    private final RedisService redisService;
//
//    private static String emailCode;
//
//    public EmailService(JavaMailSender javaMailSender, RedisService redisService) {
//        this.javaMailSender = javaMailSender;
//        this.redisService = redisService;
//    }
//
//    /**
//     *  메일 전송
//     */
//    @Transactional(readOnly = false)
//    public DataResponse<UserSignUpResponseCode> sendMail(String receiver) {
//        try {
//            emailCode = createKey();
//            MimeMessage message = createMessage(receiver);
//
//            if (redisService.existData(receiver)) { // 기존에 발급 받았던 인증 코드 삭제
//                redisService.deleteData(receiver);
//            }
//
//            javaMailSender.send(message);
//
//            redisService.setDataExpire(receiver, emailCode, 3 * 60 * 1000L); // 만료 시간 3분
//
//            return new DataResponse<>(UserSignUpResponseCode.SUCCESS);
//        } catch (MessagingException | UnsupportedEncodingException | MailException e) {
//            e.printStackTrace();
//            return new DataResponse<>(UserSignUpResponseCode.MAIL_SEND_FAILED);
//        }
//    }
//
//    /**
//     *  메일 인증 코드 확인
//     */
//    @Transactional(readOnly = true)
//    public DataResponse<UserSignUpResponseCode> verifyEmailCode(String email, String code) {
//        String userMailCode = redisService.getData(email);
//
//        if (userMailCode == null) {
//            return new DataResponse<>(UserSignUpResponseCode.EXPIRED_AUTH_MAIL_CODE);
//        }
//
//        if (userMailCode.equals(code)) { // 올바른 인증 코드인 경우
//            return new DataResponse<>(UserSignUpResponseCode.SUCCESS);
//        }
//
//        else { // 올바르지 않은 인증 코드인 경우
//            return new DataResponse<>(UserSignUpResponseCode.INVALID_AUTH_MAIL_CODE);
//        }
//    }
//
//    /**
//     *  메일 내용 작성
//     */
//    public MimeMessage createMessage(String receiver) throws MessagingException, UnsupportedEncodingException {
//        MimeMessage message = javaMailSender.createMimeMessage();
//
//        message.addRecipients(Message.RecipientType.TO, receiver);
//        message.setSubject("[오늘의집] 인증코드 안내");
//
//        StringBuilder mailMsg2 = new StringBuilder();
//        mailMsg2.append("<div>");
//        mailMsg2.append("인증코드를 확인해주세요.<br><strong style=\"font-size: 30px;\">");
//        mailMsg2.append(emailCode);
//        mailMsg2.append("</strong><br>이메일 인증 절차에 따라 이메일 인증코드를 발급해드립니다.<br>인증코드는 이메일 발송 시점으로부터 3분동안 유효합니다.</div>");
//
//        message.setText(mailMsg2.toString(), "utf-8", "html");
//        message.setFrom(new InternetAddress("todayHome", "MEOW~ Developer"));
//
//        return message;
//    }
//
//    /**
//     *  메일 인증 코드 생성
//     */
//    private static String createKey() {
//        StringBuilder key = new StringBuilder();
//
//        Random random = new Random();
//
//        for (int i = 0; i < 6; i++) {
//            int index = random.nextInt(10);
//            key.append(index);
//        }
//
//        return key.toString();
//    }
}
