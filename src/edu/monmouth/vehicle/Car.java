package edu.monmouth.vehicle;

public class Car implements Vehicle
{
	private int engineSize;
	private PowerTypes powerSource;

	public Car(PowerTypes powerSource, int engineSize) throws CarException 
	{
		setPowerSource(powerSource);
		setEngineSize(engineSize);
	}

	public int getEngineSize() 
	{
		return engineSize;
	}

	public void setEngineSize(int engineSize) throws CarException 
	{
		// precondition: MINENGINESIZE <= engineSize <= MAXENGINESIZE
		if (engineSize < VehicleConstants.MINENGINESIZE || engineSize > VehicleConstants.MAXENGINESIZE) 
		{
			throw new CarException("Invalid engine size " + engineSize + ": must be between "
					+ VehicleConstants.MINENGINESIZE + " and " + VehicleConstants.MAXENGINESIZE);
		}
		this.engineSize = engineSize;
	}

	@Override
	public void go() 
	{
		System.out.println("Car is driving");
	}

	@Override
	public void stop() 
	{
		System.out.println("Car is stopping");
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
		return "Car [engineSize=" + engineSize + ", powerSource=" + powerSource + "]";
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
		Car other = (Car) obj;
		return engineSize == other.engineSize && powerSource == other.powerSource;
	}
}
