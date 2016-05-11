package edu.sjsu.cmpe275.service;

/**
 * Created by yutao on 5/11/16.
 */
public interface MailService {
    public void send(String from, String to, String subject, String text);
}
