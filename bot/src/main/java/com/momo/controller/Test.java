package com.momo.controller;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        String head = "https://warframe.huijiwiki.com/index.php?title=分类:MOD";

        boolean hasNext = true;
        String url = head;
        while (hasNext) {
            try {
                Connection connect = Jsoup.connect(url);
                Document document = connect.get();

                //获取 MOD
                Elements li = document.getElementsByTag("li");
                for (Element element : li) {
                    if(element.hasText()) {
                        if(element.text().length() > 15) {
                            continue;
                        } else {
                            System.out.println(element.text());
                        }
                    }
                }


                //获取下一页
                Elements a = document.getElementsByTag("a");

                boolean isNext = false;
                for (Element element : a) {
                    if (element.hasText()) {
                        if ("下一页".equals(element.text())) {
                            isNext = true;
                            String href = element.attr("href");
                            String[] split = href.split("&");
                            url = head + "&".concat(split[1]);
                            break;
                        }
                    }
                }

                if (!isNext) {
                    hasNext = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
