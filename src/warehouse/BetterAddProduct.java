package warehouse;

/*
 * Use this class to test the betterAddProduct method.
 */ 
public class BetterAddProduct {
    public static void main(String[] args) {
        StdIn.setFile("betteraddproduct.in");
        StdOut.setFile("betteraddproduct.out");
        Warehouse w = new Warehouse();
        int i = 0;
        int numProducts = 0;
        int day = 0;
        int id = 0;
        String name = "";
        int stock = 0;
        int demand = 0;
        while(StdIn.hasNextLine()){
            String input = StdIn.readLine();
            if (i < 1){
                numProducts = Integer.parseInt(input);
            }
            else{
                int firstSp = -1;
                int lastSp = input.indexOf(" ");
                day = Integer.parseInt(input.substring(firstSp + 1, lastSp));
                firstSp = input.indexOf(" ");
                lastSp = input.indexOf(" ", firstSp + 1);
                id = Integer.parseInt(input.substring(firstSp + 1, lastSp));
                firstSp = lastSp;
                lastSp = lastSp = input.indexOf(" ", firstSp + 1);
                name = input.substring(firstSp + 1, lastSp);
                firstSp = lastSp;
                lastSp = lastSp = input.indexOf(" ", firstSp + 1);
                stock = Integer.parseInt(input.substring(firstSp + 1, lastSp));
                firstSp = lastSp;
                demand = Integer.parseInt(input.substring(firstSp + 1));
                w.betterAddProduct(id, name, stock, day, demand);
            }
            i++;
        }
        StdOut.println(w);
    }
}
