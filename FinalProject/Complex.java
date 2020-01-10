import java.text.DecimalFormat;
import java.util.*;

public class Complex {

	double real;
	double imaginary;
	double mod;

	public Complex(double r, double i) {

		real = r;
		imaginary = i;
		mod = mod(this);

	}

    public Complex(double num){
        real = num;
        imaginary = 0;
        mod = Math.abs(num);
    }

	public static Complex square(Complex var) {
		Complex output = new Complex(Math.pow(var.real,2) - Math.pow(var.imaginary, 2), 2 * var.real * var.imaginary);
		return output;
	}

    public Complex sqrt(double var){
        Complex output = new Complex(0, Math.sqrt(-var));
        return output;
    }

	public static Complex add(Complex var1, Complex var2) {
		Complex output = new Complex (var1.real + var2.real, var1.imaginary + var2.imaginary);
		return output;
	}

    public void Complex sub(Complex var1, Complex var2){
        Complex neg = new Complex(-var2.real, -var2.imaginary);
        Complex output = new Complex.add(var1, neg);
        return output;
    }

    public static Complex mult(Complex var1, Complex var2){
        Complex output = new Complex(Math.pow(var1.real, 2) - Math.pow(var2.imaginary, 2), var1.real * var2.imaginary + var2.real* var1.imaginary);
        return output;
    }

    public

	public static double mod(Complex var) {
		double output = Math.hypot(var.real, var.imaginary);
		return output;
	}

	public static void display(Complex var) {
		String output =var.real +  " + " + var.imaginary + "i";
		System.out.println(output);
	}

    public void display() {
		String output = this.real +  " + " + this.imaginary + "i";
		System.out.println(output);
	}




}
