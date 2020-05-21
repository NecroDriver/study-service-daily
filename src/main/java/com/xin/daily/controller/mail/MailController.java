package com.xin.daily.controller.mail;

import com.xin.daily.common.consts.MailConst;
import com.xin.web.base.BaseController;
import com.xin.web.pojo.Context;
import com.xin.web.utils.mail.MailUtils;
import com.xin.web.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 邮件控制类
 *
 * @author creator mafh 2019/12/17 13:20
 * @author updater
 * @version 1.0.0
 */
@RestController
@RequestMapping("/study/public")
public class MailController extends BaseController {

    /**
     * 邮件工具类
     */
    @Autowired
    private MailUtils mailUtils;

    @PostMapping("/sendMail")
    public ResultVo sendMail(Context context, String receiver, String mailContent) {
        mailUtils.sendMail(MailConst.MAIL_SENDER, receiver, MailConst.MAIL_DEFAULT_SUBJECT, mailContent);
        return ResultVo.successVo("发送邮件成功！");
    }

    @PostMapping("/sendHtmlMail")
    public ResultVo sendHtmlMail(Context context, String receiver, String mailContent) {
        mailUtils.sendHtmlMail(MailConst.MAIL_SENDER, receiver, MailConst.MAIL_DEFAULT_SUBJECT, mailContent);
        return ResultVo.successVo("发送html邮件成功！");
    }

    @PostMapping("/sendFileMail")
    public ResultVo sendFileMail(Context context, String receiver, String mailContent, String filePath) {
        mailUtils.sendFileMail(MailConst.MAIL_SENDER, receiver, MailConst.MAIL_DEFAULT_SUBJECT, mailContent, filePath);
        return ResultVo.successVo("发送附件邮件成功！");
    }

    @PostMapping("/sendInlineImgMail")
    public ResultVo sendInlineImgMail(Context context, String receiver, String mailContent, String imgPath) {
        List<String> imgList = new ArrayList<>();
        imgList.add(imgPath);
        mailUtils.sendInlineImgMail(MailConst.MAIL_SENDER, receiver, MailConst.MAIL_DEFAULT_SUBJECT, mailContent, imgList);
        return ResultVo.successVo("发送内容图片邮件成功！");
    }
}
