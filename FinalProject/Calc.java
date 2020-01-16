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
                    ch = 0;
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
                while(lookFor(' ')){
                    advance();
                }

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
                else if(lookFor('(')){
                    advance();
                    answer = addTogether();
                    do {
                        advance();
                    }while(lookFor(')'));
                    back();
                }

                else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') {
                        advance();
                    }
                    if(lookFor('(')){
                        back();
                        answer = Double.parseDouble(toSolve.substring(startPos, curPos + 1));
                        advance();advance();
                        answer *= addTogether();
                        do{
                            advance();

                        }while(lookFor(')'));
                        back();
                        return answer;
                    }
                    else if(ch >= 'a' && ch <= 'z') {
                        back();
                        answer = Double.parseDouble(toSolve.substring(startPos, curPos + 1));
                        advance();
                        answer*= addTogether();
                        return answer;
                    }
                    else if(!(ch == 0)){
                        back();
                    }

                    answer = Double.parseDouble(toSolve.substring(startPos, curPos + 1));
                }
                else if (ch >= 'a' && ch <= 'z') {
                    while (ch >= 'a' && ch <= 'z'){
                        advance();
                    }
                    if(!(ch >= 'a' && ch <= 'z') && ch != 0){
                        back();
                    }

                    String func = toSolve.substring(startPos, curPos + 1);
                    advance();
                    if(lookFor('(')){
                        advance();

                    }
                    if(ch == 0){
                        throw new RuntimeException ("No input for function: " + func);

                    }
                    else {
                        back();
                        if(!lookFor('(')){
                            advance();

                        }
                        answer = functionCompute();
                    }


                    if (func.equals("sqrt")) answer = Math.sqrt(answer);
                    else if (func.equals("sin")) answer = Math.sin(Math.toRadians(answer));
                    else if (func.equals("cos")) answer = Math.cos(Math.toRadians(answer));
                    else if (func.equals("tan")) answer = Math.tan(Math.toRadians(answer));
                    else throw new RuntimeException("Unknown function: " + func);
                }
                else {
                    throw new RuntimeException("Unexpected character: " + toSolve.charAt(curPos));
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
