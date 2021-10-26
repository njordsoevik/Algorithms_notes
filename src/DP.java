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

    public static void main(String[] args) {
        // get rod
//        int[] p = new int[]{2, 1, 6, 8};
//        int n = 4;
//        getRod(p, n);

        // get trials
        //System.out.println(getLadderTrials(5,3));


        // jars
        System.out.println(getDrops(2,5));


    }
}
