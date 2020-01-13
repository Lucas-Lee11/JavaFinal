import java.util.*;

public class Calc{

    public static double eqSolver(String toSolve){
        return new Object(){
            int curPos = 0, ch  = toSolve.charAt(curPos);

            void advance(){
                if(curPos + 1 < toSolve.length()){
                    curPos++;
                    ch = toSolve.charAt(curPos);
                }
                else{
                    ch = '\0';
                }
            }

            void back(){
                if(!(curPos - 1 < 0)){
                    curPos--;
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
                        answer += multiplyTogether();
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
                    if(lookFor('*') || lookFor('(')){
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
                    advance();
                    return functionCompute();
                }
                else if(lookFor('-')){
                    advance();
                    return -functionCompute();
                }

                if(lookFor('(')){
                    advance();
                    answer = addTogether();
                }

                else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') {
                        advance();
                    }
                    if(!((ch >= '0' && ch <= '9') || ch == '.' || ch == '\0')){
                        back();
                    }

                    answer = Double.parseDouble(toSolve.substring(startPos, curPos + 1));
                }
                else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z'){
                        advance();
                    }
                    if(!(ch >= 'a' && ch <= 'z')){
                        back();
                    }

                    String func = toSolve.substring(startPos, curPos + 1);
                    advance();

                    if(ch != '\0'){
                        answer = functionCompute();
                    }
                    else throw new RuntimeException ("No input for function: " + func);


                    if (func.equals("sqrt")) answer = Math.sqrt(answer);
                    else if (func.equals("sin")) answer = Math.sin(Math.toRadians(answer));
                    else if (func.equals("cos")) answer = Math.cos(Math.toRadians(answer));
                    else if (func.equals("tan")) answer = Math.tan(Math.toRadians(answer));
                    else throw new RuntimeException("Unknown function: " + func);
                }
                else {
                    throw new RuntimeException("Unexpected character");
                }

                advance();
                if (lookFor('^')) {
                    advance();
                    answer = Math.pow(answer, functionCompute());
                }
                else{
                    back();
                }

                return answer;

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


            System.out.println("Evaluation: " + eqSolver(eq));



        }

    }
}
