public class checkToken {
	
	/* VARIABLE DECLARATION */
	
	static boolean pariralaCheck = true;
	static boolean titikCheck = true;
	static boolean komentoCheck = true;
	static boolean packageCheck = true;
	
	static boolean exitCheck = false;
	static boolean firstPass = false;
	static boolean successKomento = false;
	static boolean oneTitik = false;

	static int finalKomIndex = 0;
	
	String[] stringCheck = new String[0];
	String temp;
	String tempToken = " ", tempLexe = " ";
	
	/* SYNTACTICAL ELEMENTS */
	String[] abakada_set		= new String[] {"pambuo", "hatimbuo", "bulyan", "titik", "parirala", "hindi", "at", "odikaya", "itigil", 
			"ituloy", "ibalik", "kapag", "kundi", "palipat", "kaso", "magpalya", "para", "gawin", "habang", "kawalan", "wala", "sukat_ng", 
			"ang", "bago", "ito", "ay", "haba", "tama", "mali", "isulat", "basahin", "angkat", "+", "-", "*", "%", "=", "<", ">", "?", ":", "&", "^", "|", 
			"!", "“", "#", "$", "‘", "\"", "(", ")", ".", "/", ";", "@", "[", "]", "^", "_", "`", "{", "}", "~",  "1", "2", "3", "4", "5", 
			"6", "7", "8", "9", "\'", "\"", "isulat", "basahin"};
	
