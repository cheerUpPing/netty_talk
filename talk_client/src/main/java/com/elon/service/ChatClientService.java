package com.elon.service;

import com.elon.comm.AbstractService;
import com.elon.comm.ClientConstants;
import com.elon.entity.RequestEntity;
import com.elon.entity.ResponseEntity;
import com.elon.gui.ChatFrame;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * 2017/6/23 13:47.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class ChatClientService extends AbstractService {

    private static Logger logger = Logger.getLogger(ChatClientService.class);

    @Override
    public void doBusiness(ChannelHandlerContext channelHandlerContext, RequestEntity entity) {
        ResponseEntity responseEntity = (ResponseEntity) entity;
        ChatFrame chatFrame = ClientConstants.chatFrames.get(ClientConstants.curr_user.equals(responseEntity.getFrom()) ? responseEntity.getTo() : responseEntity.getFrom());
        if (chatFrame == null) {
            chatFrame = new ChatFrame("聊天室--正在和 " + responseEntity.getFrom() + " 聊天", responseEntity.getFrom());
            ClientConstants.chatFrames.put(responseEntity.getFrom(), chatFrame);
        } else {
            chatFrame.requestFocus();
        }
        chatFrame.jpanel_add("0000".equals(responseEntity.getResult()) ? (String) responseEntity.getData() : responseEntity.getMsg(), 5, 5, 5, 5);
        if (!"0000".equals(responseEntity.getResult())) {
            JOptionPane.showMessageDialog(chatFrame, responseEntity.getMsg());
        }
    }
}
