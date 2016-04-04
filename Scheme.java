/*****************************************************
 * class Scheme
 * Simulates a rudimentary Scheme interpreter
 *
 * ALGORITHM for EVALUATING A SCHEME EXPRESSION:
      1. Steal underpants.
      2. ...
      5. Profit!
 *
 * STACK OF CHOICE: LLStack by BSS
 * b/c ...
 ******************************************************/

public class Scheme {

    /****************************************************** 
     * precond:  Assumes expr is a valid Scheme (prefix) expression,
     *           with whitespace separating all operators, parens, and 
     *           integer operands.
     * postcond: Returns the simplified value of the expression, as a String
     * eg,
     *           evaluate( "( + 4 3 )" ) -> 7
     *	         evaluate( "( + 4 ( * 2 5 ) 3 )" ) -> 17
     ******************************************************/
    //max 2 numbers per expression
    public static String evaluate( String expr ){
	expr = expr.substring(1,expr.length()-1); //removes first and last parenthesis
	expr = flip(expr); //flips values for processing
	LLStack<String> storage = new LLStack<String>();
	for (int i = 0; i < expr.length() - 1; i++){
	    if (!(expr.substring(i,i+1).equals(" "))){
    	    if ((expr.substring(i,i+1).equals(")"))) { //if it finds a mini expression in the bigger expression, it puts the entire mini expression on one layer of the stack.
	    	    String tmp = "";
	    	    while (!(expr.substring(i,i+1).equals("("))){
	        		tmp += (expr.substring(i,i+1));
	          		i++;
		            }
		        tmp += "(";
		        storage.push(tmp);
    	    }
    	    else {
    	        String tmp = "";
    	        while (!(expr.substring(i,i+1).equals(" "))){
    	            tmp = expr.substring(i,i+1) + tmp;
    	            i++;
    	        }
    	        storage.push(tmp);
    	    }
	    }
	}

	if (storage.peek().equals("+")){
	    storage.pop();
	    return "" + unload(1,storage);
	}
	else if (storage.peek().equals("-")){
	    storage.pop();
	    return "" + unload(2,storage);
	}
	else if (storage.peek().equals("*")){
	    storage.pop();
	    return "" + unload(3,storage);
	}
	return "";   
    }//end evaluate()


    /****************************************************** 
     * precond:  Assumes top of input stack is a number.
     * postcond: Performs op on nums until closing paren is seen thru peek().
     *           Returns the result of operating on sequence of operands.
     *           Ops: + is 1, - is 2, * is 3
     ******************************************************/
    public static int unload( int op, LLStack<String> numbers ) {
	if (!isNumber(numbers.peek())) {
	    numbers.push(evaluate(flip(numbers.pop())));
		return unload(op,numbers); 
	    }
	    
	int first= Integer.parseInt(numbers.pop());
	if (numbers.isEmpty()){
	    return first;
	}
	
	else if (op == 1){
	    return first + unload(1,numbers);
	}
	else if (op == 2){
	    return first - unload(1,numbers);
	}
	else if (op == 3){
	    return first * unload(3,numbers);
	}
	return 0;
    }//end unload()


    //optional check-to-see-if-its-a-number helper fxn:
    public static boolean isNumber( String s ) {
        try {
	    Integer.parseInt(s);
	    return true;
	}
        catch( NumberFormatException e ) {
	    return false;
	}
    }
    
    public static String flip( String s ) 
    {
	String retStr = "";
	LLStack<String> caleb = new LLStack<String>();
	for (int i = 0; i < s.length(); i++){
	    caleb.push(s.substring(i,i+1));
	}
	while (!(caleb.isEmpty())){
	    retStr+=caleb.pop();
	}
	return retStr;

    }//end flip()

    //main method for testing
    public static void main( String[] args ) {

	String zoo1 = "( + 4 3 )";
	System.out.println(zoo1);
	System.out.println("zoo1 eval'd: " + evaluate(zoo1) );
	//...7
	
	String zoo2 = "( + 4 ( * 2 5 ) 3 )";
	System.out.println(zoo2);
	System.out.println("zoo2 eval'd: " + evaluate(zoo2) );
	//...17

	String zoo3 = "( + 4 ( * 2 5 ) 6 3 ( - 56 50 ) )";
	System.out.println(zoo3);
	System.out.println("zoo3 eval'd: " + evaluate(zoo3) );
	//...29

	String zoo4 = "( - 1 2 3 )";
	System.out.println(zoo4);
	System.out.println("zoo4 eval'd: " + evaluate(zoo4) );
	//...-4
		  /*v~~~~~~~~~~~~~~MAKE MORE~~~~~~~~~~~~~~v
          ^~~~~~~~~~~~~~~~AWESOME~~~~~~~~~~~~~~~^*/
    }//main

}//end class Scheme
