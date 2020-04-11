import java.util.HashMap;
import java.util.Map;

public class MapExample {
	public MapExample() {
		String test = "1.1_MFA_CHECK_RESULT: OK";
		String[] foo = test.split(": ");
		
		Map<String, String> map = new HashMap();
		
		map.put(foo[0], foo[1]);
		map.put("aaa", foo[1]);
		
		//System.out.println("1.1_MFA_CHECK_RESULT:" + map.get("aaa"));
		//System.out.println("key!!!: "+map.keySet());
	}

}
