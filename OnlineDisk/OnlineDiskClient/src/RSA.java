

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class RSA
{
	public BigInteger PubKey = null;// 公钥
	public BigInteger PriKey = null;// 私钥
	public BigInteger n = null;// n = p * q 1024位

	private BigInteger One = new BigInteger("1");

	public void CreateKey()// 生成一对公私钥
	{
		BigInteger p = FindPrime();// 生成p 512位
		BigInteger q = FindPrime();// 生成q 512位
		n = p.multiply(q);// n = p * q
		BigInteger Fain = (p.subtract(One)).multiply(q.subtract(One));// φ(n) =(p - 1)(q- 1)
		PubKey = CreatePubKey(Fain);// 创建公钥
		PriKey = CalcInverse(PubKey, Fain);// 计算私钥(公钥的逆元)
	}
	
	public static String Encrypt(String plain, BigInteger key, BigInteger moder)// 加解密
	{
		String cipher = null;//密文
		//明文分组
		LinkedList<String> list = Divider(plain,50);
		//首先把明文plain按char加密
		int i;
		for(i = 0;i < list.size();i++)//对于明文的每个字符
		{
			String num = StringToNum(list.get(i));//转换成String
			BigInteger temp = new BigInteger(num);//转换成BigInteger
			temp = temp.modPow(key, moder);//加密
			if(i == 0)
				cipher = temp.toString() + "_";
			else
				cipher = cipher + temp.toString() + "_";//转换成String连接到密文上
		}
		return cipher;
	}
	public static String Decrypt(String cipher, BigInteger key, BigInteger moder)
	{
		String plain = "";//明文
		String s[] = cipher.split("_");//按"_"分开
		int i;
		for(i = 0;i < s.length;i++)
		{
			BigInteger num = new BigInteger(s[i]);//转换成BigInteger
			num = num.modPow(key, moder);//解密
			plain = plain + NumToString(num.toString());
		}
		return plain;
	}
	public static String StringToNum(String s)
	{
		String result = "";
		for(int i = 0;i <s.length();i++)//对于明文的每个字符
		{
			String num = Integer.toString(s.charAt(i));//转换成String
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
	public static LinkedList<String> Divider(String s,int size)//分组
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
	private BigInteger FindPrime()// 生成一个随机的大素数 512位
	{
		SecureRandom rnd = new SecureRandom();// 随机数源
		BigInteger big_prime;
		int bitlength = 512;
		big_prime = BigInteger.probablePrime(bitlength, rnd);// 512bit的长素数
		return big_prime;
	}

	private BigInteger CreatePubKey(BigInteger Fain)// 生成公钥（算法未定）
	{
		// 返回(e,n)
		BigInteger init_val = new BigInteger("5113");
		if (Fain.gcd(init_val).intValue() == 1)
		{
			return init_val;
		} // 互素
		else
		{
			do
			{
				BigInteger val;
				val = init_val.nextProbablePrime();// 找到下一个素数
				if (Fain.gcd(val).intValue() == 1)
				{
					return val;
				}

			} while (true);
		}
	}

	private static BigInteger CalcInverse(BigInteger value, BigInteger moder)// 求value模moder的逆
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
		// 检查gcd ==1
		// 检查a>b
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
