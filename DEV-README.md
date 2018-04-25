# Setup new environment
* Download MDK from https://files.minecraftforge.net/
* Copy following files/folders from it:
    * .gitignore
    * build.gradle
    * gradle
    * gradle.properties
    * gradlew
    * gradlew.bat
    * src/main/resources/*
* Change version, group and archivesBaseName in build.gradle
* Add JEI dependency (https://github.com/mezz/JustEnoughItems/wiki/Getting-Started)
* Add InventoryTweaks dependency (https://github.com/Inventory-Tweaks/inventory-tweaks/issues/408#issuecomment-292771625)
* Run `chmod +x gradlew*`
* Open inteliJ and choose "Import Project" and select the build.gradle
    * Make sure the following checkboxes are checked:
        * Create directories for empty content roots automatically
        * Create separate module per source set
* Open the gradle Tab on the right side
* Create a run configuration for setupDecompWorkspace with VM Options: `-Xmx4g -Xms4g`
* Run the task
* Run task genIntellijRuns once
* Close and reopen the project
