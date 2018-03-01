package com.elon.gui;

import com.elon.client.Client;
import com.elon.comm.ClientConstants;
import com.elon.comm.Constants;
import com.elon.entity.RequestEntity;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 2017/6/23 17:33.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * 聊天界面
 */
public class ChatFrame extends JFrame {

    private Container container;

    private JPanel jPanel = null;

    private JTextField jtf_input = null;
    private JButton jbt_send = null;

    private String to = null;

    private int width = 350;
    private int height = 450;


    public ChatFrame(String title, String to) {
        container = getContentPane();
        this.to = to;
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
        JScrollPane jScrollPane = new JScrollPane(jPanel);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setPreferredSize(new Dimension(width, height));

        jtf_input = new JTextField(20);
        jbt_send = new JButton("发送");
        JPanel jp_south = new JPanel(new FlowLayout());
        jp_south.add(jtf_input);
        jp_south.add(jbt_send);
        container.add(jScrollPane, BorderLayout.CENTER);
        container.add(jp_south, BorderLayout.SOUTH);

    }

    private void initEvent() {
        /**
         * 窗口关闭事件
         */
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ClientConstants.chatFrames.remove(to);
            }
        });

        jbt_send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String send_text = jtf_input.getText();
                if (StringUtils.isEmpty(send_text)) {
                    return;
                }
                jpanel_add(send_text, 5, 5, 5, 5);
                jtf_input.setText("");
                RequestEntity requestEntity = new RequestEntity(ClientConstants.curr_user, to, Constants.Command.chat, send_text);
                Client.sendMessage(requestEntity);
            }
        });
    }

    public synchronized void jpanel_add(String text, int top, int left, int bottom, int right) {
        JButton add_text_panel = new JButton(text);
        add_text_panel.setPreferredSize(new Dimension(width, 30));
        add_text_panel.setMargin(new Insets(top, left, bottom, right));
        jPanel.add(add_text_panel);
        jPanel.revalidate();
    }


}
