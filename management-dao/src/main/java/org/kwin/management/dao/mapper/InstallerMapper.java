package org.kwin.management.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.kwin.management.entity.Installer;

import java.util.List;

public interface InstallerMapper {
    int deleteByPrimaryKey(@Param("installerId")Integer installerId,@Param("userId") Integer userId);

    int insert(@Param("installer")Installer record,@Param("userId")Integer userId);

    int insertSelective(@Param("installer")Installer record,@Param("userId")Integer userId);

    Installer selectByPrimaryKey(@Param("installerId")Integer installerId,@Param("userId")Integer userId);

    List<Installer> selectAll(Integer userId);

    int updateByPrimaryKeySelective(@Param("installer")Installer record,@Param("userId")Integer userId);

    int updateByPrimaryKey(@Param("installer")Installer record,@Param("userId")Integer userId);
}