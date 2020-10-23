public class Dungeon {

    // Instance Variables
    Room[][] rooms;
    
    // Constructor
    public Dungeon() {
        rooms = new Room[5][5];
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                rooms[x][y] = new Room();
            }
        }
    }

    // Room Getter
    public Room getRoom(int x, int y) {
        return rooms[x][y];
    }

    // Room Setter
    public void setRoom(int x, int y, String newObject) {
        rooms[x][y].setObject(newObject);
    }

    // Displayer
    public void display(Player you) {

        // Initialize
        int playerX = you.getX();
        int playerY = you.getY();
        boolean playerAlive = !you.isDead();

        // Big 'Ol Loop
        for (int y = 0; y < 5; y++) {

            // Line Top
            System.out.println("+----+----+----+----+----+");

            // Cells
            System.out.print("|");
            for (int x = 0; x < 5; x++) {

                // Player Side
                System.out.print(" ");
                if (playerX == x && playerY == y) {
                    if (playerAlive) { System.out.print("@"); }
                    else { System.out.print("*"); }
                } else {
                    System.out.print(" ");
                }

                // Object Side
                if (x != 0 || y != 0) {
                    System.out.print(rooms[x][y].getObject());
                } else {
                    System.out.print("]");
                }
                System.out.print(" |");

            }

            // Advance
            System.out.println();

        }
        
        // Cap Off
        System.out.println("+----+----+----+----+----+");
    }
    
}