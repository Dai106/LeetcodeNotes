import java.util.Arrays;

// return the sum of the 3 integers which has the closest sum
public class Q16ThreeSumClosest {
	public static void main(String[] args) {
		int[] nums = new int[] {-1,2,1,-4};
		int result = twoPointers(nums, 1);
		System.out.println(result);
		
	}
	
	public static int twoPointers(int[] nums, int target) {
		
		
		//sort
		Arrays.sort(nums);
		
		int diff = Integer.MAX_VALUE;
		
		for(int i = 0; i<nums.length-2 && diff != 0; i++ ) {
			int low = i+1;
			int high = nums.length - 1;
			while( low < high ) {
				int sum = nums[i] + nums[low] + nums[high];
				if(Math.abs(target - sum) < Math.abs(diff)) {
					diff = target - sum;
				}
				if(sum < target) {
					low ++;
				}else {
					high --;
				}
			}
		}
		return target - diff;
	}
	
	
}
