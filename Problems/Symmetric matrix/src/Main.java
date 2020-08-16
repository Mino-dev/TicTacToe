import java.io.*;
import java.util.*;
class Main {
    public static boolean check(List<List<String>> data, int n) {
        for  (int i = 0; i < n; i++) {
            for  (int j = 0; j < n; j++) {
                if (i != j && !data.get(i).get(j).equals(data.get(j).get(i))) {
                    return false;        
                }
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<List<String>> data = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            data.add(Arrays.asList(br.readLine().split(" ")));
        }
        br.close();
        System.out.println(check(data, n) ? "YES" : "NO");
        
    }
}
