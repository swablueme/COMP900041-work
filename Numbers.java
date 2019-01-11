import java.util.Arrays;
import java.util.stream.Stream;
class Numbers {
    public static void main(String args[]) {

        //create int array from args
        int[] nums = Arrays.stream(args).mapToInt(x -> Integer.parseInt(x)).toArray();
        //sum
        System.out.printf("%d\n", nums[0] + nums[1]);
        //difference
        System.out.printf("%d\n", nums[0] - nums[1]);
        //product
        System.out.printf("%d\n", nums[0] * nums[1]);
        //quotient
        System.out.printf("%d\n", nums[0] / nums[1]);
        //modulus
        System.out.printf("%d\n", nums[0] % nums[1]);

    }

}