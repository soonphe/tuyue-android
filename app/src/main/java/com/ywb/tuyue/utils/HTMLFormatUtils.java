package com.ywb.tuyue.utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * Description: html模板类
 * Created by wcystart on 2018/6/29.
 */

public class HTMLFormatUtils {
    public static String getNewContent(String htmltext) {

        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width", "100%").attr("height", "auto");
        }
//attr("-ms-interpolation-mode", "bicubic")
        return doc.toString();
    }
}
