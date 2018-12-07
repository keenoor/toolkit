#!/usr/bin/env bash

gradlew clean build generatePomFileForMavenPublication publishMavenPublicationToMavenLocal bintrayUpload -PbintrayUser=BINTRAY_USER -PbintrayKey=BINTRAY_KEY -PdryRun=false