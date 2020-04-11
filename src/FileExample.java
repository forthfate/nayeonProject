import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FileExample{
	private static final String testData = "1.1_MFA_CHECK_RESULT= OK";
	private static String currentPath;
//	public static final String FileAValue="";
	//public static final String FileBValue="";
	
	public static String getAValue(String sample) throws Exception{	
		
		//init
		File SampleA = new File("O365_Sample_A.ini");
		
		//read
		FileInputStream fiA = new FileInputStream(SampleA);
		StringBuilder stringBuilderA = new StringBuilder();
		
		//read(binary to String)
		int i = 0;
		while((i = fiA.read()) != -1) {
			stringBuilderA.append((char)i); 
		}
		
		//set key and value (FileA)
		String testA = stringBuilderA.toString();
		String[] split1 = testA.split("\n"); 
		
		//get as data
		Map<String, String> mapA = new HashMap();
	
		for(int s=0; s < split1.length; s++) {

			String[] foo = split1[s].split(" = ");
			
			if(foo[0].equals("1.1_MULTY_PLATFORM_AUTHENTICATION")) {
				mapA.put(foo[0], foo[1].trim());//split이 실패할 경우에는 foo의 length는 1이다
				
				//output
				System.out.println(foo[0] + ": " + mapA.get(foo[0]));
				
				if(mapA.get(foo[0]).trim().equals("true;")) {
					return "OK";

				} else {
					return "NG";
				}
			}
		
		}
		return null;
		
		}
	
	public static String getValue(String itemName) throws Exception{
		switch(itemName) {
		case("MFA"):
			return getAValue("1.1_MULTY_PLATFORM_AUTHENTICATION");

		case("about_admin"):
			return getBValue("1.2_ADMINISTATOR_COUNT");

		}
		
		
		return null;
		
	}

	
	public static String getBValue(String sample2) throws Exception {
		
		//getBValue("1.2_ADMINISTATOR_COUNT");
		File SampleB = new File("O365_Sample_B.ini");
		FileInputStream fiB = new FileInputStream(SampleB);
		StringBuilder stringBuilderB = new StringBuilder();
		
		int i = 0;
		while((i = fiB.read()) != -1) {
			stringBuilderB.append((char)i); 
		}
		
			//set key and value (FileB)
			String testB = stringBuilderB.toString();
			String[] split2 = testB.split("\n"); 
				
			//get as data
			Map<String, String> mapB = new HashMap();
						
			for(int s=0; s < split2.length; s++) {
				
				String[] foo = split2[s].split(" = ");
					
				if(foo[0].equals(sample2)) {	
				mapB.put(foo[0], foo[1].trim().replace(";", ""));
					
				//output
				System.out.println(foo[0] + ": " + mapB.get(foo[0]));
				int foo2 = Integer.valueOf(mapB.get(foo[0]).trim());
					
				if(foo2 > 2 && foo2 <5) {
					return "OK";
				} else {
					return "NG";
				}
			}
	
		}

		return null;
	}
	
}
