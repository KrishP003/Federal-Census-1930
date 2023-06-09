import java.text.DecimalFormat;
public class Citizen implements Comparable<Citizen>
{
	private String first;
	private String last;
	private String streetName;
	private int streetNumber;
	private String relation;
	private String rentOwn;
	private double valueProp;
	private String gender;
	private double age;
	private String maritalStatus;
	private int ageFirstMarriage;
	private boolean attendSchool;
	private boolean canRead;
	private String birthplace;
	private String fathersBirthplace;
	private String mothersBirthplace;
	private String motherTongue;
	private int yearImmigrated;
	private String occupation;
	private String industry;
	private String transcibedRemarks;

	public Citizen(String first, String last, String streetName, String streetNumber, String relation, String rentOwn, String valueProp, String gender, String age, String maritalStatus, String ageFirstMarriage, String attendSchool, String canRead, String birthplace, String fathersBirthplace, String mothersBirthplace, String mothertongue, String yearImmigrated, String occupation, String industry, String transcibedRemarks)
	{
		this.first=first;
		this.last=last;
		this.streetName=streetName;
		try
		{
			this.streetNumber = Integer.parseInt(streetNumber);
		}catch(NumberFormatException e)
		{
			this.streetNumber = -1;
		}
		this.relation=relation;
		this.rentOwn=rentOwn.substring(0,1);

		if(valueProp.charAt(0) == '$')
			valueProp = valueProp.substring(1);
		try
		{
			this.valueProp = Double.parseDouble(valueProp);
		}catch(NumberFormatException e)
		{
			if(valueProp.contains("/"))
			{
				String whole = valueProp.substring(0, valueProp.indexOf(" "));
				String numerator = valueProp.substring(valueProp.indexOf(" ")+ 1, valueProp.indexOf("/"));
				String denominator = valueProp.substring(valueProp.indexOf("/") + 1);
				this.valueProp = Double.parseDouble(whole) + Double.parseDouble(numerator) / Double.parseDouble(denominator);
			}
		}
		this.gender=gender;
		try
		{
			this.age = Double.parseDouble(age);
		}catch(NumberFormatException e)
		{
			if(age.charAt(0) == '.' || age.equals("un"))
				this.age -= 1;
			else if(age.charAt(1) == ' ' && age.contains("/"))
			{
				String whole = age.substring(0, age.indexOf(" "));
				double decimal;
				if(age.substring(age.indexOf(" ") +  1, age.indexOf("/")).contains("*"))
					decimal = 0.5;
				else
				{
					String numerator = age.substring(age.indexOf(" ") +  1, age.indexOf("/"));
					String denominator = age.substring(age.indexOf("/") + 1);
					decimal = Double.parseDouble(numerator) / Double.parseDouble(denominator);
				}
				this.age = Double.parseDouble(whole) + decimal;
			}
			else if(age.contains("*"))
			{
				this.age = Double.parseDouble(age.substring(0, age.indexOf("*")));
			}
			else
			{
				String numerator = age.substring(0, age.indexOf("/"));
				String denominator = age.substring(age.indexOf("/") + 1);
				this.age = Double.parseDouble(numerator) / Double.parseDouble(denominator);
			}
		}
		this.maritalStatus=maritalStatus;
		try
		{
			this.ageFirstMarriage = Integer.parseInt(ageFirstMarriage);
		}catch(NumberFormatException e)
		{
			this.ageFirstMarriage = -1;
		}

		this.attendSchool = false;
		if(attendSchool.equalsIgnoreCase("Yes"))
			this.attendSchool=true;

		this.canRead = false;
		if(canRead.equalsIgnoreCase("Yes"))
			this.canRead = true;

		this.birthplace=birthplace;
		this.fathersBirthplace=fathersBirthplace;
		this.mothersBirthplace=mothersBirthplace;
		this.motherTongue=motherTongue;

		try
		{
			this.yearImmigrated = Integer.parseInt(yearImmigrated);
		}catch(NumberFormatException e)
		{
			this.yearImmigrated = -1;
		}
		this.occupation=occupation.substring(0,1).toUpperCase() + occupation.substring(1).toLowerCase();
		this.industry=industry;
		this.transcibedRemarks=transcibedRemarks;
	}

	public int compareTo(Citizen other)
	{
		if(getFirstName().compareTo(other.getFirstName()) != 0)
			return getFirstName().compareTo(other.getFirstName());
		else if(getLastName().compareTo(other.getLastName()) != 0)
			return getLastName().compareTo(other.getLastName());
		else if(getStreetName().compareTo(other.getStreetName()) != 0)
			return getStreetName().compareTo(other.getStreetName());
		else if(getStreetNumber() < other.getStreetNumber())
			return -1;
		else if(getStreetNumber() > other.getStreetNumber())
			return 1;
		else if(getRelation().compareTo(other.getRelation()) != 0)
			return getRelation().compareTo(other.getRelation());
		else if(getRentOwn().compareTo(other.getRentOwn()) != 0)
			return getRentOwn().compareTo(other.getRentOwn());
		else if(getValueProp() < other.getValueProp())
			return -1;
		else if(getValueProp() > other.getValueProp())
			return 1;
		else if(getGender().compareTo(other.getGender()) != 0)
			return getGender().compareTo(other.getGender());
		else if(getAge() < other.getAge())
			return -1;
		else if(getAge() > other.getAge())
			return 1;
		else if(getMaritalStatus().compareTo(other.getMaritalStatus()) != 0)
			return getMaritalStatus().compareTo(other.getMaritalStatus());
		else if(getAgeFirstMarriage() < other.getAgeFirstMarriage())
			return -1;
		else if(getAgeFirstMarriage() > other.getAgeFirstMarriage())
			return 1;
		else if(getAttendSchool())
			return -1;
		else if(!getAttendSchool())
			return 1;
		return 0;
	}

    public String toString()
    {
		DecimalFormat df = new DecimalFormat("###.##");
		return String.format("%-25sAge: %s",last+", "+first,df.format(age));//getFirstName() + " " + getLastName() + " from " + getStreet();
    }

    public String getFirstName()
    {
        return first;
    }

    public String getLastName()
    {
        return last;
    }

	public String getStreetName()
	{
		return streetName;
	}

	public int getStreetNumber()
	{
		return streetNumber;
	}

	public String getRelation()
	{
		return relation;
	}

	public String getRentOwn()
	{
		return rentOwn;
	}

	public double getValueProp()
	{
		return valueProp;
	}

	public String getGender()
	{
		return gender;
	}

	public double getAge()
	{
		return age;
	}

	public String getMaritalStatus()
	{
		return maritalStatus;
	}

	public int getAgeFirstMarriage()
	{
		return ageFirstMarriage;
	}

	public boolean getAttendSchool()
	{
		return attendSchool;
	}

	public boolean getCanRead()
	{
		return canRead;
	}

	public String getBirthplace()
	{
		return birthplace;
	}

	public String getFathersBirthplace()
	{
		return fathersBirthplace;
	}

	public String getMothersBirthplace()
	{
		return mothersBirthplace;
	}

	public String getMotherTongue()
	{
		return motherTongue;
	}

	public int getYearImmigrated()
	{
		return yearImmigrated;
	}

	public String getOccupation()
	{
		return occupation;
	}

	public String getIndustry()
	{
		return industry;
	}

	public String getTranscibedRemarks()
	{
		return transcibedRemarks;
	}
}