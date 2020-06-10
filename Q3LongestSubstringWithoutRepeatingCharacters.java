import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
public class Q3LongestSubstringWithoutRepeatingCharacters {
	public static void main(String[] args) {
		//String s = "abcabcbb";
		String s = "pwwkew";
		//String s = "aab";
		//String s = "tmmzuxt";
		int result = withMap(s);
		System.out.println(result);
	}
	
	// brute force
	public static int bruteForce(String s) {
		int answer = 0;
		for(int i = 0; i<s.length(); i++) {
			for(int j = i+1; j<=s.length(); j++) {
				if(allUnique(s, i, j)) {
					answer = Math.max(answer, j-i);
				}
			}
		}
		return answer;
	}
	
	private static boolean allUnique(String s, int start, int end) {
		// similar idea as two sums
		Set<Character> set = new HashSet<>();
		for(int i = start; i<end; i++) {
			Character c = s.charAt(i);
			if(set.contains(c)) {
				return false;
			}
			set.add(c);
		}
		return true;
	}
	
	public static int slidingWindow(String s) {
		// special case
		if(s == null) {
			return 0;
		}
		int answer = 0;
		int n = s.length();
		// i is the beginning of the window
		int i = 0;
		// j is the end of the window
		int j = 0;
		
		Set<Character> set = new HashSet<>();
		
		while( i < n && j < n) {
			Character c = s.charAt(j);
			//if not included in the set
			if(!set.contains(c)) {
				// add it to the set
				set.add(c);
				
				// increase j(the ending of the window)
				j ++;
				// update the answer
				// j - i because i update j, now j is excluded
				answer = Math.max(answer, j - i);
				
			}else {
				// remove the char
				set.remove(s.charAt(i));
				i++;
			}
		}
		return answer;
	}
	
	public static int withMap(String s) {
		// special case
		if(s == null) {
			return 0;
		}
		
		int i = 0;
		int j = 0;
		int answer = 0;
		int n = s.length();
		HashMap<Character, Integer> map = new HashMap<>();
		
		while(i < n && j < n) {
			Character c = s.charAt(j);
			// if in the map
			if(!map.containsKey(c)) {
				map.put(c, j);
				j ++;
				answer = Math.max(answer, j - i);
			}else {
				if(map.get(c) >= i) {
					i = map.get(c) + 1;
				}
				j ++;
				answer = Math.max(answer, j - i);
			}
		}
		return answer;
	}
}
