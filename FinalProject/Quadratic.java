import java.util.*;

public class Quadratic{

    public double a = 0;
    public double b = 0;
    public double c = 0;
    public double[] roots = findRoots(this);

    public Quadratic(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Quadratic(double b, double c){
        a = 0;
        this.b = b;
        this.c = c;
    }

    public Quadratic(double c){
        a = 0;
        b = 0;
        this.c = 0;
    }


    public static double[] findRoots(Quadratic eq){

        double root1 = (-eq.b + Math.sqrt(Math.pow(eq.b, 2) + 4 * eq.a * eq.c))/ (2 * eq.a);
        double root2 = (-eq.b - Math.sqrt(Math.pow(eq.b, 2) + 4 * eq.a * eq.c))/ (2 * eq.a);

        double[] roots = {root1, root2};

        return roots;


    }




}
