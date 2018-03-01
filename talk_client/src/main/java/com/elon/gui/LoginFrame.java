package com.elon.gui;

import com.elon.client.Client;
import com.elon.comm.ClientConstants;
import com.elon.comm.Constants;
import com.elon.entity.RequestEntity;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 2017/6/23 10:36.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class LoginFrame extends JFrame {

    private Container container = null;

    public static LoginFrame loginFrame = null;


    private JPanel jp_center = null;
    private JPanel jp_sourth = null;

    private JLabel jbl_name = null;
    private JLabel jbl_pass = null;

    private JTextField jfd_name = null;
    private JTextField jfd_pass = null;

    public static JOptionPane jOptionPane = null;

    //动作按钮
    private JButton go = null;
    private JButton cancel = null;

    //窗口尺寸
    private int width = 350;
    private int height = 450;

    public LoginFrame(String title) {
        loginFrame = this;
        container = getContentPane();
        setTitle(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //获取屏幕大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //让窗口居中显示
        setLocation(screenSize.width / 2 - width / 2, screenSize.height / 2 - height / 2);
        setSize(width, height);
        init();
        initEvent();
    }

    private void init() {

        BorderLayout borderLayout = new BorderLayout();
        FlowLayout flowLayout = new FlowLayout();
        container.setLayout(borderLayout);

        jp_center = new JPanel(flowLayout);
        jp_sourth = new JPanel(flowLayout);

        container.add(jp_center, BorderLayout.CENTER);
        container.add(jp_sourth, BorderLayout.SOUTH);

        go = new JButton("登陆");
        cancel = new JButton("取消");

        jp_sourth.add(go);
        jp_sourth.add(cancel);

        jbl_name = new JLabel("用户名");
        jbl_pass = new JLabel("密码");

        jfd_name = new JTextField(10);
        jfd_pass = new JTextField(10);

        jp_center.add(jbl_name);
        jp_center.add(jfd_name);
        jp_center.add(jbl_pass);
        jp_center.add(jfd_pass);

        setVisible(true);
    }

    /**
     * 绑定事件
     */
    private void initEvent() {
        //注册 按钮
        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = LoginFrame.this.jfd_name.getText();
                String pass = LoginFrame.this.jfd_pass.getText();
                if (StringUtils.isEmpty(username) || StringUtils.isEmpty(pass)) {
                    JOptionPane.showMessageDialog(LoginFrame.this,"输入框不能为空");
                    return;
                }

                ClientConstants.curr_user = username;
                RequestEntity requestEntity = new RequestEntity(username, Constants.Command.login, DigestUtils.md2Hex(pass));
                //注册
                Client.sendMessage(requestEntity);

            }
        });
        //暂不登陆 按钮
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginFrame.this.dispose();
            }
        });

    }

    public static void main(String[] args) {
        LoginFrame loginFrame = new LoginFrame("聊天室--登陆");
    }

}
