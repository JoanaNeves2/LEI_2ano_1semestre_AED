// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static double potIt(double x, int n){
        double result = 1;
        if(n!=0){
            for(int k=n; k>=1; k--){
                result = result * x;
            }
        }
        return result;
    }

    private static double potRec(double x, int n){
        double result = 1;
        if (n != 0) {
            if(x % 2 == 0 && x>=0){
                result = potRec(x*x,n/2);
            }
            else{
                result = potRec(x*x,n/2)*x;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        double result;
        int pot = 1;
        double start, end;
        for(int k =1; k<=18; k++){
            pot = pot * 10;
            start = System.nanoTime();
            result = potIt(2, pot);
            end = System.nanoTime();
            System.out.println("It: "+ k + " => "+ (end - start));

            start = System.nanoTime();
            result = potRec(2, pot);
            end = System.nanoTime();
            System.out.println("Rec: "+ k + " => "+ (end - start));

            System.out.println();
        }

    }
}