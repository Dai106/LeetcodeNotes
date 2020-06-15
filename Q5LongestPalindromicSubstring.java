
public class Q5LongestPalindromicSubstring {
	public static void main(String[] args) {
		//String s = "babad";
		String s = "bb";
		String result = DP2(s);
		if(result.length() == 0) {
			System.out.println("There is no palindromic Substring");
		}else {
			System.out.println(result);
		}
	}
	
	public static String bruteForce(String s) {
		int max = 0;
		String result = "";
		for(int i = 0; i< s.length(); i ++) {
			for(int j = i+1; j <= s.length(); j++) {
				String substring = s.substring(i,j);
				if(isPalindromic(substring) && substring.length() > max) {
					
					result = s.substring(i,j);
					max = result.length();
				}
			}
		}
		return result;
	}
	
	private static boolean isPalindromic(String s) {
		int head = 0;
		int tail = s.length() - 1;
		while(head <= tail) {
			if(s.charAt(head) != s.charAt(tail)) {
				return false;
			}
			head ++;
			tail --;
		}
		return true;
	}
	
	
	public static String LCS(String s) {
		
		// reverse the string
		int n = s.length();
		if(n == 0) {
			return "";
		}
		char[] str = s.toCharArray();
		char[] strRev = new char[n];
		int index = 0;
		for(char c: str) {
			strRev[n - index - 1] = c;
			index++;
		}
		
		// create a table to apply dp
		int[][] dp = new int[n+1][n+1];
		int max = 0;
		int maxEnd = 0;
		
		for(int i = 0; i<=n; i++) {
			for(int j = 0; j<=n; j++) {
				// first row and first column
				if(i == 0 || j == 0) {
					dp[i][j] = 0;
				}else if(str[i-1] == strRev[j-1]) {
					// since i and j are indices in the table
					// to apply in the string, need to -1
					dp[i][j] = dp[i-1][j-1] + 1;
					
				}else {
					dp[i][j] = 0;
				}
				
				if(dp[i][j] > max) {
					int befRev = n - j + 1;
					if(befRev + dp[i][j] - 1 == i) {
						max = dp[i][j];
						maxEnd = i-1;
					}
					
				}
			}
		}
		
		return s.substring(maxEnd - max + 1, maxEnd + 1);
		
	}
	
	public static String ExpandFromCenter(String s) {
		int max = 0;
		int idx = 0;
		for(int i = 0; i< s.length(); i++) {
			int len1 = extend(s, i, i); // for odd length case
			int len2 = extend(s, i, i+1); // for even length case
			if(max < Math.max(len1,  len2)) {
				max = Math.max(len1, len2);
				idx = (len1 > len2) ? (i - len1/2) : (i - len2/2 + 1);
			}
		}
		return s.substring(idx, idx + max);
	}
	
	private static int extend(String s, int i, int j) {
		for(; i>=0 && j < s.length(); i--, j++) {
			if(s.charAt(i) != s.charAt(j)) {
				break;
			}
		}
		return j - i - 2 + 1;
		// 2 means current two unmatched char
	}
	
	public static String DP1(String s) {
		//check for special case
		if(s == null || s.length() <= 1) {
			return s;
		}
		
		int length = s.length();
		boolean[][] p = new boolean[length][length];
		int maxLen = 0;
		String result = "";
		for(int len = 1; len<=length; len++) {
			for(int start = 0; start < length; start++) {
				int end = start + len - 1;
				if(end >= length) {
					break;
				}
				p[start][end] = (len == 1 || len == 2 || p[start + 1][end - 1]) && s.charAt(start) == s.charAt(end);
				if(p[start][end] && len > maxLen) {
					maxLen = len;
					result = s.substring(start, end+1);
				}
			}
		}
		return result;
	}
	
	public static String DP2(String s) {
	
		    int n = s.length();
		    String res = "";
		    boolean[][] dp = new boolean[n][n];
		    for (int i = n - 1; i >= 0; i--) {
		        for (int j = i; j < n; j++) {
		            dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1]); //j - i 代表长度减去 1        
		            if (dp[i][j] &&  j - i + 1 > res.length()) {
		                res = s.substring(i, j + 1);
		            }
		        }
		    }
		    return res;
		

	}
	
	public static String Manacher(String s) {
		// to initialize, we insert # around every char
		// and to avoid out of range, we add two different char at the beginning and the end
		String t = "$#";
		for(int i = 0; i< s.length(); i++) {
			t += s.charAt(i);
			t += "#";
		}
		
		// now we need to process t
		// p[i] is the radius of palindromic Substring with center at i
		int[] p = new int[t.length()];
		// mx is the current found right most char of palindromic substring
		int mx = 0;
		// id is the mx corresponding center
		int id = 0;
		// result length and result center
		int resultLen = 0;
		int resultCenter = 0;
		
		for(int i = 1; i<t.length() -1; i++) {
			//if i is inside the boundary
			if(mx > i) {
				p[i] = Math.min(p[2*id - i], mx-i);
			}else {
				// if i is outside the boundary
				p[i] = 1;
			}
			
			// match for the outside boundary cases
			while((i-p[i])>=0 //the left side is inside the boundary
					&& (i+p[i])<t.length()-1 // the right side is inside the boundary
					&& (t.charAt(i-p[i]) == t.charAt(i+p[i])))// left char == right char
			{
				p[i] ++; //increment p[i]
			}
			
			if(mx < i+p[i]) {
				// if we find a further right bound, update
				mx = i+p[i];
				id = i;
			}
			if(resultLen < p[i]) {
				// if we find a longer substring, update
				resultLen = p[i];
				resultCenter = i;
			}
			
		}
		return s.substring( (resultCenter - resultLen)/2, (resultCenter - resultLen)/2 + resultLen-1);
	}
}












