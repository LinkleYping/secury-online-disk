import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;

public class VipFrame extends JFrame {

	private JPanel contentPane;
	public Point origin = new Point();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VipFrame frame = new VipFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VipFrame() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 507, 369);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);
		
		JPanel panel = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(Color.GREEN);
				g.fillRect(16, 71, (int) (0), 18);
			}

		};
		//panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(14, 142, 231));
		panel.setBounds(0, 0, 507, 71);
		
		JLabel Title = new JLabel("欢迎使用网盘扩容业务");
		Font f = new Font("微软雅黑", Font.BOLD, 20);
		Title.setFont(f);
		Title.setBounds(144, 0, 341, 71);
		Title.setForeground(Color.WHITE);
		panel.add(Title);
		contentPane.add(panel);
		panel.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		VipFrame this_temp = this;
		panel.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{
				Point p = this_temp.getLocation();
				this_temp.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
			}
		});
		ImageIcon ico = new ImageIcon("左上角.jpg");
		Image temp = ico.getImage().getScaledInstance(30, 30, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image = new JLabel(ico);
		Image.setBounds(35, 10, 65, 50);
		panel.add(Image);
		
		// 最小化按钮
		JButton MinButton = new JButton();
		MinButton.setBounds(440, 8, 12, 12);
		MinButton.setLayout(null);
		MinButton.setBackground(new Color(14, 142, 231));
		MinButton.setBorderPainted(false);
		//VipFrame this_temp = this;
		MinButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				this_temp.setExtendedState(JFrame.ICONIFIED);
			}
		});

		ico = new ImageIcon("最小化.jpg");
		temp = ico.getImage().getScaledInstance(12, 12, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		Image = new JLabel(ico);
		Image.setBounds(0, 0, 12, 12);
		MinButton.add(Image);
		panel.add(MinButton);
		// 退出按钮
		JButton ExitButton = new JButton();
		ExitButton.setBounds(473, 8, 12, 12);
		ExitButton.setLayout(null);
		ExitButton.setBackground(new Color(14, 142, 231));
		ExitButton.setBorderPainted(false);
		ExitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});

		ico = new ImageIcon("退出.jpg");
		temp = ico.getImage().getScaledInstance(12, 12, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		Image = new JLabel(ico);
		Image.setBounds(0, 0, 12, 12);
		ExitButton.add(Image);
		panel.add(ExitButton);
		
		Font fbut = new Font("微软雅黑", Font.BOLD, 15);
		
		JButton btng = new JButton("扩容4G");
		btng.setFont(fbut);
		btng.setForeground(Color.GRAY);
		btng.setBackground(Color.WHITE);
		//btng.setBorder(null);
		btng.setFocusPainted(false);

		//扩容4G
		btng.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object[] options ={ "确认", "取消" };  
				int m = JOptionPane.showOptionDialog(null,"<html><font color=\"green\"  style=\""
	                    + "font-weight:bold;\" >"   
                        + "本次扩容需花费5元，您确认购买吗？" + "</font></html>" , 
						"确认",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
						null, options, options[0]); 
				if(m==0)
				{
					JOptionPane  
	                .showMessageDialog(  
	                    null,  
	                    "<html><font color=\"green\"  style=\""
	                    + "font-weight:bold;\" >"   
	                        + "购买成功" + "</font></html>", "提示",  
	                    JOptionPane.INFORMATION_MESSAGE);
					
				}
				else
					return;
			}
		});
		btng.setBounds(94, 100, 166, 48);
		contentPane.add(btng);
		
		JButton btng_1 = new JButton("扩容8G");
		btng_1.setFont(fbut);
		btng_1.setForeground(Color.GRAY);
		btng_1.setBackground(Color.WHITE);
		//btng_1.setBorder(null);
		btng_1.setFocusPainted(false);
		//扩容8G
		btng_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object[] options ={ "确认", "取消" };  
				int m = JOptionPane.showOptionDialog(null,"<html><font color=\"green\"  style=\""
	                    + "font-weight:bold;\" >"   
                        + "本次扩容需花费9.5元，您确认购买吗？" + "</font></html>" , 
						"确认",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
						null, options, options[0]); 
				if(m==0)
				{
					JOptionPane  
	                .showMessageDialog(  
	                    null,  
	                    "<html><font color=\"green\"  style=\""
	                    + "font-weight:bold;\" >"   
	                        + "购买成功" + "</font></html>", "提示",  
	                    JOptionPane.INFORMATION_MESSAGE);  
				}
				else
					return;
			}
		});
		btng_1.setBounds(94, 177, 166, 48);
		contentPane.add(btng_1);
		
		JButton btng_2 = new JButton("扩容16G");
		btng_2.setFont(fbut);
		btng_2.setForeground(Color.GRAY);
		btng_2.setBackground(Color.WHITE);
		//btng_2.setBorder(null);
		btng_2.setFocusPainted(false);
		//扩容16G
		btng_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object[] options ={ "确认", "取消" };  
				int m = JOptionPane.showOptionDialog(null,"<html><font color=\"green\"  style=\""
	                    + "font-weight:bold;\" >"   
                        + "本次扩容需花费18元，您确认购买吗？" + "</font></html>" , 
						"确认",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
						null, options, options[0]); 
				if(m==0)
				{
					JOptionPane  
	                .showMessageDialog(  
	                    null,  
	                    "<html><font color=\"green\"  style=\""
	                    + "font-weight:bold;\" >"   
	                        + "购买成功" + "</font></html>", "提示",  
	                    JOptionPane.INFORMATION_MESSAGE);  
				}
				else
					return;
			}
		});
		btng_2.setBounds(94, 259, 166, 43);
		contentPane.add(btng_2);
		
		Font flab = new Font("微软雅黑", Font.BOLD, 18);
		JLabel label = new JLabel("5元");
		label.setFont(flab);
		label.setBounds(338, 105, 127, 37);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("9.5元");
		label_1.setFont(flab);
		label_1.setBounds(338, 182, 111, 37);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("18元");
		label_2.setFont(flab);
		label_2.setBounds(338, 261, 111, 37);
		contentPane.add(label_2);
	}
}
