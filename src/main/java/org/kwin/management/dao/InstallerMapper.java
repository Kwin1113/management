package org.kwin.management.dao;

import org.kwin.management.entity.Installer;

import java.util.List;

public interface InstallerMapper {
    int deleteByPrimaryKey(Integer installerId);

    int insert(Installer record);

    int insertSelective(Installer record);

    Installer selectByPrimaryKey(Integer installerId);

    List<Installer> selectAll();

    int updateByPrimaryKeySelective(Installer record);

    int updateByPrimaryKey(Installer record);
}