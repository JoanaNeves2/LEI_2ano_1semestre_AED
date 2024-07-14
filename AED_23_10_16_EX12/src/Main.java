public class Main {

    //O(n)
    private static int maxVec(int[] vec, int length){
        if(length==1){
            return vec[vec.length-1];
        }
        else{
            int tmp = vec[--length];
            return max(tmp, maxVec(vec,length));
        }
    }

    private static int max(int a, int b){
        if(a<=b){
            return b;
        }
        return a;
    }
    public static void main(String[] args) {
        int[] array = {1,4,-2,40};
        System.out.println(maxVec(array,array.length));
    }
}