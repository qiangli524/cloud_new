package com.sitech.basd.sxcloud.util.exception;

public class MySecurityException extends BaseException
{

    static final long serialVersionUID = 0xc875f450fb287d79L;
    static String msg = "��û���㹻�ķ���Ȩ�ޣ���������������µ�¼��֪ͨ����Ա��";

    public MySecurityException()
    {
        super(msg);
    }

    public MySecurityException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public MySecurityException(String message)
    {
        super(message);
    }

    public MySecurityException(Throwable cause)
    {
        super(msg, cause);
    }

}
