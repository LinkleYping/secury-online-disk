import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Login extends JFrame implements KeyListener
{
	Container container = null;
	JPanel UpPanel = null;
	JPanel DownPanel = null;
	JPanel RightPanel = null;
	JTextField UsernameText = null;
	JTextField PasswordText = null;
	JTextField PasswordTipText = null;
	JLabel Error = null;
	JCheckBox RemPassword = null;
	JCheckBox AutoLogin = null;
	JButton LoginButton = null;
	Signup signup = null;
	String Key = "2fh;mvok3;faojdjojf3n2";
	public Point origin = new Point();

	public Login()
	{
		Init();
		MakeUpPanel();
		MakeDownPanel();
		MakeRightPanel();
		this.setVisible(true);
	}
	
	public void Init()
	{
		this.setBounds(330, 150, 680, 460);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setUndecorated(true);
		container = this.getContentPane();
		container.setLayout(null);
		UpPanel = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(Color.BLACK);
				g.drawLine(0, 0, 680, 0);
				g.drawLine(0, 0, 0, 88);
				g.drawLine(678, 0, 678, 88);
			}
		};
		UpPanel.setBounds(0, 0, 680, 88);
		UpPanel.setLayout(null);
		UpPanel.setBackground(new Color(10, 146, 238));
		container.add(UpPanel);

		DownPanel = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(Color.BLACK);
				g.drawLine(0, 0, 0, 392);
				g.drawLine(0, 370, 680, 370);
				g.drawLine(678, 0, 678, 392);
			}
		};
		DownPanel.setBounds(0, 88, 680, 392);
		DownPanel.setLayout(null);
		DownPanel.setBackground(new Color(243, 251, 255));
		container.add(DownPanel);

		RightPanel = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(new Color(138, 209, 244));
				g.drawRect(0, 0, 310, 340);
			}
		};
		RightPanel.setBounds(350, 10, 320, 360);
		RightPanel.setLayout(null);
		RightPanel.setBackground(new Color(246, 252, 255));
		DownPanel.add(RightPanel);
	}

	public void MakeUpPanel()
	{
		// ���Ͻ�ͼ��
		ImageIcon ico = new ImageIcon("���Ͻ�(��¼����).jpg");
		Image temp = ico.getImage().getScaledInstance(80, 60, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image = new JLabel(ico);
		Image.setBounds(13, 10, 80, 60);
		UpPanel.add(Image);
		// ����
		JLabel Title = new JLabel();
		Font f = new Font("����", Font.BOLD, 27);
		Title.setForeground(new Color(238, 245, 255));
		Title.setFont(f);
		Title.setText("���̼�");
		Title.setBounds(100, 16, 100, 50);
		UpPanel.add(Title);
		// ��С����ť
		JButton MinButton = new JButton();
		MinButton.setBounds(630, 8, 12, 12);
		MinButton.setLayout(null);
		MinButton.setBackground(new Color(14, 142, 231));
		MinButton.setBackground(Color.BLACK);
		MinButton.setBorderPainted(false);
		Login this_temp = this;
		MinButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				this_temp.setExtendedState(JFrame.ICONIFIED);
			}
		});

		ico = new ImageIcon("��С��.jpg");
		temp = ico.getImage().getScaledInstance(12, 12, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		Image = new JLabel(ico);
		Image.setBounds(0, 0, 12, 12);
		MinButton.add(Image);
		UpPanel.add(MinButton);
		// �˳���ť
		JButton ExitButton = new JButton();
		ExitButton.setBounds(652, 3, 20, 20);
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

		ico = new ImageIcon("�˳�.jpg");
		temp = ico.getImage().getScaledInstance(12, 12, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		Image = new JLabel(ico);
		Image.setBounds(5, 5, 12, 12);
		ExitButton.add(Image);
		UpPanel.add(ExitButton);
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

	public void MakeDownPanel()
	{
		// ���Ͻ�ͼ��
		ImageIcon ico = new ImageIcon("��¼˵��.jpg");
		Image temp = ico.getImage().getScaledInstance(329, 360, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image = new JLabel(ico);
		Image.setBounds(1, 0, 329, 360);
		DownPanel.add(Image);
	}

	private void AddGirlImage()
	{
		// ���̼�ͷ��
		ImageIcon ico = new ImageIcon("���̼�.jpg");
		Image temp = ico.getImage().getScaledInstance(30, 32, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image = new JLabel(ico);
		Image.setBounds(70, 30, 30, 32);
		RightPanel.add(Image);
	}

	private void AddTitle()
	{
		// ����
		JLabel Title = new JLabel();
		Title.setBounds(110, 30, 120, 30);
		Title.setForeground(new Color(102, 102, 102));
		Font f = new Font("����", Font.BOLD, 20);
		Title.setFont(f);
		Title.setText("��¼���̼�");
		RightPanel.add(Title);
	}

	private void AddErrorLabel()
	{
		// �����ǩ
		Error = new JLabel();
		Error.setBounds(40, 62, 200, 30);
		Error.setForeground(new Color(252, 109, 123));
		Font f = new Font("����", Font.PLAIN, 13);
		Error.setFont(f);
		Error.setText("");
		RightPanel.add(Error);
	}

	private void AddUsername()
	{
		// �û���
		UsernameText = new JTextField("�������û���");
		UsernameText.setBounds(40, 95, 240, 27);
		UsernameText.setForeground(Color.GRAY);
		UsernameText.addKeyListener(this);
		RightPanel.add(UsernameText);
		UsernameText.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				if (UsernameText.getText().equals("�������û���"))
				{
					UsernameText.setText("");
					UsernameText.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e)
			{
				if (UsernameText.getText().equals(""))
				{
					UsernameText.setForeground(Color.GRAY);
					UsernameText.setText("�������û���");
				}
			}
		});
		// �û���ǰ���ͼƬ
		ImageIcon ico = new ImageIcon("�û���.jpg");
		Image temp = ico.getImage().getScaledInstance(20, 22, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image = new JLabel(ico);
		Image.setBounds(17, 95, 20, 22);
		RightPanel.add(Image);
	}

	private void AddPassword()
	{
		// ����
		PasswordText = new JPasswordField();
		PasswordText.setBounds(40, 130, 240, 27);
		PasswordText.setText("");
		PasswordText.setForeground(Color.GRAY);
		PasswordText.setVisible(false);
		PasswordText.addKeyListener(this);
		RightPanel.add(PasswordText);

		PasswordTipText = new JTextField();
		PasswordTipText.setBounds(40, 130, 240, 27);
		PasswordTipText.setText("����������");
		PasswordTipText.setForeground(Color.GRAY);
		RightPanel.add(PasswordTipText);
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
				if (PasswordText.getText().equals("����������"))
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
					PasswordTipText.setText("����������");
					PasswordTipText.setVisible(true);
					PasswordText.setVisible(false);
				}
			}
		});
		// ����ǰ���ͼƬ
		ImageIcon ico = new ImageIcon("����.jpg");
		Image temp = ico.getImage().getScaledInstance(20, 22, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image = new JLabel(ico);
		Image.setBounds(16, 130, 20, 22);
		RightPanel.add(Image);
	}

	private void AddRemPassword()
	{
		// ��ס����ǰ��ĸ�ѡ��
		RemPassword = new JCheckBox();
		RemPassword.setBounds(36, 167, 20, 20);
		RemPassword.setBackground(new Color(246, 252, 255));
		RemPassword.addKeyListener(this);
		RemPassword.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						if(!RemPassword.isSelected())
							AutoLogin.setSelected(false);
					}
				});
		RightPanel.add(RemPassword);
		// ��ס�����ǩ
		JLabel RemPassLabel = new JLabel();
		RemPassLabel.setBounds(56, 162, 56, 30);
		RemPassLabel.setForeground(new Color(102, 102, 102));
		Font f = new Font("����", Font.PLAIN, 13);
		RemPassLabel.setFont(f);
		RemPassLabel.setText("��ס����");
		RemPassLabel.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				RemPassword.setSelected(!RemPassword.isSelected());
				if(!RemPassword.isSelected())
					AutoLogin.setSelected(false);
			}
		});
		RightPanel.add(RemPassLabel);
		//����д洢�����룬����ʹ�ô洢��
		try
		{
			File file = new File("Password.txt");
			FileReader filereader = new FileReader(file);
			BufferedReader br = new BufferedReader(filereader);
			String Username = null;
			Username = br.readLine();
			if(Username == null || Username.isEmpty())
			{
				br.close();
				return;
			}
			String Password = AES.decrypt(br.readLine(), Key);
			br.close();
			UsernameText.setText(Username);
			PasswordText.setText(Password);
			PasswordTipText.setVisible(false);
			RemPassword.setSelected(true);
			PasswordText.setVisible(true);
		} catch (Exception e1)
		{
		}
	}

	private void AddAuto()
	{
		// �Զ���¼ǰ��ĸ�ѡ��
		AutoLogin = new JCheckBox();
		AutoLogin.setBounds(150, 167, 20, 20);
		AutoLogin.setBackground(new Color(246, 252, 255));
		AutoLogin.addKeyListener(this);
		AutoLogin.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						if(AutoLogin.isSelected())
							RemPassword.setSelected(true);
					}
				});
		RightPanel.add(AutoLogin);
		// �Զ���¼��ǩ
		JLabel AutoLabel = new JLabel();
		AutoLabel.setBounds(170, 162, 56, 30);
		AutoLabel.setForeground(new Color(102, 102, 102));
		Font f = new Font("����", Font.PLAIN, 13);
		AutoLabel.setFont(f);
		AutoLabel.setText("�Զ���¼");
		AutoLabel.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				AutoLogin.setSelected(!AutoLogin.isSelected());
				if(AutoLogin.isSelected())
					RemPassword.setSelected(true);
			}
		});
		RightPanel.add(AutoLabel);
		AutoLogin.addChangeListener(new ChangeListener()
				{
					public void stateChanged(ChangeEvent arg0)
					{
						if((!AutoLogin.isSelected()) && Error.getText().equals("�����Զ���¼..."))
							Error.setText("");
					}
				});
	}

	private void AddLoginButton()
	{
		// ��¼��ť
		ImageIcon ico = new ImageIcon("��¼.jpg");
		Image temp = ico.getImage().getScaledInstance(240, 34, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image1 = new JLabel(ico);
		Image1.setBounds(0, 0, 240, 34);

		ico = new ImageIcon("��¼2.jpg");
		temp = ico.getImage().getScaledInstance(240, 34, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image2 = new JLabel(ico);
		Image2.setBounds(0, 0, 240, 34);

		ico = new ImageIcon("��¼3.jpg");
		temp = ico.getImage().getScaledInstance(240, 34, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image3 = new JLabel(ico);
		Image3.setBounds(0, 0, 240, 34);
		LoginButton = new JButton();
		LoginButton.setEnabled(false);
		LoginButton.setBackground(new Color(100, 186, 243));
		LoginButton.setBounds(40, 195, 240, 34);
		LoginButton.setBorder(null);
		LoginButton.requestFocusInWindow();
		LoginButton.add(Image1);
		Login this_temp = this;
		LoginButton.addKeyListener(this);
		LoginButton.addMouseListener(new MouseAdapter()
		{
			// �������
			public void mouseEntered(MouseEvent e)
			{
				super.mouseEntered(e);
				LoginButton.removeAll();
				LoginButton.add(Image2);
			}

			// ����Ƴ�
			public void mouseExited(MouseEvent e)
			{
				super.mouseExited(e);
				LoginButton.removeAll();
				LoginButton.add(Image1);
			}

			public void mousePressed(MouseEvent e)
			{
				LoginButton.removeAll();
				LoginButton.add(Image3);
			}

			public void mouseReleased(MouseEvent e)
			{
				LoginButton.removeAll();
				LoginButton.add(Image2);
			}

			public void mouseClicked(MouseEvent e)
			{
				if(LoginButton.isEnabled() == false)
					return;
				if(UsernameText.getText().isEmpty())//û�����û���
					Error.setText("�������û���");
				if(PasswordText.getText().isEmpty())//û��������
					Error.setText("����������");
				HashMap<String,String> map = new HashMap<>();
				map.put("Mark", "Login");
				map.put("Username", UsernameText.getText());
				map.put("Password", PasswordText.getText());
				Send.send(MapString.MapToString(map));
				//���ѡ�˼�ס���룬�����뱣�浽����
				if(RemPassword.isSelected())
				{
					File file = new File("Password.txt");
					try
					{
						FileWriter filewriter = new FileWriter(file); 
						BufferedWriter bw = new BufferedWriter(filewriter);
						bw.write(UsernameText.getText() + "\r\n");
						bw.write(AES.encrypt(PasswordText.getText(), Key));
						bw.close();
						filewriter.close();
					} catch (Exception e1)
					{
						e1.printStackTrace();
					}
				}
				else
				{
					File file = new File("Password.txt");
					file.delete();
				}
				//�Ƿ��Զ���¼����¼���ļ���
				File file = new File("AutoLogin.txt");
				if(AutoLogin.isSelected())
				{
					try
					{
						file.createNewFile();
					} catch (IOException e1)
					{
					}
				}
				else
				{
					file.delete();
				}
			}
		});
		RightPanel.add(LoginButton);
	}

	private void Signup()
	{
		// û���˺ű�ǩ
		JLabel NoAccount = new JLabel();
		NoAccount.setBounds(30, 300, 100, 30);
		NoAccount.setForeground(Color.GRAY);
		Font f = new Font("����", Font.PLAIN, 13);
		NoAccount.setFont(f);
		NoAccount.setText("û�����̼��˺�?");
		RightPanel.add(NoAccount);
		// ע�ᰴť
		JButton SignupButton = new JButton();
		SignupButton = new JButton();
		SignupButton.setBackground(new Color(236, 247, 255));
		SignupButton.setBounds(150, 306, 110, 24);
		SignupButton.setBorder(BorderFactory.createLineBorder(new Color(82, 164, 236)));
		SignupButton.setForeground(new Color(27, 113, 243));
		f = new Font("����", Font.PLAIN, 13);
		SignupButton.setFont(f);
		SignupButton.setText("����ע��");
		SignupButton.setFocusable(false);
		SignupButton.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent e)
			{
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e)
			{
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mouseClicked(MouseEvent e)
			{
				signup = new Signup();
			}
		});
		RightPanel.add(SignupButton);
	}

	public void MakeRightPanel()
	{
		AddGirlImage();
		AddTitle();
		AddErrorLabel();
		AddUsername();
		AddPassword();
		AddRemPassword();
		AddAuto();
		AddLoginButton();
		Signup();
	}

	public void AutoLogin()
	{
		
		File file1 = new File("AutoLogin.txt");
		File file2 = new File("Password.txt");
		if(file1.exists())
			AutoLogin.setSelected(true);
		else
			AutoLogin.setSelected(false);
		
		if(file1.exists() && file2.exists() && AutoLogin.isSelected())
		{
			Error.setText("�����Զ���¼...");
			try
			{
				Thread.sleep(2000);
			} catch (InterruptedException e)
			{
			}
			if(!AutoLogin.isSelected())
				return;
			if(LoginButton.isEnabled() == false)
				return;
			if(UsernameText.getText().isEmpty())//û�����û���
				Error.setText("�������û���");
			if(PasswordText.getText().isEmpty())//û��������
				Error.setText("����������");
			HashMap<String,String> map = new HashMap<>();
			map.put("Mark", "Login");
			map.put("Username", UsernameText.getText());
			map.put("Password", PasswordText.getText());
			Send.send(MapString.MapToString(map));
			
			//���ѡ�˼�ס���룬�����뱣�浽����
			if(RemPassword.isSelected())
			{
				try
				{
					FileWriter filewriter = new FileWriter(file2); 
					BufferedWriter bw = new BufferedWriter(filewriter);
					bw.write(UsernameText.getText() + "\r\n");
					bw.write(AES.encrypt(PasswordText.getText(), Key));
					bw.close();
					filewriter.close();
				} catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
			else
			{
				file2.delete();
			}
			//�Ƿ��Զ���¼����¼���ļ���
			if(AutoLogin.isSelected())
			{
				try
				{
					file1.createNewFile();
				} catch (IOException e1)
				{
				}
			}
			else
			{
				file1.delete();
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		
		
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		if(e.getKeyChar() == '\n')
		{
			if(UsernameText.getText().isEmpty())//û�����û���
				Error.setText("�������û���");
			if(PasswordText.getText().isEmpty())//û��������
				Error.setText("����������");
			HashMap<String,String> map = new HashMap<>();
			map.put("Mark", "Login");
			map.put("Username", UsernameText.getText());
			map.put("Password", PasswordText.getText());
			Send.send(MapString.MapToString(map));
			
			//���ѡ�˼�ס���룬�����뱣�浽����
			if(RemPassword.isSelected())
			{
				File file = new File("Password.txt");
				try
				{
					FileWriter filewriter = new FileWriter(file); 
					BufferedWriter bw = new BufferedWriter(filewriter);
					bw.write(UsernameText.getText() + "\r\n");
					bw.write(AES.encrypt(PasswordText.getText(), Key));
					bw.close();
					filewriter.close();
				} catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
			else
			{
				File file = new File("Password.txt");
				file.delete();
			}
			//�Ƿ��Զ���¼����¼���ļ���
			File file = new File("AutoLogin.txt");
			if(AutoLogin.isSelected())
			{
				try
				{
					file.createNewFile();
				} catch (IOException e1)
				{
				}
			}
			else
			{
				file.delete();
			}
		}
	}
	
	
}
