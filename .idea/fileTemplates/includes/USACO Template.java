static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }
    
    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

public static void main(String[] args) throws Exception {
    in = new StreamTokenizer(new BufferedReader(new FileReader("${NAME}.in")));

    int result = 0;
    PrintWriter out = new PrintWriter(new File("${NAME}.out"));
    System.out.println(result);
    out.println(result);
    out.close();
  }