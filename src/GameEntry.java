import java.util.InputMismatchException;
import java.util.Scanner;


public class GameEntry {

	private static Zone startingZone;
	private static Player mainPlayer = new Player();
	
	private static void setupWorld() {
		startingZone = new Zone();
		mainPlayer = new Player();
		
		CustomRoom bedroom = new CustomRoom();
		bedroom.setDescription("An alien spaceship appears to have crashed into your bedroom wall");
		bedroom.setLocation("Your Bedroom");
		startingZone.getRooms().add(bedroom);
		mainPlayer.setCurrentRoom(bedroom);	
		
		CustomRoom Bathroom = new CustomRoom();
		Bathroom.setLocation("Alien Bathroom");
		Bathroom.setDescription("A Broken window opens up into what appears to be a bathroom inside the alien spaceship");
		bedroom.addRoom("Climb in Spaceship window", Bathroom);
		
		CustomRoom hallway = new CustomRoom();
		hallway.setLocation("Alien Hallway");
		hallway.setDescription("A hallway much longer than the ship appears runs into the distance");
		Bathroom.addRoom("Wash hands, leave bathroom", hallway);
		hallway.addRoom("Bathroom door", Bathroom);
		
		CustomRoom labRoom = new CustomRoom();
		labRoom.setLocation("Alien Laboratory");
		labRoom.setDescription("Some sort of scary laboratorium!");
		hallway.addRoom("Glowing Door", labRoom);
		
		CustomRoom armory = new CustomRoom();
		armory.setLocation("Alien Armory");
		armory.setDescription("An awesome alien weapons locker!");
		armory.getItems().add(new Crowbar());
		armory.getEnemies().add(new BlueAlien());
		armory.addRoom("Metal Door", hallway);
		hallway.addRoom("Metal Door", armory);
		
		CustomRoom jail = new CustomRoom();
		jail.setLocation("Alien Jail");
		jail.setDescription("Like an idiot, you walked yourself into a jail cell, which has closed on you.");
		armory.addRoom("Metal Bars", jail);	
		
		
	}	
	
	private static void gameLoop() {
		boolean quit = false;
		while (!quit) {
			boolean hasPath = false, hasEnemies = false, hasItems = false;
			
			System.out.println("You are standing in " + mainPlayer.getCurrentRoom().getLocation());
			System.out.println("\n" + mainPlayer.getCurrentRoom().getDescription());
			if ( mainPlayer.getCurrentRoom().getEnemies().size() > 0 ) {
				System.out.println("There are enemies here!");
				hasEnemies = true;
				for ( Enemy e: mainPlayer.getCurrentRoom().getEnemies() )
				{
					System.out.println(e.getName() );
					System.out.println(e.getDescription() );
				}
			}
			if ( mainPlayer.getCurrentRoom().getItems().size() > 0 ) {
				System.out.println("There is stuff here!");
				hasItems = true;
				for ( HoldableObject e: mainPlayer.getCurrentRoom().getItems() )
				{
					System.out.println(e.getName() );
					System.out.println(e.getDescription() );
				}
			}
			if ( mainPlayer.getCurrentRoom().getAttachedRooms().size() > 0 ) {
				hasPath = true;
				System.out.println("There are " + mainPlayer.getCurrentRoom().getAttachedRooms().size() + " nearby paths");
				for ( String s: mainPlayer.getCurrentRoom().getAttachedRooms().keySet() )
				{
					System.out.println("Path: " + s);
				}
			}
			else {
				System.out.println("This room is a dead end! You are trapped!");
				System.out.println("The End");
				quit = true;
			}
			
			// Print main menu
			boolean choiceMade = false;
			while (!choiceMade && !quit) {
				System.out.println("What would you like to do?");
				if ( hasPath ) System.out.println("W: Walk down a path");
				if ( hasEnemies ) System.out.println("A: Attack an enemy with your " + mainPlayer.getCurretWeapon().getName());
				if ( hasItems ) System.out.println("P: Pick up an Item");
				System.out.println("\nYour Choice: ");
				
				Scanner s = new Scanner(System.in);
				String choice = s.nextLine();
				if ( choice.length() > 0 && choice.toLowerCase().charAt(0) == 'q') {
					quit = true;
					choiceMade = true;
					continue;
				}
				
				if ( hasPath && choice.length() > 0 && choice.toLowerCase().charAt(0) == 'w') {
					// List paths and find route
					System.out.println("What route would you like to take?");
					int counter = 1;
					for ( String roomname: mainPlayer.getCurrentRoom().getAttachedRooms().keySet() ) {
						System.out.println(counter + ": " + roomname);
						counter++;
					}					
					System.out.println("Route: ");
					try {
						int routeChoice = s.nextInt();
						if ( routeChoice - 1 > mainPlayer.getCurrentRoom().getAttachedRooms().keySet().size() )
							continue;
						mainPlayer.setCurrentRoom((Room)mainPlayer.getCurrentRoom().getAttachedRooms().get(mainPlayer.getCurrentRoom().getAttachedRooms().keySet().toArray()[routeChoice-1]));
						choiceMade = true;
					}
					catch ( InputMismatchException e ) {}					
				}
				if ( hasEnemies && choice.length() > 0 && choice.toLowerCase().charAt(0) == 'a') {
					// List Enemies and attack
					System.out.println("What would you like to attack?");
					int counter = 1;
					for ( Enemy e: mainPlayer.getCurrentRoom().getEnemies() ) {
						System.out.println(counter + ": " + e.getName());
						counter++;
					}					
					System.out.println("Enemy: ");
					try {
						int enemyChoice = s.nextInt();
						if ( enemyChoice > mainPlayer.getCurrentRoom().getEnemies().size() )
							continue;
						Enemy e = mainPlayer.getCurrentRoom().getEnemies().get(enemyChoice-1);
						int dam = mainPlayer.getCurretWeapon().getDamage(e);
						e.setHealth(e.getHealth()-dam);
						System.out.println("You hit the " + e.getName() + " for " + dam + " Damage!");					
						choiceMade = true;
					}
					catch ( InputMismatchException e ) {}	
				}
				if ( hasItems && choice.length() > 0 && choice.toLowerCase().charAt(0) == 'p') {
					// List items and pick up
				}
								
			}
			
		}
	}
	
	public static void main(String[] args) {
		setupWorld();
		gameLoop();
    // Wait for one more enter-press after exiting, to keep the console open
    System.in.read();
	}

}
