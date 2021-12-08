import java.util.*;

public class res_sort {     //最后的排序是 一个单词 然后出现过的文档 按一个权重  多个词再看
    public static int get_term_frequency(daopaisuoyin ddd,String word,int d_number){      //单词 在文档d 中出现的次数
        return ddd.wordcount.get(ddd.wordmap.get(word)).get(ddd.count.get(ddd.wordmap.get(word)).lastIndexOf(d_number));
    }
    public static List<Integer> tf_sort(daopaisuoyin ddd,String word){
        List<Integer>res=new ArrayList<>();
        if (!ddd.wordmap.containsKey(word)){
            System.out.print("tf_sort word bucunzai");
            return res;
        }
        Map<String,Integer> tf_map = new HashMap<>();
        int wordindex=ddd.wordmap.get(word);
        for (int i=1;i<ddd.count.get(wordindex).size();i++){
            int d_number=ddd.count.get(wordindex).get(i);
            int tf_count=ddd.wordcount.get(ddd.wordmap.get(word)).get(ddd.count.get(ddd.wordmap.get(word)).lastIndexOf(d_number));
            tf_map.put(String.valueOf(d_number),tf_count);
        }


        List<Map.Entry<String,Integer>>list=new ArrayList<>(tf_map.entrySet());
        res_sort.ValueComparator vc=new ValueComparator();
        list.sort(vc);
        for (Map.Entry<String,Integer> s : list) {
            res.add(Integer.valueOf(s.getKey()));
        }
        return res;
    }
    private static class ValueComparator implements Comparator<Map.Entry<String,Integer>>
    {
        public int compare(Map.Entry<String,Integer> m,Map.Entry<String,Integer> n)
        {
            return n.getValue()-m.getValue();
        }
    }

    //文档频率 出现词项的文档数目  df 罕见词项高权重 常见词项低权重
    //对于含有两个以上查询词的query idf才会影响排序结果 一个词不会有影响
    public static double get_dfidf(daopaisuoyin d, String word, int d_number){
        double df=1+Math.log10((double)d.wordcount.get(d.wordmap.get(word)).get(d.count.get(d.wordmap.get(word)).lastIndexOf(d_number)));
        return df*Math.log10(d.file_num/df);
    }
    public static  List<Integer> dfidf_sort(daopaisuoyin ddd, String word1, String word2){
        List<Integer>res=new ArrayList<>();
        if (!ddd.wordmap.containsKey(word1)||!ddd.wordmap.containsKey(word2)) {
            System.out.print(word1 +" "+word2);
            System.out.print("dfidf_sort word bucunzai ");
            return res;
        }
        List<Integer>list1=ddd.count.get(ddd.wordmap.get(word1));
        List<Integer>list2=ddd.count.get(ddd.wordmap.get(word2));
        List<Integer>reslist=jiansuo.and(list1,list2);
        if (reslist.size()==0)
            return res;
        Map<String,Integer> dfidf_map = new HashMap<>();
        for (int d_number:reslist){
            double idf1=get_dfidf(ddd,word1,d_number);
            double idf2=get_dfidf(ddd,word2,d_number);
            int wdt=(int)(idf1+idf2);
            dfidf_map.put(String.valueOf(d_number),wdt);
        }

        List<Map.Entry<String,Integer>>list=new ArrayList<>(dfidf_map.entrySet());
        res_sort.ValueComparator vc=new ValueComparator();
        list.sort(vc);
        for (Map.Entry<String,Integer> s : list) {
            res.add(Integer.valueOf(s.getKey()));
        }
        return res;
    }
}
