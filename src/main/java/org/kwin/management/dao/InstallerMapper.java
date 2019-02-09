package org.kwin.management.dao;

import org.kwin.management.entity.Installer;

public interface InstallerMapper {
    int deleteByPrimaryKey(Integer installerId);

    int insert(Installer record);

    int insertSelective(Installer record);

    Installer selectByPrimaryKey(Integer installerId);

    int updateByPrimaryKeySelective(Installer record);

    int updateByPrimaryKey(Installer record);
}