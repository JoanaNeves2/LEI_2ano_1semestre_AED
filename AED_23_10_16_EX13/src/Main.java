public class Main {

    private static int mul(int a, int b){
        if(b==0 || a==0)
            return 0;
        if(b==1)
            return a;
        else
            return a + mul(a, b-1);
    }

    public static void main(String[] args) {
        int a = 3;
        int b = 5;
        System.out.println(mul(a,b));
    }
}