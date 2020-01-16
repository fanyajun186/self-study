package com.example.demo.util.encrypt.yc;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * 提供对票据的属性和值的访问
 *
 * @author 韩锐
 * @date 2019/6/5
 */
public final class Ticket {

    private int version;
    private String name;
    private LocalDateTime expiration;
    private LocalDateTime issueDate;
    private boolean isPersistent;
    private String userData;
    private String cookiePath;

    private LocalDateTime expirationUtc;
    private LocalDateTime issueDateUtc;

    public Ticket(int version,
           String name,
           LocalDateTime issueDate,
           LocalDateTime expiration,
           boolean isPersistent,
           String userData,
           String cookiePath) {

        this.version = version;
        this.name = name;
        this.expiration = expiration;
        this.expirationUtc = expiration.atZone(ZoneOffset.UTC).toLocalDateTime();
        this.issueDate = issueDate;
        this.issueDateUtc = issueDate.atZone(ZoneOffset.UTC).toLocalDateTime();
        this.isPersistent = isPersistent;
        this.userData = userData;
        this.cookiePath = cookiePath;
    }

    private Ticket() {
    }

    static Ticket fromUtc(int version, String name, LocalDateTime issueDateUtc, LocalDateTime expirationUtc, boolean isPersistent, String userData, String cookiePath) {
        Ticket ticket = new Ticket();
        ticket.version = version;
        ticket.name = name;
        ticket.expiration = expirationUtc.atZone(ZoneId.systemDefault()).toLocalDateTime();
        ticket.expirationUtc = expirationUtc;
        ticket.issueDate = issueDateUtc.atZone(ZoneId.systemDefault()).toLocalDateTime();
        ticket.issueDateUtc = issueDateUtc;
        ticket.isPersistent = isPersistent;
        ticket.userData = userData;
        ticket.cookiePath = cookiePath;

        return ticket;
    }

    /**
     * 将已加密过的票据字符串解密
     */
    public static Ticket decrypt(String encryptedTicket) {
        return FormsAuthentication.decrypt(encryptedTicket);
    }

    /**
     * 加密票据
     */
    public String encrypt() {
        try {
            return FormsAuthentication.encrypt(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 票据的版本号
     */
    public int getVersion() {
        return version;
    }

    /**
     * 证票相关联的用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 该值指示Cookie是否为持久性的
     */
    public boolean isPersistent() {
        return isPersistent;
    }

    /**
     * 存储在票据中的用户自定义字符串
     */
    public String getUserData() {
        return userData;
    }

    /**
     * 票据的 Cookie 路径
     */
    public String getCookiePath() {
        return cookiePath;
    }


    /**
     * 票据过期时的本地日期和时间
     */
    public LocalDateTime getExpiration() {
        return expiration;
    }

    /**
     * 票据过期时的utc日期和时间
     */
    public LocalDateTime getExpirationUtc() {
        return expirationUtc;
    }

    /**
     * 最初发出票据时的本地日期和时间
     */
    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    /**
     * 最初发出票据时的utc日期和时间
     */
    public LocalDateTime getIssueDateUtc() {
        return issueDateUtc;
    }

}
