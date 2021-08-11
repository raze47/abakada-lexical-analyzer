import java.util.Arrays;

/* OPTIONAL CLASS - For Space Sensitive Input*/
public class Token {
	String lineInput;

    String[] stringArray = new String[0];


    public void Tokenizer(String input) {
    	
        lineInput = input;
    }

    public void toStringArray() {
    	
    	/* Space Sensitive Format */
        stringArray = lineInput.split(" ");
        Arrays.toString(stringArray);
    }


    public String[] getStringArray() {

    	/* Return */
    	/* Main --> Check Token */
    	return stringArray;
    }

    public void showStringArray() {

        for (int i = 0; i < stringArray.length; i++) {
            System.out.println(stringArray[i]);
        }
    }
}








