import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

    /**
     * @param args
     * @throws Exception 
     */
	public void send(String Server,String FromAccount,String Password,String ToAccount,
			String Title,String Text) throws Exception
	{
		Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp." + Server);
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", 587);
        //ʹ��JavaMail�����ʼ���5������
        //1������session
        Session session = Session.getInstance(prop);
        //����Session��debugģʽ�������Ϳ��Բ鿴��������Email������״̬
        session.setDebug(false);
        //2��ͨ��session�õ�transport����
        Transport ts = session.getTransport();
        //3��ʹ��������û��������������ʼ��������������ʼ�ʱ����������Ҫ�ύ������û����������smtp���������û��������붼ͨ����֤֮����ܹ����������ʼ����ռ��ˡ�
        ts.connect("smtp." + Server, FromAccount, Password);
        //4�������ʼ�
        Message message = createSimpleMail(session,FromAccount,ToAccount,Title,Text);
        //5�������ʼ�
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
	}


    public static MimeMessage createSimpleMail(Session session,String FromAccount,
    		String ToAccount,String Title,String Text) throws Exception {
        //�����ʼ�����
        MimeMessage message = new MimeMessage(session);
        //ָ���ʼ��ķ�����
        message.setFrom(new InternetAddress(FromAccount));
        //ָ���ʼ����ռ��ˣ����ڷ����˺��ռ�����һ���ģ��Ǿ����Լ����Լ���
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(ToAccount));
        //�ʼ��ı���
        message.setSubject(Title);
        //�ʼ����ı�����
        message.setContent(Text, "text/html;charset=UTF-8");
        //���ش����õ��ʼ�����
        return message;
    }
}