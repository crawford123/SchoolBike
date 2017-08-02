package com.paul.schoolbike.utils;

/**
 * desc : 本地常量
 * Created by yanggangjun on 16/5/11.
 */


public class Const {
    public static final String UPLOAD_COMPLETED = "upload_completed";
    public static final String UPLOADICON_COMPLETED = "uploadIcon_completed";
    public static final String CMPCONTACT = "cmpconcat";
    public static final int DELETE_ALBUMS = 200; //删除相册
    public static final String NEW_MSG = "news_msg_come";
    public static final String SELECT_FIND = "select_find";
    public static final String CHARGED = "充值成功";
    public static final int NEW_ACCOUNT = 225;
    public static final String UNBIND_MAIL = "UNBIND_MAIL";
    public static final String BIND_MAIL = "BIND_MAIL";
    public static final int SUCCESS_SEND_TOPIC = 222;
    public static final String LIKE = "223";
    public static final String UNLIKE = "224";
    public static final String COLLECT = "225";
    public static final String CANCLECOLLECT = "226";
    public static final int SET_SCREENLOCK = 1; //第一次设置锁屏密码
    public static final int COMFIRM_SCREENLOCK = 2;//第一次设置锁屏密码，二次确认密码
    public static final int CHECK_SCREENLOCK = 3;//打开应用检查密码
    public static final int CANCLE_SCREENLOCK = 4;//取消锁屏密码
    public static final int STARTAPP_SCREENLOCK = 5;//取消锁屏密码
    public static final String SCREENLOCK_SETTING = "SCREENLOCK_SETTING";
    public static final String HAS_SCREENLOCK = "HAS_SCREENLOCK";
    public static final String AUTO_LOGIN = "AUTO_LOGIN";
    public static final String AUTO_LOGIN_NAME = "AUTO_LOGIN_NAME";
    public static final String AUTO_LOGIN_PWD = "AUTO_LOGIN_PWD";
    public static boolean DEBUG_LOG = true;

    /**
     * SharedPreferences 常量存放
     */
    public static final class SharedPreferences {
        public static final String USER = "user_bean";
        public static final String LOADTYPE = "load_type";

    }


}
