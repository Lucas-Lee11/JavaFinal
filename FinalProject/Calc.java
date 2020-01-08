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
                System.out.println("Oops");
                input.nextLine();
                continue;
            }
            if (num == 666){
                break;
            }

            System.out.println(num);







        }

    }
}
