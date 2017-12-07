
import java.security.*;  
import javax.crypto.*;  
  
import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;  
  
public class AES {  
    public static String DES = "AES"; // optional value AES/DES/DESede  
      
    public static String CIPHER_ALGORITHM = "AES"; // optional value AES/DES/DESede  
      
  
    public static Key getSecretKey(String key) throws Exception
    {  
        SecretKey securekey = null;  
        if(key == null){  
            key = "";  
        }  
        KeyGenerator keyGenerator = KeyGenerator.getInstance(DES);  
        keyGenerator.init(new SecureRandom(key.getBytes()));  
        securekey = keyGenerator.generateKey();  
        return securekey;  
    }  
      
    public static String encrypt(String data,String key) throws Exception {  
        SecureRandom sr = new SecureRandom();  
        Key securekey = getSecretKey(key);  
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);  
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);  
        byte[] bt = cipher.doFinal(data.getBytes());  
        String strs = new BASE64Encoder().encode(bt);  
        return strs;  
    }  
      
      
    public static String decrypt(String message,String key) throws Exception{  
        SecureRandom sr = new SecureRandom();  
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);  
        Key securekey = getSecretKey(key);  
        cipher.init(Cipher.DECRYPT_MODE, securekey,sr);  
        byte[] res = new BASE64Decoder().decodeBuffer(message);  
        res = cipher.doFinal(res);  
        return new String(res);  
    }  
      
   
}  