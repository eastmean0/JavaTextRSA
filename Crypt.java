import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Crypt 
{
	public static void main(String[] args) throws InvalidKeySpecException
	{	
		try
		{
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            
            KeyPair keyPair = keyPairGenerator.genKeyPair();
            Key publicKey = keyPair.getPublic(); // public key
            Key privateKey = keyPair.getPrivate(); // private key
            
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
            RSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);
            
            //Encrypt text
            Scanner text = new Scanner(System.in);
            System.out.println("Enter Plain Text");
            String ptext = text.nextLine();
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] encryptBytes = cipher.doFinal(ptext.getBytes());
			String Etext = new String(encryptBytes);
			System.out.println(Etext+"\n");
			
			//Decrypt text
			System.out.println("복호화를 진행합니다");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] decryptBytes = cipher.doFinal(encryptBytes);
			String Dtext = new String(decryptBytes);
			System.out.println(Dtext);
			
		}
			catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex)
			{
				ex.printStackTrace();
				
			}
	}
}