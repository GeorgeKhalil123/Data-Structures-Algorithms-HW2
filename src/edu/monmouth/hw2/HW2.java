package edu.monmouth.hw2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.monmouth.vehicle.Boat;
import edu.monmouth.vehicle.BoatException;
import edu.monmouth.vehicle.Car;
import edu.monmouth.vehicle.CarException;
import edu.monmouth.vehicle.Plane;
import edu.monmouth.vehicle.PowerTypes;
import edu.monmouth.vehicle.Vehicle;

public class HW2
{
	public static void main(String[] args)
	{
		initialize(args);

		List<String> strings = new ArrayList<>();
		List<Vehicle> vehicles = new LinkedList<>();

		System.out.println("String list isEmpty before reading file: " + strings.isEmpty());
		System.out.println("Vehicle list isEmpty before reading file: " + vehicles.isEmpty());

		System.out.println("\n--- Reading " + args[HW2Constants.STRINGFILEARG] + " ---");
		readStrings(args[HW2Constants.STRINGFILEARG], strings);
		System.out.println("Strings added: " + strings.size());

		System.out.println("\n--- Reading " + args[HW2Constants.VEHICLEFILEARG] + " ---");
		readVehicles(args[HW2Constants.VEHICLEFILEARG], vehicles);
		System.out.println("Vehicles added: " + vehicles.size());

		System.out.println("\n===== String List =====");
		exerciseList(strings, "Added String");

		System.out.println("\n===== Vehicle List =====");
		exerciseList(vehicles, new Plane("Boeing350", PowerTypes.DEISEL));
		exerciseContains(vehicles);
	}

	// verify command line arguments and redirect STDOUT & STDERR to the file named in the first argument
	private static void initialize(String[] args)
	{
		if (args.length != HW2Constants.REQUIREDARGS)
		{
			System.err.println("Expected " + HW2Constants.REQUIREDARGS + " arguments, received " + args.length
					+ ". Usage: HW2 <output file> <strings file> <vehicles file>");
			System.exit(HW2Constants.BADARGS);
		}
		try
		{
			PrintStream redirectStream = new PrintStream(args[HW2Constants.OUTPUTFILEARG]);
			System.setOut(redirectStream);
			System.setErr(redirectStream);
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Cannot redirect stderr and stdout: " + e.getMessage());
			e.printStackTrace();
			System.exit(HW2Constants.BADREDIRECT);
		}
	}

	private static void readStrings(String fileName, List<String> strings)
	{
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
		{
			String line;
			while ((line = reader.readLine()) != null)
			{
				strings.add(line);
			}
		}
		catch (IOException e)
		{
			reportFileError(fileName, e);
		}
	}

	private static void readVehicles(String fileName, List<Vehicle> vehicles)
	{
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
		{
			String line;
			int lineNumber = 0;
			while ((line = reader.readLine()) != null)
			{
				lineNumber++;
				try
				{
					Vehicle vehicle = createVehicle(line);
					vehicles.add(vehicle);
					System.out.println("Line " + lineNumber + " added: " + vehicle);
				}
				catch (BoatException | CarException | IllegalArgumentException e)
				{
					// NumberFormatException is an IllegalArgumentException
					System.err.println("Line " + lineNumber + " discarded \"" + line + "\": " + e.getMessage());
				}
			}
		}
		catch (IOException e)
		{
			reportFileError(fileName, e);
		}
	}

	// values are passed as is: the parsers and the Vehicle constructors reject anything invalid
	private static Vehicle createVehicle(String line) throws BoatException, CarException
	{
		// -1 keeps trailing empty fields, so "c,GASOLINE,4," counts as 4 fields rather than 3
		String[] fields = line.split(HW2Constants.SEPARATOR, -1);
		if (fields.length != HW2Constants.VEHICLEFIELDS)
		{
			throw new IllegalArgumentException("Expected " + HW2Constants.VEHICLEFIELDS + " fields, found " + fields.length);
		}

		String type = fields[HW2Constants.TYPEFIELD];
		PowerTypes power = PowerTypes.valueOf(fields[HW2Constants.POWERFIELD]);
		int size = Integer.parseInt(fields[HW2Constants.SIZEFIELD]);

		if (type.equals(HW2Constants.CARTYPE))
		{
			return new Car(power, size);
		}
		if (type.equals(HW2Constants.BOATTYPE))
		{
			return new Boat(power, size);
		}
		throw new IllegalArgumentException("Unknown vehicle type \"" + type + "\"");
	}

	private static void reportFileError(String fileName, IOException e)
	{
		System.err.println("Cannot read " + fileName + ": " + e.getMessage());
		e.printStackTrace();
		System.exit(HW2Constants.BADINPUTFILE);
	}

	// isEmpty, remove (by index), add and iterator
	private static <T> void exerciseList(List<T> list, T elementToAdd)
	{
		System.out.println("isEmpty: " + list.isEmpty());

		System.out.println("\nIterating over the list before remove:");
		printList(list);

		try
		{
			T removed = list.remove(HW2Constants.REMOVEINDEX);
			System.out.println("\nremove(" + HW2Constants.REMOVEINDEX + ") removed: " + removed);
		}
		catch (IndexOutOfBoundsException e)
		{
			System.err.println("\nCannot remove index " + HW2Constants.REMOVEINDEX + ": " + e.getMessage());
		}
		System.out.println("Iterating over the list after remove:");
		printList(list);

		list.add(elementToAdd);
		System.out.println("\nadd inserted: " + elementToAdd);
		System.out.println("Iterating over the list after add:");
		printList(list);
	}

	private static void printList(List<?> list)
	{
		Iterator<?> iterator = list.iterator();
		while (iterator.hasNext())
		{
			System.out.println("  " + iterator.next());
		}
	}

	// the objects searched for are newly constructed, so a match can only come from equals()
	private static void exerciseContains(List<Vehicle> vehicles)
	{
		try
		{
			Vehicle inList = new Car(PowerTypes.FUELOIL, 6);
			Vehicle notInList = new Car(PowerTypes.ELECTRICITY, 6);

			System.out.println("\ncontains(" + inList + "): " + vehicles.contains(inList));
			System.out.println("contains(" + notInList + "): " + vehicles.contains(notInList));
		}
		catch (CarException e)
		{
			System.err.println("Cannot create Car for contains: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
