package com.elon.service;

import com.elon.comm.AbstractService;
import com.elon.entity.RequestEntity;
import com.elon.entity.ResponseEntity;
import com.elon.gui.FriendsFrame;
import com.elon.gui.LoginFrame;
import com.elon.gui.RegisterFrame;
import io.netty.channel.ChannelHandlerContext;

import javax.swing.*;

/**
 * 2017/6/23 13:47.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class RegisterClientService extends AbstractService {

    @Override
    public void doBusiness(ChannelHandlerContext channelHandlerContext, RequestEntity entity) {
        ResponseEntity responseEntity = (ResponseEntity) entity;
        if ("0000".equals(responseEntity.getResult())) {
            JOptionPane.showMessageDialog(LoginFrame.loginFrame, "注册成功");
            JOptionPane.showMessageDialog(LoginFrame.loginFrame, "登陆成功");
            RegisterFrame.registerFrame.dispose();
            new FriendsFrame("聊天室");
        } else {
            JOptionPane.showMessageDialog(LoginFrame.loginFrame, responseEntity.getMsg());
        }
    }
}
