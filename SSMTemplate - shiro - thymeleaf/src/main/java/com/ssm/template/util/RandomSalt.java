package com.ssm.template.util;

import java.util.Random;

/**
 * 生成六位数随机盐值
 */
public class RandomSalt {

    public static int numSalt() {
        Random random = new Random();
        return random.nextInt(899999) + 100000;
    }
}
