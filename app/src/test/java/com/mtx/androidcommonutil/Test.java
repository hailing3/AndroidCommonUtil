package com.mtx.androidcommonutil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    @org.junit.Test
    public void test() {
        String returnXml = "<resultdescription>单据  16613dd7d9a00000000000000000000vouchergl0  开始处理...单据  16613dd7d9a00000000000000000000vouchergl0  处理完毕!"
                + "</resultdescription>"
                + "<content>2018.09-记账凭证-5</content>"
                + "<billpk></billpk><bdocid>16613dd7d9a00000000000000000000vouchergl0</bdocid>"
                + "<filename>vouchergld861102.xml</filename><resultcode>1</resultcode>"
                + "<resultdescription>单据  16613dd7d9a00000000000000000000vouchergl0  开始处理...单据  16613dd7d9a00000000000000000000vouchergl0  处理完毕!"
                + "</resultdescription>"
                + "<content>2018.09-记账凭证-6</content></sendresult>";
        String regex = "<content>(.*?)</content>";
        List<String> list = new ArrayList<String>();
        List<String> extvounoLists = new ArrayList<String>();
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(returnXml);
        while (m.find()) {
            int i = 1;
            list.add(m.group(i));
            i++;
        }

        for (String str : list) {
            System.out.println(str);
            String[] strs = str.split("-");
            String strss = strs[strs.length - 1];
            extvounoLists.add(strs[strs.length - 1]);
        }

        for (String string : extvounoLists) {
            System.out.println(string);
        }
    }


    @org.junit.Test
    public void test2() {
        // 问题：重复名称，无法找到后面的位置
        String content = "456//@咖啡瘾少女2：123//@咖啡瘾少女2：再次转发//@我爱咖啡瘾少女2：123//@我不爱咖啡瘾少女2：123";
        String regex = "//@(.*?)：";
        List<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(content);
        while (m.find()) {
            list.add(m.group(1));
        }
        for (String string : list) {
            int startIndex = content.indexOf(string);
            int endIndex = startIndex + string.length();
            System.out.println(string + ", index = " + startIndex + "-" + endIndex);

        }
    }

    @org.junit.Test
    public void test3() {
        String content = "456//@咖啡瘾少女2：111//@咖啡瘾少女2：123//@咖啡瘾少女2：再次转发//@我爱咖啡瘾少女2：123//@我不爱咖啡瘾少女2：123";
        char[] cArray = content.toCharArray();

        int startIndex = -1;
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < cArray.length; i++) {
            char c = cArray[i];
            if (isStartOrEnd(cArray, i, PREFIX)) { // 是开始位置
                startIndex = i + PREFIX.length();
                sb = new StringBuffer();
                i = startIndex - 1;
            } else if (isStartOrEnd(cArray, i, SUFFIX)) { // 是结束位置
                String result = sb.toString();
                System.out.println(result + ' ' + startIndex + "-" + i);

                startIndex = -1;
                sb = new StringBuffer();
            } else if (startIndex > 0) { // 已经开始了，追加字符串
                sb.append(c);
            }
        }

    }

    private static final String PREFIX = "//@";
    private static final String SUFFIX = "："; // 注意此处是中文冒号

    /**
     * 是否是开始或结束的位置
     *
     * @param cArray      源字符串
     * @param i           开始检查的位置
     * @param preOrSufFix 进行对比的前缀或后缀字符串
     * @return
     */
    private boolean isStartOrEnd(char[] cArray, int i, String preOrSufFix) {
        if (i >= 0 && i + preOrSufFix.length() - 1 < cArray.length) {
            char[] prefixArray = preOrSufFix.toCharArray();
            for (int j = 0; j < prefixArray.length; j++) {
                if (cArray[i + j] != prefixArray[j]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
