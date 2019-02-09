package org.kwin.management.service.imp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kwin.management.entity.Installer;
import org.kwin.management.service.InstallerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class InstallerServiceImplTest {

    @Autowired
    InstallerService installerService;

    @Test
    public void add() {
        Installer installer = new Installer();
        installer.setInstallerName("Kwin");
        installer.setInstallerPhone("18989464565");

        installerService.add(installer);
        log.info("installer={}", installer);
    }

    @Test
    public void update() {
        Installer installer = new Installer();
        installer.setInstallerId(1);
        installer.setInstallerName("Jing");
        installer.setInstallerPhone("18989464565");

        installerService.update(installer);
        log.info("installer={}", installer);
    }

    @Test
    public void delete() {
    }
}