import java.util.*;

public class Quadratic{

    public double a;
    public double b;
    public double c;
    public Complex[] roots = new Complex[2];

    public Quadratic(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
        roots = findRoots(this);
    }


    public static Complex[] findRoots(Quadratic eq){
        System.out.println(eq.a);
        System.out.println(eq.b);
        System.out.println(eq.c);

        Complex root1 = Complex.div(Complex.add(new Complex(-eq.b), Complex.sqrt(new Complex(Math.pow(eq.b, 2) - 4 * eq.a * eq.c))), new Complex(2 * eq.a));
        Complex root2 = Complex.div(Complex.sub(new Complex(-eq.b), Complex.sqrt(new Complex(Math.pow(eq.b, 2) - 4 * eq.a * eq.c))), new Complex(2 * eq.a));

        Complex[] roots = {root1, root2};

        return roots;


    }

    public void display(){
        for(Complex root : roots){
            root.display();
        }
    }

    public static Quadratic combine(Quadratic eq1, Quadratic eq2){
        Quadratic output = new Quadratic(eq1.a -eq2.a, eq1.b - eq2.b, eq1.c - eq2.c);
        return output;
    }

    public static void main(String[] args) {
        Quadratic quad = new Quadratic(1,0,1);

        quad.roots[0].display();
    }




}
