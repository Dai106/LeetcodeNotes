import java.util.HashMap;

public class Q13RomanToInteger {
	public static void main(String[] args) {
		int result = switchCase("MCMXCIV");
		System.out.println(result);
	}
	public static int usingMap(String s) {
		HashMap<Character, Integer> map = new HashMap<>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		
		char[] input = s.toCharArray();
		int result = 0;
		for(int i=0, j=1; j<input.length; i++, j++) {
			if(map.get(input[i]) >= map.get(input[j])) {
				result += map.get(input[i]);
			}else {
				result -= map.get(input[i]);
			}
		}
		result += map.get(input[input.length-1]);
		return result;
	}
	
	public static int switchCase(String s) {
		int result = 0;
		int prevVal = 0;
		for(int i = s.length() -1; i>=0; i--) {
			int currVal = 0;
			char curr = s.charAt(i);
			switch(curr) {
			case 'I':
				currVal = 1;
				break;
			case 'V':
				currVal = 5;
				break;
			case 'X':
				currVal = 10;
				break;
			case 'L':
				currVal = 50;
				break;
			case 'C':
				currVal = 100;
				break;
			case 'D':
				currVal = 500;
				break;
			case'M':
				currVal = 1000;
				break;
			}
			
			if(prevVal != 0 && currVal < prevVal) {
				result -= currVal;
			}else {
				result += currVal;
			}
			
			prevVal = currVal;
		}
		
		return result;
	}
}
