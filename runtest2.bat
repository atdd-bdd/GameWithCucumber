
set THISPATH="C:\Users\KenV1\IdeaProjects\jarfiles\*;C:\Users\KenV1\IdeaProjects\jarfiles; C:\Users\KenV1\IdeaProjects\GameWithCucumber\build\classes\kotlin\main;C:\Users\KenV1\IdeaProjects\GameWithCucumber\build\classes\kotlin\test;"
echo %THISPATH%
java -cp %THISPATH% org.junit.runner.JUnitCore   build.classes.kotlin.test.RunKukesTest
rem java -cp %THISPATH% io.cucumber.core.cli.Main game.feature
 
