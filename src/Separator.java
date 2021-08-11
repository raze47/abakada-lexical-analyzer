public class Separator {
	
	/* SYNTACTICAL ELEMENTS */
	String[] abakada_set		= new String[] {"pambuo", "hatimbuo", "bulyan", "titik", "parirala", "hindi", "at", "odikaya", "itigil", 
			"ituloy", "ibalik", "kapag", "kundi", "palipat", "kaso", "magpalya", "para", "gawin", "habang", "kawalan", "wala", "sukat_ng",
			"ang", "bago", "ito", "ay", "haba", "tama", "mali", "isulat", "basahin", "=", "<", ">",
			"?", ":", "&", "^", "|", "<<", ">>", ">>>", "!", "“", "#", "$", "‘", "\"", "(", ")", ".", "/", ";", "@", "[", 
			"]", "^", "_", "`", "{", "}", "~", "1", "2", "3", "4", "5", "6", "7", "8", "9", "\n", "+", "\'", "\""};
	
	/* Character Set */
	String[] abakada_malaki		= new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", 
			"S", "T", "U", "V", "W", "X", "Y", "Z"};
	String[] abakada_maliit		= new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", 
			"s", "t", "u", "v", "w", "x", "y", "z"};
	String[] bilang				= new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};	// Might delete
	String[] zero_bilang		= new String[] {"0"};
	String[] di_zero_bilang		= new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
	String[] simbolo			= new String[] {"!", "“", "#", "$", "%", "&", "‘", "(", ")", "*", "+", "|", "-", ".", "/", ":", ";", "<", 
			"=", ">", "?", "@", "[", "]", "\\", "^", "_", "`", "{", "|", "}", "~"};
	String[] pang_ayos			= new String[] {"\\t", "\\n", "\\\"", "\\\'", "\\\\"};

	/* Data Type */
	String[] pang_uri			= new String[] {"pambuo", "hatimbuo", "bulyan", "titik", "parirala"};
	
	/* Keywords and Reserved Words */
	String[] abakada_mahalaga	= new String[] {"itigil", 
			"ituloy", "ibalik", "kapag", "kundi", "palipat", "kaso", "magpalya", "para", "gawin", "habang", "kawalan", "wala", "sukat_ng"};
	String[] abakada_nakalaan 	= new String[] {"bago", "ito", "ay", "haba"};
	
	/* Boolean */
	String[] bulyan				= new String[] {"tama", "mali"};
	
	/* Comment */
	String[] kom_marami_huli	= new String[] {"*/"};
	
	/* Operators */
	String[] op_takda 			= new String[] {"="};
	String[] op_yunari 			= new String[] {"+", "-"};
	String[] op_palatuusan 		= new String[] {"+", "-", "*", "/", "%"};
	String[] op_relasyunal		= new String[] {"<", ">", "@", "$"};
	String[] op_lohikal			= new String[] {"at", "odikaya", "hindi"};
	String[] op_kondisyunal		= new String[] {"?", ":"};
	String[] op_pirasong_pantas	= new String[] {"&", "^", "|"};
	String[] op_parirala_dagdag = new String[] {"+"};
	
	/* Delimiters and Brackets */
	String[] tuldok_kuwit		= new String[] {";"};
	String[] del_saklong_una	= new String[] {"("};
	String[] del_saklong_huli	= new String[] {")"};
	String[] del_kulong_una		= new String[] {"{"};
	String[] del_kulong_huli	= new String[] {"}"};
	String[] del_palong_una		= new String[] {"["};			// Might delete
	String[] del_palong_huli	= new String[] {"]"};			// Might delete
	String[] del_separator		= new String[] {","};
	String[] del_titik			= new String[] {"'"};
	String[] del_parirala		= new String[] {"\""};
	String[] del_sunodLinya     = new String[] {"\n"};
	
	/* Pre-defined Functions */
	String[] abakada_pdf_basa	= new String[] {"basahin"};
	String[] abakada_pdf_sulat	= new String[] {"isulat"};
	
	char[] abakadaNum = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    char[] abakadaVar = new char[]{'_'};
    char[] abakadaDelChar = new char[]{'"'};
    
	String word;
	static boolean stopSeparate = false;
	static int lengthWord = 0;
	
	public String [] separate(String temp) {
		int ctr = 0;
		String temporary = temp;
		String acceptedLexemesTemp [] = new String [1000];
		char characterLexemes1 [] = characterize(temporary);
		while(!stopSeparate) {
			acceptedLexemesTemp[ctr] = wordWhiteSpaceGone(characterLexemes1);
			ctr++;
		}
		for(int i = 0; i<ctr; i++) {
			acceptedLexemesTemp[i] = trimmer(acceptedLexemesTemp[i]);
		}
		lengthWord = 0;
		stopSeparate = false;
		return acceptedLexemesTemp;
	}
	
	public String[] getLexemes(String[] acceptedLexemes) {	
		int lexLen = 0;
		while(acceptedLexemes[lexLen] != null) {
			lexLen++;
		}
		String[] trueAcceptedLexemes = new String[lexLen];
		for(int i = 0; i<lexLen; i++) {
			trueAcceptedLexemes[i] = acceptedLexemes[i];
		}
		return trueAcceptedLexemes;
	}
	
	public String trimmer(String str) {
		int len = str.length();
        int st = 0;
        char[] val = str.toCharArray();
        while ((st < len) && (val[len - 1] <= ' ')) {
            len--;
        }
        return str.substring(st, len);
	}
	
	public String wordWhiteSpaceGone(char[]characterLexemes) {
		int i = 0;
		boolean exit = false;
		boolean numDigit = false;
		char[] catcher = new char[10000];
		char test;
		if(characterLexemes.length<=lengthWord) {
			stopSeparate = true;
			exit = true;
		}
		
		try {
		while(!exit) {
			
			test = characterLexemes[lengthWord]; 
			if(Character.isWhitespace(test)) {
				exit = true;
			}
			else if(test == '.' || test == '$' || test == '@' || test =='&' || test == '|' || test == '!' || test== '<' ||test == '>' || test == ')' || test=='(' || test==';' ||test=='+' || test=='-' || test == '*' || test == '/' || test == '%' || test == '#' || test == '=' || test=='\"' || test=='\'' || test=='{' || test=='}') 
			{/*!Character.isDigit(test) && !Character.isLetter(test)*/
				if(numDigit) {
					return toString(catcher);
				}
				catcher[i] = test; 
				exit = true;
			}
			else {
				catcher[i] = test; 
				numDigit = true;
			}
			i++;
			lengthWord++;
		
		}
	
		}
		catch(Exception ex) {
			System.out.println(lengthWord);
			System.out.println(characterLexemes.length);
		}
		return toString(catcher);
	}
	
	public String toString(char[] a) { 
		 String string = new String(a); 
		 return string; 
	} 

	public char[] characterize(String temp) {
		return temp.toCharArray();
	}
}
