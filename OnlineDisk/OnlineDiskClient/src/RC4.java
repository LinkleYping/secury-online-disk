public class RC4{
	static int LEN=256;
	private static byte[] initkey(String key)
	{
		byte[] K=key.getBytes();
		byte[] T=new byte[LEN];
		byte[] S=new byte[LEN];
		for(int i=0;i<LEN;i++)
		{
			S[i]=(byte)i;
			T[i]=K[i % K.length];
		}
		if(K==null||K.length==0)
			return null;
		int j=0;
		for(int i=0;i<LEN;i++)
		{
			j=((j+(S[i]& 0xff)+(T[i]& 0xff))& 0xff) % LEN;
			byte temp=S[i];
			S[i]=S[j];
			S[j]=temp;
		}
		return S;
	}
	
	public byte[] rc4(byte[] msg,String key)
	{
		int i=0,j=0,t=0,k=0;
		byte[] bkey= initkey(key);
		byte[] result=new byte[msg.length];
		for(int n=0 ;n<msg.length;n++)
		{
			i=((i+1)& 0xff)%LEN;
			j=((j + bkey[i])& 0xff) % LEN;
			byte temp=bkey[i];
			bkey[i]=bkey[j];
			bkey[j]=temp;
			t=((bkey[i]+bkey[j])& 0xff) % LEN;
			k=(bkey[t]& 0xff)%LEN;
			result[n]=(byte)(msg[n]^bkey[k]);
		}
		return result;
	}
	/*public static void main(String[] args)
	{
		byte[] a=new byte[3];
		a[0]=0;
		a[1]=1;
		byte[] b=rc4(a,"123");
		System.out.println(b[0]+" "+b[1]);
		byte[] r=rc4(b,"123");
		System.out.println(r[0]+" "+r[1]);
	}*/
}