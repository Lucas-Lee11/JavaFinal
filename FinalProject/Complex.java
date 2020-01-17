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

    public static Complex fullSqrt(double var){
        if (var >=0){
            return new Complex(Math.sqrt(var));
        }
        else{
            return new Complex(0, Math.sqrt(-var));
        }
    }

	public static Complex pow(Complex var, int pow) {
        if(pow >=0){
            Complex output = new Complex(1);
    		for(int i = 1; i <= pow; i++){
                output = mult(output, var);
            }
    		return output;
        }
        else{
            return div(new Complex(1), pow(var, -pow));
        }

	}

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

	public static double mod(Complex var) {
		double output = Math.hypot(var.real, var.imaginary);
		return output;
	}

    public Complex conjugate(){
        return new Complex(this.real, -this.imaginary);
    }

	public static void display(Complex var) {
		String output =var.real +  " + " + var.imaginary + "i";
		System.out.println(output);
	}

    public void display() {
		String output = this.real +  " + " + this.imaginary + "i";
		System.out.println(output);
	}


    public static void main(String[] args) {


        Complex a = new Complex(1,2);
        Complex b = new Complex(3,4);

        sub(a, b).display();
    }




}
