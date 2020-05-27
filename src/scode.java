// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class scode {

    private static int result = 0;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("scode.in"));
        String input = in.next();
        in.close();

        scoder(input);

        PrintWriter out = new PrintWriter(new File("scode.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static void scoder(String input) {
        if (input.length() == 2) return; // base case

        // smaller half is x / 2
        // bigger half is x - x/2

        String firsthalf1 = input.substring(0, input.length() / 2 + 1); // need to have 1 more than half
        String firsthalf2 = input.substring(input.length() / 2 + 1); // need to be one less than half1

        if (firsthalf1.substring(1).equals(firsthalf2)) {
            result++;
            scoder(firsthalf1);
        }

        if (firsthalf1.substring(0, firsthalf1.length() - 1).equals(firsthalf2)) {
            result++;
            scoder(firsthalf1);
        }

        // switch the string that had more characters

        String secondhalf1 = input.substring(0, input.length() / 2);
        String secondhalf2 = input.substring(input.length() / 2);

        if (secondhalf2.substring(1).equals(secondhalf1)) {
            result++;
            scoder(secondhalf2);
        }

        if (secondhalf2.substring(0, secondhalf2.length() - 1).equals(secondhalf1)) {
            result++;
            scoder(secondhalf2);
        }
    }
  
 /*
 ANALYSIS

 Consider half the letters rounded up from the front and the back

 If starting with last 3 letters --> have to be string with first letter chopped off

 recursive problem

 Keep breaking halfs down -- into 2 letters at the minimum

 Example:

 ABCDBCD

 Group 1: ABCD

 Is BCD the result result of chopping of first: (removing A to make BCD) Yes count 1 recursive count of ABCD
 result of chopping off last? (removing D to make ABC) No

 Group 2: DBCD
 Is ABC result of chopping of first: (removing D to make BCD) No
 result of Chopping off last: (removing D to make DBC) No

 Example:

 ABABA

 Group 1: ABA (talking half of the passed in string) --> now need to look at the other half which is BA

 I need to check if BA can be created in ABA

 Is BA the result of chopping off the first: (removing A to make BA) Yes, count 1 recursive count of ABA (This is all in one step)
 result of chopping off the last: (removing A to make AB) No

 Group 1a: ABA

 Is BA the result of chopping off A


Workflow:
Get the first half and the second half

With the first half check if you can make the second half by finding the halves themselves if so, then run recursion on that half

do the same on the second half
 */
}


