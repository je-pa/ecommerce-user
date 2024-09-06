/**
 * @Date : 2024. 08. 23.
 * @author : jieun(je-pa)
 */
package com.ecommerceuser.global.mail.event.listener;

import com.ecommerceuser.global.mail.event.SendEmailEvent;
import com.ecommerceuser.global.mail.service.EmailService;
import com.ecommerceuser.global.mail.service.EmailServiceImpl.SendMailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendEmailEventListener {
  private final EmailService emailService;

  @EventListener
  public void handleSendEmailEvent(final SendEmailEvent event) {
    emailService.sendMailMessage(SendMailDto.from(event));
  }

}
