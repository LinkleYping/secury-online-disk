import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class MainWindow extends JFrame
{
	public Container container = null;
	public JPanel UpPanel = null;
	public JPanel RoutePane = null;
	public JPanel TableHeadPane = null;
	public JPanel TablePane = null;
	public JPanel DownPane = null;
	public JPanel TranListPane = null;
	public JPanel DownloadPane = null;
	public JPanel UploadPane = null;
	public JPanel DonePane = null;
	public JTabbedPane TabbedPane = null;
	public JScrollPane ScrollPane = null;
	public JLabel Capacity = null;
	public JTextField RouteText = null;
	public JTextField SearchText = null;
	public JLabel Nickname = null;
	public JButton BackButton = null;
	public JPanel MyDisk = null;
	public JPanel Upload = null;
	public JPanel Download = null;
	public JPanel Share = null;
	public JPanel Delete = null;
	public JPanel NewFolder = null;
	public JCheckBox AllSelectBox = null;
	public JButton FilenameButton = null;
	public JButton FileSizeButton = null;
	public JButton FileModTimeButton = null;
	public JButton TranList = null;
	public ArrayList<FileView> FileList = new ArrayList<>();
	public ArrayList<UploadView> UploadList = new ArrayList<>();
	public ArrayList<DownloadView> DownloadList = new ArrayList<>();
	public ArrayList<DoneView> DoneList = new ArrayList<>();
	public Point origin = new Point();
	public String DefaultDownloadRoute = "g:\\OnlineDiskLadyDownload";
	public boolean IsTranListOpened = false;
	private int CapacityPercent = 0;
	private String Username = null;
	private String Route = "";
	private int UploadNum = 0;
	private int DownloadNum = 0;
	private int DoneNum = 0;

	public static MainWindow mainwindow = null;

	public MainWindow(String Username)// ������
	{
		Init();
		AddTitle();
		AddTable();
		AddRouteText();
		AddSearchText();
		AddNickname();
		AddBackButton();
		AddMyDisk();
		AddUpload();
		AddDownload();
		AddShare();
		AddDelete();
		AddNewFolder();
		AddTranList();
		AddTabbedPane();
		this.Username = Username;
		// ��ʾ
		this.setVisible(true);
		mainwindow = this;
	}

	private void Init()
	{
		// ��ʼ��������
		this.setBounds(250, 80, 940, 553);// ��Сλ��
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);// �ر�����
		this.setResizable(false);// ���ɸı��С
		this.setTitle("�ٶ�����");
		this.setUndecorated(true);
		container = this.getContentPane();// ����
		container.setLayout(null);
		container.setBackground(Color.WHITE);
		UpPanel = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(Color.BLACK);
				g.drawLine(0, 0, 940, 0);
				g.drawLine(0, 0, 0, 105);
				g.drawLine(938, 0, 938, 105);
				// ����
				g.setColor(new Color(34, 89, 172));
				g.fillRect(15, 70, 120, 20);
				g.setColor(Color.GREEN);
				g.fillRect(16, 71, (int) (CapacityPercent * 1.18), 18);
			}
		};
		UpPanel.setLayout(null);
		UpPanel.setBounds(0, 0, 940, 105);
		UpPanel.setBackground(new Color(14, 142, 231));
		UpPanel.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		MainWindow this_temp = this;
		UpPanel.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{
				Point p = this_temp.getLocation();
				this_temp.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
			}
		});

		container.add(UpPanel);

		RoutePane = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(Color.GRAY);
				g.drawLine(0, 0, 0, 45);
				g.drawLine(938, 0, 938, 45);
			}
		};
		RoutePane.setBounds(0, 105, 940, 45);
		RoutePane.setLayout(null);
		RoutePane.setBackground(Color.WHITE);
		container.add(RoutePane);

		DownPane = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(Color.GRAY);
				g.drawLine(0, 0, 0, 45);
				g.drawLine(938, 0, 938, 45);
			}
		};
		DownPane.setBounds(0, 552, 940, 1);
		DownPane.setLayout(null);
		DownPane.setBackground(Color.BLACK);
		container.add(DownPane);

		TabbedPane = new JTabbedPane(JTabbedPane.TOP)
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(Color.GRAY);
				g.drawLine(0, 0, 0, 940);
				g.drawLine(0, 0, 0, 10000);
				g.drawLine(938, 0, 938, 10000);
			}
		};
		TabbedPane.setBounds(0, 105, 943, 448);
		TabbedPane.setBackground(Color.WHITE);
		TabbedPane.setVisible(false);
		container.add(TabbedPane);
	}

	private void AddTitle()
	{
		// ���Ͻ�ͼ��
		ImageIcon ico = new ImageIcon("���Ͻ�.jpg");
		Image temp = ico.getImage().getScaledInstance(25, 25, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image = new JLabel(ico);
		Image.setBounds(13, 4, 25, 25);
		UpPanel.add(Image);
		// ����
		JLabel Title = new JLabel("���̼�");
		Font f = new Font("΢���ź�", Font.BOLD, 13);
		Title.setFont(f);
		Title.setBounds(40, 4, 50, 25);
		Title.setForeground(Color.WHITE);
		UpPanel.add(Title);
		// ��������
		Capacity = new JLabel();
		Capacity.setBounds(20, 73, 150, 20);
		UpPanel.add(Capacity);
		// ��С����ť
		JButton MinButton = new JButton();
		MinButton.setBounds(893, 8, 12, 12);
		MinButton.setLayout(null);
		MinButton.setBackground(new Color(14, 142, 231));
		MinButton.setBorderPainted(false);
		MainWindow this_temp = this;
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
		ExitButton.setBounds(920, 6, 12, 12);
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
		Image.setBounds(0, 0, 12, 12);
		ExitButton.add(Image);
		UpPanel.add(ExitButton);
	}

	private void AddTable()
	{
		// ��ӱ�ͷ
		TableHeadPane = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(Color.GRAY);
				g.drawLine(368, 4, 368, 36);
				g.drawLine(450, 4, 450, 36);
				g.drawLine(628, 4, 628, 36);
				g.drawLine(710, 4, 710, 36);
				g.drawLine(0, 4, 1000, 4);
				g.drawLine(0, 36, 1000, 36);
				g.drawLine(0, 0, 0, 40);
				g.drawLine(938, 0, 938, 40);
			}
		};
		TableHeadPane.setBounds(0, 150, 1000, 40);
		TableHeadPane.setLayout(null);
		TableHeadPane.setBackground(Color.WHITE);
		// ���ȫѡ��
		AllSelectBox = new JCheckBox();
		AllSelectBox.setBounds(38, 11, 17, 17);
		AllSelectBox.setBackground(Color.WHITE);
		AllSelectBox.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0)
			{
				// �ж��Ƿ�ȫѡ
				boolean IsAllSelected = true;
				for (int i = 0; i < FileList.size(); i++)
				{
					if (FileList.get(i).checkbox.isSelected() == false)
					{
						IsAllSelected = false;
						break;
					}
				}
				if (IsAllSelected == true)// ���ȫѡ�����ó�ȫ��ѡ״̬
				{
					for (int i = 0; i < FileList.size(); i++)
					{
						FileList.get(i).checkbox.setSelected(false);
					}
				} else// �����ûѡ�ģ����ó�ȫѡ
				{
					for (int i = 0; i < FileList.size(); i++)
					{
						FileList.get(i).checkbox.setSelected(true);
					}
				}
			}
		});
		TableHeadPane.add(AllSelectBox);
		// ��ӡ��ļ�������ť
		FilenameButton = new JButton("�ļ���");
		Font f = new Font("΢���ź�", Font.PLAIN, 12);
		FilenameButton.setFont(f);
		FilenameButton.setBounds(55, 5, 80, 30);
		FilenameButton.setForeground(Color.GRAY);
		FilenameButton.setBackground(Color.WHITE);
		FilenameButton.setBorder(null);
		FilenameButton.setFocusPainted(false);
		TableHeadPane.add(FilenameButton);
		// ��ӡ���С����ť
		FileSizeButton = new JButton("��С");
		FileSizeButton.setFont(f);
		FileSizeButton.setBounds(370, 5, 80, 30);
		FileSizeButton.setForeground(Color.GRAY);
		FileSizeButton.setBackground(Color.WHITE);
		FileSizeButton.setBorder(null);
		FileSizeButton.setFocusPainted(false);
		TableHeadPane.add(FileSizeButton);
		// ��ӡ��޸�ʱ�䡱��ť
		FileModTimeButton = new JButton("�޸�ʱ��");
		FileModTimeButton.setFont(f);
		FileModTimeButton.setBounds(630, 5, 80, 30);
		FileModTimeButton.setForeground(Color.GRAY);
		FileModTimeButton.setBackground(Color.WHITE);
		FileModTimeButton.setBorder(null);
		FileModTimeButton.setFocusPainted(false);
		TableHeadPane.add(FileModTimeButton);
		// �������ݱ��
		TablePane = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(Color.GRAY);
				g.drawLine(0, 0, 0, 10000);
				g.drawLine(938, 0, 938, 10000);
			}
		};
		;
		TablePane.setBounds(0, 0, 100, 100);
		TablePane.setPreferredSize(new Dimension(100, 100));
		TablePane.setBackground(Color.WHITE);
		TablePane.setLayout(null);
		ScrollPane = new JScrollPane(TablePane);
		ScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		ScrollPane.setBorder(null);
		ScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		ScrollPane.setBounds(0, 190, 940, 360);
		container.add(ScrollPane);
		container.add(TableHeadPane);
	}

	private void AddRouteText()
	{
		// ���·����ʾ��
		RouteText = new JTextField();
		RouteText.setBounds(100, 10, 530, 30);
		RouteText.setEditable(false);
		RouteText.setBackground(Color.WHITE);
		RouteText.setText("�ҵ����� �� ");
		RoutePane.add(RouteText);
	}

	private void AddSearchText()
	{
		// ���������
		SearchText = new JTextField();
		SearchText.setBounds(650, 10, 200, 30);
		RoutePane.add(SearchText);
		SearchText.setText("�����ҵ������ļ�");
		SearchText.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				SearchText.setText("");
			}

			@Override
			public void focusLost(FocusEvent e)
			{
				SearchText.setText("�����ҵ������ļ�");
			}
		});
	}

	private void AddNickname()
	{
		// ����ǳ�
		Nickname = new JLabel("", JLabel.CENTER);
		Font f = new Font("΢���ź�", Font.BOLD, 18);
		Nickname.setFont(f);
		Nickname.setBounds(0, 30, 150, 40);
		Nickname.setBackground(new Color(14, 142, 231));
		Nickname.setForeground(Color.WHITE);
		UpPanel.add(Nickname);
		Nickname.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				NewNickname();
			}
			public void mouseEntered(MouseEvent e)
			{
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e)
			{
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e)
			{
				Nickname.setLocation(2, 32);
			}
			public void mouseReleased(MouseEvent e)
			{
				Nickname.setLocation(0, 30);
			}
		});
	}

	private void AddBackButton()
	{
		// ���˰�ť
		ImageIcon ico = new ImageIcon("����.jpg");
		Image temp = ico.getImage().getScaledInstance(50, 24, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image = new JLabel(ico);
		Image.setBounds(0, 0, 60, 24);
		Image.setFocusable(false);

		BackButton = new JButton();
		BackButton.add(Image);
		BackButton.setBounds(10, 10, 60, 24);
		BackButton.setBackground(Color.WHITE);
		BackButton.setBorderPainted(false);
		BackButton.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (Route.isEmpty() || Route == null)
					return;
				int i;
				for (i = Route.length() - 1; i >= 0; i--)
				{
					if (Route.charAt(i) == '\\')
						break;
				}
				Route = Route.substring(0, i);
				new Thread(new AskFileList(Username, Route)).start();
				String Route2 = Route.replace("\\", " �� ");
				RouteText.setText("�ҵ�����" + Route2);
			}
		});
		RoutePane.add(BackButton);
	}

	private void AddMyDisk()
	{
		MyDisk = new JPanel();
		MyDisk.setLayout(null);
		MyDisk.setBackground(new Color(14, 142, 231));

		
		ImageIcon ico = new ImageIcon("�ҵ�����.jpg");
		Image temp = ico.getImage().getScaledInstance(50, 50, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image = new JLabel(ico);
		Image.setBounds(17, 0, 50, 50);
		Image.setFocusable(false);

		MyDisk.add(Image);

		JLabel Label = new JLabel("�ҵ�����");
		Font f = new Font("΢���ź�", Font.BOLD, 13);
		Label.setFont(f);
		Label.setBounds(18, 53, 60, 20);
		Label.setForeground(Color.WHITE);
		MyDisk.add(Label);
		MyDisk.setBounds(150, 21, 90, 85);
		UpPanel.add(MyDisk);

		MyDisk.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent e)
			{
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e)
			{
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e)
			{
				MyDisk.setLocation(152, 23);
			}
			public void mouseReleased(MouseEvent e)
			{
				MyDisk.setLocation(150, 21);
			}
			
			public void mouseClicked(MouseEvent e)
			{
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				Route = "";
				new Thread(new AskFileList(Username, Route)).start();
				RouteText.setText("�ҵ����� �� ");

				RoutePane.setVisible(true);
				ScrollPane.setVisible(true);
				TableHeadPane.setVisible(true);
				TablePane.setVisible(true);
				TranList.removeAll();

				ImageIcon ico = new ImageIcon("�����б�.jpg");
				Image temp = ico.getImage().getScaledInstance(75, 30, ico.getImage().SCALE_DEFAULT);
				ico = new ImageIcon(temp);
				JLabel Image = new JLabel(ico);
				Image.setBounds(0, 0, 75, 30);

				TranList.setBounds(862, 76, 75, 30);
				TranList.add(Image);
				TabbedPane.setVisible(false);
				IsTranListOpened = false;
			}
		});
	}

	private void AddUpload()
	{
		Upload = new JPanel();
		Upload.setLayout(null);
		Upload.setForeground(Color.WHITE);
		Upload.setBackground(new Color(14, 142, 231));

		ImageIcon ico = new ImageIcon("�ϴ�.jpg");
		Image temp = ico.getImage().getScaledInstance(50, 50, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image = new JLabel(ico);
		Image.setBounds(7, 0, 50, 50);
		Upload.add(Image);

		JLabel Label = new JLabel("�ϴ�");
		Font f = new Font("΢���ź�", Font.BOLD, 13);
		Label.setFont(f);
		Label.setBounds(18, 53, 60, 20);
		Label.setForeground(Color.WHITE);
		Upload.add(Label);
		Upload.setBounds(250, 21, 90, 85);

		Upload.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent e)
			{
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e)
			{
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e)
			{
				Upload.setLocation(252, 23);
			}
			public void mouseReleased(MouseEvent e)
			{
				Upload.setLocation(250, 21);
			}
			
			public void mouseClicked(MouseEvent arg0)
			{
				String[] FileRoute = SelectFile();
				if(FileRoute == null || FileRoute.length == 0)
					return;
				for (String s : FileRoute)
				{
					if (s == null || s.isEmpty())
						return;
					String RemoteRoute = Route + "\\" + new File(s).getName();
					new Thread(new Upload(Username, s, RemoteRoute)).start();
					try
					{
						Thread.sleep(100);
					} catch (InterruptedException e)
					{
					}
				}
			}
		});
		UpPanel.add(Upload);
	}

	private void AddDownload()
	{
		Download = new JPanel();
		Download.setLayout(null);
		Download.setForeground(Color.WHITE);
		Download.setBackground(new Color(14, 142, 231));

		ImageIcon ico = new ImageIcon("����.jpg");
		Image temp = ico.getImage().getScaledInstance(50, 50, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image = new JLabel(ico);
		Image.setBounds(7, 0, 50, 50);
		Download.add(Image);

		JLabel Label = new JLabel("����");
		Font f = new Font("΢���ź�", Font.BOLD, 13);
		Label.setFont(f);
		Label.setBounds(18, 53, 60, 20);
		Label.setForeground(Color.WHITE);
		Download.add(Label);
		Download.setBounds(350, 21, 90, 85);
		UpPanel.add(Download);
		Download.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent e)
			{
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e)
			{
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e)
			{
				Download.setLocation(352, 23);
			}
			public void mouseReleased(MouseEvent e)
			{
				Download.setLocation(350, 21);
			}
			
			public void mouseClicked(MouseEvent e)
			{
				ArrayList<FileView> selectfile = GetSelectFile();// ��ȡѡ����ļ��б�
				if (selectfile.isEmpty())
				{
					return;
				}
				for (int i = 0; i < selectfile.size(); i++)
				{
					new Thread(new Download(Username, selectfile.get(i).Filename.getText(),
							selectfile.get(i).FileSize.getText(), Route)).start();
				}
			}
		});
	}

	private void AddShare()
	{
		Share = new JPanel();
		Share.setLayout(null);
		Share.setForeground(Color.WHITE);
		Share.setBackground(new Color(14, 142, 231));

		ImageIcon ico = new ImageIcon("����.jpg");
		Image temp = ico.getImage().getScaledInstance(40, 40, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image = new JLabel(ico);
		Image.setBounds(7, 0, 50, 50);
		Share.add(Image);

		JLabel Label = new JLabel("����");
		Font f = new Font("΢���ź�", Font.BOLD, 13);
		Label.setFont(f);
		Label.setBounds(18, 53, 60, 20);
		Label.setForeground(Color.WHITE);
		Share.add(Label);
		Share.setBounds(450, 21, 90, 85);
		UpPanel.add(Share);
		Share.addMouseListener(new MouseAdapter()
				{
					public void mouseEntered(MouseEvent e)
					{
						setCursor(new Cursor(Cursor.HAND_CURSOR));
					}
					public void mouseExited(MouseEvent e)
					{
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
					public void mousePressed(MouseEvent e)
					{
						Share.setLocation(452, 23);
					}
					public void mouseReleased(MouseEvent e)
					{
						Share.setLocation(450, 21);
					}
					public void mouseClicked(MouseEvent e)
					{
						
					}
				
				});
	}

	private void AddDelete()
	{
		Delete = new JPanel();
		Delete.setLayout(null);
		Delete.setForeground(Color.WHITE);
		Delete.setBackground(new Color(14, 142, 231));

		ImageIcon ico = new ImageIcon("ɾ��.jpg");
		Image temp = ico.getImage().getScaledInstance(40, 40, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image = new JLabel(ico);
		Image.setBounds(7, 0, 50, 50);
		Delete.add(Image);

		JLabel Label = new JLabel("ɾ��");
		Font f = new Font("΢���ź�", Font.BOLD, 13);
		Label.setFont(f);
		Label.setBounds(18, 53, 60, 20);
		Label.setForeground(Color.WHITE);
		Delete.add(Label);
		Delete.setBounds(550, 21, 90, 85);
		Delete.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent e)
			{
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e)
			{
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e)
			{
				Delete.setLocation(552, 23);
			}
			public void mouseReleased(MouseEvent e)
			{
				Delete.setLocation(550, 21);
			}
			
			public void mouseClicked(MouseEvent e)
			{
				ArrayList<FileView> selectfile = GetSelectFile();
				for (int i = 0; i < selectfile.size(); i++)
				{
					if (i == selectfile.size() - 1)
						new Thread(new DeleteFile(Username, Route + "\\" + selectfile.get(i).Filename.getText(), true))
								.start();
					else
						new Thread(new DeleteFile(Username, Route + "\\" + selectfile.get(i).Filename.getText(), false))
								.start();
				}
				AllSelectBox.setSelected(false);
				try
				{
					Thread.sleep(1000);
					new Thread(new AskFileList(Username, Route)).start();
				} catch (InterruptedException e1)
				{
				}
			}
		});
		UpPanel.add(Delete);
	}

	private void AddNewFolder()
	{
		NewFolder = new JPanel();
		NewFolder.setLayout(null);
		NewFolder.setForeground(Color.WHITE);
		NewFolder.setBackground(new Color(14, 142, 231));

		ImageIcon ico = new ImageIcon("�½��ļ���.jpg");
		Image temp = ico.getImage().getScaledInstance(40, 40, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image = new JLabel(ico);
		Image.setBounds(7, 0, 50, 50);
		NewFolder.add(Image);

		JLabel Label = new JLabel("�½��ļ���");
		Font f = new Font("΢���ź�", Font.BOLD, 13);
		Label.setFont(f);
		Label.setBounds(0, 53, 80, 20);
		Label.setForeground(Color.WHITE);
		NewFolder.add(Label);
		NewFolder.setBounds(650, 21, 90, 85);
		UpPanel.add(NewFolder);
		NewFolder.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent e)
			{
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e)
			{
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e)
			{
				NewFolder.setLocation(652, 23);
			}
			public void mouseReleased(MouseEvent e)
			{
				NewFolder.setLocation(650, 21);
			}
			
			public void mouseClicked(MouseEvent arg0)
			{
				NewFolder();
			}
		});
	}

	private void NewFolder()
	{
		// �����ƶ���
		Point origin = new Point();
		// �����Ի���
		JDialog Dialog = new JDialog();
		Dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Container container = Dialog.getContentPane();
		container.setLayout(null);
		Dialog.setBounds(600, 250, 210, 110);
		Dialog.setUndecorated(true);
		// �������
		JPanel Panel = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(Color.GRAY);
				g.drawRect(0, 0, 209, 109);
			}
		};
		Panel.setBounds(0, 0, 210, 110);
		Panel.setLayout(null);
		container.add(Panel);
		Panel.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		Panel.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{
				Point p = Dialog.getLocation();
				Dialog.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
			}
		});

		Panel.setBackground(Color.WHITE);

		// �ļ�������ʾ��ǩ
		JLabel tip = new JLabel("�ļ�����");
		tip.setBounds(20, 0, 200, 30);
		Panel.add(tip);
		// �ļ����������
		JTextField Foldername = new JTextField("�½��ļ���");
		Foldername.setBounds(20, 30, 170, 27);
		Panel.add(Foldername);
		Foldername.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent arg0)
			{
				if (arg0.getKeyChar() == '\n')
				{
					if (CheckFoldername(Foldername.getText()) == true)
					{
						new Thread(new NewFolder(Foldername.getText(), Username, Route)).start();
						Dialog.dispose();
					} else
					{
						tip.setForeground(Color.RED);
						tip.setText("�ļ������Ʋ��Ϸ����ظ�");
					}
				}
			}
		});
		// ȷ����ť
		JButton yes = new JButton("ȷ��");
		yes.setBackground(new Color(14, 142, 231));
		yes.setBounds(30, 70, 70, 30);
		Panel.add(yes);
		yes.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0)
			{
				if (CheckFoldername(Foldername.getText()) == true)
				{
					new Thread(new NewFolder(Foldername.getText(), Username, Route)).start();
					Dialog.dispose();
				} else
				{
					tip.setForeground(Color.RED);
					tip.setText("�ļ������Ʋ��Ϸ����ظ�");
				}
			}
		});
		// ȡ����ť
		JButton no = new JButton("ȡ��");
		no.setBackground(new Color(14, 142, 231));
		no.setBounds(110, 70, 70, 30);
		Panel.add(no);
		Dialog.setVisible(true);
		no.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0)
			{
				Dialog.dispose();
			}
		});
	}

	private void NewNickname()
	{
		// �����ƶ���
		Point origin = new Point();
		// �����Ի���
		JDialog Dialog = new JDialog();
		Dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Container container = Dialog.getContentPane();
		container.setLayout(null);
		Dialog.setBounds(600, 250, 210, 110);
		Dialog.setUndecorated(true);
		// �������
		JPanel Panel = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(Color.GRAY);
				g.drawRect(0, 0, 209, 109);
			}
		};
		Panel.setBounds(0, 0, 210, 110);
		Panel.setLayout(null);
		container.add(Panel);
		Panel.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		Panel.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{
				Point p = Dialog.getLocation();
				Dialog.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
			}
		});

		Panel.setBackground(Color.WHITE);

		// �ļ�������ʾ��ǩ
		JLabel tip = new JLabel("���ǳ�");
		tip.setBounds(20, 0, 200, 30);
		Panel.add(tip);
		// �ļ����������
		JTextField Nickname = new JTextField("");
		Nickname.setBounds(20, 30, 170, 27);
		Panel.add(Nickname);
		Nickname.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent arg0)
			{
				if (arg0.getKeyChar() == '\n')
				{
					if (CheckNickname(Nickname.getText()) == true)
					{
						new Thread(new NewNickname(Username,Nickname.getText())).start();
						Dialog.dispose();
					} else
					{
						tip.setForeground(Color.RED);
						tip.setText("�ǳƲ��Ϸ�");
					}
				}
			}
		});
		// ȷ����ť
		JButton yes = new JButton("ȷ��");
		yes.setBackground(new Color(14, 142, 231));
		yes.setBounds(30, 70, 70, 30);
		Panel.add(yes);
		yes.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0)
			{
				if (CheckNickname(Nickname.getText()) == true)
				{
					new Thread(new NewNickname(Username,Nickname.getText())).start();
					Dialog.dispose();
				} else
				{
					tip.setForeground(Color.RED);
					tip.setText("�ǳƲ��Ϸ�");
				}
			}
		});
		// ȡ����ť
		JButton no = new JButton("ȡ��");
		no.setBackground(new Color(14, 142, 231));
		no.setBounds(110, 70, 70, 30);
		Panel.add(no);
		Dialog.setVisible(true);
		no.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0)
			{
				Dialog.dispose();
			}
		});
	}

	
	private boolean CheckFoldername(String name)
	{
		if (name == null)
			return false;
		if (name.isEmpty())
			return false;
		for (int i = 0; i < FileList.size(); i++)
		{
			if (FileList.get(i).Filename.getText().equals(name) && FileList.get(i).FileSize.getText().equals("-"))
				return false;
		}
		return true;
	}

	private void AddTranList()
	{
		TranList = new JButton();
		TranList.setLayout(null);
		TranList.setBackground(new Color(14, 142, 231));
		TranList.setBorderPainted(false);
		TranList.setBounds(862, 74, 75, 30);

		ImageIcon ico = new ImageIcon("�����б�.jpg");
		Image temp = ico.getImage().getScaledInstance(75, 30, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image = new JLabel(ico);
		Image.setBounds(0, 0, 75, 30);

		ico = new ImageIcon("�����б�2.jpg");
		temp = ico.getImage().getScaledInstance(75, 30, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image2 = new JLabel(ico);
		Image2.setBounds(0, 0, 75, 30);

		ico = new ImageIcon("�������б�.jpg");
		temp = ico.getImage().getScaledInstance(95, 30, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image3 = new JLabel(ico);
		Image3.setBounds(0, 0, 95, 30);

		ico = new ImageIcon("�������б�2.jpg");
		temp = ico.getImage().getScaledInstance(95, 30, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image4 = new JLabel(ico);
		Image4.setBounds(0, 0, 95, 30);

		TranList.add(Image);
		UpPanel.add(TranList);
		TranList.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0)
			{
				RoutePane.setVisible(IsTranListOpened);
				ScrollPane.setVisible(IsTranListOpened);
				TableHeadPane.setVisible(IsTranListOpened);
				TablePane.setVisible(IsTranListOpened);
				TranList.removeAll();
				if (IsTranListOpened == true)
				{
					TranList.setBounds(862, 76, 75, 30);
					TranList.add(Image);
					TabbedPane.setVisible(false);
				} else
				{
					TranList.setBounds(842, 76, 95, 30);
					TranList.add(Image3);
					TabbedPane.setVisible(true);
				}
				IsTranListOpened = !IsTranListOpened;
			}

			public void mousePressed(MouseEvent arg0)
			{
				TranList.removeAll();
				if (IsTranListOpened)
					TranList.add(Image4);
				else
					TranList.add(Image2);
			}

			public void mouseReleased(MouseEvent arg0)
			{
				TranList.removeAll();
				if (IsTranListOpened)
					TranList.add(Image3);
				else
					TranList.add(Image);
			}
		});
	}

	private void AddTabbedPane()
	{
		AddUploading();
		AddDownloading();
		AddDone();
		Font f = new Font("΢���ź�", Font.PLAIN, 13);
		TabbedPane.setFont(f);

		JLabel Uploading = new JLabel(CreateLongName("�����ϴ�", 30), JLabel.CENTER);
		Uploading.setBackground(new Color(242, 246, 250));
		Uploading.setFont(f);
		TabbedPane.setTabComponentAt(0, Uploading);

		JLabel Downloading = new JLabel(CreateLongName("��������", 30), JLabel.CENTER);
		Downloading.setBackground(new Color(242, 246, 250));
		Downloading.setFont(f);
		TabbedPane.setTabComponentAt(1, Downloading);

		JLabel Done = new JLabel(CreateLongName("�������", 29), JLabel.CENTER);
		Done.setBackground(new Color(242, 246, 250));
		Done.setFont(f);
		TabbedPane.setTabComponentAt(2, Done);
	}

	private String CreateLongName(String name, int width)
	{
		String result = "";
		for (int i = 0; i < width; i++)
			result = result + " ";
		result = result + name;
		for (int i = 0; i < width; i++)
			result = result + " ";
		return result;
	}

	private void AddDownloading()
	{
		DownloadPane = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.drawLine(936, 0, 936, 10000);
			}
		};
		DownloadPane.setBounds(0, 0, 100, 100);
		DownloadPane.setPreferredSize(new Dimension(100, 100));
		DownloadPane.setBackground(Color.WHITE);
		DownloadPane.setLayout(null);
		JScrollPane ScrollPane = new JScrollPane(DownloadPane);
		ScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		ScrollPane.setBorder(null);
		ScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		ScrollPane.setBounds(0, 190, 940, 360);
		TabbedPane.add("�������� ", ScrollPane);
	}

	private void AddUploading()
	{
		UploadPane = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.drawLine(936, 0, 936, 10000);
			}
		};
		UploadPane.setBounds(0, 0, 100, 100);
		UploadPane.setPreferredSize(new Dimension(100, 100));
		UploadPane.setBackground(Color.WHITE);
		UploadPane.setLayout(null);
		JScrollPane ScrollPane = new JScrollPane(UploadPane);
		ScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		ScrollPane.setBorder(null);
		ScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		ScrollPane.setBounds(0, 190, 940, 360);
		TabbedPane.add("�����ϴ�", ScrollPane);
	}

	private void AddDone()
	{
		DonePane = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.drawLine(936, 0, 936, 10000);
			}
		};
		DonePane.setBounds(0, 0, 100, 100);
		DonePane.setPreferredSize(new Dimension(100, 100));
		DonePane.setBackground(Color.WHITE);
		DonePane.setLayout(null);
		JScrollPane ScrollPane = new JScrollPane(DonePane);
		ScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		ScrollPane.setBorder(null);
		ScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		ScrollPane.setBounds(0, 190, 910, 360);
		TabbedPane.add("�������", ScrollPane);
	}

	public JProgressBar AddOneDownloadFile(String Filename, String Size)
	{
		DownloadNum = DownloadList.size();
		// ���
		JPanel panel = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(new Color(153, 153, 153));
				g.drawLine(0, 0, 940, 0);
				g.drawLine(0, 69, 940, 69);
			}
		};
		panel.setLayout(null);
		panel.setBounds(2, -57 + (DownloadNum + 1) * 69, 920, 70);
		panel.setBackground(Color.WHITE);
		DownloadPane.add(panel);
		// �ļ�����ǩ
		JLabel FilenameLabel = new JLabel(Filename);
		Font f = new Font("΢���ź�", Font.BOLD, 16);
		FilenameLabel.setFont(f);
		FilenameLabel.setBounds(38, 10, 300, 30);
		panel.add(FilenameLabel);
		// �ļ���С��ǩ
		JLabel FileSize = new JLabel(Size);
		FileSize.setBounds(48, 40, 300, 30);
		panel.add(FileSize);
		// ������
		JProgressBar bar = new JProgressBar();
		bar.setBounds(400, 12, 200, 20);
		bar.setMinimum(0);
		bar.setMaximum(100);
		bar.setValue(0);
		bar.setStringPainted(true);
		bar.setForeground(new Color(61, 149, 215));
		bar.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				if (bar.getValue() == bar.getMaximum())
				{
					RemoveOneDownloadFile(Filename);
					AddOneDoneFile(Filename, Size);
				}
			}
		});
		panel.add(bar);
		DownloadPane.updateUI();
		if (76 + DownloadNum * 69 > DownloadPane.getHeight())
		{
			DownloadPane.setPreferredSize(new Dimension(100, 69 + DownloadNum * 75));
		}
		// ���뵽�б�
		DownloadView downloadview = new DownloadView();
		downloadview.Filename = FilenameLabel;
		downloadview.FileSize = FileSize;
		downloadview.bar = bar;
		downloadview.panel = panel;
		DownloadList.add(downloadview);
		return bar;
	}

	public JProgressBar AddOneUploadFile(String Filename, String Size)
	{
		UploadNum = UploadList.size();
		// ���
		JPanel panel = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(Color.GRAY);
				g.drawLine(0, 0, 940, 0);
				g.drawLine(0, 69, 940, 69);
			}
		};
		panel.setLayout(null);
		panel.setBounds(2, -60 + (UploadNum + 1) * 69, 920, 70);
		panel.setBackground(Color.WHITE);
		UploadPane.add(panel);
		// �ļ�����ǩ
		JLabel FilenameLabel = new JLabel(Filename);
		Font f = new Font("΢���ź�", Font.BOLD, 14);
		FilenameLabel.setFont(f);
		FilenameLabel.setBounds(38, 10, 300, 30);
		panel.add(FilenameLabel);
		// �ļ���С��ǩ
		JLabel FileSize = new JLabel(Size);
		FileSize.setBounds(48, 40, 300, 30);
		panel.add(FileSize);
		// ������
		JProgressBar bar = new JProgressBar();
		bar.setBounds(400, 22, 200, 20);
		bar.setMinimum(0);
		bar.setMaximum(100);
		bar.setValue(0);
		bar.setStringPainted(true);
		bar.setForeground(new Color(61, 149, 215));
		bar.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				if (bar.getValue() == bar.getMaximum())
				{
					RemoveOneUploadFile(Filename);
					AddOneDoneFile(Filename, Size);
				}
			}
		});
		panel.add(bar);
		
		if (76 + UploadNum * 69 > UploadPane.getHeight())
		{
			UploadPane.setPreferredSize(new Dimension(100, 69 + UploadNum * 75));
		}
		//��ͣ��ǩ
		JLabel Stop = new JLabel("||");
		f = new Font("΢���ź�", Font.BOLD, 14);
		Stop.setFont(f);
		Stop.setBounds(700, 15, 30, 30);
		
		panel.add(Stop);
		//������ǩ
		
		
		ImageIcon ico = new ImageIcon("����.jpg");
		Image temp = ico.getImage().getScaledInstance(17, 17, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Continue = new JLabel(ico);
		Continue.setBounds(700, 24, 17, 17);
		Continue.setVisible(false);
		panel.add(Continue);
		UploadPane.updateUI();
		
		Stop.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				Stop.setVisible(!Stop.isVisible());
				Continue.setVisible(!Continue.isVisible());
			}
		});
		
		Continue.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				Stop.setVisible(!Stop.isVisible());
				Continue.setVisible(!Continue.isVisible());
			}
		});
		
		// ���뵽�б�
		UploadView uploadview = new UploadView();
		uploadview.panel = panel;
		uploadview.Filename = FilenameLabel;
		uploadview.FileSize = FileSize;
		uploadview.bar = bar;
		UploadList.add(uploadview);

		return bar;
	}

	public void AddOneDoneFile(String Filename, String Size)
	{
		DoneNum = DoneList.size();
		// ���
		JPanel panel = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(new Color(153, 153, 153));
				g.drawLine(0, 0, 940, 0);
				g.drawLine(0, 69, 940, 69);
			}
		};
		panel.setLayout(null);
		panel.setBounds(2, -57 + (DoneNum + 1) * 69, 920, 70);
		panel.setBackground(Color.WHITE);
		DonePane.add(panel);
		// �ļ�����ǩ
		JLabel FilenameLabel = new JLabel(Filename);
		Font f = new Font("΢���ź�", Font.BOLD, 16);
		FilenameLabel.setFont(f);
		FilenameLabel.setBounds(38, 10, 300, 30);
		panel.add(FilenameLabel);
		// �ļ���С��ǩ
		JLabel FileSize = new JLabel(Size);
		FileSize.setBounds(48, 40, 300, 30);
		panel.add(FileSize);
		DonePane.updateUI();
		if (76 + DoneNum * 69 > DonePane.getHeight())
		{
			DonePane.setPreferredSize(new Dimension(100, 69 + DoneNum * 75));
		}
		// ɾ����ť
		JLabel Delete = new JLabel();
		f = new Font("΢���ź�", Font.BOLD, 20);
		Delete.setBounds(500, 20, 30, 30);
		Delete.setFont(f);
		Delete.setText("��");
		Delete.setBorder(null);
		Delete.setFocusable(false);
		Delete.setBackground(Color.WHITE);
		panel.add(Delete);
		Delete.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				RemoveOneDoneFile(Filename);
			}

			public void mouseEntered(MouseEvent e)
			{
				Delete.setForeground(Color.RED);
			}

			public void mouseExited(MouseEvent e)
			{
				Delete.setForeground(Color.BLACK);
			}

			public void mousePressed(MouseEvent e)
			{
				Delete.setLocation(502, 22);
			}

			public void mouseReleased(MouseEvent e)
			{
				Delete.setLocation(500, 20);
			}
		});
		// ���ļ���ť
		ImageIcon ico = new ImageIcon("���ļ�.jpg");
		Image temp = ico.getImage().getScaledInstance(15, 15, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image = new JLabel(ico);
		Image.setBounds(0, 0, 15, 15);

		JLabel OpenFile = new JLabel();
		OpenFile.setBounds(550, 29, 15, 15);
		OpenFile.setBorder(null);
		OpenFile.setFocusable(false);
		OpenFile.setBackground(Color.WHITE);
		OpenFile.add(Image);
		panel.add(OpenFile);
		OpenFile.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				//����Filename�ڱ��в�ѯ���ļ�����Ӧ��·��
				try {
					int MAXLEN = 65535;
					String route = null;
					String routename[] = new String[MAXLEN];
					String filepath = "G:\\OnlineDiskLadyDownload\\RouteList.txt";
					File routefile = new File(filepath);
			        FileInputStream fileInputStream = new FileInputStream(routefile);  
			        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "GBK");  
			        BufferedReader reader = new BufferedReader(inputStreamReader);  
			        String lineContent = ""; 
			        int i = 0;
			        while ((lineContent = reader.readLine()) != null) {  
			        	System.out.println(lineContent);  
			            routename[i] = lineContent.split("\\|")[1];
			            System.out.println("·��Ϊ��"+routename[i]);  //��
			            int len = routename[i].split("\\\\").length;
			            System.out.println("�ָ��ĳ���Ϊ��"+len);
			            String tempfilename = routename[i].split("\\\\")[len-1];
			            System.out.println("�õ����ļ���Ϊ��"+tempfilename);
			            System.out.println(Filename);
			            if(tempfilename.contains("\n"))
			            	System.out.println("111");
			            if(tempfilename.equals(Filename)){
			            	//ƥ��ɹ�
			            	route = routename[i];
			            	System.out.println("��ѯ�����ļ�·��Ϊ��"+route);
			            	break;
			            }
			            
			            i++;
			        }  
			          
			        fileInputStream.close();  
			        inputStreamReader.close();  
			        reader.close();
			        //���ļ�����
			        String command = "cmd.exe /c start "+route;
			        System.out.println("����Ϊ��"+command);
		            Runtime run = Runtime.getRuntime();//�����뵱ǰ Java Ӧ�ó�����ص�����ʱ����
		            try {
		                Process p = run.exec(command);// ������һ��������ִ������
		                BufferedInputStream in = new BufferedInputStream(p.getInputStream());
		                BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
		                String lineStr;
		                while ((lineStr = inBr.readLine()) != null)
		                    //�������ִ�к��ڿ���̨�������Ϣ
		                    System.out.println(lineStr);// ��ӡ�����Ϣ
		                //��������Ƿ�ִ��ʧ�ܡ�
		                if (p.waitFor() != 0) {
		                    if (p.exitValue() == 1)//p.exitValue()==0��ʾ����������1������������
		                        System.err.println("����ִ��ʧ��!");
		                }
		                inBr.close();
		                in.close();
		            } catch (Exception e1) {
		                e1.printStackTrace();
		            }
			        
			    } catch (Exception e1) {  
			        e1.printStackTrace();  
			    }
			}

			public void mousePressed(MouseEvent e)
			{
				OpenFile.setLocation(552, 30);
			}

			public void mouseReleased(MouseEvent e)
			{
				OpenFile.setLocation(550, 29);
			}
		});
		// ���뵽�б�
		DoneView doneview = new DoneView();
		doneview.Filename = FilenameLabel;
		doneview.FileSize = FileSize;
		doneview.panel = panel;
		DoneList.add(doneview);
	}

	private void RemoveOneDownloadFile(String Filename)
	{
		boolean IsFound = false;
		int i = 0;
		for (i = 0; i < DownloadList.size(); i++)
		{
			if (DownloadList.get(i).Filename.getText().equals(Filename))
			{
				DownloadPane.remove(DownloadList.get(i).panel);
				DownloadList.remove(i);
				IsFound = true;
				break;
			}
		}
		if (IsFound == true)
		{
			for (; i < DownloadList.size(); i++)
			{
				DownloadList.get(i).panel.setLocation(2, -18 + (i + 1) * 60);
			}
		}

		DownloadPane.updateUI();
	}

	private void RemoveOneUploadFile(String Filename)
	{
		boolean IsFound = false;
		int i = 0;
		for (i = 0; i < UploadList.size(); i++)
		{
			if (UploadList.get(i).Filename.getText().equals(Filename))
			{
				UploadPane.remove(UploadList.get(i).panel);
				UploadList.remove(i);
				IsFound = true;
				break;
			}
		}
		if (IsFound == true)
		{
			for (; i < DownloadList.size(); i++)
			{
				UploadList.get(i).panel.setLocation(2, -18 + (i + 1) * 60);
			}
		}
		UploadPane.updateUI();
	}

	private void RemoveOneDoneFile(String Filename)
	{
		new Thread(new RemoveOneDoneFile(Username, Filename)).start();// ֪ͨ������ɾ��������¼
		boolean IsFound = false;
		int i = 0;
		for (i = 0; i < DoneList.size(); i++)
		{
			if (DoneList.get(i).Filename.getText().equals(Filename))
			{
				DonePane.remove(DoneList.get(i).panel);
				DoneList.remove(i);
				IsFound = true;
				break;
			}
		}
		if (IsFound == true)
		{
			for (; i < DoneList.size(); i++)
			{
				DoneList.get(i).panel.setLocation(2, -48 + (i + 1) * 60);
			}
		}
		DonePane.updateUI();
	}

	private void AddOneFile(String Filename, String Size, String ModTime, int location)
	{
		FileView fileview = new FileView();
		// ���
		JPanel panel = new JPanel();
		panel.setBounds(2, -30 + location * 38, 936, 38);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);

		// ��Ӹ�ѡ��
		JCheckBox checkbox = new JCheckBox();
		checkbox.setBounds(38, 7, 17, 17);
		checkbox.setBackground(Color.WHITE);
		checkbox.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent arg0)
			{
				if (checkbox.isSelected())
					panel.setBackground(new Color(219, 240, 255));
				else
					panel.setBackground(Color.WHITE);
			}
		});
		checkbox.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0)
			{
				if (checkbox.isSelected() == false)
					AllSelectBox.setSelected(false);

				if (checkbox.isSelected())
					panel.setBackground(new Color(219, 240, 255));
				else
					panel.setBackground(new Color(240, 249, 255));

			}

			public void mouseEntered(MouseEvent arg0)
			{
				if (!checkbox.isSelected())
					panel.setBackground(new Color(240, 249, 255));
			}

			public void mouseExited(MouseEvent arg0)
			{
				if (!checkbox.isSelected())
					panel.setBackground(Color.WHITE);
			}
		});
		panel.add(checkbox);
		fileview.checkbox = checkbox;
		// ������崥��
		panel.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent arg0)
			{
				if (!checkbox.isSelected())
					panel.setBackground(new Color(240, 249, 255));
			}

			public void mouseExited(MouseEvent arg0)
			{
				if (!checkbox.isSelected())
					panel.setBackground(Color.WHITE);
			}

			public void mouseClicked(MouseEvent arg0)
			{
				checkbox.setSelected(!checkbox.isSelected());
				if (checkbox.isSelected())
					panel.setBackground(new Color(219, 240, 255));
				else
					panel.setBackground(new Color(240, 249, 255));
			}
		});
		// ����ļ���С��ǩ
		JLabel SizeLabel = null;
		if (Size.equals("-"))
			SizeLabel = new JLabel("       ��");
		else
			SizeLabel = new JLabel(Size);
		SizeLabel.setBounds(380, 0, 100, 30);
		panel.add(SizeLabel);
		fileview.FileSize = SizeLabel;
		// ����ļ�����ǩ
		JLabel FilenameLabel = new JLabel(Filename);
		Font f = new Font("΢���ź�", Font.BOLD, 12);
		FilenameLabel.setFont(f);
		FilenameLabel.setBounds(78, 0, 300, 30);
		panel.add(FilenameLabel);
		fileview.Filename = FilenameLabel;
		FilenameLabel.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent e)
			{
				if (Size.equals("-"))// ������ļ���
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				if (!checkbox.isSelected())
					panel.setBackground(new Color(240, 249, 255));
			}

			public void mouseExited(MouseEvent e)
			{
				if (Size.equals("-"))// ������ļ���
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				if (!checkbox.isSelected())
					panel.setBackground(Color.WHITE);
			}

			public void mouseClicked(MouseEvent e)
			{
				if (Size.equals("-"))// ������ļ���
				{
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					Route = Route + "\\" + FilenameLabel.getText();
					new Thread(new AskFileList(Username, Route)).start();
					String Route2 = Route.replace("\\", " �� ");
					RouteText.setText("�ҵ�����" + Route2);
				}
				checkbox.setSelected(!checkbox.isSelected());
				if (checkbox.isSelected())
					panel.setBackground(new Color(219, 240, 255));
				else
					panel.setBackground(new Color(240, 249, 255));
			}
		});
		// ����޸�ʱ���ǩ
		JLabel ModTimeLabel = new JLabel(ModTime);
		ModTimeLabel.setBounds(630, 0, 150, 30);
		panel.add(ModTimeLabel);
		if (location * 38 > TablePane.getHeight())
		{
			TablePane.setPreferredSize(new Dimension(100, 38 + location * 38));
		}
		TablePane.add(panel);
		TablePane.updateUI();
		fileview.ModTime = ModTimeLabel;
		FileList.add(fileview);
	}

	public void AddMulTableData(String[] Filename, String[] Size, String[] ModTime, int TotalNum)
	{
		TablePane.removeAll();
		FileList.removeAll(FileList);
		TablePane.setPreferredSize(new Dimension(100, 20 + TotalNum * 20));
		for (int i = 0; i < TotalNum; i++)
		{
			AddOneFile(Filename[i], Size[i], ModTime[i], i + 1);
		}
		this.repaint();
	}

	public void SetCapacity(String UsedAmount, String TotalAmount, int percent)
	{
		String str = UsedAmount + "/" + TotalAmount;
		Capacity.setForeground(Color.WHITE);
		Capacity.setText(str);
		CapacityPercent = percent;
		this.repaint();
	}

	public String[] SelectFile()// ��ʾ�û�ͨ�����ѡ��һ���ļ�����������ļ���
	{
		// �ļ�ѡ����
		JFileChooser chooser = new JFileChooser("D:\\");
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setMultiSelectionEnabled(true);
		Font f = new Font("΢���ź�", Font.BOLD, 13);
		updateFont(chooser, f);
		File DefaultLoc = new File("");
		chooser.setCurrentDirectory(DefaultLoc);
		int ret = chooser.showOpenDialog(this);
		File[] dir = null;
		if (ret == JFileChooser.APPROVE_OPTION)
		{
			dir = chooser.getSelectedFiles();
		}
		if (dir != null)
		{
			String[] result = new String[dir.length];
			for (int i = 0; i < result.length; i++)
			{
				result[i] = dir[i].getAbsolutePath();
			}
			return result;
		} else
			return null;
	}

	public String GetFolder(String Route)
	{
		int i;
		for (i = Route.length() - 1; i >= 0; i--)
		{
			if (Route.charAt(i) == '\\')
				break;
		}
		return Route.substring(0, i);
	}

	public ArrayList<FileView> GetSelectFile()
	{
		ArrayList<FileView> result = new ArrayList<>();
		for (int i = 0; i < FileList.size(); i++)
		{
			if (FileList.get(i).checkbox.isSelected())
				result.add(FileList.get(i));
		}
		return result;
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
	
	private static void updateFont(Component comp, Font font)
	{
		comp.setFont(font);
		if (comp instanceof Container)
		{
			Container c = (Container) comp;
			int n = c.getComponentCount();
			for (int i = 0; i < n; i++)
			{
				updateFont(c.getComponent(i), font);
			}
		}
	}
}

class FileView
{
	JCheckBox checkbox = null;
	JLabel Filename = null;
	JLabel FileSize = null;
	JLabel ModTime = null;
}

class UploadView
{
	JPanel panel = null;
	JLabel Filename = null;
	JLabel FileSize = null;
	JProgressBar bar = null;
}

class DownloadView
{
	JPanel panel = null;
	JLabel Filename = null;
	JLabel FileSize = null;
	JProgressBar bar = null;
}

class DoneView
{
	JPanel panel = null;
	JLabel Filename = null;
	JLabel FileSize = null;
}