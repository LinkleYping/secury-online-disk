import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;
import java.util.Random;

import javax.swing.*;

public class Signup extends JFrame
{
	Container container = null;
	JPanel UpPanel = null;
	JPanel DownPanel = null;
	JTextField MailText = null;
	JTextField PasswordText = null;
	JTextField PasswordText2 = null;
	JTextField VcodeText = null;
	JTextField NicknameText = null;
	JCheckBox Agree = null;
	JLabel NicknameError = null;
	JLabel MailError = null;
	JLabel PasswordError = null;
	JLabel Password2Error = null;
	JLabel VcodeError = null;
	JButton Signup = null;
	private String Vcode = null;
	public Point origin = new Point();

	public Signup()
	{
		Init();
		AddUpPanel();
		AddDownPanel();

		this.setVisible(true);
	}

	public void Init()
	{
		this.setBounds(400, 130, 500, 460);
		this.setUndecorated(true);
		container = this.getContentPane();
		container.setLayout(null);
		// 上面板
		UpPanel = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(Color.BLACK);
				g.drawLine(0, 0, 500, 0);
				g.drawLine(0, 0, 0, 40);
				g.drawLine(498, 0, 498, 40);
			}
		};
		UpPanel.setBounds(0, 0, 500, 40);
		UpPanel.setLayout(null);
		UpPanel.setBackground(new Color(10, 146, 238));
		container.add(UpPanel);
		// 下面板
		DownPanel = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(Color.BLACK);
				g.drawLine(498, 0, 498, 419);
				g.drawLine(0, 0, 0, 460);
				g.drawLine(0, 419, 500, 419);
			}
		};
		DownPanel.setBounds(0, 40, 500, 460);
		DownPanel.setLayout(null);
		DownPanel.setBackground(Color.WHITE);
		container.add(DownPanel);
	}

	public void AddUpPanel()
	{
		// 左上角图标
		ImageIcon ico = new ImageIcon("左上角(登录界面).jpg");
		Image temp = ico.getImage().getScaledInstance(40, 30, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image = new JLabel(ico);
		Image.setBounds(3, 3, 40, 30);
		UpPanel.add(Image);
		// 标题
		JLabel Title = new JLabel();
		Font f = new Font("黑体", Font.BOLD, 17);
		Title.setForeground(new Color(238, 245, 255));
		Title.setFont(f);
		Title.setText("注册网盘姬账号");
		Title.setBounds(50, 0, 150, 40);
		UpPanel.add(Title);
		// 退出按钮
		JButton ExitButton = new JButton();
		ExitButton.setBounds(475, 3, 20, 20);
		ExitButton.setLayout(null);
		ExitButton.setBackground(new Color(14, 142, 231));
		ExitButton.setBorderPainted(false);
		Signup this_temp = this;
		ExitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				this_temp.dispose();
			}
		});

		ico = new ImageIcon("退出.jpg");
		temp = ico.getImage().getScaledInstance(12, 12, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		Image = new JLabel(ico);
		Image.setBounds(5, 5, 12, 12);
		ExitButton.add(Image);
		UpPanel.add(ExitButton);
		// 标题栏移动
		UpPanel.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});

		UpPanel.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{
				Point p = this_temp.getLocation();
				this_temp.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
			}
		});
	}

	private void AddNickname()
	{
		// 昵称标签
		JLabel NicknameLabel = new JLabel();
		NicknameLabel.setBounds(100, 30, 50, 30);
		Font f = new Font("黑体", Font.PLAIN, 16);
		NicknameLabel.setFont(f);
		NicknameLabel.setText("昵称");
		DownPanel.add(NicknameLabel);
		// 昵称
		NicknameText = new JTextField("2~8个汉字、字母或数字");
		NicknameText.setBounds(150, 34, 240, 27);
		NicknameText.setForeground(Color.GRAY);
		DownPanel.add(NicknameText);
		NicknameText.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				if (NicknameText.getText().equals("2~8个汉字、字母或数字"))
				{
					NicknameText.setText("");
					NicknameText.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e)
			{
				if (NicknameText.getText().equals(""))
				{
					NicknameText.setForeground(Color.GRAY);
					NicknameText.setText("2~8个汉字、字母或数字");
				}
			}
		});
		DownPanel.add(NicknameText);
		// 错误标签
		NicknameError = new JLabel();
		NicknameError.setBounds(400, 30, 200, 30);
		f = new Font("黑体", Font.PLAIN, 13);
		NicknameError.setForeground(Color.RED);
		NicknameError.setFont(f);
		NicknameError.setText("");
		DownPanel.add(NicknameError);
	}

	private void AddMail()
	{
		// 邮箱标签
		JLabel MailLabel = new JLabel();
		MailLabel.setBounds(100, 80, 50, 30);
		Font f = new Font("黑体", Font.PLAIN, 16);
		MailLabel.setFont(f);
		MailLabel.setText("邮箱");
		DownPanel.add(MailLabel);
		// 邮箱
		MailText = new JTextField("请输入邮箱");
		MailText.setBounds(150, 84, 240, 27);
		MailText.setForeground(Color.GRAY);
		DownPanel.add(MailText);
		MailText.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				if (MailText.getText().equals("请输入邮箱"))
				{
					MailText.setText("");
					MailText.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e)
			{
				if (MailText.getText().equals(""))
				{
					MailText.setForeground(Color.GRAY);
					MailText.setText("请输入邮箱");
				}
			}
		});
		// 错误标签
		MailError = new JLabel();
		MailError.setBounds(400, 80, 200, 30);
		f = new Font("黑体", Font.PLAIN, 13);
		MailError.setForeground(Color.RED);
		MailError.setFont(f);
		MailError.setText("");
		DownPanel.add(MailError);
	}

	private void AddPassword()
	{
		// 密码标签
		JLabel PasswordLabel = new JLabel();
		PasswordLabel.setBounds(100, 128, 50, 30);
		Font f = new Font("黑体", Font.PLAIN, 16);
		PasswordLabel.setFont(f);
		PasswordLabel.setText("密码");
		DownPanel.add(PasswordLabel);
		// 密码
		PasswordText = new JPasswordField();
		PasswordText.setBounds(150, 132, 240, 27);
		PasswordText.setText("");
		PasswordText.setForeground(Color.GRAY);
		PasswordText.setVisible(false);
		DownPanel.add(PasswordText);

		JTextField PasswordTipText = new JTextField();
		PasswordTipText.setBounds(150, 132, 240, 27);
		PasswordTipText.setText("8~20个字母和数字组合");
		PasswordTipText.setForeground(Color.GRAY);
		DownPanel.add(PasswordTipText);
		PasswordTipText.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				PasswordTipText.setVisible(false);
				PasswordText.setVisible(true);
				PasswordText.requestFocusInWindow();
			}
		});
		PasswordText.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				if (PasswordText.getText().equals("8~20个字母和数字组合"))
				{
					PasswordText.setText("");
					PasswordText.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e)
			{
				if (PasswordText.getText().equals(""))
				{
					PasswordTipText.setText("8~20个字母和数字组合");
					PasswordTipText.setVisible(true);
					PasswordText.setVisible(false);
				}
			}
		});
		// 错误标签
		PasswordError = new JLabel();
		PasswordError.setBounds(400, 132, 200, 30);
		f = new Font("黑体", Font.PLAIN, 13);
		PasswordError.setForeground(Color.RED);
		PasswordError.setFont(f);
		PasswordError.setText("");
		DownPanel.add(PasswordError);
	}

	private void AddPassword2()
	{
		// 再次密码标签
		JLabel PasswordLabel2 = new JLabel();
		PasswordLabel2.setBounds(70, 178, 70, 30);
		Font f = new Font("黑体", Font.PLAIN, 16);
		PasswordLabel2.setFont(f);
		PasswordLabel2.setText("再次密码");
		DownPanel.add(PasswordLabel2);
		// 再次密码
		PasswordText2 = new JPasswordField();
		PasswordText2.setBounds(150, 182, 240, 27);
		PasswordText2.setText("");
		PasswordText2.setForeground(Color.GRAY);
		PasswordText2.setVisible(false);
		DownPanel.add(PasswordText2);

		JTextField PasswordTipText2 = new JTextField();
		PasswordTipText2.setBounds(150, 182, 240, 27);
		PasswordTipText2.setText("两次密码必须一致");
		PasswordTipText2.setForeground(Color.GRAY);
		DownPanel.add(PasswordTipText2);
		PasswordTipText2.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				PasswordTipText2.setVisible(false);
				PasswordText2.setVisible(true);
				PasswordText2.requestFocusInWindow();
			}
		});
		PasswordText2.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				if (PasswordText2.getText().equals("两次密码必须一致"))
				{
					PasswordText2.setText("");
					PasswordText2.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e)
			{
				if (PasswordText2.getText().equals(""))
				{
					PasswordTipText2.setText("两次密码必须一致");
					PasswordTipText2.setVisible(true);
					PasswordText2.setVisible(false);
				}
			}
		});
		// 错误标签
		Password2Error = new JLabel();
		Password2Error.setBounds(400, 182, 200, 30);
		f = new Font("黑体", Font.PLAIN, 13);
		Password2Error.setForeground(Color.RED);
		Password2Error.setFont(f);
		Password2Error.setText("");
		DownPanel.add(Password2Error);
	}

	private void AddVcode()
	{
		// 验证码标签
		JLabel VcodeLabel = new JLabel();
		VcodeLabel.setBounds(90, 230, 50, 30);
		Font f = new Font("黑体", Font.PLAIN, 16);
		VcodeLabel.setFont(f);
		VcodeLabel.setText("验证码");
		DownPanel.add(VcodeLabel);
		// 验证码
		VcodeText = new JTextField("");
		VcodeText.setBounds(150, 234, 100, 26);
		VcodeText.setForeground(Color.GRAY);
		DownPanel.add(VcodeText);
		// 发送验证码按钮
		JButton SendVcode = new JButton();
		SendVcode.setBounds(260, 234, 105, 26);
		SendVcode.setBackground(Color.WHITE);
		f = new Font("黑体", Font.PLAIN, 14);
		SendVcode.setFont(f);
		SendVcode.setText("获取验证码");
		SendVcode.setFocusable(false);
		SendVcode.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				SendVcode.setEnabled(false);
				// 倒计时线程
				Thread CountSeconds = new Thread(new Runnable()
				{
					public void run()
					{
						double StartTime = System.currentTimeMillis();
						while (true)
						{
							double LastTime = (System.currentTimeMillis() - StartTime) / 1000;
							if (LastTime >= 60)
							{
								SendVcode.setText("获取验证码");
								SendVcode.setEnabled(true);
								break;
							}
							SendVcode.setText(60 - (int) LastTime + "秒");
							try
							{
								Thread.sleep(100);
							} catch (InterruptedException e)
							{
								e.printStackTrace();
							}
						}
					}
				});
				CountSeconds.start();
				// 发送邮件线程
				Thread SendMail = new Thread(new Runnable()
				{
					public void run()
					{
						SendMail s = new SendMail();
						try
						{
							s.send("qq.com", "806675223@qq.com", "jznpdachbvzzbdic", MailText.getText(), "网盘姬注册验证码",
									CreateVcode());
						} catch (Exception e)
						{
							e.printStackTrace();
						}
					}
				});
				SendMail.start();
			}
		});

		DownPanel.add(SendVcode);
		// 错误标签
		VcodeError = new JLabel();
		VcodeError.setBounds(380, 234, 200, 30);
		f = new Font("黑体", Font.PLAIN, 13);
		VcodeError.setForeground(Color.RED);
		VcodeError.setFont(f);
		VcodeError.setText("");
		DownPanel.add(VcodeError);
	}

	private void AddAgree()
	{
		// 同意条款复选框
		Agree = new JCheckBox();
		Agree.setBounds(147, 280, 20, 16);
		Agree.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (Agree.isSelected())
					Signup.setEnabled(true);
				else
					Signup.setEnabled(false);
			}
		});
		DownPanel.add(Agree);
		// 同意条款标签
		JLabel AgreeLabel = new JLabel();
		AgreeLabel.setBounds(170, 280, 200, 16);
		Font f = new Font("黑体", Font.PLAIN, 14);
		AgreeLabel.setFont(f);
		AgreeLabel.setText("阅读并接受《网盘姬用户协议》");
		AgreeLabel.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				Agree.setSelected(!Agree.isSelected());
				if (Agree.isSelected())
					Signup.setEnabled(true);
				else
					Signup.setEnabled(false);
			}
		});
		DownPanel.add(AgreeLabel);
	}

	private void AddSignupButton()
	{
		// 注册按钮
		Signup = new JButton("注册");
		Font f = new Font("黑体", Font.PLAIN, 18);
		Signup.setForeground(Color.WHITE);
		Signup.setFont(f);
		Signup.setBounds(147, 320, 250, 40);
		Signup.setFocusable(false);
		Signup.setEnabled(false);
		Color blue1 = new Color(69, 172, 239);
		Color blue2 = new Color(110, 190, 243);
		Color blue3 = new Color(97, 160, 203);
		Signup.setBorder(null);
		Signup.setBackground(blue1);
		Signup this_temp = this;
		Signup.addMouseListener(new MouseAdapter()
		{
			// 鼠标移入
			public void mouseEntered(MouseEvent e)
			{
				super.mouseEntered(e);
				if (Agree.isSelected())
					Signup.setBackground(blue2);
			}

			// 鼠标移出
			public void mouseExited(MouseEvent e)
			{
				super.mouseExited(e);
				Signup.setBackground(blue1);
			}

			public void mousePressed(MouseEvent e)
			{
				if (Agree.isSelected())
					Signup.setBackground(blue3);
			}

			public void mouseReleased(MouseEvent e)
			{
				if (Agree.isSelected())
					Signup.setBackground(blue2);
			}

			public void mouseClicked(MouseEvent e)
			{
				if (!Agree.isSelected())
					return;
				// 检查昵称
				if (NicknameText.getText().isEmpty() || "2~8个汉字、字母或数字".equals(NicknameText.getText()))
				{
					NicknameError.setText("请填写昵称");
					return;
				}
				if (CheckNickname(NicknameText.getText()) == false)
				{
					NicknameError.setText("昵称格式不正确");
					return;
				} else
				{
					NicknameError.setText("");
				}
				// 检查邮箱
				if (MailText.getText().isEmpty() || "请输入邮箱".equals(MailText.getText()))
				{
					MailError.setText("请输入邮箱");
					return;
				}
				if (CheckMail(MailText.getText()) == false)
				{
					MailError.setText("邮箱格式不正确");
					return;
				} else
				{
					MailError.setText("");
				}
				// 检查密码
				if (PasswordText.getText().isEmpty() || "8~20个字母和数字组合".equals(PasswordText.getText()))
				{
					PasswordError.setText("请输入密码");
					return;
				}
				if (CheckPassword(PasswordText.getText()) == false)
				{
					PasswordError.setText("密码格式不正确");
					return;
				} else
				{
					PasswordError.setText("");
				}
				// 检查第二次输入的密码
				if (PasswordText2.getText().isEmpty())
				{
					Password2Error.setText("请再次输入密码");
					return;
				}
				if (CheckPassword2(PasswordText.getText(),PasswordText2.getText()) == false)
				{
					System.out.println(PasswordText.getText() + " " + PasswordText2.getText());
					Password2Error.setText("两次密码不一致");
					return;
				} else
				{
					Password2Error.setText("");
				}
				//检查验证码
				if (VcodeText.getText().isEmpty())
				{
					VcodeError.setText("请输入验证码");
					return;
				}
				if (CheckVcode() == false)
				{
					VcodeError.setText("验证码错误");
					return;
				} else
				{
					VcodeError.setText("");
				}
				//发送注册数据
				SendSignupData();
			}
		});
		DownPanel.add(Signup);
	}

	private String CreateVcode()
	{
		int max = 9999;
		int min = 1000;
		Random random = new Random();

		int s = random.nextInt(max) % (max - min + 1) + min;
		Vcode = String.valueOf(s);
		return String.valueOf(s + "\r\n请您尽快进行验证，完成注册");
	}

	private boolean CheckVcode()
	{
		return Vcode.equals(VcodeText.getText());
	}

	private boolean CheckMail(String Address)
	{
		if (!Address.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$"))
			return false;
		return true;
	}

	private boolean CheckPassword(String Password)
	{
		if (Password.length() < 8 || Password.length() > 20)
			return false;
		for (int i = 0; i < Password.length(); i++)
		{
			if (Password.charAt(i) >= 0x30 && Password.charAt(i) <= 0x39)
				continue;
			if (Password.charAt(i) >= 0x61 && Password.charAt(i) <= 0x7a)
				continue;
			if (Password.charAt(i) >= 0x41 && Password.charAt(i) <= 0x5a)
				continue;
			return false;
		}
		return true;
	}

	private boolean CheckPassword2(String Password1, String Password2)
	{
		return Password1.equals(Password2);
	}

	private boolean CheckNickname(String Nickname)
	{
		if (Nickname.length() < 2 || Nickname.length() > 8)
			return false;
		for (int i = 0; i < Nickname.length(); i++)
		{
			if (Nickname.charAt(i) >= 0x4e00 && Nickname.charAt(i) <= 0x9fa5)
				continue;
			if (Nickname.charAt(i) >= 0x30 && Nickname.charAt(i) <= 0x39)
				continue;
			if (Nickname.charAt(i) >= 0x61 && Nickname.charAt(i) <= 0x7a)
				continue;
			if (Nickname.charAt(i) >= 0x41 && Nickname.charAt(i) <= 0x5a)
				continue;
			return false;
		}
		return true;
	}

	public void AddDownPanel()
	{
		AddNickname();
		AddMail();
		AddPassword();
		AddPassword2();
		AddVcode();
		AddAgree();
		AddSignupButton();
	}
	
	private void SendSignupData()
	{
		HashMap<String,String> map = new HashMap<>();
		map.put("Mark", "Sign Up");
		map.put("Nickname", NicknameText.getText());
		map.put("Username", MailText.getText());
		map.put("Password", PasswordText.getText());
		Send.send(MapString.MapToString(map));
	}
}
