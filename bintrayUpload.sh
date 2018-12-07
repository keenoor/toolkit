#!/usr/bin/env bash

gradlew clean build -x test generatePomFileForMavenPublication publishMavenPublicationToMavenLocal bintrayUpload -PbintrayUser=BINTRAY_USER -PbintrayKey=BINTRAY_KEY -PdryRun=false