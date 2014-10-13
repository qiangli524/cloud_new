// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi
// Source File Name:   SystemException.java

package com.sitech.basd.sxcloud.util.exception;


// Referenced classes of package com.infosp.base.exception:
//            BaseException

public class SystemException extends BaseException
{

    static final long serialVersionUID = 0x67ae6f2e11a0a2e1L;
    static String msg = "(ϵͳ���쳣)";

    public SystemException()
    {
        super(msg);
    }

    public SystemException(String message, Throwable cause)
    {
        super(msg + message, cause);
    }

    public SystemException(String message)
    {
        super(msg + message);
    }

    public SystemException(Throwable cause)
    {
        super(msg, cause);
    }

}
