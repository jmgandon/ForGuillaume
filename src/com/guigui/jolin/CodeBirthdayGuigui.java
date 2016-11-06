/////////////////////////////////////////////////////////////////////////////
//  Copyright JMG
/////////////////////////////////////////////////////////////////////////////
package com.guigui.jolin;

import java.nio.charset.Charset;


public class CodeBirthdayGuigui
{
    final static  String OBFUSCATION_KEY	=	"CoucouGuiguiCodePourTonAnniv" ;


    protected CodeBirthdayGuigui() {};

    public static byte[] CodePourCadeau(byte []inBuf)
    {
        byte []outBuf = null ;
        String key = OBFUSCATION_KEY ;
        int	 len = inBuf.length + key.length() ;
        byte []InitialPass = new byte[len];
        char CriptChar = 0x0 ;
        int i = 0 ;

        for(i = 0; i < key.length(); i++){
          InitialPass[i] = (byte)key.charAt(i);
        }
        for(int j = 0; j < inBuf.length; j++){
          InitialPass[j + key.length()] = inBuf[j];
        }


        byte [] CriptPassTemp = new byte[6 * len  + 6] ;
        String temp =  String.valueOf(len);
        temp += "996" ;

        int outLen = temp.length() ;
        for(i = 0 ; i < outLen; i++)
          CriptPassTemp[i] = (byte)temp.charAt(i) ;

        for(i=0; i<len; i++)
        {
            CriptChar = (char)(InitialPass[i] + i - 11);
            if(CriptChar <10)
            {
                temp += "1";
                temp += String.valueOf(CriptChar);
            }
            else if(CriptChar<100)
            {
                temp += "2";
                temp += String.valueOf(CriptChar/10);
                temp += String.valueOf(CriptChar % 10);
            }
            else if(CriptChar<1000)
            {
                temp += "3";
                temp += String.valueOf(CriptChar/100);
                temp += String.valueOf((CriptChar % 100)/10);
                temp += String.valueOf(CriptChar % 10);
            }
            else if(CriptChar<10000)
            {
                temp += "4";
                temp += String.valueOf(CriptChar/1000);
                temp += String.valueOf((CriptChar % 1000)/100);
                temp += String.valueOf((CriptChar % 100)/10);
                temp += String.valueOf(CriptChar % 10);
            }
            else if(CriptChar<100000)
            {
                temp += "5";
                temp += String.valueOf(CriptChar/10000);
                temp += String.valueOf((CriptChar % 10000)/1000);
                temp += String.valueOf((CriptChar % 1000)/100);
                temp += String.valueOf((CriptChar % 100)/10);
                temp += String.valueOf(CriptChar % 10);
            }
        }


        try
        {
          outBuf = temp.getBytes("UTF-8");
        }
        catch(java.io.UnsupportedEncodingException ue)
        {
          System.out.println("Unsupported Encoding");
          return null ;
        }

        return outBuf ;
    }


    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            usage();
        }

        String passInClear = args[0];
        byte[] passWord = passInClear.getBytes(Charset.forName("UTF-8"));
        byte[] encryotpwd = CodePourCadeau(passWord);
        String res = new String(encryotpwd, "UTF-8").substring(0,args[0].length());

        System.out.println(res);
    }

    private static void usage() {
        System.out.println("Usage: CodeBirthdayGuigui  <password>");
        System.out.println("");
        System.exit(-1);
    }
}

