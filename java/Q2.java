import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the string");
        String str=sc.nextLine();
        str=str.toLowerCase();
        String arr[]=str.split(" ");
        int count=0;
        for(int i=0;i<arr.length;i++){
            count=1;
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]!= null && arr[i].equals(arr[j])){
                    count++;
                    arr[j]=null;
                }
            }
            if(count > 1 && arr[i] != null) {
                System.out.println(arr[i]+" "+count);
            }
        }
    }
}