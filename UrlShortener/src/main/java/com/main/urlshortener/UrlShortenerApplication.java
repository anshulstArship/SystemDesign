package com.main.urlshortener;

import com.main.urlshortener.service.Base62Decoding;
import com.main.urlshortener.service.Base62Encoding;
import com.main.urlshortener.service.RandomShortUrl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrlShortenerApplication {

    public static void main(String[] args) {

        SpringApplication.run(UrlShortenerApplication.class, args);
        RandomShortUrl shortUrl = new RandomShortUrl();
        System.out.println(shortUrl.generateShortUrl(7));

        Base62Encoding getShortUrl = new Base62Encoding();
        System.out.println(getShortUrl.generateShortUrl("getTheServiceApplication"));

        Base62Decoding getLongUrl = new Base62Decoding();
        System.out.println(getLongUrl.generateLongUrl("2tx"));
    }

}
