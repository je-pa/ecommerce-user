/**
 * @Date : 2024. 08. 23.
 * @author : jieun(je-pa)
 */
package com.ecommerceuser.global.mail.event;

public interface SendEmailEvent {
  String getToEmail();

  String getSubject();

  String getMessage();
}
