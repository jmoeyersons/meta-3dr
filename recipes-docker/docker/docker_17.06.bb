SUMMARY = "Package to deploy docker arm binaries and setup dockerd as service"
DESCRIPTION =  "Package to deploy docker arm binaries and setup dockerd as service"
HOMEPAGE = "unknown"


LICENSE = "CLOSED"

SRC_URI = "file://docker"
SRC_URI += "file://docker-containerd"
SRC_URI += "file://docker-containerd-ctr"
SRC_URI += "file://docker-containerd-shim"
SRC_URI += "file://docker-init"
SRC_URI += "file://docker-proxy"
SRC_URI += "file://docker-runc"
SRC_URI += "file://dockerd"
SRC_URI += "file://docker.init"


FILES_${PN} = "${bindir} ${sysconfdir}"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/docker ${D}${bindir}/docker
	install -m 0755 ${WORKDIR}/docker-containerd ${D}${bindir}/docker-containerd
	install -m 0755 ${WORKDIR}/docker-containerd-shim ${D}${bindir}/docker-containerd-ctr
	install -m 0755 ${WORKDIR}/docker-init ${D}${bindir}/docker-init
	install -m 0755 ${WORKDIR}/docker-proxy ${D}${bindir}/docker-proxy
	install -m 0755 ${WORKDIR}/docker-runc ${D}${bindir}/docker-runc
	install -m 0755 ${WORKDIR}/dockerd ${D}${bindir}/dockerd


	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/docker.init ${D}${sysconfdir}/init.d/docker.init

	# set up to start docker daemon as service
	install -d ${D}${sysconfdir}/rc0.d
	install -d ${D}${sysconfdir}/rc1.d
	install -d ${D}${sysconfdir}/rc2.d
	install -d ${D}${sysconfdir}/rc3.d
	install -d ${D}${sysconfdir}/rc4.d
	install -d ${D}${sysconfdir}/rc5.d
	install -d ${D}${sysconfdir}/rc6.d

	ln -sf ../init.d/docker.init ${D}${sysconfdir}/rc0.d/K09docker
	ln -sf ../init.d/docker.init ${D}${sysconfdir}/rc1.d/K09docker
	ln -sf ../init.d/docker.init ${D}${sysconfdir}/rc2.d/S99docker
	ln -sf ../init.d/docker.init ${D}${sysconfdir}/rc3.d/S99docker
	ln -sf ../init.d/docker.init ${D}${sysconfdir}/rc4.d/S99docker
	ln -sf ../init.d/docker.init ${D}${sysconfdir}/rc5.d/S99docker
	ln -sf ../init.d/docker.init ${D}${sysconfdir}/rc6.d/K09docker

}
