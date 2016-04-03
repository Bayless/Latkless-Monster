/*****************************************************
 * class Scheme
 * Simulates a rudimentary Scheme interpreter
 *
 * ALGORITHM for EVALUATING A SCHEME EXPRESSION:
      1. Steal underpants.
      2. ...
      5. Profit!
 *
 * STACK OF CHOICE: ALStack by BSS
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
	expr = expr(1,expr.length-1);
	expr = expr.flip();
	ALStack<String> storage = new ALStack<String>();
	for (int i = 0; i<expr.length - 1; i++){
	    if (!(expr.substring(i,i+1).equals(" "))){
		storage.push(expr.substring(i,i+1));
	    }
	}
	ALStack sub = storage;
	int ctr = 0;
	String result = "";
	while (!sub.isEmpty){
	    if (sub.peek().equals("+")){
		sub.pop();
		unload(1,sub);
	    }
	    else if (sub.peek().equals("-")){
		sub.pop();
		unload(2,sub);
	    }
	    else if (sub.peek().equals("*")){
		sub.pop();
		unload(3,sub);
	    }
	    sub.pop();
	}
       
    }//end evaluate()


    /****************************************************** 
     * precond:  Assumes top of input stack is a number.
     * postcond: Performs op on nums until closing paren is seen thru peek().
     *           Returns the result of operating on sequence of operands.
     *           Ops: + is 1, - is 2, * is 3
     ******************************************************/
    public static String unload( int op, Stack<String> numbers ) {
	int first= Integer.parseInt(numbers.pop());
	String newNum = //make back into string????
	if (!isNumber(numbers.peek()) {
		evaluate(newNum);
	    }
	if (op == 1){
	    numbers.pop();
	    return "" + (first + unload(1,numbers));
	}
	else if (op == 2){
	    numbers.pop();
	    return "" + (first - unload(2,numbers));
	}
	else if (op == 3){
	    numbers.pop();
	    return "" + (first * unload(1,numbers));
	}
	
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
	ALStack<String> caleb = new ALStack<String>(s.length());
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

	/*v~~~~~~~~~~~~~~MAKE MORE~~~~~~~~~~~~~~v
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
          ^~~~~~~~~~~~~~~~AWESOME~~~~~~~~~~~~~~~^*/
    }//main

}//end class Scheme
