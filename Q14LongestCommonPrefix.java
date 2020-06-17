
public class Q14LongestCommonPrefix {
	
	public static void main(String[] args) {
		String[] strs1 = {"flower", "flow", "flight"};
		String[] strs2 = {"dog", "racecar", "car"};
		String[] strs3 = {"aa", "a"};
		String result1 = horizontalScanning(strs1);
		String result2 = horizontalScanning(strs2);
		String result3 = horizontalScanning(strs3);
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
	}
	 
	/**
	// horizontalScanning
	public static String horizontalScanning(String[] strs) {
		// if that's an empty string
		if(strs.length == 0 || strs == null) {
			return "";
		}
		
		String prefix = strs[0];
		for(int i = 1; i<strs.length; i++) {
			while(strs[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() -1);
				if(prefix.isEmpty()) {
					return "";
				}
			}
		}
		return prefix;
	}
	*/
	public static String horizontalScanning(String[] strs) {
		// if that's an empty array
		if(strs.length == 0 || strs == null) {
			return "";
		}
		String prefix = strs[0];
		for(int i = 1; i<strs.length; i++) {
			int n = Math.min(strs[i].length(), prefix.length());
			String s = strs[i];
			int j = 0;
			while(j<n) {
				if(prefix.charAt(j) != s.charAt(j)) {
					break;
				}
				j++;
			}
			prefix = prefix.substring(0,j);
		}
		return prefix;
	}
	
	// vertical scanning
	public static String verticalScanning(String[] strs) {
		if(strs.length == 0 || strs == null) {
			return "";
		}
		for(int i = 0; i<strs[0].length(); i++) {
			char c = strs[0].charAt(i);
			for(int j = 1; j<strs.length; j++) {
				if(i == strs[j].length() || strs[j].charAt(i) != c) {
					return strs[0].substring(0, i);
				}
			}
		}
		return strs[0];
	}
	
	//divide and conquer
	public static String divideAndConquer(String[] strs) {
		if(strs.length == 0 || strs == null) {
			return "";
		}
		return longestCommonPrefix(strs, 0, strs.length - 1);
	}
	private static String longestCommonPrefix(String[] strs, int left, int right) {
		if(left == right) {
			return strs[left];
		}else {
			int mid = (left + right)/2;
			String lcpLeft = longestCommonPrefix(strs, left, mid);
			String lcpRight = longestCommonPrefix(strs, mid+1, right);
			return commonPrefix(lcpLeft, lcpRight);
		}
	}
	
	private static String commonPrefix(String left, String right) {
		int min = Math.min(left.length(), right.length());
		for(int i = 0; i<min; i++) {
			if(left.charAt(i) != right.charAt(i)) {
				return left.substring(0, i);
			}
		}
		return left.substring(0, min);
	}
	
	
	public static String binarySearch(String[] strs) {
		if(strs == null || strs.length == 0) {
			return "";
		}
		
		int minLen = Integer.MAX_VALUE;
		// find the shortest element
		for(String str: strs) {
			minLen = Math.min(minLen, str.length());
		}
		
		int low = 1;
		int high = minLen;
		while(low <= high) {
			int middle = (low + high)/2;
			if(isCommonPrefix(strs, middle)) {
				low = middle + 1;
			}else {
				high = middle - 1;
			}
		}
		return strs[0].substring(0, (low + high)/2);
		
	}
	
	private static boolean isCommonPrefix(String[] strs, int len) {
		String str1 = strs[0].substring(0, len);
		for(int i = 1; i < strs.length; i++) {
			if(! strs[i].startsWith(str1)) {
				return false;
			}
		}
		return true;
	}
	
	
}
