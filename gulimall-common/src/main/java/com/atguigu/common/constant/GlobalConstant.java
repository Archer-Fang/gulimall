package com.atguigu.common.constant;

/**
 * @author Created by Fangzj
 * @data 2021/6/18 21:35
 **/
public class GlobalConstant {


    public enum HostEnum {
        TENCENTCLOUD("81.69.232.225", "腾讯云");

        private String host;
        private String message;
        HostEnum(String host, String message) {
            this.host = host;
            this.message = message;
        }

        public String getHost() {
            return host;
        }

        public String getMessage() {
            return message;
        }
    }

}
