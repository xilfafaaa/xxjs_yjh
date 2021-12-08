import java.util.ArrayList;
import java.util.List;

public class pinxiexiaozheng {
    public static List<String> res(daopaisuoyin d,String word){
        List<String> res=new ArrayList<>();
        res.add(" ");
        int len=2;
        for (String s:d.wordmap.keySet()) {
            if (s.equals(word))
                res.set(0,s);
            else {
                if (mindistance(word,s)<len)
                    res.add(s);
            }
        }
        return res;
    }

    public static int mindistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < dp.length; i++)
            dp[i][0] = i;
        for (int j = 0; j < dp[0].length; j++)
            dp[0][j] = j;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                int temp = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                int temp1 = word1.charAt(i - 1) == word2.charAt(j - 1) ? dp[i - 1][j - 1] : dp[i - 1][j - 1] + 1;
                dp[i][j] = Math.min(temp, temp1);
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}
