package org.kwin.management.service;

import org.kwin.management.entity.Installer;

public interface InstallerService {
    Installer add(Installer installer);

    Installer update(Installer installer);

    Installer delete(String installerId);
}
