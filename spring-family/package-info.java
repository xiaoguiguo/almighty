package com.test.spring;
/**
 * SonarQube 本地代码扫描：
 * 1. 下载sonarqube：https://www.sonarsource.com/products/sonarqube/downloads/success-download-community-edition/
 * 2. 本地启动sonarqube，双击：D:\commonSoftware\sonarqube-10.0.0.68432\bin\windows-x86-64\startSonar.bat
 * 3. 在项目下的build.gradle中，plugins里添加：id "org.sonarqube" version "4.0.0.2929"
 * 4. 在idea中terminal下，执行命令：./gradlew sonar -D "sonar.projectKey=cyberlab-node" -D "sonar.host.url=http://localhost:9000" -D "sonar.token=sqp_d917e72f5806a703eec84b99c9e7bd8c773d58d2"
 * 5. 完成后，访问http://localhost:9000，其中token需要事先在sonar平台上创建
 */