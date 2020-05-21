package com.wang.constants;

/**
 * 常量
 *
 * @author Chinhin
 * 2019-07-15
 */
public class Constants {

    public static final String EMPTY = "";
    public static final String UTF_8 = "UTF-8";
    public static final String COMMA = ",";
    public static final String SEMICOLON = ";";
    public static final String LR = "\r";
    public static final String LN = "\n";
    public static final String LT = "\t";
    public static final String LK = "%";
    public static final String STAR = "*";
    public static final String COLON = ":";
    public static final String DAWN = "、";
    public static final String SLASH = "/";
    public static final String SPACE = " ";
    public static final String HYPHEN = "-";
    public static final String EQUALS = "=";
    public static final String UNDER_CROSS = "_";
    public static final String AND = "&";

    public static final String DOUBLE_QUOTATION_MARKS = "\"";
    public static final String SINGLE_QUOTATION_MARKS = "\'";


    public static final String NULL = "null";
    public static final String OK = "OK";
    public static final String DEFAULT = "DEFAULT";

    public static final String HEADER_PARAM_TOKEN = "Authorization";
    public static final String HEADER_PARAM_LANGUAGE = "language";
    public static final String UNKONW = "unknown";

    public static final String ERROR_SYSTEM = "ERROR_SYSTEM";
    public static final String ERROR_TOKEN = "ERROR_TOKEN";
    public static final String ERROR_SAVE = "ERROR_SAVE";
    public static final String TRADE_PASSWORD_NOT_SET = "TRADE_PASSWORD_NOT_SET";

    public static final int UN_SETTLEMENT = 1;
    public static final int SETTLEMENT = 2;
    public static final int ORDER_CANCEL = 3;
    public static final int ORDER_REFUNDING = 4;
    public static final String ALL_DAY = "0-24";

    public static final String TCS = "TCS";
    public static final String TCG = "TCG";

    public static final String XLS = "xls";
    public static final String XLSX = "xlsx";
    public static final String POINT = ".";

    public static final String WALLET = "wallet";
    /**
     * 该字段不是动态数据源
     * 该字段的值为分库源的 键 需满足的条件 正则
     * 分库的源的取余顺序为从上到下
     */
    public static final String MATCH = "MATCH\\d";
    public static final String MATCH1 = "match1";
    public static final String MATCH2 = "match2";
    public static final String PREFIX = "spring.datasource.";
    public static final String JDBC_URL = "jdbc-url";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String DRIVE = "driver-class-name";
    public static final String MATCH_STATUS = "match_status";
    public static final String UPDATE_TIME = "update_time";
    public static final String MATCH_STATUS_LOCK = "match_status_lock";
    public static final String IP_PORT = "ip_port";
    public static final String IS_MAIN = "is_main";
    public static final String ALL_MATCH = "all_match";
    public static final String SATURATED = "saturated";
    public static final String STATUS = "status";
    public static final String LOAD = "load";
    public static final String EXT_OBJECT = "extObject";
    public static final String DEPTH_ASK = "ask";
    public static final String DEPTH_BID = "bid";
    public static final int STATUS_200 = 200;

    public static final String EXCHANGE_FANOUT_TYPE = "fanout";

    public static final String ZERO = "0";

    /**
     * 公钥
     */
    public static final String ACCESS_KEY = "accessKey";
    /**
     * 私钥
     */
    public static final String SECRET_KEY = "secretKey";
    /**
     * 签名方法
     */
    public static final String SIGNATURE_ALGORITHM = "HmacSHA256";
    /**
     * 接口所传字段名和签名所需
     */
    public static final String SIGN = "sign";
    /**
     * 切割签名字串的长度
     */
    public static final Integer SIGN_LEN = 8;
    /**
     * k线接口返回的条数
     */
    public static final Integer KLINE_COUNT = 300;

    /**
     * 一天的分钟数
     */
    public static final Integer ONE_DAY_MINUTES = 1440;

    /**
     * 一周的分钟数
     */
    public static final Integer ONE_WEEK_MINUTES = 10080;

    /**
     * 最新交易返回的条数
     */
    public static final Integer DEAL_COUNT = 150;

    /**
     * 验证ip地址的正则
     */
    public static final String IP_MATCHES = "((?:(?:25[0-5]|2[0-4]\\\\d|[01]?\\\\d?\\\\d)\\\\.){3}(?:25[0-5]|2[0-4]\\\\d|[01]?\\\\d?\\\\d))";

    /**
     * 逻辑删除 有效
     * */
    public static final Integer VALID = 0;

    /**
     * 逻辑删除 无效
     * */
    public static final Integer INVALID = 1;
}
