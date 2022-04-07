package warehouse;

/*
 * Use this class to put it all together.
 */ 
public class Everything {
    public static void main(String[] args) {
        StdIn.setFile("everything.in");
        StdOut.setFile("everything.out");
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
            else if(input.contains("purchase")){
                int firstSp = input.indexOf(" ");
                int lastSp = input.indexOf(" ", firstSp + 1);
                int day = Integer.parseInt(input.substring(firstSp + 1, lastSp));
                firstSp = lastSp;
                lastSp = input.indexOf(" ", firstSp + 1);
                int id = Integer.parseInt(input.substring(firstSp + 1, lastSp));
                firstSp = lastSp;
                lastSp = input.indexOf(" ", firstSp + 1);
                int amount = Integer.parseInt(input.substring(firstSp + 1));
                w.purchaseProduct(id, day, amount);
            }
            else if(input.contains("delete")){
                int firstSp = input.indexOf(" ");
                int id = Integer.parseInt(input.substring(firstSp + 1));
                w.deleteProduct(id);
            }
            else if(input.contains("restock")){
                int firstSp = input.indexOf(" ");
                int lastSp = lastSp = input.indexOf(" ", firstSp + 1);
                int id = Integer.parseInt(input.substring(firstSp + 1, lastSp));
                firstSp = lastSp;
                int amount = Integer.parseInt(input.substring(firstSp + 1));
                w.restockProduct(id, amount);
            }
        }
        StdOut.println(w);
    }
}
