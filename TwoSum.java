import java.util.HashMap;
import java.util.Map;
public class TwoSum {
	public static void main(String[] args) {
		int[] nums = new int[] {2,7,11,15};
		//int[] nums = {};
		//int[] nums = null;
		int target = 9;
		int[] result = bruteForce(nums, target);
		for(int i: result) {
			System.out.print(i + " ");
		}
	}
	public static int[] bruteForce(int[] nums, int target) {
		if(nums == null) {
			throw new IllegalArgumentException("Illegal input array");
		}
		// loop through all the elements
		for(int i = 0; i < nums.length; i++) {
			for(int j = i+1; j < nums.length; j++) {
				if(nums[i] + nums[j] == target) {
					return new int[] {i, j};
				}
			}
		}
		// else if there's no solution
		throw new IllegalArgumentException("No such solution");
	}
	
	public static int[] withHashTable(int[] nums, int target) {
		if(nums == null) {
			throw new IllegalArgumentException("Illegal input array");
		}
		//put the elements of nums in hash table
		Map<Integer, Integer> map = new HashMap<>();
		// the first integer is the value of element
		// the second integer is its index
		for(int i = 0; i< nums.length; i++) {
			map.put(nums[i], i);
		}
		
		// loop through
		for(int i = 0; i< nums.length; i++) {
			int complement = target - nums[i];
			if(map.containsKey(complement)) {
				int index = map.get(complement);
				if(index != i) {
					return new int[] {i, index};
				}
			}
		}
		throw new IllegalArgumentException("No such solution");
	}
	
	public static int[] optimize(int[] nums, int target) {
		if(nums == null) {
			throw new IllegalArgumentException("Illegal input array");
		}
		
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i<nums.length; i++) {
			int complement = target - nums[i];
			if(map.containsKey(complement) && map.get(complement) != i) {
				return new int[] {i, map.get(complement)};
			}
			map.put(nums[i], i);
		}
		throw new IllegalArgumentException("No such solution");
	}
}
