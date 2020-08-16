import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] data = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        br.close();
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = 0; i < n; ++i) {
            if (max < data[i]) {
                maxIndex = i;
                max = data[i];
            }
        }
        System.out.println(maxIndex);
    }
}
