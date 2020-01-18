import java.util.*;

public class Calc{

    public static Complex eqSolver(String toSolve){
        return new Object(){
            //Tracks place in string and the character there
            int curPos = 0, ch  = toSolve.charAt(curPos);

            //Moves along the string unless it's the end
            void advance(){
                if(curPos + 1 < toSolve.length()){
                    curPos++;
                    ch = toSolve.charAt(curPos);
                }
                else ch = 0;
            }

            //Moves backwards on the string
            void back(){
                if(!(curPos - 1 < 0)){
                    curPos--;
                    ch = toSolve.charAt(curPos);
                }
                while(ch == ' ') back();
            }

            //Tries to scan for a character
            boolean lookFor(char toLook){
                while(ch == ' ') advance();
                if(ch == toLook) return true;
                else return false;
            }

            //Main eqSolver; calls multiplyTogether and adds/subtracts results
            Complex addTogether(){
                Complex fullAnswer = multiplyTogether();
                while (true){
                    advance();
                    if(lookFor('+')){
                        advance();
                        fullAnswer = Complex.add(fullAnswer, multiplyTogether());
                    }
                    else if(lookFor('-')){
                        advance();
                        fullAnswer = Complex.sub(fullAnswer, multiplyTogether());
                    }
                    else{
                        back();
                        return fullAnswer;
                    }
                }
            }

            //Multiplies or divides results of functionCompute
            Complex multiplyTogether(){
                Complex fullAnswer = functionCompute();
                while (true){
                    advance();
                    if(lookFor('*')){
                        advance();
                        fullAnswer = Complex.mult(fullAnswer, functionCompute());
                    }
                    else if(lookFor('/')){
                        advance();
                        fullAnswer = Complex.div(fullAnswer, functionCompute());
                    }
                    else{
                        back();
                        return fullAnswer;
                    }
                }

            }

            Complex functionCompute(){
                //Gets rid of spaces
                lookFor(' ');

                double answer;
                Complex fullAnswer;
                int startPos = curPos; //Used to find full value of functions or numbers

                //For direct modifiers to a number
                if(lookFor('+')){
                    advance();
                    return functionCompute();
                }
                else if(lookFor('-')){
                    advance();
                    return Complex.mult(functionCompute(), new Complex(-1));
                }
                //For parentheses
                else if(lookFor('(')){
                    advance();
                    fullAnswer = addTogether();
                    do {
                        advance();
                    }while(lookFor(')'));
                    back();
                }

                else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    //Looks for digits
                    while ((ch >= '0' && ch <= '9') || ch == '.') {
                        advance();
                    }
                    //Automatically multiplies parentheses eg 3(2) = 6
                    if(lookFor('(')){
                        back();
                        answer = Double.parseDouble(toSolve.substring(startPos, curPos + 1));
                        advance();advance();
                        fullAnswer = new Complex(answer);
                        fullAnswer = Complex.mult(fullAnswer, addTogether());
                        do{
                            advance();
                        }while(lookFor(')'));
                        back();

                        return fullAnswer;
                    }
                    //Automatically multiplies functions eg 3sin(30) = 3/2
                    else if(ch >= 'a' && ch <= 'z') {
                        back();
                        answer = Double.parseDouble(toSolve.substring(startPos, curPos + 1));
                        fullAnswer = new Complex(answer);
                        advance();
                        fullAnswer = Complex.mult(fullAnswer, addTogether());
                        return fullAnswer;
                    }
                    else if(!(ch == 0)){
                        back();
                    }

                    answer = Double.parseDouble(toSolve.substring(startPos, curPos + 1));
                    fullAnswer = new Complex(answer);

                }
                else if (ch >= 'a' && ch <= 'z') {
                    //Looks for the names of functions
                    while (ch >= 'a' && ch <= 'z'){
                        advance();
                    }

                    if(ch != 0)back();

                    String func = toSolve.substring(startPos, curPos + 1);
                    if (func.equals("e")){
                        answer = Math.E;
                        fullAnswer = new Complex(answer);
                        return fullAnswer;
                    }
                    else if (func.equals("pi")){
                        answer = Math.PI;
                        fullAnswer = new Complex(answer);
                        return fullAnswer;
                    }


                    advance();
                    if(lookFor('(')){
                        advance();

                    }
                    if(ch == 0){
                        throw new RuntimeException ("No input for function: " + func + "()");

                    }
                    else {
                        back();
                        if(!lookFor('(')){
                            advance();

                        }
                        fullAnswer = functionCompute();
                    }


                    //List of recognized functions
                    /*
                    if (func.equals("sqrt")) answer = Math.sqrt(answer);
                    else if (func.equals("sin")) answer = Math.sin(Math.toRadians(answer));
                    else if (func.equals("cos")) answer = Math.cos(Math.toRadians(answer));
                    else if (func.equals("tan")) answer = Math.tan(Math.toRadians(answer));
                    else throw new RuntimeException("Unknown function: " + func + "()");
                    */
                }
                else {
                    throw new RuntimeException("Unexpected character: " + toSolve.charAt(curPos));
                }

                //For exponents
                advance();
                if (lookFor('^')) {
                    advance();
                    fullAnswer = Complex.pow(fullAnswer, (int) functionCompute().real);
                }
                else back();

                return fullAnswer;

            }

        }.addTogether();


    }
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Type exit to leave");

        while (true){

            System.out.println("***************************");
            System.out.println("Enter an expression");
            String eq = input.nextLine();

            if(eq.toLowerCase().equals("exit")){
                break;
            }


            System.out.print("Evaluation: ");
            eqSolver(eq).display();



        }

    }
}
