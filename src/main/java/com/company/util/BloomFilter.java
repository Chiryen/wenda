package com.company.util;

import com.company.service.SensitiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by John on 2017/7/20.
 */
@Service
public class BloomFilter implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveService.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("SensitiveWords.txt");
            InputStreamReader read = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineText;
            while ((lineText = bufferedReader.readLine()) != null) {
            //    add(lineText.trim());
            }
            read.close();
        } catch (Exception e) {
            logger.error("读取敏感词文件失败");
        }
    }

    private static final int DEFAULT_SIZE = 2 << 24;
    private static final int[] seeds = {3, 5, 7, 11, 13, 31,};
}
