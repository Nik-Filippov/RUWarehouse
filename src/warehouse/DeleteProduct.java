package warehouse;

/*
 * Use this class to test the deleteProduct method.
 */ 
public class DeleteProduct {
    public static void main(String[] args) {
        StdIn.setFile("deleteproduct.in");
        StdOut.setFile("deleteproduct.out");
        Warehouse w = new Warehouse();
        while(StdIn.hasNextLine()){
            String input = StdIn.readLine();
            if(input.contains("add")){
                int firstSp = input.indexOf(" ");
                int lastSp = input.indexOf(" ", firstSp + 1);
                int day = Integer.parseInt(input.substring(firstSp + 1, lastSp));
                firstSp = lastSp;
                lastSp = input.indexOf(" ", firstSp + 1);
                int id = Integer.parseInt(input.substring(firstSp + 1, lastSp));
                firstSp = lastSp;
                lastSp = input.indexOf(" ", firstSp + 1);
                String name = input.substring(firstSp + 1, lastSp);
                firstSp = lastSp;
                lastSp = input.indexOf(" ", firstSp + 1);
                int stock = Integer.parseInt(input.substring(firstSp + 1, lastSp));
                firstSp = lastSp;
                int demand = Integer.parseInt(input.substring(firstSp + 1));
                w.addProduct(id, name, stock, day, demand);
            }
            else if(input.contains("delete")){
                int firstSp = input.indexOf(" ");
                int id = Integer.parseInt(input.substring(firstSp + 1));
                w.deleteProduct(id);
            }
        }
        StdOut.println(w);
    }
}
