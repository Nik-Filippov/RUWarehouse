package warehouse;

/*
 *
 * This class implements a warehouse on a Hash Table like structure, 
 * where each entry of the table stores a priority queue. 
 * Due to your limited space, you are unable to simply rehash to get more space. 
 * However, you can use your priority queue structure to delete less popular items 
 * and keep the space constant.
 * 
 * @author Ishaan Ivaturi
 */ 
public class Warehouse {
    private Sector[] sectors;
    
    // Initializes every sector to an empty sector
    public Warehouse() {
        sectors = new Sector[10];

        for (int i = 0; i < 10; i++) {
            sectors[i] = new Sector();
        }
    }
    
    /**
     * Provided method, code the parts to add their behavior
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    public void addProduct(int id, String name, int stock, int day, int demand) {
        evictIfNeeded(id);
        addToEnd(id, name, stock, day, demand);
        fixHeap(id);
    }

    /**
     * Add a new product to the end of the correct sector
     * Requires proper use of the .add() method in the Sector class
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    private void addToEnd(int id, String name, int stock, int day, int demand) {
        // IMPLEMENT THIS METHOD
        Product newProd = new Product(id, name, stock, day, demand);
        int sectorId = Integer.parseInt((Integer.toString(id)).substring(Integer.toString(id).length() - 1));
        sectors[sectorId].add(newProd);
    }

    /**
     * Fix the heap structure of the sector, assuming the item was already added
     * Requires proper use of the .swim() and .getSize() methods in the Sector class
     * @param id The id of the item which was added
     */
    private void fixHeap(int id) {
        // IMPLEMENT THIS METHOD
        int sectorId = Integer.parseInt((Integer.toString(id)).substring(Integer.toString(id).length() - 1));
        if(sectors[sectorId].getSize() > 1){
            sectors[sectorId].swim(sectors[sectorId].getSize());
        }
    }

    /**
     * Delete the least popular item in the correct sector, only if its size is 5 while maintaining heap
     * Requires proper use of the .swap(), .deleteLast(), and .sink() methods in the Sector class
     * @param id The id of the item which is about to be added
     */
    private void evictIfNeeded(int id) {
       // IMPLEMENT THIS METHOD
       int sectorId = Integer.parseInt((Integer.toString(id)).substring(Integer.toString(id).length() - 1));
       if(sectors[sectorId].getSize() == 5){
            sectors[sectorId].swap(1, sectors[sectorId].getSize());
            sectors[sectorId].deleteLast();
            sectors[sectorId].sink(1);
       }
    }

    /**
     * Update the stock of some item by some amount
     * Requires proper use of the .getSize() and .get() methods in the Sector class
     * Requires proper use of the .updateStock() method in the Product class
     * @param id The id of the item to restock
     * @param amount The amount by which to update the stock
     */
    public void restockProduct(int id, int amount) {
        for(int i = 0; i < sectors.length; i++){
            for(int j = 0; j < sectors[i].getSize(); j++){
                if(sectors[i].get(j + 1).getId() == id){
                    sectors[i].get(j + 1).updateStock(sectors[i].get(j + 1).getStock() + amount);
                }
            }
        }
    }
    
    /**
     * Delete some arbitrary product while maintaining the heap structure in O(logn)
     * Requires proper use of the .getSize(), .get(), .swap(), .deleteLast(), .sink() and/or .swim() methods
     * Requires proper use of the .getId() method from the Product class
     * @param id The id of the product to delete
     */
    public void deleteProduct(int id) {
        for(int i = 0; i < sectors.length; i++){
            for(int j = 0; j < sectors[i].getSize(); j++){
                if(sectors[i].get(j + 1).getId() == id){
                    sectors[i].swap(j + 1, sectors[i].getSize());
                    sectors[i].deleteLast();
                    sectors[i].sink(j + 1);
                }
            }
        }
    }
    
    /**
     * Simulate a purchase order for some product
     * Requires proper use of the getSize(), sink(), get() methods in the Sector class
     * Requires proper use of the getId(), getStock(), setLastPurchaseDay(), updateStock(), updateDemand() methods
     * @param id The id of the purchased product
     * @param day The current day
     * @param amount The amount purchased
     */
    public void purchaseProduct(int id, int day, int amount) {
        for(int i = 0; i < sectors.length; i++){
            for(int j = 0; j < sectors[i].getSize(); j++){
                if(sectors[i].get(j + 1).getId() == id){
                    if(amount <= sectors[i].get(j + 1).getStock()){
                        sectors[i].get(j + 1).setLastPurchaseDay(day);
                        sectors[i].get(j + 1).updateStock(-amount);
                        sectors[i].get(j + 1).setDemand(sectors[i].get(j + 1).getDemand() + amount);
                        sectors[i].swim(j + 1);
                    }
                }
            }
        }
    }
    
    /**
     * Construct a better scheme to add a product, where empty spaces are always filled
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    public void betterAddProduct(int id, String name, int stock, int day, int demand) {
        int sectorId = Integer.parseInt((Integer.toString(id)).substring(Integer.toString(id).length() - 1));
        if(sectors[sectorId].getSize() == 5){
            boolean isProdAdded = false;
            for(int i = 0; i < sectors.length; i++){
                if(sectors[i].getSize() < 5){
                    int newId = Integer.parseInt((Integer.toString(id)).substring(0, Integer.toString(id).length() - 1) + Integer.toString(i));
                    addToEnd(newId, name, stock, day, demand);
                    fixHeap(newId);
                    isProdAdded = true;
                    break;
                }
            }
            if(!isProdAdded){
                addProduct(id, name, stock, day, demand);
            }
        }
        else{
            addProduct(id, name, stock, day, demand);
        }
    }

    /*
     * Returns the string representation of the warehouse
     */
    public String toString() {
        String warehouseString = "[\n";

        for (int i = 0; i < 10; i++) {
            warehouseString += "\t" + sectors[i].toString() + "\n";
        }
        
        return warehouseString + "]";
    }

    /*
     * Do not remove this method, it is used by Autolab
     */ 
    public Sector[] getSectors () {
        return sectors;
    }
}
