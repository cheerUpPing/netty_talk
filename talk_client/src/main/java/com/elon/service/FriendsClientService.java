package com.elon.service;

import com.elon.comm.AbstractService;
import com.elon.comm.ClientConstants;
import com.elon.entity.Friends;
import com.elon.entity.RequestEntity;
import com.elon.entity.ResponseEntity;
import com.elon.gui.ChatFrame;
import com.elon.gui.FriendsFrame;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * 2017/6/23 13:47.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class FriendsClientService extends AbstractService {

    private static Logger logger = Logger.getLogger(FriendsClientService.class);

    @Override
    public void doBusiness(ChannelHandlerContext channelHandlerContext, RequestEntity entity) {
        ResponseEntity responseEntity = (ResponseEntity) entity;
        if ("0000".equals(responseEntity.getResult())) {
            List<Friends> friends = (List<Friends>) responseEntity.getData();
            if (friends == null || friends.size() == 0) {
                JOptionPane.showMessageDialog(FriendsFrame.friendsFrame, "你目前没有好友");
                return;
            }
            for (Friends friend : friends) {
                String friend_name = friend.get("friend_name");
                JButton jButton = new JButton(friend_name);
                jButton.setPreferredSize(new Dimension(200, 20));
                FriendsFrame.jPanel.add(jButton);
                //必须重绘
                FriendsFrame.jPanel.revalidate();
                //添加jlabel的双击事件
                jButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 2) {
                            JButton jbl = (JButton) e.getSource();
                            String to = jbl.getText();
                            if (ClientConstants.chatFrames.get(to) == null) {
                                ChatFrame chatFrame = new ChatFrame("聊天室--正在和 " + to + " 聊天", to);
                                ClientConstants.chatFrames.put(to, chatFrame);
                            } else {
                                //获取焦点
                                ClientConstants.chatFrames.get(to).requestFocus();
                            }
                        }
                    }
                });
            }
        } else {
            JOptionPane.showMessageDialog(FriendsFrame.friendsFrame, responseEntity.getMsg());
        }
    }
}
