package com.dj.sometest.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Vector;


import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * @Author: Chris
 * @Date: 2020/8/19 15:28
 */
public class SftpUtil {

    static Session sshSession = null;
    static Channel channel = null;
    static ChannelSftp sftp = null;


    static {
        String host = "192.168.100.128";
        int port = 22;
        String userName = "root";
        String passWord = "123456";
        try {
            JSch jsch = new JSch();
            jsch.getSession(userName, host, port);
            sshSession = jsch.getSession(userName, host, port);
            System.out.println("Session created.");
            sshSession.setPassword(passWord);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            System.out.println("Session connected.");
            channel = sshSession.openChannel("sftp");
            channel.connect();
            System.out.println("Opening Channel.");
            sftp = (ChannelSftp) channel;
            System.out.println("Connected to " + host + ".");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void upload(String directory, String uploadFile) {
        try {
            sftp.cd(directory);
            File file = new File(uploadFile);
            sftp.put(new FileInputStream(file), file.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void upload2(String directory,InputStream inputStream, String fileName) {
        try {
            sftp.cd(directory);
            sftp.put(inputStream, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 上传流
     *
     * @param sInputString 要上传的字符串
     * @param directory    上传的目录
     * @param dst          目标文件名
     */
    public static void upload(String sInputString, String directory, String dst) {
        try {
            sftp.cd(directory);
            InputStream is = getStringStream(sInputString);
            sftp.put(is, dst);
            System.out.println("上传完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static InputStream getStringStream(String s) {
        ByteArrayInputStream tInputStringStream = null;
        try {
            tInputStringStream = new ByteArrayInputStream(s.getBytes());
            return tInputStringStream;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                tInputStringStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static InputStream strToStream(String sInputString) {
        if (sInputString != null && !sInputString.trim().equals("")) {
            try {
                ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
                return tInputStringStream;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }


    public static String streamToStr(InputStream tInputStream) {
        if (tInputStream != null) {
            try {
                BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(tInputStream));
                StringBuffer tStringBuffer = new StringBuffer();
                String sTempOneLine;
                while ((sTempOneLine = tBufferedReader.readLine()) != null) {
                    tStringBuffer.append(sTempOneLine);
                }
                return tStringBuffer.toString();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
