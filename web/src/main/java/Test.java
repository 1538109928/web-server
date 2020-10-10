import java.util.Arrays;
import java.util.HashMap;

public class Test {

    public int cutRope(int target) {
        if (target == 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }
        int[] nums = new int[target + 1];
        for (int num : nums) {
            num = -1;
        }
        return getMaxSum(target, nums);
    }

    private int getMaxSum(int target, int[] nums) {
        if (target <= 4) {
            return target;
        }
        if (nums[target] != -1) {
            return nums[target];
        }
        int ret = 0;
        for (int i = 1; i < target; i++) {
            ret = Math.max(ret, i * getMaxSum(target - i, nums));
        }
        return nums[target] = ret;
    }

    public static void main(String[] args) {

        int x = 20;
        int y = 3;
        System.out.println((double)x/y);
    }
}
