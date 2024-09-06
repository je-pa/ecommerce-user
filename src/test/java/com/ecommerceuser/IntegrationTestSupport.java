package com.ecommerceuser;

import com.ecommerceuser.global.mail.service.EmailService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public abstract class IntegrationTestSupport {

  @MockBean
  protected EmailService emailService;

}
