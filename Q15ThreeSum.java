
import java.util.Arrays;

import java.util.LinkedList;
import java.util.List;

public class Q15ThreeSum {
	
	public static void main(String[] args) {
		//-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6
		int[] a = new int[] {-1, 0, 1, 2, -1, -4};
		for(int i: a) {
			System.out.print(i + " ");
		}
		System.out.println();
		List<List<Integer>> res = bruteForce(a);
		System.out.println(res);
	}

	public static List<List<Integer>> bruteForce(int[] nums){
		
		List<List<Integer>> res = new LinkedList<>();
		
		// sort the array
		Arrays.sort(nums);
		
		// loop
		for(int i = 0; i<nums.length-2; i++) {
			// i without duplicate, != to the previous one
			if(i == 0 || (i>0 && nums[i] != nums[i-1])) {
				int low = i+1;
				int high = nums.length - 1;
				int sum = 0 - nums[i];
				
				// find the other two
				while(low < high) {
					if(nums[low] + nums[high] == sum) {
						res.add(Arrays.asList(nums[i], nums[low], nums[high]));
						// skip all the repeated elements
						while(low < high && nums[low] == nums[low+1]) low ++;
						while(low < high && nums[high] == nums[high-1]) high --;
						low ++;
						high --;
					}else if(nums[low] + nums[high] < sum) {
						low ++;
					}else {
						high --;
					}
				}
			}
		}
		
		return res;
	}
}
