package edu.njucm.book.chart.utils;

import java.io.*;

public class ProcessFile {
    static void modifyFile(String path){
        BufferedReader bufferedReader=null;
        BufferedWriter bufferedWriter=null;
        try {
            InputStreamReader file=new InputStreamReader(new FileInputStream(path),"utf-8");
            String line;
            StringBuffer stringBuffer=new StringBuffer("[");
            bufferedReader=new BufferedReader(file);
            while((line=bufferedReader.readLine())!=null){
                line=line.substring(line.indexOf(' ')+1).trim().replace("格式","").toLowerCase();
                line="\"."+line+"\"";
                stringBuffer.append(line+",");
            }
            stringBuffer.deleteCharAt(stringBuffer.length()-1);
            stringBuffer.append("]");

            FileOutputStream out=new FileOutputStream(path);
            bufferedWriter=new BufferedWriter(new OutputStreamWriter(out));
            System.out.println(stringBuffer.toString());
            bufferedWriter.write(stringBuffer.toString());
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
            e.printStackTrace();
        }catch (UnsupportedEncodingException e){
            System.out.println("UnsupportedEncodingException");
            e.printStackTrace();
        }catch(IOException e){
            System.out.println("IOException");
            e.printStackTrace();
        }finally {
            try {
                if(bufferedReader!=null)
                    bufferedReader.close();
            } catch (IOException e) {
                System.out.println("关闭输入流 出错");
                e.printStackTrace();
            }
            try {
                if(bufferedWriter!=null)
                    bufferedWriter.close();
            } catch (IOException e) {
                System.out.println("关闭输出流 出错");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String path="C:\\Users\\黄皓\\Desktop\\old.txt";
        modifyFile(path);
    }
}
