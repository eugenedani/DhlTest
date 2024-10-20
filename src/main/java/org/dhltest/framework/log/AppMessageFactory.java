package org.dhltest.framework.log;

import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory2;
import org.apache.logging.log4j.message.ReusableMessageFactory;

/**
 * Makes possible to add a prefix at the beginning of Log4j logger message
 * for example, Browser name
 */
public class AppMessageFactory implements MessageFactory2 {
    private static final ReusableMessageFactory MESSAGE_FACTORY = ReusableMessageFactory.INSTANCE;
    private final String logMessagePrefix;

    public AppMessageFactory(String logMessagePrefix) {
        this.logMessagePrefix = logMessagePrefix;
    }

    @Override
    public Message newMessage(CharSequence charSequence) {
        return MESSAGE_FACTORY.newMessage(fixedMessage(String.valueOf(charSequence)));
    }

    @Override
    public Message newMessage(String message, Object p0) {
        return MESSAGE_FACTORY.newMessage(fixedMessage(message), p0);
    }

    @Override
    public Message newMessage(String message, Object p0, Object p1) {
        return MESSAGE_FACTORY.newMessage(fixedMessage(message), p0, p1);
    }

    @Override
    public Message newMessage(String message, Object p0, Object p1, Object p2) {
        return MESSAGE_FACTORY.newMessage(fixedMessage(message), p0, p1, p2);
    }

    @Override
    public Message newMessage(String message, Object p0, Object p1, Object p2, Object p3) {
        return MESSAGE_FACTORY.newMessage(fixedMessage(message), p0, p1, p2, p3);
    }

    @Override
    public Message newMessage(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        return MESSAGE_FACTORY.newMessage(fixedMessage(message), p0, p1, p2, p3, p4);
    }

    @Override
    public Message newMessage(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        return MESSAGE_FACTORY.newMessage(fixedMessage(message), p0, p1, p2, p3, p4, p5);
    }

    @Override
    public Message newMessage(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        return MESSAGE_FACTORY.newMessage(fixedMessage(message), p0, p1, p2, p3, p4, p5, p6);
    }

    @Override
    public Message newMessage(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        return MESSAGE_FACTORY.newMessage(fixedMessage(message), p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override
    public Message newMessage(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        return MESSAGE_FACTORY.newMessage(fixedMessage(message), p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override
    public Message newMessage(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        return MESSAGE_FACTORY.newMessage(fixedMessage(message), p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override
    public Message newMessage(Object message) {
        if (message instanceof String) {
            message = fixedMessage((String) message);
        }
        return MESSAGE_FACTORY.newMessage(message);
    }

    @Override
    public Message newMessage(String message) {
        return MESSAGE_FACTORY.newMessage(fixedMessage(message));
    }

    @Override
    public Message newMessage(String message, Object... params) {
        return MESSAGE_FACTORY.newMessage(fixedMessage(message), params);
    }

    private String fixedMessage(String message) {
        return logMessagePrefix + ": " + message;
    }
}
