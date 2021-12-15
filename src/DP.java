import java.util.Arrays;

public class DP {


    public static int getDrops(int jars, int floors){
        int [][] jarDrops = new int [jars+1][floors+1];
        int [][] s = new int [jars+1][floors+1];
        //base case 1:
        // if floors = 0 then no drops are required // OR floors = 1 then 1 drop is required
        for (int i = 1; i <=jars ; i++) {
            jarDrops[i][0] = 0;
            jarDrops[i][1] = 1;
            s[i][1]=1;
        }
        //base case 2:
        //if only one jar is there then drops = floors
        for (int i = 1; i <=floors ; i++) {
            jarDrops[1][i] = i;
            s[1][i]=i;
        }

        for (int i = 2; i<=jars ; i++) {
            for (int j = 2;j<=floors; j++) {
                jarDrops[i][j] = Integer.MAX_VALUE;
                int tempResult;
                for (int k = 1 ; k<j ; k++) {
                    tempResult = 1 + Math.max(jarDrops[i-1][k-1],jarDrops[i][j-k]);
                    if (tempResult < jarDrops[i][j]) {
                        jarDrops[i][j] = tempResult;
                        s[i][j]=k;
                    }
                }
            }
        }
        printJars(s);
        //traceJars(c,s);
        return jarDrops[jars][floors];
    }

    public static void printJars(int[][] s) {
        for (int i = 0;i<s.length;i++) {
            System.out.println();
            for (int j = 0;j<s[0].length;j++) {
                System.out.print(s[i][j] +" ");
            }
        }
        System.out.println();
    }

    public static void traceJars(int[][] s, int[][] c) {
        for (int i = 0;i<s.length;i++) {
            System.out.println();
            for (int j = 0;j<s[0].length;j++) {
                System.out.print(s[i][j] +" ");
            }
        }
        System.out.println();
    }

    public static void getRod(int[] p, int n) {
        int[] s = new int[n + 1];
        int[] r = new int[n + 1];
        r[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            int best = -1;
            int best_k=0;
            for (int k = 0; k < p.length; k++) {
                if (i - k - 1 >= 0) {
                    best = Math.max(best, p[k] + r[i - k - 1]);
                    best_k = k;
                }
            }
            s[i] = best_k;
            r[i] = best;
        }
        for (int i = 0; i < n + 1; i++) {
            System.out.print(i + ": "+ r[i]+ " ");
        }
        System.out.println();
        for (int i = 0; i < n + 1; i++) {
            System.out.print(s[i]);
        }

    }

    public static void getPalindrome(String p) {
        int[][] c = new int[p.length()][p.length()];
        int[][] s = new int[p.length()][p.length()];
        int best_p;
        int best_i;
        int best;
        c[0][0] = 0;
        c[1][0] = 1;
        c[0][1] = 1;
        // loop through p lengths
        for (int z=2;z<p.length();z++) {
            String x1 = p.substring(0,z);
            String x2 = p.substring(z);
            best = Integer.MAX_VALUE*(-1);
            for (int i=1;i<z;i++) {
                best_i = 0;
                for (int j=i;j>=0;j--) {
                    if (x1.indexOf(i) == x2.indexOf(j)) {
                        if ((1+c[i-1][j-1])>best) {
                            best_i = (1+c[i-1][j-1]);
                        }
                    }
                    else {
                        int temp = Math.max(c[i-1][j],c[i][j-1]);
                        if (temp>best) {
                            best_i = temp;
                        }
                    }
                }
                c[z][i]=best;
            }
        }
        // loop through indeces 1 to n
        // loop to that indece



    }

    public static void main(String[] args) {
        // get rod
//        int[] p = new int[]{2, 1, 6, 8};
//        int n = 4;
//        getRod(p, n);

        // get trials
        //System.out.println(getLadderTrials(5,3));


        // jars
        //System.out.println(getDrops(2,5));

//        String[]
//        getPalindrome();
//        int[] A = new int[]{2,43,4,7,11,2,4};
//        getLIS(A);

        char[] B = new char[]{'d','e','f'};
        char[] C = new char[]{'d','e','f'};
        getLCS(B,C);
    }

    public static void getLCS(char[] B, char[] C) {

        int[][] c = new int[B.length+1][B.length+1];
        int[][] s = new int[B.length+1][B.length+1];
        c[0][0] = 0;

        for (int i=1;i<B.length;i++) {
            for (int j=1;j<B.length;j++) { //{2,43,4,7,11,2,4}
                if (B[i]==C[j]) {
                    c[i][j] = 1 + c[i-1][j-1];
                }
                else {
                    c[i][j] = Math.max(c[i-1][j],c[i][j-1]);
                }
            }
        }
        System.out.println(c[B.length][B.length]);
    }

    public static void getLIS(int[] A) {
        // recurrence , c[n] = maxj c[j] + 1

        int[] c = new int[A.length];
        int[] s = new int[A.length];
        c[0] = 1;
        c[1] = 1;

        for (int i=1;i<A.length;i++) {
            int best = 1;
            int best_j = 0;
            for (int j=0;j<i;j++) { //{2,43,4,7,11,2,4}
                if (A[i]>A[j]) {
                    best = Math.max(best,c[j] + 1);
                    best_j = j;
                }
            }
            c[i] = best;
            s[i] = best_j;
        }
        for (int p=0;p<c.length;p++) {
            System.out.print(c[p]);
        }
        System.out.println();
        for (int p=0;p<c.length;p++) {
            System.out.print(s[p]);
        }
    }
}
