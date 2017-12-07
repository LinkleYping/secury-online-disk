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
		// �����
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
		// �����
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
		// ���Ͻ�ͼ��
		ImageIcon ico = new ImageIcon("���Ͻ�(��¼����).jpg");
		Image temp = ico.getImage().getScaledInstance(40, 30, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image = new JLabel(ico);
		Image.setBounds(3, 3, 40, 30);
		UpPanel.add(Image);
		// ����
		JLabel Title = new JLabel();
		Font f = new Font("����", Font.BOLD, 17);
		Title.setForeground(new Color(238, 245, 255));
		Title.setFont(f);
		Title.setText("ע�����̼��˺�");
		Title.setBounds(50, 0, 150, 40);
		UpPanel.add(Title);
		// �˳���ť
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

		ico = new ImageIcon("�˳�.jpg");
		temp = ico.getImage().getScaledInstance(12, 12, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		Image = new JLabel(ico);
		Image.setBounds(5, 5, 12, 12);
		ExitButton.add(Image);
		UpPanel.add(ExitButton);
		// �������ƶ�
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
		// �ǳƱ�ǩ
		JLabel NicknameLabel = new JLabel();
		NicknameLabel.setBounds(100, 30, 50, 30);
		Font f = new Font("����", Font.PLAIN, 16);
		NicknameLabel.setFont(f);
		NicknameLabel.setText("�ǳ�");
		DownPanel.add(NicknameLabel);
		// �ǳ�
		NicknameText = new JTextField("2~8�����֡���ĸ������");
		NicknameText.setBounds(150, 34, 240, 27);
		NicknameText.setForeground(Color.GRAY);
		DownPanel.add(NicknameText);
		NicknameText.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				if (NicknameText.getText().equals("2~8�����֡���ĸ������"))
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
					NicknameText.setText("2~8�����֡���ĸ������");
				}
			}
		});
		DownPanel.add(NicknameText);
		// �����ǩ
		NicknameError = new JLabel();
		NicknameError.setBounds(400, 30, 200, 30);
		f = new Font("����", Font.PLAIN, 13);
		NicknameError.setForeground(Color.RED);
		NicknameError.setFont(f);
		NicknameError.setText("");
		DownPanel.add(NicknameError);
	}

	private void AddMail()
	{
		// �����ǩ
		JLabel MailLabel = new JLabel();
		MailLabel.setBounds(100, 80, 50, 30);
		Font f = new Font("����", Font.PLAIN, 16);
		MailLabel.setFont(f);
		MailLabel.setText("����");
		DownPanel.add(MailLabel);
		// ����
		MailText = new JTextField("����������");
		MailText.setBounds(150, 84, 240, 27);
		MailText.setForeground(Color.GRAY);
		DownPanel.add(MailText);
		MailText.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				if (MailText.getText().equals("����������"))
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
					MailText.setText("����������");
				}
			}
		});
		// �����ǩ
		MailError = new JLabel();
		MailError.setBounds(400, 80, 200, 30);
		f = new Font("����", Font.PLAIN, 13);
		MailError.setForeground(Color.RED);
		MailError.setFont(f);
		MailError.setText("");
		DownPanel.add(MailError);
	}

	private void AddPassword()
	{
		// �����ǩ
		JLabel PasswordLabel = new JLabel();
		PasswordLabel.setBounds(100, 128, 50, 30);
		Font f = new Font("����", Font.PLAIN, 16);
		PasswordLabel.setFont(f);
		PasswordLabel.setText("����");
		DownPanel.add(PasswordLabel);
		// ����
		PasswordText = new JPasswordField();
		PasswordText.setBounds(150, 132, 240, 27);
		PasswordText.setText("");
		PasswordText.setForeground(Color.GRAY);
		PasswordText.setVisible(false);
		DownPanel.add(PasswordText);

		JTextField PasswordTipText = new JTextField();
		PasswordTipText.setBounds(150, 132, 240, 27);
		PasswordTipText.setText("8~20����ĸ���������");
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
				if (PasswordText.getText().equals("8~20����ĸ���������"))
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
					PasswordTipText.setText("8~20����ĸ���������");
					PasswordTipText.setVisible(true);
					PasswordText.setVisible(false);
				}
			}
		});
		// �����ǩ
		PasswordError = new JLabel();
		PasswordError.setBounds(400, 132, 200, 30);
		f = new Font("����", Font.PLAIN, 13);
		PasswordError.setForeground(Color.RED);
		PasswordError.setFont(f);
		PasswordError.setText("");
		DownPanel.add(PasswordError);
	}

	private void AddPassword2()
	{
		// �ٴ������ǩ
		JLabel PasswordLabel2 = new JLabel();
		PasswordLabel2.setBounds(70, 178, 70, 30);
		Font f = new Font("����", Font.PLAIN, 16);
		PasswordLabel2.setFont(f);
		PasswordLabel2.setText("�ٴ�����");
		DownPanel.add(PasswordLabel2);
		// �ٴ�����
		PasswordText2 = new JPasswordField();
		PasswordText2.setBounds(150, 182, 240, 27);
		PasswordText2.setText("");
		PasswordText2.setForeground(Color.GRAY);
		PasswordText2.setVisible(false);
		DownPanel.add(PasswordText2);

		JTextField PasswordTipText2 = new JTextField();
		PasswordTipText2.setBounds(150, 182, 240, 27);
		PasswordTipText2.setText("�����������һ��");
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
				if (PasswordText2.getText().equals("�����������һ��"))
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
					PasswordTipText2.setText("�����������һ��");
					PasswordTipText2.setVisible(true);
					PasswordText2.setVisible(false);
				}
			}
		});
		// �����ǩ
		Password2Error = new JLabel();
		Password2Error.setBounds(400, 182, 200, 30);
		f = new Font("����", Font.PLAIN, 13);
		Password2Error.setForeground(Color.RED);
		Password2Error.setFont(f);
		Password2Error.setText("");
		DownPanel.add(Password2Error);
	}

	private void AddVcode()
	{
		// ��֤���ǩ
		JLabel VcodeLabel = new JLabel();
		VcodeLabel.setBounds(90, 230, 50, 30);
		Font f = new Font("����", Font.PLAIN, 16);
		VcodeLabel.setFont(f);
		VcodeLabel.setText("��֤��");
		DownPanel.add(VcodeLabel);
		// ��֤��
		VcodeText = new JTextField("");
		VcodeText.setBounds(150, 234, 100, 26);
		VcodeText.setForeground(Color.GRAY);
		DownPanel.add(VcodeText);
		// ������֤�밴ť
		JButton SendVcode = new JButton();
		SendVcode.setBounds(260, 234, 105, 26);
		SendVcode.setBackground(Color.WHITE);
		f = new Font("����", Font.PLAIN, 14);
		SendVcode.setFont(f);
		SendVcode.setText("��ȡ��֤��");
		SendVcode.setFocusable(false);
		SendVcode.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				SendVcode.setEnabled(false);
				// ����ʱ�߳�
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
								SendVcode.setText("��ȡ��֤��");
								SendVcode.setEnabled(true);
								break;
							}
							SendVcode.setText(60 - (int) LastTime + "��");
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
				// �����ʼ��߳�
				Thread SendMail = new Thread(new Runnable()
				{
					public void run()
					{
						SendMail s = new SendMail();
						try
						{
							s.send("qq.com", "806675223@qq.com", "jznpdachbvzzbdic", MailText.getText(), "���̼�ע����֤��",
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
		// �����ǩ
		VcodeError = new JLabel();
		VcodeError.setBounds(380, 234, 200, 30);
		f = new Font("����", Font.PLAIN, 13);
		VcodeError.setForeground(Color.RED);
		VcodeError.setFont(f);
		VcodeError.setText("");
		DownPanel.add(VcodeError);
	}

	private void AddAgree()
	{
		// ͬ�����ѡ��
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
		// ͬ�������ǩ
		JLabel AgreeLabel = new JLabel();
		AgreeLabel.setBounds(170, 280, 200, 16);
		Font f = new Font("����", Font.PLAIN, 14);
		AgreeLabel.setFont(f);
		AgreeLabel.setText("�Ķ������ܡ����̼��û�Э�顷");
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
		// ע�ᰴť
		Signup = new JButton("ע��");
		Font f = new Font("����", Font.PLAIN, 18);
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
			// �������
			public void mouseEntered(MouseEvent e)
			{
				super.mouseEntered(e);
				if (Agree.isSelected())
					Signup.setBackground(blue2);
			}

			// ����Ƴ�
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
				// ����ǳ�
				if (NicknameText.getText().isEmpty() || "2~8�����֡���ĸ������".equals(NicknameText.getText()))
				{
					NicknameError.setText("����д�ǳ�");
					return;
				}
				if (CheckNickname(NicknameText.getText()) == false)
				{
					NicknameError.setText("�ǳƸ�ʽ����ȷ");
					return;
				} else
				{
					NicknameError.setText("");
				}
				// �������
				if (MailText.getText().isEmpty() || "����������".equals(MailText.getText()))
				{
					MailError.setText("����������");
					return;
				}
				if (CheckMail(MailText.getText()) == false)
				{
					MailError.setText("�����ʽ����ȷ");
					return;
				} else
				{
					MailError.setText("");
				}
				// �������
				if (PasswordText.getText().isEmpty() || "8~20����ĸ���������".equals(PasswordText.getText()))
				{
					PasswordError.setText("����������");
					return;
				}
				if (CheckPassword(PasswordText.getText()) == false)
				{
					PasswordError.setText("�����ʽ����ȷ");
					return;
				} else
				{
					PasswordError.setText("");
				}
				// ���ڶ������������
				if (PasswordText2.getText().isEmpty())
				{
					Password2Error.setText("���ٴ���������");
					return;
				}
				if (CheckPassword2(PasswordText.getText(),PasswordText2.getText()) == false)
				{
					System.out.println(PasswordText.getText() + " " + PasswordText2.getText());
					Password2Error.setText("�������벻һ��");
					return;
				} else
				{
					Password2Error.setText("");
				}
				//�����֤��
				if (VcodeText.getText().isEmpty())
				{
					VcodeError.setText("��������֤��");
					return;
				}
				if (CheckVcode() == false)
				{
					VcodeError.setText("��֤�����");
					return;
				} else
				{
					VcodeError.setText("");
				}
				//����ע������
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
		return String.valueOf(s + "\r\n�������������֤�����ע��");
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
