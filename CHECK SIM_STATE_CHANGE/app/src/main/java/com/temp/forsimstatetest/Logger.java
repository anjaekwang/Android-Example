package com.temp.forsimstatetest;

import android.util.Log;

public class Logger {

    public static final String TAG = "ForSimStateTest";

    /** 로그 레벨을 정의한다. 2:verbose이상, 3:debug이상, 4:info이상, 5:warn이상, 6:error이상, 0 또는 7이상 : 로그 기록 안함 */
    public static int mLogLevel = 3;


    /** AJK */
    /*public static int mLogFontWidth = BuildConfig.LOGFONTWIDTH;
    private static String temp (String str){
        int i;
        int diff = mLogFontWidth - str.length();
        StringBuilder dest = new StringBuilder();

        if(diff > 0 ) {
            dest.append(str);
            for (i = 0; i < diff; i++) {
                dest.append(' ');
            }
            return dest.toString();
        }
        return str;
    }*/

    private static String getTag(final Class<?> clazz){
        if (clazz != null) {
            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            if (elements != null) {
                for (StackTraceElement e : elements) {
                    if ((clazz.getName()).equals(e.getClassName()) || (clazz.getSimpleName()).equals(e.getClassName())) {
                        return TAG + "/" + clazz.getSimpleName() + "." + e.getMethodName() + "()";
                    }
                }
            }
        }
        return "";
    }
    private static String getLine(final Class<?> clazz){
        if (clazz != null) {
            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            if (elements != null) {
                for (StackTraceElement e : elements) {
                    if ((clazz.getName()).equals(e.getClassName()) || (clazz.getSimpleName()).equals(e.getClassName())) {
                        return "[L" + e.getLineNumber() + "]\t";
                    }
                }
            }
        }
        return "";
    }



    /** Send a {@link #VERBOSE} log message. */
    public static int v(final Class<?> clazz, final String msg) {
        if (isVerboseEnabled()) {
            return Log.println(Log.VERBOSE, getTag(clazz), getLine(clazz) + msg);
        }
        return 0;
    }

    /** Send a {@link #VERBOSE} log message and log the exception. */
    public static int v(final Class<?> clazz, final Throwable tr) {
        if (isVerboseEnabled()) {
            return Log.println(Log.VERBOSE, getTag(clazz), getLine(clazz) + Log.getStackTraceString(tr));
        }
        return 0;
    }

    /** Send a {@link #VERBOSE} log message and log the exception. */
    public static int v(final Class<?> clazz, final String msg, final Throwable tr) {
        if (isVerboseEnabled()) {
            return Log.println(Log.VERBOSE, getTag(clazz), getLine(clazz) + msg + '\n' + Log.getStackTraceString(tr));
        }
        return 0;
    }

    /** Send a {@link #DEBUG} log message. */
    public static int d(final Class<?> clazz, final String msg) {
        if (isDebugEnabled()) {
            return Log.println(Log.DEBUG, getTag(clazz), getLine(clazz)+ msg);
        }
        return 0;
    }

    /** Send a {@link #DEBUG} log message and log the exception. */
    public static int d(final Class<?> clazz, final Throwable tr) {
        if (isDebugEnabled()) {
            return Log.println(Log.DEBUG, getTag(clazz), getLine(clazz) + Log.getStackTraceString(tr));
        }
        return 0;
    }

    /** Send a {@link #DEBUG} log message and log the exception. */
    public static int d(final Class<?> clazz, final String msg, final Throwable tr) {
        if (isDebugEnabled()) {
            return Log.println(Log.DEBUG, getTag(clazz), getLine(clazz)+ msg + '\n' + Log.getStackTraceString(tr));
        }
        return 0;
    }

    /** Send an {@link #INFO} log message. */
    public static int i(final Class<?> clazz, final String msg) {
        if (isInfoEnabled()) {
            return Log.println(Log.INFO, getTag(clazz), getLine(clazz) + msg);
        }
        return 0;
    }

    /** Send a {@link #INFO} log message and log the exception. */
    public static int i(final Class<?> clazz, final Throwable tr) {
        if (isInfoEnabled()) {
            return Log.println(Log.INFO, getTag(clazz), getLine(clazz)+ Log.getStackTraceString(tr));
        }
        return 0;
    }

    /** Send a {@link #INFO} log message and log the exception. */
    public static int i(final Class<?> clazz, final String msg, final Throwable tr) {
        if (isInfoEnabled()) {
            return Log.println(Log.INFO, getTag(clazz), getLine(clazz) + msg + '\n' + Log.getStackTraceString(tr));
        }
        return 0;
    }

