import java.util.Random;

import com.swing.foodserving.utility.MailUtil;
import com.swing.foodserving.utility.PasswordEncoder;

public class Test {
	
    public static void main (String[] args) {  
        System.out.println(PasswordEncoder.getEncodedPassword("12345")); 
    }  
}
