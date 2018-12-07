package com.keenoor.toolkit.utils;


import com.keenoor.toolkit.utils.constant.RegexConstants;

import org.junit.Test;

/**
 * @author: chenliuchun
 * Date:        2018/8/17
 * Description:
 * Modification History:
 * Date       Author       Version     Description
 * -----------------------------------------------------
 */
public class RegexUtilsTest {

    private final String regex = "^http://" + RegexConstants.REGEX_IP + ":" + RegexConstants.REGEX_PORT + "(/.*)?";

    @Test
    public void isMatch() {
        String url = "http://192.168.2.88:8080/asda-saf";
        boolean match = RegexUtils.isMatch(regex, url);
        System.out.println(match);
    }

    @Test
    public void getMatches() {
    }
}