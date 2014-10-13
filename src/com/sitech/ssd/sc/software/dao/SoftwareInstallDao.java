package com.sitech.ssd.sc.software.dao;

import java.util.List;

import com.sitech.ssd.sc.software.domain.SoftwareInstallObj;

public interface SoftwareInstallDao {
	public int insertByObj(SoftwareInstallObj obj);

	public List queryForList(SoftwareInstallObj obj);

	public SoftwareInstallObj queryByObj(SoftwareInstallObj obj);

	public int updateByObj(SoftwareInstallObj obj);

	public int deleteByObj(SoftwareInstallObj obj);
}
