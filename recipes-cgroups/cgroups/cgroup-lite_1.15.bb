SUMMARY = "Light-weight package to set up cgroups at system boot."
DESCRIPTION =  "Light-weight package to set up cgroups at system boot."
HOMEPAGE = "http://packages.ubuntu.com/source/artful/cgroup-lite"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://debian/copyright;md5=5d5da4e0867cf06014f87102154d0102"
SRC_URI = "https://launchpad.net/ubuntu/+archive/primary/+files/cgroup-lite_1.15.tar.xz"
SRC_URI += "file://cgroups-init"
SRC_URI[md5sum] = "1438c1f4a7227c0dedfce5f86f02591d"
SRC_URI[sha256sum] = "02f44c70ed3cf27b9e89e5266492fddf4b455336ab4e03abc85e92297537201f"

FILES_${PN} += "${bindir}/"
FILES_${PN} += "${sysconfdir}/"

do_install() {
	install -d ${D}/bin
	install -m 0755 ${S}/scripts/cgroups-mount ${D}/bin
	install -m 0755 ${S}/scripts/cgroups-umount ${D}/bin

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/cgroups-init ${D}${sysconfdir}/init.d/cgroups-init

	install -d ${D}${sysconfdir}/rcS.d
	install -d ${D}${sysconfdir}/rc2.d
	install -d ${D}${sysconfdir}/rc3.d
	install -d ${D}${sysconfdir}/rc4.d
	install -d ${D}${sysconfdir}/rc5.d
	ln -sf ../init.d/cgroups-init ${D}${sysconfdir}/rcS.d/S98cgroups	
	ln -sf ../init.d/cgroups-init ${D}${sysconfdir}/rc2.d/S98cgroups	
	ln -sf ../init.d/cgroups-init ${D}${sysconfdir}/rc3.d/S98cgroups	
	ln -sf ../init.d/cgroups-init ${D}${sysconfdir}/rc4.d/S98cgroups	
	ln -sf ../init.d/cgroups-init ${D}${sysconfdir}/rc5.d/S98cgroups	
}
