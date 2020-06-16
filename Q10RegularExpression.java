
public class Q10RegularExpression {
	 public boolean recursiveIsMatch(String s, String p) {
	        
	        // case: if p is empty
	        if(p.length() == 0){
	            return s.length() == 0;
	        }
	        
	        //the important one is to check if the second char is *
	        if(p.length() > 1 && p.charAt(1) == '*'){
	            // if the first char has 0 match
	            if(recursiveIsMatch(s, p.substring(2))){
	                return true;
	            }
	            // if the first char has 1 or more match
	            if( s.length()>0 && (p.charAt(0)=='.' || s.charAt(0) == p.charAt(0)) ){
	                return recursiveIsMatch(s.substring(1), p);
	            }
	            // otherwise
	            return false;
	        }else{
	            //if the first char matches
	            if( s.length()>0 && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0))) {
	                return recursiveIsMatch(s.substring(1), p.substring(1));
	            }
	            return false;
	        }
	        
	    }
	 
	 public boolean dpIsMatch(String s, String p) {
	        
	        //special cases
	        if(p == null || p.length() == 0){
	            return (s == null || s.length()==0);
	        }
	        
	        //declare the dp
	        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
	        dp[0][0] = true;
	        
	        //initialize the first row
	        for(int j = 2; j<=p.length(); j++){
	            if(p.charAt(j-1) == '*' && dp[0][j-2]){
	                dp[0][j] = true;
	            }
	        }
	        
	       for(int i = 1; i<=s.length(); i++) {
				for(int j =1 ; j<=p.length(); j++) {
					if( p.charAt(j-1)=='.' || p.charAt(j-1) == s.charAt(i-1) ) {
						dp[i][j] = dp[i-1][j-1]; 
					}else if(p.charAt(j-1)=='*') {
						   dp[i][j] = dp[i][j-2] || ((s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.') && dp[i-1][j]); 
					}
					else {
						dp[i][j] = false;
					}
				}
			}
	        return dp[s.length()][p.length()];
	    }
}
