import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class jiansuo {

    public static String jiansuo(daopaisuoyin d, String s){
        String match = "(?<word1>\\w+)[ ]+(?<judge>[ANDORT]{2,3})[ ]+(?<word2>\\w+)";
        Pattern pattern = Pattern.compile(match);
        Matcher matcher = pattern.matcher(s);
        if (!matcher.find()) {
            if (pinxiexiaozheng.res(d,s).get(0).equals(" ")) {
                String res="<body><p align=\\\"center\\\">"+s+" 没有   匹配了距离较近的:<br/>  "+pinxiexiaozheng.res(d, s).get(1)+"     "+pinxiexiaozheng.res(d,s)+"<br/>";
                return res+res_sort.tf_sort(d, pinxiexiaozheng.res(d, s).get(1))+"</p></body>";
            }
            return res_sort.tf_sort(d,pinxiexiaozheng.res(d,s).get(0)).toString();

        }
//        System.out.println(boolljiansuo(d,s));
        return boolljiansuo(d,s).toString();
    }
    public static List<Integer> boolljiansuo(daopaisuoyin d, String s){
        String match = "(?<word1>\\w+)[ ]+(?<judge>[ANDORT]{2,3})[ ]+(?<word2>\\w+)";
        Pattern pattern = Pattern.compile(match);
        Matcher matcher = pattern.matcher(s);
        if (!matcher.find())
            return new ArrayList<>();
        List<Integer> list1=d.count.get(d.wordmap.get(matcher.group("word1")));
        List<Integer> list2=d.count.get(d.wordmap.get(matcher.group("word2")));
        if (matcher.group("judge").equals("AND"))
            return res_sort.dfidf_sort(d,matcher.group("word1"),matcher.group("word2"));
        if (matcher.group("judge").equals("OR"))
            return or(list1,list2);
        if (matcher.group("judge").equals("NOT"))
            return not(list1,list2);
        return new ArrayList<>();
    }

    static List<Integer> and(List<Integer> list1,List<Integer> list2){
        List<Integer> res=new ArrayList<>();
        if (list1.size()<2 || list2.size()<2)
            return res;
        for (int j=1;j<list2.size();j++){
            if (list1.contains(list2.get(j)))
                res.add(list2.get(j));
        }
        return res;
    }

    static List<Integer> or(List<Integer> list1,List<Integer> list2){
        List<Integer> res=new ArrayList<>();
        if (list1.size()<2 || list2.size()<2)
            return res;
        res.addAll(list1);
        list1.remove(0);
        for (int j=1;j<list2.size();j++){
            if (!list1.contains(list2.get(j)))
                res.add(list2.get(j));
        }
        Collections.sort(res);
        return res;
    }

    static List<Integer> not(List<Integer> list1,List<Integer> list2){
        List<Integer> res=new ArrayList<>();
        if (list1.size()<2 || list2.size()<2)
            return res;
        for (int i=1;i<list1.size();i++){
            if (!list2.contains(list1.get(i)))
                res.add(list1.get(i));
        }
        return res;
    }
    public static void showres(List<Integer> res){
        for (Integer n:res) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    public static void showwd(List<String> res){
        for (String n:res) {
            System.out.print(n + " ");
        }
        System.out.println();
    }


}
