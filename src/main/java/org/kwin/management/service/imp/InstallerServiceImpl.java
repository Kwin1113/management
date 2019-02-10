package org.kwin.management.service.imp;

import org.kwin.management.dao.InstallerMapper;
import org.kwin.management.entity.Installer;
import org.kwin.management.service.InstallerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstallerServiceImpl implements InstallerService {

    @Autowired
    InstallerMapper installerMapper;

    @Override
    public List<Installer> list() {
        List<Installer> installerList = installerMapper.selectAll();
        return installerList;
    }

    @Override
    public Installer selectOne(Integer installerId) {
        Installer installer = installerMapper.selectByPrimaryKey(installerId);
        return installer;
    }

    @Override
    public Installer add(Installer installer) {
        installerMapper.insert(installer);
        return installer;
    }

    @Override
    public Installer update(Installer installer) {
        installerMapper.updateByPrimaryKeySelective(installer);
        return installer;
    }

    @Override
    public void delete(Integer installerId) {
        installerMapper.deleteByPrimaryKey(installerId);
    }
}
