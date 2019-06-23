package com.zcj.demo.testcollection;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 10062376
 * @Date: 2019/5/13 11:20
 * @Description:
 */
public class TestIPToLong {
    public static void main(String[] args){
        Long l1 = ipToLong("192.68.0.1");
        Long l2 = ipToLong("192.189.2.1");
        Long l3 = ipToLong("10.189.2.1");
        Long l4 = ipToLong("10.10.2.1");
        System.out.println(l1);
        System.out.println(l2);
        System.out.println(l3);
        System.out.println(l4);
    }
    public static long ipToLong(String ip) {
        String[] ipArray = ip.split("\\.");
        List ipNums = new ArrayList();
        for (int i = 0; i < 4; ++i) {
            ipNums.add(Long.valueOf(Long.parseLong(ipArray[i].trim())));
        }
        long longIp = ((Long) ipNums.get(0)).longValue() * 256L * 256L * 256L
                + ((Long) ipNums.get(1)).longValue() * 256L * 256L
                + ((Long) ipNums.get(2)).longValue() * 256L
                + ((Long) ipNums.get(3)).longValue();

        return longIp;
    }
}
