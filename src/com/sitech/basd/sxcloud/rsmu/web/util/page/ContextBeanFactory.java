// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   ContextBeanFactory.java

package com.sitech.basd.sxcloud.rsmu.web.util.page;

// Referenced classes of package com.infosp.base.context:
//            ContextBean

public class ContextBeanFactory {

	private static ContextBean contextBean;

	public ContextBeanFactory() {
	}

	public static synchronized ContextBean getContextBean(String pageSize) {
		if (contextBean == null)
			contextBean = new ContextBean(pageSize);
		return contextBean;
	}

	public static void setBean(ContextBean bean) {
		contextBean = bean;
	}
}
