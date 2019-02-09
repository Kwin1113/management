package org.kwin.management.service.imp;

import org.kwin.management.dao.InstallerMapper;
import org.kwin.management.entity.Installer;
import org.kwin.management.service.InstallerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstallerServiceImpl implements InstallerService {

    @Autowired
    InstallerMapper installerMapper;

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
    public Installer delete(String installerId) {
        //TODO
        return null;
    }
}
