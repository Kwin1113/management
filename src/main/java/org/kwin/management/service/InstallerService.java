package org.kwin.management.service;

import org.kwin.management.entity.Installer;

import java.util.List;

public interface InstallerService {

    List<Installer> list();

    Installer selectOne(Integer installerId);

    Installer add(Installer installer);

    Installer update(Installer installer);

    void delete(Integer installerId);
}
