package com.elon.gui;

import com.elon.client.Client;
import com.elon.comm.ClientConstants;
import com.elon.comm.Constants;
import com.elon.entity.RequestEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Map;

/**
 * 2017/6/23 14:13.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * 聊天主界面
 */
public class FriendsFrame extends JFrame {

    private Container container;

    public static FriendsFrame friendsFrame = null;

    public static JPanel jPanel = null;

    public JScrollPane jScrollPane = null;

    private int width = 280;
    private int height = 440;


    public FriendsFrame(String title) {
        container = getContentPane();
        friendsFrame = this;
        setTitle(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //获取屏幕大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //让窗口居中显示
        setLocation(screenSize.width / 2 - width / 2, screenSize.height / 2 - height / 2);
        setSize(width, height);


        init();
        initEvent();
        setVisible(true);
    }

    private void init() {
        BorderLayout borderLayout = new BorderLayout();
        container.setLayout(borderLayout);
        FlowLayout flowLayout = new FlowLayout();
        jPanel = new JPanel(flowLayout);
        jPanel.setPreferredSize(new Dimension(width, height));
        jScrollPane = new JScrollPane(jPanel);//表示用来滚动jTable
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setPreferredSize(new Dimension(width, height));
        container.add(jScrollPane, BorderLayout.CENTER);

        RequestEntity requestEntity = new RequestEntity(ClientConstants.curr_user, Constants.Command.friends);
        Client.sendMessage(requestEntity);
    }

    private void initEvent() {
        /**关闭聊天主界面**/
        this.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                /**关闭所有的子聊天界面**/
                Map<String, ChatFrame> chatFrameMap = ClientConstants.chatFrames;
                for (Map.Entry<String,ChatFrame> entry : chatFrameMap.entrySet()){
                    entry.getValue().dispose();
                }
                RequestEntity requestEntity = new RequestEntity(ClientConstants.curr_user,Constants.Command.logout);
                Client.sendMessage(requestEntity);
            }
        });
    }


}
