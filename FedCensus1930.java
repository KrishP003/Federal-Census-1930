import java.io.*;
import java.util.*;
import java.text.DecimalFormat;
public class FedCensus1930
{
	ArrayList<Citizen> listOfCitizens;
	public FedCensus1930()
	{
		listOfCitizens = new ArrayList<Citizen>();
		File fileName = new File("FedCensusData.txt");
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			while((text = input.readLine()) != null)
			{
				//System.out.println(text);
				if(text.length() > 2 && text.substring(0,2).equals("17"))
				{
					String first = text.substring(71,88).trim();
					String last = text.substring(55,71).trim();
					String streetName = text.substring(20,36).trim();
					String streetNumber = text.substring(36,45).trim();
					String relation = text.substring(88,108).trim();
					String rentOwn = text.substring(108,113).trim();
					String valueProp = text.substring(113,121).trim();
					String gender = text.substring(133,134).trim();
					String age = text.substring(143,151).trim();
					String maritalStatus = text.substring(151,156).trim();
					String ageFirstMarriage = text.substring(156,162).trim();
					String attendSchool = text.substring(162,167).trim();
					String canRead = text.substring(167,173).trim();
					String birthplace = text.substring(173,190).trim();
					String fathersBirthplace = text.substring(190,207).trim();
					String mothersBirthplace = text.substring(207,224).trim();
					String motherTongue = text.substring(224,235).trim();
					String yearImmigrated = text.substring(235,241).trim();
					String occupation = text.substring(252,274).trim();
					String industry = text.substring(274,303).trim();
					String transcibedRemarks = text.substring(342).trim();

					listOfCitizens.add(new Citizen(first,last,streetName,streetNumber,relation,rentOwn,valueProp,gender,age,maritalStatus,ageFirstMarriage,attendSchool,canRead,birthplace,fathersBirthplace,mothersBirthplace,motherTongue,yearImmigrated,occupation,industry,transcibedRemarks));
				}
			}
		}catch(IOException e)
		{
			System.out.println("File not found.");
		}

