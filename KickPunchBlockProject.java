package KickPunchBlockProject;

import java.util.Scanner;

public class KickPunchBlockProject
{
	// Creates scanner object
	static Scanner input = new Scanner(System.in);

	// Static because variables are not used in a constructor
	static String fighterName = "";
	static String opponentName = "";
	static String country = "";
	static String countryUpperCase = "";
	static boolean opponentHurt = false;
	static boolean fighterHurt = false;
	static boolean counterAttack = false;
	static int fighterHealth = 100;
	static int opponentHealth = 100;
	static int roundNumber = 1;
	static final int DAMAGE_MULTIPLIER = 2;
	static int damageRoll;
	static int fighterAction;
	static int opponentAction;

	
	
	

	public static void main(String[] args)
	{
		Introduction();
		PickCountry();
		Fight();
	}
	
	
	
	

	// Fight loop
	private static void Fight()
	{
		do
		{
			// Actions and base damage
			Actions();		
			
			// Fighter kicks opponent kicks
			if (fighterAction == 1 && opponentAction == 1)
			{
				PrintBorder();
				System.out.println("Announcer says, \"" + fighterName + " and " + opponentName + " smash their shins together!\"");
				opponentHurt = true;
				fighterHurt = true;
			}
			
			// Fighter kicks opponent punches
			if (fighterAction == 1 && opponentAction == 2)
			{
				PrintBorder();
				System.out.println("Announcer says, \"" + opponentName + " gives a big uppercut to " + fighterName + "!\"");
				fighterHurt = true;
				counterAttack = true;
			}
			
			// Fighter kicks opponent blocks
			if (fighterAction == 1 && opponentAction == 3)
			{
				PrintBorder();
				System.out.println("Announcer says, \"" + opponentName + " takes a big kick to the ribs from " + fighterName + "!\"");
				opponentHurt = true;
				counterAttack = true;
			}
			
			// Fighter punches opponent kicks
			if (fighterAction == 2 && opponentAction == 1)
			{
				PrintBorder();
				System.out.println("Announcer says, \"" + fighterName + " gives a big uppercut to  " + opponentName + "!");
				opponentHurt = true;
				counterAttack = true;
			}
			
			// Fighter punches opponent punches
			if (fighterAction == 2 && opponentAction == 2)
			{
				PrintBorder();
				System.out.println("Announcer says, \"" + fighterName + " and " + opponentName + " exchange quick jabs!\"");
				opponentHurt = true;
				fighterHurt = true;
			}

			// Fighter punches opponent blocks
			if (fighterAction == 2 && opponentAction == 3)
			{
				PrintBorder();
				System.out.println("Announcer says, \"" + opponentName + " parries the punch and deals big damage to " + fighterName + "!\"");
				fighterHurt = true;
				counterAttack = true;
			}
			
			// Fighter blocks opponent kicks
			if (fighterAction == 3 && opponentAction == 1)
			{
				PrintBorder();
				System.out.println("Announcer says, \"" + fighterName + " takes a big kick to the ribs from " + opponentName + "!\"");
				fighterHurt = true;
				counterAttack = true;
			}

			// Fighter blocks opponent punches
			if (fighterAction == 3 && opponentAction == 2)
			{
				PrintBorder();
				System.out.println("Announcer says, \"" + fighterName + " parries the punch and deals big damage to " + opponentName + "!\"");
				opponentHurt = true;
				counterAttack = true;
			}
			
			// Both block
			if (fighterAction == 3 && opponentAction == 3)
			{
				PrintBorder();
				System.out.println("Announcer says, \"Both fighters stand still, waiting for the other to make a move!\"");
			}

			// Update health then check if health is less than zero
			UpdateHealth();
			CheckForKnockout();
			
			// Reset for next round
			opponentHurt = false;
			fighterHurt = false;
			counterAttack = false;
			roundNumber++;

		} while (fighterHealth > 0 && opponentHealth > 0);
	}

		
	// Prints border
	private static void PrintBorder()
	{	
		System.out.println("================================================================================");
	}

	// Check for knock out
	private static void CheckForKnockout()
	{
		// Check fighter health
		if (fighterHealth <= 0)
		{
			System.out.println("*DING* *DING* *DING* The match is over in round number " + roundNumber + "!!\n" + fighterName + " was knocked out, and " + opponentName + " still had " + opponentHealth + " health left! \nBetter luck next time!!!");
		}
			
		// Check opponent heath
		if (opponentHealth <= 0)
		{
			System.out.println("*DING* *DING* *DING* The match is over in round number " + roundNumber + "!! \n" + fighterName + " knocked out " + opponentName + "! \nCONGRATULATIONS!!!");
		}
	}

	// Update health
	private static void UpdateHealth()
	{
		if (counterAttack)
		{
			if (fighterHurt) fighterHealth -= damageRoll * DAMAGE_MULTIPLIER;
			if (opponentHurt) opponentHealth -= damageRoll * DAMAGE_MULTIPLIER;
		} 
		else 
		{
			if (fighterHurt) fighterHealth -= damageRoll;
			if (opponentHurt) opponentHealth -= damageRoll;
		}
	}
	
	// Calculate actions and damage
	private static void Actions()
	{
		System.out.print("Round Number " + roundNumber + "!\n" + fighterName + " has " + fighterHealth + " health left, and " + opponentName + " has " + opponentHealth + " health left.\nEnter 1 to kick, 2 to punch, 3 to block: ");
		
		// Base damage for both
		damageRoll = (int) (Math.random() * 5 + 5);	
		
		// User and opponent pick actions
		opponentAction = (int) (Math.random() * 3 + 1);
		fighterAction = input.nextInt();
		while (fighterAction < 1 || fighterAction > 3 ) // Test input
		{
			PrintBorder();
			System.out.print("What are you trying to do???\nYou can only enter 1 to kick, 2 to punch, 3 to block: ");
			fighterAction = input.nextInt();
		}
	}
	
	// Determines opponent's name
	private static void PickCountry()
	{	
		do
		{
			PrintBorder();
			System.out.print("Which country would you like to fight? USA, Russia, Canada, Germany, Wakanda \nEnter the country name here: ");
			country = input.nextLine();
			countryUpperCase = country.toUpperCase(); // Method returns a NEW string, doesn't change old string

			switch (countryUpperCase)
			{
			case "USA":
				opponentName = "Rocky";
				break;
			case "RUSSIA":
				opponentName = "Drago";
				break;
			case "CANADA":
				opponentName = "Horton";
				break;
			case "GERMANY":
				opponentName = "Heltzenburg";
				break;
			case "WAKANDA":
				opponentName = "Black Panther";
				break;
			}
		} while (opponentName == "");
	}

	// Introduction
	private static void Introduction()
	{
		System.out.print("Welcome to Kick Punch Block!! \nEnter your fighter name: ");
		fighterName = input.nextLine();
	}

}