	/* Character Set */
	String[] abakada_malaki		= new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", 
			"S", "T", "U", "V", "W", "X", "Y", "Z"};
	String[] abakada_maliit		= new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", 
			"s", "t", "u", "v", "w", "x", "y", "z"};
	String[] bilang				= new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};	
	String[] zero_bilang		= new String[] {"0"};
	String[] di_zero_bilang		= new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
	String[] simbolo			= new String[] {"!", "“", "#", "$", "%", "&", "‘", "(", ")", "*", "+", "|", "-", ".", "/", ":", ";", "<", 
			"=", ">", "?", "@", "[", "]", "\\", "^", "_", "`", "{", "|", "}", "~"};
	String[] pang_ayos			= new String[] {"\\t", "\\n", "\\\"", "\\\'", "\\\\"};

	/* Data Type */
	String[] pang_uri			= new String[] {"pambuo", "hatimbuo", "bulyan", "titik", "parirala"};
	
	/* Keywords, Reserved Words, and Noise Words */
	String[] abakada_mahalaga	= new String[] {"itigil", "ituloy", "ibalik", "kapag", "kundi", "palipat", "kaso", "magpalya", "para", "gawin", 
			"habang", "kawalan", "wala", "sukat_ng", "isulat", "basahin"};
	String[] abakada_nakalaan 	= new String[] {"bago", "ito", "ay", "haba"};
	String[] abakada_ingay		= new String[] {"ang"};
	String[] abakada_angkat 	= new String[] {"angkat"};
	
	/* Boolean */
	String[] bulyan				= new String[] {"tama", "mali"};
	
	/* Comment */
	String[] kom_marami_check   = new String[] {"#"};
	
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
	String[] del_titik			= new String[] {"\'"};
	String[] del_parirala		= new String[] {"\""};
	String[] del_sunodLinya     = new String[] {"\n"};

	/* Pre-defined Functions */
	String[] abakada_pdf_basa	= new String[] {"basahin"};
	String[] abakada_pdf_sulat	= new String[] {"isulat"};
	
	char[] abakadaNum = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    char[] abakadaVar = new char[]{'_'};
    char[] abakadaDelChar = new char[]{'"'};
    
    String outArr[];
    
    /* Check each string value */
	public String[] inStringtoCheck(String[] stringArray) {
		
		stringCheck = stringArray;
		outArr = new String[stringCheck.length];
		
		//System.out.print("\n");
		
		/* Check the Local String Array */
		for (int i = 0; i < stringCheck.length; i++) {
			int j = i - 1;
			int k = i - 2;
			int l = i - 3;
			
			exitCheck = false; 
			pariralaCheck = true;
			packageCheck = true;
			komentoCheck = true;
			
			if (searchValue(stringCheck[i])) {
				
				// String append
				if(stringCheck[i].equals(op_parirala_dagdag[0]) && !exitCheck && 
						(outArr[j].equals("String Declarator (End)") || outArr[k].equals("Append") || outArr[l].equals("Keyword")/*|| *//* outArr[j].equals("Identifier")
								||*/ /*outArr[k].equals("Delimiter Parenthesis (Start)")*/)) {
					outArr[i] = "Append";
					exitCheck = true;
				}
				
			
			         	


      
			         	
				
				// Keywords
         		for (int a = 0; a < abakada_mahalaga.length; a++) {
         			if (stringCheck[i].equals(abakada_mahalaga[a]) && !exitCheck) {
         				
         				if(stringCheck[i].equals("isulat")) {
         					outArr[i] = "Keyword (Pre-defined Function)";
         				}
         				else if (stringCheck[i].equals("basahin")) {
         					outArr[i] = "Keyword (Pre-defined Function)";
         				}
         				else if (stringCheck[i].equals("kawalan")) {
         					outArr[i] = "Keyword (Modifier)";
         				}
         				else {
         					outArr[i] = "Keyword";
         				}
         				exitCheck = true;
                     	break;
         			}
         		}
         		
         		// Reserved Words
        		for (int a = 0; a < abakada_nakalaan.length; a++) {
        			if (stringCheck[i].equals(abakada_nakalaan[a]) && !exitCheck) {
        				outArr[i] = "Reserved Word";
        				exitCheck = true;
                    	break;
                    }
        		}
        		
        		// Noise Words
        		if (stringCheck[i].equals(abakada_ingay[0]) && !exitCheck) {
    				outArr[i] = "Noise Word";
    				exitCheck = true;
                	//break;
                }
         		 
        		// Data Type
         		for (int a = 0; a < pang_uri.length; a++) {
         			if (stringCheck[i].equals(pang_uri[a]) && !exitCheck) {
         				
         				if(stringCheck[i].equals("pambuo")) {
         					outArr[i] = "Keyword (Integer)";
         				}
         				else if(stringCheck[i].equals("hatimbuo")) {
         					outArr[i] = "Keyword (Float)";
         				}
						else if(stringCheck[i].equals("bulyan")) {
							outArr[i] = "Keyword (Booleanr)";				
						         				}
						else if(stringCheck[i].equals("titik")) {
							outArr[i] = "Keyword (Character)";
							}
						else if(stringCheck[i].equals("parirala")) {
							outArr[i] = "Keyword (String)";
							}
	         				
         				//outArr[i] = "Keyword (Data Type)";
         				exitCheck = true;
                     	break;
                    }
         		}
         		 
         		// Boolean
         		for (int a = 0; a < bulyan.length; a++) {
         			if (stringCheck[i].equals(bulyan[a]) && !exitCheck) {
         				
         				if(stringCheck[i].equals("tama")) {
         					outArr[i] = "Boolean (True)";
         				}
         				else if(stringCheck[i].equals("mali")) {
         					outArr[i] = "Boolean (False)";
         				}
         				
         				outArr[i] = "Boolean";
         				exitCheck = true;
                     	break;
                    }
         		}
         		 
         		 // Operators
         		 for (int a = 0; a < op_takda.length; a++) {			// Assignment Operator
         			 if (stringCheck[i].equals(op_takda[a]) && !exitCheck) {
         				 outArr[i] = "Assignment Operator";
         				exitCheck = true;
         				 break;
         			 }
         		 }
         		 
         		for (int a = 0; a < op_palatuusan.length; a++) {		// Arithmetic Operator
         			if (stringCheck[i].equals(op_palatuusan[a]) && !exitCheck) {
         				if(stringCheck[i].equals("+")) {
         					outArr[i] = "Arithmetic Operator Plus";
         				}
         				else if(stringCheck[i].equals("-")) {
         					outArr[i] = "Arithmetic Operator Minus";
         				}
         				else if(stringCheck[i].equals("*")) {
         					outArr[i] = "Arithmetic Operator Multiplication";
         				}
         				else if(stringCheck[i].equals("/")) {
         					outArr[i] = "Arithmetic Operator Division";
         				}
         				else if(stringCheck[i].equals("%")) {
         					outArr[i] = "Arithmetic Operator Modulo";
         				}
           				//outArr[i] = "Arithmetic Operator";
           				exitCheck = true;
                       	break;
                    }
         		}
         		 
         		 for (int a = 0; a < op_yunari.length; a++) {			// Unary Operator
         			 if (stringCheck[i].equals(op_yunari[a]) && !exitCheck) {
         				 outArr[i] = "Unary Operator";
         				exitCheck = true;
         				 break;
         			 }
         		 }
         		
         		 
         		 for (int a = 0; a < op_relasyunal.length; a++) {		// Relational Operator
         			 if (stringCheck[i].equals(op_relasyunal[a]) && !exitCheck) {
         				
         				 if(stringCheck[i].equals("@")) {
         					 outArr[i] = "Relational Operator(EQUALS)";
         				 }
         				 else if(stringCheck[i].equals("$")) {
         					 outArr[i] = "Relational Operator(NOT EQUALS)";
         				 }
         				 else if(stringCheck[i].equals(">")) {
         					 outArr[i] = "Relational Operator(GREATER THAN)";
         				 } 
         				 else if(stringCheck[i].equals("<")) {
         					 outArr[i] = "Relational Operator(LESS THAN)";
         				 }
         				 
         				 //outArr[i] = "Relational Operator";
         				exitCheck = true;
                     	 break;
                      }
         		 }
         		 
         		 for (int a = 0; a < op_lohikal.length; a++) {			// Logical Operator
         			 if (stringCheck[i].equals(op_lohikal[a]) && !exitCheck) {
         				 
         				 if(stringCheck[i].equals("at")) {
         					 outArr[i] = "Logical Operator(AND)";
         				 }
         				 else if(stringCheck[i].equals("odikaya")){
         					 outArr[i] = "Logical Operator(OR)";
         				 }
         				 else if(stringCheck[i].equals("hindi")) {
         					 outArr[i] = "Logical Operator(INVERSE)";
         				 }
         				 
         				 
         				 //outArr[i] = "Logical Operator";
         				 exitCheck = true;
         				 break;
                     }
         		 }
         		 
         		 for (int a = 0; a < op_kondisyunal.length; a++) {		// Conditional Operator
         			 if (stringCheck[i].equals(op_kondisyunal[a]) && !exitCheck) {
         				 
         				 if(stringCheck[i].equals("?")) {
         					 outArr[i] = "Conditional Operator(?)";
         				 }
         				 else if(stringCheck[i].equals(":")) {
         					 outArr[i] = "Conditional Operator(:)";
         				 }
         				// outArr[i] = "Conditional Operator";
         				 exitCheck = true;
                     	 break;
                     }
         		 }
         		 
         		 for (int a = 0; a < op_pirasong_pantas.length; a++) {	// Bitwise Operator
         			 if (stringCheck[i].equals(op_pirasong_pantas[a]) && !exitCheck) {
         				 
         				 if(stringCheck[i].equals("&")) {
         					 outArr[i] = "Bitwise Operator(&)";
         				 }
         				 else if(stringCheck[i].equals("^")){
         					 outArr[i] = "Bitwise Operator(^)";
         				 }
         				 else if(stringCheck[i].equals("|")) {
         					 outArr[i] = "Bitwise Operator(|)";
         				 }
         				 
         				 
         				 
         				 
         				 //outArr[i] = "Bitwise Operator";
         				 exitCheck = true;
                     	 break;
                     }
         		 }
         		 
         		// Comments
         		for (int a = 0; a < kom_marami_check.length; a++) {
         			 if (stringCheck[i].equals(kom_marami_check[0]) && !exitCheck) {
         				 
         				 outArr[i] = "Multiple-line Comment (Start)";
         				 
         				 while(komentoCheck) {
         					
         					 i++;
         	
         					 if(stringCheck[i].equals(kom_marami_check[0])) {
         						 outArr[i] = "Multiple-line Comment (End)";
         						 komentoCheck = false;
         					 }
         					 
         					 else {
         						 outArr[i] = "Comment";
         					 }
         					 
         					 if(i >= stringCheck.length) {
         						 System.out.println("No closing");
         						 break;
         					 }
         					 
         				 }
         				 
         				 exitCheck = true;
         				 break;
                     }
         		 }
         		
         		
         	//[package
         	for (int a = 0; a < kom_marami_check.length; a++) {
        		if (stringCheck[i].equals(abakada_angkat[0]) && !exitCheck) {
        				 
        			 outArr[i] = "Keyword (PACKAGE)";
        				 
        				while(packageCheck) {
        					
        					 i++;
        	
        					 if(stringCheck[i].equals(";")) {
        						 outArr[i] = "Delimiter Semicolon";
        						 packageCheck = false;
        					 }
        					 
        					 else if(stringCheck[i].equals(".")) {
        						 outArr[i] = "Period";
        					 }
        					 
        					 else if(stringCheck[i-1].equals(".") && !stringCheck[i].equals(".")) {
        						 outArr[i] = "Package Extension";
        					 }
        					 else {
        						 outArr[i] = "Package";
        					 }
        					 
        					 if(i >= stringCheck.length) {
        						 System.out.println("No closing");
        						 break;
        					 }
        					 
        				 }
        				 
        				 exitCheck = true;
        				 break;
                    }
         	}
     		
         		
         		
         		
         		
         		 
         		 // Delimiters and Brackets
         		 for (int a = 0; a < tuldok_kuwit.length; a++) {		// Semicolon
         			 if (stringCheck[i].equals(tuldok_kuwit[a]) && !exitCheck) {
         				 outArr[i] = "Delimiter Semicolon";
         				exitCheck = true;
                     	 break;
                     }
         		 }
         		 
         		 for (int a = 0; a < del_separator.length; a++) {		// Separator
         			 if (stringCheck[i].equals(del_separator[a]) && !exitCheck) {
         				 outArr[i] = "Separator";
         				exitCheck = true;
                     	 break;
                     }
         		 }

         		 // Character Declarator
         		 for (int a = 0; a < del_titik.length; a++) {	
         			 oneTitik = false;
         			 if (stringCheck[i].equals(del_titik[a]) && !exitCheck) {
         		
         				 outArr[i] = "Character Declarator (Start)";
         				 
         				 while(pariralaCheck) {
         					
         					 i++;
         					 
         					 if(stringCheck[i].equals(del_titik[a])) {
         						 outArr[i] = "Character Declarator (End)";
         						 pariralaCheck = false;
         					 }
         					 
         					 else if (!oneTitik ){
         						 if(stringCheck[i].length() == 1) {
         							 oneTitik = true;
             						 outArr[i] = "titik";
         						 }
         						 
         						 else {
         							outArr[i] = "Invalid Token";
         						 }
         					
         					 }
         					 
         					 if(i >= stringCheck.length) {
         						 System.out.println("No closing");
         						 break;
         					 }
         				 }
         				 
         				 exitCheck = true;
         				 break;
                     }
         		 }
         		 
         		 // String Declarator
         		 for (int a = 0; a < del_parirala.length; a++) {
         			 if (stringCheck[i].equals(del_parirala[a]) && !exitCheck) {
         		
         				 outArr[i] = "String Declarator (Start)";
         				 
         				 while(pariralaCheck) {
         					
         					 i++;
         					 System.out.println(stringCheck[i]+" value of i: "+i);
         					 if(stringCheck[i].equals(del_parirala[a])) {
         						outArr[i] = "String Declarator (End)";
         						 pariralaCheck = false;
         					 }
         					 
         					 else {
         						 outArr[i] = "String";
         					 }
         					 
         					 if(i >= stringCheck.length) {
         						 System.out.println("No closing");
         						 break;
         					 }
         				 }
         				 
         				 exitCheck = true;
         				 break;
                     }
         		 }
         		 
         		 for (int a = 0; a < del_saklong_una.length; a++) {		// Parenthesis
         			 if (stringCheck[i].equals(del_saklong_una[a]) && !exitCheck) {
         				 outArr[i] = "Delimiter Parenthesis (Start)";
         				exitCheck = true;
                     	 break;
                     }
         		 }
         		 
         		for (int a = 0; a < del_saklong_huli.length; a++) {		 
         			 if (stringCheck[i].equals(del_saklong_huli[a]) && !exitCheck) {
         				 outArr[i] = "Delimiter Parenthesis (End)";
         				exitCheck = true;
                     	 break;
                     }
         		 }
         		 
         		 for (int a = 0; a < del_kulong_una.length; a++) {		// Curly Bracket
         			 if (stringCheck[i].equals(del_kulong_una[a]) && !exitCheck) {
         				 outArr[i] = "Delimiter Curly Bracket (Start)";
         				exitCheck = true;
                     	 break;
                     }
         		 }
         		 
         		for (int a = 0; a < del_kulong_huli.length; a++) {		 
         			 if (stringCheck[i].equals(del_kulong_huli[a]) && !exitCheck) {
         				 outArr[i] = "Delimiter Curly Bracket (End)";
         				exitCheck = true;
         				 break;
                     }
         		 }
         		 
         		 for (int a = 0; a < del_palong_una.length; a++) {		// Bracket
         			 if (stringCheck[i].equals(del_palong_una[a]) && !exitCheck) {
         				 outArr[i] = "Bracket (Start)";
         				exitCheck = true;
                     	 break;
                     }
         		 }
         		 
         		for (int a = 0; a < del_palong_huli.length; a++) {			 
         			 if (stringCheck[i].equals(del_palong_huli[a]) && !exitCheck) {
         				outArr[i] = "Bracket (End)";
         				exitCheck = true;
                     	 break;
                      }
         		 }
         		
         		// Pre-defined Functions
         		for (int a = 0; a < abakada_pdf_basa.length; a++) {		// Read Function 
        			 if (stringCheck[i].equals(abakada_pdf_basa[a]) && !exitCheck) {
        				 outArr[i] = "Read function";
        				 exitCheck = true;
                    	 break;
        			 }
        		 }
         		
         		for (int a = 0; a < abakada_pdf_sulat.length; a++) {	// Write Function	 
         			if (stringCheck[i].equals(abakada_pdf_sulat[a]) && !exitCheck) {
          				 outArr[i] = "Write function";
          				exitCheck = true;
                      	 break;
         			}
         		}
         		
         		if(!exitCheck) {
         			outArr[i] = "Constant Number"; // next line if ever
         			
         		}
         			
      	 	}
   	
			else if (checkConNum(stringCheck[i])) {
                outArr[i] = "Constant Number";
			}
			
			else if (checkVar(stringCheck[i])) {
                outArr[i] = "Identifier";
			}
        
			else {
                outArr[i] = "Invalid Identifier";
			}
         
		} // for loop
		
		return outArr;
	} // class method
	
	/* DISPLAY OUTPUT */
	public void printOutput(String [] temp){
		System.out.print("\n");
		System.out.print("LEXEME\t\t\t\t\t TOKEN\n");
		String formatline = "%-40s %-40s %n";
	    for(int i = 0; i < temp.length;i++){
	        System.out.format(formatline, stringCheck[i], temp[i]);
	    }
	   
	} // class method
	
    public boolean searchValue(String temp) {
        boolean counter = false;

        for (int i = 0; i < abakada_set.length; i++) {
        	if (temp.equalsIgnoreCase(abakada_set[i]) || temp.equalsIgnoreCase(String.valueOf(abakadaDelChar)))
                counter = true;
        }
        
        return counter;
        
    } // class method

    /* Check if the given string is an identifier */
    public boolean checkVar(String temp) {
        boolean counter = false;
        char[] ch = new char[temp.length()];
        
        for (int i = 0; i < temp.length(); i++) {
        	ch[i] = temp.charAt(i);
        }
        
        if(Character.isLetter(ch[0]) || ch[0] == abakadaVar[0] && Character.isLetter(ch[1])){
        	counter = true;
        }
        
        return counter;

    } // class method


    /* Check if the given string is an constant number */
    public boolean checkConNum(String temp) {
        boolean counter = true;
        char[] ch = new char[temp.length()];
       
        for (int i = 0; i < temp.length(); i++) {
                ch[i] = temp.charAt(i);
        }
        
        for(int g = 0 ;g < ch.length; g++){
        	if(!Character.isDigit(ch[g])){
                counter = false;
                break;
        	}
        }
        
        return counter;
           
    } // class method
	
}