    /** Send a {@link #WARN} log message. */
    public static int w(final Class<?> clazz, final String msg) {
        if (isWarnEnabled()) {
            return Log.println(Log.WARN, getTag(clazz), getLine(clazz) + msg);
        }
        return 0;
    }

    /** Send a {@link #WARN} log message and log the exception. */
    public static int w(final Class<?> clazz, final Throwable tr) {
        if (isWarnEnabled()) {
            return Log.println(Log.WARN, getTag(clazz), getLine(clazz) + Log.getStackTraceString(tr));
        }
        return 0;
    }

    /** Send a {@link #WARN} log message and log the exception. */
    public static int w(final Class<?> clazz, final String msg, final Throwable tr) {
        if (isWarnEnabled()) {
            return Log.println(Log.WARN, getTag(clazz), getLine(clazz) + msg + '\n' + Log.getStackTraceString(tr));
        }
        return 0;
    }

    /** Send an {@link #ERROR} log message. */
    public static int e(final Class<?> clazz, final String msg) {
        if (isErrorEnabled()) {
            return Log.println(Log.ERROR, getTag(clazz), getLine(clazz) + msg);
        }
        return 0;
    }

    /** Send a {@link #ERROR} log message and log the exception. */
    public static int e(final Class<?> clazz, final Throwable tr) {
        if (isErrorEnabled()) {
            return Log.println(Log.ERROR, getTag(clazz), getLine(clazz)+ Log.getStackTraceString(tr));
        }
        return 0;
    }

    /** Send a {@link #ERROR} log message and log the exception. */
    public static int e(final Class<?> clazz, final String msg, final Throwable tr) {
        if (isErrorEnabled()) {
            return Log.println(Log.ERROR, getTag(clazz), getLine(clazz) + msg + '\n' + Log.getStackTraceString(tr));
        }
        return 0;
    }

    /** Stack Trace 로그를 기록한다. */
    public static final void stackTrace(final Class<?> clazz, final String msg) {
        stackTrace(Log.INFO, clazz, Logger.getClassLineNumber(clazz) + "|    " + msg);
    }

    public static final void stackTrace(final int level, final Class<?> clazz, final String msg) {
        if (isLevelEnabled(level)) {
            Thread th = Thread.currentThread();
            StackTraceElement[] stack = th.getStackTrace();

            StringBuilder sb = new StringBuilder();
            sb.append(msg).append('\n');
            for (StackTraceElement element : stack) {
                if (!"getStackTrace".equals(element.getMethodName()) && !"stackTrace".equals(element.getMethodName())) {
                    sb.append("\tat ").append(element.toString()).append('\n');
                }
            }
            Log.println(level, getTag(clazz), Logger.getClassLineNumber(clazz) + " - " + sb.toString());
        }
    }

    /**
     * VERBOSE 로그를 기록할 것인지 판단한다.
     */
    public static final boolean isVerboseEnabled() {
        return isLevelEnabled(Log.VERBOSE);
    }

    /**
     * DEBUG 로그를 기록할 것인지 판단한다.
     */
    public static final boolean isDebugEnabled() {
        return isLevelEnabled(Log.DEBUG);
    }

    /**
     * INFO 로그를 기록할 것인지 판단한다.
     */
    public static final boolean isInfoEnabled() {
        return isLevelEnabled(Log.INFO);
    }

    /**
     * WARN 로그를 기록할 것인지 판단한다.
     */
    public static final boolean isWarnEnabled() {
        return isLevelEnabled(Log.WARN);
    }

    /**
     * ERROR 로그를 기록할 것인지 판단한다.
     */
    public static final boolean isErrorEnabled() {
        return isLevelEnabled(Log.ERROR);
    }

    /**
     * ASSERT 로그를 기록할 것인지 판단한다.
     */
    public static final boolean isAssertEnabled() {
        return isLevelEnabled(Log.ASSERT);
    }

    /**
     * 로그 레벨을 확인해서 로그를 기록 할 것인지 확인한다.
     */
    public static final boolean isLevelEnabled(final int level) {
        return mLogLevel >= Log.VERBOSE && mLogLevel < (level + 1) && mLogLevel <= Log.ASSERT;
    }

    /** 클래스명과 메소드명, 라인번호를 기록할 수 있도록한다. */
    private static String getClassLineNumber(final Class<?> clazz) {
        if (clazz != null) {
            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            if (elements != null) {
                for (StackTraceElement e : elements) {
                    if ((clazz.getName()).equals(e.getClassName()) || (clazz.getSimpleName()).equals(e.getClassName())) {
                        return clazz.getSimpleName() + "." + e.getMethodName() + "() L" + e.getLineNumber();
                    }
                }
            }
        }
        return "";
    }
}
