
set THISPATH="C:/Users/KenV1/AppData/Roaming/JetBrains/IdeaIC2022.2/plugins/cucumber-java/lib/*;  C:/Users/KenV1/AppData/Roaming/JetBrains/IdeaIC2022.2/plugins/cucumber-kotlin/lib/*; C:/Users/KenV1/.gradle/caches/modules-2/files-2.1/io.cucumber/cucumber-junit/7.6.0/7fad691eff13eb623e86433b80275c92b165328b/cucumber-junit-7.6.0.jar;C:/Users/KenV1/IdeaProjects/GameWithCucumber/build/classes/kotlin/main/*"


echo %THISPATH%
java -cp %THISPATH% org.junit.runner.JUnitCore RunCukesTest
java -cp %THISPATH% io.cucumber.core.cli.Main RunCukesTest
