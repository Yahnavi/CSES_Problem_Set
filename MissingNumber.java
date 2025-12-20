import java.util.Scanner;

public class MissingNumber{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean[] flag = new boolean[n];
        for(int i = 0; i < n - 1; i++){
            int curr = sc.nextInt();
            flag[curr - 1] = true;
        }

        for(int i = 0; i < n; i++){
            if(!flag[i]){
                System.out.println(i + 1);
                break;
            }
        }
        sc.close();
    }
}