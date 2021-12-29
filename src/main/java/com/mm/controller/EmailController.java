package com.mm.controller;

import com.mm.dto.ResultInfo;
import com.mm.dto.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Description 邮箱类
 * @Author MKC
 * @Date 2021/12/29
 */
@Api(tags = "邮箱类")
@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private JavaMailSender jms;
    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送简单邮件
     * @return 成功失败
     */
    @ApiOperation(value = "发送简单邮件", notes = "发送简单邮件")
    @GetMapping("/sendSimpleEmail")
    public ResultInfo sendSimpleEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            // 接收地址
            message.setTo("自己的邮箱@qq.com");
            // 标题
            message.setSubject("一封简单的邮件");
            // 内容
            message.setText("使用Spring Boot发送简单邮件。");
            jms.send(message);
            return new ResultInfo(Status.SUCCESS.code, "发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultInfo(Status.SYSTEM_ERROR.code, e.getMessage());
        }
    }

    /**
     * 发送HTML格式的邮件
     * @return 成功失败
     */
    @ApiOperation(value = "发送HTML格式的邮件", notes = "发送HTML格式的邮件")
    @GetMapping("sendHtmlEmail")
    public ResultInfo sendHtmlEmail() {
        try {
            MimeMessage message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            // 接收地址
            helper.setTo("自己的邮箱@qq.com");
            // 标题
            helper.setSubject("一封HTML格式的邮件");
            // 带HTML格式的内容
            helper.setText("<p style='color:#6db33f'>使用Spring Boot发送HTML格式邮件。</p>", true);
            jms.send(message);
            return new ResultInfo(Status.SUCCESS.code, "发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultInfo(Status.SYSTEM_ERROR.code, e.getMessage());
        }
    }

    /**
     * 发送带附件的邮件
     * @return 成功失败
     */
    @ApiOperation(value = "发送带附件的邮件", notes = "发送带附件的邮件")
    @GetMapping("sendAttachmentsMail")
    public ResultInfo sendAttachmentsMail() {
        try {
            MimeMessage message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            // 接收地址
            helper.setTo("自己的邮箱@qq.com");
            // 标题
            helper.setSubject("一封带附件的邮件");
            // 内容
            helper.setText("使用Spring Boot发送带附件的邮件,详情参见附件内容！");
            // 传入附件
            FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/file/1.txt"));
            helper.addAttachment("1.txt", file);
            jms.send(message);
            return new ResultInfo(Status.SUCCESS.code, "发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultInfo(Status.SYSTEM_ERROR.code, e.getMessage());
        }
    }

    /**
     * 发送带静态资源的邮件
     * @return 成功失败
     */
    @ApiOperation(value = "发送带静态资源的邮件", notes = "发送带静态资源的邮件")
    @GetMapping("sendInlineMail")
    public ResultInfo sendInlineMail() {
        try {
            MimeMessage message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            // 接收地址
            helper.setTo("自己的邮箱@qq.com");
            // 标题
            helper.setSubject("一封带静态资源的邮件");
            // 内容，需要使用cid来标识资源
            helper.setText("<html><body><img src='cid:img'/></body></html>", true);
            // 传入附件
            FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/img/1.png"));
            //这边的img就是上面cid后面的
            helper.addInline("img", file);
            jms.send(message);
            return new ResultInfo(Status.SUCCESS.code, "发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultInfo(Status.SYSTEM_ERROR.code, e.getMessage());
        }
    }

    /**
     * 发送验证码的邮件，本质还是Html
     * @param code 验证码
     * @return 成功失败
     */
    @ApiOperation(value = "发送验证码的邮件", notes = "发送验证码的邮件")
    @GetMapping("sendTemplateEmail")
    public ResultInfo sendTemplateEmail(String code) {
        try {
            MimeMessage message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            // 接收地址
            helper.setTo("自己的邮箱@qq.com");
            // 标题
            helper.setSubject("验证码的邮件摸板测试");
            // 处理邮件模板
            Context context = new Context();
            context.setVariable("code", code);
            String template = templateEngine.process("emailTemplate", context);
            helper.setText(template, true);
            jms.send(message);
            return new ResultInfo(Status.SUCCESS.code, "发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultInfo(Status.SYSTEM_ERROR.code, e.getMessage());
        }
    }
}
