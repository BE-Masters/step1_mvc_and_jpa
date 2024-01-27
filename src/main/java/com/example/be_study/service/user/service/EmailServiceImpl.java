package com.example.be_study.service.user.service;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

//    private final RedisUtil redisUtil;

    private static final String emailCode = createKey();

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMail(String receiver) throws Exception {
        MimeMessage message = createMessage(receiver);

//        if (redisUtil.existData(receiver)) {
//            redisUtil.deleteData(receiver);
//        }

        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }

//        redisUtil.setDataExpire(receiver, emailCode, 60 * 5L);
    }

//    @Override
//    public boolean verifyEmailCode(String email, String code) {
//        String codeFoundByEmail = redisUtil.getData(email);
//
//        if (codeFoundByEmail == null) {
//            return false;
//        }
//
//        return redisUtil.getData(email).equals(code);
//    }

    private MimeMessage createMessage(String receiver) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, receiver);
        message.setSubject("[오늘의집] 인증코드 안내");

        String mailMsg = "";
        mailMsg += "<div>";
        mailMsg += "인증코드를 확인해주세요.<br><strong style=\"font-size: 30px;\">";
        mailMsg += emailCode;
        mailMsg += "</strong><br>이메일 인증 절차에 따라 이메일 인증코드를 발급해드립니다.<br>인증코드는 이메일 발송 시점으로부터 3분동안 유효합니다.</div>";

        message.setText(mailMsg, "utf-8", "html");
        message.setFrom(new InternetAddress("todayHome", "MEOW~ Developer"));

        return message;
    }

    private static String createKey() {
        StringBuffer key = new StringBuffer();

        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(10);
            key.append(index);
        }

        return key.toString();
    }
}
