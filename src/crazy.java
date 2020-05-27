// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class crazy {

    static int[][] field;

    static int counter = 0;
    static int[][] fence;
    static int[][] cows;
    static int number;
    static int cowsLeft;
    static int Xbuffer;
    static int Ybuffer;


    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("crazy.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crazy.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());

        number = Integer.parseInt(st.nextToken());
        cowsLeft = Integer.parseInt(st.nextToken());
        fence = new int[number][4];
        cows  = new int[cowsLeft][2];
        int maxX = 0;
        int maxY = 0;
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for(int x = 0;x<number;x++){
            st = new StringTokenizer(f.readLine());
            fence[x][0] = Integer.parseInt(st.nextToken());
            if(maxX<fence[x][0]) maxX = fence[x][0];
            if(minX>fence[x][0]) minX = fence[x][0];
            fence[x][1] = Integer.parseInt(st.nextToken());
            if(maxY<fence[x][1]) maxY = fence[x][1];
            if(minY>fence[x][1]) minY = fence[x][1];


            for(int a = 2;a<4;a++){
                fence[x][a] = Integer.parseInt(st.nextToken());
            }
        }

        for(int x = 0;x<cowsLeft;x++){
            st = new StringTokenizer(f.readLine());

            cows[x][0] = Integer.parseInt(st.nextToken());
            if(maxX<cows[x][0]) maxX = cows[x][0];
            if(minX>cows[x][0]) minX = cows[x][0];
            cows[x][1] = Integer.parseInt(st.nextToken());
            if(maxY<cows[x][1]) maxY = cows[x][1];
            if(minY>cows[x][1]) minY = cows[x][1];

        }

        Xbuffer = maxX-minX;
        Ybuffer = maxY-minY;
        field = new int[Ybuffer+1][Xbuffer+1];

        for(int x = 0;x<cowsLeft;x++){
            field[cows[x][1]-minY][cows[x][0]-minX] = 1;
        }

        for(int x = 0;x<number;x++){
            fence[x][0]-=minX;
            fence[x][1]-=minY;
            fence[x][2]-=minX;
            fence[x][3]-=minY;
        }

        for(int x = 0;x<number;x++){
            if(fence[x][0]==fence[x][2]){
                for(int y = fence[x][1];y<=fence[x][3];y++){
                    field[y][fence[x][0]]=2;
                }
                for(int y = fence[x][3];y<=fence[x][1];y++){
                    field[y][fence[x][0]]=2;
                }
            }
            else if(fence[x][1]==fence[x][3]){
                for(int a = fence[x][0];a<=fence[x][2];a++){
                    field[fence[x][1]][a]=2;
                }
                for(int a = fence[x][2];a<=fence[x][0];a++){
                    field[fence[x][1]][a]=2;
                }
            }
        }

        Point holder = empty();
        int max = 0;
        while(holder!=null){
            counter = 0;
            fill(holder.x,holder.y);
            if(max<counter) max = counter;
            holder = empty();
        }
        out.println(max+1);


        out.close();
        System.exit(0);
    }
    static Point empty(){
        for(int x = 0;x<Xbuffer;x++){
            for(int y = 0; y<Ybuffer;y++){
                if(field[y][x]==0||field[y][x]==1) return new Point(x, y);
            }
        }
        return null;
    }


    static void fill(int x, int y){

        if(y<0||x<0||y>=Ybuffer||x>=Xbuffer) return;
        if(field[y][x]==2) return;
        if(field[y][x]==1) counter++;

        field[y][x] = 2;

        fill(x, y-1);
        fill(x, y+1);
        fill(x+1, y);
        fill(x-1, y);
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}


