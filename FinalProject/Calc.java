import java.util.*;

public class Calc{

    public static double eqSolver(String toSolve){
        System.out.println("ln:" + toSolve.length());
        return new Object(){
            int curPos = 0, ch  = toSolve.charAt(curPos);

            void advance(){
                if(curPos + 1 < toSolve.length()){
                    curPos++;
                    System.out.println("advancing to :" + curPos);
                    ch = toSolve.charAt(curPos);
                }
                else{
                    ch = '\0';
                }
            }

            void back(){
                if(!(curPos - 1 < 0)){
                    curPos--;
                    System.out.println("back to :" + curPos);
                    ch = toSolve.charAt(curPos);

                }
            }


            boolean lookFor(char toLook){
                while(ch == ' '){
                    advance();
                }
                if(ch == toLook){
                    return true;
                }
                else{
                    return false;
                }
            }


            double addTogether(){

                double answer = multiplyTogether();
                while (true){
                    advance();
                    if(lookFor('+')){
                        advance();
                        System.out.println("found a +");
                        answer += multiplyTogether();
                        System.out.println("after adding:" + answer);
                    }
                    else if(lookFor('-')){
                        advance();
                        answer -= multiplyTogether();
                    }
                    else{
                        back();
                        return answer;
                    }

                }

            }

            double multiplyTogether(){
                double answer = functionCompute();
                while (true){
                    advance();
                    if(lookFor('*')){
                        advance();
                        answer *= functionCompute();
                    }
                    else if(lookFor('/')){
                        advance();
                        answer /= functionCompute();
                    }
                    else{
                        back();
                        return answer;
                    }



                }


            }

            double functionCompute(){
                double answer;
                int startPos = curPos;

                if(lookFor('+')){
                    return functionCompute();
                }
                else if(lookFor('-')){
                    return -functionCompute();
                }

                if(lookFor('(')){
                    advance();
                    answer = addTogether();
                    lookFor(')');
                }

                else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') {
                        System.out.println(ch);
                        advance();
                    }
                    if(!((ch >= '0' && ch <= '9') || ch == '.' || ch == '\0')){
                        back();

                    }

                    System.out.println("final num: " + ch);
                    System.out.println(curPos);
                    answer = Double.parseDouble(toSolve.substring(startPos, curPos + 1));
                    System.out.println(answer);
                }
                else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z'){
                        advance();

                    }
                    if(!(ch >= 'a' && ch <= 'z')){
                        back();
                    }
                    String func = toSolve.substring(startPos, curPos + 1);
                    answer = functionCompute();
                    if (func.equals("sqrt")) answer = Math.sqrt(answer);
                    else if (func.equals("sin")) answer = Math.sin(Math.toRadians(answer));
                    else if (func.equals("cos")) answer = Math.cos(Math.toRadians(answer));
                    else if (func.equals("tan")) answer = Math.tan(Math.toRadians(answer));
                    else throw new RuntimeException("Unknown function: " + func);
                }
                else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (lookFor('^')) answer = Math.pow(answer, functionCompute()); // exponentiation
                System.out.println("End Compute");

                return answer;

            }




        }.addTogether();



    }


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        while (true){

            System.out.println("Input pls.");
            String eq = input.nextLine();


            System.out.println("final:" + eqSolver(eq));



        }

    }
}
