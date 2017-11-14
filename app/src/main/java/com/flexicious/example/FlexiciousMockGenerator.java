package com.flexicious.example;

	
	import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.flexicious.controls.core.Event;
import com.flexicious.controls.core.EventDispatcher;
import com.flexicious.controls.core.FlexTimer;
import com.flexicious.controls.core.Function;
import com.flexicious.controls.core.TimerEvent;
import com.flexicious.example.model.BaseEntity;
import com.flexicious.example.model.billing.Invoice;
import com.flexicious.example.model.billing.LineItem;
import com.flexicious.example.model.common.Address;
import com.flexicious.example.model.common.PagedResult;
import com.flexicious.example.model.common.ReferenceData;
import com.flexicious.example.model.common.SystemConstants;
import com.flexicious.example.model.organizations.CustomerOrganization;
import com.flexicious.example.model.organizations.Organization;
import com.flexicious.example.model.persons.CommercialContact;
import com.flexicious.example.model.persons.SystemUser;
import com.flexicious.example.model.transactions.Deal;
import com.flexicious.grids.events.PrintExportOptions;
import com.flexicious.grids.filters.Filter;
import com.flexicious.grids.filters.PrintExportFilter;
import com.flexicious.nestedtreedatagrid.utils.ExtendedUIUtils;
import com.flexicious.utils.UIUtils;
	 
 
	public class FlexiciousMockGenerator extends EventDispatcher
	{
		public final int DEALS_PER_ORG = 1;
		public final int INVOICES_PER_DEAL = 5;
		public final int LINEITEMS_PER_INVOICE = 5;
		
		private static FlexiciousMockGenerator  _instance = new FlexiciousMockGenerator();
		public static FlexiciousMockGenerator instance()
		{
			return _instance;
		} 
		public FlexiciousMockGenerator()
		{
			
		}
		
		public static ArrayList<LineItem> lineItems =new ArrayList<LineItem>();
		
		public static ArrayList<Organization> simpleList;
		
		public static ArrayList<Organization> deepList;
		
		public float progress;
		
		public ArrayList<LineItem> getAllLineItems(){
			if(lineItems.size()==0){
				getDeepOrgList();
			}
			return lineItems;
		}
		
		public List<Organization> getFlatOrgList(){
			if(simpleList == null){
				simpleList=getOrgList(false);
			}
			return   simpleList.subList(0, simpleList.size()-1);
		}
		public ArrayList<Organization> getDeepOrgList(){
			if(deepList==null){
				deepList=getOrgList(true);
			}
			return deepList;
		}
		@SuppressWarnings("unused")
		private static int _index =0;
		public void init(){
			deepList=new ArrayList<Organization>();
			index=0;
			FlexTimer timer =new FlexTimer(100, 0);
			timer.addEventListener(TimerEvent.TIMER,new Function(this, "onTimer"));
			timer.start();
			
		}
		private int _pageIndex =0;
		private int _pageSize =30;
		@SuppressWarnings("unused")
		private void onTimer(TimerEvent evt)
		{
			for(int i =_pageIndex*_pageSize;i<Math.min(companyNames.length,(_pageIndex+1)*_pageSize);i++){
				String  nm =companyNames[i];
				deepList.add(createOrganization(nm,true));
			}
			if((_pageIndex*_pageSize)>=companyNames.length){
 ((FlexTimer) evt.target).stop();
				progress=100;
				dispatchEvent(new Event("progress"));
				
				return;
			}else{
				progress=(_pageIndex*_pageSize)*100/companyNames.length;
				_pageIndex++;
				dispatchEvent(new Event("progress"));
 ((FlexTimer) evt.target).start();
			}
		}
		
		
		public ArrayList<Organization> getOrgList(Boolean deep) {
			ArrayList<Organization> orgs =new ArrayList<Organization>();
			for(int i =0;i<companyNames.length;i++){
				String  nm =companyNames[i];
				orgs.add(createOrganization(nm,deep));
			}
			return orgs;
		}
		public int index =0;
		public Organization getDeepOrg(float orgId){
			for  (Object orgo:   deepList){
				Organization org = (Organization) orgo;
				if(org.id==orgId){
					return (Organization) org.clone(true);
				}
			}
			throw new Error("Invalid org ID passed in : " + orgId);
		}
		//this method takes a filter object, and returns the set of records that match the filter object.
		//some special things about this method - it is a multipurpose method. Meaning,
		//that it is called by the grid when it is filtered, paged or sorted.
		//it is also called by print and export controllers when user requests all pages or selected pages
		//this is required because the in filterPageSortMode=server, the grid does not load all the data, only the current record
		//So if the user requests data that does not exist on the client, it triggers the PrintExportDataRequest event, that 
		//we hook into, (In FullyLazyLoadedGrid) and call this method with the filter Parameter.
		public PagedResult getPagedOrganizationList(Filter filter)
		{
			if(simpleList==null){
				simpleList=getOrgList(false);
			}
			if(filter instanceof PrintExportFilter){
				PrintExportFilter  pef = (PrintExportFilter) filter;
				if(pef.printExportOptions.printExportOption == PrintExportOptions.PRINT_EXPORT_ALL_PAGES){//anurag
					filter.pageIndex=-1;//so we return all records.
					return new PagedResult(ExtendedUIUtils.filterPageSort(deepList,filter, null, false),filter.recordCount, null, true);
				}else{
					return new PagedResult(ExtendedUIUtils.filterPageSort(deepList,filter,new int[]
							{(int) pef.printExportOptions.pageFrom,(int) pef.printExportOptions.pageTo},false),filter.recordCount, null, true);
				}
			}
			else
				return new PagedResult((ExtendedUIUtils.filterPageSort(simpleList,filter, null , false)),filter.recordCount,null, true);
		}
		public PagedResult getDealsForOrganization(float orgId, Filter filter)
		{
			for (Organization org : getDeepOrgList()){
				if(org.id==orgId){
					ArrayList<Deal> arr =org.deals;
					return new PagedResult(filter.pageIndex>=0?new ArrayList<Object>(
							ExtendedUIUtils.filterPageSort(arr,filter,null,false)):arr,arr.size(),
						UIUtils.toDictionary("total",UIUtils.sum(org.deals,"dealAmount"),"count",arr.size()),false);
				}
			}
			return new PagedResult(new ArrayList<Deal>(),0, null, true);
		}
		public PagedResult getInvoicesForDeal(float dealId, Filter filter)
		{
			for (Organization org: getDeepOrgList()){
				for (Deal deal: org.deals){
					if(deal.id==dealId){
						ArrayList<Invoice> arr =deal.invoices;
						return new PagedResult(filter.pageIndex>=0?new ArrayList<Object>
							(ExtendedUIUtils.filterPageSort(arr,filter,null,false)):arr,arr.size(),
							UIUtils.toDictionary("total",UIUtils.sum(arr,"invoiceAmount"),"count",arr.size()),false);
					}
				}
			}
			return new PagedResult(new ArrayList<Object>(),0, null, true);
		}
		public PagedResult getLineItemsForInvoice(float invoiceId, Filter filter)
		{
			for (Organization org : getDeepOrgList()){
				for (Deal deal : org.deals){
					for (Invoice invoice : deal.invoices){
						if(invoice.id==invoiceId){
							ArrayList<LineItem> arr =invoice.lineItems;
							return new PagedResult(filter.pageIndex>=0?new ArrayList<Object>
								(ExtendedUIUtils.filterPageSort(arr,filter, null, false)):arr,arr.size(),
								UIUtils.toDictionary("total",UIUtils.sum(arr,"lineItemAmount"),"count",org.deals.size()),false);
						}
					}
				}
			}
			return new PagedResult(new ArrayList<Object>(),0, null, true);
		}
		public Organization createOrganization(String legalName, Boolean deep){
			if(deep)_index++;
			CustomerOrganization  org = new CustomerOrganization();
			org.id= 20800 + Arrays.asList(companyNames).indexOf(legalName);
			org.legalName=legalName;
			org.headquarterAddress = createAddress();
			org.mailingAddress = createAddress();
			org.billingContact=createContact();
			org.salesContact=createContact();
			org.annualRevenue = getRandom(1000,60000);
			org.numEmployees = getRandom(1000,60000);
			org.earningsPerShare = getRandom(1,6) + (getRandom(1,99)/100);
			org.lastStockPrice = getRandom(10,30) + (getRandom(1,99)/100);
			org.url = "http://www.google.com/search?q="+legalName+"";
			org.chartUrl="http://www.flexicious.com/resources/images/chart" + getRandom(1,7)+".png";
			if(deep){
				for(int dealIdx =0;dealIdx<DEALS_PER_ORG;dealIdx++){
					org.deals.add(createDeal(dealIdx,org,deep));	
				}
			}
			return org;
		}
		
		@SuppressWarnings("deprecation")
		public Deal createDeal(int idx, CustomerOrganization org, Boolean deep)
		{
			Deal  deal = new Deal();
			deal.customer=org;
			deal.dealDate = getRandomDate();
			deal.dealDescription = "Project # "+(org.deals.size()+1)+" - " +org.legalName + " - "  + (deal.dealDate.getMonth()+1) + "/" +deal.dealDate.getYear();
			deal.dealStatus= getRandomReferenceData(SystemConstants.dealStatuses).cloneSpecial();
			deal.id = (org.id*10)+(idx);
			if(deep){
				
				for(int invoiceIDx =0;invoiceIDx<INVOICES_PER_DEAL;invoiceIDx++){
					deal.invoices.add(createInvoice(invoiceIDx,deal,deep));	
				}
			}
			setGlobals(deal);
			return deal;
		}
		public Invoice createInvoice(int idx, Deal deal, Boolean deep){
			Invoice  invoice = new Invoice();
			invoice.deal=deal;
			invoice.invoiceDate = getRandomDate();
			invoice.id = (deal.id*10)+idx;
			invoice.invoiceStatus= getRandomReferenceData(SystemConstants.invoiceStatuses).cloneSpecial();
			invoice.dueDate = invoice.invoiceDate;
			//DateUtils.dateAdd(DateUtils.DAY_OF_MONTH,30,invoice.invoiceDate);
			invoice.hasPdf = getRandom(1,2)==1;
			if(deep){
				for(int lineItemIDx =0;lineItemIDx<LINEITEMS_PER_INVOICE;lineItemIDx++){
					LineItem  lineItem = createInvoiceLineItem(lineItemIDx,invoice,deep);
					invoice.lineItems.add(lineItem);	
				}
			}
			setGlobals(invoice);
			return invoice;
		}
		public LineItem createInvoiceLineItem(int lineItemIdx, Invoice invoice, Boolean deep){
			LineItem  lineItem = new LineItem();
			lineItem.id=(invoice.id*10)+lineItemIdx;
			lineItem.invoice=invoice;
			lineItem.lineItemAmount=getRandom(10000,50000);
			lineItem.lineItemDescription = "Professional Services - " + getRandomReferenceData(SystemConstants.billableConsultants).cloneSpecial().name;
			lineItem.units = lineItem.lineItemAmount/100;
			setGlobals(lineItem);
			lineItems.add(lineItem);
			return lineItem;
		}
		public static ReferenceData getRandomReferenceData(ReferenceData[] arr){
			return arr[getRandom(0,arr.length-1)];
		}
		public static CommercialContact createContact()
		{
			CommercialContact commercialContact =new CommercialContact();
			commercialContact.firstName=firstNames[getRandom(0,firstNames.length-1)];
			commercialContact.lastName=lastNames[getRandom(0,lastNames.length-1)];
			commercialContact.homeAddress=createAddress();
			commercialContact.telephone=generatePhone();
			setGlobals(commercialContact);
			return commercialContact;
		}
		public static void setGlobals(BaseEntity entity){
			entity.addedBy=getSystemUser();
			entity.addedDate = getRandomDate();
			entity.updatedDate=entity.addedDate;
			entity.updatedBy=getSystemUser();
				
		}
		public static Date getRandomDate(){
			return new GregorianCalendar(getRandom(2005,2013),
					getRandom(0,11), getRandom(1,28),
					0, 0, 0).getTime();
		}
		public static String generatePhone(){
			return areaCodes[getRandom(0,3)] + "-" + 
				getRandom(100,999).toString()+ "-" + getRandom(1000,9999).toString();

		}
		private static SystemUser sysAdmin;
		public static SystemUser getSystemUser(){
			if(sysAdmin != null)return sysAdmin;
			
			SystemUser user =new SystemUser();
			user.addedBy=user;
			user.addedDate= new GregorianCalendar(2010,1,1).getTime();
			user.updatedBy = user;
			user.updatedDate = new GregorianCalendar(2010,1,1).getTime();
			user.id=1;
			user.firstName=firstNames[getRandom(0,firstNames.length-1)];
			user.lastName=lastNames[getRandom(0,lastNames.length-1)];
			user.loginNm ="system_admin";
			sysAdmin=user;
			return user;
		}
		
		public static Address createAddress(){
			Address  address =new Address();
			address.line1=getRandom(100,999).toString() + " " +streetNames[getRandom(0,streetNames.length-1)]+ " " + streetTypes[getRandom(0,streetTypes.length-1)];
			address.line2="Suite #" + getRandom(1,1000);
			address.city=SystemConstants.cities[getRandom(0,SystemConstants.cities.length-1)];
			address.state=SystemConstants.states[getRandom(0,SystemConstants.states.length-1)];
			address.country = SystemConstants.usCountry;
			return address;
		}
		public static Integer getRandom(float minNum, float maxNum)
		{
			return (int) (Math.ceil(Math.random() * (maxNum - minNum + 1)) + (minNum - 1));
		}


		
		private static final String [] areaCodes = new String[] {"201","732","212","646","800","866"};
		private static final String [] streetTypes = new String[] {"Ave","Blvd","Rd","St","Lane"};
		private static final String [] streetNames = new String[] {"Park","West","Newark","King","Gardner"};
		

		private static String [] companyNames=new String[] {"3M Co",
		"Abbott Laboratories",
		"Adobe Systems",
		"Advanced Micro Dev",
		"Aetna Inc",
		"Affiliated Computer Svcs",
		"AFLAC Inc",
		"Air Products & Chem",
		"Airgas Inc",
		"AK Steel Holding",
		"Akamai Technologies",
		"Alcoa Inc",
		"Allegheny Energy",
		"Allegheny Technologies",
		"Allergan, Inc",
		"Allstate Corp",
		"Altera Corp",
		"Altria Group",
		"Amazon.com Inc",
		"Amer Electric Pwr",
		"Amer Express",
		"Amer Tower",
		"Ameren Corp",
		"Ameriprise Financial",
		"AmerisourceBergen Corp",
		"Amgen Inc",
		"Amphenol Corp",
		"Anadarko Petroleum",
		"Aon Corp",
		"Apache Corp",
		"Apartment Investment & Mgmt",
		"Apollo Group",
		"Apple Inc",
		"Archer-Daniels-Midland",
		"Assurant Inc",
		"AT&T Inc",
		"Automatic Data Proc",
		"AutoNation Inc",
		"AutoZone Inc",
		"AvalonBay Communities",
		"Avery Dennison Corp",
		"Avon Products",
		"Baker Hughes Inc",
		"Ball Corp",
		"Bank of America",
		"Bank of New York Mellon",
		"Bard (C.R.)",
		"Baxter Intl",
		"BB&T Corp",
		"Becton, Dickinson",
		"Bed Bath & Beyond",
		"Bemis Co",
		"Best Buy",
		"Biogen Idec",
		"Black & Decker Corp",
		"BMC Software",
		"Boeing Co",
		"Boston Properties",
		"Boston Scientific",
		"Bristol-Myers Squibb",
		"Broadcom Corp",
		"Burlington Northn Santa Fe",
		"C.H. Robinson Worldwide",
		"CA Inc",
		"Cabot Oil & Gas",
		"Cameron Intl",
		"Capital One Financial",
		"Carnival Corp",
		"Caterpillar Inc",
		"CB Richard Ellis Grp",
		"Celgene Corp",
		"CenterPoint Energy",
		"Cephalon Inc",
		"CF Industries Hldgs",
		"Chesapeake Energy",
		"Chevron Corp",
		"Chubb Corp",
		"Cincinnati Financial",
		"Cintas Corp",
		"Cisco Systems",
		"Citigroup Inc",
		"Citrix Systems",
		"Clorox Co",
		"CME Group Inc",
		"CMS Energy",
		"Coach Inc",
		"Coca-Cola Co",
		"Coca-Cola Enterprises",
		"Cognizant Tech Solutions",
		"Colgate-Palmolive",
		"Comcast Cl",
		"Comerica Inc",
		"Compuware Corp",
		"ConAgra Foods",
		"ConocoPhillips",
		"CONSOL Energy",
		"Consolidated Edison",
		"Constellation Brands",
		"Constellation Energy Group",
		"Convergys Corp",
		"Corning Inc",
		"Costco Wholesale",
		"Coventry Health Care",
		"CSX Corp",
		"Cummins Inc",
		"Danaher Corp",
		"Darden Restaurants",
		"DaVita Inc",
		"Dean Foods",
		"DENTSPLY Intl",
		"Devon Energy",
		"DeVry Inc",
		"Diamond Offshore Drilling",
		"Discover Financial Svcs",
		"Dominion Resources",
		"Donnelley(R.R.)& Sons",
		"Dover Corp",
		"Dow Chemical",
		"DTE Energy",
		"Duke Energy",
		"Dun & Bradstreet",
		"duPont(E.I.)deNemours",
		"E Trade Financial",
		"Eastman Chemical",
		"Eastman Kodak",
		"Eaton Corp",
		"eBay Inc",
		"Ecolab Inc",
		"El Paso Corp",
		"EMC Corp",
		"Emerson Electric",
		"ENSCO Intl",
		"Entergy Corp",
		"EQT Corp",
		"Equifax Inc",
		"Equity Residential",
		"Exelon Corp",
		"Expedia Inc",
		"Expeditors Intl,Wash",
		"Express Scripts",
		"Exxon Mobil",
		"Family Dollar Stores",
		"Fastenal Co",
		"Federated Investors ",
		"FedEx Corp",
		"Fidelity Natl Info Svcs",
		"Fifth Third Bancorp",
		"First Horizon Natl",
		"FirstEnergy Corp",
		"Fiserv Inc",
		"FLIR Systems",
		"Flowserve Corp",
		"FMC Corp",
		"FMC Technologies",
		"Ford Motor",
		"Forest Labs",
		"Fortune Brands",
		"FPL Group",
		"Franklin Resources",
		"Freept McMoRan Copper&Gold",
		"Frontier Communications",
		"Gannett Co",
		"Genl Dynamics",
		"Genl Electric",
		"Genl Mills",
		"Genuine Parts",
		"Genworth Financial",
		"Genzyme Corp",
		"Gilead Sciences",
		"Goldman Sachs Group",
		"Goodrich Corp",
		"Goodyear Tire & Rub",
		"Google Inc",
		"Grainger (W.W.)",
		"Halliburton Co",
		"Harley-Davidson",
		"Harman Intl",
		"Harris Corp",
		"Hartford Finl Svcs Gp",
		"Hasbro Inc",
		"HCP Inc",
		"Health Care REIT",
		"Hershey Co",
		"Hess Corp",
		"Honeywell Intl",
		"Hospira Inc",
		"Host Hotels & Resorts",
		"Hudson City Bancorp",
		"Humana Inc",
		"Huntington Bancshares",
		"Illinois Tool Works",
		"IMS Health",
		"Intel Corp",
		"IntercontinentalExchange Inc",
		"Interpublic Grp Cos",
		"Intl Bus. Machines",
		"Intl Flavors/Fragr",
		"Intl Paper",
		"Intuitive Surgical",
		"INVESCO Ltd",
		"Iron Mountain",
		"ITT Corp",
		"Jabil Circuit",
		"Janus Capital Group",
		"Johnson & Johnson",
		"Johnson Controls",
		"JPMorgan Chase & Co",
		"Juniper Networks",
		"KB HOME",
		"Kellogg Co",
		"KeyCorp",
		"Kimberly-Clark",
		"Kimco Realty",
		"KLA-Tencor Corp",
		"Kraft Foods",
		"L-3 Communications Hldgs",
		"Laboratory Corp Amer Hldgs",
		"Lauder (Estee) Co",
		"Legg Mason Inc",
		"Leggett & Platt",
		"Lennar Corp",
		"Lexmark Intl",
		"Life Technologies",
		"Lilly (Eli)",
		"Lincoln Natl Corp",
		"Linear Technology Corp",
		"Lockheed Martin",
		"Loews Corp",
		"Lorillard Inc",
		"LSI Corp",
		"M&T Bank",
		"Marathon Oil",
		"Marriott Intl",
		"Marsh & McLennan",
		"Marshall & Ilsley",
		"Masco Corp",
		"Massey Energy",
		"MasterCard Inc",
		"Mattel, Inc",
		"McAfee Inc",
		"McCormick & Co",
		"McDonalds Corp",
		"McGraw-Hill Companies",
		"McKesson Corp",
		"MeadWestvaco Corp",
		"Medco Health Solutions",
		"MEMC Electronic Materials",
		"Merck & Co",
		"Meredith Corp",
		"MetLife Inc",
		"Microchip Technology",
		"Micron Technology",
		"Microsoft Corp",
		"Molex Inc",
		"Molson Coors Brewing",
		"Monsanto Co",
		"Monster Worldwide",
		"Moodys Corp",
		"Morgan Stanley",
		"Motorola, Inc",
		"Murphy Oil",
		"Mylan Inc",
		"Nabors Indus",
		"Natl Oilwell Varco",
		"Natl Semiconductor",
		"New York Times",
		"Newell Rubbermaid",
		"Newmont Mining",
		"News Corp ",
		"NICOR Inc",
		"NIKE, Inc",
		"NiSource Inc",
		"Noble Energy",
		"Norfolk Southern",
		"Northeast Utilities",
		"Northern Trust",
		"Northrop Grumman",
		"Novellus Systems",
		"Nucor Corp",
		"NYSE Euronext",
		"OReilly Automotive",
		"Occidental Petroleum",
		"Office Depot",
		"Omnicom Group",
		"Oracle Corp",
		"Owens-Illinois",
		"PACCAR Inc",
		"Pactiv Corp",
		"Parker-Hannifin",
		"Paychex Inc",
		"Peabody Energy",
		"Peoples United Financial",
		"Pepco Holdings",
		"Pepsi Bottling Group",
		"PepsiCo Inc",
		"PerkinElmer Inc",
		"Pfizer, Inc",
		"PG&E Corp",
		"Philip Morris Intl",
		"Pinnacle West Capital",
		"Pioneer Natural Resources",
		"Pitney Bowes",
		"Plum Creek Timber",
		"PNC Financial Services Group",
		"Polo Ralph Lauren",
		"PPG Indus",
		"PPL Corp",
		"Praxair Inc",
		"Precision Castparts",
		"Principal Financial Grp",
		"Procter & Gamble",
		"Progress Energy",
		"Progressive Corp,Ohio",
		"ProLogis",
		"Prudential Financial",
		"Public Svc Enterprises",
		"Pulte Homes",
		"QLogic Corp",
		"QUALCOMM Inc",
		"Quanta Services",
		"Quest Diagnostics",
		"Questar Corp",
		"Qwest Communications Intl",
		"RadioShack Corp",
		"Range Resources",
		"Raytheon Co",
		"Red Hat Inc",
		"Regions Financial",
		"Republic Services",
		"Reynolds American",
		"Robert Half Intl",
		"Rockwell Collins",
		"Rowan Cos",
		"Ryder System",
		"Safeway Inc",
		"SanDisk Corp",
		"SCANA Corp",
		"Schering-Plough",
		"Schlumberger Ltd",
		"Schwab(Charles)Corp",
		"Sealed Air",
		"Sherwin-Williams",
		"Sigma-Aldrich",
		"Simon Property Group",
		"SLM Corp",
		"Smith Intl",
		"Snap-On Inc",
		"Southern Co",
		"Southwest Airlines",
		"Southwestern Energy",
		"Sprint Nextel Corp",
		"St. Jude Medical",
		"Stanley Works",
		"Starwood Hotels&Res Worldwide",
		"State Street Corp",
		"Stericycle Inc",
		"Stryker Corp",
		"SunTrust Banks",
		"Supervalu Inc",
		"Symantec Corp",
		"Sysco Corp",
		"T.Rowe Price Group",
		"TECO Energy",
		"Tellabs, Inc",
		"Tenet Healthcare",
		"Teradyne Inc",
		"Texas Instruments",
		"Textron, Inc",
		"Thermo Fisher Scientific",
		"Time Warner",
		"Torchmark Corp",
		"Total System Svcs",
		"Travelers Cos",
		"U.S. Bancorp",
		"U.S. Steel",
		"Union Pacific",
		"United Parcel",
		"United Technologies",
		"UnitedHealth Group",
		"Unum Group",
		"Valero Energy",
		"Varian Medical Systems",
		"Ventas Inc",
		"Verizon Communications",
		"VF Corp",
		"Viacom Inc",
		"Vornado Realty Trust",
		"Vulcan Materials",
		"Walgreen Co",
		"Washington Post",
		"Waste Management",
		"Waters Corp",
		"Watson Pharmaceuticals",
		"WellPoint Inc",
		"Wells Fargo",
		"Western Digital",
		"Western Union",
		"Whirlpool Corp",
		"Whole Foods Market",
		"Williams Cos",
		"Wisconsin Energy Corp",
		"Wyndham Worldwide",
		"Wynn Resorts",
		"Xcel Energy",
		"Xerox Corp",
		"Xilinx Inc",
		"XL Capital Ltd",
		"XTO Energy",
		"Yahoo Inc",
		"Yum Brands",
		"Zimmer Holdings"
		};
		
		private static String[] lastNames=new String[]{"SMITH",
			"JOHNSON",
			"WILLIAMS",
			"BROWN",
			"JONES",
			"MILLER",
			"DAVIS",
			"GARCIA",
			"RODRIGUEZ",
			"WILSON",
			"MARTINEZ",
			"ANDERSON",
			"TAYLOR",
			"THOMAS",
			"HERNANDEZ",
			"MOORE",
			"MARTIN",
			"JACKSON",
			"THOMPSON",
			"WHITE",
			"LOPEZ",
			"LEE",
			"GONZALEZ",
			"HARRIS",
			"CLARK",
			"LEWIS",
			"ROBINSON",
			"WALKER",
			"PEREZ",
			"HALL",
			"YOUNG",
			"ALLEN",
			"SANCHEZ",
			"WRIGHT",
			"KING",
			"SCOTT",
	};
		
		private static String[] firstNames= new String[]{"LATONYA",
		"CANDY",
		"MORGAN",
		"CONSUELO",
		"TAMIKA",
		"ROSETTA",
		"DEBORA",
		"CHERIE",
		"POLLY",
		"DINA",
		"JEWELL",
		"FAY",
		"JILLIAN",
		"DOROTHEA",
		"NELL",
		"TRUDY",
		"ESPERANZA",
		"PATRICA",
		"KIMBERLEY",
		"FRANK",
		"SCOTT",
		"ERIC",
		"STEPHEN",
		"ANDREW",
		"RAYMOND",
		"GREGORY",
		"JOSHUA",
		"JERRY",
		"DENNIS",
		"WALTER",
		"PATRICK",
		"PETER",
		"HAROLD",
		"DOUGLAS",
		"HENRY",
		"CARL",
		"ARTHUR",
		"RYAN"
		};
	}