		for(int i = listOfCitizens.size() - 1; i >= 0; i--)
		{
			String last = listOfCitizens.get(i).getLastName();
			String first = listOfCitizens.get(i).getFirstName();
			if(last.equals(".") && first.equals("."))
				listOfCitizens.remove(i);
		}
		Collections.sort(listOfCitizens);
		streetCitizen();
		//birthPlaceAge();
		//motherTongueName();
		//occupationFatherBirthplace();
		//genderRemarks();
		//rentOwnValues();
		//firstMarriageCanRead();
	}

	public void streetCitizen()
	{
		TreeMap<String,TreeSet<Citizen>> streetCitizenMap = new TreeMap<>();
		for(Citizen c : listOfCitizens)
		{
			if(!streetCitizenMap.containsKey(c.getStreetName()))
				streetCitizenMap.put(c.getStreetName(), new TreeSet<Citizen>());
			streetCitizenMap.get(c.getStreetName()).add(c);
		}
		Iterator<String> it = streetCitizenMap.keySet().iterator();
		while(it.hasNext())
		{
			String street = it.next();
			System.out.println(street+":");
			TreeSet<Citizen> temp = streetCitizenMap.get(street);
			for(Citizen c : temp)
				System.out.println("\t"+c);
		}
	}

	public void birthPlaceAge()
	{
		DecimalFormat df = new DecimalFormat("###.###");
		TreeMap<String, PriorityQueue<Double>> birthplaceAgeMap = new TreeMap<>();
		for(Citizen c : listOfCitizens)
		{
			if(!birthplaceAgeMap.containsKey(c.getBirthplace()))
				birthplaceAgeMap.put(c.getBirthplace() , new PriorityQueue<Double>());
			birthplaceAgeMap.get(c.getBirthplace()).add(c.getAge());
		}

		Iterator<String> it = birthplaceAgeMap.keySet().iterator();
		while(it.hasNext())
		{
			String birthplace = it.next();
			System.out.println(birthplace+":");
			PriorityQueue<Double> temp = birthplaceAgeMap.get(birthplace);
			System.out.print("[");
			while(!temp.isEmpty())
			{
				double age = temp.poll();
				if(age >= 0)
				{
					if(temp.peek() != null)
						System.out.print(df.format(age)+", ");
					else
						System.out.print(df.format(age));
				}
			}
			System.out.println("]");
		}
	}

	public void motherTongueName()
	{
		TreeMap<String, ArrayList<String>> motherTongueNameMap = new TreeMap<>();
		for(Citizen c : listOfCitizens)
		{
			if(!motherTongueNameMap.containsKey(c.getMotherTongue()))
				motherTongueNameMap.put(c.getMotherTongue() , new ArrayList<String>());
			motherTongueNameMap.get(c.getMotherTongue()).add(c.getFirstName()+" "+c.getLastName());
		}

		Iterator<String> it = motherTongueNameMap.keySet().iterator();
		while(it.hasNext())
		{
			String motherTongue = it.next();
			System.out.println(motherTongue+":");
			ArrayList<String> temp = motherTongueNameMap.get(motherTongue);
			for(String c : temp)
				System.out.println("\t"+c);
		}
	}

	public void occupationFatherBirthplace()
	{
		TreeMap<String,HashSet<String>> occupationFatherBirthplace = new TreeMap<>();
		for(Citizen c : listOfCitizens)
		{
			if(!occupationFatherBirthplace.containsKey(c.getOccupation()))
				occupationFatherBirthplace.put(c.getOccupation() , new HashSet<String>());
			occupationFatherBirthplace.get(c.getOccupation()).add(c.getFathersBirthplace());
		}

		Iterator<String> it = occupationFatherBirthplace.keySet().iterator();
		while(it.hasNext())
		{
			String occupation = it.next();
			System.out.println(occupation+":");
			HashSet<String> temp = occupationFatherBirthplace.get(occupation);
			Iterator<String> hashIt = temp.iterator();
			while(hashIt.hasNext())
				System.out.println("\t"+hashIt.next());
		}
	}

	public void genderRemarks()
	{
		TreeMap<String,HashSet<String>> genderRemarksMap = new TreeMap<>();
		for(Citizen c : listOfCitizens)
		{
			if(!genderRemarksMap.containsKey(c.getGender()))
				genderRemarksMap.put(c.getGender(), new HashSet<String>());
			genderRemarksMap.get(c.getGender()).add(c.getTranscibedRemarks());
		}

		Iterator<String> it = genderRemarksMap.keySet().iterator();
		while(it.hasNext())
		{
			String gender = it.next();
			System.out.println(gender+":");
			HashSet<String> temp = genderRemarksMap.get(gender);
			for(String st : temp)
				System.out.println("\t"+st);
		}
	}

	public void rentOwnValues()
	{
		DecimalFormat df = new DecimalFormat("###.###");
		TreeMap<String,TreeSet<Double>> rentOwnValuesMap = new TreeMap<>();
		for(Citizen c : listOfCitizens)
		{
			if(!rentOwnValuesMap.containsKey(c.getRentOwn()))
				rentOwnValuesMap.put(c.getRentOwn(), new TreeSet<Double>());
			rentOwnValuesMap.get(c.getRentOwn()).add(c.getValueProp());
		}

		Iterator<String> it = rentOwnValuesMap.keySet().iterator();
		while(it.hasNext())
		{
			String rentOwn = it.next();
			System.out.println(rentOwn+":");
			TreeSet<Double> temp = rentOwnValuesMap.get(rentOwn);
			for(Double num : temp)
				System.out.println("\t"+df.format(num));
		}
	}

	public void firstMarriageCanRead()
	{
		TreeMap<String,TreeSet<Integer>> firstMarriageCanReadMap = new TreeMap<>();
		for(Citizen c : listOfCitizens)
		{
			if(!firstMarriageCanReadMap.containsKey(Boolean.toString(c.getCanRead())))
				firstMarriageCanReadMap.put(Boolean.toString(c.getCanRead()) , new TreeSet<Integer>());
			firstMarriageCanReadMap.get(Boolean.toString(c.getCanRead())).add(c.getAgeFirstMarriage());
		}

		Iterator<String> it = firstMarriageCanReadMap.keySet().iterator();
		while(it.hasNext())
		{
			String canRead = it.next();
			System.out.println(canRead+":");
			TreeSet<Integer> temp = firstMarriageCanReadMap.get(canRead);
			for(int age : temp)
				if(age >= 0)
					System.out.println("\t"+age);
		}
	}

	public static void main(String[] args)
	{
		FedCensus1930 app = new FedCensus1930();
	}
}