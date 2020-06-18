import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Q18fourSums {
	public static void main(String[] args) {
		//int[] nums = new int[] {1, 0, -1, 0, -2, 2};
		int[] nums = new int[] {0,0,0,0};
		//int[] nums = new int[] {-3,-2,-1,0,0,1,2,3};
		
		List<List<Integer>> result = twoPointer(nums, 0);
		System.out.println(result);
	}
	
	public static List<List<Integer>> twoPointer(int[] nums, int target){
		List<List<Integer>> result = new LinkedList<>();
		
		//if nums does not satisfy the requirement
		if(nums == null || nums.length < 4) {
			return result;
		}
		
		//sort the array first
		Arrays.sort(nums);
		
		for(int i = 0; i < nums.length-3; i++) {
			// skip the repeating element for i
			if(i != 0 && nums[i] == nums[i-1]) {
				continue;
			}
			for(int j = i+1; j<nums.length-2; j++) {
				if(j > i+1 && nums[j] == nums[j-1]) {
					continue;
				}
				int start = j+1;
				int end = nums.length-1;
				int complement = target - nums[i] - nums[j];
				while(start < end) {
					if(nums[start] + nums[end] == complement) {
						result.add(Arrays.asList(nums[i], nums[j], nums[start], nums[end]));
						//skip all the repeating elements
						while(start < end && nums[start] == nums[start+1]) start ++;
						while(start < end && nums[end] == nums[end-1]) end --;
						start ++;
						end --;
					}else if(nums[start] + nums[end] < complement) {
						start ++;
					}else {
						end --;
					}
				}
			}
		}
		
		return result;
	}
}
