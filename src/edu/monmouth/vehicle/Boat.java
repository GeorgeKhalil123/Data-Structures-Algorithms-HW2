package edu.monmouth.vehicle;

public class Boat implements Vehicle 
{
	private int boatLength;
	private PowerTypes powerSource;

	public Boat(PowerTypes powerSource, int boatLength) throws BoatException
	{
		setPowerSource(powerSource);
		setBoatLength(boatLength);
	}

	public int getBoatLength() 
	{
		return boatLength;
	}

	public void setBoatLength(int boatLength) throws BoatException 
	{
		// precondition: MINLENGTH <= boatLength <= MAXLENGTH
		if (boatLength < VehicleConstants.MINLENGTH || boatLength > VehicleConstants.MAXLENGTH) 
		{
			throw new BoatException("Invalid boat length " + boatLength + ": must be between "
					+ VehicleConstants.MINLENGTH + " and " + VehicleConstants.MAXLENGTH);
		}
		this.boatLength = boatLength;
	}

	@Override
	public void go() 
	{
		System.out.println("Boat is sailing");
	}

	@Override
	public void stop() 
	{
		System.out.println("Boat is stopping");
	}

	@Override
	public PowerTypes powerBy() 
	{
		return powerSource;
	}

	public void setPowerSource(PowerTypes powerSource) 
	{
		this.powerSource = powerSource;
	}

	public PowerTypes getPowerSource() 
	{
		return powerSource;
	}

	@Override
	public String toString() 
	{
		return "Boat [boatLength=" + boatLength + ", powerSource=" + powerSource + "]";
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null || getClass() != obj.getClass())
		{
			return false;
		}
		Boat other = (Boat) obj;
		return boatLength == other.boatLength && powerSource == other.powerSource;
	}
}
