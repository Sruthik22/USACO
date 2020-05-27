// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class citystate {

    private static HashMap<String, Integer> cityState;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("citystate.in"));
        int n = in.nextInt();

        cityState = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String city = in.next();

            String cityCode = city.substring(0, 2);

            String stateCode = in.next();

            if (!cityCode.equals(stateCode)) {
                cityState.putIfAbsent(cityCode+stateCode, 0);

                cityState.put(cityCode+stateCode, cityState.get(cityCode+stateCode) + 1);
            }

        }

        in.close();

        int result = cities() / 2;

        PrintWriter out = new PrintWriter(new File("citystate.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static int cities() {

        int pairs = 0;

        for (Map.Entry<String,Integer> entry : cityState.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();

            String lookingFor = key.substring(2) + key.substring(0, 2);

            if (cityState.get(lookingFor) != null) {
                pairs += value * cityState.get(lookingFor);
            }


        }

        return pairs;
    }
  
 /*
 ANALYSIS

 MI FL  FL MI
 DA TX  TX DA
 FL MI  MI FL
 CL SC  SC CL
 BO MA  MA BO
 OR FL  FL OR


 look for the value as a key, and check for both sides

 when checking, go down 1 entry each time (to not repeat past checks, and to not check against own entries)
 */
}


