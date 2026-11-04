package edu.monmouth.vehicle;

import java.util.Objects;

public class Plane implements Vehicle
{
	private String model;
	private PowerTypes powerSource;
	
	public Plane(String f, PowerTypes p)
	{
		setModel(f);
		setPowerSource(p);
	}
	
	public PowerTypes getPowerSource()
	{
		return powerSource;
	}
	
	
	public void setPowerSource(PowerTypes p)
	{
		powerSource = p;
	}
	public String getModel()
	{
		return model;
	}
	public void setModel(String p)
	{
		 model = p;
	}
	
	@Override
	public PowerTypes powerBy()
	{
		return powerSource;
	}
	
	@Override
	public void go()
	{
		System.out.println("The Plane is taking off");
	}
	
	@Override
	public void stop()
	{
		System.out.println("The plane is landing");
	}
	
	@Override
	public String toString()
	{
		return "Plane [Model=" + model + ", powerSource=" + powerSource + "]";
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
		Plane other = (Plane) obj;
		return Objects.equals(model, other.model) && powerSource == other.powerSource;
	}
}
