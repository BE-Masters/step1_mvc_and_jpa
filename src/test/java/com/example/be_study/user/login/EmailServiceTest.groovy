package com.example.be_study.user.login

import com.example.be_study.service.user.service.EmailService
import com.example.be_study.service.user.service.RedisService
import jakarta.mail.internet.MimeMessage
import org.springframework.mail.javamail.JavaMailSender
import spock.lang.Specification
import spock.lang.Subject

class EmailServiceTest extends Specification {

    @Subject
    EmailService emailService

    JavaMailSender javaMailSender = Mock()

    RedisService redisService = Mock()

    MimeMessage mimeMessage = Mock(MimeMessage)

    def setup() {
        emailService = new EmailService(
                javaMailSender, redisService
        )
    }
}
