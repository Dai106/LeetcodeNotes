import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q17LetterCombinationOfPhoneNum {
	public static void main(String[] args) {
		String input = "23";
		List<String> result = withHashMap(input);
		System.out.println(result);
	}
	
	public static List<String> withHashMap(String digits){
		//result
		Queue<String> result = new LinkedList<>();
		
		//if it is an empty string
		if(digits.isEmpty()) {
			return (List<String>)result;
		}
		
		//hash map to store the representation of each char
		HashMap<Character, char[]> map = new HashMap<>();
		char[] c2 = new char[] {'a','b','c'};
		char[] c3 = new char[] {'d','e','f'};
		char[] c4 = new char[] {'g','h','i'};
		char[] c5 = new char[] {'j','k','l'};
		char[] c6 = new char[] {'m','n','o'};
		char[] c7 = new char[] {'p','q','r','s'};
		char[] c8 = new char[] {'t','u','v'};
		char[] c9 = new char[] {'w','x','y','z'};
		
		map.put('2', c2);
		map.put('3', c3);
		map.put('4', c4);
		map.put('5', c5);
		map.put('6', c6);
		map.put('7', c7);
		map.put('8', c8);
		map.put('9', c9);
		
		for(int i = 0; i<digits.length(); i++) {
			char[] current = map.get(digits.charAt(i));
			if(result.isEmpty()) {
				for(char c: current) {
					result.add("" + c);
				}
			}else {
				int size = result.size();
				for(int j = 0; j<size; j++) {
					String s = result.remove();
					for(char c: current) {
						result.add(s+c);
					}
				}
			}
			
		}
		
		return (List<String>) result;
		
	}
}
