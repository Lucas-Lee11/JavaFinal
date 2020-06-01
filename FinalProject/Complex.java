import java.util.*;

public class Complex {

	private double real;
	private double imaginary;
    public boolean isReal;

	public Complex(double r, double i) {

		real = r;
		imaginary = i;
        if(i == 0) isReal = true;
        else isReal = false;

	}

    public Complex(double num){
        real = num;
        imaginary = 0;
        isReal = true;
    }

	//Methods to handle polar aspects of complex numbers
    public double arg(){
        double output = Math.atan2(imaginary, real);
        return output;
    }

	public double mod() {
		double output = Math.hypot(real, imaginary);
		return output;
	}

	//Returs a number's complex consjugate
    public Complex conjugate(){
        return new Complex(real, -imaginary);
    }

	public String toString() {
		String output =
		((double) Math.round(real *100000))/100000 +  " + " + ((double) Math.round(imaginary *100000))/100000 + "i";
		return output;
	}

    //Basic arithmatic
	public static Complex add(Complex var1, Complex var2) {
		Complex output = new Complex (var1.real + var2.real, var1.imaginary + var2.imaginary);
		return output;
	}

    public static Complex sub(Complex var1, Complex var2){
        Complex neg = new Complex(-var2.real, -var2.imaginary);
        Complex output = add(var1, neg);
        return output;
    }

    public static Complex mult(Complex var1, Complex var2){
        Complex output = new Complex(var1.real* var2.real - var1.imaginary * var2.imaginary, var1.real * var2.imaginary + var2.real* var1.imaginary);
        return output;
    }

    public static Complex div(Complex var1, Complex var2){
         Complex numerator = mult(var1, var2.conjugate());
         double denominator = Math.pow(var2.real, 2) + Math.pow(var2.imaginary, 2);
         Complex output = new Complex(numerator.real/denominator, numerator.imaginary/denominator);
         return output;
     }

    public static Complex pow(Complex var, Complex pow) {
         Complex a = mult(new Complex(Math.log(var.mod())), pow);
         Complex temp = mult(new Complex(var.arg()), pow);
         Complex b = mult(new Complex(0, 1), temp);
         Complex exp = add(a, b);

         double mod = Math.pow(Math.E, exp.real);
         double arg = exp.imaginary;

         Complex output = polarToCoor(mod, arg);
         return output;
	 }

    public static Complex sqrt(Complex var){
        int sign;
        if(var.imaginary >= 0) sign = 1;
        else sign = -1;
        Complex output = new Complex(Math.sqrt((var.mod() + var.real)/2), sign * Math.sqrt((var.mod() - var.real)/2));
        return output;
    }

    public static Complex sin(Complex var){
        Complex output = new Complex(Math.sin(var.real) * Math.cosh(var.imaginary), Math.cos(var.real) * Math.sinh(var.imaginary));
        return output;
    }

    public static Complex cos(Complex var){
        Complex output = new Complex(Math.cos(var.real) * Math.cosh(var.imaginary),  -1 * Math.sin(var.real) * Math.sinh(var.imaginary));
        return output;
    }

    public static Complex tan(Complex var){
        Complex output = div(sin(var), cos(var));
        return output;
    }

    //Converts from a given modulus and argument to a+bi form
    public static Complex polarToCoor(double mod, double arg){
        Complex output = new Complex(mod * Math.cos(arg), mod * Math.sin(arg));
        return output;
    }

}
