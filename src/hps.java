// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class hps {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("hps.in"));

        int n = in.nextInt();
        char[] predictedMoves = new char[n+1];

        int[] hoofWins = new int[n+1];
        int[] paperWins = new int[n+1];
        int[] scissorsWins = new int[n+1];

        for (int i = 1; i < n+1; i++) {
            predictedMoves[i] = in.next().charAt(0);

            scissorsWins[i] = scissorsWins[i-1];
            paperWins[i] = paperWins[i-1];
            hoofWins[i] = hoofWins[i-1];

            if (predictedMoves[i] == 'P') {
                scissorsWins[i] += 1;
            }

            else if (predictedMoves[i] == 'H') {
                paperWins[i] += 1;
            }

            else {
                hoofWins[i] += 1;
            }
        }

        in.close();

        int result = 0;

        for (int i = 1; i < n+1; i++) {
            // i is the stopping point not including it

            int begin = getMax(new int[] {hoofWins[i-1], paperWins[i-1], scissorsWins[i-1]});

            int hoofWinsAfterI = hoofWins[n] - hoofWins[i-1];
            int paperWinsAfterI = paperWins[n] - paperWins[i-1];
            int scissorsWinsAfterI = scissorsWins[n] - scissorsWins[i-1];

            int end = getMax(new int[] {hoofWinsAfterI, paperWinsAfterI, scissorsWinsAfterI});

            result = Math.max(result, begin+end);
        }

        PrintWriter out = new PrintWriter(new File("hps.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static int getMax(int[] inputArray){
        int maxValue = inputArray[0];
        for(int i=1;i < inputArray.length;i++){
            if(inputArray[i] > maxValue){
                maxValue = inputArray[i];
            }
        }
        return maxValue;
    }
  
 /*
 ANALYSIS
 
 */
}


