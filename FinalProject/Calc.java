import java.util.*;

public class Calc{

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int num = 0;

        while (true){

            System.out.println("Input pls.");
            try{
                num = input.nextInt();

            }
            catch (InputMismatchException err){
                String exit = input.nextLine();
                if (exit.equals("exit")){
                    break;
                }
                System.out.println("Oops" + err);
                continue;
            }


            Complex sum = Complex.add(new Complex(2,3), new Complex(num));
            sum.display();







        }

    }
}
