 void main() {
        IO.println("FindMax");
        int a = 1;
        int b = 5;
        int c = 10;
        int max = 0;
        if ((a > b) && (a > c)) {
            max = a;
            IO.println("Max = " +max);
        } else if ((b > a) && (b > c)) {
            max = b;
            IO.println("Max = " +max);
        } else {
            max = c;
            IO.println("Max = " +max);
        }
}