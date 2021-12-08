import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainn {
    private static void createf() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        Font font = new Font("C:\\Windows\\Fonts\\等线", Font.PLAIN,15);
        JFrame frame = new JFrame("信息检索");
        frame.setSize(600, 555);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(444, 355, 755, 444);
        frame.setBackground(Color.white);
        frame.setFont(font);

        JPanel panel = new JPanel();
        panel.setBackground(Color.white);

        JTextField text1 = new JTextField();
        text1.setBounds(88, 45, 400, 25);
        text1.setEditable(true);
        text1.setColumns(20);
        frame.add(text1);
        text1.setFont(font);


        JTextField text2 = new JTextField();
        text2.setBounds(88, 90, 400, 25);
        text2.setEditable(true);
        text2.setColumns(30);
        frame.add(text2);
        text2.setFont(font);

        JLabel label1 = new JLabel("文件夹:");
        label1.setBounds(22, 40, 444, 30);
        frame.add(label1);
        label1.setFont(font);

        JLabel label2 = new JLabel("  查询:");
        label2.setBounds(22, 85, 444, 30);
        frame.add(label2);
        label2.setFont(font);

        JLabel label3 = new JLabel("  结果:");
        label3.setBounds(22, 120, 444, 30);
        frame.add(label3);
        label3.setFont(font);

        JLabel label4 = new JLabel( );
        label4.setBounds(22, 140, 666, 888);
        label4.setSize(666,222);
        frame.add(label4);
        label4.setFont(font);

        JButton btn0 = new JButton("创建并显示倒排索引");
        btn0.setBounds(555, 40, 170, 35);
        btn0.setBackground(Color.white);
        btn0.setForeground(Color.BLACK);
        btn0.setFont(font);
        btn0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        //  ".\\hyatt-k"
                daopaisuoyin ddd = new daopaisuoyin(text1.getText());
                ddd.show();
            }
        });
        frame.add(btn0);


        JButton btn1 = new JButton("查询");
        btn1.setBounds(555, 80, 170, 35);
        btn1.setBackground(Color.white);
        btn1.setForeground(Color.BLACK);
        btn1.setFont(font);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                daopaisuoyin ddd = new daopaisuoyin(".\\hyatt-k");
                daopaisuoyin ddd = new daopaisuoyin(text1.getText());
                label4.setText("<html>"+jiansuo.jiansuo(ddd,text2.getText())+"</html>");
            }
        });
        frame.add(btn1);


        frame.add(panel);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        createf();

//        daopaisuoyin ddd=new daopaisuoyin(".\\hyatt-k");
//        ddd.show();
//        jiansuo.jiansuo(ddd,"dHTML");
//        jiansuo.jiansuo(ddd,"HTML AND and");


    }

}
