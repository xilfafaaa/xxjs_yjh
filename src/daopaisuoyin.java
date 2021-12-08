import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class daopaisuoyin {
    int file_num=0;
    List<List<Integer>> count= new ArrayList<>();
    HashMap<String,Integer> wordmap= new HashMap<>();
    List<List<Integer>> wordcount=new ArrayList<>();
    HashMap<String,Integer> filemap=new HashMap<>();
    daopaisuoyin (String path){
        create_all(path);
    }
    public void create_all(String path) {
        int fileNum = 0, folderNum = 0;
        File file = new File(path);

        if (file.exists()) {
            if (null == file.listFiles()) {
                return;
            }
            LinkedList<File> list = new LinkedList<>(Arrays.asList(Objects.requireNonNull(file.listFiles())));
            while (!list.isEmpty()) {
                File[] files = list.removeFirst().listFiles();
                if (null == files) {
                    continue;
                }
                for (File f : files) {
                    if (f.isDirectory()) {
//                        System.out.println("文件夹  " + f.getAbsolutePath());
//                        System.out.println("文件夹  " + folderNum);
                        list.add(f);
                        folderNum++;
                    } else {
//                        System.out.println("文件  " + f.getAbsolutePath());
                        filemap.put(f.getAbsolutePath(),fileNum);
                        createdaopaisuoyin(readeveryfile(f),fileNum);
                        fileNum++;
                    }
                }
            }
        } else {
            System.out.println("文件不存在");
        }
        file_num=fileNum;
//        System.out.println("文件夹  " + folderNum + "   文件  " + fileNum);

    }
    public StringBuilder readeveryfile(File file){
        StringBuilder sb=new StringBuilder();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sb.append(tempStr);
            }
            reader.close();
            return sb;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }
    public void createdaopaisuoyin(StringBuilder sb,int n){     //n  wendang number
        String t=sb.toString();
        t=t.replace("_"," ");
        t=t.replace("|"," ");
        t=t.replace("~"," ");
        t=t.replace(":"," ");
        t=t.replace("'"," ' ");
        t=t.replace(","," ");
        t=t.replace("."," ");
        t=t.replace("."," ");
        t=t.replace("!"," ");
        t=t.replace("@"," ");
        t=t.replace("#"," ");
        t=t.replace("$"," ");
        t=t.replace("%"," ");
        t=t.replace("^"," ");
        t=t.replace("&"," ");
        t=t.replace("*"," ");
        t=t.replace("("," ");
        t=t.replace(")"," ");
        t=t.replace("="," = ");
        t=t.replace("<"," ");
        t=t.replace(">"," ");
        t=t.replace(";"," ");
        t=t.replace("?"," ");
        t=t.replace("/"," ");
        t=t.replace("-"," ");
        t=t.replace("\""," ");
        t=t.replace("\t"," ");

        for (String s:t.split(" ")){
            if (s.equals(""))
                continue;
            if (wordmap.containsKey(s)){            //这个词已经出现过
                int wendang_index=count.get(wordmap.get(s)).lastIndexOf(n);     //这个词的小列表里 编号n的文档的位置
                if (wendang_index<1){                //这个词 在编号n的文档里没有出现
                    count.get(wordmap.get(s)).add(n);               //把文档号n加入小列表
                    count.get(wordmap.get(s)).set(0,count.get(wordmap.get(s)).get(0)+1);    //这个词 出现过的总文档数+1
                    wordcount.get(wordmap.get(s)).add(1);       //每个文档出现几次的大列表加入这个文档对应的初始次数 1
                }
                else {                              //这个词 在编号n的文档里出现过 编号n的文档在 第一个小列表的位置是wendang_index
                    int wordindex=wordmap.get(s);           //词在大列表的第几个
                    int wordcountt=wordcount.get(wordindex).get(wendang_index);    //单词 小列表里 每个文档位置对应的是在文档里出现的次数
                    wordcount.get(wordindex).set(wendang_index,wordcountt+1);
                }
            }
            else {                               //这个词  还没出现过
                List<Integer> temp = new ArrayList<>();
                temp.add(1);
                temp.add(n);
                count.add(temp);

                List<Integer> wordtemp = new ArrayList<>();
                wordtemp.add(1);
                wordtemp.add(1);
                wordcount.add(wordtemp);

                wordmap.put(s, count.size() - 1);
            }
        }
    }
    public void show(){
        for (String s:wordmap.keySet()) {
            System.out.println(s+" "+count.get(wordmap.get(s)));
//            System.out.println("\t"+wordcount.get(wordmap.get(s)));
        }
    }
}
