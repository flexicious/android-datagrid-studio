package com.flexicious.example.model.classic; 
	import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.flexicious.nestedtreedatagrid.valueobjects.NameValue;
import com.flexicious.utils.UIUtils;
	 
	/**
	 * Typical model class, Employee with typical properties  
	 */	
 
	public class Employee
	{
		
		//these are just random data elements to create realistic data for the demo.
		public static final NameValue[] allDepartments = new NameValue[]{new NameValue("IT", "IT")
				, new NameValue("Accounting", "Accounting")
		, new NameValue("Sales", "Sales")
		, new NameValue("HR", "HR")};
		public static final String[] areaCodes = new String[]{"201","732","212","646"};
		public static final String[] streetTypes = new String[]{"Ave","Blvd","Rd","St","Lane"};
		public static final String[] streetNames = new String[]{"Park","West","Newark","King","Gardner"};
		public static final String[] cities = new String[]{"Grand Rapids","Albany","Stroudsburgh","Barrie","Springfield"};
		 
		 //this is used in the hierarchical datagrid example
		public static final NameValue[] allStates= new NameValue[]{new NameValue("New Jersey", "NJ")
		, new NameValue("New York", "NY")
, new NameValue("Texas", "TX")
, new NameValue("California", "CA")};
//		new NameValue[] ([
//		{"data":"NJ", "label":"New Jersey", "employees":new ArrayList()}, 
//		{"data":"NY", "label":"New York", "employees":new ArrayList()}, 
//		{"data":"TX", "label":"Texas", "employees":new ArrayList()}, 
//		{"data":"CA", "label":"California", "employees":new ArrayList()}]);
		 
		
		
		public String firstName;
		public String lastName;
		public float employeeId;
		public Date hireDate;
		public String stateCode;
		
		public Address address;//complex property
		public String phoneNumber;
		public Integer annualSalary;
		public Boolean isActive;
		public String department;
		public float departmentId;
		public ArrayList<Object> addresses =new ArrayList<Object>();
		
		private static float index = 1;
		
		public String getFullName(){
			return firstName + " " + lastName;
		}
		public static Employee createNullEmployee(){
			Employee  employee = new Employee();
			employee.firstName = null;
			employee.lastName = null;
			employee.hireDate=null; 
			employee.employeeId=index++;
			employee.phoneNumber = null;
			employee.isActive= getRandom(1,2)==1;
			employee.department=null;
			employee.stateCode=null;
			return employee;
			
		}
		public static Employee createEmployee(String fName, Date hDate, String lName)
		{
			Employee  employee = new Employee();
			employee.firstName = fName;
			employee.lastName = lName;
			employee.hireDate=hDate; 
			employee.employeeId=index++;
			employee.phoneNumber = (areaCodes[((Integer) getRandom(0,3))] + "-" + 
			getRandom(100,999).toString()+ "-" + getRandom(1000,9999).toString());
			employee.isActive= getRandom(1,2)==1;
			employee.annualSalary=getRandom(50000,100000);
			employee.department=allDepartments[getRandom(0,3)].data;
			NameValue  state = allStates[getRandom(0,3)];
			employee.stateCode=state.data;
			//state.employees.addItem(employee);
			employee.address= createAddress(state.data);
			
			int  max = getRandom(0,4);
			for(int i =0;i<max;i++)
				employee.addresses.add(createAddress(allStates[getRandom(0,3)].data));
			
			return employee;
		}
		public static Address createAddress(String stateCode){
			Address  address =new Address();
			address.street1=getRandom(100,999).toString() + " " + streetNames[getRandom(0,streetNames.length-1)]+ " " + streetTypes[getRandom(0,streetTypes.length-1)];
			address.city=cities[getRandom(0,cities.length-1)];
			address.state=stateCode;
			address.country="USA";
			
			address.apartmentNumber = getRandom(1,15);
			address.street2="Apt " + address.apartmentNumber;
			address.validFrom =  new GregorianCalendar(getRandom(2000,2005),
					getRandom(0,11), getRandom(1,28),
					0, 0, 0).getTime();
			address.validTo =  new GregorianCalendar(getRandom(2005,2010),
					getRandom(0,11), getRandom(1,28),
					0, 0, 0).getTime();
			
			return address;
		}
		public String getDetails(){
			return employeeId + ". " + firstName + " " + lastName + ", " + phoneNumber + ", " + department + ", " + stateCode + ", " 
		+ UIUtils.formatCurrency(annualSalary,"") + ", "+UIUtils.formatDate(hireDate,"") ;
		}
		public String getCity(){
			return address.city;
		}
		public static Integer getRandom(float minNum, float maxNum)
		{
			return (int) (Math.ceil(Math.random() * (maxNum - minNum + 1)) + (minNum - 1));
		}
		public String getDepartmentManual(){
			return department;
		}
		public String getDepartmentParameterized(){
			return department;
		}
		
		//just for testing
		public String getHireDateString(){
			return this.employeeId==5? "":this.hireDate != null?this.hireDate.toString():null;
		}
		public String getSalaryString(){
			//just createing some random test scenarios
			return this.employeeId==5? null:this.employeeId==6? "111111":this.annualSalary.toString();
		}
		public static ArrayList<Employee> getAllEmployees()
		{
			return new ArrayList<Employee>(Arrays.asList(employees));

			//return (ArrayList<Employee>) Arrays.asList<Employee>(employees);
		}
		public static ArrayList<Employee> getAllEmployeesWithFilter()
		{
			//test with filter function
			ArrayList<Employee> coll =getAllEmployees(); 
			return coll;
		} 
		
		public static ArrayList<Employee> createCollection(float num)
		{
			ArrayList<Employee>  emp = new ArrayList<Employee>();
			while(emp.size()<num){
					emp.add(Employee.createEmployee("FirstName"+emp.size(),createDate("06/03/1996"),"LastName"+emp.size()));
			}
			return emp;
		}
		/**
		* Initialize our dummy database here.
		*/		
		public static Employee[] employees= new Employee[]{
		
			Employee.createEmployee("Brice",createDate("06/03/1996"),"Rittwage"),
			Employee.createEmployee("CHUN-FEN",createDate("01/28/2002"),"Hukill"),
			
			
			Employee.createEmployee("Takahiro",createDate("06/22/2008"),"Aksu"),
			Employee.createEmployee("Corinthians",createDate("02/19/2005"),"Orchard"),
			Employee.createEmployee("Karna",createDate("09/27/2003"),"Arledge"),
			Employee.createEmployee("Takehisa",createDate("07/29/1998"),"Akmanligil"),
			Employee.createEmployee("Ranjana",createDate("10/09/2007"),"Anupoju"),
			Employee.createEmployee("Erico",createDate("10/10/2004"),"Riddell"),
			Employee.createEmployee("Llora",createDate("07/14/1999"),"Lovig"),
			Employee.createEmployee("TIBBY",createDate("07/28/2005"),"Iburg"),
			Employee.createEmployee("Masanori",createDate("02/18/2009"),"ASENCIO"),
			Employee.createEmployee("Jeevan",createDate("01/10/2007"),"EEddddy@"),
			Employee.createEmployee("FAYIZ",createDate("06/04/2000"),"Ayman"),
			Employee.createEmployee("Rassoul",createDate("07/06/1999"),"Asaba"),
			Employee.createEmployee("Emerito",createDate("09/06/2001"),"Meussner"),
			Employee.createEmployee("Luigia",createDate("02/23/2004"),"Uisprapassorn"),
			
			Employee.createEmployee("Gwenda",createDate("05/14/1999"),"Weilert"),
			Employee.createEmployee("datatel2",createDate("08/22/2005"),"Atmaja"),
			Employee.createEmployee("Silvana",createDate("12/21/1998"),"Illiano"),
			Employee.createEmployee("Beaul",createDate("10/23/2000"),"Eagle"),
			Employee.createEmployee("Pamala",createDate("10/29/2003"),"Amott"),
			Employee.createEmployee("Roberta",createDate("02/11/2002"),"Obohoski"),
			Employee.createEmployee("Khiem",createDate("07/14/2001"),"Hirji"),
			Employee.createEmployee("Corbin",createDate("04/23/2003"),"Orlandella"),
			Employee.createEmployee("Peg",createDate("06/10/1999"),"Egelston"),
			Employee.createEmployee("GURAVA",createDate("09/07/2006"),"Urban"),
			Employee.createEmployee("Gregory",createDate("09/14/2004"),"Renca"),
			Employee.createEmployee("YVELINE",createDate("09/13/2001"),"Vellos Coker"),
			Employee.createEmployee("Ginina",createDate("09/12/2009"),"Inyang"),
			Employee.createEmployee("YUN",createDate("03/07/2007"),"UNGCO"),
			Employee.createEmployee("Berdine",createDate("10/02/2002"),"Ericksen"),
			Employee.createEmployee("Surendra",createDate("08/10/2009"),"URISH"),
			Employee.createEmployee("Wei-Cheng",createDate("05/07/2002"),"EIGER FACP"),
			Employee.createEmployee("Lie-Jun",createDate("05/09/2005"),"Iek"),
			Employee.createEmployee("Bajaji",createDate("01/08/1997"),"Ajmani"),
			Employee.createEmployee("Dariush",createDate("10/17/2006"),"ARTIFICIO"),
			Employee.createEmployee("Suzahne",createDate("02/03/2009"),"Uzal"),
			Employee.createEmployee("CATOR",createDate("08/06/1999"),"Athuluru"),
			Employee.createEmployee("Joye",createDate("11/27/1999"),"Oyler"),
			
			Employee.createEmployee("Beng",createDate("09/15/2009"),"Ender"),
			Employee.createEmployee("Patience",createDate("12/16/1998"),"Atluri"),
			Employee.createEmployee("Saiful",createDate("08/01/1999"),"Aiello"),
			Employee.createEmployee("Stefan",createDate("09/19/2001"),"Tewell"),
			Employee.createEmployee("nazneen",createDate("10/20/1997"),"AZANCOT"),
			
			Employee.createEmployee("Hironori",createDate("07/29/1997"),"Irudhayanathan"),
			Employee.createEmployee("Radhika",createDate("05/29/2007"),"Adeva"),
			Employee.createEmployee("Beau",createDate("04/26/1996"),"Eady"),
			Employee.createEmployee("Edyth",createDate("05/06/1999"),"Dygean"),
			Employee.createEmployee("Spyridon",createDate("11/14/2004"),"Pyzik"),
			Employee.createEmployee("Saekyu",createDate("06/18/2008"),"Aekka"),
			Employee.createEmployee("MATTKE",createDate("07/11/2006"),"Atlmayer"),
			Employee.createEmployee("Liang",createDate("12/25/2007"),"Iacovana"),
			Employee.createEmployee("Raffoul",createDate("05/01/2002"),"Afflebach"),
			Employee.createEmployee("Tavia",createDate("03/28/2001"),"AVENIDO"),
			
			Employee.createEmployee("Wacira",createDate("08/03/2005"),"Ack"),
			
			Employee.createEmployee("Andria",createDate("01/08/2006"),"Ndlovu"),
			Employee.createEmployee("Pug",createDate("11/13/1999"),"Ugalde"),
			Employee.createEmployee("SHRUTI",createDate("02/14/2008"),"Hrycko"),
			Employee.createEmployee("Yong-Hwa",createDate("03/03/2002"),"Ontjes"),
			Employee.createEmployee("Yosseph",createDate("03/03/1998"),"Osinuga"),
			Employee.createEmployee("Shih-Tse",createDate("04/05/2000"),"High"),
			Employee.createEmployee("Thommie",createDate("10/16/2006"),"HOMCY"),
			Employee.createEmployee("Bell",createDate("01/11/2005"),"Elenbogen"),
			Employee.createEmployee("Madireddy",createDate("11/20/2008"),"ADAD"),
			Employee.createEmployee("Estle",createDate("07/14/2004"),"Stringer"),
			Employee.createEmployee("Katheleen",createDate("01/21/2008"),"Atturu"),
			Employee.createEmployee("VENKATESWARLU",createDate("10/07/2003"),"Englehart"),
			Employee.createEmployee("Andres",createDate("06/04/1998"),"Ndungu"),
			Employee.createEmployee("Roseline",createDate("01/01/2002"),"Osteen"),
			Employee.createEmployee("Jolyne",createDate("02/03/1999"),"Oldmixon"),
			Employee.createEmployee("Rennie",createDate("07/22/1999"),"ENESCU"),
			Employee.createEmployee("Jamshid",createDate("02/04/1999"),"Amir"),
			Employee.createEmployee("Harmeet",createDate("12/23/2008"),"Aramburu"),
			Employee.createEmployee("PANNEE",createDate("05/08/1996"),"Andoh"),
			Employee.createEmployee("Vernelle",createDate("08/05/2004"),"Erwin"),
			Employee.createEmployee("Bentley",createDate("08/15/2007"),"Englerth"),
			Employee.createEmployee("Renu",createDate("03/23/2003"),"Engel"),
			Employee.createEmployee("Kamal",createDate("10/04/1997"),"Ameer"),
			
			Employee.createEmployee("TZYY-SHUH",createDate("09/10/2006"),"Zyne"),
			Employee.createEmployee("Tobias",createDate("01/09/1997"),"Obando Lopez"),
			Employee.createEmployee("MAHAYSAK",createDate("06/09/2005"),"AHARONI"),
			Employee.createEmployee("Pryor",createDate("03/23/2004"),"Ryder"),
			Employee.createEmployee("Harikishan",createDate("11/26/2007"),"ARTAN"),
			Employee.createEmployee("Bhagwan",createDate("03/16/2000"),"Harts"),
			Employee.createEmployee("SOON",createDate("06/01/2000"),"OOSTHUIZEN"),
			Employee.createEmployee("SAICHOL",createDate("07/01/1997"),"Aisenberg"),
			Employee.createEmployee("Glenda",createDate("02/23/2008"),"Leil"),
			Employee.createEmployee("Keenan",createDate("05/03/2002"),"EEddddy@"),
			Employee.createEmployee("Lura",createDate("05/17/1996"),"Urschel"),
			Employee.createEmployee("Vikrant",createDate("03/24/1998"),"Ikpirijar"),
			Employee.createEmployee("Llal",createDate("06/02/1997"),"LAMPROS"),
			Employee.createEmployee("Elia",createDate("04/10/2001"),"Liscum"),
			Employee.createEmployee("Yogesh",createDate("12/13/2003"),"Ogilvie"),
			Employee.createEmployee("Protima",createDate("07/24/2001"),"Roeseler"),
			Employee.createEmployee("Chubby",createDate("01/03/1997"),"Huling"),
			Employee.createEmployee("Jean-Patrick",createDate("09/06/1998"),"Easler"),
			Employee.createEmployee("IMTYAZ",createDate("12/18/1999"),"Mthalane"),
			Employee.createEmployee("Ayoka",createDate("07/15/2008"),"Young"),
			Employee.createEmployee("Aravind",createDate("01/27/2000"),"Raichlin"),
			Employee.createEmployee("Dwight",createDate("08/14/1998"),"Wichmann"),
			Employee.createEmployee("Reigo",createDate("12/22/2006"),"Eisner"),
			Employee.createEmployee("Squiz",createDate("02/15/1997"),"Quintanilla Attorney"),
			Employee.createEmployee("KEIJO",createDate("06/12/2005"),"eichen"),
			Employee.createEmployee("Monema",createDate("09/13/1996"),"Onaifo"),
			Employee.createEmployee("Gwendolyn",createDate("10/04/2008"),"Weatherspoon"),
			Employee.createEmployee("Navid",createDate("06/02/2005"),"Avendula"),
			Employee.createEmployee("Marek",createDate("10/16/2007"),"Armott"),
			Employee.createEmployee("BRIJPAL",createDate("09/13/2007"),"Ridzik"),
			Employee.createEmployee("CEYHAN",createDate("03/13/2008"),"Eyerman"),
			Employee.createEmployee("Naohisas",createDate("05/20/2001"),"Aoyagi"),
			Employee.createEmployee("Trew",createDate("08/26/2006"),"Reichenbach-Schweers"),
			
			Employee.createEmployee("Rueben",createDate("11/10/1997"),"Uemoto"),
			Employee.createEmployee("Gopal",createDate("09/12/1998"),"Opinya"),
			Employee.createEmployee("Lisenne",createDate("01/23/2006"),"Ishizaka"),
			Employee.createEmployee("Bushra",createDate("04/27/2006"),"Usen"),
			Employee.createEmployee("FAOUZI",createDate("01/27/2001"),"Aoe"),
			Employee.createEmployee("Osbaldo",createDate("12/29/2005"),"Sblendorio"),
			Employee.createEmployee("Dorcas",createDate("02/11/1997"),"Ornstein"),
			Employee.createEmployee("Normie",createDate("02/09/2007"),"Ortolani"),
			Employee.createEmployee("Chen",createDate("05/05/2006"),"Hennan"),
			Employee.createEmployee("Hussian",createDate("04/16/2004"),"User3"),
			Employee.createEmployee("Piyush",createDate("11/13/2005"),"IYADOMI"),
			Employee.createEmployee("GAUDENCIO",createDate("11/14/1998"),"Aumack"),
			Employee.createEmployee("Swiki",createDate("10/03/1999"),"Wihtol"),
			Employee.createEmployee("Vasthie",createDate("07/11/2001"),"Ashurian"),
			Employee.createEmployee("Ryuji",createDate("07/25/2008"),"YUHAS"),
			Employee.createEmployee("Carie",createDate("06/06/2003"),"ARGODALE"),
			Employee.createEmployee("EVELIN",createDate("04/18/1999"),"VENEREO"),
			Employee.createEmployee("WOO",createDate("08/21/2004"),"OOSTHUIZEN"),
			Employee.createEmployee("Caddie",createDate("01/21/2005"),"Adler"),
			Employee.createEmployee("Gabriel",createDate("11/03/1999"),"Abushady"),
			Employee.createEmployee("Kamatchi",createDate("03/04/2002"),"Ambrosey"),
			Employee.createEmployee("MITSUGU",createDate("08/03/2008"),"Italiene"),
			Employee.createEmployee("Pratyush",createDate("09/29/2000"),"Raleigh"),
			Employee.createEmployee("Ritu",createDate("02/04/2008"),"Itagaki"),
			Employee.createEmployee("ROARK",createDate("10/20/2004"),"Oaferina"),
			Employee.createEmployee("Lugean",createDate("03/01/2000"),"Ugbaja"),
			Employee.createEmployee("Remy",createDate("03/17/2001"),"Emerick"),
			Employee.createEmployee("Narsimha",createDate("06/24/2007"),"Arterbury"),
			Employee.createEmployee("Derrek",createDate("08/15/2002"),"Ereckson"),
			Employee.createEmployee("Dennette",createDate("07/24/2008"),"Endl"),
			Employee.createEmployee("Ezio",createDate("05/23/1996"),"ZICHICHI"),
			Employee.createEmployee("Sanjay",createDate("03/14/2006"),"Anksorus"),
			Employee.createEmployee("Shen-Shin",createDate("05/14/2000"),"Hermann"),
			
			Employee.createEmployee("Siw",createDate("06/03/2002"),"Iwamoto"),
			Employee.createEmployee("MANASA",createDate("07/27/1998"),"ANTHINY"),
			Employee.createEmployee("MIRIAN",createDate("10/19/2004"),"IRANIESQ"),
			Employee.createEmployee("Mohan",createDate("01/31/2001"),"Ohnishi"),
			Employee.createEmployee("Dynah",createDate("11/07/2007"),"YNAYA"),
			Employee.createEmployee("Jerome",createDate("03/30/1996"),"Erwin Manager"),
			Employee.createEmployee("Tori",createDate("12/03/2001"),"ORTLIEB"),
			Employee.createEmployee("ccccccc",createDate("07/17/2008"),"ccccccc"),
			Employee.createEmployee("Myriam",createDate("03/20/2006"),"Yribarren"),
			Employee.createEmployee("Yukie",createDate("11/05/2002"),"Ukpokodu"),
			Employee.createEmployee("ACHYUT",createDate("04/10/2003"),"Cheetham"),
			Employee.createEmployee("Barri",createDate("10/08/1998"),"Arulmoli"),
			Employee.createEmployee("Gamaliel",createDate("06/16/1999"),"Amiri"),
			Employee.createEmployee("Benji",createDate("09/11/1997"),"Engles"),
			Employee.createEmployee("Hidetoshi",createDate("01/03/2009"),"Idzior"),
			Employee.createEmployee("ACAROL",createDate("11/10/1999"),"Carmell"),
			Employee.createEmployee("Yaw",createDate("07/27/2009"),"Awadalla"),
			Employee.createEmployee("Federico",createDate("10/21/1997"),"Edgell"),
			Employee.createEmployee("Darvia",createDate("05/26/2003"),"Arseniev"),
			Employee.createEmployee("BHAT",createDate("03/29/1997"),"Hanlon"),
			Employee.createEmployee("Ramkumar",createDate("09/18/2000"),"Amabile"),
			Employee.createEmployee("AUGUSTIN",createDate("06/20/2000"),"Ugalde"),
			Employee.createEmployee("Sassan",createDate("02/18/2004"),"Askin"),
			Employee.createEmployee("Sebastian",createDate("04/27/2002"),"Ebato"),
			Employee.createEmployee("Juliann",createDate("08/23/2009"),"ULISLAM"),
			Employee.createEmployee("Lhea",createDate("07/04/2005"),"Heady"),
			Employee.createEmployee("Tamyan",createDate("10/27/1998"),"AMBATIPUDI"),
			
			Employee.createEmployee("Yongmin",createDate("09/23/2005"),"Ondrusek"),
			Employee.createEmployee("Satheeshkumar",createDate("03/30/1998"),"ATISHA"),
			Employee.createEmployee("Sudarsan",createDate("11/20/2007"),"Udagawa"),
			Employee.createEmployee("Phuntsok",createDate("04/18/2009"),"Huebert"),
			Employee.createEmployee("Nikki",createDate("06/22/2003"),"Ikpirijar"),
			
			
			Employee.createEmployee("Stubby",createDate("03/09/2005"),"Tuhacek"),
			Employee.createEmployee("Maralene",createDate("04/23/1999"),"Arnsberg"),
			Employee.createEmployee("NILAM",createDate("09/14/2003"),"ILYEON"),
			Employee.createEmployee("Artur",createDate("04/14/2003"),"rtrt"),
			Employee.createEmployee("Iza",createDate("10/06/2007"),"Zagury"),
			Employee.createEmployee("Syed",createDate("06/17/1998"),"Yetkin"),
			Employee.createEmployee("Nanci",createDate("05/05/2002"),"Anglum"),
			Employee.createEmployee("Kiley",createDate("08/15/2000"),"ILLOULIAN"),
			Employee.createEmployee("Irene",createDate("01/25/1999"),"Rettig"),
			Employee.createEmployee("prescila",createDate("05/02/2006"),"Renolds"),
			Employee.createEmployee("AttorneyLionel",createDate("07/04/1997"),"ttgfgyu"),
			Employee.createEmployee("Georgan",createDate("06/09/2004"),"Eovaldi"),
			Employee.createEmployee("RAJKUMAR",createDate("02/16/2007"),"AJAYI"),
			Employee.createEmployee("Thearlene",createDate("10/16/2000"),"Herrman"),
			Employee.createEmployee("Beverly",createDate("11/27/1997"),"Everett"),
			Employee.createEmployee("Buttons",createDate("07/14/2009"),"Utley"),
			Employee.createEmployee("Kelby",createDate("06/29/2000"),"Elmer"),
			Employee.createEmployee("djeffe5763",createDate("05/07/2001"),"Jez"),
			Employee.createEmployee("Moriah",createDate("04/07/2002"),"Oranzo"),
			Employee.createEmployee("UMAKANT",createDate("04/28/2005"),"Marzola"),
			Employee.createEmployee("Stefano",createDate("09/14/2003"),"Tenant"),
			Employee.createEmployee("Tomas",createDate("09/22/1997"),"Omrani"),
			Employee.createEmployee("Cordelia",createDate("08/05/2003"),"ORENTREICH"),
			Employee.createEmployee("LaTonia",createDate("09/27/2005"),"Athar"),
			Employee.createEmployee("DECATUR",createDate("11/10/2003"),"Economou"),
			Employee.createEmployee("Mostapha",createDate("07/06/1998"),"Osmani"),
			Employee.createEmployee("Minhui",createDate("08/31/2006"),"Iniguez"),
			Employee.createEmployee("SHINSAENG",createDate("03/04/2009"),"Hirsche"),
			Employee.createEmployee("CHRISTOPHE",createDate("06/29/2008"),"Hrusovsky"),
			Employee.createEmployee("Marygrace",createDate("02/23/2001"),"Armes"),
			Employee.createEmployee("ZAIN",createDate("01/26/2002"),"AITON"),
			Employee.createEmployee("Ina",createDate("04/30/2008"),"Navar"),
			Employee.createEmployee("Camella",createDate("08/22/1996"),"Amsz"),
			
			Employee.createEmployee("Sameera",createDate("06/17/2004"),"Ambhore"),
			Employee.createEmployee("Joy",createDate("12/05/1998"),"Oyler"),
			Employee.createEmployee("Venk",createDate("09/21/1996"),"Enrique"),
			Employee.createEmployee("BAOKUN",createDate("08/03/1998"),"Aoshima"),
			Employee.createEmployee("Sachin",createDate("09/03/1996"),"Acree"),
			Employee.createEmployee("Merea",createDate("10/24/2004"),"Ereli"),
			Employee.createEmployee("Terasa",createDate("08/30/1998"),"Erstad"),
			Employee.createEmployee("Clare",createDate("05/30/2007"),"Lacquement"),
			Employee.createEmployee("Esseye",createDate("06/30/2000"),"SSRINIVASAN"),
			Employee.createEmployee("Viola",createDate("03/23/2005"),"Iovino"),
			Employee.createEmployee("Robertine",createDate("09/25/2000"),"Oberkfell"),
			Employee.createEmployee("Roshan",createDate("10/13/1997"),"Osinuga"),
			Employee.createEmployee("Pamuan",createDate("12/09/1997"),"Amico"),
			Employee.createEmployee("Bertine",createDate("11/29/1999"),"EROGLU"),
			Employee.createEmployee("Mozella",createDate("10/08/1998"),"Ozumba"),
			Employee.createEmployee("Tetsuya",createDate("04/06/1998"),"Etter"),
			Employee.createEmployee("KLAAS",createDate("03/15/2004"),"LaMacchia"),
			Employee.createEmployee("Ilaha",createDate("09/06/2004"),"Lauzerique"),
			Employee.createEmployee("Julio",createDate("10/30/1998"),"Uluc"),
			Employee.createEmployee("Yamile",createDate("09/28/2003"),"Amey"),
			Employee.createEmployee("Subhabrato",createDate("01/13/2003"),"Uber"),
			Employee.createEmployee("Moti",createDate("01/18/2001"),"Oton"),
			Employee.createEmployee("Ronan",createDate("11/17/1996"),"Oniani"),
			Employee.createEmployee("Maple",createDate("07/27/2004"),"Apelewitch"),
			Employee.createEmployee("LAZELL",createDate("07/22/2004"),"Azmi"),
			Employee.createEmployee("Norose",createDate("10/14/1997"),"OROURKE"),
			Employee.createEmployee("Demesha",createDate("05/10/1997"),"Emandi"),
			Employee.createEmployee("Carin",createDate("11/29/2004"),"Aranda"),
			
			Employee.createEmployee("Emmet",createDate("12/12/2003"),"mmpark79"),
			Employee.createEmployee("Deuk",createDate("11/21/2007"),"Eustis"),
			Employee.createEmployee("Hangwoo",createDate("07/20/2004"),"and Berger"),
			Employee.createEmployee("Carol-Anne",createDate("08/11/1996"),"Arifin"),
			Employee.createEmployee("ATEF",createDate("06/17/2005"),"test4"),
			Employee.createEmployee("Somsri",createDate("07/24/1999"),"Omwenga"),
			Employee.createEmployee("Bertina",createDate("06/09/2006"),"Erker"),
			
			Employee.createEmployee("Wendolyne",createDate("12/28/2006"),"Ennist"),
			Employee.createEmployee("Minor",createDate("06/06/1997"),"Ingram"),
			Employee.createEmployee("Chacko",createDate("11/16/2007"),"Hatchel"),
			
			Employee.createEmployee("Ramanarao",createDate("11/18/2006"),"Amy"),
			Employee.createEmployee("Francesca",createDate("03/30/2003"),"Rajca"),
			Employee.createEmployee("Hammond",createDate("05/27/1998"),"Amey"),
			Employee.createEmployee("Benda",createDate("09/02/2002"),"Enzenroth"),
			Employee.createEmployee("Laureen",createDate("11/18/2005"),"Auber"),
			Employee.createEmployee("Lias",createDate("02/21/1996"),"Iannotta"),
			Employee.createEmployee("Emile",createDate("03/31/1996"),"Mitsunari"),
			Employee.createEmployee("Valy",createDate("05/29/2001"),"ALBERENGA"),
			Employee.createEmployee("Hiroki",createDate("11/21/1996"),"Irvin"),
			Employee.createEmployee("Harpeet",createDate("06/21/2000"),"Areton"),
			Employee.createEmployee("FAYEZ",createDate("05/03/2009"),"Aybar-Llenas"),
			Employee.createEmployee("Gerti",createDate("01/08/2007"),"Ernevad"),
			
			Employee.createEmployee("LICHENG",createDate("05/08/2001"),"Ice"),
			Employee.createEmployee("Callecia",createDate("03/01/2004"),"Allveri"),
			Employee.createEmployee("NAM",createDate("05/08/2007"),"Amorose"),
			Employee.createEmployee("Stamatina",createDate("12/31/2000"),"TALLAPRAGAD"),
			Employee.createEmployee("Lucile",createDate("08/05/2000"),"Ucles"),
			
			Employee.createEmployee("Kaven",createDate("07/26/1999"),"Averbach"),
			Employee.createEmployee("Anisur",createDate("03/19/2008"),"Nicolau"),
			Employee.createEmployee("UNA",createDate("12/27/1997"),"Naqvi"),
			Employee.createEmployee("Runelvys",createDate("01/02/2009"),"Understahl"),
			Employee.createEmployee("Kristelle",createDate("09/20/1999"),"Richards-Kortum"),
			Employee.createEmployee("Abolghasem",createDate("04/19/2007"),"Bool"),
			Employee.createEmployee("Chris",createDate("08/30/2007"),"Hriniak"),
			Employee.createEmployee("Chutuoc",createDate("03/29/2002"),"HUGUES"),
			Employee.createEmployee("Nettie",createDate("07/31/2000"),"Etten"),
			
			Employee.createEmployee("Aswathy",createDate("05/28/1997"),"Swiech"),
			
			Employee.createEmployee("Kourosh",createDate("10/03/2003"),"Ouchi"),
			Employee.createEmployee("Jenanne",createDate("06/16/1996"),"Engels-Churchill"),
			Employee.createEmployee("Darlan",createDate("07/05/2007"),"ARMATAS"),
			Employee.createEmployee("NAFEESUNNISA",createDate("11/10/2005"),"Afflebach"),
			Employee.createEmployee("Candra",createDate("07/09/2004"),"Ansloan"),
			Employee.createEmployee("Seynabou",createDate("03/16/2001"),"Eyring"),
			Employee.createEmployee("Nicer",createDate("04/13/1996"),"Ickes"),
			Employee.createEmployee("Marini",createDate("05/17/2003"),"Arden"),
			Employee.createEmployee("Pussy",createDate("08/11/2001"),"USI"),
			Employee.createEmployee("JUGAL",createDate("09/12/2002"),"Ugino"),
			Employee.createEmployee("Marketing",createDate("03/11/2000"),"Aresco"),
			Employee.createEmployee("Meenakshi",createDate("03/16/2006"),"Eells"),
			Employee.createEmployee("Basit",createDate("05/15/1996"),"Astorino"),
			Employee.createEmployee("Antonio",createDate("03/13/1996"),"Ntakirutimana"),
			Employee.createEmployee("Sean",createDate("05/24/2005"),"Eagles"),
			Employee.createEmployee("Maryse",createDate("02/14/2007"),"Archer"),
			Employee.createEmployee("smc01",createDate("10/06/2005"),"McCandless"),
			Employee.createEmployee("Sabyasachi",createDate("07/14/2004"),"Abrazhevich"),
			Employee.createEmployee("GIOVANNI",createDate("02/04/1998"),"Iosifides"),
			Employee.createEmployee("Lukeman",createDate("07/27/2005"),"Ukman"),
			Employee.createEmployee("Udell",createDate("06/13/2006"),"DEL VECCHIO"),
			Employee.createEmployee("RASHIDA",createDate("07/12/2004"),"ASWAD"),
			Employee.createEmployee("Ivo",createDate("07/31/2008"),"Vohden"),
			Employee.createEmployee("Sugnana",createDate("03/03/2004"),"Ugalde"),
			Employee.createEmployee("Yuhong",createDate("07/16/2004"),"Uhlenkamp"),
			Employee.createEmployee("Pierre",createDate("12/22/2008"),"Iennaco"),
			Employee.createEmployee("Cyd",createDate("04/03/1996"),"Yde"),
			Employee.createEmployee("Sukumar",createDate("08/25/1997"),"Uku"),
			Employee.createEmployee("Pinfen",createDate("11/12/2007"),"Inami"),
			Employee.createEmployee("LaCinda",createDate("06/01/2004"),"Accardo"),
			Employee.createEmployee("Yasukazu",createDate("08/05/2008"),"Ashman"),
			Employee.createEmployee("THEMIS",createDate("04/17/2004"),"Heiser"),
			Employee.createEmployee("Jitender",createDate("03/17/2004"),"ITAKURA"),
			Employee.createEmployee("Tam",createDate("06/02/2001"),"Amble"),
			Employee.createEmployee("JEETA",createDate("03/09/2008"),"Eells"),
			Employee.createEmployee("Wickey",createDate("04/13/2002"),"Ichinomiya"),
			Employee.createEmployee("Rajko",createDate("06/04/2009"),"Ajith"),
			Employee.createEmployee("Ranvir",createDate("07/07/1996"),"Antiuk"),
			Employee.createEmployee("Dror",createDate("09/03/2002"),"Rodriguez-Bernier"),
			Employee.createEmployee("Kamella",createDate("06/22/2002"),"Amier"),
			Employee.createEmployee("Mukul",createDate("01/05/2007"),"Ukpokodu"),
			Employee.createEmployee("Vaidhyanathan",createDate("11/05/1999"),"AIVAZIAN"),
			Employee.createEmployee("JIAYUAN",createDate("12/16/2001"),"Iannello"),
			Employee.createEmployee("Curtice",createDate("04/06/2007"),"URBANCZYK"),
			Employee.createEmployee("Leven",createDate("03/23/1999"),"Eveland"),
			Employee.createEmployee("Cirrie",createDate("04/10/2009"),"Irby"),
			Employee.createEmployee("Eunis",createDate("02/08/2007"),"Underhill"),
			Employee.createEmployee("ITI",createDate("01/20/2008"),"Tinklenberg"),
			Employee.createEmployee("Kayley",createDate("05/04/2000"),"Ayer"),
			Employee.createEmployee("Renetta",createDate("08/25/2005"),"Enyenihi"),
			Employee.createEmployee("Edyta",createDate("02/20/2007"),"Dyck"),
			Employee.createEmployee("Milana",createDate("09/22/2004"),"Iles"),
			Employee.createEmployee("Hui",createDate("06/06/2004"),"Uitz"),
			Employee.createEmployee("Shareen",createDate("11/12/1999"),"Harte"),
			Employee.createEmployee("Melgar",createDate("06/21/1998"),"Elsberg"),
			Employee.createEmployee("Abu",createDate("01/10/2004"),"Buggeln"),
			Employee.createEmployee("Lovissa",createDate("01/13/1999"),"Ovena"),
			Employee.createEmployee("HanJu",createDate("06/16/1998"),"Andrieu"),
			Employee.createEmployee("Daman",createDate("07/09/2006"),"Amodio"),
			Employee.createEmployee("Orell",createDate("05/07/2002"),"Regis-Brito"),
			Employee.createEmployee("Moxie",createDate("12/02/2003"),"Oxciano"),
			Employee.createEmployee("Vik",createDate("08/26/2004"),"IKE"),
			Employee.createEmployee("Vaunceil",createDate("07/03/2001"),"Aumiller"),
			Employee.createEmployee("Liese",createDate("06/29/1999"),"Iek"),
			
			Employee.createEmployee("Tirk",createDate("06/09/1997"),"Ireland"),
			Employee.createEmployee("Omiga",createDate("08/12/2009"),"Miksis"),
			Employee.createEmployee("Chiao",createDate("01/15/2005"),"Hiznay"),
			Employee.createEmployee("Takahito",createDate("05/26/2001"),"Akaike"),
			Employee.createEmployee("JUNGJIN",createDate("03/16/2005"),"Unno"),
			Employee.createEmployee("SAJAD",createDate("01/08/2003"),"Ajenstat"),
			Employee.createEmployee("Haifa",createDate("06/13/1996"),"AITON"),
			
			
			Employee.createEmployee("Jianliang",createDate("04/07/2000"),"iavicoli"),
			Employee.createEmployee("Mohgan",createDate("09/01/2006"),"Ohrn-Hicks"),
			Employee.createEmployee("Yelena",createDate("05/08/2009"),"Elkhoury"),
			Employee.createEmployee("Rebeca",createDate("09/07/2005"),"Eberhart"),
			Employee.createEmployee("Gill",createDate("03/24/2007"),"ILER"),
			Employee.createEmployee("Regis",createDate("01/26/1998"),"Eggleton"),
			Employee.createEmployee("PollyAnn",createDate("09/17/2005"),"Olguin"),
			Employee.createEmployee("Elliott",createDate("09/10/2001"),"Llyod"),
			Employee.createEmployee("Roxene",createDate("08/15/1998"),"Oxnevad"),
			Employee.createEmployee("Rufus",createDate("07/27/2006"),"UFFORD"),
			Employee.createEmployee("Rhiannon",createDate("03/25/1996"),"Higaki"),
			Employee.createEmployee("Kalpesh",createDate("03/03/2009"),"ALURI"),
			Employee.createEmployee("Elart",createDate("04/14/2009"),"Lacuesta"),
			Employee.createEmployee("Balasaheb",createDate("02/20/1996"),"Almus"),
			Employee.createEmployee("Tonmoy",createDate("06/10/1999"),"Ontiveros"),
			Employee.createEmployee("Efren",createDate("05/20/1999"),"Franzini"),
			Employee.createEmployee("Dragon",createDate("12/05/2003"),"Raghunandan"),
			Employee.createEmployee("Quinette",createDate("04/23/2003"),"Uittenbogaard"),
			Employee.createEmployee("Pedro",createDate("02/26/2006"),"Edmondson"),
			Employee.createEmployee("Loraine",createDate("06/08/2001"),"Orlando"),
			Employee.createEmployee("BAZIEL",createDate("06/24/1999"),"Azeem"),
			Employee.createEmployee("Rechelle",createDate("02/03/2003"),"Eckhart"),
			Employee.createEmployee("Pinti",createDate("08/05/2003"),"Insley"),
			Employee.createEmployee("Candis",createDate("09/12/2004"),"Andrulewicz"),
			Employee.createEmployee("Derak",createDate("11/15/2004"),"Ertem"),
			Employee.createEmployee("Darcie",createDate("07/20/2006"),"Arslanian"),
			Employee.createEmployee("Enos",createDate("04/17/2004"),"Noreika"),
			Employee.createEmployee("Karin",createDate("04/10/2008"),"Armendariz"),
			
			Employee.createEmployee("Hanu",createDate("12/11/2006"),"Andreoni"),
			Employee.createEmployee("SANJA",createDate("02/24/2003"),"Andersson"),
			Employee.createEmployee("Vanburn",createDate("09/21/2009"),"Anliker"),
			Employee.createEmployee("Tiffanie",createDate("10/20/2000"),"Ifurung"),
			Employee.createEmployee("Gayle",createDate("03/10/1999"),"Ayer"),
			Employee.createEmployee("RANJANI",createDate("06/04/2002"),"ANGELUCCI"),
			Employee.createEmployee("Cordula",createDate("10/06/2001"),"Ortmeier"),
			Employee.createEmployee("Mourad",createDate("08/28/1997"),"Ourtiague"),
			Employee.createEmployee("ROITH",createDate("01/28/2005"),"Oishi"),
			Employee.createEmployee("Gerd",createDate("07/26/2009"),"Erstling"),
			Employee.createEmployee("Ogbonna",createDate("04/25/2001"),"Gbalekuma"),
			Employee.createEmployee("Weihua",createDate("06/11/2007"),"EIK"),
			Employee.createEmployee("Ralf",createDate("05/24/2005"),"Althouse"),
			Employee.createEmployee("Loann",createDate("10/05/2000"),"Oana"),
			Employee.createEmployee("Jayshree",createDate("06/04/1999"),"Ayatollahzadeh"),
			Employee.createEmployee("Natsumi",createDate("12/07/2003"),"Athota"),
			Employee.createEmployee("Deann",createDate("02/20/2001"),"Earls"),
			Employee.createEmployee("Barclay",createDate("01/19/1998"),"Arrabelli"),
			Employee.createEmployee("Asim",createDate("10/03/2005"),"Sider"),
			Employee.createEmployee("Eliyahy",createDate("07/24/2007"),"Lipon"),
			
			
			Employee.createEmployee("Timothye",createDate("10/05/1997"),"Imburgia"),
			Employee.createEmployee("Mehdi",createDate("02/05/2008"),"Ehmen"),
			Employee.createEmployee("Tameka",createDate("08/24/2009"),"Amorim"),
			Employee.createEmployee("Leflora",createDate("11/28/2008"),"Efting"),
			Employee.createEmployee("Meeta",createDate("11/11/1997"),"Eechambadi"),
			Employee.createEmployee("Milham",createDate("03/14/1998"),"ILENE"),
			
			Employee.createEmployee("Anex",createDate("01/21/1996"),"Nelson"),
			Employee.createEmployee("Kanitha",createDate("07/14/2002"),"Anstine"),
			Employee.createEmployee("Daykin",createDate("07/25/2008"),"Aybar-Llenas"),
			Employee.createEmployee("Wendi",createDate("10/11/2000"),"ENGEMAN"),
			Employee.createEmployee("Ulysses",createDate("12/12/2004"),"Lyle"),
			Employee.createEmployee("Dena",createDate("10/17/2001"),"Engelbrecht"),
			Employee.createEmployee("Dietrich",createDate("11/23/2002"),"Ierullo"),
			Employee.createEmployee("Malia",createDate("09/14/2008"),"Alessi"),
			Employee.createEmployee("Susheela",createDate("01/08/1999"),"Usedly"),
			Employee.createEmployee("Young-Gurl",createDate("11/17/2004"),"Ourtiague"),
			Employee.createEmployee("Adele",createDate("02/18/2000"),"Detrick"),
			Employee.createEmployee("Pao",createDate("08/04/2001"),"AONUMA"),
			
			Employee.createEmployee("Mosen",createDate("06/17/1996"),"OSELLAME"),
			Employee.createEmployee("WAHEED",createDate("01/03/2006"),"Ahlschwede"),
			Employee.createEmployee("Nanavaty",createDate("05/17/2000"),"Antonangeli"),
			Employee.createEmployee("Yuping",createDate("04/25/2001"),"Upadhyayula"),
			Employee.createEmployee("Lambertus",createDate("03/22/2007"),"Ameer"),
			Employee.createEmployee("Kanwal",createDate("03/21/2007"),"Ando"),
			Employee.createEmployee("SHIGEYUKI",createDate("07/12/1996"),"Hitt"),
			Employee.createEmployee("Toyin",createDate("01/28/2006"),"Oyer"),
			Employee.createEmployee("Wenchin",createDate("02/18/2007"),"Engfer"),
			Employee.createEmployee("Heidi",createDate("03/18/2002"),"Eide"),
			Employee.createEmployee("Aurea",createDate("02/14/2007"),"Urso"),
			Employee.createEmployee("Bethanne",createDate("01/09/1997"),"Etie"),
			
			
			
			Employee.createEmployee("Muftah",createDate("09/12/2008"),"UFRET"),
			Employee.createEmployee("HIDEHIRO",createDate("09/23/2006"),"Idriss"),
			Employee.createEmployee("Isaiah",createDate("02/24/2003"),"Saurer"),
			Employee.createEmployee("Ailla",createDate("02/23/2000"),"Ilagan"),
			Employee.createEmployee("Nikhil",createDate("11/21/2001"),"IKEGAYA"),
			Employee.createEmployee("Gilberta",createDate("05/16/1998"),"ILANGO"),
			Employee.createEmployee("Hsuan",createDate("11/29/2002"),"Sussillo"),
			Employee.createEmployee("Pravee",createDate("08/28/2009"),"Rahn"),
			Employee.createEmployee("Shirlie",createDate("03/17/1996"),"Hinnergardt"),
			Employee.createEmployee("Roscoe",createDate("02/10/2002"),"Osterli"),
			
			Employee.createEmployee("Kandan",createDate("06/04/2004"),"Annunziata"),
			Employee.createEmployee("EDUVIGIS",createDate("05/26/2007"),"Dushey"),
			Employee.createEmployee("Sreekumar",createDate("02/22/2006"),"Reznikov"),
			Employee.createEmployee("Sang-Tack",createDate("04/03/1996"),"Anjanappa"),
			Employee.createEmployee("Margerete",createDate("07/28/2000"),"Arana"),
			Employee.createEmployee("Medhat",createDate("07/04/2000"),"EDNALDO"),
			Employee.createEmployee("Roann",createDate("10/05/2000"),"Oana"),
			Employee.createEmployee("Tyrus",createDate("10/06/1996"),"Yribarren"),
			Employee.createEmployee("Sharda",createDate("09/06/1997"),"Hannon"),
			Employee.createEmployee("Doo-Sung",createDate("07/17/2006"),"Oosterman"),
			Employee.createEmployee("Dilcia",createDate("02/14/2007"),"ILLURI"),
			Employee.createEmployee("YASUNORI",createDate("03/22/1999"),"ashachauhan"),
			
			Employee.createEmployee("Pascual",createDate("07/24/1998"),"Aspromonte"),
			Employee.createEmployee("Guruprasath",createDate("10/21/2006"),"UR REHMAN"),
			Employee.createEmployee("Branch",createDate("04/25/2001"),"Rapien"),
			Employee.createEmployee("Karilyn",createDate("04/09/2006"),"Arens"),
			Employee.createEmployee("PRITH",createDate("03/24/2006"),"Riley-Nowlin"),
			Employee.createEmployee("Bao",createDate("05/27/2005"),"Aoe"),
			Employee.createEmployee("Syamala",createDate("03/03/2001"),"YASUNAGA"),
			Employee.createEmployee("Roanna",createDate("11/20/1998"),"Oakcrum"),
			Employee.createEmployee("Dustin",createDate("09/22/1999"),"Usowski"),
			Employee.createEmployee("Nathan",createDate("09/11/2008"),"Atwell"),
			Employee.createEmployee("Marshan",createDate("02/24/2003"),"ARGIZ"),
			Employee.createEmployee("Jonus",createDate("09/27/2008"),"Onodera"),
			Employee.createEmployee("Nick",createDate("08/13/2003"),"ICHIHARA"),
			Employee.createEmployee("Matty",createDate("01/14/2001"),"ATHY"),
			Employee.createEmployee("Bethan",createDate("03/07/2004"),"Etinoff-Gordon"),
			Employee.createEmployee("Uohn",createDate("09/10/2006"),"Ohrstrom"),
			Employee.createEmployee("Stanlee",createDate("12/11/1998"),"Taillon"),
			Employee.createEmployee("Loyce",createDate("06/06/2009"),"Oyenuga"),
			Employee.createEmployee("Yubin",createDate("10/14/2004"),"Ubieta"),
			Employee.createEmployee("Tarif",createDate("08/21/2002"),"Arnould"),
			Employee.createEmployee("Hayden",createDate("11/09/2008"),"Ayer")
		};
		private static Date createDate(String string) {
 			try {
				return new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH).parse(string);
			} catch (ParseException e) {
				return new Date();
			}
		}
		
		
	} 
