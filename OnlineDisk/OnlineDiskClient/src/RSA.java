

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class RSA
{
	public BigInteger PubKey = null;// ��Կ
	public BigInteger PriKey = null;// ˽Կ
	public BigInteger n = null;// n = p * q 1024λ

	private BigInteger One = new BigInteger("1");

	public void CreateKey()// ����һ�Թ�˽Կ
	{
		BigInteger p = FindPrime();// ����p 512λ
		BigInteger q = FindPrime();// ����q 512λ
		n = p.multiply(q);// n = p * q
		BigInteger Fain = (p.subtract(One)).multiply(q.subtract(One));// ��(n) =(p - 1)(q- 1)
		PubKey = CreatePubKey(Fain);// ������Կ
		PriKey = CalcInverse(PubKey, Fain);// ����˽Կ(��Կ����Ԫ)
	}
	
	public static String Encrypt(String plain, BigInteger key, BigInteger moder)// �ӽ���
	{
		String cipher = null;//����
		//���ķ���
		LinkedList<String> list = Divider(plain,50);
		//���Ȱ�����plain��char����
		int i;
		for(i = 0;i < list.size();i++)//�������ĵ�ÿ���ַ�
		{
			String num = StringToNum(list.get(i));//ת����String
			BigInteger temp = new BigInteger(num);//ת����BigInteger
			temp = temp.modPow(key, moder);//����
			if(i == 0)
				cipher = temp.toString() + "_";
			else
				cipher = cipher + temp.toString() + "_";//ת����String���ӵ�������
		}
		return cipher;
	}
	public static String Decrypt(String cipher, BigInteger key, BigInteger moder)
	{
		String plain = "";//����
		String s[] = cipher.split("_");//��"_"�ֿ�
		int i;
		for(i = 0;i < s.length;i++)
		{
			BigInteger num = new BigInteger(s[i]);//ת����BigInteger
			num = num.modPow(key, moder);//����
			plain = plain + NumToString(num.toString());
		}
		return plain;
	}
	public static String StringToNum(String s)
	{
		String result = "";
		for(int i = 0;i <s.length();i++)//�������ĵ�ÿ���ַ�
		{
			String num = Integer.toString(s.charAt(i));//ת����String
			int len = num.length();
			if(len < 5)
			{
				for(int j = 0;j < 5 - len;j++)
				{
					result = result + "0";
				}
			}
			result = result + num;
		}
		result = "1" + result;
		return result;
	}
	public static String NumToString(String s)
	{
		String result = "";
		String s_copy = s.substring(1,s.length());
		for(int i = 0;i + 5 <=s_copy.length();i+=5)
		{
			String temp = s_copy.substring(i, i + 5);
			int j;
			for(j = 0;j < 5;j++)
			{
				if(temp.charAt(j) != '0')
				{
					break;
				}
			}
			char c = (char)Integer.parseInt(temp.substring(j, 5));
			result = result + c;
		}
		return result;
	}
	public static LinkedList<String> Divider(String s,int size)//����
	{
		LinkedList<String> list = new LinkedList<>();
		int len = s.length() / size;
		int i;
		for(i = 0;i < len;i++)
		{
			list.add(s.substring(i*size, i*size + size));
		}
		list.add(s.substring(i*size,s.length()));
		return list;
	}
	private BigInteger FindPrime()// ����һ������Ĵ����� 512λ
	{
		SecureRandom rnd = new SecureRandom();// �����Դ
		BigInteger big_prime;
		int bitlength = 512;
		big_prime = BigInteger.probablePrime(bitlength, rnd);// 512bit�ĳ�����
		return big_prime;
	}

	private BigInteger CreatePubKey(BigInteger Fain)// ���ɹ�Կ���㷨δ����
	{
		// ����(e,n)
		BigInteger init_val = new BigInteger("5113");
		if (Fain.gcd(init_val).intValue() == 1)
		{
			return init_val;
		} // ����
		else
		{
			do
			{
				BigInteger val;
				val = init_val.nextProbablePrime();// �ҵ���һ������
				if (Fain.gcd(val).intValue() == 1)
				{
					return val;
				}

			} while (true);
		}
	}

	private static BigInteger CalcInverse(BigInteger value, BigInteger moder)// ��valueģmoder����
	{
		BigInteger i1 = BigInteger.valueOf(1);
		BigInteger i2 = BigInteger.valueOf(0);
		BigInteger j1 = BigInteger.valueOf(0);
		BigInteger j2 = BigInteger.valueOf(1);
		BigInteger a = value, b = moder;
		BigInteger q = BigInteger.valueOf(0);
		BigInteger temp = BigInteger.valueOf(0);
		BigInteger tempi = BigInteger.valueOf(0);
		BigInteger tempj = BigInteger.valueOf(0);
		// ���gcd ==1
		// ���a>b
		while (b.compareTo(BigInteger.valueOf(1)) != 0)
		{
			q = a.divide(b);
			temp = b;
			b = a.remainder(b);
			a = temp;
			tempi = i2;
			tempj = j2;
			i2 = i1.subtract(q.multiply(i2));
			j2 = j1.subtract(j2.multiply(q));
			i1 = tempi;
			j2 = tempj;
		}
		if (i2.compareTo(BigInteger.valueOf(0)) == -1)
		{
			i2 = i2.add(moder);
		}
		return i2;
	}
}
