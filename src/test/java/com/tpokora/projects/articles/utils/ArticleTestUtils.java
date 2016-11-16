package com.tpokora.projects.articles.utils;

import com.tpokora.projects.articles.model.Article;
import com.tpokora.projects.user.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pokor on 26.03.2016.
 */
public class ArticleTestUtils {

    public static List<Article> generateArticles(final int amount, final Integer userId) {
        return new ArrayList<Article>() {
            {
                User user = new User();
                user.setId(userId);

                for (int i = 0; i < amount; i++) {
                    add(new Article(i, "article" + i, "articleContent" + i, new Date(),user ));
                }
            }
        };
    }
}
