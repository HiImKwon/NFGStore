/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.crawl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Bui Quan
 */
public class Crawler {

    public static StreamSource inUseHTML = null;
    public static int pageCount = 0;

    public static void parseHTML(String uri, String beginPoint, String endPoint, String pagination)
            throws MalformedURLException, IOException {

        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("");
        boolean isInside = false;
        int count = 0;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
            URL url = new URL(uri);
            URLConnection uc = url.openConnection();
            uc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            uc.addRequestProperty("Accept-Charset", "UTF-8");
            is = uc.getInputStream();
            is = wellformHTML(is);
            isr = new InputStreamReader(is, "UTF-8");
            br = new BufferedReader(isr);
            String inputLine = null;

            while ((inputLine = br.readLine()) != null) {

                if (inputLine.contains(beginPoint)) {
                    if (count == 0) {
                        isInside = true;
                    }
                    count++;
                }
                if (inputLine.contains(endPoint)) {
                    isInside = false;
                }
                if (isInside) {
                    if (!pagination.equals("")) {
                        updatePageCount(inputLine, pagination);
                    }
                    htmlContent.append(inputLine);
                }
            }

//            is = new ByteArrayInputStream(htmlContent.toString().getBytes());
//            System.out.println(htmlContent);;
            if (htmlContent.toString().equals("")) {
                htmlContent = htmlContent.append("<divineProduct></divineProduct>");
            }
            inUseHTML = new StreamSource(new StringReader(htmlContent.toString()));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();

            }
            if (isr != null) {
                isr.close();

            }
            if (is != null) {
                is.close();

            }
        }
    }

    public static InputStream wellformHTML(InputStream pureHtml)
            throws UnsupportedEncodingException, IOException {
        StringBuffer sb = new StringBuffer();
        InputStreamReader isr = new InputStreamReader(pureHtml, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line;
        sb.append("<xml? version=\"1.0\" encoding=\"UTF-8\"?>\n");
        while ((line = br.readLine()) != null) {
            if (line.contains("<html") && !line.contains("xmlns=\"http://www.w3.org/1999/xhtml\"")) {
                line = line.replace("<html", "<html xmlns=\"http://www.w3.org/1999/xhtml\"");
            }
            if (line.contains("ng-app=")) {
                line = line.replace("ng-app=\"my-app\"", "");
            }
            if (line.contains("src") || line.contains("href")) {
                line = line.replace("&", "&amp;");
            }
            if (line.contains("<noscript><img")) {
                line = line.replace("<noscript>"
                        + "<img height=\"1\" width=\"1\" style=\"display:none\" src=\"https://www.facebook.com/tr?id=248291075662258&ev=PageView&noscript=1\"/>"
                        + "</noscript>", "");
            }
            if (line.contains("<style>")) {
                line = line.replace("<style>", "");
            }
            if (line.contains("</style>")) {
                line = line.replace("</style>", "");
            }
            if (line.contains("id=\"menulist\"")) {
                line = line.replace("id=\"menulist\"", "");
            }
            if (line.contains("<meta name=\"description\"")) {
                line = line.replace("<meta name=\"description\"", "<meta ");
            }
            if (line.contains("<img") && !line.contains("alt")) {
                line = line.replace("<img", "<img alt=\"\"");
            }
            if (line.contains("<meta name=\"_token\"")) {
                line = line.replace("<meta name=\"_token\"", "<meta ");
            }
            if (line.contains("<div") && line.contains("page_id")) {
                line = line.replace("page_id=\"1508057562857981\"", "");
            }
            if (line.contains("&laquo;")) {
                line = line.replace("&laquo;", "&#171;");
            }
            if (line.contains("&raquo")) {
                line = line.replace("&raquo;", "&#187;");
            }
            if (line.contains("controls")) {
                line = line.replace("controls", "");
            }
            if (line.contains("muted")) {
                line = line.replace("muted", "");
            }
            if (line.contains("<source")) {
                line = line.concat("</source>");
            }
            if (line.contains("<img") && !line.endsWith("/>")) {
                line = line.concat("</img>");
            }
            sb = sb.append(line).append("\n");
        }
        if (br != null) {
            br.close();
        }
        if (isr != null) {
            isr.close();
        }
        if (sb.toString().contains("<div class=\"row products-category\">")) {
            String tmp = sb.toString();
            tmp = tmp.replace("</img>", "");
            sb = new StringBuffer(tmp);
        }
        if (sb.toString().contains("<section class=\"detail-product\" >") || sb.toString().contains("<section class=\"wrapper-box\">")) {
            InputStream is = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
            return is;

        } else {
            sb = new StringBuffer(fixString(sb.toString()));
            InputStream is = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
            return is;
        }

    }

//    STACK
    public static boolean isAlphaChar(char input) {
        return (input >= 'a' && input <= 'z');
    }

    public static String getTagName(String content) {
        //kt có phải thẻ đóng sẵn như <br/> không
        if (content.charAt(content.length() - 2) == '/') {
            return null;
        }
        String res = "";
        int i = 1;
        //kt có phải thẻ đóng không
        if (content.charAt(1) == '/') {
            res = res + "/";
            i++;
        }
        //đọc tên thẻ
        while (isAlphaChar(content.charAt(i))) {
            res = res + content.charAt(i);
            i++;
        }
        //nếu chuỗi rỗng hoặc thiếu đóng tag thì trả về null
        if (res.length() == 0
                || (res.length() == 1 && res.charAt(0) == '/')) {
            return null;
        }
        return res;
    }

    public static String fixString(String content) {
        //stack để lưu trữ
        List<String> stack = new ArrayList<>();
        //list song song để lưu vị trí thẻ bỏ vô
        List<Integer> li = new ArrayList<>();
        //list thẻ phải bổ sung vào
        List<String> addTag = new ArrayList<>();

        int mark[] = new int[content.length()];
        Arrays.fill(mark, -1);

        int i = 0;
        while (i < content.length()) {
            if (content.charAt(i) == '<') {
                int j = i + 1;

                String tagTmp = "" + content.charAt(i);

                while (j < content.length() && content.charAt(j) != '>') {
                    tagTmp = tagTmp + content.charAt(j);
                    j++;
                }

                int curEnd = j;
                tagTmp = tagTmp + '>';
                i = j + 1;
                String tag = getTagName(tagTmp);

                if (tag != null) {
                    //xác định có phải thẻ mở k
                    //trường hợp là thẻ mở
                    if (tag.charAt(0) != '/') {
                        //đưa vô stack thẻ mở đã được bỏ các dâu <,>
                        stack.add(tag);
                        //đưa vô li vị trí của thẻ này trên dãy string đưa vô
                        li.add(curEnd);
                    } else {
                        while (stack.size() > 0) {
                            if (stack.get(stack.size() - 1).equals(tag.substring(1))) {
                                stack.remove(stack.size() - 1);
                                li.remove(li.size() - 1);
                                break;
                            } else {
                                //need do
                                addTag.add(stack.get(stack.size() - 1));
                                mark[li.get(li.size() - 1)] = addTag.size() - 1;
                                //remove
                                stack.remove(stack.size() - 1);
                                li.remove(li.size() - 1);
                            }
                        }
                    }
                }
            } else {
                i++;
            }
        }//end while
        while (stack.size() > 0) {
            addTag.add(stack.get(stack.size() - 1));
            mark[li.get(li.size() - 1)] = addTag.size() - 1;
            stack.remove(stack.size() - 1);
            li.remove(li.size() - 1);
        }

        String newContent = "";

        for (int j = 0; j < content.length(); j++) {
            newContent = newContent + content.charAt(j);
            if (mark[j] != -1) {
                newContent = newContent + "</" + addTag.get(mark[j]) + ">";
            }
        }

        return "<root>" + newContent + "</root>";
    }

    public static void updatePageCount(String content, String keyword) {
        if (content.contains(keyword)) {
            int pos = content.indexOf(keyword);

            int beginPage = pos + keyword.length();
            int endPage = -1;

            for (int i = beginPage; i < content.length(); i++) {
                if (content.charAt(i) == '"') {
                    endPage = i;
                    break;
                }

            }
            if (endPage != -1) {
                int num = Integer.parseInt(content.substring(beginPage, endPage));
                pageCount = Math.max(num, pageCount);
            }
        }
    }
}
